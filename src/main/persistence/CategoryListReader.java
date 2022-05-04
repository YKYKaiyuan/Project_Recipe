package persistence;

import model.CategoryList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// The reader reads category list file.
public class CategoryListReader extends Reader {

    // EFFECTS: returns a list of category list parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static List<CategoryList> readCategory(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns a list of category list parsed from list of strings
    // where each string contains data for one account
    public static List<CategoryList> parseContent(List<String> fileContent) {
        List<CategoryList> categoryList = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            categoryList.add(parseRecipe(lineComponents));
        }
        return categoryList;
    }

    // REQUIRES: A non empty list of category list.
    // EFFECTS: returns a list of category list constructed from components
    protected static CategoryList parseRecipe(ArrayList<String> components) {
        List<String> categoryList = new ArrayList<>();
        for (int i = 1; i <= components.size(); i++) {
            categoryList.add(components.get(i - 1));
        }
        return new CategoryList(categoryList);
    }

}
