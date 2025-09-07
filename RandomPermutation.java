import java.util.Random;

public class RandomPermutation {

    static final long N = 77_414_400_000L;

    // gcd helper
    private static long gcd(long a, long b) {
        while (b != 0) {
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    // find a random coprime multiplier
    public static long findCoprime(Random rng) {
        long a;
        do {
            a = 2 + Math.floorMod(rng.nextLong(), N - 2);
        } while (gcd(a, N) != 1);
        return a;
    }

    // permutation function, guaranteed non-negative
    public static long permute(long i, long a, long b) {
        return Math.floorMod(a * i + b, N);
    }

    public static void main(String[] args) {
        Random rng = new Random(12345);
        long a = findCoprime(rng);
        long b = Math.floorMod(rng.nextLong(), N);

        System.out.println("Using a=" + a + " b=" + b);

        for (long i = 0; i < 10; i++) {
            long val = permute(i, a, b);
            System.out.println("i=" + i + " -> perm=" + val);
        }
    }
}
