package persistence;

import model.PreferenceList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// The reader that reads preference list file.
public class PreferenceListReader extends Reader {
    // EFFECTS: returns a list of accounts parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static List<PreferenceList> readPreference(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns a list of accounts parsed from list of strings
    // where each string contains data for one account
    public static List<PreferenceList> parseContent(List<String> fileContent) {
        List<PreferenceList> preferenceLists = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            preferenceLists.add(parseRecipe(lineComponents));
        }
        return preferenceLists;
    }

    // REQUIRES: A non empty list of preference list.
    // EFFECTS: returns a list of preference list constructed from components
    protected static PreferenceList parseRecipe(ArrayList<String> components) {
        List<String> preferenceList = new ArrayList<>();
        for (int i = 1; i <= components.size(); i++) {
            preferenceList.add(components.get(i - 1));
        }
        return new PreferenceList(preferenceList);
    }
}
