package com.springBoot1.SB2.validation.user;

import com.springBoot1.SB2.repository.UserRepository;
import com.springBoot1.SB2.validation.FieldNamesValidationEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class UserFieldNotTakenImpl implements ConstraintValidator<UserFieldNotTaken,String> {
    private FieldNamesValidationEnum filed;
    @Autowired
    private UserRepository userRepository;
    @Override
    public void initialize(UserFieldNotTaken constraintAnnotation) {
        this.filed=constraintAnnotation.field();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(!StringUtils.hasText(value))return true;
        switch (filed){
            case VALIDATE_FIELD_USERNAME:
                return Objects.isNull(userRepository.findByUsername(value));
            case VALIDATE_FIELD_PHONE:
                return Objects.isNull(userRepository.findByPhone(value));
            case VALIDATE_FIELD_EMAIL:
                return userRepository.findByEmail(value).isEmpty();
            default:
                return true;
        }
    }
}
