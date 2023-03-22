package com.springBoot1.SB2.validation.customer;

import com.springBoot1.SB2.repository.CustomerRepository;
import com.springBoot1.SB2.repository.UserRepository;
import com.springBoot1.SB2.validation.FieldNamesValidationEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class CustomerFieldNotTakenImpl implements ConstraintValidator<CustomerFieldNotTaken,String> {
    private FieldNamesValidationEnum filed;
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public void initialize(CustomerFieldNotTaken constraintAnnotation) {
        this.filed=constraintAnnotation.field();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(!StringUtils.hasText(value))return true;
        switch (filed){
            case VALIDATE_FIELD_PHONE:
                return customerRepository.findByPhone(value).isEmpty();
            case VALIDATE_FIELD_EMAIL:
                return customerRepository.findByEmail(value).isEmpty();
            default:
                return true;
        }
    }
}
