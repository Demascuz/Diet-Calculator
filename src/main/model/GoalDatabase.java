package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a database containing goals and the food eaten for each day.
public class GoalDatabase implements Writable {
    private ArrayList<Goal> goalDatabase;
    private Goal latestGoal;

    /*
    * MODIFIES: this
    * EFFECTS: creates a GoalDatabase object with an empty goalDatabase array.
    * */
    public GoalDatabase() {
        this.goalDatabase = new ArrayList<>();
    }

    public ArrayList<Goal> getGoalDatabase() {
        return goalDatabase;
    }

    /*
    * EFFECTS: returns the goal object in goalDatabase at the element index.
    * */
    public Goal getGoal(int index) {
        return this.goalDatabase.get(index);
    }

    /*
     * EFFECTS: returns the last goal object in goalDatabase.
     * */
    public Goal getLatestGoal() {
        return latestGoal;
    }

    /*
    * MODIFIES: this
    * EFFECTS: adds the goal object to goalDatabase.
    * */
    public void addGoal(Goal goal) {
        this.goalDatabase.add(goal);
        this.latestGoal = this.goalDatabase.get(this.goalDatabase.size() - 1);

        EventLog.getInstance().logEvent(new Event("Initiated the first goal in goal database."));
    }

    /*
     * MODIFIES: this
     * EFFECTS: changes goalDatabase's last goal's calorie, protein, carb and fat values to the given
     *          calorie, protein, carb and fat.
     * */
    public void modifyLatestGoal(int calorie, int protein, int carb, int fat) {
        this.latestGoal.setCalorieGoal(calorie);
        this.latestGoal.setProteinGoal(protein);
        this.latestGoal.setCarbGoal(carb);
        this.latestGoal.setFatGoal(fat);

        EventLog.getInstance().logEvent(new Event("Modified the latest goal."));
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds the food's nutritional values to the latest goal.
     * */
    public void addFoodToGoal(Food food) {
        this.latestGoal.addCalorie(food.getCalorie());
        this.latestGoal.addProtein(food.getProtein());
        this.latestGoal.addCarb(food.getCarb());
        this.latestGoal.addFat(food.getFat());

        EventLog.getInstance().logEvent(new Event("Added " + food.getName()
                + "'s nutrients to the latest goal."));
    }

    /*
     * MODIFIES: this
     * EFFECTS: Creates new goal object with latestGoal's goal values and adds it to the goalDatabase for a new day.
     *          Sets latestGoal equal to the new goal object.
     * */
    public void newDay() {
        int prevGoalCalorie = this.latestGoal.getCalorieGoal();
        int prevGoalProtein = this.latestGoal.getProteinGoal();
        int prevGoalCarb = this.latestGoal.getCarbGoal();
        int prevGoalFat = this.latestGoal.getFatGoal();

        this.latestGoal.getCalorieStatus();
        this.latestGoal.getProteinStatus();
        this.latestGoal.getCarbStatus();
        this.latestGoal.getFatStatus();

        Goal newGoal = new Goal(prevGoalCalorie, prevGoalProtein, prevGoalCarb, prevGoalFat);
        this.goalDatabase.add(newGoal);

        this.latestGoal = newGoal;
        EventLog.getInstance().logEvent(new Event("New day has arrived."));
    }

    // CITE: toJson is based on toJson from JsonSerializationDemo-Master
    // EFFECTS:  returns JSONObject of this goal database object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Goal data", goalsToJson());
        return json;
    }

    // CITE: goalsToJson is based on thingiesToJson from JsonSerializationDemo-Master
    // EFFECTS: returns goals in this goal database as a JSON array
    private JSONArray goalsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Goal goal : goalDatabase) {
            jsonArray.put(goal.toJson());
        }

        return jsonArray;
    }


}