import java.util.List;
import java.util.Objects;

public class functions {
    static int inList(int x, int y, List<int[]> pastGuesses) {
        int value = pastGuesses.stream()
                .filter(arr -> arr[0] == x && arr[1] == y)
                .findFirst()
                .map(arr -> arr[2])
                .orElse(-1);

        return value;
    }
    public static int[] maxElement(int[][] arr) {
    int max = Integer.MIN_VALUE;
    int maxRow = -1, maxCol = -1;

    for (int r = 0; r < arr.length; r++) {
        for (int c = 0; c < arr[r].length; c++) {
            if (arr[r][c] > max) {
                max = arr[r][c];
                maxRow = r;
                maxCol = c;
            }
        }
    }

    return new int[]{maxRow, maxCol}; // [row, col]
}

    
}
