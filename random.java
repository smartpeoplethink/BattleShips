import java.util.Arrays;
import java.util.List;
import java.util.Random;

class random extends player{
    
    Random rand = new Random();
    @Override
    public int[] Guess() {
        int x, y;

    do {
        x = rand.nextInt(10); // shorter form: 0–9
        y = rand.nextInt(10);
    } while (-1 != functions.inList(x, y, GameStats.guessesList));

        return new int[]{x, y};
    }



}
