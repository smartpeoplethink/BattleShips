
import java.util.*;

public class probSimulater implements player{
    int simNumber = 1;
    generateShips gen = new generateShips();
    @Override
    public int[] guess() {
        return numbers();
    }
    public int[] numbers(){
        List<int[]> ships = new ArrayList<>();
        int[][] prob = new int[10][10];
        int quantity = 0;
        while (quantity <= simNumber){
            ships = gen.generate();
            boolean isNew = true;
            for (int[] pastGuess :pastGuesses){
                if (functions.inList(pastGuess[0], pastGuess[1], ships) & pastGuess[2] == 1){
                    isNew = false;
                    
                }
            }
            if (isNew){
                
                quantity++;
                for (int[] ship : ships){
                    if (!functions.inList(ship[0], ship[1], pastGuesses)){
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
    
}
