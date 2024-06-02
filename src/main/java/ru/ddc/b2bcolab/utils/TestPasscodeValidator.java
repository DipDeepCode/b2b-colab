package ru.ddc.b2bcolab.utils;

import org.springframework.stereotype.Component;

@Component
public class TestPasscodeValidator implements PasscodeValidator {

    @Override
    public boolean validate(String passcode) {
        return passcode.equals("4598");
    }
}
