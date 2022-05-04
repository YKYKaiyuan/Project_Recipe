package ui;

import exceptions.FalseLengthException;
import exceptions.FalseNumberException;
import model.CategoryList;
import model.PreferenceList;
import model.Recipe;
import persistence.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// Recipe application.
public class RecipeConsoleInterface {
    private static final String RECIPES_FILE = "./data/recipes";
    private static final String PREFERENCES_FILE = "./data/preferenceList";
    private static final String CATEGORIES_FILE = "./data/categoryList";
    private Scanner userInput;
    private List<Recipe> recipeList;
    private CategoryList categoryList;
    private PreferenceList preferenceList;
    private List<String> preferences;
    private Recipe newRecipe;
    private int randomRecipe;
    private List<Recipe> savedRecipeList;


    // Code reference from TellApp.
    // EFFECTS: runs the teller application
    public RecipeConsoleInterface() {
        runApp();
    }

    // Code reference from TellApp.
    // MODIFIES: this
    // EFFECTS: loads Recipes from RECIPES_FILE, if that
    // MODIFIES: this
    // EFFECTS: loads Category list from CATEGORY_LIST_FILE, if that

    // MODIFIES: this
    // EFFECTS: loads Preference list from PREFERENCE_LIST_FILE, if that

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runApp() {
        boolean keepGoing = true;
        String command = null;
        userInput = new Scanner(System.in);

        loadRecipe();
        loadCategoryList();
        loadPreferenceList();
        randomRecommendRecipe();

        while (keepGoing) {
            displayMenu();
            command = userInput.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                checkBeforeQuit();
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // Code reference from TellApp.
    // MODIFIES: this
    // EFFECTS: loads recipes from PREFERENCE_FILE, default value already assigned to the file.
    private void loadPreferenceList() {
        try {
            List<PreferenceList> preferences = new ArrayList<>();
            preferences = PreferenceListReader.readPreference(new File(PREFERENCES_FILE));
            preferenceList = preferences.get(0);
        } catch (IOException e) {
            //PreferenceList have default values.
        }
    }

    // Code reference from TellApp.
    // MODIFIES: this
    // EFFECTS: loads recipes from CATEGORIES_FILE, default value already assigned to the file.
    private void loadCategoryList() {
        try {
            List<CategoryList> categories = new ArrayList<>();
            categories = CategoryListReader.readCategory(new File(CATEGORIES_FILE));
            categoryList = categories.get(0);
        } catch (IOException e) {
            //CategoryList have default values.
        }
    }

    // Code reference from TellApp.
    // MODIFIES: this
    // EFFECTS: loads recipes from RECIPES_FILE.
    private void loadRecipe() {
        try {
            recipeList = RecipeReader.readRecipes(new File(RECIPES_FILE));
        } catch (IOException e) {
            //do nothing since recipe list is allowed to be empty
        }
    }

    // Code reference from TellApp.
    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tadd -> add recipes");
        System.out.println("\tview -> view existed recipes");
        System.out.println("\trecommend -> view recommend recipes");
        System.out.println("\tcategory -> search recipes in a specific category");
        System.out.println("\tpreference -> search recipes with a specific preference");
        System.out.println("\tsave -> save all changes");
        System.out.println("\tq -> quit");
    }

    // Code reference from TellApp.
    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("add")) {
            addRecipes();
        } else if (command.equals("view")) {
            viewRecipes();
        } else if (command.equals("recommend")) {
            viewRecommendRecipe();
        } else if (command.equals("category")) {
            searchRecipesByCategory();
        } else if (command.equals("preference")) {
            searchRecipesByPreference();
        } else if (command.equals("save")) {
            saveChanges();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // Code reference from TellApp.
    // MODIFIES: this
    // EFFECTS: Let user to create a new recipe by entering required contents.
    private void addRecipes() {
        preferences = new ArrayList<>();
        newRecipe = new Recipe(0, "", "", "", preferences);
        userInput = new Scanner(System.in);

        addName();
        addCategory();
        addPreference();
        addDescription();
        addRate();

        recipeList.add(newRecipe);
        System.out.println("New recipe is added!");
    }

    // Code reference from TellApp.
    // MODIFIES: this
    // EFFECTS: user input of recipe name.
    private void addName() {
        System.out.print("Enter name of the recipe: \n");
        try {
            String name = userInput.nextLine();
            newRecipe.setName(name);
        } catch (FalseLengthException e) {
            System.out.println("Please enter a valid name.");
            addName();
        }
    }

    // Code reference from TellApp.
    // MODIFIES: this
    // EFFECTS: user input of recipe rate.
    private void addRate() {
        System.out.print("give a rate of the recipe: \n");
        try {
            String rate = userInput.nextLine();
            int n = Integer.parseInt(rate);
            newRecipe.giveRate(n);
        } catch (FalseNumberException e) {
            System.out.println("Please give a valid rate.");
            addRate();
        }
    }

    // Code reference from TellApp.
    // MODIFIES: this
    // EFFECTS: user input of recipe description.
    private void addDescription() {
        System.out.print("Enter description of the recipe: \n");
        String description = userInput.nextLine();
        newRecipe.setDescription(description);
    }

    // Code reference from TellApp.
    // MODIFIES: this
    // EFFECTS: user input of recipe category.
    private void addCategory() {
        System.out.print("Enter category of the recipe: \n");
        try {
            String category = userInput.nextLine();
            newRecipe.setCategory(category);
        } catch (FalseLengthException e) {
            System.out.println("Please enter a valid category.");
            addCategory();
        }
    }

    // Code reference from TellApp.
    // MODIFIES: this
    // EFFECTS: user input of recipe preference.
    private void addPreference() {
        boolean keepGoing = true;
        while (keepGoing) {
            System.out.println("What preference do you want to add? Press q if you don't need to enter "
                    + "any more preferences");
            String response = userInput.nextLine();

            if (response.equals("q")) {
                keepGoing = false;
            } else {
                newRecipe.addAllergyAndPreference(response);
            }
        }
    }

    // Code reference from TellApp.
    // EFFECTS: Print out the name of recipes.
    private void viewRecipes() {
        for (int i = 1; i <= recipeList.size(); i++) {
            System.out.println("Name:" + recipeList.get(i - 1).getName()
                    + "   Rate: " + recipeList.get(i - 1).getRate());
        }
        viewDetail();
    }


    // EFFECTS: Ask user to enter the name of the recipe they want to check.
    private void viewDetail() {
        boolean keepGoing = true;
        userInput = new Scanner(System.in);
        while (keepGoing) {
            System.out.println("Which recipe do you want to check? Enter b if you want to go back to main menu.");
            String response = userInput.nextLine();

            if (response.equals("b")) {
                keepGoing = false;
            } else {
                for (int i = 1; i <= recipeList.size(); i++) {
                    if (recipeList.get(i - 1).getName().equals(response)) {
                        printDetail(i);
                    }
                }
            }
        }
    }

    // EFFECTS: print the detail of the recipe
    private void printDetail(int i) {
        String rate = Integer.toString(recipeList.get(i - 1).getRate());
        System.out.println("Rate: " + rate);
        System.out.println("Name: " + recipeList.get(i - 1).getName());
        System.out.println("Description: " + recipeList.get(i - 1).getDescription());
        System.out.println("Category: " + recipeList.get(i - 1).getCategory());
        System.out.print("Preferences: ");
        for (int n = 1; n <= recipeList.get(i - 1).getAllergyAndPreference().size(); n++) {
            if (n == recipeList.get(i - 1).getAllergyAndPreference().size()) {
                System.out.println(recipeList.get(i - 1).getAllergyAndPreference().get(n - 1));
            } else {
                System.out.print(recipeList.get(i - 1).getAllergyAndPreference().get(n - 1));
                System.out.print(", ");
            }
        }
    }

    // Code reference from TellApp.
    // MODIFIES: this
    // EFFECTS: Let user to search a recipe with a specific category.
    private void searchRecipesByCategory() {
        System.out.println("Please choose the category you want to search: ");
        for (int i = 1; i <= categoryList.getCategorySize(); i++) {
            System.out.println(categoryList.getCategoryName(i));
        }

        String searchedCategory = userInput.next();

        System.out.println("Here are the recipes in given category");
        for (int i = 1; i <= recipeList.size(); i++) {
            if (recipeList.get(i - 1).getCategory().equals(searchedCategory)) {
                System.out.print("Rate:" + recipeList.get(i - 1).getRate());
                System.out.print(", Name:");
                System.out.print(recipeList.get(i - 1).getName());
                System.out.print(", Description:");
                System.out.print(recipeList.get(i - 1).getDescription());
                System.out.print(", Category:");
                System.out.print(recipeList.get(i - 1).getCategory());
                System.out.print(", Preferences:");
                System.out.println(recipeList.get(i - 1).getAllergyAndPreference());
            }
        }
    }

    // Code reference from TellApp.
    // MODIFIES: this
    // EFFECTS: Let user to search a recipe with a specific preference.
    private void searchRecipesByPreference() {
        System.out.println("Please choose the Preference you want to search: ");
        for (int i = 1; i <= preferenceList.getPreferenceSize(); i++) {
            System.out.println(preferenceList.getPreference(i));
        }

        String searchedPreference = userInput.next();

        System.out.println("Here are the recipes in given category");
        for (int i = 1; i <= recipeList.size(); i++) {
            if (recipeList.get(i - 1).getAllergyAndPreference().contains(searchedPreference)) {
                System.out.print("Rate:" + recipeList.get(i - 1).getRate());
                System.out.print(", Name:");
                System.out.print(recipeList.get(i - 1).getName());
                System.out.print(", Description:");
                System.out.print(recipeList.get(i - 1).getDescription());
                System.out.print(", Category:");
                System.out.print(recipeList.get(i - 1).getCategory());
                System.out.print(", Preferences:");
                System.out.println(recipeList.get(i - 1).getAllergyAndPreference());
            }
        }
    }

    // Code reference from TellApp.
    // MODIFIES: this
    // EFFECTS: save all changes user made.
    private void saveChanges() {
        saveRecipe();
        saveCategories();
        savePreferences();
    }

    // Code reference from TellApp.
    // MODIFIES: this
    // EFFECTS: save all changes user made in recipe.
    private void saveRecipe() {
        try {
            Writer writer = new Writer(new File(RECIPES_FILE));
            for (int i = 1; i <= recipeList.size(); i++) {
                writer.write(recipeList.get(i - 1));
            }
            writer.close();
            System.out.println("Recipes saved to file " + RECIPES_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save recipes to " + RECIPES_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    // Code reference from TellApp.
    // MODIFIES: this
    // EFFECTS: save all changes user made in category list.
    private void saveCategories() {
        try {
            Writer writer = new Writer(new File(CATEGORIES_FILE));
            writer.write(categoryList);
            writer.close();
            System.out.println("categories saved to file " + CATEGORIES_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save categories to " + CATEGORIES_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    // Code reference from TellApp.
    // MODIFIES: this
    // EFFECTS: save all changes user made in preferences.
    private void savePreferences() {
        try {
            Writer writer = new Writer(new File(PREFERENCES_FILE));
            writer.write(preferenceList);
            writer.close();
            System.out.println("Preferences saved to file " + PREFERENCES_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save preference to " + PREFERENCES_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    // EFFECTS: Randomly choose a recipe which at least have a rate of 3 and print it out.
    private void randomRecommendRecipe() {
        if (recipeList.size() > 0) {
            randomRecipe = (int) (Math.random() * recipeList.size());
            while (recipeList.get(randomRecipe).getRate() <= 3) {
                randomRecipe = (int) (Math.random() * recipeList.size());
            }
            System.out.println("A random lucky recipe we recommend for you is: \n"
                    + recipeList.get(randomRecipe).getName()
                    + " which you rated for " + recipeList.get(randomRecipe).getRate() + " stars");
        }
    }

    // EFFECTS: Print the detail for the recommend recipe.
    private void viewRecommendRecipe() {
        printDetail(randomRecipe + 1);
    }

    // Code reference from TellApp.
    // MODIFIES: this
    // EFFECTS: loads recipes from RECIPES_FILE which haven't got modified.
    private void loadSavedRecipe() {
        try {
            savedRecipeList = RecipeReader.readRecipes(new File(RECIPES_FILE));
        } catch (IOException e) {
            //do nothing since recipe list is allowed to be empty
        }
    }

    // MODIFIES: this
    // EFFECTS: Ask user if they want to save changes before quit if there were changes made by user,
    // otherwise quit right away.
    private void checkBeforeQuit() {
        loadSavedRecipe();
        if (!compareCurrentAndSavedData()) {
            System.out.println("Do you want to save before exit? "
                    + "enter save to save changes you made so far, q to exit");
            String response = userInput.next();
            checkIfWantToSave(response);
        }
    }

    // MODIFIES: this
    // EFFECTS: if user response is save then save the changes user made. If the response is q then quit.
    private void checkIfWantToSave(String string) {
        if (string.equals("save")) {
            saveChanges();
        } else if (!string.equals("q")) {
            System.out.println("Please enter a valid response!");
            System.out.println("enter save to save changes you made so far, q to exit.");
            checkIfWantToSave(string);
        }
    }

    // EFFECTS: Return true if current data is same as the data saved in file.
    private boolean compareCurrentAndSavedData() {
        if (recipeList.size() == savedRecipeList.size()) {
            for (int i = 0; i + 1 <= recipeList.size(); i++) {
                if (recipeList.get(i).getName().equals(savedRecipeList.get(i).getName())) {
                    return true;
                }
            }
        }
        return false;
    }


}
