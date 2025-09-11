import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class csvReader {
    private BufferedReader reader;

    public csvReader(String filePath) {
        try {
            reader = new BufferedReader(new FileReader(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            reader = null;
        }
    }

    public int[] line() {
        if (reader == null) return null;

        try {
            String row = reader.readLine();
            if (row == null) return null;

            String[] parts = row.split(",");
            return new int[] {
                    Integer.parseInt(parts[0].trim()),
                    Integer.parseInt(parts[1].trim())
            };
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void close() {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
