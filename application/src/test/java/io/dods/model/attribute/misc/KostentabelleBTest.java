package io.dods.model.attribute.misc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Richard Gottschalk
 */
public class KostentabelleBTest {

    @Test
    public void testToLevel0() {
        assertEquals("Activation to level 0 should cost 2", 2, Kostentabelle.B.getAp(0));
    }

    @Test
    public void testToLevel1() {
        assertEquals("Total costs for level 1 should be 4", 4, Kostentabelle.B.getAp(1));
    }

    @Test
    public void testToLevel2() {
        assertEquals("Total costs for level 2 should be 6", 6, Kostentabelle.B.getAp(2));
    }

    @Test
    public void testToLevel3() {
        assertEquals("Total costs for level 3 should be 8", 8, Kostentabelle.B.getAp(3));
    }

    @Test
    public void testToLevel4() {
        assertEquals("Total costs for level 4 should be 10", 10, Kostentabelle.B.getAp(4));
    }

    @Test
    public void testToLevel5() {
        assertEquals("Total costs for level 5 should be 12", 12, Kostentabelle.B.getAp(5));
    }

    @Test
    public void testToLevel6() {
        assertEquals("Total costs for level 6 should be 14", 14, Kostentabelle.B.getAp(6));
    }

    @Test
    public void testToLevel7() {
        assertEquals("Total costs for level 7 should be 16", 16, Kostentabelle.B.getAp(7));
    }

    @Test
    public void testToLevel8() {
        assertEquals("Total costs for level 8 should be 18", 18, Kostentabelle.B.getAp(8));
    }

    @Test
    public void testToLevel9() {
        assertEquals("Total costs for level 9 should be 20", 20, Kostentabelle.B.getAp(9));
    }

    @Test
    public void testToLevel10() {
        assertEquals("Total costs for level 10 should be 22", 22, Kostentabelle.B.getAp(10));
    }

    @Test
    public void testToLevel11() {
        assertEquals("Total costs for level 11 should be 24", 24, Kostentabelle.B.getAp(11));
    }

    @Test
    public void testToLevel12() {
        assertEquals("Total costs for level 12 should be 26", 26, Kostentabelle.B.getAp(12));
    }

    @Test
    public void testToLevel13() {
        assertEquals("Total costs for level 13 should be 30", 30, Kostentabelle.B.getAp(13));
    }

    @Test
    public void testToLevel14() {
        assertEquals("Total costs for level 14 should be 36", 36, Kostentabelle.B.getAp(14));
    }

    @Test
    public void testToLeve15() {
        assertEquals("Total costs for level 15 should be 44", 44, Kostentabelle.B.getAp(15));
    }

    @Test
    public void testToLevel16() {
        assertEquals("Total costs for level 16 should be 54", 54, Kostentabelle.B.getAp(16));
    }

    @Test
    public void testToLevel17() {
        assertEquals("Total costs for level 17 should be 66", 66, Kostentabelle.B.getAp(17));
    }

    @Test
    public void testToLevel18() {
        assertEquals("Total costs for level 18 should be 80", 80, Kostentabelle.B.getAp(18));
    }

    @Test
    public void testToLevel19() {
        assertEquals("Total costs for level 19 should be 96", 96, Kostentabelle.B.getAp(19));
    }

    @Test
    public void testToLevel20() {
        assertEquals("Total costs for level 20 should be 114", 114, Kostentabelle.B.getAp(20));
    }

    @Test
    public void testToLevel21() {
        assertEquals("Total costs for level 21 should be 134", 134, Kostentabelle.B.getAp(21));
    }

    @Test
    public void testToLevel22() {
        assertEquals("Total costs for level 22 should be 156", 156, Kostentabelle.B.getAp(22));
    }

    @Test
    public void testToLevel23() {
        assertEquals("Total costs for level 23 should be 180", 180, Kostentabelle.B.getAp(23));
    }

    @Test
    public void testToLevel24() {
        assertEquals("Total costs for level 24 should be 206", 206, Kostentabelle.B.getAp(24));
    }

    @Test
    public void testToLevel25() {
        assertEquals("Total costs for level 25 should be 234", 234, Kostentabelle.B.getAp(25));
    }

    @Test
    public void testFrom3To5() {
        assertEquals(4, Kostentabelle.B.getAp(3, 5));
    }

    @Test
    public void testFrom15To20() {
        assertEquals(70, Kostentabelle.B.getAp(15, 20));
    }
}
