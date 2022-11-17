package report_screens;



import entities.Review;
import entities.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;



public class ReportScreen extends JPanel implements ActionListener {

    JTextArea reason = new JTextArea(5, 20);

    ReportController reportController;

    Review current_review;
    User current_user;

    JButton report, cancel;

    //JFrame report_window;
    public ReportScreen(ReportController controller, Review current_review, User current_user) {
        this.reportController = controller;
        this.current_review = current_review;
        this.current_user = current_user;

       // report_window = new JFrame();
        JLabel title = new JLabel("Report Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel reasonInfo = new LabelTextPanel(
                new JLabel("Enter your reason here"), reason);

        report = new JButton("Report");
        cancel = new JButton("Cancel");

        JPanel buttons = new JPanel();
        buttons.add(report);
        buttons.add(cancel);

        report.addActionListener(this);
        cancel.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setSize(200, 300);
        this.add(title);
        this.add(reasonInfo);
        this.add(buttons);


    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
        if (evt.getSource() == report) {
            try {
                reportController.create(reason.getText(), this.current_review, this.current_user);
                JOptionPane.showMessageDialog(this, "Reported successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());


            }
        } else if(evt.getSource() == cancel){
                this.setVisible(false);
        }

        }
    }
