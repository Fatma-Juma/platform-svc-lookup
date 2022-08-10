package com.emaratech.platform.lookupsvc.test;

import com.emaratech.platform.lookupsvc.model.FlightCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FlightCodeTest {
    private Long id = 1L;

    /**
     * Tests the getter for FlightCode.
     */
    @Test
    public void testGettersWhenConstructorInvokedExpectAttributesNotNull() {
        FlightCode flightCode = new FlightCode("K3", id);
        assertEquals(1L,flightCode.getFlightCodeId().longValue());
        assertEquals("K3", flightCode.getFlightCode());
    }

    /**
     * Tests the setter for FlightCode.
     */
    @Test
    public void testSettersWhenNoArgsConstructorInvokedExpectAttributesNotNull() {
        FlightCode flightCode = new FlightCode();
        flightCode.setFlightCodeId(id);
        flightCode.setFlightCode("K3");
        assertEquals(1L, flightCode.getFlightCodeId().longValue());
        assertEquals("K3", flightCode.getFlightCode());
    }

    /**
     * Tests equals method when two instances with same data compared. Expect
     * equality.
     */
    @Test
    public void testEqualsWhenTwoSameInstanceCreatedExpectEquality() {
        FlightCode flightCode1 = new FlightCode("K3", id);
        FlightCode flightCode2 = new FlightCode("K3", id);

        assertTrue(flightCode1.equals(flightCode2));
    }

    /**
     * Tests equals method when two instances with different data compared. Expect
     * inequality.
     */
    @Test
    public void testEqualsWhenTwoDiffInstanceCreatedExpectInEquality() {
        FlightCode flightCode1 = new FlightCode("K3", id);
        FlightCode flightCode2 = new FlightCode("F3", id);

        assertFalse(flightCode1.equals(flightCode2));

        assertFalse(flightCode1.equals(null));
    }

    /**
     * Tests hashcode method.
     */
    @Test
    public void testHashcodeWhenTwoSameInstanceCreatedExpectSameHashcode() {
        FlightCode flightCode1 = new FlightCode("F3", id);
        FlightCode flightCode2 = new FlightCode("F3", id);
        assertEquals(flightCode1.hashCode(), flightCode2.hashCode());
    }

    /**
     * Tests toString method.
     */
    @Test
    public void testToStringExpectStringDataToBeCreated() {
        FlightCode flightCode = new FlightCode("F3", id);
        assertEquals("FlightCode(flightCodeId=1, flightCode=F3)", flightCode.toString());
    }

}
