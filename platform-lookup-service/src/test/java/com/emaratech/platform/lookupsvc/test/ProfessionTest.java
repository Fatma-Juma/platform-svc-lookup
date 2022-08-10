package com.emaratech.platform.lookupsvc.test;

import com.emaratech.platform.lookupsvc.model.Profession;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProfessionTest {
    private Long id = 1L;

    /**
     * Tests the getter for Profession.
     */
    @Test
    public void testGettersWhenConstructorInvokedExpectAttributesNotNull() {
        Profession profession = new Profession(id, "مدير خدمات العملاء", "CUSTOMER SERVICES MANAGER");
        assertEquals(1L, profession.getProfessionId().longValue());
        assertEquals("مدير خدمات العملاء", profession.getProfessionNameAr());
        assertEquals("CUSTOMER SERVICES MANAGER", profession.getProfessionNameEn());
    }

    /**
     * Tests the setter for Profession.
     */
    @Test
    public void testSettersWhenNoArgsConstructorInvokedExpectAttributesNotNull() {
        Profession profession = new Profession();
        profession.setProfessionId(id);
        profession.setProfessionNameAr("مدير خدمات العملاء");
        profession.setProfessionNameEn("CUSTOMER SERVICES MANAGER");
        assertEquals(1L, profession.getProfessionId().longValue());
        assertEquals("مدير خدمات العملاء", profession.getProfessionNameAr());
        assertEquals("CUSTOMER SERVICES MANAGER", profession.getProfessionNameEn());
    }

    /**
     * Tests equals method when two instances with same data compared. Expect
     * equality.
     */
    @Test
    public void testEqualsWhenTwoSameInstanceCreatedExpectEquality() {
        Profession profession1 = new Profession(id, "مدير خدمات العملاء", "CUSTOMER SERVICES MANAGER");
        Profession profession2 = new Profession(id, "مدير خدمات العملاء", "CUSTOMER SERVICES MANAGER");

        assertTrue(profession1.equals(profession2));
    }

    /**
     * Tests equals method when two instances with different data compared. Expect
     * inequality.
     */
    @Test
    public void testEqualsWhenTwoDiffInstanceCreatedExpectInEquality() {
        Profession profession1 = new Profession(id, "مدير خدمات العملاء", "CUSTOMER SERVICES MANAGER");
        Profession profession2 = new Profession(id, "مدير ادارة - خدمة العملاء لمنطقة المطار", "DIRECTOR AIRPORT ZONE CUSTOMER SERVICES");

        assertFalse(profession1.equals(profession2));

        assertFalse(profession1.equals(null));
    }

    /**
     * Tests hashcode method.
     */
    @Test
    public void testHashcodeWhenTwoSameInstanceCreatedExpectSameHashcode() {
        Profession profession1 = new Profession(id, "مدير ادارة - خدمة العملاء لمنطقة المطار", "DIRECTOR AIRPORT ZONE CUSTOMER SERVICES");
        Profession profession2 = new Profession(id, "مدير ادارة - خدمة العملاء لمنطقة المطار", "DIRECTOR AIRPORT ZONE CUSTOMER SERVICES");
        assertEquals(profession1.hashCode(), profession2.hashCode());
    }

    /**
     * Tests toString method.
     */
    @Test
    public void testToStringExpectStringDataToBeCreated() {
        Profession profession = new Profession(id, "مدير ادارة - خدمة العملاء لمنطقة المطار", "DIRECTOR AIRPORT ZONE CUSTOMER SERVICES");
        assertEquals("Profession(professionId=1, professionNameAr=مدير ادارة - خدمة العملاء لمنطقة المطار, professionNameEn=DIRECTOR AIRPORT ZONE CUSTOMER SERVICES)",
                     profession.toString());
    }
}
