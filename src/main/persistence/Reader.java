package persistence;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Code reference from TellerApp
// A reader that can read data from a file
public abstract class Reader {
    public static final String DELIMITER = ",";

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    protected static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    protected static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }
}
