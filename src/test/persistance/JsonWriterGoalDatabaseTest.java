package persistance;

import model.*;
import org.junit.jupiter.api.Test;
import persistence.*;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//CITE: tests based on the format of tests from JsonSerializationDemo-Master
public class JsonWriterGoalDatabaseTest extends JsonTest{
    @Test
    public void testWriterInvalidFile() {
        try {
            GoalDatabase gd = new GoalDatabase();
            gd.addGoal(new Goal(100, 5, 70, 5));
            JsonWriterFoodDatabase writer = new JsonWriterFoodDatabase("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyGoalDatabase() {
        try {
            GoalDatabase gd = new GoalDatabase();
            JsonWriterGoalDatabase writer = new JsonWriterGoalDatabase("./data/testWriterEmptyGoalDatabase.json");
            writer.open();
            writer.write(gd);
            writer.close();

            JsonReaderGoalDatabase reader = new JsonReaderGoalDatabase("./data/testWriterEmptyGoalDatabase.json");
            gd = reader.read();
            assertEquals(0, gd.getGoalDatabase().size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralGoalDatabase() {
        try {
            GoalDatabase gd = new GoalDatabase();
            Goal goal1 = new Goal(100,100,100,100);
            Goal goal2 = new Goal(100,200,300,400);

            gd.addGoal(goal1);
            gd.addGoal(goal2);

            JsonWriterGoalDatabase writer = new JsonWriterGoalDatabase("./data/testWriterGeneralGoalDatabase.json");
            writer.open();
            writer.write(gd);
            writer.close();

            JsonReaderGoalDatabase reader = new JsonReaderGoalDatabase("./data/testWriterGeneralGoalDatabase.json");
            gd = reader.read();
            ArrayList<Goal> goals = gd.getGoalDatabase();
            assertEquals(2, goals.size());
            checkGoalEquals(goal1, goals.get(0));
            checkGoalEquals(goal2, goals.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
