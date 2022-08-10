package com.emaratech.platform.lookupsvc.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "VISA_TYPE_LK")
/**
 * VisaType Class for holding the VisaType data.
 */
public class VisaType implements Serializable {
    @JsonProperty(value = "id")
    @Id
    @Column(name = "VISA_TYPE_ID")
    private Long visaTypeId;

    @Size(min = 1, message = "Visa type name shouldn't be less than 1 character.")
    @Size(max = 100, message = "Visa type name shouldn't be greater than 100 characters.")
    @JsonProperty(value = "nameEn")
    @Column(name = "VISA_TYPE_EN")
    private String visaTypeNameEn;

    @Size(min = 1, message = "Visa type name shouldn't be less than 1 character.")
    @Size(max = 100, message = "Visa type name shouldn't be greater than 100 characters.")
    @JsonProperty(value = "nameAr")
    @Column(name = "VISA_TYPE_AR")
    private String visaTypeNameAr;

    @Range(max = 4, message = "Validity days before entry shouldn't be greater than 4 digits.")
    @Column(name = "VALIDITY_DAYS_BEFORE_ENTRY")
    private Long validityDaysBeforeEntry;

    @Range(max = 4, message = "Validity days after entry shouldn't be greater than 4 digits.")
    @Column(name = "VALIDITY_DAYS_AFTER_ENTRY")
    private Long validityDaysAfterEntry;

    @Range(max = 4, message = "Stay days shouldn't be greater than 4 digits.")
    @Column(name = "STAY_DAYS")
    private Long stayDays;

    @Range(max = 4, message = "Stay grace days shouldn't be greater than 4 digits.")
    @Column(name = "STAY_GRACE_DAYS")
    private Long stayGraceDays;

    @Range(max = 4, message = "Extension 1 days shouldn't be greater than 4 digits.")
    @Column(name = "EXTENSION1_DAYS")
    private Long extension1Days;

    @Range(max = 4, message = "Extension 1 grace days shouldn't be greater than 4 digits.")
    @Column(name = "EXTENSION1_GRACE_DAYS")
    private Long extension1GraceDays;

    @Range(max = 4, message = "Extension 2 days shouldn't be greater than 4 digits.")
    @Column(name = "EXTENSION2_DAYS")
    private Long extension2Days;

    @Range(max = 4, message = "Extension 2 grace days shouldn't be greater than 4 digits.")
    @Column(name = "EXTENSION2_GRACE_DAYS")
    private Long extension2GraceDays;

    @Range(max = 1, message = "IsBorderVisa shouldn't be greater than 1.")
    @Column(name = "IS_BORDER_VISA")
    private Long isBorderVisa;

    @Range(max = 1, message = "IsMultipleEntryVisa shouldn't be greater than 1.")
    @Column(name = "IS_MULTIPLE_ENTRY_VISA")
    private Long isMultipleEntryVisa;

    @Range(max = 4, message = "Violation grace days shouldn't be greater than 4 digits.")
    @Column(name = "VIOLATION_GRACE_DAYS")
    private Long violationGraceDays;

    @Range(max = 1, message = "IsArchived shouldn't be greater than 1.")
    @Column(name = "IS_ARCHIVED")
    private Long isArchived;

    @Column(name = "JOB_CLOSE_AFTER_DAYS")
    private Long jobCloseAfterDays;

    @Range(max = 1, message = "IsAllowedForEstabQuota shouldn't be greater than 1.")
    @Column(name = "IS_ALLOWED_FOR_ESTAB_QUOTA")
    private Long isAllowedForEstabQuota;

    @Column(name = "REPLACE_WITH_VISA_TYPE_ID")
    private Long replaceWithVisaTypeId;

    @Range(max = 1, message = "IsFromChangeStatusAllowed shouldn't be greater than 1.")
    @Column(name = "IS_FROM_CHANGE_STATUS_ALLOWED")
    private Long isFromChangeStatusAllowed;

    @Range(max = 1, message = "IsToChangeStatusAllowed shouldn't be greater than 1.")
    @Column(name = "IS_TO_CHANGE_STATUS_ALLOWED")
    private Long isToChangeStatusAllowed;

    @Range(max = 1, message = "IsAccompaniedAllowed shouldn't be greater than 1.")
    @Column(name = "IS_ACCOMPANIED_ALLOWED ")
    private Long isAccompaniedAllowed;

    private Long isValidityFromIssueDate;

    private Long isValidityFromEntryDate;

    /**
     *
     *
     * @param visaTypeId
     * @param visaTypeNameEn
     * @param visaTypeNameAr
     * @param isBorderVisa
     */
    public VisaType(Long visaTypeId, String visaTypeNameEn, String visaTypeNameAr, Long isBorderVisa) {
        this.visaTypeId = visaTypeId;
        this.visaTypeNameEn = visaTypeNameEn;
        this.visaTypeNameAr = visaTypeNameAr;
        this.isBorderVisa = isBorderVisa;
    }

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
