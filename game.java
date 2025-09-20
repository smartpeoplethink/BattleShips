import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class game {
    gameController controller;
    game(){
        controller = new gameController();
    } 
    public void start(){
        int amountOfTurns = 0;
        int roundsToPlay = 100;
        int maxAmount = 0;
        int minAmount = 100;
        player bestP = null;
        player worstP = null;
        List<int[]> bestShip = null;
        List<int[]> worstShip = null;

        for (int i = 0; i<roundsToPlay; i++){
            generateShips ship = new generateShips();
            List<int[]> ships = ship.generate();
           player player = new patternPossibilityCalc();
           int guesses = controller.playGame(player, ships);
           amountOfTurns+= guesses;
           if (guesses>maxAmount){
               maxAmount = guesses;
               worstP = player;
               worstShip = ships;
           }
            if (guesses<minAmount){
                minAmount = guesses;
                bestP = player;
                bestShip = ships;
            }
         }
        System.out.println(((double)amountOfTurns)/((double)roundsToPlay));
        System.out.println(minAmount);
        System.out.println(maxAmount);
        screenSetUp(worstShip, worstP.GameStats.guessesList);
        screenSetUp(bestShip, bestP.GameStats.guessesList);
    }
    public static void screenSetUp(List<int[]> greyCells, List<int[]> numberArray){
        JFrame frame = new JFrame("Grid Display with Grey Cells");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new gridDisplay(greyCells, numberArray));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
}
    
}

