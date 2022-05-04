package model;

import persistence.Reader;
import ui.Saveable;

import java.io.PrintWriter;
import java.util.List;


// Construct a list of preferences or allergies.
public class PreferenceList implements Saveable {
    private List<String> preferenceList;

    //EFFECTS: Constructs a list of preference/allergy.
    public PreferenceList(List<String> preferenceList) {
        this.preferenceList = preferenceList;
    }

    //EFFECTS: Get the name of preference/allergy at the specific position.
    public String getPreference(int i) {
        return preferenceList.get(i - 1);
    }

    //MODIFIES: this
    //EFFECTS: Add a new preference to the list of preferences.
    public void addPreference(String preference) {
        preferenceList.add(preference);
    }

    //EFFECTS: return the size of the preference list
    public int getPreferenceSize() {
        return preferenceList.size();
    }


    @Override
    public void save(PrintWriter printWriter) {
        for (int i = 1; i <= preferenceList.size(); i++) {
            printWriter.print(preferenceList.get(i - 1));
            printWriter.print(Reader.DELIMITER);
        }
    }
}
