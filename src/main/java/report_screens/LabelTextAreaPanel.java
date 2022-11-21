package report_screens;

import javax.swing.*;




/**
 *
 * Used to add a Label beside a JTextArea
 */
public class LabelTextAreaPanel extends JPanel {
    /**
     *
     * @param label
     * @param textArea
     *
     *  creating a label to assigned jtextarea
     */
    public LabelTextAreaPanel(JLabel label, JTextArea textArea) {
        this.add(label);
        this.add(textArea);
    }
}