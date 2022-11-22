package report_screens;



import entities.Review;
import entities.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;



public class ReportScreen extends JDialog implements ActionListener {

    //where the user types his/her reason
    JTextArea reason = new JTextArea(5, 20);

    ReportController reportController;

    Review current_review;
    User current_user;

    JButton report, cancel;

    JFrame owner;

    /**
     * @param previousFrame: main JFrame
     * @param controller
     * @param current_review
     * @param current_user
     *
     * INITIALIZE REPORT VIEW: A POP OUT WINDOW
     */
    public ReportScreen(JFrame previousFrame, ReportController controller, Review current_review, User current_user) {
        super(previousFrame, true);
        this.reportController = controller;
        this.current_review = current_review;
        this.current_user = current_user;


        JLabel title = new JLabel("Report Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Label the TextArea, reason.
        LabelTextAreaPanel reasonInfo = new LabelTextAreaPanel(
                new JLabel("Enter your reason here"), reason);

        //set up buttons
        report = new JButton("Report");
        cancel = new JButton("Cancel");

        JPanel buttons = new JPanel();
        buttons.add(report);
        buttons.add(cancel);

        //add actionListener
        report.addActionListener(this);
        cancel.addActionListener(this);

        JPanel main = new JPanel();
        main.add(buttons);
        main.add(reasonInfo);

        this.setContentPane(main);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(600, 400));
        this.pack();
        this.setLocationRelativeTo(null);

        //Make the JFrame appear
        this.setVisible(true);


    }

    /**
     *
     * @param evt the event to be processed
     *  Reacts to button click
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
        //case1: if the user hit report button
        if (evt.getSource() == report) {
            //case1.1: Report is created successfully
            try {
                reportController.create(reason.getText(), this.current_review, this.current_user);
                JOptionPane.showMessageDialog(this, "Reported successfully!");
            } //case 1.2: Report isn't created; reason why report isn't created is shown by the error message
            catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());


            }

            this.dispose();
        } else if(evt.getSource() == cancel){

            this.dispose();

        }

        }
    }
