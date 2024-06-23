package ru.ddc.b2bcolab.utils;

import lombok.RequiredArgsConstructor;
import org.passay.CharacterRule;
import org.passay.PasswordGenerator;

@RequiredArgsConstructor
public class PasscodeGenerator {
    private final PasswordGenerator passwordGenerator;
    private final CharacterRule characterRule;
    private final int numberOfCharacters;

    public String generate() {
        return passwordGenerator.generatePassword(numberOfCharacters, characterRule);
    }
}
