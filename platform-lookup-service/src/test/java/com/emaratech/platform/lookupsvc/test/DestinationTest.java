package com.emaratech.platform.lookupsvc.test;

import com.emaratech.platform.lookupsvc.model.Country;
import com.emaratech.platform.lookupsvc.model.Destination;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DestinationTest {
    private BigDecimal id = BigDecimal.valueOf(1L);

    /**
     * Tests the getter for Destination.
     */
    @Test
    public void testGettersWhenConstructorInvokedExpectAttributesNotNull() {
        Destination destination = new Destination(id, "بوخارست، رومانيا", "Bucharest, Romania", "OTP");
        assertEquals(1L, destination.getDestinationId().longValue());
        assertEquals("بوخارست، رومانيا", destination.getDestinationNameAr());
        assertEquals("Bucharest, Romania", destination.getDestinationNameEn());
        assertEquals("OTP", destination.getDestinationCode());
    }

    /**
     * Tests the setter for Destination.
     */
    @Test
    public void testSettersWhenNoArgsConstructorInvokedExpectAttributesNotNull() {
        Destination destination = new Destination();
        destination.setDestinationId(id);
        destination.setDestinationNameAr("بوخارست، رومانيا");
        destination.setDestinationNameEn("Bucharest, Romania");
        destination.setDestinationCode("OTP");
        assertEquals(1L, destination.getDestinationId().longValue());
        assertEquals("بوخارست، رومانيا", destination.getDestinationNameAr());
        assertEquals("Bucharest, Romania", destination.getDestinationNameEn());
        assertEquals("OTP", destination.getDestinationCode());
    }

    /**
     * Tests equals method when two instances with same data compared. Expect
     * equality.
     */
    @Test
    public void testEqualsWhenTwoSameInstanceCreatedExpectEquality() {
        Destination destination1 = new Destination(id, "بوخارست، رومانيا", "Bucharest, Romania", "OTP");
        Destination destination2 = new Destination(id, "بوخارست، رومانيا", "Bucharest, Romania", "OTP");

        assertTrue(destination1.equals(destination2));
    }

    /**
     * Tests equals method when two instances with different data compared. Expect
     * inequality.
     */
    @Test
    public void testEqualsWhenTwoDiffInstanceCreatedExpectInEquality() {
        Destination destination1 = new Destination(id, "بوخارست، رومانيا", "Bucharest, Romania", "OTP");
        Destination destination2 = new Destination(id, "بيرم، روسيا", "Perm, Russia", "PEE");

        assertFalse(destination1.equals(destination2));

        assertFalse(destination1.equals(null));
    }

    /**
     * Tests hashcode method.
     */
    @Test
    public void testHashcodeWhenTwoSameInstanceCreatedExpectSameHashcode() {
        Destination destination1 = new Destination(id, "بيرم، روسيا", "Perm, Russia", "PEE");
        Destination destination2 = new Destination(id, "بيرم، روسيا", "Perm, Russia", "PEE");
        assertEquals(destination1.hashCode(), destination2.hashCode());
    }

    /**
     * Tests toString method.
     */
    @Test
    public void testToStringExpectStringDataToBeCreated() {
        Destination destination = new Destination(id, "بيرم، روسيا", "Perm, Russia", "PEE");
        assertEquals("Destination(destinationId=1, destinationNameAr=بيرم، روسيا, destinationNameEn=Perm, Russia, destinationCode=PEE)", destination.toString());
    }
}
