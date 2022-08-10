package com.emaratech.platform.lookupsvc.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.emaratech.platform.lookupsvc.model.AlternateCountryCode;

public class AlternateCountryCodeTest {
    private Long id = 1L;

    /**
     * Tests the getter for AlternateCountryCode.
     */
    @Test
    public void testGettersWhenConstructorInvokedExpectAttributesNotNull() {
        AlternateCountryCode alternateCountryCode = new AlternateCountryCode(id, "TCD");
        assertEquals(1L, alternateCountryCode.getAlternateCountryCodeId().longValue());
        assertEquals("TCD", alternateCountryCode.getAlternateCountryCode());
    }

    /**
     * Tests the setter for AlternateCountryCode.
     */
    @Test
    public void testSettersWhenNoArgsConstructorInvokedExpectAttributesNotNull() {
        AlternateCountryCode alternateCountryCode = new AlternateCountryCode();
        alternateCountryCode.setAlternateCountryCodeId(id);
        alternateCountryCode.setAlternateCountryCode("TCD");
        assertEquals(1L, alternateCountryCode.getAlternateCountryCodeId().longValue());
        assertEquals("TCD", alternateCountryCode.getAlternateCountryCode());
    }

    /**
     * Tests equals method when two instances with same data compared. Expect
     * equality.
     */
    @Test
    public void testEqualsWhenTwoSameInstanceCreatedExpectEquality() {
        AlternateCountryCode alternateCountryCode1 = new AlternateCountryCode(id, "TCD");
        AlternateCountryCode alternateCountryCode2 = new AlternateCountryCode(id, "TCD");

        assertTrue(alternateCountryCode1.equals(alternateCountryCode2));
    }

    /**
     * Tests equals method when two instances with different data compared. Expect
     * inequality.
     */
    @Test
    public void testEqualsWhenTwoDiffInstanceCreatedExpectInEquality() {
        AlternateCountryCode alternateCountryCode1 = new AlternateCountryCode(id, "TCD");
        AlternateCountryCode alternateCountryCode2 = new AlternateCountryCode(id, "D");

        assertFalse(alternateCountryCode1.equals(alternateCountryCode2));

        assertFalse(alternateCountryCode1.equals(null));
    }

    /**
     * Tests hashcode method.
     */
    @Test
    public void testHashcodeWhenTwoSameInstanceCreatedExpectSameHashcode() {
        AlternateCountryCode alternateCountryCode1 = new AlternateCountryCode(id, "D");
        AlternateCountryCode alternateCountryCode2 = new AlternateCountryCode(id, "D");
        assertEquals(alternateCountryCode1.hashCode(), alternateCountryCode2.hashCode());
    }

    /**
     * Tests toString method.
     */
    @Test
    public void testToStringExpectStringDataToBeCreated() {
        AlternateCountryCode alternateCountryCode = new AlternateCountryCode(id, "D");
        assertEquals("AlternateCountryCode(alternateCountryCodeId=1, alternateCountryCode=D)", alternateCountryCode.toString());
    }

}
