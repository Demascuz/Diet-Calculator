package persistance;

import model.Food;
import model.Goal;

import static org.junit.jupiter.api.Assertions.assertEquals;

//CITE: JsonTest based on the format of JsonTest from JsonSerializationDemo-Master
public class JsonTest {

    protected static void checkFoodEquals (Food precedent, Food antecedent) {
            assertEquals(precedent.getName(), antecedent.getName());
            assertEquals(precedent.getCalorie(), antecedent.getCalorie());
            assertEquals(precedent.getProtein(), antecedent.getProtein());
            assertEquals(precedent.getCarb(), antecedent.getCarb());
            assertEquals(precedent.getFat(), antecedent.getFat());
    }

    protected void checkGoalEquals(Goal precedent, Goal antecedent) {
        assertEquals(precedent.getCalorieGoal(), antecedent.getCalorieGoal());
        assertEquals(precedent.getProteinGoal(), antecedent.getProteinGoal());
        assertEquals(precedent.getCarbGoal(), antecedent.getCarbGoal());
        assertEquals(precedent.getFatGoal(), antecedent.getFatGoal());

        assertEquals(precedent.getCurrentCalorie(), antecedent.getCurrentCalorie());
        assertEquals(precedent.getCurrentProtein(), antecedent.getCurrentProtein());
        assertEquals(precedent.getCurrentCarb(), antecedent.getCurrentCarb());
        assertEquals(precedent.getCurrentFat(), antecedent.getCurrentFat());
    }



}
