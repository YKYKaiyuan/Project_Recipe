package model;

import exceptions.FalseLengthException;
import exceptions.FalseNumberException;
import persistence.Reader;
import ui.Saveable;

import java.io.PrintWriter;
import java.util.List;


// Construct a recipe object which contains its rate, name, description, category and preferences or allergies.
public class Recipe implements Saveable {
    private int rate;
    private String name;
    private String description;
    private String category;
    private List<String> allergyAndPreference;

    // EFFECTS: Construct a recipe object with added date, name, description, category, allergy and preference.
    // The recipe can must have a name and a category.
    public Recipe(int rate, String name, String description, String category, List<String> allergyAndPreference) {
        this.rate = rate;
        this.name = name;
        this.description = description;
        this.category = category;
        this.allergyAndPreference = allergyAndPreference;
    }

    // EFFECTS: Return added Date.
    public int getRate() {
        return rate;
    }

    // EFFECTS: Return name.
    public String getName() {
        return name;
    }

    // EFFECTS: Return description.
    public String getDescription() {
        return description;
    }

    // EFFECTS: Return category.
    public String getCategory() {
        return category;
    }

    // EFFECTS: Return allergy and preference.
    public List<String> getAllergyAndPreference() {
        return allergyAndPreference;
    }

    //MODIFIES: this
    //EFFECTS: Set the name of the recipe.
    public void setName(String name) throws FalseLengthException {
        if (name.length() == 0) {
            throw new FalseLengthException();
        }
        this.name = name;
    }

    //MODIFIES: this
    //EFFECTS: Set the rate of the recipe.
    public void giveRate(int rate) throws FalseNumberException {
        if (rate < 0 || rate > 5) {
            throw new FalseNumberException();
        }
        this.rate = rate;
    }

    //MODIFIES: this
    //EFFECTS: Set the description of the recipe.
    public void setDescription(String description) {
        this.description = description;
    }

    //MODIFIES: this
    //EFFECTS: Set the category of the recipe.
    public void setCategory(String category) throws FalseLengthException {
        if (category.length() == 0) {
            throw new FalseLengthException();
        }
        this.category = category;
    }

    //MODIFIES: this
    //EFFECTS: Add allergy of preference to the AllergyAndPreference list.
    public void addAllergyAndPreference(String allergyAndPreference) {
        this.allergyAndPreference.add(allergyAndPreference);
    }

    public void setAllergyAndPreference(List<String> allergyAndPreference) {
        this.allergyAndPreference = allergyAndPreference;
    }

    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(rate);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(name);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(description);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(category);
        printWriter.print(Reader.DELIMITER);
        for (int i = 1; i <= allergyAndPreference.size(); i++) {
            if (i == allergyAndPreference.size()) {
                printWriter.println(allergyAndPreference.get(i - 1));
            } else {
                printWriter.print(allergyAndPreference.get(i - 1));
                printWriter.print(Reader.DELIMITER);
            }
        }
    }
}
