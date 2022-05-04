package persistence;

import model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//Code reference from TellerApp
public class RecipeWriterTest {
    private static final String TEST_FILE = "./data/testRecipe";
    private Writer testWriter;
    private Recipe tofu;
    private Recipe duck;
    private List<String> tofuPreference;
    private List<String> duckPreference;


    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));

        tofuPreference = new ArrayList<>();
        tofuPreference.add("vegan");
        tofuPreference.add("side dish");

        duckPreference = new ArrayList<>();
        duckPreference.add("meat");
        duckPreference.add("no pork");

        tofu = new Recipe(5, "Tofu","Good Tofu dish.","Asian",tofuPreference);
        duck = new Recipe(4, "Duck","Good duck dish.","French",duckPreference);
    }

    @Test
    void testWriteRecipes() {
        // save date file
        testWriter.write(tofu);
        testWriter.write(duck);
        testWriter.close();

        // now read them back in and verify
        try {
            List<Recipe> accounts = RecipeReader.readRecipes(new File(TEST_FILE));
            Recipe tofu = accounts.get(0);
            assertEquals(5, tofu.getRate());
            assertEquals("Tofu", tofu.getName());
            assertEquals("Good Tofu dish.", tofu.getDescription());
            assertEquals("Asian",tofu.getCategory());
            assertEquals(tofuPreference,tofu.getAllergyAndPreference());

            Recipe duck = accounts.get(1);
            assertEquals(4, duck.getRate());
            assertEquals("Duck", duck.getName());
            assertEquals("Good duck dish.", duck.getDescription());
            assertEquals("French",duck.getCategory());
            assertEquals(duckPreference,duck.getAllergyAndPreference());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
