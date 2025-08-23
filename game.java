import java.util.List;

import javax.swing.JFrame;

public class game {
    public void start(){
        
        generateShips ship = new generateShips();
        List<int[]> ships = ship.generate();
        screenSetUp(ships, new int[10][10]);
    }
    public void screenSetUp(List<int[]> greyCells, int[][] numberArray){
        JFrame frame = new JFrame("Grid Display with Grey Cells");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new gridDisplay(greyCells, numberArray));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
}
}