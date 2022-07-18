package com.emaratech.platform.lookupsvc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

@Getter
@Setter
/**
 * VisaType Class for holding the VisaType data.
 */
public class VisaType implements Serializable {

    @JsonProperty(value = "id")
    private BigDecimal visaTypeId;

    @Size(min = 1, message = "Visa type name shouldn't be less than 1 character.")
    @Size(max = 100, message = "Visa type name shouldn't be greater than 100 characters.")
    @JsonProperty(value = "nameEn")
    private String visaTypeNameEn;

    @Size(min = 1, message = "Visa type name shouldn't be less than 1 character.")
    @Size(max = 100, message = "Visa type name shouldn't be greater than 100 characters.")
    @JsonProperty(value = "nameAr")
    private String visaTypeNameAr;

    @Digits(integer = 4, fraction = 0, message = "Validity days before entry shouldn't be greater than 4 digits.")
    private BigDecimal validityDaysBeforeEntry;

    @Digits(integer = 4, fraction = 0, message = "Validity days after entry shouldn't be greater than 4 digits.")
    private BigDecimal validityDaysAfterEntry;

    @Digits(integer = 4, fraction = 0, message = "Stay days shouldn't be greater than 4 digits.")
    private BigDecimal stayDays;

    @Digits(integer = 4, fraction = 0, message = "Stay grace days shouldn't be greater than 4 digits.")
    private BigDecimal stayGraceDays;

    @Digits(integer = 4, fraction = 0, message = "Extension 1 days shouldn't be greater than 4 digits.")
    private BigDecimal extension1Days;

    @Digits(integer = 4, fraction = 0, message = "Extension 1 grace days shouldn't be greater than 4 digits.")
    private BigDecimal extension1GraceDays;

    @Digits(integer = 4, fraction = 0, message = "Extension 2 days shouldn't be greater than 4 digits.")
    private BigDecimal extension2Days;

    @Digits(integer = 4, fraction = 0, message = "Extension 2 grace days shouldn't be greater than 4 digits.")
    private BigDecimal extension2GraceDays;

    @Digits(integer = 1, fraction = 0, message = "IsBorderVisa shouldn't be greater than 1.")
    private BigDecimal isBorderVisa;

    @Digits(integer = 1, fraction = 0, message = "IsMultipleEntryVisa shouldn't be greater than 1.")
    private BigDecimal isMultipleEntryVisa;

    @Digits(integer = 4, fraction = 0, message = "Violation grace days shouldn't be greater than 4 digits.")
    private BigDecimal violationGraceDays;

    @Digits(integer = 1, fraction = 0, message = "IsArchived shouldn't be greater than 1.")
    private BigDecimal isArchived;

    private BigDecimal jobCloseAfterDays;

    @Digits(integer = 1, fraction = 0, message = "IsAllowedForEstabQuota shouldn't be greater than 1.")
    private BigDecimal isAllowedForEstabQuota;

    private BigDecimal replaceWithVisaTypeId;

    @Digits(integer = 1, fraction = 0, message = "IsFromChangeStatusAllowed shouldn't be greater than 1.")
    private BigDecimal isFromChangeStatusAllowed;

    @Digits(integer = 1, fraction = 0, message = "IsToChangeStatusAllowed shouldn't be greater than 1.")
    private BigDecimal isToChangeStatusAllowed;

    @Digits(integer = 1, fraction = 0, message = "IsAccompaniedAllowed shouldn't be greater than 1.")
    private BigDecimal isAccompaniedAllowed;

    private BigDecimal isValidityFromIssueDate;

    private BigDecimal isValidityFromEntryDate;


    /**
     * Checks the equality between two {@code visaType} objects.
     *
     * @param visaType {@code visaType} object
     * @return true if both the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object visaType) {
        if (this == visaType) {
            return true;
        }

        if (visaType == null || getClass() != visaType.getClass()) {
            return false;
        }

        VisaType that = (VisaType) visaType;

        return new EqualsBuilder()
                .append(visaTypeId, that.visaTypeId)
                .append(visaTypeNameEn, that.visaTypeNameEn)
                .append(visaTypeNameAr, that.visaTypeNameAr)
                .append(isBorderVisa, that.isBorderVisa)
                .isEquals();
    }

    /**
     * Returns the hashcode of the {@code VisaType} object.
     *
     * @return hashcode of the {@code VisaType} object
     */
    @Override
    public int hashCode() {
        return Objects.hash(visaTypeId, visaTypeNameEn);
    }

    /**
     * Returns the {@code String} representation of {@code visaType} object.
     *
     * @return the string representation of the object.
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", VisaType.class.getSimpleName() + "(", ")")
                .add("visaTypeId=" + visaTypeId + "")
                .add("visaTypeNameEn=" + visaTypeNameEn + "")
                .add("visaTypeNameAr=" + visaTypeNameAr + "")
                .add("isBorderVisa=" + isBorderVisa + "")
                .toString();
    }

}
