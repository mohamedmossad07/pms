package com.springBoot1.SB2.validation.user;

import com.springBoot1.SB2.validation.FieldNamesValidationEnum;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {UserFieldNotTakenImpl.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface UserFieldNotTaken {
    String message() default "{exceptions.field.taken}";
    FieldNamesValidationEnum field();
    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
