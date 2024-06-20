package ru.ddc.b2bcolab.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import ru.ddc.b2bcolab.controller.payload.ChangePasswordRequest;
import ru.ddc.b2bcolab.controller.payload.LoginRequest;
import ru.ddc.b2bcolab.controller.payload.RegisterCustomerRequest;
import ru.ddc.b2bcolab.model.Authority;
import ru.ddc.b2bcolab.model.AuthorityDetails;
import ru.ddc.b2bcolab.model.Customer;
import ru.ddc.b2bcolab.model.Role;
import ru.ddc.b2bcolab.repository.AuthorityRepository;
import ru.ddc.b2bcolab.repository.CustomerRepository;
import ru.ddc.b2bcolab.utils.RegisterCustomerRequestValidator;
import ru.ddc.b2bcolab.utils.TestPasscodeValidator;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final RegisterCustomerRequestValidator registerCustomerRequestValidator;
    private final AuthenticationManager authenticationManager;
    private final HttpSessionSecurityContextRepository contextRepository = new HttpSessionSecurityContextRepository();
    private final TestPasscodeValidator passcodeValidator;

    public void createCustomer(RegisterCustomerRequest request,
                               BindingResult bindingResult,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) throws BindException {
        registerCustomerRequestValidator.validate(request, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        Customer customer = Customer.builder()
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Role.ROLE_USER)
                .build();
        customerRepository.save(customer);

        UsernamePasswordAuthenticationToken token =
                UsernamePasswordAuthenticationToken.unauthenticated(customer, null);
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(token);
        SecurityContextHolder.setContext(context);
        contextRepository.saveContext(context, httpServletRequest, httpServletResponse);

        passcodeValidator.sendPasscode(request.getEmail());

    }

    public void registerCustomer(String passcode,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse) {
        SecurityContext context = SecurityContextHolder.getContext();
        if (passcodeValidator.validate(passcode)) {
            Authentication authentication = context.getAuthentication();
            Customer customer = (Customer) authentication.getPrincipal();
            customerRepository.save(customer);
            if (!customer.getCustomerAuthorities().isEmpty()) {
                customer.getCustomerAuthorities().forEach(authorityRepository::save);
            }
            // login
            UsernamePasswordAuthenticationToken authenticated = UsernamePasswordAuthenticationToken.authenticated(
                    customer, null, customer.getAuthorities());
            context.setAuthentication(authenticated);
            SecurityContextHolder.setContext(context);
            contextRepository.saveContext(context, httpServletRequest, httpServletResponse);
        } else {
            context.setAuthentication(null);
            SecurityContextHolder.setContext(context);
            contextRepository.saveContext(context, httpServletRequest, httpServletResponse);
            throw new AccessDeniedException("Неверный код");
        }
    }

    public void login(LoginRequest request,
                      HttpServletRequest httpServletRequest,
                      HttpServletResponse httpServletResponse) {
        UsernamePasswordAuthenticationToken token =
                UsernamePasswordAuthenticationToken.unauthenticated(
                        request.getPhoneNumberOrEmail(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
        contextRepository.saveContext(context, httpServletRequest, httpServletResponse);
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return authentication.getAuthorities();
    }

    public void changePassword(ChangePasswordRequest request,
                               BindingResult bindingResult,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        Authentication currentAuthentication = SecurityContextHolder.getContext().getAuthentication();
        if (currentAuthentication == null) {
            throw new AccessDeniedException("Can't change password as no Authentication object found in context for current user.");
        } else {
            String phoneNumber = currentAuthentication.getName();
            this.authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken.unauthenticated(phoneNumber, request.getOldPassword())
            );

            Customer customer = customerRepository.findByPhoneNumber(phoneNumber).orElseThrow();
            customer.setPassword(passwordEncoder.encode(request.getNewPassword()));
            customerRepository.update(customer);

            List<Authority> authorities = authorityRepository.findAllByPhoneNumber(phoneNumber);
            UsernamePasswordAuthenticationToken newAuthentication = UsernamePasswordAuthenticationToken.authenticated(
                            customer, null, authorities.stream().map(AuthorityDetails::of).toList());
            newAuthentication.setDetails(currentAuthentication.getDetails());
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(newAuthentication);
            SecurityContextHolder.setContext(context);
            contextRepository.saveContext(context, httpServletRequest, httpServletResponse);
        }
    }
}
