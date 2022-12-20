package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a dietary goal having specific goals for calories, protein, carbohydrate and fats.
// Also tracks the user's calorie, protein, carbohydrate and fat intake.
public class Goal implements Writable {
    private int calorieGoal;
    private int proteinGoal;
    private int carbGoal;
    private int fatGoal;

    private int currentCalorie;
    private int currentProtein;
    private int currentCarb;
    private int currentFat;

    /*
     * REQUIRES: calorieGoal, proteinGoal, carbGoal or fatGoal >= 0
     * MODIFIES: this
     * EFFECTS: creates a goal with the given calorieGoal, proteinGoal, carbGoal or fatGoal.
     *          Does not change currentCalorie, currentProtein, currentCarb or currentFat.
     */
    public Goal(int calorieGoal, int proteinGoal, int carbGoal, int fatGoal) {
        this.calorieGoal = calorieGoal;
        this.proteinGoal = proteinGoal;
        this.carbGoal = carbGoal;
        this.fatGoal = fatGoal;
    }

    public int getCalorieGoal() {
        return calorieGoal;
    }

    public int getProteinGoal() {
        return proteinGoal;
    }

    public int getCarbGoal() {
        return carbGoal;
    }

    public int getFatGoal() {
        return fatGoal;
    }

    public int getCurrentCalorie() {
        return currentCalorie;
    }

    public int getCurrentProtein() {
        return currentProtein;
    }

    public int getCurrentCarb() {
        return currentCarb;
    }

    public int getCurrentFat() {
        return currentFat;
    }

    /*
     * REQUIRES: amount > 0
     * MODIFIES: this
     * EFFECTS: adds amount to currentCalorie
     * */
    public void addCalorie(int amount) {
        this.currentCalorie += amount;
    }

    /*
     * REQUIRES: amount > 0
     * MODIFIES: this
     * EFFECTS: adds amount to currentProtein
     * */
    public void addProtein(int amount) {
        this.currentProtein += amount;
    }

    /*
     * REQUIRES: amount > 0
     * MODIFIES: this
     * EFFECTS: adds amount to currentCarb
     * */
    public void addCarb(int amount) {
        this.currentCarb += amount;
    }

    /*
     * REQUIRES: amount > 0
     * MODIFIES: this
     * EFFECTS: adds amount to currentFat
     * */
    public void addFat(int amount) {
        this.currentFat += amount;
    }

    /*
     * REQUIRES: amount > 0
     * MODIFIES: this
     * EFFECTS: changes calorieGoal to amount
     * */
    public void setCalorieGoal(int amount) {
        this.calorieGoal = amount;
    }

    /*
     * REQUIRES: amount > 0
     * MODIFIES: this
     * EFFECTS: changes proteinGoal to amount
     * */
    public void setProteinGoal(int amount) {
        this.proteinGoal = amount;
    }

    /*
     * REQUIRES: amount > 0
     * MODIFIES: this
     * EFFECTS: changes carbGoal to amount
     * */
    public void setCarbGoal(int amount) {
        this.carbGoal = amount;
    }

    /*
     * REQUIRES: amount > 0
     * MODIFIES: this
     * EFFECTS: changes fatGoal to amount
     * */
    public void setFatGoal(int amount) {
        this.fatGoal = amount;
    }

    /*
     * REQUIRES: amount > 0
     * MODIFIES: this
     * EFFECTS: changes setCurrentCalorie to amount
     * */
    public void setCurrentCalorie(int amount) {
        this.currentCalorie = amount;
    }

    /*
     * REQUIRES: amount > 0
     * MODIFIES: this
     * EFFECTS: changes setCurrentProtein to amount
     * */
    public void setCurrentProtein(int amount) {
        this.currentProtein = amount;
    }

    /*
     * REQUIRES: amount > 0
     * MODIFIES: this
     * EFFECTS: changes setCurrentCarb to amount
     * */
    public void setCurrentCarb(int amount) {
        this.currentCarb = amount;
    }

    /*
     * REQUIRES: amount > 0
     * MODIFIES: this
     * EFFECTS: changes setCurrentFat to amount
     * */
    public void setCurrentFat(int amount) {
        this.currentFat = amount;
    }

    /*
     * EFFECTS: returns the difference of subtracting currentCalorie from calorieGoal.
     * */
    public int calculateCalorieDiff() {
        return (this.calorieGoal - this.currentCalorie);
    }

    /*
     * EFFECTS: returns the difference of subtracting currentProtein from proteinGoal.
     * */
    public int calculateProteinDiff() {
        return (this.proteinGoal - this.currentProtein);
    }

    /*
     * EFFECTS: returns the difference of subtracting currentCarb from carbGoal.
     * */
    public int calculateCarbDiff() {
        return (this.carbGoal - this.currentCarb);
    }

    /*
     * EFFECTS: returns the difference of subtracting currentFat from fatGoal.
     * */
    public int calculateFatDiff() {
        return (this.fatGoal - this.currentFat);
    }

    /*
     * MODIFIES: this
     * EFFECTS: compares calorieGoal to the currentCalorieGoal and returns:
     *          0 if user can still eat more calories
     *          1 if user is over the calorie goal limit
     *          2 if the user has met the calorie goal
     * */
    public int getCalorieStatus() {
        int calorieStatus;
        if (this.calculateCalorieDiff() > 0) {
            calorieStatus = 0;
        } else if (this.calculateCalorieDiff() < 0) {
            calorieStatus = 1;
        } else {
            calorieStatus = 2;
        }
        return calorieStatus;
    }

    /*
     * MODIFIES: this
     * EFFECTS: compares proteinGoal to the currentProteinGoal and returns:
     *          0 if user can still eat more protein
     *          1 if user is over the protein goal limit
     *          2 if the user has met the protein goal
     * */
    public int getProteinStatus() {
        int proteinStatus;
        if (this.calculateProteinDiff() > 0) {
            proteinStatus = 0;
        } else if (this.calculateProteinDiff() < 0) {
            proteinStatus = 1;
        } else {
            proteinStatus = 2;
        }
        return proteinStatus;
    }

    /*
     * MODIFIES: this
     * EFFECTS: compares carbGoal to the currentCarbGoal and returns:
     *          0 if user can still eat more carb
     *          1 if user is over the carb goal limit
     *          2 if the user has met the carb goal
     * */
    public int getCarbStatus() {
        int carbStatus;
        if (this.calculateCarbDiff() > 0) {
            carbStatus = 0;
        } else if (this.calculateCarbDiff() < 0) {
            carbStatus = 1;
        } else {
            carbStatus = 2;
        }
        return carbStatus;
    }

    /*
     * MODIFIES: this
     * EFFECTS: compares fatGoal to the currentFatGoal and returns:
     *          0 if user can still eat more fat
     *          1 if user is over the calorie fat limit
     *          2 if the user has met the fat goal
     * */
    public int getFatStatus() {
        int fatStatus;
        if (this.calculateFatDiff() > 0) {
            fatStatus = 0;
        } else if (this.calculateFatDiff() < 0) {
            fatStatus = 1;
        } else {
            fatStatus = 2;
        }
        return fatStatus;
    }

    // CITE: toJson is based on toJson from JsonSerializationDemo-Master
    // EFFECTS: returns a JSONObject of this goal object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Calorie goal", calorieGoal);
        json.put("Protein goal", proteinGoal);
        json.put("Carb goal", carbGoal);
        json.put("Fat goal", fatGoal);

        json.put("Calorie eaten", currentCalorie);
        json.put("Protein eaten", currentProtein);
        json.put("Carb eaten", currentCarb);
        json.put("Fat eaten", currentFat);

        return json;
    }
}































