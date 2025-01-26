import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManagementSystemGUI {

    // Student class to represent individual students
    public static class Student {
        private String name;
        private String rollNumber;
        private String grade;

        // Constructor
        public Student(String name, String rollNumber, String grade) {
            this.name = name;
            this.rollNumber = rollNumber;
            this.grade = grade;
        }

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRollNumber() {
            return rollNumber;
        }

        public void setRollNumber(String rollNumber) {
            this.rollNumber = rollNumber;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        // Method to display student details
        public String displayStudentInfo() {
            return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
        }
    }

    // StudentManagementSystem class to manage collection of students
    public static class StudentManagementSystem {
        private List<Student> students;

        // Constructor
        public StudentManagementSystem() {
            this.students = new ArrayList<>();
        }

        // Method to add a student
        public void addStudent(String name, String rollNumber, String grade) {
            students.add(new Student(name, rollNumber, grade));
        }

        // Method to remove a student by roll number
        public boolean removeStudent(String rollNumber) {
            for (Student student : students) {
                if (student.getRollNumber().equals(rollNumber)) {
                    students.remove(student);
                    return true;
                }
            }
            return false;
        }

        // Method to search for a student by roll number
        public Student searchStudent(String rollNumber) {
            for (Student student : students) {
                if (student.getRollNumber().equals(rollNumber)) {
                    return student;
                }
            }
            return null;
        }

        // Method to display all students
        public String displayAllStudents() {
            StringBuilder result = new StringBuilder();
            if (students.isEmpty()) {
                result.append("No students found.");
            } else {
                for (Student student : students) {
                    result.append(student.displayStudentInfo()).append("\n");
                }
            }
            return result.toString();
        }
    }

    // GUI Class for Student Management System
    public static class StudentManagementSystemGUIApp {

        private StudentManagementSystem sms;
        private JTextArea resultArea;
        private JTextField nameField, rollNumberField, gradeField;

        public StudentManagementSystemGUIApp() {
            sms = new StudentManagementSystem();

            JFrame frame = new JFrame("Student Management System");
            frame.setSize(500, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout(10, 10));

            // Title label
            JLabel titleLabel = new JLabel("STUDENT MANAGEMENT SYSTEM", JLabel.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
            titleLabel.setForeground(Color.DARK_GRAY);
            frame.add(titleLabel, BorderLayout.NORTH);

            // Panel for input fields and buttons
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(5, 2, 10, 10));

            // Name input
            panel.add(new JLabel("Student Name:"));
            nameField = new JTextField();
            panel.add(nameField);

            // Roll number input
            panel.add(new JLabel("Roll Number:"));
            rollNumberField = new JTextField();
            panel.add(rollNumberField);

            // Grade input
            panel.add(new JLabel("Grade:"));
            gradeField = new JTextField();
            panel.add(gradeField);

            // Add student button
            JButton addButton = new JButton("Add Student");
            addButton.setBackground(new Color(34, 139, 34)); // Green color
            addButton.setForeground(Color.WHITE);
            panel.add(addButton);

            // Remove student button
            JButton removeButton = new JButton("Remove Student");
            removeButton.setBackground(new Color(255, 69, 0)); // Red color
            removeButton.setForeground(Color.WHITE);
            panel.add(removeButton);

            // Search student button
            JButton searchButton = new JButton("Search Student");
            searchButton.setBackground(new Color(30, 144, 255)); // Blue color
            searchButton.setForeground(Color.WHITE);
            panel.add(searchButton);

            // Display all students button
            JButton displayButton = new JButton("Display All Students");
            displayButton.setBackground(new Color(255, 215, 0)); // Yellow color
            displayButton.setForeground(Color.WHITE);
            panel.add(displayButton);

            // Result area to show output
            resultArea = new JTextArea(10, 40);
            resultArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(resultArea);

            // Add panel and result area to frame
            frame.add(panel, BorderLayout.CENTER);
            frame.add(scrollPane, BorderLayout.SOUTH);

            // Add button actions
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText().trim();
                    String rollNumber = rollNumberField.getText().trim();
                    String grade = gradeField.getText().trim();
                    if (!name.isEmpty() && !rollNumber.isEmpty() && !grade.isEmpty()) {
                        sms.addStudent(name, rollNumber, grade);
                        resultArea.setText("Student added successfully.");
                    } else {
                        resultArea.setText("Please fill all fields.");
                    }
                }
            });

            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String rollNumber = rollNumberField.getText().trim();
                    if (!rollNumber.isEmpty() && sms.removeStudent(rollNumber)) {
                        resultArea.setText("Student removed successfully.");
                    } else {
                        resultArea.setText("Student not found.");
                    }
                }
            });

            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String rollNumber = rollNumberField.getText().trim();
                    if (!rollNumber.isEmpty()) {
                        Student student = sms.searchStudent(rollNumber);
                        if (student != null) {
                            resultArea.setText(student.displayStudentInfo());
                        } else {
                            resultArea.setText("Student not found.");
                        }
                    } else {
                        resultArea.setText("Please enter a roll number.");
                    }
                }
            });

            displayButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    resultArea.setText(sms.displayAllStudents());
                }
            });

            // Show the frame
            frame.setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentManagementSystemGUIApp();
            }
        });
    }
}
