package ui;

import model.Recipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Dialog for feedback.
public class FeedBackDialog extends Dialog implements ActionListener {
    private List<Recipe> recipeList;
    private List<Recipe> savedList;
    private Container container;

    // EFFECTS: Constructs a feed back dialog which takes a recipe list, and shows the lists on the frame with a delete
    // button between each of those.
    public FeedBackDialog(List<Recipe> recipeList,List<Recipe> savedList) {
        int rowNumber = 0;

        this.setResizable(true);
        this.setBounds(200,100,1000,100);

        this.recipeList = recipeList;
        this.savedList = savedList;
        container = this.getContentPane();
        container.setLayout(new GridLayout(rowNumber,2));

        for (int i = 0; i <= recipeList.size() - 1; i++) {
            rowNumber += 1;
            container.setLayout(new GridLayout(rowNumber,2));
            JButton delete = new JButton("Delete Item No." + (i + 1));
            container.add(new Label(recipeListToString(i)));
            container.add(delete);
            delete.addActionListener(this);
        }
    }

    // EFFECTS: construct a feed back dialog tells user they successfully added a recipe.
    public FeedBackDialog() {
        JLabel label = new JLabel("    Added recipe successfully!");

        this.add(label);
    }

    // EFFECTS: A helper method turns all recipes in the recipe list into a list of string.
    private String recipeListToString(int i) {
        String rate = Integer.toString(recipeList.get(i).getRate());
        return "Rate: " + rate
                + " Name: " + recipeList.get(i).getName()
                + " Description: " + recipeList.get(i).getDescription()
                + " Category: " + recipeList.get(i).getCategory()
                + " Preferences: " + recipeList.get(i).getAllergyAndPreference();
    }

    // MODIFIES: this
    // EFFECTS: when user click the delete button, then delete the recipe on the same row.
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i <= recipeList.size() - 1; i++) {
            if (e.getActionCommand().equals("Delete Item No." + (i + 1)) && contains(recipeList.get(i),savedList)) {
                new WarningDialog();
            } else if (e.getActionCommand().equals("Delete Item No." + (i + 1))
                    && !contains(recipeList.get(i),savedList)) {
                recipeList.remove(i);
            }
        }
        setVisible(false);
        new FeedBackDialog(recipeList,savedList);
    }

    // MODIFIES: this
    // EFFECTS: A helper check if the recipe is in another list.
    private boolean contains(Recipe recipe, List<Recipe> savedRecipeList) {
        for (int i = 0; i <= savedRecipeList.size() - 1; i++) {
            if (recipe.getName().equals(savedRecipeList.get(i).getName())) {
                return true;
            }
        }
        return false;
    }
}
