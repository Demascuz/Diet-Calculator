package persistance;

import model.*;
import org.junit.jupiter.api.Test;
import persistence.JsonReaderGoalDatabase;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

//CITE: tests based on the format of tests from JsonSerializationDemo-Master
public class JsonReaderGoalDatabaseTest extends JsonTest{

    @Test
    public void testReaderNonExistentFile() {
        JsonReaderGoalDatabase reader = new JsonReaderGoalDatabase("./data/noSuchFile.json");
        try {
            GoalDatabase gd = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyFileForGoalDatabase() {
        JsonReaderGoalDatabase reader = new JsonReaderGoalDatabase("./data/testReaderEmptyGoalDatabase.json");
        try {
            GoalDatabase gd = reader.read();
            assertEquals(0, gd.getGoalDatabase().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralFileForGoalDatabase() {
        JsonReaderGoalDatabase reader = new JsonReaderGoalDatabase("./data/testReaderGeneralGoalDatabase.json");
        try {
            GoalDatabase gd = reader.read();
            Goal goal1 = gd.getGoalDatabase().get(0);
            Goal goal2 = gd.getGoalDatabase().get(1);

            assertEquals(100, goal1.getCalorieGoal());
            assertEquals(100, goal1.getProteinGoal());
            assertEquals(100, goal1.getCarbGoal());
            assertEquals(100, goal1.getFatGoal());

            assertEquals(100, goal2.getCalorieGoal());
            assertEquals(200, goal2.getProteinGoal());
            assertEquals(300, goal2.getCarbGoal());
            assertEquals(400, goal2.getFatGoal());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
