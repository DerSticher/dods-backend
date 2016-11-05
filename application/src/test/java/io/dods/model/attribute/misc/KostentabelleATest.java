package io.dods.model.attribute.misc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Richard Gottschalk
 */
public class KostentabelleATest {

    @Test
    public void testToLevel0() {
        assertEquals("Activation to level 0 should cost 1", 1, Kostentabelle.A.getAp(0));
    }

    @Test
    public void testToLevel1() {
        assertEquals("Total costs for level 1 should be 2", 2, Kostentabelle.A.getAp(1));
    }

    @Test
    public void testToLevel2() {
        assertEquals("Total costs for level 2 should be 3", 3, Kostentabelle.A.getAp(2));
    }

    @Test
    public void testToLevel3() {
        assertEquals("Total costs for level 3 should be 4", 4, Kostentabelle.A.getAp(3));
    }

    @Test
    public void testToLevel4() {
        assertEquals("Total costs for level 4 should be 5", 5, Kostentabelle.A.getAp(4));
    }

    @Test
    public void testToLevel5() {
        assertEquals("Total costs for level 5 should be 6", 6, Kostentabelle.A.getAp(5));
    }

    @Test
    public void testToLevel6() {
        assertEquals("Total costs for level 6 should be 7", 7, Kostentabelle.A.getAp(6));
    }

    @Test
    public void testToLevel7() {
        assertEquals("Total costs for level 7 should be 8", 8, Kostentabelle.A.getAp(7));
    }

    @Test
    public void testToLevel8() {
        assertEquals("Total costs for level 8 should be 9", 9, Kostentabelle.A.getAp(8));
    }

    @Test
    public void testToLevel9() {
        assertEquals("Total costs for level 9 should be 10", 10, Kostentabelle.A.getAp(9));
    }

    @Test
    public void testToLevel10() {
        assertEquals("Total costs for level 10 should be 11", 11, Kostentabelle.A.getAp(10));
    }

    @Test
    public void testToLevel11() {
        assertEquals("Total costs for level 11 should be 12", 12, Kostentabelle.A.getAp(11));
    }

    @Test
    public void testToLevel12() {
        assertEquals("Total costs for level 12 should be 13", 13, Kostentabelle.A.getAp(12));
    }

    @Test
    public void testToLevel13() {
        assertEquals("Total costs for level 13 should be 15", 15, Kostentabelle.A.getAp(13));
    }

    @Test
    public void testToLevel14() {
        assertEquals("Total costs for level 14 should be 18", 18, Kostentabelle.A.getAp(14));
    }

    @Test
    public void testToLeve15() {
        assertEquals("Total costs for level 15 should be 22", 22, Kostentabelle.A.getAp(15));
    }

    @Test
    public void testToLevel16() {
        assertEquals("Total costs for level 16 should be 27", 27, Kostentabelle.A.getAp(16));
    }

    @Test
    public void testToLevel17() {
        assertEquals("Total costs for level 17 should be 33", 33, Kostentabelle.A.getAp(17));
    }

    @Test
    public void testToLevel18() {
        assertEquals("Total costs for level 18 should be 40", 40, Kostentabelle.A.getAp(18));
    }

    @Test
    public void testToLevel19() {
        assertEquals("Total costs for level 19 should be 48", 48, Kostentabelle.A.getAp(19));
    }

    @Test
    public void testToLevel20() {
        assertEquals("Total costs for level 20 should be 57", 57, Kostentabelle.A.getAp(20));
    }

    @Test
    public void testToLevel21() {
        assertEquals("Total costs for level 21 should be 67", 67, Kostentabelle.A.getAp(21));
    }

    @Test
    public void testToLevel22() {
        assertEquals("Total costs for level 22 should be 78", 78, Kostentabelle.A.getAp(22));
    }

    @Test
    public void testToLevel23() {
        assertEquals("Total costs for level 23 should be 90", 90, Kostentabelle.A.getAp(23));
    }

    @Test
    public void testToLevel24() {
        assertEquals("Total costs for level 24 should be 103", 103, Kostentabelle.A.getAp(24));
    }

    @Test
    public void testToLevel25() {
        assertEquals("Total costs for level 25 should be 117", 117, Kostentabelle.A.getAp(25));
    }

    @Test
    public void testFrom3To5() {
        assertEquals(2, Kostentabelle.A.getAp(3, 5));
    }

    @Test
    public void testFrom15To20() {
        assertEquals(35, Kostentabelle.A.getAp(15, 20));
    }
}
