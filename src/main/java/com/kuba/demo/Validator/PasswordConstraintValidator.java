package com.kuba.demo.Validator;

import com.google.common.base.Joiner;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String>
{
    @Override
    public void initialize(ValidPassword constraintAnnotation)
    {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext)
    {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 30),
                new UppercaseCharacterRule(1),
                new DigitCharacterRule(1),
                new SpecialCharacterRule(1),
                new NumericalSequenceRule(5,false),
                new AlphabeticalSequenceRule(5,false),
                new QwertySequenceRule(5,false),
                new WhitespaceRule()));

        RuleResult result = validator.validate(new PasswordData(s));
        if (result.isValid()) {
            return true;
        }
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(
                Joiner.on(",").join(validator.getMessages(result)))
                .addConstraintViolation();
        return false;
    }
}
