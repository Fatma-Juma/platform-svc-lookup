package com.emaratech.platform.lookupsvc.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
/**
 * Emirate Class for holding the Emirate data.
 */
public class Emirate implements Serializable {

    private BigDecimal emirateId;

    @Size(min = 1, message = "Emirate Name shouldn't be less than 1 character.")
    @Size(max = 20, message = "Emirate Name shouldn't be greater than 20 characters.")
    private String emirateNameEn;

    @Size(min = 1, message = "Emirate Name shouldn't be less than 1 character.")
    @Size(max = 20, message = "Emirate Name shouldn't be greater than 20 characters.")
    private String emirateNameAr;

    @Size(min = 1, message = "Code shouldn't be less than 1 character.")
    @Size(max = 20, message = "Code shouldn't be greater than 20 characters.")
    private String code;

    @Digits(integer = 1, fraction = 0, message = "isArchived shouldn't be greater than 1.")
    private BigDecimal isArchived;

}
