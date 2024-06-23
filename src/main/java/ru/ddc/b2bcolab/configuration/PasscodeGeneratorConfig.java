package ru.ddc.b2bcolab.configuration;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ddc.b2bcolab.utils.PasscodeGenerator;

@Configuration
public class PasscodeGeneratorConfig {

    @Bean
    public PasscodeGenerator passcodeGenerator() {
        return new PasscodeGenerator(
                new PasswordGenerator(),
                new CharacterRule(EnglishCharacterData.Digit, 4),
                4);
    }
}
