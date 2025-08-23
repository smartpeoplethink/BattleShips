import java.util.ArrayList;
import java.util.List;

public interface player {
    List<int[]> pastGuesses = new ArrayList<>();

    int[] guess();

    default void result(int[] guess, boolean hit) {
        pastGuesses.add(new int[]{guess[0], guess[1], hit ? 0 : 1});

    } 


}
