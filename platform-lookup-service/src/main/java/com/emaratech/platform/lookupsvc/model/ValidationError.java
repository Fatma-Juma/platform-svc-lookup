package com.emaratech.platform.lookupsvc.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 */
@Getter
@Setter
public class ValidationError {

    String fieldName;

    String errorMessage;
}
