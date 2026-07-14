import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class TicTacToeGUI extends JFrame implements ActionListener {

    JButton[] buttons = new JButton[9];

    Random random = new Random();

    public TicTacToeGUI() {

        setTitle("Tic Tac Toe - User vs Computer");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(3, 3, 5, 5));

        Font font = new Font("Arial", Font.BOLD, 60);

        for (int i = 0; i < 9; i++) {

            buttons[i] = new JButton("");

            buttons[i].setFont(font);

            buttons[i].addActionListener(this);

            panel.add(buttons[i]);
        }

        add(panel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        JButton clickedButton = (JButton) e.getSource();

        // Prevent clicking filled button
        if (!clickedButton.getText().equals("")) {
            return;
        }

        // User Move
        clickedButton.setText("X");

        // Check if User Wins
        if (checkWinner("X")) {

            JOptionPane.showMessageDialog(this, "You Win!");

            resetGame();

            return;
        }

        // Check Draw
        if (isDraw()) {

            JOptionPane.showMessageDialog(this, "Match Draw!");

            resetGame();

            return;
        }

        // Computer Move
        computerMove();

        // Check if Computer Wins
        if (checkWinner("O")) {

            JOptionPane.showMessageDialog(this, "Computer Wins!");

            resetGame();

            return;
        }

        // Check Draw Again
        if (isDraw()) {

            JOptionPane.showMessageDialog(this, "Match Draw!");

            resetGame();
        }
    }

    // Smart Computer Move
    public void computerMove() {

        // Try to Win
        for (int i = 0; i < 9; i++) {

            if (buttons[i].getText().equals("")) {

                buttons[i].setText("O");

                if (checkWinner("O")) {
                    return;
                }

                buttons[i].setText("");
            }
        }

        // Block User Winning
        for (int i = 0; i < 9; i++) {

            if (buttons[i].getText().equals("")) {

                buttons[i].setText("X");

                if (checkWinner("X")) {

                    buttons[i].setText("O");

                    return;
                }

                buttons[i].setText("");
            }
        }

        // Random Move
        int move;

        do {

            move = random.nextInt(9);

        } while (!buttons[move].getText().equals(""));

        buttons[move].setText("O");
    }

    // Check Winner
    public boolean checkWinner(String player) {

        int[][] winPositions = {
                {0,1,2},
                {3,4,5},
                {6,7,8},
                {0,3,6},
                {1,4,7},
                {2,5,8},
                {0,4,8},
                {2,4,6}
        };

        for (int[] pos : winPositions) {

            if (buttons[pos[0]].getText().equals(player) &&
                buttons[pos[1]].getText().equals(player) &&
                buttons[pos[2]].getText().equals(player)) {

                return true;
            }
        }

        return false;
    }

    // Check Draw
    public boolean isDraw() {

        for (JButton button : buttons) {

            if (button.getText().equals("")) {

                return false;
            }
        }

        return true;
    }

    // Reset Game
    public void resetGame() {

        for (JButton button : buttons) {

            button.setText("");
        }
    }

    public static void main(String[] args) {

        new TicTacToeGUI();
    }
}