package ru.ddc.b2bcolab.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.ddc.b2bcolab.controller.payload.RegisterCustomerRequest;
import ru.ddc.b2bcolab.repository.CustomerRepository;

import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class RegisterCustomerRequestValidator implements Validator {
    private final CustomerRepository customerRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(RegisterCustomerRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterCustomerRequest request = (RegisterCustomerRequest) target;
        String phoneRegex = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
        if (!Pattern.matches(phoneRegex, request.getPhoneNumber())) {
            errors.rejectValue("phoneNumber", "", "not valid value");
        }
        if (customerRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            errors.rejectValue("phoneNumber", "", "phoneNumber is already taken");
        }
        if (customerRepository.existsByEmail(request.getEmail())) {
            errors.rejectValue("email", "", "email is already taken");
        }
    }
}
