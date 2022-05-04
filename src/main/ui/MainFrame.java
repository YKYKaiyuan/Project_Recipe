package ui;

import exceptions.FalseLengthException;
import exceptions.FalseNumberException;
import model.Recipe;
import persistence.RecipeReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Run the recipe application.
public class MainFrame extends JFrame implements ActionListener {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 900;
    public static final int BOUND_X = 500;
    public static final int BOUND_Y = 50;
    public TextField name;
    public TextField rate;
    public TextField description;
    public TextField category;
    public TextField preference;
    public JLabel nameLabel;
    public JLabel rateLabel;
    public JLabel descriptionLabel;
    public JLabel categoryLabel;
    public JLabel preferenceLabel;
    public JLabel warningSign1;
    public JLabel warningSign2;
    public JLabel warningSign3;
    public Recipe recipe;
    public List<Recipe> recipeList = new ArrayList<>();
    public List<Recipe> savedRecipeList = new ArrayList<>();
    private final String addRecipe = "add recipe";
    private final String viewRecipes = "view recipe";
    private final String loadData = "load data";
    private final String saveData = "save data";
    private static final String RECIPES_FILE = "./data/recipes";
    private static final String ARROW_IMAGE_FILE = "./data/arrow.png";
    private JFrame gui;
    private JPanel buttonPanel;
    private JPanel inputPanel;
    private JPanel iconPanel;
    private List<String> recipeNames;
    private List<String> savedRecipeNames;

    public static void main(String[] args) {
//        new RecipeApp();
        new MainFrame();
    }

    public MainFrame() {
        super("Recipe");
        initializeWindow();
    }

    // MODIFIES: this
    // EFFECTS: setup the main frame of the app
    public void initializeWindow() {
        gui = new JFrame("Recipe App");
        buttonPanel = new JPanel();
        inputPanel = new JPanel();
        iconPanel = new JPanel();

        gui.setVisible(true);
        gui.setBounds(BOUND_X, BOUND_Y, WIDTH, HEIGHT);
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gui.setResizable(false);
        gui.setLayout(null);

        buttonPanel.setBounds(WIDTH - WIDTH / 5, (HEIGHT - HEIGHT / 10 * 9) / 2, WIDTH / 10 * 2, HEIGHT / 10 * 9);
        inputPanel.setBounds(WIDTH / 10 - WIDTH / 20, (HEIGHT - HEIGHT / 10 * 9) / 2,
                WIDTH - WIDTH / 3, HEIGHT / 10 * 9);
        iconPanel.setBounds(WIDTH - WIDTH / 10 * 3, (HEIGHT - HEIGHT / 10 * 9) / 2,
                WIDTH / 10, HEIGHT / 10 * 9);
        iconPanel.setBackground(Color.lightGray);
        inputPanel.setBackground(Color.lightGray);
        buttonPanel.setBackground(Color.lightGray);

        gui.add(iconPanel);
        gui.add(buttonPanel);
        gui.add(inputPanel);

        guiButtonLayout();
        guiInputLayout();
        guiIconLayout();
    }

    // MODIFIES: this
    // EFFECTS: a helper method arrange the layout for the input panel
    private void guiInputLayout() {
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        setComponentsInInputPanel();
        addComponentsInInputPanel();
        pack();
    }

    // MODIFIES: this
    // EFFECTS: a helper method arrange the layout for the icon panel
    private void guiIconLayout() {
        int imageWidth = WIDTH / 10;
        int imageHeight = HEIGHT / 10;
        ImageIcon originalArrow = new ImageIcon(ARROW_IMAGE_FILE);
        Image arrow = originalArrow.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        iconPanel.setLayout(null);
        warningSign1 = new JLabel(new ImageIcon(arrow));

        warningSign2 = new JLabel(new ImageIcon(arrow));

        warningSign3 = new JLabel(new ImageIcon(arrow));
        warningSign1.setBounds(0, HEIGHT / 20, imageWidth, imageHeight);
        warningSign2.setBounds(0, HEIGHT / 5 + HEIGHT / 22, imageWidth, imageHeight);
        warningSign3.setBounds(0, HEIGHT / 10 * 7 - HEIGHT / 10, imageWidth, imageHeight);
        iconPanel.add(warningSign1);
        iconPanel.add(warningSign2);
        iconPanel.add(warningSign3);
        pack();
        initializeArrow();
    }

    // MODIFIES: this
    // EFFECTS: a helper method place components into input panel
    private void setComponentsInInputPanel() {
        nameLabel = new JLabel("Please enter the name of the recipe.");
        nameLabel.setFont(new Font("Serif", Font.BOLD, 24));
        rateLabel = new JLabel("Give a rate to your recipe.");
        rateLabel.setFont(new Font("Serif", Font.BOLD, 24));
        descriptionLabel = new JLabel("Write some description of the recipe.");
        descriptionLabel.setFont(new Font("Serif", Font.BOLD, 24));
        categoryLabel = new JLabel("What is the category of the recipe?");
        categoryLabel.setFont(new Font("Serif", Font.BOLD, 24));
        preferenceLabel = new JLabel("Any preferences or allergies? If there are multiple preferences"
                + " use comma to separate them.");
        preferenceLabel.setFont(new Font("Serif", Font.BOLD, 24));
        name = new TextField();
        rate = new TextField();
        description = new TextField();
        category = new TextField();
        preference = new TextField();

    }

    // MODIFIES: this
    // EFFECTS: a helper method adds the text fields to input panel
    private void addComponentsInInputPanel() {
        inputPanel.add(nameLabel);
        inputPanel.add(name);
        inputPanel.add(rateLabel);
        inputPanel.add(rate);
        inputPanel.add(descriptionLabel);
        inputPanel.add(description);
        inputPanel.add(categoryLabel);
        inputPanel.add(category);
        inputPanel.add(preferenceLabel);
        inputPanel.add(preference);

    }

    // MODIFIES: this
    // EFFECTS: a helper method set the layout for button panel
    private void guiButtonLayout() {
        int boundX = 0;
        int standardBoundY = HEIGHT / 10;
        int width = WIDTH / 10 * 2;
        int height = HEIGHT / 10;

        buttonPanel.setLayout(null);

        JButton addRecipe = new JButton(this.addRecipe);
        JButton viewRecipe = new JButton(this.viewRecipes);
        JButton loadData = new JButton(this.loadData);
        JButton saveData = new JButton(this.saveData);

        addRecipe.setBounds(boundX, standardBoundY, width, height);
        viewRecipe.setBounds(boundX, standardBoundY * 2, width, height);
        loadData.setBounds(boundX, standardBoundY * 3, width, height);
        saveData.setBounds(boundX, standardBoundY * 4, width, height);

        buttonPanel.add(addRecipe);
        buttonPanel.add(viewRecipe);
        buttonPanel.add(loadData);
        buttonPanel.add(saveData);
        pack();
        addRecipe.addActionListener(this);
        viewRecipe.addActionListener(this);
        loadData.addActionListener(this);
        saveData.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: identify which user press and respond with different action
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case addRecipe:
                addRecipeFromInput();
                break;
            case viewRecipes:
                new FeedBackDialog(recipeList,savedRecipeList);
                break;
            case loadData:
                loadRecipe();
                System.out.println(recipeList);
                break;
            case saveData:
                saveRecipe();
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: Action that reads user input and generate a new recipe, then add it to the list. If user input does not
    // meet requirement for the text field, give feed back corresponding to the error to them.
    private void addRecipeFromInput() {
        recipe = new Recipe(0, "", "", "", null);
        initializeArrow();
        try {
            recipe.setName(name.getText());
            recipe.giveRate(Integer.parseInt(rate.getText()));
            recipe.setDescription(description.getText());
            recipe.setCategory(category.getText());
            String inputPreference = preference.getText();
            String[] array = inputPreference.split(",");
            recipe.setAllergyAndPreference(Arrays.asList(array));
            recipeList.add(recipe);
            new FeedBackDialog();
            clearTextFields();
        } catch (FalseLengthException falseLengthException) {
            initializeArrow();
            new WarningDialog(falseLengthException);
            checkWhichSign();
        } catch (FalseNumberException falseNumberException) {
            new WarningDialog(falseNumberException);
            ;
            rate.setText("");
            warningSign2.setVisible(true);
        }
    }

    // MODIFIES: this
    // EFFECTS: a helper method identify which warning sign should be activate.
    private void checkWhichSign() {
        if (name.getText().equals("") && category.getText().equals("")) {
            warningSign1.setVisible(true);
            warningSign3.setVisible(true);
        } else if (category.getText().equals("")) {
            warningSign3.setVisible(true);
        } else if (name.getText().equals("")) {
            warningSign1.setVisible(true);
        }
    }

    // MODIFIES: this
    // EFFECTS: a helper method clear all the text fields
    private void clearTextFields() {
        name.setText("");
        rate.setText("");
        description.setText("");
        category.setText("");
        preference.setText("");
    }

    // MODIFIES: this
    // EFFECTS: load the recipe list from saved data
    private void loadRecipe() {
        try {
            savedRecipeList = RecipeReader.readRecipes(new File(RECIPES_FILE));
            getRecipeNames(recipeList);
            getSavedNames(savedRecipeList);
            if (!recipeNames.containsAll(savedRecipeNames)) {
                recipeList.addAll(savedRecipeList);
            }
        } catch (IOException e) {
            //do nothing since recipe list is allowed to be empty
        }
    }

    // MODIFIES: this
    // EFFECTS: save the recipe list into the data file
    private void saveRecipe() {
        File savedRecipe = new File(RECIPES_FILE);
        savedRecipeNames = new ArrayList<>();
        try {
            savedRecipeList = RecipeReader.readRecipes(new File(RECIPES_FILE));
            getSavedNames(savedRecipeList);
            PrintWriter out = new PrintWriter(new FileWriter(savedRecipe, true));
            for (int i = 1; i <= recipeList.size(); i++) {
                if (!savedRecipeNames.contains(recipeList.get(i - 1).getName())) {
                    recipeList.get(i - 1).save(out);
                }
            }
            out.close();
            System.out.println("Recipes saved to file " + RECIPES_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // this is due to a programming error
    }

    // MODIFIES: this
    // EFFECTS: a helper method set all warning arrow to be invisible
    private void initializeArrow() {
        warningSign1.setVisible(false);
        warningSign2.setVisible(false);
        warningSign3.setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: a getter method get all names of recipe list
    private void getRecipeNames(List<Recipe> recipeList) {
        recipeNames = new ArrayList<>();
        readNames(recipeNames,recipeList);
    }

    // MODIFIES: this
    // EFFECTS: a getter method get all names of the recipe list in the data file
    private void getSavedNames(List<Recipe> recipeList) {
        savedRecipeNames = new ArrayList<>();
        readNames(savedRecipeNames,recipeList);
    }

    private void readNames(List<String> recipeNamesList, List<Recipe> givenRecipeList) {
        for (Recipe recipe : givenRecipeList) {
            recipeNamesList.add(recipe.getName());
        }
    }

}

