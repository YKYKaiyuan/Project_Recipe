package model;

import persistence.Reader;
import ui.Saveable;

import java.io.PrintWriter;
import java.util.List;


// Construct a list of categories.
public class CategoryList implements Saveable {
    private List<String> categoryList;

    //EFFECTS: Constructs a list of categories or cuisine.
    public CategoryList(List<String> categoryList) {
        this.categoryList = categoryList;
    }

    //EFFECTS: Get the name of category at the specific position.
    public String getCategoryName(int i) {
        return categoryList.get(i - 1);
    }

    //MODIFIES: this
    //EFFECTS: Add a new category to the list of categories.
    public void addCategory(String category) {
        categoryList.add(category);
    }

    //EFFECTS: return the size of the category list
    public int getCategorySize() {
        return categoryList.size();
    }


    @Override
    public void save(PrintWriter printWriter) {
        for (int i = 1; i <= categoryList.size(); i++) {
            printWriter.print(categoryList.get(i - 1));
            printWriter.print(Reader.DELIMITER);
        }
    }
}
