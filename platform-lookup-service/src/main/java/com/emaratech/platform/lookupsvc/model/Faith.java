package com.emaratech.platform.lookupsvc.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.poiji.annotation.ExcelCellName;
import org.apache.commons.lang3.builder.EqualsBuilder;

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
@Table(name = "FAITH_LK")
/**
 * Faith class for holding the faith data.
 */
public class Faith implements Serializable {
    @JsonProperty(value = "id")
    @ExcelCellName("FAITH_ID")
    @Id
    @Column(name = "FAITH_ID")
    private Long faithId;

    @Size(min = 1, message = "Faith name shouldn't be less than 1 character.")
    @Size(max = 100, message = "Faith name shouldn't be greater than 20 characters.")
    @JsonProperty(value = "nameAr")
    @ExcelCellName("FAITH_AR")
    @Column(name = "FAITH_AR")
    private String faithNameAr;

    @Size(min = 1, message = "Faith name shouldn't be less than 1 character.")
    @Size(max = 100, message = "Faith name shouldn't be greater than 18 characters.")
    @JsonProperty(value = "nameEn")
    @ExcelCellName("FAITH_EN")
    @Column(name = "FAITH_EN")
    private String faithNameEn;

    @Min(value = 0, message = "Religion Id shouldn't be greater than 2 digits.")
    @ExcelCellName("RELIGION_ID")
    @Column(name = "RELIGION_ID")
    private Long religionId;

    @Max(value = 1, message = "IsArchived shouldn't be greater than 1.")
    @ExcelCellName("IS_ARCHIVED")
    @Column(name = "IS_ARCHIVED")
    private Long isArchived;

    /**
     *
     *
     * @param faithId
     * @param faithNameAr
     * @param faithNameEn
     */
    public Faith(Long faithId, String faithNameAr, String faithNameEn) {
        this.faithId = faithId;
        this.faithNameAr = faithNameAr;
        this.faithNameEn = faithNameEn;
    }

    /**
     * Checks the equality between two {@code faith} objects.
     *
     * @param faith {@code faith} object
     * @return true if both the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object faith) {
        if (this == faith) {
            return true;
        }

        if (faith == null || getClass() != faith.getClass()) {
            return false;
        }

        Faith that = (Faith) faith;

        return new EqualsBuilder()
                .append(faithId, that.faithId)
                .append(faithNameAr, that.faithNameAr)
                .append(faithNameEn, that.faithNameEn)
                .isEquals();
    }

    /**
     * Returns the hashcode of the {@code Faith} object.
     *
     * @return hashcode of the {@code Faith} object
     */
    @Override
    public int hashCode() {
        return Objects.hash(faithId, faithNameEn);
    }

    /**
     * Returns the {@code String} representation of {@code faith} object.
     *
     * @return the string representation of the object.
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", Faith.class.getSimpleName() + "(", ")")
                .add("faithId=" + faithId + "")
                .add("faithNameAr=" + faithNameAr + "")
                .add("faithNameEn=" + faithNameEn + "")
                .toString();
    }
}
