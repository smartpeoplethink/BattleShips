import java.util.List;

import javax.swing.JFrame;

public class game {
    gameController controller;
    game(){
        controller = new gameController();
    } 
    public void start(){
        
        generateShips ship = new generateShips();
        List<int[]> ships = ship.generate();


        player player = new probSimulater();
        controller.playGame(player, ships);
        screenSetUp(ships, player.pastGuesses);
    }
    public void screenSetUp(List<int[]> greyCells, List<int[]> numberArray){
        JFrame frame = new JFrame("Grid Display with Grey Cells");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new gridDisplay(greyCells, numberArray));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
}
    
}

