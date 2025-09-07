import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class repeatSquareShips {
    static final long N = 77_414_400_000L;
    public static boolean allDifferent(List<int[]> arrays) {
        Set<String> seen = new HashSet<>();
        for (int[] arr : arrays) {
            String key = Arrays.toString(arr); // content-based string representation
            if (!seen.add(key)) {
                return false; // duplicate found
            }
        }
        return true; // all unique
    }
    public static void main(String[] args) {
        long i = 0;
        Random rng = new Random(12345);
        long a = RandomPermutation.findCoprime(rng);
        long b = Math.floorMod(rng.nextLong(), N);
        long index = 0;
        while (index <= N){
            // index = RandomPermutation.permute(i, a, b);
            List<int[]> shipSquares = ShipConfigMapper.alterData(ShipConfigMapper.decode(index));
            // System.out.print("Index: ");
            // System.out.println(i);
            // System.out.print("Unique: ");
            // System.out.println(allDifferent(shipSquares));
            if (allDifferent(shipSquares)){
                i++;
            }
            if (index % 10_000_000 == 0){
                System.out.print("10 million: ");
                System.out.println(i);
            }
            index++;

        }
    }
}
// 10 million: 0
// 10 million: 0
// 10 million: 0
// 10 million: 0
// 10 million: 0
// 10 million: 0
// 10 million: 0
// 10 million: 0
// 10 million: 0
// 10 million: 0
// 10 million: 0
// 10 million: 0
// 10 million: 0
// 10 million: 0
// 10 million: 0
// 10 million: 0
// 10 million: 0
// 10 million: 0
// 10 million: 262801
// 10 million: 489600
// 10 million: 735201
// 10 million: 971401
// 10 million: 1236401
// 10 million: 1443801
// 10 million: 1728000
// 10 million: 1944000
// 10 million: 2160000
// 10 million: 2160000
// 10 million: 2160000
// 10 million: 2390400
// 10 million: 2592000
// 10 million: 2592000
// 10 million: 2649600
// 10 million: 2952000
// 10 million: 3139200
// 10 million: 3443001
// 10 million: 3628800
// 10 million: 3888000
// 10 million: 4118400
// 10 million: 4320000
// 10 million: 4320000
// 10 million: 4320000
// 10 million: 4606801
// 10 million: 4809600
// 10 million: 5079201
// 10 million: 5184000
// 10 million: 5184000
// 10 million: 5299200
// 10 million: 5616000
// 10 million: 5788800
// 10 million: 6050001
// 10 million: 6278400
// 10 million: 6480000
// 10 million: 6480000
// 10 million: 6534001
// 10 million: 6784601
// 10 million: 7006401
// 10 million: 7285801
// 10 million: 7459200
// 10 million: 7776000
// 10 million: 7776000
// 10 million: 7776000
// 10 million: 7977201
// 10 million: 8251200
// 10 million: 8449601
// 10 million: 8640000 650,000,000