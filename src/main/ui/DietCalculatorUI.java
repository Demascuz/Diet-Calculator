package ui;

import model.*;
import model.Event;
import persistence.JsonReaderFoodDatabase;
import persistence.JsonReaderGoalDatabase;
import persistence.JsonWriterFoodDatabase;
import persistence.JsonWriterGoalDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// Diet Calculator application
public class DietCalculatorUI {
    private static final int MAIN_WIDTH = 700;
    private static final int MAIN_HEIGHT = 450;
    private static final int BAR_WIDTH = 50;
    private static final int BAR_HEIGHT = 200;

    private static final String JSON_STORE_FD = "./data/dietAppFD.json";
    private static final String JSON_STORE_GD = "./data/dietAppGD.json";

    protected FoodDatabase foodDatabase;
    protected GoalDatabase goalDatabase;

    private JsonWriterFoodDatabase jsonWriterFD;
    private JsonReaderFoodDatabase jsonReaderFD;
    private JsonWriterGoalDatabase jsonWriterGD;
    private JsonReaderGoalDatabase jsonReaderGD;

    private JFrame desktop;
    private JPanel controlPanel;
    private JPanel graphPanel;
    private JPanel goalPanel;
    private JComboBox<String> dropdown;
    private JPanel dropDownPanel;

    private RectDraw calorieBar;
    private RectDraw proteinBar;
    private RectDraw carbBar;
    private RectDraw fatBar;

    public DietCalculatorUI() {
        init();

        desktop = new JFrame("Diet Calculator");

        desktop.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        desktop.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                for (Event next: EventLog.getInstance()) {
                    System.out.println(next.toString());
                }
                EventLog.getInstance().clear();
                System.exit(0);
            }
        });

        desktop.setLayout(new GridLayout(1, 2));

        controlPanel = new JPanel(new GridLayout(7, 1));
        makeControlPanel();
        desktop.add(controlPanel);

        goalPanel = new JPanel(new GridLayout(2, 1));
        makeGoalPanel();
        desktop.add(goalPanel);

        desktop.setSize(MAIN_WIDTH, MAIN_HEIGHT);
        desktop.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Initializes the foodDatabase and adds generic foods' apple, banana and noodles
    //          Initializes the goalDatabase and adds genericGoal
    private void init() {
        foodDatabase = new FoodDatabase();
        goalDatabase = new GoalDatabase();

        Food apple = new Food("Apple", 100, 5, 70, 5);
        Food banana = new Food("Banana", 200, 15, 150, 20);
        Food noodles = new Food("Noodle", 350, 50, 250, 35);
        foodDatabase.addFood(apple);
        foodDatabase.addFood(banana);
        foodDatabase.addFood(noodles);

        Goal genericGoal = new Goal(2000, 150, 300, 100);
        goalDatabase.addGoal(genericGoal);

        jsonWriterFD = new JsonWriterFoodDatabase(JSON_STORE_FD);
        jsonReaderFD = new JsonReaderFoodDatabase(JSON_STORE_FD);
        jsonWriterGD = new JsonWriterGoalDatabase(JSON_STORE_GD);
        jsonReaderGD = new JsonReaderGoalDatabase(JSON_STORE_GD);
    }

    // MODIFIES: this
    // EFFECTS: Makes the main frame control panels with a foodDatabase dropdown,
    //                                                     add new food button,
    //                                                     change goal button,
    //                                                     view diet history button,
    //                                                     save data button,
    //                                                     load data button
    private void makeControlPanel() {
        controlPanel.setBackground(Color.ORANGE);

        dropDownPanel = new JPanel(new GridLayout(1, 2));
        makeDropDownFoodPanel();
        controlPanel.add(dropDownPanel);

        JButton addNewFoodButton = new JButton(new AddNewFoodAction());
        JButton changeGoalButton = new JButton(new ChangeGoalAction());
        JButton viewDietHistoryButton = new JButton(new ViewHistoryAction());
        JButton saveDataButton = new JButton(new SaveAction());
        JButton loadDataButton = new JButton(new LoadAction());

        JButton newDayButton = new JButton(new NewDayAction());

        controlPanel.add(addNewFoodButton);
        controlPanel.add(changeGoalButton);
        controlPanel.add(viewDietHistoryButton);
        controlPanel.add(saveDataButton);
        controlPanel.add(loadDataButton);


        controlPanel.add(newDayButton);
    }

    // MODIFIES: this
    // EFFECTS: Makes the foodDatabase dropdown component
    private void makeDropDownFoodPanel() {
        dropDownPanel.setBackground(Color.ORANGE);
        JButton selectFoodButton = new JButton(new SelectFoodAction());
        ArrayList<String> foodNames = new ArrayList<>();

        for (Food food : foodDatabase.getFoodData()) {
            foodNames.add(food.getName());
        }

        dropdown = new JComboBox<>(foodNames.toArray(new String[0]));
        dropDownPanel.add(dropdown);
        dropDownPanel.add(selectFoodButton);
    }

    // MODIFIES: this
    // EFFECTS: Makes the main goal panel
    private void makeGoalPanel() {
        graphPanel = new JPanel(new GridLayout(1, 7));
        graphPanel.setBackground(Color.ORANGE);
        makeGraphPanel();

        JPanel labelGraphPanel = new JPanel(new GridLayout(1, 7));
        labelGraphPanel.setBackground(Color.ORANGE);
        makeLabelGraphPanel(labelGraphPanel);

        goalPanel.add(graphPanel);
        goalPanel.add(labelGraphPanel);
    }

    // MODIFIES: this
    // EFFECTS: Makes the main goal panel's graph's panel
    private void makeGraphPanel() {
        Goal latestGoal = goalDatabase.getLatestGoal();

        calorieBar = updateBar(latestGoal.getCalorieStatus(), latestGoal.getCalorieGoal(),
                latestGoal.getCurrentCalorie());
        graphPanel.add(calorieBar);

        RectDraw gap1 = new RectDraw();
        graphPanel.add(gap1);

        proteinBar = updateBar(latestGoal.getProteinStatus(), latestGoal.getProteinGoal(),
                latestGoal.getCurrentProtein());
        graphPanel.add(proteinBar);

        RectDraw gap2 = new RectDraw();
        graphPanel.add(gap2);

        carbBar = updateBar(latestGoal.getCarbStatus(), latestGoal.getCarbGoal(),
                latestGoal.getCurrentCarb());
        graphPanel.add(carbBar);

        RectDraw gap3 = new RectDraw();
        graphPanel.add(gap3);

        fatBar = updateBar(latestGoal.getFatStatus(), latestGoal.getFatGoal(),
                latestGoal.getCurrentFat());
        graphPanel.add(fatBar);
    }

    // MODIFIES: this
    // EFFECTS: Makes the main goal panel's graph's labels
    private void makeLabelGraphPanel(JPanel panel) {
        JLabel calorie = new JLabel("Calorie");
        JLabel gap1 = new JLabel("");

        JLabel protein = new JLabel("Protein");
        JLabel gap2 = new JLabel("");

        JLabel carb = new JLabel("Carb");
        JLabel gap3 = new JLabel("");

        JLabel fat = new JLabel("Fat");

        panel.add(calorie);
        panel.add(gap1);
        panel.add(protein);
        panel.add(gap2);
        panel.add(carb);
        panel.add(gap3);
        panel.add(fat);
    }

    // EFFECTS: Makes a rectangle for given nutrient goal
    private RectDraw updateBar(int status, int goal, int current) {
        RectDraw bar = new RectDraw();

        int percentageFill = calculatePercentageFill(goal, current);

        if (status == 0) {
            bar.setDimension(percentageFill);
            bar.setColour(Color.CYAN);
        } else if (status == 1) {
            bar.setDimension(BAR_HEIGHT);
            bar.setColour(Color.RED);
        } else {
            bar.setDimension(BAR_HEIGHT);
            bar.setColour(Color.GREEN);
        }

        return bar;
    }

    // EFFECTS: calculates the percentage of colour fill for rectangle
    private int calculatePercentageFill(int goal, int current) {
        return (int) (((double) current / (double) goal) * (double) BAR_HEIGHT);
    }

    // Makes a rectangle object
    private static class RectDraw extends JPanel {
        int percentageFill;
        Color color;

        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            g.drawRect(0, 0, BAR_WIDTH, BAR_HEIGHT);
            g.setColor(color);
            g.fillRect(0, (BAR_HEIGHT - percentageFill), BAR_WIDTH, percentageFill);
        }

        // MODIFIES: this
        // EFFECTS: sets the rectangle's fill height
        public void setDimension(int percentageFill) {
            this.percentageFill = percentageFill;
        }

        // MODIFIES: this
        // EFFECTS: sets the rectangle's colour
        public void setColour(Color color) {
            this.color = color;
        }
    }

    // Selects food from the dropdown and adds it to the goalDatabase
    private class SelectFoodAction extends AbstractAction {

        SelectFoodAction() {
            super("Add food");
        }

        // EFFECTS: adds the selected food to foodDatabase
        @Override
        public void actionPerformed(ActionEvent evt) {
            addFoodToGoal(foodDatabase.getFood(dropdown.getSelectedIndex()));
        }
    }

    // Calls AddNewFoodUI
    private class AddNewFoodAction extends AbstractAction {

        AddNewFoodAction() {
            super("Add new food");
        }

        // EFFECTS: calls callAddNewFood
        @Override
        public void actionPerformed(ActionEvent evt) {
            callAddNewFood();
        }
    }

    // Calls ChangeGoalUI and modifies the latestGoal in goalDatabase
    private class ChangeGoalAction extends AbstractAction {

        ChangeGoalAction() {
            super("Change goal");
        }

        // EFFECTS: calls callChangeGoal
        @Override
        public void actionPerformed(ActionEvent evt) {
            callChangeGoal();
        }
    }

    // Calls DietHistoryUI
    private class ViewHistoryAction extends AbstractAction {

        ViewHistoryAction() {
            super("View diet history");
        }

        // EFFECTS: calls DietHistoryUI
        @Override
        public void actionPerformed(ActionEvent evt) {
            new DietHistoryUI(goalDatabase);
        }
    }

    // Saves the foodDatabase and goalDatabase
    private class SaveAction extends AbstractAction {

        SaveAction() {
            super("Save data");
        }

        // MODIFIES: this
        // EFFECTS: saves the food and goal database to file
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                jsonWriterFD.open();
                jsonWriterFD.write(foodDatabase);
                jsonWriterFD.close();

                jsonWriterGD.open();
                jsonWriterGD.write(goalDatabase);
                jsonWriterGD.close();

            } catch (FileNotFoundException e) {
                System.out.println("Unable to write to file");
            }
        }
    }

    // Loads the foodDatabase and goalDatabase
    private class LoadAction extends AbstractAction {

        LoadAction() {
            super("Load data");
        }

        // MODIFIES: this
        // EFFECTS: loads the food and goal database to file
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                foodDatabase = jsonReaderFD.read();
                goalDatabase = jsonReaderGD.read();
                updateGraphPanel();
                updateDropDownFoodPanel();

            } catch (IOException e) {
                System.out.println("Unable to read from file");
            }
        }
    }

    // Simulates when a new day has begun, and sets new blank goal
    private class NewDayAction extends AbstractAction {

        NewDayAction() {
            super("New day");
        }

        // MODIFIES: this
        // EFFECTS: makes a new latestgoal in goalDatabase and updates the graph panel
        @Override
        public void actionPerformed(ActionEvent evt) {
            goalDatabase.newDay();
            updateGraphPanel();
        }
    }

    // EFFECT: calls the AddNewFoodUI class
    private void callAddNewFood() {
        new AddNewFoodUI(this);
    }

    // EFFECT: calls the ChangeGoalUI class
    private void callChangeGoal() {
        new ChangeGoalUI(this);
    }

    // MODIFIES: this
    // EFFECTS: adds given food to goal and updates the graph panel
    public void addFoodToGoal(Food food) {
        goalDatabase.addFoodToGoal(food);
        updateGraphPanel();
    }

    // MODIFIES: this
    // EFFECTS: adds the given food to the foodDatabase and updates the graph panel
    public void addFoodToDatabase(Food food) {
        foodDatabase.addFood(food);
        updateDropDownFoodPanel();
    }

    // MODIFIES: this
    // EFFECTS: changes the latest goal and updates the graph panel
    public void modifyGoal(int newCalorie, int newProtein, int newCarb, int newFat) {
        goalDatabase.modifyLatestGoal(newCalorie, newProtein, newCarb, newFat);
        updateGraphPanel();
    }

    // MODIFIES: this
    // EFFECTS: removes all components from graph panel, and revalidates and repaints the graph panel so that it updates
    private void updateGraphPanel() {
        graphPanel.removeAll();
        makeGraphPanel();
        graphPanel.revalidate();
        graphPanel.repaint();
    }

    // MODIFIES: this
    // EFFECTS: removes all components from dropdown panel, and revalidates and repaints the dropdown panel
    //          so that it updates
    private void updateDropDownFoodPanel() {
        dropDownPanel.removeAll();
        makeDropDownFoodPanel();
        dropDownPanel.validate();
        dropDownPanel.repaint();
    }

    // EFFECTS: starts the application
    public static void main(String[] args) {
        new DietCalculatorUI();
    }
}