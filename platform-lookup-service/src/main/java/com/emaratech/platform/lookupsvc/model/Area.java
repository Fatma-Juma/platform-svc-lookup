package com.emaratech.platform.lookupsvc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Area Class for holding the area data.
 */
public class Area implements Serializable {
    /**
     *
     *
     * @param areaId
     * @param areaNameAr
     * @param areaNameEn
     */
    public Area(BigDecimal areaId, String areaNameAr, String areaNameEn) {
        this.areaId = areaId;
        this.areaNameAr = areaNameAr;
        this.areaNameEn = areaNameEn;
    }

    @JsonProperty(value = "id")
    private BigDecimal areaId;

    @Size(min = 1, message = "Area name shouldn't be less than 1 character.")
    @Size(max = 50, message = "Area name shouldn't be greater than 50 characters.")
    @JsonProperty(value = "nameAr")
    private String areaNameAr;

    @Size(min = 1, message = "Area name shouldn't be less than 1 character.")
    @Size(max = 50, message = "Area name shouldn't be greater than 50 characters.")
    @JsonProperty(value = "nameEn")
    private String areaNameEn;

    @Digits(integer = 5, fraction = 0, message = "City Id shouldn't be greater than 5 digits.")
    private BigDecimal cityId;

    @Digits(integer = 1, fraction = 0, message = "IsArchived shouldn't be greater than 1.")
    private BigDecimal isArchived;

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
