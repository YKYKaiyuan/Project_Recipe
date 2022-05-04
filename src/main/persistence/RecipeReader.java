package persistence;

import model.Recipe;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// The reader that reads recipe file.
public class RecipeReader extends Reader {

    // EFFECTS: returns a list of recipes parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static List<Recipe> readRecipes(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns a list of recipes parsed from list of strings
    // where each string contains data for one account
    public static List<Recipe> parseContent(List<String> fileContent) {
        List<Recipe> recipes = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            recipes.add(parseRecipe(lineComponents));
        }

        return recipes;
    }

    // REQUIRES: component has all required arguments for a Recipe.
    // EFFECTS: returns recipe constructed from components
    protected static Recipe parseRecipe(List<String> components) {
        List<String> allergyAndPreference;
        allergyAndPreference = new ArrayList<>();

        int rate = Integer.parseInt(components.get(0));
        String name = components.get(1);
        String description = components.get(2);
        String category = components.get(3);
        for (int i = 4; i <= components.size() - 1; i++) {
            allergyAndPreference.add(components.get(i));
        }

        return new Recipe(rate, name, description, category, allergyAndPreference);
    }
}
