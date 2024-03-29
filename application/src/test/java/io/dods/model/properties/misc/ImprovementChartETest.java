package io.dods.model.properties.misc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Richard Gottschalk
 */
public class ImprovementChartETest {

    @Test
    public void testToLevel0() {
        assertEquals("SPECIAL RULE: E cannot be on level zero!", 0, ImprovementChart.E.getAp(0));
    }

    @Test
    public void testToLevel1() {
        assertEquals("Total costs for level 1 should be 15", 15, ImprovementChart.E.getAp(1));
    }

    @Test
    public void testToLevel2() {
        assertEquals("Total costs for level 2 should be 30", 30, ImprovementChart.E.getAp(2));
    }

    @Test
    public void testToLevel3() {
        assertEquals("Total costs for level 3 should be 45", 45, ImprovementChart.E.getAp(3));
    }

    @Test
    public void testToLevel4() {
        assertEquals("Total costs for level 4 should be 60", 60, ImprovementChart.E.getAp(4));
    }

    @Test
    public void testToLevel5() {
        assertEquals("Total costs for level 5 should be 75", 75, ImprovementChart.E.getAp(5));
    }

    @Test
    public void testToLevel6() {
        assertEquals("Total costs for level 6 should be 90", 90, ImprovementChart.E.getAp(6));
    }

    @Test
    public void testToLevel7() {
        assertEquals("Total costs for level 7 should be 105", 105, ImprovementChart.E.getAp(7));
    }

    @Test
    public void testToLevel8() {
        assertEquals("Total costs for level 8 should be 120", 120, ImprovementChart.E.getAp(8));
    }

    @Test
    public void testToLevel9() {
        assertEquals("Total costs for level 9 should be 135", 135, ImprovementChart.E.getAp(9));
    }

    @Test
    public void testToLevel10() {
        assertEquals("Total costs for level 10 should be 150", 150, ImprovementChart.E.getAp(10));
    }

    @Test
    public void testToLevel11() {
        assertEquals("Total costs for level 11 should be 165", 165, ImprovementChart.E.getAp(11));
    }

    @Test
    public void testToLevel12() {
        assertEquals("Total costs for level 12 should be 180", 180, ImprovementChart.E.getAp(12));
    }

    @Test
    public void testToLevel13() {
        assertEquals("Total costs for level 13 should be 195", 195, ImprovementChart.E.getAp(13));
    }

    @Test
    public void testToLevel14() {
        assertEquals("Total costs for level 14 should be 210", 210, ImprovementChart.E.getAp(14));
    }

    @Test
    public void testToLeve15() {
        assertEquals("Total costs for level 15 should be 240", 240, ImprovementChart.E.getAp(15));
    }

    @Test
    public void testToLevel16() {
        assertEquals("Total costs for level 16 should be 285", 285, ImprovementChart.E.getAp(16));
    }

    @Test
    public void testToLevel17() {
        assertEquals("Total costs for level 17 should be 345", 345, ImprovementChart.E.getAp(17));
    }

    @Test
    public void testToLevel18() {
        assertEquals("Total costs for level 18 should be 420", 420, ImprovementChart.E.getAp(18));
    }

    @Test
    public void testToLevel19() {
        assertEquals("Total costs for level 19 should be 510", 510, ImprovementChart.E.getAp(19));
    }

    @Test
    public void testToLevel20() {
        assertEquals("Total costs for level 20 should be 615", 615, ImprovementChart.E.getAp(20));
    }

    @Test
    public void testToLevel21() {
        assertEquals("Total costs for level 21 should be 735", 735, ImprovementChart.E.getAp(21));
    }

    @Test
    public void testToLevel22() {
        assertEquals("Total costs for level 22 should be 870", 870, ImprovementChart.E.getAp(22));
    }

    @Test
    public void testToLevel23() {
        assertEquals("Total costs for level 23 should be 1020", 1020, ImprovementChart.E.getAp(23));
    }

    @Test
    public void testToLevel24() {
        assertEquals("Total costs for level 24 should be 1185", 1185, ImprovementChart.E.getAp(24));
    }

    @Test
    public void testToLevel25() {
        assertEquals("Total costs for level 25 should be 1365", 1365, ImprovementChart.E.getAp(25));
    }

    @Test
    public void testFrom3To5() {
        assertEquals(30, ImprovementChart.E.getAp(3, 5));
    }

    @Test
    public void testFrom15To20() {
        assertEquals(375, ImprovementChart.E.getAp(15, 20));
    }
}
