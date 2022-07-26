package com.emaratech.platform.lookupsvc.test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.emaratech.platform.lookupsvc.model.Area;

public class AreaTest {
    private BigDecimal id = BigDecimal.valueOf(1L);

    /**
     * Tests the getter for Area.
     */
    @Test
    public void testGettersWhenConstructorInvokedExpectAttributesNotNull() {
        Area area = new Area(id, "المدام", "AL MADAM");
        assertEquals(1L, area.getAreaId().longValue());
        assertEquals("المدام", area.getAreaNameAr());
        assertEquals("AL MADAM", area.getAreaNameEn());
    }

    /**
     * Tests the setter for Area.
     */
    @Test
    public void testSettersWhenNoArgsConstructorInvokedExpectAttributesNotNull() {
        Area area = new Area();
        area.setAreaId(id);
        area.setAreaNameAr("المدام");
        area.setAreaNameEn("AL MADAM");
        assertEquals(1L, area.getAreaId().longValue());
        assertEquals("المدام", area.getAreaNameAr());
        assertEquals("AL MADAM", area.getAreaNameEn());
    }

    /**
     * Tests equals method when two instances with same data compared. Expect
     * equality.
     */
    @Test
    public void testEqualsWhenTwoSameInstanceCreatedExpectEquality() {
        Area area1 = new Area(id, "المدام", "AL MADAM");
        Area area2 = new Area(id, "المدام", "AL MADAM");

        assertTrue(area1.equals(area2));
    }

    /**
     * Tests equals method when two instances with different data compared. Expect
     * inequality.
     */
    @Test
    public void testEqualsWhenTwoDiffInstanceCreatedExpectInEquality() {
        Area area1 = new Area(id, "المدام", "AL MADAM");
        Area area2 = new Area(id, "الحوية", "AL HAWIAH");

        assertFalse(area1.equals(area2));

        assertFalse(area1.equals(null));
    }

    /**
     * Tests hashcode method.
     */
    @Test
    public void testHashcodeWhenTwoSameInstanceCreatedExpectSameHashcode() {
        Area area1 = new Area(id, "الحوية", "AL HAWIAH");
        Area area2 = new Area(id, "الحوية", "AL HAWIAH");
        assertEquals(area1.hashCode(), area2.hashCode());
    }

    /**
     * Tests toString method.
     */
    @Test
    public void testToStringExpectStringDataToBeCreated() {
        Area area = new Area(id, "الحوية", "AL HAWIAH");
        assertEquals("Area(areaId=1, areaNameAr=الحوية, areaNameEn=AL HAWIAH)", area.toString());
    }
}
