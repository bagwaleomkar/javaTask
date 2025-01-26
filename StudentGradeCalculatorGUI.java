import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeCalculatorGUI {

    private JTextField[] subjectFields;
    private JLabel totalMarksLabel;
    private JLabel averageLabel;
    private JLabel gradeLabel;

    public StudentGradeCalculatorGUI() {
        // Initialize the GUI
        JFrame frame = new JFrame("Student Grade Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel instructionsLabel = new JLabel("Enter marks for each subject (out of 100):");
        instructionsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(instructionsLabel);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Create input fields for 5 subjects
        subjectFields = new JTextField[5];
        for (int i = 0; i < 5; i++) {
            JLabel subjectLabel = new JLabel("Subject " + (i + 1) + ":");
            subjectLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(subjectLabel);

            JTextField subjectField = new JTextField();
            subjectField.setMaximumSize(new Dimension(200, 30));
            subjectFields[i] = subjectField;
            panel.add(subjectField);

            panel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        calculateButton.addActionListener(new CalculateButtonListener());
        panel.add(calculateButton);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        totalMarksLabel = new JLabel("Total Marks: ");
        totalMarksLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(totalMarksLabel);

        averageLabel = new JLabel("Average Percentage: ");
        averageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(averageLabel);

        gradeLabel = new JLabel("Grade: ");
        gradeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(gradeLabel);

        frame.add(panel);
        frame.setVisible(true);
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int totalMarks = 0;
                int numSubjects = subjectFields.length;

                for (JTextField field : subjectFields) {
                    int marks = Integer.parseInt(field.getText());
                    if (marks < 0 || marks > 100) {
                        throw new NumberFormatException("Marks should be between 0 and 100.");
                    }
                    totalMarks += marks;
                }

                int average = totalMarks / numSubjects;
                String grade;

                if (average >= 90) {
                    grade = "A";
                } else if (average >= 80) {
                    grade = "B";
                } else if (average >= 70) {
                    grade = "C";
                } else if (average >= 60) {
                    grade = "D";
                } else {
                    grade = "F";
                }

                totalMarksLabel.setText("Total Marks: " + totalMarks);
                averageLabel.setText("Average Percentage: " + average + "%");
                gradeLabel.setText("Grade: " + grade);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid marks between 0 and 100.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentGradeCalculatorGUI::new);
    }
}
