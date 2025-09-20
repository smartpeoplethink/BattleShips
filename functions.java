import java.util.ArrayList;
import java.util.List;

public class functions {
    public static void main(String[] args) {
        List<int[]> newList = new ArrayList<>();
        newList.add(new int[]{0,1,2,3});
        newList.add(new int[]{10,11,12,13});
        newList.add(new int[]{100,101,102,103});
        System.out.print(inListBoolean(0,1,newList));
    }
    static int inList(int x, int y, List<int[]> pastGuesses) {
        int value = pastGuesses.stream()
                .filter(arr -> arr[0] == x && arr[1] == y)
                .findFirst()
                .map(arr -> arr[3])
                .orElse(-1);

        return value;
    }
    static boolean inListBoolean(int x, int y, List<int[]> pastGuesses) {
        boolean value = pastGuesses.stream()
                .anyMatch(arr -> arr[0] == x && arr[1] == y);

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
