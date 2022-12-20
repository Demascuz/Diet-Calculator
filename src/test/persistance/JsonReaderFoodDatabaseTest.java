package persistance;

import model.*;
import org.junit.jupiter.api.Test;
import persistence.JsonReaderFoodDatabase;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//CITE: tests based on the format of tests from JsonSerializationDemo-Master
public class JsonReaderFoodDatabaseTest extends JsonTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReaderFoodDatabase reader = new JsonReaderFoodDatabase("./data/noSuchFile.json");
        try {
            FoodDatabase fb = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyFileForFoodDatabase() {
        JsonReaderFoodDatabase reader = new JsonReaderFoodDatabase("./data/testReaderEmptyFoodDatabase.json");
        try {
            FoodDatabase fb = reader.read();
            assertEquals(0, fb.getFoodData().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralFileForFoodDatabase() {
        JsonReaderFoodDatabase reader = new JsonReaderFoodDatabase("./data/testReaderGeneralFoodDatabase.json");
        try {
            FoodDatabase fb = reader.read();
            ArrayList<Food> foods = fb.getFoodData();
            assertEquals(3, foods.size());
            checkFoodEquals(new Food("Apple", 100, 5, 70,5), foods.get(0));
            checkFoodEquals(new Food("Banana", 200, 15, 150,20), foods.get(1));
            checkFoodEquals(new Food("Noodle", 350, 50, 250,35), foods.get(2));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
