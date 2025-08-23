import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class gridDisplay extends JPanel {
    private List<int[]> greyCells;  // list of grey cell coordinates
    private int[][] numberArray;    // numbers

    public gridDisplay(List<int[]> greyCells, int[][] numberArray) {
        this.greyCells = greyCells;
        this.numberArray = numberArray;
        setPreferredSize(new Dimension(500, 500)); // grid size
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int rows = numberArray.length;
        int cols = numberArray[0].length;
        int cellWidth = getWidth() / cols;
        int cellHeight = getHeight() / rows;
        int circleDiameter = Math.min(cellWidth, cellHeight) / 2; // circle size

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int xCell = c * cellWidth;
                int yCell = r * cellHeight;

                // Default = blue
                g.setColor(Color.BLUE);

                // If this cell is in greyCells → make it grey
                for (int[] coord : greyCells) {
                    if (coord[0] == c && coord[1] == r) {
                        g.setColor(Color.GRAY);
                        break;
                    }
                }
                g.fillRect(xCell, yCell, cellWidth, cellHeight);

                // Draw grid border
                g.setColor(Color.BLACK);
                g.drawRect(xCell, yCell, cellWidth, cellHeight);

                // Only draw circle & number if number != 0
                if (numberArray[r][c] != 0) {
                    // Draw white circle in center of cell
                    int circleX = xCell + (cellWidth - circleDiameter) / 2;
                    int circleY = yCell + (cellHeight - circleDiameter) / 2;
                    g.setColor(Color.WHITE);
                    g.fillOval(circleX, circleY, circleDiameter, circleDiameter);

                    g.setColor(Color.BLACK);
                    g.drawOval(circleX, circleY, circleDiameter, circleDiameter);

                    // Draw number centered inside circle
                    String text = String.valueOf(numberArray[r][c]);
                    FontMetrics fm = g.getFontMetrics();
                    int textWidth = fm.stringWidth(text);
                    int textHeight = fm.getAscent();
                    int textX = circleX + (circleDiameter - textWidth) / 2;
                    int textY = circleY + (circleDiameter + textHeight) / 2 - 3;
                    g.drawString(text, textX, textY);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] numbers = new int[10][10];
        // Example: fill some test numbers
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                numbers[r][c] = (r + c) % 4 == 0 ? (r * 10 + c) : 0; // some cells numbered, some zero
            }
        }

        // Example: grey cells list
        List<int[]> greys = new ArrayList<>();
        greys.add(new int[]{1, 1});
        greys.add(new int[]{2, 3});
        greys.add(new int[]{4, 5});
        greys.add(new int[]{7, 8});

        
    }
}
