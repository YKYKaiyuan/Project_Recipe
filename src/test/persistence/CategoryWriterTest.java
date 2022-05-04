package persistence;

import model.CategoryList;
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

public class CategoryWriterTest {
    private static final String TEST_FILE = "./data/testCategory";

    private Writer testWriter;
    private List<String> testList;
    private List<String> expectedList;
    private CategoryList testCategoryList;
    private CategoryList expectedCategoryList;


    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));

        testList = new ArrayList<>();
        expectedList = new ArrayList<>();

        testList.add("Asian");
        testList.add("French");
        testList.add("Italian");
        testList.add("American");

        expectedList.add("Asian");
        expectedList.add("French");
        expectedList.add("Italian");
        expectedList.add("American");

        testCategoryList = new CategoryList(testList);
        expectedCategoryList = new CategoryList(expectedList);

    }

    @Test
    void testWriteCategories() {
        // save date
        testWriter.write(testCategoryList);
        testWriter.close();

        // now read them back in and verify
        try {
            List<CategoryList> testCategoryList = CategoryListReader.readCategory(new File(TEST_FILE));
            CategoryList categoryList = testCategoryList.get(0);

            assertEquals(expectedCategoryList.getCategorySize(),categoryList.getCategorySize());
            for (int i = 1; i <= categoryList.getCategorySize(); i++) {
                assertEquals(expectedCategoryList.getCategoryName(i),categoryList.getCategoryName(i));
            }


        } catch (IOException e) {
            fail("Exceptions should not have been thrown");
        }
    }
}
