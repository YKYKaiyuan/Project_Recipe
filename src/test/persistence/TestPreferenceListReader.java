package persistence;

import model.PreferenceList;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestPreferenceListReader {
    //Code Reference from TellerApp.
    @Test
    void testPreferenceFile1() {
        try {
            List<String> testList = new ArrayList<>();
            List<PreferenceList> preferenceLists = PreferenceListReader.readPreference
                    (new File("./data/testPreferenceList1"));
            PreferenceList savedCategoryList = preferenceLists.get(0);


            testList.add("no bean");
            testList.add("vegan");
            testList.add("no seafood");

            PreferenceList testPreferenceList = new PreferenceList(testList);

            assertEquals(testPreferenceList.getPreferenceSize(),savedCategoryList.getPreferenceSize());
            for (int i = 1; i <= savedCategoryList.getPreferenceSize(); i++) {
                assertEquals(testPreferenceList.getPreference(i),savedCategoryList.getPreference(i));
            }


        } catch (IOException e) {
            fail("Exceptions should not have been thrown");
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
