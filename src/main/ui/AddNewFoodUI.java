package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// Creates a new window where the user can add a new food
public class AddNewFoodUI {
    private static final int MAIN_WIDTH = 500;
    private static final int MAIN_HEIGHT = 300;

    private JFrame addNewFoodFrame;
    private JPanel componentPanel;

    private JTextField name = new JTextField();
    private JTextField calorie = new JTextField();
    private JTextField protein = new JTextField();
    private JTextField carb = new JTextField();
    private JTextField fat = new JTextField();

    private Food newFood;
    private DietCalculatorUI dc;

    public AddNewFoodUI(DietCalculatorUI dc) {
        this.dc = dc;

        addNewFoodFrame = new JFrame("Add new food");
        addNewFoodFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addNewFoodFrame.setLayout(new GridLayout(2, 1));

        componentPanel = new JPanel(new GridLayout(1, 2));

        addNewFoodLabels();
        addNewFoodTextBoxes();

        addNewFoodFrame.add(componentPanel);

        JButton button = new JButton(new AddFoodAction());
        addNewFoodFrame.add(button);

        addNewFoodFrame.setSize(MAIN_WIDTH, MAIN_HEIGHT);
        addNewFoodFrame.setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: makes new food panel's labels
    private void addNewFoodLabels() {
        JPanel labels = new JPanel(new GridLayout(5, 1));

        JLabel name = new JLabel("Enter food name:");
        JLabel calorie = new JLabel("Enter calorie:");
        JLabel protein = new JLabel("Enter protein:");
        JLabel carb = new JLabel("Enter calories:");
        JLabel fat = new JLabel("Enter fat:");

        labels.add(name);
        labels.add(calorie);
        labels.add(protein);
        labels.add(carb);
        labels.add(fat);

        componentPanel.add(labels);
    }

    // MODIFIES: this
    // EFFECTS: makes new food panel's textboxes
    private void addNewFoodTextBoxes() {
        JPanel textFields = new JPanel(new GridLayout(5, 1));

        textFields.add(name);
        textFields.add(calorie);
        textFields.add(protein);
        textFields.add(carb);
        textFields.add(fat);

        componentPanel.add(textFields);
    }

    // Sends the new food object to DietCalculatorUI
    private class AddFoodAction extends AbstractAction {
        AddFoodAction() {
            super("Done");
        }

        // EFFECTS: makes a new food object and send it to the DietCalculatorUI class
        @Override
        public void actionPerformed(ActionEvent evt) {
            newFood = new Food(name.getText(),
                    Integer.parseInt(calorie.getText()),
                    Integer.parseInt(protein.getText()),
                    Integer.parseInt(carb.getText()),
                    Integer.parseInt(fat.getText()));

            dc.addFoodToGoal(newFood);
            dc.addFoodToDatabase(newFood);
            addNewFoodFrame.dispose();
        }
    }
}
