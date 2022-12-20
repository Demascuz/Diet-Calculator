package ui;

import model.Food;
import model.Goal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// Creates a new window where the user can modify the current goal
public class ChangeGoalUI {
    private static final int MAIN_WIDTH = 500;
    private static final int MAIN_HEIGHT = 300;

    private JFrame changeGoalFrame;
    private JPanel componentPanel;

    private JTextField calorie = new JTextField();
    private JTextField protein = new JTextField();
    private JTextField carb = new JTextField();
    private JTextField fat = new JTextField();

    private DietCalculatorUI dc;

    public ChangeGoalUI(DietCalculatorUI dc) {
        this.dc = dc;

        changeGoalFrame = new JFrame("Change current goal");
        changeGoalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        changeGoalFrame.setLayout(new GridLayout(2, 1));

        componentPanel = new JPanel(new GridLayout(1, 2));

        changeGoalLabels();
        changeGoalTextBoxes();

        changeGoalFrame.add(componentPanel);

        JButton button = new JButton(new ChangeGoalAction());
        changeGoalFrame.add(button);

        changeGoalFrame.setSize(MAIN_WIDTH, MAIN_HEIGHT);
        changeGoalFrame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: makes new change goal panel's labels
    private void changeGoalLabels() {
        JPanel labels = new JPanel(new GridLayout(5, 1));

        JLabel calorie = new JLabel("Enter calorie goal:");
        JLabel protein = new JLabel("Enter protein goal:");
        JLabel carb = new JLabel("Enter calories goal:");
        JLabel fat = new JLabel("Enter fat goal:");

        labels.add(calorie);
        labels.add(protein);
        labels.add(carb);
        labels.add(fat);

        componentPanel.add(labels);
    }

    // MODIFIES: this
    // EFFECTS: makes new change goal panel's textboxes
    private void changeGoalTextBoxes() {
        JPanel textFields = new JPanel(new GridLayout(4, 1));

        textFields.add(calorie);
        textFields.add(protein);
        textFields.add(carb);
        textFields.add(fat);

        componentPanel.add(textFields);
    }

    // Modifies the latest goal from goalDatabase from DietCalculatorUI
    private class ChangeGoalAction extends AbstractAction {
        ChangeGoalAction() {
            super("Done");
        }

        // EFFECTS: Modifies the latest goal from goalDatabase from DietCalculatorUI
        @Override
        public void actionPerformed(ActionEvent evt) {

            dc.modifyGoal(Integer.parseInt(calorie.getText()),
                    Integer.parseInt(protein.getText()),
                    Integer.parseInt(carb.getText()),
                    Integer.parseInt(fat.getText()));

            changeGoalFrame.dispose();
        }
    }
}
