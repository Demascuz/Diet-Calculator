package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a database object for food, which contains all the foods in foodData.
public class FoodDatabase implements Writable {
    private ArrayList<Food> foodData;

    /*
    * MODIFIES: this
    * EFFECTS: creates a FoodDatabase object with an empty foodData array.
    * */
    public FoodDatabase() {
        this.foodData = new ArrayList<>();
    }

    public ArrayList<Food> getFoodData() {
        return this.foodData;
    }

    /*
     * EFFECTS: returns the food object from foodData at the given index.
     * */
    public Food getFood(int index) {
        return this.foodData.get(index);
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds the given food to the foodData array.
     * */
    public void addFood(Food food) {
        this.foodData.add(food);
        EventLog.getInstance().logEvent(new Event("Added " + food.getName() + " to food database."));
    }

    // CITE: toJson is based on toJson from JsonSerializationDemo-Master
    // EFFECTS:  returns JSONObject of this food database object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Food data", foodsToJson());
        return json;
    }

    // CITE: foodsToJson is based on thingiesToJson from JsonSerializationDemo-Master
    // EFFECTS: returns foods in this food database as a JSON array
    private JSONArray foodsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Food food : foodData) {
            jsonArray.put(food.toJson());
        }

        return jsonArray;
    }
}
