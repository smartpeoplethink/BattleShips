import java.util.List;

public class functions {
    static boolean inList(int x, int y, List<int[]> pastGuesses) {
        return pastGuesses.stream()
                        .anyMatch(arr -> arr[0] == x && arr[1] == y);
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
