package persistence;

import model.Food;
import model.FoodDatabase;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Loads the FoodDatabase object from JSon format
// CITE: JsonReaderFoodDatabase based on the format of JsonReader from JsonSerializationDemo-Master
public class JsonReaderFoodDatabase {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderFoodDatabase(String source) {
        this.source = source;
    }

    // EFFECTS: reads food database from file and returns it;
    // throws IOException if an error occurs reading data from file
    public FoodDatabase read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFoodDatabase(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses food database from JSON object and returns it
    private FoodDatabase parseFoodDatabase(JSONObject jsonObject) {
        FoodDatabase fd = new FoodDatabase();
        addFoods(fd, jsonObject);
        return fd;
    }

    // MODIFIES: fd
    // EFFECTS: parses foods from JSON object and adds them to food database
    private void addFoods(FoodDatabase fd, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Food data");

        for (Object json : jsonArray) {
            JSONObject nextFood = (JSONObject) json;
            addFood(fd, nextFood);
        }
    }

    // MODIFIES: fd
    // EFFECTS: parses food from JSON object and adds it to workroom
    private void addFood(FoodDatabase fd, JSONObject jsonObject) {
        String name = jsonObject.getString("Name");
        int calorie = jsonObject.getInt("Calorie");
        int protein = jsonObject.getInt("Protein");
        int carb = jsonObject.getInt("Carb");
        int fat = jsonObject.getInt("Fat");

        Food food = new Food(name, calorie, protein, carb, fat);

        fd.addFood(food);
    }
}









