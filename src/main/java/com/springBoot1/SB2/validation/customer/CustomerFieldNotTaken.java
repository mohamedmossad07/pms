package com.springBoot1.SB2.validation.customer;

import com.springBoot1.SB2.validation.FieldNamesValidationEnum;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {CustomerFieldNotTakenImpl.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface CustomerFieldNotTaken {
    String message() default "{exceptions.field.taken}";
    FieldNamesValidationEnum field();
    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
