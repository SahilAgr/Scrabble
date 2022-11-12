import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private Game model;
    private Coordinates coords;
    public Controller(Game model, Coordinates coords) {
        this.model=model;
        this.coords=coords;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] options = {"Test", "Place"};
        int testPlace = JOptionPane.showOptionDialog(null, "Would you like to Test or Place a word?",
                "Select an Option",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null, options, options[1]);
        String[] options2 = {"Right", "Down"};
        int rightDown = JOptionPane.showOptionDialog(null, "Would you like the word to be placed Right or Down?",
                "Select an Option",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null, options2, options2[1]);
        String word = JOptionPane.showInputDialog(null, "Enter your Word:");
        if(testPlace == 0){
            model.place(options[rightDown].toLowerCase(), coords, word, true);
        }else if(testPlace == 1){
            model.place(options[rightDown].toLowerCase(), coords, word, false);
        }

    }
}