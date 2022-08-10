package com.emaratech.platform.lookupsvc.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

import com.poiji.annotation.ExcelCellName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PROFESSION_LK")
/**
 * Profession class for holding the faith data.
 */
public class Profession implements Serializable {

    @ExcelCellName("PROFESSION_ID")
    @Id
    @Column(name = "PROFESSION_ID")
    private Long professionId;

    @Size(min = 1, message = "Profession name shouldn't be less than 1 character.")
    @Size(max = 100, message = "Profession name shouldn't be greater than 100 characters.")
    @ExcelCellName("PROFESSION_NAME_AR")
    @Column(name = "PROFESSION_NAME_AR")
    private String professionNameAr;

    @Size(min = 1, message = "Profession name shouldn't be less than 1 character.")
    @Size(max = 100, message = "Profession name shouldn't be greater than 100 characters.")
    @ExcelCellName("PROFESSION_NAME_EN")
    @Column(name = "PROFESSION_NAME_EN")
    private String professionNameEn;

    @Max(value = 1, message = "Is change status allowed shouldn't be greater than 1 digit.")
    @ExcelCellName("IS_CHANGE_STATUS_ALLOWED")
    @Column(name = "IS_CHANGE_STATUS_ALLOWED")
    private Integer isChangeStatusAllowed;

    @Max(value = 1, message = "Is Archived shouldn't be greater than 1 digit.")
    @ExcelCellName("IS_ARCHIVED")
    @Column(name = "IS_ARCHIVED")
    private Integer isArchived;

    /**
     *
     *
     * @param professionId
     * @param professionNameAr
     * @param professionNameEn
     */
    public Profession(Long professionId, String professionNameAr, String professionNameEn) {
        this.professionId = professionId;
        this.professionNameAr = professionNameAr;
        this.professionNameEn = professionNameEn;
    }

    /**
     * Checks the equality between two {@code profession} objects.
     *
     * @param profession {@code profession} object
     * @return true if both the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object profession) {
        if (this == profession) {
            return true;
        }

        if (profession == null || getClass() != profession.getClass()) {
            return false;
        }

        Profession that = (Profession) profession;

        return new EqualsBuilder()
                .append(professionId, that.professionId)
                .append(professionNameAr, that.professionNameAr)
                .append(professionNameEn, that.professionNameEn)
                .isEquals();
    }

    /**
     * Returns the hashcode of the {@code  Profession} object.
     *
     * @return hashcode of the {@code  Profession} object
     */
    @Override
    public int hashCode() {
        return Objects.hash(professionId, professionNameEn);
    }

    /**
     * Returns the {@code String} representation of {@code profession} object.
     *
     * @return the string representation of the object.
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", Profession.class.getSimpleName() + "(", ")")
                .add("professionId=" + professionId + "")
                .add("professionNameAr=" + professionNameAr + "")
                .add("professionNameEn=" + professionNameEn + "")
                .toString();
    }
}
