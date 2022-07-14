package com.emaratech.platform.lookupsvc.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 *
 */
public class VisaType implements Serializable {

    @JsonProperty(value = "id")
    private BigDecimal visaTypeId;

    @JsonProperty(value = "nameEn")
    private String visaTypeNameEn;

    @JsonProperty(value = "nameAr")
    private String visaTypeNameAr;

    private BigDecimal validityDaysBeforeEntry;

    private BigDecimal validityDaysAfterEntry;

    private BigDecimal stayDays;

    private BigDecimal stayGraceDays;

    private BigDecimal extension1Days;

    private BigDecimal extension1GraceDays;

    private BigDecimal extension2Days;

    private BigDecimal extension2GraceDays;

    private BigDecimal isBorderVisa;

    private BigDecimal isMultipleEntryVisa;

    private BigDecimal violationGraceDays;

    private BigDecimal isArchived;

    private BigDecimal jobCloseAfterDays;

    private BigDecimal isAllowedForEstabQuota;

    private BigDecimal replaceWithVisaTypeId;

    private BigDecimal isFromChangeStatusAllowed;

    private BigDecimal isToChangeStatusAllowed;

    private BigDecimal isAccompaniedAllowed;

    private BigDecimal isValidityFromIssueDate;

    private BigDecimal IsValidityFromEntryDate;

}
