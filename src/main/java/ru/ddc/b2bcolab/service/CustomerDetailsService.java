package ru.ddc.b2bcolab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.ddc.b2bcolab.model.Authority;
import ru.ddc.b2bcolab.model.Customer;
import ru.ddc.b2bcolab.repository.AuthorityRepository;
import ru.ddc.b2bcolab.repository.CustomerRepository;

import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
public class CustomerDetailsService implements UserDetailsService {
    private final CustomerRepository customerRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByPhoneNumberOrEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("Customer not found")
        );
        List<Authority> authorities = authorityRepository.findAllByPhoneNumber(customer.getPhoneNumber());
        customer.setCustomerAuthorities(new HashSet<>(authorities));
        return customer;
    }
}
