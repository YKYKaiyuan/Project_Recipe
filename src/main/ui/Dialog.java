package ui;

import javax.swing.*;

public abstract class Dialog extends JDialog {
    public Dialog() {
        this.setResizable(false);
        this.setVisible(true);
        this.setBounds(800,500,300,100);
    }

}
