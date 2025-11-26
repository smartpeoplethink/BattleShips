import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DataLogger {

    private PrintWriter writer;

    // Call this once at program startup
    public void initLogger(String filename) throws IOException {
        writer = new PrintWriter(new FileWriter(filename, false)); 
    }

    // Call this periodically with your integer
    public void logInt(long value) {
        writer.print(value + ",");
        //writer.flush(); // ensure it's written immediately
    }
    public void newLine(){
        
        writer.println("");
        writer.print(mainPanel.evenSquareIncrease);
        writer.print(",");
        //writer.flush();
    }

    // Call at shutdown to close file
    public void closeLogger() {
        writer.flush();
        if (writer != null) {
            writer.close();
        }
    }

    // Example usage
    public static void main(String[] args) throws Exception {
        DataLogger logger = new DataLogger();
        logger.initLogger("output.txt");

        logger.logInt(5);
        logger.logInt(42);
        logger.logInt(9);

        logger.closeLogger();
    }
}
