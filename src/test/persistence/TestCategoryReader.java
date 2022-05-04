package persistence;

import model.CategoryList;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestCategoryReader {
    //Code Reference from TellerApp.
    @Test
    void testCategoryFile1() {
        try {
            CategoryList testCategoryList;
            List<String> testList = new ArrayList<>();
            List<CategoryList> categoryLists = CategoryListReader.readCategory
                    (new File("./data/testCategoryList1"));
            CategoryList savedCategoryList = categoryLists.get(0);


            testList.add("Asian");
            testList.add("Pizza");
            testList.add("Wings");
            testList.add("American");
            testList.add("European");
            testList.add("African");
            testCategoryList = new CategoryList(testList);

            assertEquals(testCategoryList.getCategorySize(),savedCategoryList.getCategorySize());
            for (int i = 1; i <= savedCategoryList.getCategorySize(); i++) {
                assertEquals(testCategoryList.getCategoryName(i),savedCategoryList.getCategoryName(i));
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
