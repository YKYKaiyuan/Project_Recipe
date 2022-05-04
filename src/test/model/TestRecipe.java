package model;

import exceptions.FalseLengthException;
import exceptions.FalseNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TestRecipe {
    private Recipe testRecipe;

    @BeforeEach
    void runBefore() {
        List<String> allergyAndPreference;
        allergyAndPreference = new ArrayList<>();
        testRecipe = new Recipe(1,"Mapuo Tofu","Delicious Spicy Tofu","Asian",
                allergyAndPreference);
    }

    @Test
    void testConstructor() {
        assertEquals(1,testRecipe.getRate());
        assertEquals("Mapuo Tofu", testRecipe.getName());
        assertEquals("Delicious Spicy Tofu", testRecipe.getDescription());
        assertEquals("Asian", testRecipe.getCategory());
    }

    @Test
    void testSetNameNoThrow() {
        try {
            testRecipe.setName("Sushi Nigiri");
        } catch (FalseLengthException e) {
            fail("False Length Exception should not be thrown");
        }
        assertEquals("Sushi Nigiri", testRecipe.getName());
    }

    @Test
    void testSetNameThrow() {
        try {
            testRecipe.setName("");
            fail("Exception should be thrown");
        } catch (FalseLengthException e) {
            // expected
        }
    }

    @Test
    void testSetDescription() {
        testRecipe.setDescription("");
        assertEquals("",testRecipe.getDescription());

        testRecipe.setDescription("Yammy Tofu");
        assertEquals("Yammy Tofu",testRecipe.getDescription());
    }

    @Test
    void testAddPreference() {
        List<String> preference;
        preference = new ArrayList<>();

        testRecipe.addAllergyAndPreference("Spicy");
        preference.add("Spicy");
        assertEquals(preference,testRecipe.getAllergyAndPreference());
    }

    @Test
    void testGiveRateThrowLowerBound() {
        try {
            testRecipe.giveRate(-1);
            fail("Exception should be thrown.");
        } catch (FalseNumberException e) {
            // expected
        }
    }

    @Test
    void testGiveRateThrowUpperBound() {
        try {
            testRecipe.giveRate(6);
            fail("Exception should be thrown.");
        } catch (FalseNumberException e) {
            //expected
        }
    }

    @Test
    void testGiveRateNoThrowLowerBound() {
        try {
            testRecipe.giveRate(0);
        } catch (FalseNumberException e) {
            fail("False Number Exception should not be thrown.");
        }
        assertEquals(0,testRecipe.getRate());
    }

    @Test
    void testGiveRateNoThrowUpperBound() {
        try {
            testRecipe.giveRate(5);
        } catch (FalseNumberException e) {
            fail("False Number Exception should not be thrown.");
        }
        assertEquals(5,testRecipe.getRate());
    }

    @Test
    void testSetCategoryNoThrow() {
        try {
            testRecipe.setCategory("Tofu");
        } catch (FalseLengthException e) {
            fail("False Length Exception should not be thrown");
        }
        assertEquals("Tofu", testRecipe.getCategory());
    }

    @Test
    void testSetCategoryThrow() {
        try {
            testRecipe.setCategory("");
            fail("Exception should be thrown");
        } catch (FalseLengthException e) {
            //expected
        }
    }
}
