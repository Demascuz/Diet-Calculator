package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class FoodDatabaseTest {
    private FoodDatabase foodDatabase;

    @BeforeEach
    public void runBefore() {
        foodDatabase = new FoodDatabase();
    }

    @Test
    public void testConstructor() {
        assertEquals(new ArrayList<Food>(), foodDatabase.getFoodData());
        assertEquals(0, foodDatabase.getFoodData().size());
    }

    @Test
    public void testAddFoodParameterFood() {
        ArrayList<Food> foods = new ArrayList<>();

        Food food = new Food("0", 100, 200, 300, 400);
        Food food1 = new Food("1", 200, 300, 400, 500);

        //1 element
        foodDatabase.addFood(food);
        foods.add(food);
        assertEquals(foods, foodDatabase.getFoodData());
        assertEquals(1, foodDatabase.getFoodData().size());

        //2 elements
        foodDatabase.addFood(food1);
        foods.add(food1);
        assertEquals(foods, foodDatabase.getFoodData());
        assertEquals(2, foodDatabase.getFoodData().size());
    }

    @Test
    public void testGetFood() {
        Food food = new Food("3", 100, 200, 300, 400);
        Food food1 = new Food("4", 200, 300, 400, 500);
        Food food2 = new Food("9", 300, 400, 500, 600);

        foodDatabase.addFood(food);
        foodDatabase.addFood(food1);
        foodDatabase.addFood(food2);

        //Get 1st element
        assertEquals(food, foodDatabase.getFood(0));

        //Get 2nd element
        assertEquals(food1, foodDatabase.getFood(1));

        //Get last element
        assertEquals(food2, foodDatabase.getFood(foodDatabase.getFoodData().size() - 1));
    }
}
















