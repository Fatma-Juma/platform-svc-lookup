package com.emaratech.platform.lookupsvc.test;

import com.emaratech.platform.lookupsvc.model.Country;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CountryTest {

    private BigDecimal id = BigDecimal.valueOf(1L);

    /**
     * Tests the getter for Country.
     */
    @Test
    public void testGettersWhenConstructorInvokedExpectAttributesNotNull() {
        Country country = new Country(id, "Pakistan", "", "PAK");
        assertEquals(1L, country.getCountryId().longValue());
        assertEquals("Pakistan", country.getCountryNameEn());
        assertEquals("", country.getCountryNameAr());
        assertEquals("PAK", country.getCountryCode());
    }

    /**
     * Tests the setter for Country.
     */
    @Test
    public void testSettersWhenNoArgsConstructorInvokedExpectAttributesNotNull() {
        Country country = new Country();
        country.setCountryId(id);
        country.setCountryNameEn("Pakistan");
        country.setCountryCode("PAK");
        assertEquals(1L, country.getCountryId().longValue());
        assertEquals("Pakistan", country.getCountryNameEn());
        assertEquals("PAK", country.getCountryCode());
    }

    /**
     * Tests equals method when two instances with same data compared. Expect
     * equality.
     */
    @Test
    public void testEqualsWhenTwoSameInstanceCreatedExpectEquality() {
        Country country1 = new Country(id, "Pakistan", "", "PAK");
        Country country2 = new Country(id, "Pakistan", "", "PAK");

        assertTrue(country1.equals(country2));
    }

    /**
     * Tests equals method when two instances with different data compared. Expect
     * inequality.
     */
    @Test
    public void testEqualsWhenTwoDiffInstanceCreatedExpectInEquality() {
        Country country1 = new Country(id, "Pakistan", "", "PAK");
        Country country2 = new Country(id, "United Arab Emirates", "", "ARE");

        assertFalse(country1.equals(country2));

        assertFalse(country1.equals(null));
    }

    /**
     * Tests hashcode method.
     */
    @Test
    public void testHashcodeWhenTwoSameInstanceCreatedExpectSameHashcode() {
        Country country1 = new Country(id, "United Arab Emirates", "", "ARE");
        Country country2 = new Country(id, "United Arab Emirates", "", "ARE");
        assertEquals(country1.hashCode(), country2.hashCode());
    }

    /**
     * Tests toString method.
     */
    @Test
    public void testToStringExpectStringDataToBeCreated() {
        Country country = new Country(id, "United Arab Emirates", "", "ARE");
        assertEquals("Country(countryId=1, countryNameEn=United Arab Emirates, countryNameAr=, countryCode=ARE)", country.toString());
    }
}
