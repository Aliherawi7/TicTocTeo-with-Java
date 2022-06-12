package TicTocTeo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.LineBorder;

class TicTacTeo extends JFrame{

    private char whoseTurn = 'X';
    private Cell[][] cells = new Cell[3][3];
    private JLabel jlbStatus = new JLabel("x's turn  to play");

    public TicTacTeo() {
        JPanel playArea = new JPanel(new GridLayout(4,3,0,0));
        playArea.setSize(400,300);
        jlbStatus.setHorizontalTextPosition(SwingConstants.CENTER);
        for(int i =0; i<3; i++)
            for(int j=0; j<3; j++)
                playArea.add(cells[i][j] = new Cell());

        playArea.setBorder(new LineBorder(Color.red, 2));
        playArea.add(jlbStatus);
        // JFrame setup
        setSize(400,450);
        add(playArea);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //check if cells are full
    public boolean isFull() {
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                if(cells[i][j].getToken() == ' ')
                    return false;

        return true;
    }
    // check who is the winner
    public boolean isWon(char token) {
        for(int i=0; i<3; i++)
            if((cells[i][0].getToken() == token) &&
                    (cells[i][1].getToken() == token) &&
                    (cells[i][2].getToken() == token))
                return true;
        for(int j =0; j<3; j++)
            if((cells[0][j].getToken() == token) &&
                    (cells[1][j].getToken() == token)&&
                    (cells[2][j].getToken() == token))
                return true;

        if((cells[0][0].getToken() == token) &&
                (cells[1][1].getToken() == token)&&
                (cells[2][2].getToken() == token))
            return true;

        if((cells[0][2].getToken() == token) &&
                (cells[1][1].getToken() == token)&&
                (cells[2][0].getToken() == token))
            return true;

        return false;
    }


    //private inner class cell
    public class Cell extends JPanel{
        private char token = ' ';

        public Cell() {
            setBorder(new LineBorder(Color.GREEN,1));
            setBackground(Color.yellow);
            addMouseListener(new MyMouseListener());

        }

        public char getToken() {
            return token;
        }

        public void setToken(char token) {
            this.token = token;
            repaint();
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(token == 'X') {
                g.drawLine(10, 10, getWidth()-10, getHeight()-10);
                g.drawLine(getWidth()-10, 10, 10, getHeight()-10 );
            }
            else if(token == 'O') {
                g.drawOval(10,  10, getWidth()-20,  getHeight()-20);

            }
        }
        private class MyMouseListener extends MouseAdapter{
            public void mouseClicked(MouseEvent e) {
                if(token == ' ' && whoseTurn != ' ') {
                    setToken(whoseTurn);

                    if(isWon(whoseTurn)) {
                        jlbStatus.setText(whoseTurn +" won! game is over");
                        JOptionPane.showMessageDialog(null, whoseTurn +" won! game is over");
                        whoseTurn = ' ';//the game is over

                    }else if(isFull()) {
                        jlbStatus.setText("draw! game is Over");
                        JOptionPane.showMessageDialog(null, "draw! game is Over");
                    }else {
                        whoseTurn = (whoseTurn == 'X')? 'O':'X';
                        jlbStatus.setText(whoseTurn + " is turn");
                    }
                }
            }
        }
    }

}

