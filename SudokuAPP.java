import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SudokuAPP extends JFrame implements ActionListener {
    private JTextField[][] fields = new JTextField[9][9];
    private JButton checkButton;
    
    public SudokuAPP() {
        setTitle("Sudoku");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel(new GridLayout(9, 9, 2, 2));
        Font font = new Font("Arial", Font.PLAIN, 20);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                fields[i][j] = new JTextField();
                fields[i][j].setFont(font);
                panel.add(fields[i][j]);
            }
        }
        
        JPanel buttonPanel = new JPanel();
        checkButton = new JButton("Check");
        checkButton.addActionListener(this);
        buttonPanel.add(checkButton);
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkButton) {
            if (isSudokuSolved()) {
                JOptionPane.showMessageDialog(this, "Selamat! Anda berhasil menyelesaikan Sudoku!");
            } else {
                JOptionPane.showMessageDialog(this, "Ada kesalahan dalam solusi Sudoku.");
            }
        }
    }
    
    private boolean isSudokuSolved() {
        int[][] sudoku = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                try {
                    sudoku[i][j] = Integer.parseInt(fields[i][j].getText());
                } catch (NumberFormatException e) {
                    return false; 
                }
            }
        }
        
        // Memeriksa baris
        for (int i = 0; i < 9; i++) {
            boolean[] found = new boolean[10]; 
            for (int j = 0; j < 9; j++) {
                if (sudoku[i][j] != 0 && found[sudoku[i][j]]) {
                    return false; 
                }
                found[sudoku[i][j]] = true;
            }
        }
        
        // Memeriksa kolom
        for (int j = 0; j < 9; j++) {
            boolean[] found = new boolean[10];
            for (int i = 0; i < 9; i++) {
                if (sudoku[i][j] != 0 && found[sudoku[i][j]]) {
                    return false;
                }
                found[sudoku[i][j]] = true;
            }
        }
        
        // Memeriksa subgrid 3x3
        for (int k = 0; k < 9; k += 3) {
            for (int l = 0; l < 9; l += 3) {
                boolean[] found = new boolean[10];
                for (int i = k; i < k + 3; i++) {
                    for (int j = l; j < l + 3; j++) {
                        if (sudoku[i][j] != 0 && found[sudoku[i][j]]) {
                            return false;
                        }
                        found[sudoku[i][j]] = true;
                    }
                }
            }
        }
        
        return true; 
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SudokuAPP::new);
    }
}
