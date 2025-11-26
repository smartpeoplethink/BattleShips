import javax.swing.*;
import java.awt.*;
import java.util.List;
public class mainPanel extends javax.swing.JPanel{
    static double evenSquareIncrease = 1;
    static int numberOfGames = 100000;
    static boolean additionMethod = true;
    static boolean runGameSaver = true;


    public static void main(String[] args){
        if (runGameSaver){
            gameSaver G = new gameSaver();
            G.start();
        }
        else{
            game G = new game();
            G.start();
        }
        
        
    }
    

}