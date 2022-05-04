package persistence;

import model.Recipe;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestRecipeReader {
    //Code Reference from TellerApp.
    @Test
    void testRecipeFile1() {
        try {
            List<String> preference;
            preference = new ArrayList<>();

            List<Recipe> recipeList = RecipeReader.readRecipes(new File("./data/testRecipeFile1"));
            Recipe firstRecipe = recipeList.get(0);
            assertEquals(5, firstRecipe.getRate());
            assertEquals("Mapuo Tofu", firstRecipe.getName());
            assertEquals("Delicious Tofu", firstRecipe.getDescription());
            assertEquals("Asian",firstRecipe.getCategory());

            preference.add("Spicy");
            preference.add("Contain Pork");
            preference.add("Soybean");
            preference.add("side dish");

            assertEquals(preference,firstRecipe.getAllergyAndPreference());

            preference = new ArrayList<>();
            Recipe secondRecipe = recipeList.get(1);
            assertEquals(4, secondRecipe.getRate());
            assertEquals("Peking Duck", secondRecipe.getName());
            assertEquals("Delicious roast duck", secondRecipe.getDescription());
            assertEquals("Asian-Chinese", secondRecipe.getCategory());

            preference.add("main dish");

            assertEquals(preference,secondRecipe.getAllergyAndPreference());



        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    // Code copied from TellerApp.
    @Test
    void testIOException() {
        try {
            RecipeReader.readRecipes(new File("./path/does/not/exist/testAccount.txt"));
        } catch (IOException e) {
            // expected
        }
    }
}
