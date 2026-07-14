import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizGameGUI extends JFrame implements ActionListener {

    JLabel questionLabel;
    JRadioButton option1, option2, option3, option4;
    JButton nextButton;
    ButtonGroup group;

    int score = 0;
    int questionIndex = 0;

    String[] questions = {
            "1. Which language is used for Android Development?",
            "2. Who developed Java?",
            "3. Which keyword is used to create object in Java?",
            "4. Which symbol is used to end a statement in Java?",
            "5. Which method is the entry point of Java program?",
            "6. Java is a ____ language.",
            "7. Which package is used for GUI in Java?",
            "8. Which operator is used for addition?"
    };

    String[][] options = {
            {"Python", "Java", "C++", "HTML"},
            {"Microsoft", "Apple", "Sun Microsystems", "Google"},
            {"new", "class", "object", "this"},
            {".", ";", ":", ","},
            {"start()", "main()", "run()", "execute()"},
            {"Low Level", "Machine", "High Level", "Binary"},
            {"java.awt", "java.io", "java.sql", "java.net"},
            {"*", "-", "+", "/"}
    };

    int[] answers = {1, 2, 0, 1, 1, 2, 0, 2};

    public QuizGameGUI() {

        setTitle("Quiz Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        questionLabel = new JLabel();
        questionLabel.setBounds(30, 30, 520, 30);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));

        option1 = new JRadioButton();
        option1.setBounds(50, 80, 300, 30);

        option2 = new JRadioButton();
        option2.setBounds(50, 120, 300, 30);

        option3 = new JRadioButton();
        option3.setBounds(50, 160, 300, 30);

        option4 = new JRadioButton();
        option4.setBounds(50, 200, 300, 30);

        group = new ButtonGroup();
        group.add(option1);
        group.add(option2);
        group.add(option3);
        group.add(option4);

        nextButton = new JButton("Next");
        nextButton.setBounds(220, 280, 100, 40);
        nextButton.addActionListener(this);

        add(questionLabel);
        add(option1);
        add(option2);
        add(option3);
        add(option4);
        add(nextButton);

        setLayout(null);

        loadQuestion();

        setVisible(true);
    }

    public void loadQuestion() {

        if (questionIndex < questions.length) {

            questionLabel.setText(questions[questionIndex]);

            option1.setText(options[questionIndex][0]);
            option2.setText(options[questionIndex][1]);
            option3.setText(options[questionIndex][2]);
            option4.setText(options[questionIndex][3]);

            group.clearSelection();

        } else {

            JOptionPane.showMessageDialog(this,
                    "Quiz Finished!\nYour Score: " + score + "/" + questions.length);

            System.exit(0);
        }
    }

    public void actionPerformed(ActionEvent e) {

        int selectedAnswer = -1;

        if (option1.isSelected()) selectedAnswer = 0;
        if (option2.isSelected()) selectedAnswer = 1;
        if (option3.isSelected()) selectedAnswer = 2;
        if (option4.isSelected()) selectedAnswer = 3;

        if (selectedAnswer == answers[questionIndex]) {
            score++;
        }

        questionIndex++;
        loadQuestion();
    }

    public static void main(String[] args) {
        new QuizGameGUI();
    }
}