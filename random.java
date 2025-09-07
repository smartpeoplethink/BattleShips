import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class random implements player{
    
    Random rand = new Random();
    @Override
    public int[] guess() {
        int x, y;

    do {
        x = rand.nextInt(10); // shorter form: 0–9
        y = rand.nextInt(10);
    } while (functions.inList(x, y, pastGuesses));

        return new int[]{x, y};
    }

    

    
}
