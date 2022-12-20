package persistence;

import model.Goal;
import model.GoalDatabase;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Loads the GoalDatabase object from JSon format
// CITE: JsonReaderGoalDatabase based on the format of JsonReader from JsonSerializationDemo-Master
public class JsonReaderGoalDatabase {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderGoalDatabase(String source) {
        this.source = source;
    }

    // EFFECTS: reads goal database from file and returns it;
    // throws IOException if an error occurs reading data from file
    public GoalDatabase read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGoalDatabase(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses goal database from JSON object and returns it
    private GoalDatabase parseGoalDatabase(JSONObject jsonObject) {
        GoalDatabase gd = new GoalDatabase();

        addGoals(gd, jsonObject);
        return gd;
    }

    // MODIFIES: gd
    // EFFECTS: parses goals from JSON object and adds them to goal database
    private void addGoals(GoalDatabase gd, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Goal data");

        for (Object json : jsonArray) {
            JSONObject nextGoal = (JSONObject) json;
            addGoal(gd, nextGoal);
        }
    }

    // MODIFIES: gd
    // EFFECTS: parses goal from JSON object and adds it to goal database
    private void addGoal(GoalDatabase gd, JSONObject jsonObject) {
        int calorieGoal = jsonObject.getInt("Calorie goal");
        int proteinGoal = jsonObject.getInt("Protein goal");
        int carbGoal = jsonObject.getInt("Carb goal");
        int fatGoal = jsonObject.getInt("Fat goal");

        int currentCalorie = jsonObject.getInt("Calorie eaten");
        int currentProtein = jsonObject.getInt("Protein eaten");
        int currentCarb = jsonObject.getInt("Carb eaten");
        int currentFat = jsonObject.getInt("Fat eaten");

        Goal goal = new Goal(calorieGoal, proteinGoal, carbGoal, fatGoal);

        goal.setCurrentCalorie(currentCalorie);
        goal.setCurrentProtein(currentProtein);
        goal.setCurrentCarb(currentCarb);
        goal.setCurrentFat(currentFat);

        gd.addGoal(goal);
    }
}






















