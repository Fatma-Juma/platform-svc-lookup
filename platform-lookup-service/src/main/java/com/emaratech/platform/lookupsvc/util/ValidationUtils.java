package com.emaratech.platform.lookupsvc.util;

import java.util.Set;

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
     * Makes the constructor private.
     */
    private ValidationUtils() {

    }

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
                .getValidator();
            return validator.validate(targetClass);
    }

}
