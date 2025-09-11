import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class pattern extends player{
    csvReader file = new csvReader("lines.csv");

    boolean part1 = true;
    List<int[]> OriginalHits;
    int hit = 0;
    int side = 0;
    public pattern(){
    }

    @Override
    public int[] Guess(){
        return Prediction();
    }

    private int[] Prediction(){
        if (GameStats.hitsMade < 10){
            return file.line();
        }
        if (part1){
            OriginalHits = GameStats.hitsList;
        }
        part1 = false;

        return null;
    }
}
