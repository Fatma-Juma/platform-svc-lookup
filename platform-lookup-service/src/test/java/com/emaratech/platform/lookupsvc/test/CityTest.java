package com.emaratech.platform.lookupsvc.test;

import com.emaratech.platform.lookupsvc.model.City;
import com.emaratech.platform.lookupsvc.model.Country;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CityTest {

    private BigDecimal id = BigDecimal.valueOf(1L);

    /**
     * Tests the getter for City.
     */
    @Test
    public void testGettersWhenConstructorInvokedExpectAttributesNotNull() {
        City city = new City(id, "ASFI", "أصفي");
        assertEquals(1L, city.getCityId().longValue());
        assertEquals("ASFI", city.getCityNameEn());
        assertEquals("أصفي", city.getCityNameAr());
    }

    /**
     * Tests the setter for City.
     */
    @Test
    public void testSettersWhenNoArgsConstructorInvokedExpectAttributesNotNull() {
        City city = new City();
        city.setCityId(id);
        city.setCityNameEn("ASFI");
        city.setCityNameAr("أصفي");
        assertEquals(1L, city.getCityId().longValue());
        assertEquals("ASFI", city.getCityNameEn());
        assertEquals("أصفي", city.getCityNameAr());
    }

    /**
     * Tests equals method when two instances with same data compared. Expect
     * equality.
     */
    @Test
    public void testEqualsWhenTwoSameInstanceCreatedExpectEquality() {
        City city1 = new City(id, "ASFI", "أصفي");
        City city2 = new City(id, "ASFI", "أصفي");

        assertTrue(city1.equals(city2));
    }

    /**
     * Tests equals method when two instances with different data compared. Expect
     * inequality.
     */
    @Test
    public void testEqualsWhenTwoDiffInstanceCreatedExpectInEquality() {
        City city1 = new City(id, "ASFI", "أصفي");
        City city2 = new City(id, "BATAYIH", "البطائح");

        assertFalse(city1.equals(city2));

        assertFalse(city1.equals(null));
    }

    /**
     * Tests hashcode method.
     */
    @Test
    public void testHashcodeWhenTwoSameInstanceCreatedExpectSameHashcode() {
        City city1 = new City(id, "BATAYIH", "البطائح");
        City city2 = new City(id, "BATAYIH", "البطائح");
        assertEquals(city1.hashCode(), city2.hashCode());
    }

    /**
     * Tests toString method.
     */
    @Test
    public void testToStringExpectStringDataToBeCreated() {
        City city = new City(id, "BATAYIH", "البطائح");
        assertEquals("City(cityId=1, cityNameEn=BATAYIH, cityNameAr=البطائح)", city.toString());
    }
}
