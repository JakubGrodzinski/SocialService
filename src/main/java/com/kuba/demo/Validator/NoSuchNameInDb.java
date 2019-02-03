package com.kuba.demo.Validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NoSuchNameInDbValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoSuchNameInDb
{
    String message () default "{noSuchNameInDb.error.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
