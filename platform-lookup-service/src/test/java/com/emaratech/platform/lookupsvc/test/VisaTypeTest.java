package com.emaratech.platform.lookupsvc.test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.emaratech.platform.lookupsvc.model.VisaType;

public class VisaTypeTest {
    private BigDecimal id = BigDecimal.valueOf(1L);

    /**
     * Tests the getter for VisaType.
     */
    @Test
    public void testGettersWhenConstructorInvokedExpectAttributesNotNull() {
        VisaType visaType = new VisaType(id, "Embassy Visit", "سفاره-زياره", BigDecimal.ZERO);
        assertEquals(1L, visaType.getVisaTypeId().longValue());
        assertEquals("Embassy Visit", visaType.getVisaTypeNameEn());
        assertEquals("سفاره-زياره", visaType.getVisaTypeNameAr());
        assertEquals(BigDecimal.ZERO, visaType.getIsBorderVisa());
    }

    /**
     * Tests the setter for VisaType.
     */
    @Test
    public void testSettersWhenNoArgsConstructorInvokedExpectAttributesNotNull() {
        VisaType visaType = new VisaType();
        visaType.setVisaTypeId(id);
        visaType.setVisaTypeNameEn("Embassy Visit");
        visaType.setVisaTypeNameAr("سفاره-زياره");
        visaType.setIsBorderVisa(BigDecimal.ZERO);
        assertEquals(1L, visaType.getVisaTypeId().longValue());
        assertEquals("Embassy Visit", visaType.getVisaTypeNameEn());
        assertEquals("سفاره-زياره", visaType.getVisaTypeNameAr());
        assertEquals(BigDecimal.ZERO, visaType.getIsBorderVisa());
    }

    /**
     * Tests equals method when two instances with same data compared. Expect
     * equality.
     */
    @Test
    public void testEqualsWhenTwoSameInstanceCreatedExpectEquality() {
        VisaType visaType1 = new VisaType(id, "Embassy Visit", "سفاره-زياره", BigDecimal.ZERO);
        VisaType visaType2 = new VisaType(id, "Embassy Visit", "سفاره-زياره", BigDecimal.ZERO);

        assertTrue(visaType1.equals(visaType2));
    }

    /**
     * Tests equals method when two instances with different data compared. Expect
     * inequality.
     */
    @Test
    public void testEqualsWhenTwoDiffInstanceCreatedExpectInEquality() {
        VisaType visaType1 = new VisaType(id, "Embassy Visit", "سفاره-زياره", BigDecimal.ZERO);
        VisaType visaType2 = new VisaType(id, "EMBASSY?WORK", "سفاره-عمل", BigDecimal.ZERO);

        assertFalse(visaType1.equals(visaType2));

        assertFalse(visaType1.equals(null));
    }

    /**
     * Tests hashcode method.
     */
    @Test
    public void testHashcodeWhenTwoSameInstanceCreatedExpectSameHashcode() {
        VisaType visaType1 = new VisaType(id, "EMBASSY?WORK", "سفاره-عمل", BigDecimal.ZERO);
        VisaType visaType2 = new VisaType(id, "EMBASSY?WORK", "سفاره-عمل", BigDecimal.ZERO);
        assertEquals(visaType1.hashCode(), visaType2.hashCode());
    }

    /**
     * Tests toString method.
     */
    @Test
    public void testToStringExpectStringDataToBeCreated() {
        VisaType visaType = new VisaType(id, "EMBASSY?WORK", "سفاره-عمل", BigDecimal.ZERO);
        assertEquals("VisaType(visaTypeId=1, visaTypeNameEn=EMBASSY?WORK, visaTypeNameAr=سفاره-عمل, isBorderVisa=0)", visaType.toString());
    }
}
