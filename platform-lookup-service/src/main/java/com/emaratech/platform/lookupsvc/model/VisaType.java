package com.emaratech.platform.lookupsvc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;

@Getter
@Setter
/**
 * VisaType Class for holding the VisaType data.
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
