import java.util.*;

public class generateShips {
    final int[] SHIPS = new int[]{2, 3, 3, 4, 5};
    int index = 0;
    public List<int[]> generate() {
        Random rand = new Random();
        List<int[]> ships = new ArrayList<>();
        Set<String> occupied = new HashSet<>(); // tracks taken coordinates

        for (int shiplen : SHIPS) {
            boolean notGood = true;
            List<int[]> newShip = null;

            while (notGood) {
                int perpCord = rand.nextInt(10);
                int paralCord = rand.nextInt(10 - shiplen);
                int r = rand.nextInt(2);
                int x = (r == 0) ? paralCord : perpCord;
                int y = (r == 1) ? paralCord : perpCord;

                newShip = generatePath(x, y, r, shiplen, index);


                // check overlap
                boolean overlap = false;
                for (int[] coord : newShip) {
                    String key = coord[0] + "," + coord[1];
                    if (occupied.contains(key)) {
                        overlap = true;
                        break;
                    }
                }

                if (!overlap) {
                    notGood = false;
                    // mark as occupied
                    for (int[] coord : newShip) {
                        occupied.add(coord[0] + "," + coord[1]);
                    }
                }
            }
            index++;
            ships.addAll(newShip);
        }
        return ships;
    }

    public void printShips(List<int[]> ships) {
        for (int[] arr : ships) {
            System.out.println(Arrays.toString(arr));
        }
    }

    public static List<int[]> generatePath(int startX, int startY, int direction, int length, int index) {
        List<int[]> path = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            int x = startX;
            int y = startY;

            if (direction == 0) { // horizontal
                x += i;
            } else if (direction == 1) { // vertical
                y += i;
            }

            path.add(new int[]{x, y, length, index});
        }
        return path;
    }
}
