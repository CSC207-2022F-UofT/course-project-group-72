package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;


public class ReportScreen extends JPanel implements ActionListener {

    JTextField reason = new JTextField(50);

    ReportController reportController;

    Review current_review;
    User current_user;

    public ReportScreen(ReportController controller, Review current_review, User current_user) {

        this.reportController = controller;
        this.current_review = current_review;
        this.current_user = current_user;

        JLabel title = new JLabel("Report Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel reasonInfo = new LabelTextPanel(
                new JLabel("Enter your reason here (Max:50 words)"), reason);

        JButton report = new JButton("Report");
        JButton cancel = new JButton("Cancel");

        JPanel buttons = new JPanel();
        buttons.add(report);
        buttons.add(cancel);

        report.addActionListener(this);
        cancel.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(reasonInfo);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());

        try {
            reportController.create(reason.getText(), this.current_review, this.current_user);
            JOptionPane.showMessageDialog(this, "Reported successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());


        }
    }
}
