import java.util.ArrayList;
import java.util.List;

public class ShipConfigMapper {
    private static final int[] BASES = {
        10, 6, 10, 7, 10, 8, 10, 8, 10, 9, 2, 2, 2, 2, 2
    };

    private static final int[]LENGTHS = {5,4,3,3,2};
    // decode index → config values
    public static int[] decode(long index) {
        int[] values = new int[BASES.length];
        for (int i = BASES.length - 1; i >= 0; i--) {
            values[i] = (int)(index % BASES[i]);
            index /= BASES[i];
        }
        return values;
    }

    public static List<int[]> alterData(int[] data){
        List<int[]> res = new ArrayList<>();
        
        for (int i = 0; i <5; i++){
            res.addAll(generateShips.generatePath(
                data[i+10] == 1 ? data[i] : data[i+1],
                data[i+10] == 1 ? data[i] : data[i+1],
                data[i+10], LENGTHS[i]));
        }
        
        return res;
    }
    // encode config → index
    public static long encode(int[] values) {
        long index = 0;
        for (int i = 0; i < BASES.length; i++) {
            index = index * BASES[i] + values[i];
        }
        return index;
    }

    public static void main(String[] args) {
        // Example: take any number
        long idx = 1_620_000_000L;  // from your run

        // Decode into configuration
        int[] config = decode(idx);
        System.out.println("Decoded config for " + idx + ":");
        for (int i = 0; i < config.length; i++) {
            System.out.print(config[i] + " ");
        }
        System.out.println();

        // Encode back → should match idx
        System.out.println("Re-encoded = " + encode(config));
    }
}
