package ui;

import exceptions.FalseLengthException;
import exceptions.FalseNumberException;

import javax.swing.*;
import java.awt.*;

public class WarningDialog extends Dialog {

    // EFFECTS: Constructs a warning dialog shows what went wrong
    public WarningDialog(FalseLengthException e) {
        Toolkit.getDefaultToolkit().beep();
        this.setTitle("Warning");
        JLabel label = new JLabel("    Please enter a valid String");

        this.add(label);
    }

    // EFFECTS: Constructs a warning dialog shows what went wrong
    public WarningDialog(FalseNumberException e) {
        Toolkit.getDefaultToolkit().beep();
        this.setTitle("Warning");
        JLabel label = new JLabel("    Please enter a valid Number");

        this.add(label);
    }

    // EFFECTS: Constructs a warning dialog shows what went wrong
    public WarningDialog() {
        Toolkit.getDefaultToolkit().beep();
        this.setTitle("Warning");
        this.setBounds(800,500,400,100);
        JLabel label = new JLabel("    Sorry, current version does not support deleting saved data");

        this.add(label);
    }
}
