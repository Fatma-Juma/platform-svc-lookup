package com.emaratech.platform.lookupsvc.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.stereotype.Component;

@Component
public class ValidationUtils {

    /**
     *
     *
     * @param targetClass
     * @return
     */
    public static Set<?> validate(Object targetClass) {

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(targetClass);
        return constraintViolations;
    }

}
