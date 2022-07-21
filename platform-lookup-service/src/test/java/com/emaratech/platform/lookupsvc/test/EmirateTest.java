package com.emaratech.platform.lookupsvc.test;

import com.emaratech.platform.lookupsvc.model.Country;
import com.emaratech.platform.lookupsvc.model.Emirate;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EmirateTest {

    private BigDecimal id = BigDecimal.valueOf(1L);

    /**
     * Tests the getter for Emirate.
     */
    @Test
    public void testGettersWhenConstructorInvokedExpectAttributesNotNull() {
        Emirate emirate = new Emirate(id, "FEDERAL", "الاتحاديه","FED");
        assertEquals(1L, emirate.getEmirateId().longValue());
        assertEquals("FEDERAL", emirate.getEmirateNameEn());
        assertEquals("الاتحاديه", emirate.getEmirateNameAr());
        assertEquals("FED", emirate.getCode());
    }

    /**
     * Tests the setter for Emirate.
     */
    @Test
    public void testSettersWhenNoArgsConstructorInvokedExpectAttributesNotNull() {
        Emirate emirate = new Emirate();
        emirate.setEmirateId(id);
        emirate.setEmirateNameEn("FEDERAL");
        emirate.setEmirateNameAr("الاتحاديه");
        emirate.setCode("FED");
        assertEquals(1L, emirate.getEmirateId().longValue());
        assertEquals("FEDERAL", emirate.getEmirateNameEn());
        assertEquals("الاتحاديه", emirate.getEmirateNameAr());
        assertEquals("FED", emirate.getCode());
    }

    /**
     * Tests equals method when two instances with same data compared. Expect
     * equality.
     */
    @Test
    public void testEqualsWhenTwoSameInstanceCreatedExpectEquality() {
        Emirate emirate1 = new Emirate(id, "FEDERAL", "الاتحاديه", "FED");
        Emirate emirate2 = new Emirate(id, "FEDERAL", "الاتحاديه", "FED");

        assertTrue(emirate1.equals(emirate2));
    }

    /**
     * Tests equals method when two instances with different data compared. Expect
     * inequality.
     */
    @Test
    public void testEqualsWhenTwoDiffInstanceCreatedExpectInEquality() {
        Emirate emirate1 = new Emirate(id, "FEDERAL", "الاتحاديه", "FED");
        Emirate emirate2 = new Emirate(id, "DUBAI", "دبي", "DXB");

        assertFalse(emirate1.equals(emirate2));

        assertFalse(emirate1.equals(null));
    }

    /**
     * Tests hashcode method.
     */
    @Test
    public void testHashcodeWhenTwoSameInstanceCreatedExpectSameHashcode() {
        Emirate emirate1 = new Emirate(id, "DUBAI", "دبي", "DXB");
        Emirate emirate2 = new Emirate(id, "DUBAI", "دبي", "DXB");
        assertEquals(emirate1.hashCode(), emirate2.hashCode());
    }

    /**
     * Tests toString method.
     */
    @Test
    public void testToStringExpectStringDataToBeCreated() {
        Emirate emirate = new Emirate(id, "DUBAI", "دبي", "DXB");
        assertEquals("Emirate(emirateId=1, emirateNameEn=DUBAI, emirateNameAr=دبي, code=DXB)", emirate.toString());
    }
}
