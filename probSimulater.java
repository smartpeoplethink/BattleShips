
import java.util.*;

public class probSimulater extends player{
    int SIMMULTIPLIER = 100;
    double proportionIncrease = 1.1;
    generateShips gen = new generateShips();

    @Override
    public int[] Guess() {
        return numbers();
    }
    public int[] numbers(){
        List<int[]> ships;
        int[][] prob = new int[10][10];
        int quantity = 0;

        //TO:DO Fix this it is somewhat broken. I think it has to do with inlistboolean
        while (quantity < GuessesNeeded(17-GameStats.hitsMade)){
            ships = gen.generate();
            boolean isNew = true;
            for (int[] pastGuess :GameStats.guessesList){
                if (functions.inListBoolean(pastGuess[0], pastGuess[1], ships) ){
                    isNew = false;

                }
            }
            if (isNew){
                
                quantity++;
                for (int[] ship : ships){
                    if (functions.inListBoolean(ship[0], ship[1], GameStats.guessesList)){
                        prob[ship[0]][ship[1]]++;
                    }
                    else{
                        prob[ship[0]][ship[1]]-=1;
                    }
                }
            }
            
        }
        
        return functions.maxElement(prob);
    }
    private int GuessesNeeded(int hitsNeeded){
        return switch (hitsNeeded) {
            case (1), (2), (3), (4) -> 1;
            case (5) -> 2;
            case (6) -> 10;
            case (7) -> 50;
            case (8) -> 80;
            case (9) -> 100;
            case (10) -> 200;
            case (11) -> 500;
            case (12) -> 800;
            case (13) -> 1000;
            case (14) -> 3000;
            case (15) -> 5000;
            case (16) -> 10000;
            case (17) -> 1000000;
            default -> {
                System.out.println("*************ERROR************");
                yield 0;
            }
        };
    }
    
}
