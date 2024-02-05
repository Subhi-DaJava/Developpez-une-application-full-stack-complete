package com.openclassrooms.mddapi.security_config;

import org.passay.*;

import java.util.Arrays;
import java.util.List;

public class PasswordValidatorUtil {

    // Private constructor to prevent instantiation
    private PasswordValidatorUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void validatePassword(String password) {
        // rule for length of password
        LengthRule lengthRule = new LengthRule(8, Integer.MAX_VALUE);

        // rule for at least one digit
        CharacterRule digitRule = new CharacterRule(EnglishCharacterData.Digit, 1);

        // rule for at least one lowercase letter
        CharacterRule lowerCaseRule = new CharacterRule(EnglishCharacterData.LowerCase, 1);

        // rule for at least one uppercase letter
        CharacterRule upperCaseRule = new CharacterRule(EnglishCharacterData.UpperCase, 1);

        // rule for at least one special character
        CharacterRule specialCharRule = new CharacterRule(EnglishCharacterData.Special, 1);

        // combine all rules
        List<Rule> ruleList = Arrays.asList(lengthRule, digitRule, lowerCaseRule, upperCaseRule, specialCharRule);

        PasswordValidator validator = new PasswordValidator(ruleList);
        PasswordData passwordData = new PasswordData(password);

        RuleResult result = validator.validate(passwordData);

        if (!result.isValid()) {
            String message = "Invalid password: " + validator.getMessages(result);
            throw new IllegalArgumentException(message);
        }
    }
}
