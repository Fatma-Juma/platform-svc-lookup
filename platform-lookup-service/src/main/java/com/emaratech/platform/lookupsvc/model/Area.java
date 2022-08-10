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
@Table(name = "AREA_LK")
/**
 * Area Class for holding the area data.
 */
public class Area implements Serializable {
    @JsonProperty(value = "id")
    @ExcelCellName("AREA_ID")
    @Id
    @Column(name = "AREA_ID")
    private Long areaId;

    @Size(min = 1, message = "Area name shouldn't be less than 1 character.")
    @Size(max = 50, message = "Area name shouldn't be greater than 50 characters.")
    @JsonProperty(value = "nameAr")
    @ExcelCellName("AREA_AR")
    @Column(name = "AREA_AR")
    private String areaNameAr;

    @Size(min = 1, message = "Area name shouldn't be less than 1 character.")
    @Size(max = 50, message = "Area name shouldn't be greater than 50 characters.")
    @JsonProperty(value = "nameEn")
    @ExcelCellName("AREA_EN")
    @Column(name = "AREA_EN")
    private String areaNameEn;

    @Min(value = 100, message = "City Id shouldn't be greater than 5 digits.")
    @ExcelCellName("CITY_ID")
    @Column(name = "CITY_ID")
    private Long cityId;

    @Max(value = 1, message = "IsArchived shouldn't be greater than 1.")
    @ExcelCellName("IS_ARCHIVED")
    @Column(name = "IS_ARCHIVED")
    private Long isArchived; 

    /**
     *
     *
     * @param areaId
     * @param areaNameAr
     * @param areaNameEn
     */
    public Area(Long areaId, String areaNameAr, String areaNameEn) {
        this.areaId = areaId;
        this.areaNameAr = areaNameAr;
        this.areaNameEn = areaNameEn;
    }

    /**
     * Checks the equality between two {@code area} objects.
     *
     * @param area {@code area} object
     * @return true if both the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object area) {
        if (this == area) {
            return true;
        }

        if (area == null || getClass() != area.getClass()) {
            return false;
        }

        Area that = (Area) area;

        return new EqualsBuilder()
                .append(areaId, that.areaId)
                .append(areaNameAr, that.areaNameAr)
                .append(areaNameEn, that.areaNameEn)
                .isEquals();
    }

    /**
     * Returns the hashcode of the {@code Area} object.
     *
     * @return hashcode of the {@code Area} object
     */
    @Override
    public int hashCode() {
        return Objects.hash(areaId, areaNameEn);
    }

    /**
     * Returns the {@code String} representation of {@code area} object.
     *
     * @return the string representation of the object.
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", Area.class.getSimpleName() + "(", ")")
                .add("areaId=" + areaId + "")
                .add("areaNameAr=" + areaNameAr + "")
                .add("areaNameEn=" + areaNameEn + "")
                .toString();
    }

}
