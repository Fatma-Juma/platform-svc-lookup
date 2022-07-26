package com.emaratech.platform.lookupsvc.test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.emaratech.platform.lookupsvc.model.Faith;

public class FaithTest {
    private BigDecimal id = BigDecimal.valueOf(1L);

    /**
     * Tests the getter for Faith.
     */
    @Test
    public void testGettersWhenConstructorInvokedExpectAttributesNotNull() {
        Faith faith = new Faith(id, "سني", "Sunni");
        assertEquals(1L, faith.getFaithId().longValue());
        assertEquals("سني", faith.getFaithNameAr());
        assertEquals("Sunni", faith.getFaithNameEn());
    }

    /**
     * Tests the setter for Faith.
     */
    @Test
    public void testSettersWhenNoArgsConstructorInvokedExpectAttributesNotNull() {
        Faith faith = new Faith();
        faith.setFaithId(id);
        faith.setFaithNameAr("سني");
        faith.setFaithNameEn("Sunni");
        assertEquals(1L, faith.getFaithId().longValue());
        assertEquals("سني", faith.getFaithNameAr());
        assertEquals("Sunni", faith.getFaithNameEn());
    }

    /**
     * Tests equals method when two instances with same data compared. Expect
     * equality.
     */
    @Test
    public void testEqualsWhenTwoSameInstanceCreatedExpectEquality() {
        Faith faith1 = new Faith(id, "سني", "Sunni");
        Faith faith2 = new Faith(id, "سني", "Sunni");

        assertTrue(faith1.equals(faith2));
    }

    /**
     * Tests equals method when two instances with different data compared. Expect
     * inequality.
     */
    @Test
    public void testEqualsWhenTwoDiffInstanceCreatedExpectInEquality() {
        Faith faith1 = new Faith(id, "سني", "Sunni");
        Faith faith2 = new Faith(id, "شيعي", "Shia");

        assertFalse(faith1.equals(faith2));

        assertFalse(faith1.equals(null));
    }

    /**
     * Tests hashcode method.
     */
    @Test
    public void testHashcodeWhenTwoSameInstanceCreatedExpectSameHashcode() {
        Faith faith1 = new Faith(id, "شيعي", "Shia");
        Faith faith2 = new Faith(id, "شيعي", "Shia");
        assertEquals(faith1.hashCode(), faith2.hashCode());
    }

    /**
     * Tests toString method.
     */
    @Test
    public void testToStringExpectStringDataToBeCreated() {
        Faith faith = new Faith(id, "شيعي", "Shia");
        assertEquals("Faith(faithId=1, faithNameAr=شيعي, faithNameEn=Shia)", faith.toString());
    }

}
