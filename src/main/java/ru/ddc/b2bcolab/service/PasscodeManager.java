package ru.ddc.b2bcolab.service;

import ru.ddc.b2bcolab.model.Customer;

public interface PasscodeManager {
    void sendPasscode(Customer customer);
    boolean verifyPasscode(String passcode);
}
