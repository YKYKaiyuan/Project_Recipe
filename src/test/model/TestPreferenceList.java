package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestPreferenceList {
    private PreferenceList preferenceList;
    private List<String> preferences = new ArrayList<>();


    @BeforeEach
    void runBefore() {
        preferenceList = new PreferenceList(preferences);
    }

    @Test
    void testGetPreference() {
        preferenceList.addPreference("No peanut");
        preferenceList.addPreference("No seafood");
        preferenceList.addPreference("Vegan");

        assertEquals("Vegan", preferenceList.getPreference(3));

    }

    @Test
    void testPreferenceSize() {
        preferenceList.addPreference("No peanut");
        preferenceList.addPreference("No seafood");
        preferenceList.addPreference("Vegan");

        assertEquals(3, preferenceList.getPreferenceSize());
    }
}