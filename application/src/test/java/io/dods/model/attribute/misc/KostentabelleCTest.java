package io.dods.model.attribute.misc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Richard Gottschalk
 */
public class KostentabelleCTest {

    @Test
    public void testToLevel0() {
        assertEquals("Activation to level 0 should cost 3", 3, Kostentabelle.C.getAp(0));
    }

    @Test
    public void testToLevel1() {
        assertEquals("Total costs for level 1 should be 6", 6, Kostentabelle.C.getAp(1));
    }

    @Test
    public void testToLevel2() {
        assertEquals("Total costs for level 2 should be 9", 9, Kostentabelle.C.getAp(2));
    }

    @Test
    public void testToLevel3() {
        assertEquals("Total costs for level 3 should be 12", 12, Kostentabelle.C.getAp(3));
    }

    @Test
    public void testToLevel4() {
        assertEquals("Total costs for level 4 should be 15", 15, Kostentabelle.C.getAp(4));
    }

    @Test
    public void testToLevel5() {
        assertEquals("Total costs for level 5 should be 18", 18, Kostentabelle.C.getAp(5));
    }

    @Test
    public void testToLevel6() {
        assertEquals("Total costs for level 6 should be 21", 21, Kostentabelle.C.getAp(6));
    }

    @Test
    public void testToLevel7() {
        assertEquals("Total costs for level 7 should be 24", 24, Kostentabelle.C.getAp(7));
    }

    @Test
    public void testToLevel8() {
        assertEquals("Total costs for level 8 should be 27", 27, Kostentabelle.C.getAp(8));
    }

    @Test
    public void testToLevel9() {
        assertEquals("Total costs for level 9 should be 30", 30, Kostentabelle.C.getAp(9));
    }

    @Test
    public void testToLevel10() {
        assertEquals("Total costs for level 10 should be 33", 33, Kostentabelle.C.getAp(10));
    }

    @Test
    public void testToLevel11() {
        assertEquals("Total costs for level 11 should be 36", 36, Kostentabelle.C.getAp(11));
    }

    @Test
    public void testToLevel12() {
        assertEquals("Total costs for level 12 should be 39", 39, Kostentabelle.C.getAp(12));
    }

    @Test
    public void testToLevel13() {
        assertEquals("Total costs for level 13 should be 45", 45, Kostentabelle.C.getAp(13));
    }

    @Test
    public void testToLevel14() {
        assertEquals("Total costs for level 14 should be 54", 54, Kostentabelle.C.getAp(14));
    }

    @Test
    public void testToLeve15() {
        assertEquals("Total costs for level 15 should be 66", 66, Kostentabelle.C.getAp(15));
    }

    @Test
    public void testToLevel16() {
        assertEquals("Total costs for level 16 should be 81", 81, Kostentabelle.C.getAp(16));
    }

    @Test
    public void testToLevel17() {
        assertEquals("Total costs for level 17 should be 99", 99, Kostentabelle.C.getAp(17));
    }

    @Test
    public void testToLevel18() {
        assertEquals("Total costs for level 18 should be 120", 120, Kostentabelle.C.getAp(18));
    }

    @Test
    public void testToLevel19() {
        assertEquals("Total costs for level 19 should be 144", 144, Kostentabelle.C.getAp(19));
    }

    @Test
    public void testToLevel20() {
        assertEquals("Total costs for level 20 should be 171", 171, Kostentabelle.C.getAp(20));
    }

    @Test
    public void testToLevel21() {
        assertEquals("Total costs for level 21 should be 201", 201, Kostentabelle.C.getAp(21));
    }

    @Test
    public void testToLevel22() {
        assertEquals("Total costs for level 22 should be 234", 234, Kostentabelle.C.getAp(22));
    }

    @Test
    public void testToLevel23() {
        assertEquals("Total costs for level 23 should be 270", 270, Kostentabelle.C.getAp(23));
    }

    @Test
    public void testToLevel24() {
        assertEquals("Total costs for level 24 should be 309", 309, Kostentabelle.C.getAp(24));
    }

    @Test
    public void testToLevel25() {
        assertEquals("Total costs for level 25 should be 351", 351, Kostentabelle.C.getAp(25));
    }

    @Test
    public void testFrom3To5() {
        assertEquals(6, Kostentabelle.C.getAp(3, 5));
    }

    @Test
    public void testFrom15To20() {
        assertEquals(105, Kostentabelle.C.getAp(15, 20));
    }
}
