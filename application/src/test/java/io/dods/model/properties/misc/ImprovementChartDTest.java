package io.dods.model.properties.misc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Richard Gottschalk
 */
public class ImprovementChartDTest {

    @Test
    public void testToLevel0() {
        assertEquals("Activation to level 0 should cost 4", 4, ImprovementChart.D.getAp(0));
    }

    @Test
    public void testToLevel1() {
        assertEquals("Total costs for level 1 should be 8", 8, ImprovementChart.D.getAp(1));
    }

    @Test
    public void testToLevel2() {
        assertEquals("Total costs for level 2 should be 12", 12, ImprovementChart.D.getAp(2));
    }

    @Test
    public void testToLevel3() {
        assertEquals("Total costs for level 3 should be 16", 16, ImprovementChart.D.getAp(3));
    }

    @Test
    public void testToLevel4() {
        assertEquals("Total costs for level 4 should be 20", 20, ImprovementChart.D.getAp(4));
    }

    @Test
    public void testToLevel5() {
        assertEquals("Total costs for level 5 should be 24", 24, ImprovementChart.D.getAp(5));
    }

    @Test
    public void testToLevel6() {
        assertEquals("Total costs for level 6 should be 28", 28, ImprovementChart.D.getAp(6));
    }

    @Test
    public void testToLevel7() {
        assertEquals("Total costs for level 7 should be 32", 32, ImprovementChart.D.getAp(7));
    }

    @Test
    public void testToLevel8() {
        assertEquals("Total costs for level 8 should be 36", 36, ImprovementChart.D.getAp(8));
    }

    @Test
    public void testToLevel9() {
        assertEquals("Total costs for level 9 should be 40", 40, ImprovementChart.D.getAp(9));
    }

    @Test
    public void testToLevel10() {
        assertEquals("Total costs for level 10 should be 44", 44, ImprovementChart.D.getAp(10));
    }

    @Test
    public void testToLevel11() {
        assertEquals("Total costs for level 11 should be 48", 48, ImprovementChart.D.getAp(11));
    }

    @Test
    public void testToLevel12() {
        assertEquals("Total costs for level 12 should be 52", 52, ImprovementChart.D.getAp(12));
    }

    @Test
    public void testToLevel13() {
        assertEquals("Total costs for level 13 should be 60", 60, ImprovementChart.D.getAp(13));
    }

    @Test
    public void testToLevel14() {
        assertEquals("Total costs for level 14 should be 72", 72, ImprovementChart.D.getAp(14));
    }

    @Test
    public void testToLeve15() {
        assertEquals("Total costs for level 15 should be 88", 88, ImprovementChart.D.getAp(15));
    }

    @Test
    public void testToLevel16() {
        assertEquals("Total costs for level 16 should be 108", 108, ImprovementChart.D.getAp(16));
    }

    @Test
    public void testToLevel17() {
        assertEquals("Total costs for level 17 should be 132", 132, ImprovementChart.D.getAp(17));
    }

    @Test
    public void testToLevel18() {
        assertEquals("Total costs for level 18 should be 160", 160, ImprovementChart.D.getAp(18));
    }

    @Test
    public void testToLevel19() {
        assertEquals("Total costs for level 19 should be 192", 192, ImprovementChart.D.getAp(19));
    }

    @Test
    public void testToLevel20() {
        assertEquals("Total costs for level 20 should be 228", 228, ImprovementChart.D.getAp(20));
    }

    @Test
    public void testToLevel21() {
        assertEquals("Total costs for level 21 should be 268", 268, ImprovementChart.D.getAp(21));
    }

    @Test
    public void testToLevel22() {
        assertEquals("Total costs for level 22 should be 312", 312, ImprovementChart.D.getAp(22));
    }

    @Test
    public void testToLevel23() {
        assertEquals("Total costs for level 23 should be 360", 360, ImprovementChart.D.getAp(23));
    }

    @Test
    public void testToLevel24() {
        assertEquals("Total costs for level 24 should be 412", 412, ImprovementChart.D.getAp(24));
    }

    @Test
    public void testToLevel25() {
        assertEquals("Total costs for level 25 should be 468", 468, ImprovementChart.D.getAp(25));
    }

    @Test
    public void testFrom3To5() {
        assertEquals(8, ImprovementChart.D.getAp(3, 5));
    }

    @Test
    public void testFrom15To20() {
        assertEquals(140, ImprovementChart.D.getAp(15, 20));
    }
}
