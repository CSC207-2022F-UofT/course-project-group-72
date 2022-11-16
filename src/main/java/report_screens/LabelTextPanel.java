package report_screens;

import javax.swing.*;

public class LabelTextPanel extends JPanel {
    public LabelTextPanel(JLabel label, JTextArea textArea) {
        this.add(label);
        this.add(textArea);
    }
}