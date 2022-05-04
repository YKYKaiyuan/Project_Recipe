package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestCategoryList {
    private CategoryList categoryList;
    private List<String> categories = new ArrayList<>();


    @BeforeEach
    void runBefore() {
        categoryList = new CategoryList(categories);
    }

    @Test
    void testGetCategory() {
        categoryList.addCategory("BBQ");
        categoryList.addCategory("Asian");
        categoryList.addCategory("Fast food");

        assertEquals("Fast food", categoryList.getCategoryName(3));

    }

    @Test
    void testGetCategorySize() {
        categoryList.addCategory("BBQ");
        categoryList.addCategory("Asian");
        categoryList.addCategory("Fast food");

        assertEquals(3,categoryList.getCategorySize());
    }
}