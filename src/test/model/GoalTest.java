package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GoalTest {
    private Goal goal;

    @BeforeEach
    public void runBefore() {
        goal = new Goal(400, 300, 200, 100);
    }

    @Test
    public void testConstructor() {
        assertEquals(400, goal.getCalorieGoal());
        assertEquals(300, goal.getProteinGoal());
        assertEquals(200, goal.getCarbGoal());
        assertEquals(100, goal.getFatGoal());

        assertEquals(0, goal.getCurrentCalorie());
        assertEquals(0, goal.getCurrentProtein());
        assertEquals(0, goal.getCurrentCarb());
        assertEquals(0, goal.getCurrentFat());
    }

    @Test
    public void testAddCalorie() {
        goal.addCalorie(4);
        assertEquals(4, goal.getCurrentCalorie());

        goal.addCalorie(342);
        assertEquals(346, goal.getCurrentCalorie());
    }

    @Test
    public void testAddProtein() {
        goal.addProtein(9);
        assertEquals(9, goal.getCurrentProtein());

        goal.addProtein(912);
        assertEquals(921, goal.getCurrentProtein());
    }

    @Test
    public void testAddCarb() {
        goal.addCarb(2);
        assertEquals(2, goal.getCurrentCarb());

        goal.addCarb(1203);
        assertEquals(1205, goal.getCurrentCarb());
    }

    @Test
    public void testAddFat() {
        goal.addFat(5);
        assertEquals(5, goal.getCurrentFat());

        goal.addFat(124);
        assertEquals(129, goal.getCurrentFat());
    }

    @Test
    public void testChangeCalorieGoal() {
        goal.setCalorieGoal(1239);
        assertEquals(1239, goal.getCalorieGoal());

        goal.setCalorieGoal(8362712);
        assertEquals(8362712, goal.getCalorieGoal());
    }

    @Test
    public void testChangeProteinGoal() {
        goal.setProteinGoal(4234);
        assertEquals(4234, goal.getProteinGoal());

        goal.setProteinGoal(36289);
        assertEquals(36289, goal.getProteinGoal());
    }

    @Test
    public void testChangeCarbGoal() {
        goal.setCarbGoal(2324);
        assertEquals(2324, goal.getCarbGoal());

        goal.setCarbGoal(9876765);
        assertEquals(9876765, goal.getCarbGoal());
    }

    @Test
    public void testChangeFatGoal() {
        goal.setFatGoal(7459);
        assertEquals(7459, goal.getFatGoal());

        goal.setFatGoal(1253123);
        assertEquals(1253123, goal.getFatGoal());
    }

    @Test
    public void testCalculateCalorieDiff() {
        goal.setCalorieGoal(2000);
        goal.setCurrentCalorie(0);

        //Within boundary
        goal.addCalorie(100);
        assertEquals(1900, goal.calculateCalorieDiff());

        //At boundary-multiple
        goal.addCalorie(1900);
        assertEquals(0, goal.calculateCalorieDiff());

        //Past boundary-multiple
        goal.addCalorie(500);
        assertEquals(-500, goal.calculateCalorieDiff());

        //At boundary-single
        goal.setCalorieGoal(1500);
        goal.setCurrentCalorie(0);

        goal.addCalorie(1500);
        assertEquals(0, goal.calculateCalorieDiff());

        //Past boundary-single
        goal.setCalorieGoal(1500);
        goal.setCurrentCalorie(0);

        goal.addCalorie(1550);
        assertEquals(-50, goal.calculateCalorieDiff());
    }

    @Test
    public void testCalculateProteinDiff() {
        goal.setProteinGoal(2000);
        goal.setCurrentProtein(0);

        //Within boundary
        goal.addProtein(100);
        assertEquals(1900, goal.calculateProteinDiff());

        //At boundary-multiple
        goal.addProtein(1900);
        assertEquals(0, goal.calculateProteinDiff());

        //Past boundary-multiple
        goal.addProtein(500);
        assertEquals(-500, goal.calculateProteinDiff());

        //At boundary-single
        goal.setProteinGoal(1500);
        goal.setCurrentProtein(0);

        goal.addProtein(1500);
        assertEquals(0, goal.calculateProteinDiff());

        //Past boundary-single
        goal.setProteinGoal(1500);
        goal.setCurrentProtein(0);

        goal.addProtein(1550);
        assertEquals(-50, goal.calculateProteinDiff());
    }

    @Test
    public void testCalculateCarbDiff() {
        goal.setCarbGoal(2000);
        goal.setCurrentCarb(0);

        //Within boundary
        goal.addCarb(100);
        assertEquals(1900, goal.calculateCarbDiff());

        //At boundary-multiple
        goal.addCarb(1900);
        assertEquals(0, goal.calculateCarbDiff());

        //Past boundary-multiple
        goal.addCarb(500);
        assertEquals(-500, goal.calculateCarbDiff());

        //At boundary-single
        goal.setCarbGoal(1500);
        goal.setCurrentCarb(0);

        goal.addCarb(1500);
        assertEquals(0, goal.calculateCarbDiff());

        //Past boundary-single
        goal.setCarbGoal(1500);
        goal.setCurrentCarb(0);

        goal.addCarb(1550);
        assertEquals(-50, goal.calculateCarbDiff());
    }

    @Test
    public void testCalculateFatDiff() {
        goal.setFatGoal(2000);
        goal.setCurrentFat(0);

        //Within boundary
        goal.addFat(100);
        assertEquals(1900, goal.calculateFatDiff());

        //At boundary-multiple
        goal.addFat(1900);
        assertEquals(0, goal.calculateFatDiff());

        //Past boundary-multiple
        goal.addFat(500);
        assertEquals(-500, goal.calculateFatDiff());

        //At boundary-single
        goal.setFatGoal(1500);
        goal.setCurrentFat(0);

        goal.addFat(1500);
        assertEquals(0, goal.calculateFatDiff());

        //Past boundary-single
        goal.setFatGoal(1500);
        goal.setCurrentFat(0);

        goal.addFat(1550);
        assertEquals(-50, goal.calculateFatDiff());
    }

    @Test
    public void testGetCalorieStatus() {
        goal.setCalorieGoal(2000);

        //Eat more to get to goal
        goal.setCurrentCalorie(1000);
        assertEquals(0, goal.getCalorieStatus());

        //Just under goal
        goal.setCurrentCalorie(1999);
        assertEquals(0, goal.getCalorieStatus());

        //Over the goal
        goal.setCurrentCalorie(2100);
        assertEquals(1, goal.getCalorieStatus());

        //Just over goal
        goal.setCurrentCalorie(2001);
        assertEquals(1, goal.getCalorieStatus());

        //Met goal
        goal.setCurrentCalorie(2000);
        assertEquals(2, goal.getCalorieStatus());
    }

    @Test
    public void testGetProteinStatus() {
        goal.setProteinGoal(2000);

        //Eat more to get to goal
        goal.setCurrentProtein(1000);
        assertEquals(0, goal.getProteinStatus());

        //Just under goal
        goal.setCurrentProtein(1999);
        assertEquals(0, goal.getProteinStatus());

        //Over the goal
        goal.setCurrentProtein(2100);
        assertEquals(1, goal.getProteinStatus());

        //Just over goal
        goal.setCurrentProtein(2001);
        assertEquals(1, goal.getProteinStatus());

        //Met goal
        goal.setCurrentProtein(2000);
        assertEquals(2, goal.getProteinStatus());
    }

    @Test
    public void testGetCarbStatus() {
        goal.setCarbGoal(2000);

        //Eat more to get to goal
        goal.setCurrentCarb(1000);
        assertEquals(0, goal.getCarbStatus());

        //Just under goal
        goal.setCurrentCarb(1999);
        assertEquals(0, goal.getCarbStatus());

        //Over the goal
        goal.setCurrentCarb(2100);
        assertEquals(1, goal.getCarbStatus());

        //Just over goal
        goal.setCurrentCarb(2001);
        assertEquals(1, goal.getCarbStatus());

        //Met goal
        goal.setCurrentCarb(2000);
        assertEquals(2, goal.getCarbStatus());
    }

    @Test
    public void testGetFatStatus() {
        goal.setFatGoal(2000);

        //Eat more to get to goal
        goal.setCurrentFat(1000);
        assertEquals(0, goal.getFatStatus());

        //Just under goal
        goal.setCurrentFat(1999);
        assertEquals(0, goal.getFatStatus());

        //Over the goal
        goal.setCurrentFat(2100);
        assertEquals(1, goal.getFatStatus());

        //Just over goal
        goal.setCurrentFat(2001);
        assertEquals(1, goal.getFatStatus());

        //Met goal
        goal.setCurrentFat(2000);
        assertEquals(2, goal.getFatStatus());
    }

}
















