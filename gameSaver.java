import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class gameSaver {
    double evenSquareIncrease = 1.1;
    gameController controller;
    DataLogger dataLogger;
    gameSaver(){
        dataLogger = new DataLogger();
        try {
            dataLogger.initLogger("data4.csv");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        controller = new gameController();
    } 
    public void start(){
        int roundsToPlay = mainPanel.numberOfGames;
        double amount_increase = 0.1;
        int range = (int)(1/amount_increase);
        long[] turns = new long[101]; 
        for (int i = 0; i < range; i++){
            turns = new long[101];
            mainPanel.evenSquareIncrease = 1+(amount_increase*(double)i);
            mainPanel.evenSquareIncrease = ((double) Math.round(range*mainPanel.evenSquareIncrease))*amount_increase;
            System.out.println(mainPanel.evenSquareIncrease);
            dataLogger.newLine();
            for (int j = 0; j<roundsToPlay; j++){
                generateShips ship = new generateShips();
                List<int[]> ships = ship.generate();
                player player = new patternPossibilityCalc();
                int guesses = controller.playGame(player, ships);
                turns[guesses]++;
            }
            for (long turn : turns){
                dataLogger.logInt(turn);
            }
        }
        
        dataLogger.closeLogger();
        
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

