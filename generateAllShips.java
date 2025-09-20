import java.io.*;
import java.util.List;
import java.util.zip.GZIPOutputStream;
public class generateAllShips {
    public static void main(String[] args) {
        ships();
    }
    public static void ships(){
        long counter = 0;
        for (int coord1Ship5 = 0; coord1Ship5<10; coord1Ship5++){
        for (int coord2Ship5 = 0; coord2Ship5<6; coord2Ship5++){
        for (int coord1Ship4 = 0; coord1Ship4<10; coord1Ship4++){
        for (int coord2Ship4 = 0; coord2Ship4<7; coord2Ship4++){
        for (int coord1Ship3_1 = 0; coord1Ship3_1<10; coord1Ship3_1++){
        for (int coord2Ship3_1 = 0; coord2Ship3_1<8; coord2Ship3_1++){
        for (int coord1Ship3_2 = 0; coord1Ship3_2<10; coord1Ship3_2++){
        for (int coord2Ship3_2 = 0; coord2Ship3_2<8; coord2Ship3_2++){
        for (int coord1Ship2 = 0; coord1Ship2<10; coord1Ship2++){
        for (int coord2Ship2 = 0; coord2Ship2<9; coord2Ship2++){
        for (int rShip5 = 0; rShip5<2; rShip5++){
        for (int rShip4 = 0; rShip4<2; rShip4++){
        for (int rShip3_1 = 0; rShip3_1<2; rShip3_1++){
        for (int rShip3_2 = 0; rShip3_2<2; rShip3_2++){
        for (int rShip2 = 0; rShip2<2; rShip2++){
            List<int[]> ship5 = generateShips.generatePath(
                (rShip5 != 1 ? coord1Ship5 : coord2Ship5),
                (rShip5 == 1 ? coord2Ship5 : coord1Ship5), 
                rShip5, 5,4);

            List<int[]> ship4 = generateShips.generatePath(
                (rShip4 != 1 ? coord1Ship4 : coord2Ship4),
                (rShip4 == 1 ? coord2Ship4 : coord1Ship4), 
                rShip4, 4, 3);

            List<int[]> ship3_1 = generateShips.generatePath(
                (rShip3_1 != 1 ? coord1Ship3_1 : coord2Ship3_1),
                (rShip3_1 == 1 ? coord2Ship3_1 : coord1Ship3_1), 
                rShip3_1, 3, 2);

            List<int[]> ship3_2 = generateShips.generatePath(
                (rShip3_2 != 1 ? coord1Ship3_2 : coord2Ship3_2),
                (rShip3_2 == 1 ? coord2Ship3_2 : coord1Ship3_2), 
                rShip3_2, 3, 1);

            List<int[]> ship2 = generateShips.generatePath(
                (rShip2 != 1 ? coord1Ship2 : coord2Ship2),
                (rShip2 == 1 ? coord2Ship2 : coord1Ship2), 
                rShip2, 2, 0);

        }
        }    
        }    
        }    
        }
        }
        }
        }
        }    
        }    
        }    
        }
        }
        }
        System.out.println("1/10");
        }
        System.out.println(counter);
    }
    

}
