package io.dods.model.properties.misc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Richard Gottschalk
 */
public class ImprovementChartATest {

    @Test
    public void testToLevel0() {
        assertEquals("Activation to level 0 should cost 1", 1, ImprovementChart.A.getAp(0));
    }

    @Test
    public void testToLevel1() {
        assertEquals("Total costs for level 1 should be 2", 2, ImprovementChart.A.getAp(1));
    }

    @Test
    public void testToLevel2() {
        assertEquals("Total costs for level 2 should be 3", 3, ImprovementChart.A.getAp(2));
    }

    @Test
    public void testToLevel3() {
        assertEquals("Total costs for level 3 should be 4", 4, ImprovementChart.A.getAp(3));
    }

    @Test
    public void testToLevel4() {
        assertEquals("Total costs for level 4 should be 5", 5, ImprovementChart.A.getAp(4));
    }

    @Test
    public void testToLevel5() {
        assertEquals("Total costs for level 5 should be 6", 6, ImprovementChart.A.getAp(5));
    }

    @Test
    public void testToLevel6() {
        assertEquals("Total costs for level 6 should be 7", 7, ImprovementChart.A.getAp(6));
    }

    @Test
    public void testToLevel7() {
        assertEquals("Total costs for level 7 should be 8", 8, ImprovementChart.A.getAp(7));
    }

    @Test
    public void testToLevel8() {
        assertEquals("Total costs for level 8 should be 9", 9, ImprovementChart.A.getAp(8));
    }

    @Test
    public void testToLevel9() {
        assertEquals("Total costs for level 9 should be 10", 10, ImprovementChart.A.getAp(9));
    }

    @Test
    public void testToLevel10() {
        assertEquals("Total costs for level 10 should be 11", 11, ImprovementChart.A.getAp(10));
    }

    @Test
    public void testToLevel11() {
        assertEquals("Total costs for level 11 should be 12", 12, ImprovementChart.A.getAp(11));
    }

    @Test
    public void testToLevel12() {
        assertEquals("Total costs for level 12 should be 13", 13, ImprovementChart.A.getAp(12));
    }

    @Test
    public void testToLevel13() {
        assertEquals("Total costs for level 13 should be 15", 15, ImprovementChart.A.getAp(13));
    }

    @Test
    public void testToLevel14() {
        assertEquals("Total costs for level 14 should be 18", 18, ImprovementChart.A.getAp(14));
    }

    @Test
    public void testToLeve15() {
        assertEquals("Total costs for level 15 should be 22", 22, ImprovementChart.A.getAp(15));
    }

    @Test
    public void testToLevel16() {
        assertEquals("Total costs for level 16 should be 27", 27, ImprovementChart.A.getAp(16));
    }

    @Test
    public void testToLevel17() {
        assertEquals("Total costs for level 17 should be 33", 33, ImprovementChart.A.getAp(17));
    }

    @Test
    public void testToLevel18() {
        assertEquals("Total costs for level 18 should be 40", 40, ImprovementChart.A.getAp(18));
    }

    @Test
    public void testToLevel19() {
        assertEquals("Total costs for level 19 should be 48", 48, ImprovementChart.A.getAp(19));
    }

    @Test
    public void testToLevel20() {
        assertEquals("Total costs for level 20 should be 57", 57, ImprovementChart.A.getAp(20));
    }

    @Test
    public void testToLevel21() {
        assertEquals("Total costs for level 21 should be 67", 67, ImprovementChart.A.getAp(21));
    }

    @Test
    public void testToLevel22() {
        assertEquals("Total costs for level 22 should be 78", 78, ImprovementChart.A.getAp(22));
    }

    @Test
    public void testToLevel23() {
        assertEquals("Total costs for level 23 should be 90", 90, ImprovementChart.A.getAp(23));
    }

    @Test
    public void testToLevel24() {
        assertEquals("Total costs for level 24 should be 103", 103, ImprovementChart.A.getAp(24));
    }

    @Test
    public void testToLevel25() {
        assertEquals("Total costs for level 25 should be 117", 117, ImprovementChart.A.getAp(25));
    }

    @Test
    public void testFrom3To5() {
        assertEquals(2, ImprovementChart.A.getAp(3, 5));
    }

    @Test
    public void testFrom15To20() {
        assertEquals(35, ImprovementChart.A.getAp(15, 20));
    }
}
