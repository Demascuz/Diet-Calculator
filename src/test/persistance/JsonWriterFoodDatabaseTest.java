package persistance;

import model.*;
import org.junit.jupiter.api.Test;
import persistence.JsonReaderFoodDatabase;
import persistence.JsonWriterFoodDatabase;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//CITE: tests based on the format of tests from JsonSerializationDemo-Master
public class JsonWriterFoodDatabaseTest extends JsonTest{

    @Test
    public void testWriterInvalidFile() {
        try {
            FoodDatabase fb = new FoodDatabase();
            fb.addFood(new Food("Apple", 100, 5, 70, 5));
            JsonWriterFoodDatabase writer = new JsonWriterFoodDatabase("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyFoodDatabase() {
        try {
            FoodDatabase fb = new FoodDatabase();
            JsonWriterFoodDatabase writer = new JsonWriterFoodDatabase("./data/testWriterEmptyFoodDatabase.json");
            writer.open();
            writer.write(fb);
            writer.close();

            JsonReaderFoodDatabase reader = new JsonReaderFoodDatabase("./data/testWriterEmptyFoodDatabase.json");
            fb = reader.read();
            assertEquals(0, fb.getFoodData().size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralFoodDatabase() {
        try {
            FoodDatabase fb = new FoodDatabase();
            Food banana = new Food("Banana", 200, 15, 150, 20);
            Food apple = new Food("Apple", 100, 5, 70, 5);
            Food noodles = new Food("Noodle", 350, 50, 250, 35);

            fb.addFood(banana);
            fb.addFood(apple);
            fb.addFood(noodles);

            JsonWriterFoodDatabase writer = new JsonWriterFoodDatabase("./data/testWriterGeneralFoodDatabase.json");
            writer.open();
            writer.write(fb);
            writer.close();

            JsonReaderFoodDatabase reader = new JsonReaderFoodDatabase("./data/testWriterGeneralFoodDatabase.json");
            fb = reader.read();
            ArrayList<Food> foods = fb.getFoodData();
            assertEquals(3, foods.size());
            checkFoodEquals(banana, foods.get(0));
            checkFoodEquals(apple, foods.get(1));
            checkFoodEquals(noodles, foods.get(2));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}








