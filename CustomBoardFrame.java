import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static java.lang.String.valueOf;

public class CustomBoardFrame extends JFrame implements ActionListener{

    private Board board;
    private JButton[][] buttons;
    private char rows[] = {' ','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O'};

    public CustomBoardFrame(){

        buttons = new JButton[BoardFrame.BOARDLENGTH][BoardFrame.BOARDLENGTH];
        this.setLayout(new GridLayout(BoardFrame.BOARDLENGTH+2,BoardFrame.BOARDLENGTH+1));

        for (int i = 0; i < BoardFrame.BOARDLENGTH+1; i++) {
            JLabel label = new JLabel(valueOf(rows[i]),SwingConstants.CENTER);
            this.add(label);
        }

        for (int i = 0; i < BoardFrame.BOARDLENGTH; i++) {
            JLabel label = new JLabel(valueOf(i+1),SwingConstants.CENTER);
            this.add(label);
            for (int j = 0; j < BoardFrame.BOARDLENGTH; j++) {
                JButton b = new JButton(" ");
                buttons[i][j] = b;
                b.setBackground(BoardFrame.BLANKCOLOR);
                b.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(b.getBackground()==BoardFrame.BLANKCOLOR){
                            b.setBackground(Tile.l2);
                        }else if(b.getBackground()==Tile.l2){
                            b.setBackground(Tile.l3);
                        } else if(b.getBackground()==Tile.l3){
                            b.setBackground(Tile.w2);
                        } else if(b.getBackground()==Tile.w2){
                            b.setBackground(Tile.w3);
                        } else if(b.getBackground()==Tile.w3){
                            b.setBackground(BoardFrame.BLANKCOLOR);
                        }
                        update();
                    }
                });
                this.add(b);
            }
        }
        JButton closeButton = new JButton("C");
        closeButton.addActionListener(this);
        closeButton.setToolTipText("Close");
        this.add(closeButton);
        for (int i = 0; i<13; i++){
            JLabel l = new JLabel("");
            this.add(l);
        }

        JButton saveButton = new JButton("S");
        saveButton.addActionListener(this);
        saveButton.setToolTipText("Save");
        this.add(saveButton);


        this.setSize(750,800);
        this.setVisible(true);
    }

    public void update() {
        for (int i = 0; i < BoardFrame.BOARDLENGTH; i++) {
            for (int j = 0; j < BoardFrame.BOARDLENGTH; j++) {
                buttons[i][j].setBackground(buttons[i][j].getBackground());

            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o instanceof JButton) {
            switch (((JButton) o).getText()) {
                case "C" -> {
                    this.dispose();
                }
                case "S" -> {
                    BoardFrame.customBoard = new Board(buttons);
                    this.dispose();
                    BoardFrame.updateCustom();


                }
                default -> System.out.println("Error");
            }
        }
    }
}
