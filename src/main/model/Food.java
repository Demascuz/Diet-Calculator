package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a food having a name and its nutrition facts calories, protein, carbohydrate and fat.
public class Food implements Writable {
    private String name;
    private int calorie;
    private int protein;
    private int carb;
    private int fat;

    // REQUIRES: name has a non-zero length,
    //           calorie, protein, carb or fat cannot have values < 0.
    // MODIFIES: this
    // EFFECTS: creates a food object with a name and nutrition values for calories, proteins, carbs and fats
    public Food(String name, int calorie, int protein, int carb, int fat) {
        this.name = name;
        this.calorie = calorie;
        this.protein = protein;
        this.carb = carb;
        this.fat = fat;
    }

    public String getName() {
        return name;
    }

    public int getCalorie() {
        return calorie;
    }

    public int getProtein() {
        return protein;
    }

    public int getCarb() {
        return carb;
    }

    public int getFat() {
        return fat;
    }

    // CITE: toJson is based on toJson from JsonSerializationDemo-Master
    // EFFECTS: returns a JSONObject of this food object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", name);
        json.put("Calorie", calorie);
        json.put("Protein", protein);
        json.put("Carb", carb);
        json.put("Fat", fat);
        return json;
    }
}
