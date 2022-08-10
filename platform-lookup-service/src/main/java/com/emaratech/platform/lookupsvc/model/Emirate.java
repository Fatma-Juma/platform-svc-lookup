package com.emaratech.platform.lookupsvc.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import com.poiji.annotation.ExcelCellName;
import org.apache.commons.lang3.builder.EqualsBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMIRATE_LK")
/**
 * Emirate Class for holding the Emirate data.
 */
public class Emirate implements Serializable {

    @JsonProperty(value = "id")
    @ExcelCellName("EMIRATE_ID")
    @Id
    @Column(name = "EMIRATE_ID")
    private Long emirateId;

    @Size(min = 1, message = "Emirate name shouldn't be less than 1 character.")
    @Size(max = 20, message = "Emirate name shouldn't be greater than 20 characters.")
    @JsonProperty(value = "nameEn")
    @ExcelCellName("EMIRATE_NAME_EN")
    @Column(name = "EMIRATE_NAME_EN")
    private String emirateNameEn;

    @Size(min = 1, message = "Emirate name shouldn't be less than 1 character.")
    @Size(max = 20, message = "Emirate name shouldn't be greater than 20 characters.")
    @JsonProperty(value = "nameAr")
    @ExcelCellName("EMIRATE_NAME_AR")
    @Column(name = "EMIRATE_NAME_AR")
    private String emirateNameAr;

    @Size(min = 1, message = "Code shouldn't be less than 1 character.")
    @Size(max = 20, message = "Code shouldn't be greater than 20 characters.")
    @ExcelCellName("CODE")
    @Column(name = "CODE")
    private String code;

    @Max(value = 1, message = "IsArchived shouldn't be greater than 1.")
    @ExcelCellName("IS_ARCHIVED")
    @Column(name = "IS_ARCHIVED")
    private Long isArchived;

    /**
     *
     *
     * @param emirateId
     * @param emirateNameEn
     * @param emirateNameAr
     * @param code
     */
    public Emirate(Long emirateId, String emirateNameEn, String emirateNameAr, String code) {
        this.emirateId = emirateId;
        this.emirateNameEn = emirateNameEn;
        this.emirateNameAr = emirateNameAr;
        this.code = code;
    }

    /**
     * Checks the equality between two {@code emirate} objects.
     *
     * @param emirate {@code emirate} object
     * @return true if both the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object emirate) {
        if (this == emirate) {
            return true;
        }

        if (emirate == null || getClass() != emirate.getClass()) {
            return false;
        }

        Emirate that = (Emirate) emirate;

        return new EqualsBuilder()
                .append(emirateId, that.emirateId)
                .append(emirateNameEn, that.emirateNameEn)
                .append(emirateNameAr, that.emirateNameAr)
                .append(code, that.code)
                .isEquals();
    }

    /**
     * Returns the hashcode of the {@code Emirate} object.
     *
     * @return hashcode of the {@code Emirate} object
     */
    @Override
    public int hashCode() {
        return Objects.hash(emirateId, code);
    }

    /**
     * Returns the {@code String} representation of {@code emirate} object.
     *
     * @return the string representation of the object.
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", Emirate.class.getSimpleName() + "(", ")")
                .add("emirateId=" + emirateId + "")
                .add("emirateNameEn=" + emirateNameEn + "")
                .add("emirateNameAr=" + emirateNameAr + "")
                .add("code=" + code + "")
                .toString();
    }

}
