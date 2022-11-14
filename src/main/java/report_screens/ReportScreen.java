package report_screens;



import entities.Review;
import entities.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;



public class ReportScreen implements ActionListener {

    JTextField reason;

    ReportController reportController;

    Review current_review;
    User current_user;

    JButton report, cancel;

    JFrame report_window;
    public ReportScreen(ReportController controller, Review current_review, User current_user) {
        reason = new JTextField(50);
        this.reportController = controller;
        this.current_review = current_review;
        this.current_user = current_user;

        report_window = new JFrame();
        JLabel title = new JLabel("Report Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel reasonInfo = new LabelTextPanel(
                new JLabel("Enter your reason here (Max:50 words)"), reason);

        report = new JButton("Report");
        cancel = new JButton("Cancel");

        JPanel buttons = new JPanel();
        buttons.add(report);
        buttons.add(cancel);

        report.addActionListener(this);
        cancel.addActionListener(this);

        report_window.setSize(500, 500);
        report_window.setLayout(new BoxLayout(report_window, BoxLayout.Y_AXIS));
        report_window.add(title);
        report_window.add(reasonInfo);
        report_window.add(buttons);
        report_window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
        if (evt.getSource() == report) {
            try {
                reportController.create(reason.getText(), this.current_review, this.current_user);
                JOptionPane.showMessageDialog(report_window, "Reported successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(report_window, e.getMessage());


            }
        } else if(evt.getSource() == cancel){
            report_window.setVisible(false);
        }

    }
}
