package com.emaratech.platform.lookupsvc.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.springframework.stereotype.Component;

/**
 * Validates the violation of class fields data.
 */
@Component
public class ValidationUtils {

    /**
     * validates the class fields data.
     *
     * @param targetClass
     * @return set of violations
     */
    public static Set<?> validate(Object targetClass) {

       Validator validator = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory()
                .getValidator();;
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(targetClass);
        return constraintViolations;
    }

}
