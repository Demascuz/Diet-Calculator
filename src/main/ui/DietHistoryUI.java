package ui;

import model.Goal;
import model.GoalDatabase;

import javax.swing.*;
import java.awt.*;

// Creates a new window displaying the past 10 days worth of diet data
public class DietHistoryUI {
    private static final int MAIN_WIDTH = 1300;
    private static final int MAIN_HEIGHT = 300;
    private static final int MAX_BLOCKS = 10;

    private JFrame historyFrame;
    private GoalDatabase gdb;

    private DietCalculatorUI dc;

    public DietHistoryUI(GoalDatabase gdb) {
        this.gdb = gdb;

        historyFrame = new JFrame("Diet History");
        historyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        historyFrame.setLayout(new GridLayout(1, gdb.getGoalDatabase().size() + 1));

        JPanel leftPane = new JPanel(new GridLayout(13, 1));
        infoBlock(leftPane);
        historyFrame.add(leftPane);

        int numOfBlocks;

        if (gdb.getGoalDatabase().size() <= MAX_BLOCKS) {
            numOfBlocks = gdb.getGoalDatabase().size();
        } else {
            numOfBlocks = MAX_BLOCKS;
        }

        int minVal = gdb.getGoalDatabase().size() - numOfBlocks;

        for (int i = gdb.getGoalDatabase().size() - 1; i >= minVal; i--) {
            JPanel block = new JPanel(new GridLayout(13, 1));
            makeBlock(block, i);
            historyFrame.add(block);
        }

        historyFrame.setSize(MAIN_WIDTH, MAIN_HEIGHT);
        historyFrame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Makes the info block panel
    private void infoBlock(JPanel panel) {
        panel.add(new JLabel("\t\t\t\t\t\t\t\t\t Info / Day"));
        panel.add(new JLabel("Calories:"));
        panel.add(new JLabel("\t\t\t Goal:"));
        panel.add(new JLabel("\t\t\t Eaten"));
        panel.add(new JLabel("Protein:"));
        panel.add(new JLabel("\t\t\t Goal:"));
        panel.add(new JLabel("\t\t\t Eaten"));
        panel.add(new JLabel("Carb:"));
        panel.add(new JLabel("\t\t\t Goal:"));
        panel.add(new JLabel("\t\t\t Eaten"));
        panel.add(new JLabel("Fat:"));
        panel.add(new JLabel("\t\t\t Goal:"));
        panel.add(new JLabel("\t\t\t Eaten"));
    }

    // MODIFIES: this
    // EFFECTS: Makes a new block with each day's goal info
    private void makeBlock(JPanel panel, int index) {
        Goal goal = gdb.getGoalDatabase().get(index);

        panel.add(new JLabel(Integer.toString(index + 1)));

        panel.add(new JLabel(""));
        panel.add(new JLabel(Integer.toString(goal.getCalorieGoal())));
        panel.add(new JLabel(Integer.toString(goal.getCurrentCalorie())));

        panel.add(new JLabel(""));
        panel.add(new JLabel(Integer.toString(goal.getProteinGoal())));
        panel.add(new JLabel(Integer.toString(goal.getCurrentProtein())));

        panel.add(new JLabel(""));
        panel.add(new JLabel(Integer.toString(goal.getCarbGoal())));
        panel.add(new JLabel(Integer.toString(goal.getCurrentCarb())));

        panel.add(new JLabel(""));
        panel.add(new JLabel(Integer.toString(goal.getFatGoal())));
        panel.add(new JLabel(Integer.toString(goal.getCurrentFat())));
    }
}
