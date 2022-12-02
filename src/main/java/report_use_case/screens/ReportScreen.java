package report_use_case.screens;



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

    Review currentReview;
    User currentUser;

    JButton report, cancel;

    JFrame owner;

    /**
     * @param previousFrame: main JFrame (specifically, the Restaurant view)
     * @param controller: ReportController being passed in
     * @param currentReview: Review, the review being reported
     * @param currentUser: User, the Reporter
     *
     * INITIALIZE REPORT VIEW: A POP OUT WINDOW
     */
    public ReportScreen(JFrame previousFrame, ReportController controller, Review currentReview, User currentUser) {
        super(previousFrame, true);
        this.reportController = controller;
        this.currentReview = currentReview;
        this.currentUser = currentUser;


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
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

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
        if (evt.getActionCommand().equals("Report")) {
            //case1.1: Report is created successfully
            try {
                reportController.create(reason.getText(), this.currentReview, this.currentUser);
                JOptionPane.showMessageDialog(this, "Reported successfully!");
            } //case 1.2: Report isn't created; reason why report isn't created is shown by the error message
            catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());


            }

            this.dispose();
        } else if(evt.getActionCommand().equals("Cancel")){

            this.dispose();

        }

        }
    }
