package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodTest {
    private Food testFood;
    private Food testFood1;

    @BeforeEach
    public void runBefore() {
        testFood = new Food("Apple", 200, 50, 100, 10);
        testFood1 = new Food("Uranium", 21000, 120000, 123123, 99999);
    }

    @Test
    public void testConstructorNormal() {
        assertEquals("Apple", testFood.getName());
        assertEquals(200, testFood.getCalorie());
        assertEquals(50, testFood.getProtein());
        assertEquals(100, testFood.getCarb());
        assertEquals(10, testFood.getFat());
    }

    @Test
    public void testConstructorAbnormal() {
        assertEquals("Uranium", testFood1.getName());
        assertEquals(21000, testFood1.getCalorie());
        assertEquals(120000, testFood1.getProtein());
        assertEquals(123123, testFood1.getCarb());
        assertEquals(99999, testFood1.getFat());
    }

}