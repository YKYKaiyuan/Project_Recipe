package persistence;

import model.PreferenceList;
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

public class PreferenceWriterTest {
    private static final String TEST_FILE = "./data/testPreference";

    private Writer testWriter;
    private List<String> testList;
    private List<String> expectedList;
    private PreferenceList testPreferenceList;
    private PreferenceList expectedPreferenceList;


    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));

        testList = new ArrayList<>();
        expectedList = new ArrayList<>();

        testList.add("no bean");
        testList.add("no fish");
        testList.add("Spicy");
        testList.add("no pork");

        expectedList.add("no bean");
        expectedList.add("no fish");
        expectedList.add("Spicy");
        expectedList.add("no pork");

        testPreferenceList = new PreferenceList(testList);
        expectedPreferenceList = new PreferenceList(expectedList);

    }

    @Test
    void testWritePreference() {
        // save  to file
        testWriter.write(testPreferenceList);
        testWriter.close();

        // now read them back in and verify
        try {
            List<PreferenceList> testPreferenceList = PreferenceListReader.readPreference(new File(TEST_FILE));
            PreferenceList categoryList = testPreferenceList.get(0);

            assertEquals(expectedPreferenceList.getPreferenceSize(),categoryList.getPreferenceSize());
            for (int i = 1; i <= categoryList.getPreferenceSize(); i++) {
                assertEquals(expectedPreferenceList.getPreference(i),categoryList.getPreference(i));
            }


        } catch (IOException e) {
            fail("Exceptions should not have been thrown");
        }
    }
}
