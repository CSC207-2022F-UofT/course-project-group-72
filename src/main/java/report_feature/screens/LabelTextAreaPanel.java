package report_feature.screens;

import javax.swing.*;




/**
 *
 * Used to add a Label beside a JTextArea
 */
public class LabelTextAreaPanel extends JPanel {
    /**
     *
     * @param label: JLabel that is added to JtextArea
     * @param textArea: JTextArea where the JLabel is added to
     *
     *  creating a label to assigned jtextarea
     */
    public LabelTextAreaPanel(JLabel label, JTextArea textArea) {
        this.add(label);
        this.add(textArea);
    }
}