package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GoalDatabaseTest {
    private GoalDatabase goalDatabase;

    @BeforeEach
    public void runBefore() {
        goalDatabase = new GoalDatabase();
    }

    @Test
    public void testConstructor() {
        assertEquals(new ArrayList<>(), goalDatabase.getGoalDatabase());
        assertEquals(0, goalDatabase.getGoalDatabase().size());

        ArrayList<ArrayList<Food>> foods = new ArrayList<>();
        ArrayList<Food> insideFood = new ArrayList<>();
        foods.add(insideFood);
    }

    @Test
    public void testAddGoal() {
        ArrayList<Goal> goals = new ArrayList<>();
        Goal goal = new Goal(100, 200, 300, 400);
        Goal goal1 = new Goal(200, 300, 400, 500);

        //Add 1 goal
        goalDatabase.addGoal(goal);
        goals.add(goal);

        assertEquals(goals, goalDatabase.getGoalDatabase());
        assertEquals(1, goalDatabase.getGoalDatabase().size());
        assertEquals(goal, goalDatabase.getLatestGoal());

        //Add 2 goals
        goalDatabase.addGoal(goal1);
        goals.add(goal1);

        assertEquals(goals, goalDatabase.getGoalDatabase());
        assertEquals(2, goalDatabase.getGoalDatabase().size());
        assertEquals(goal1, goalDatabase.getLatestGoal());
    }

    @Test
    public void testModifyLatestGoal() {
        Goal goal = new Goal(10, 10, 10, 10);
        goalDatabase.addGoal(goal);

        //1st modification
        goalDatabase.modifyLatestGoal(100, 200, 300, 400);

        assertEquals(100, goalDatabase.getLatestGoal().getCalorieGoal());
        assertEquals(200, goalDatabase.getLatestGoal().getProteinGoal());
        assertEquals(300, goalDatabase.getLatestGoal().getCarbGoal());
        assertEquals(400, goalDatabase.getLatestGoal().getFatGoal());

        assertEquals(0, goalDatabase.getLatestGoal().getCurrentCalorie());
        assertEquals(0, goalDatabase.getLatestGoal().getCurrentProtein());
        assertEquals(0, goalDatabase.getLatestGoal().getCurrentCarb());
        assertEquals(0, goalDatabase.getLatestGoal().getCurrentFat());


        //2nd modification
        goalDatabase.modifyLatestGoal(50, 400, 20, 900);

        assertEquals(50, goalDatabase.getLatestGoal().getCalorieGoal());
        assertEquals(400, goalDatabase.getLatestGoal().getProteinGoal());
        assertEquals(20, goalDatabase.getLatestGoal().getCarbGoal());
        assertEquals(900, goalDatabase.getLatestGoal().getFatGoal());

        assertEquals(0, goalDatabase.getLatestGoal().getCurrentCalorie());
        assertEquals(0, goalDatabase.getLatestGoal().getCurrentProtein());
        assertEquals(0, goalDatabase.getLatestGoal().getCurrentCarb());
        assertEquals(0, goalDatabase.getLatestGoal().getCurrentFat());
    }

    @Test
    public void testAddFoodToGoal() {
        Food food = new Food("1", 10, 20, 30, 40);
        Food food1 = new Food("1", 20, 40, 60, 80);
        goalDatabase.addGoal(new Goal(10, 10, 10, 10));

        //Add 1 food
        goalDatabase.addFoodToGoal(food);

        assertEquals(10, goalDatabase.getLatestGoal().getCurrentCalorie());
        assertEquals(20, goalDatabase.getLatestGoal().getCurrentProtein());
        assertEquals(30, goalDatabase.getLatestGoal().getCurrentCarb());
        assertEquals(40, goalDatabase.getLatestGoal().getCurrentFat());

        //Add 2 foods
        goalDatabase.addFoodToGoal(food1);

        assertEquals(30, goalDatabase.getLatestGoal().getCurrentCalorie());
        assertEquals(60, goalDatabase.getLatestGoal().getCurrentProtein());
        assertEquals(90, goalDatabase.getLatestGoal().getCurrentCarb());
        assertEquals(120, goalDatabase.getLatestGoal().getCurrentFat());
    }

    @Test
    public void testNewDay() {
        Goal goal = new Goal(100, 200, 300,400);

        goal.addCalorie(50);
        goal.addProtein(300);
        goal.addCarb(150);
        goal.addFat(400);

        goalDatabase.addGoal(goal);

        //Nutrition statuses - before update
        assertEquals(0, goalDatabase.getLatestGoal().getCalorieStatus());
        assertEquals(1, goalDatabase.getLatestGoal().getProteinStatus());
        assertEquals(0, goalDatabase.getLatestGoal().getCarbStatus());
        assertEquals(2, goalDatabase.getLatestGoal().getFatStatus());

        goalDatabase.newDay();

        //Goal overview
        assertEquals(goal, goalDatabase.getGoal(goalDatabase.getGoalDatabase().size() - 2));

        assertEquals(100, goalDatabase.getLatestGoal().getCalorieGoal());
        assertEquals(200, goalDatabase.getLatestGoal().getProteinGoal());
        assertEquals(300, goalDatabase.getLatestGoal().getCarbGoal());
        assertEquals(400, goalDatabase.getLatestGoal().getFatGoal());

        //Current fields set to 0
        assertEquals(0, goalDatabase.getLatestGoal().getCurrentCalorie());
        assertEquals(0, goalDatabase.getLatestGoal().getCurrentProtein());
        assertEquals(0, goalDatabase.getLatestGoal().getCurrentCarb());
        assertEquals(0, goalDatabase.getLatestGoal().getCurrentFat());
    }
}






















