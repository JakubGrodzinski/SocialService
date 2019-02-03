package com.kuba.demo.Validator;

import com.kuba.demo.Model.User;
import com.kuba.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NoSuchNameInDbValidator implements ConstraintValidator <NoSuchNameInDb, String>
{
    @Autowired
    UserRepository userRepository;

    @Override
    public void initialize(NoSuchNameInDb constraintAnnotation)
    {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext)
    {

        User user = userRepository.findByUsername(s);
        if (user == null)
        {
            return true;
        }
        return false;
    }
}
