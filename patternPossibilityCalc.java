import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class patternPossibilityCalc extends player {
    public static void main(String[] args) {
        //For testing purposes
        patternPossibilityCalc poss = new patternPossibilityCalc();
        poss.result(new int[]{4,4}, -1);
        poss.result(new int[]{5,5}, -1);
        poss.result(new int[]{3,3}, -1);
        poss.result(new int[]{6,4}, 4);
        poss.result(new int[]{6,2}, 4);
        double[][] board = poss.iterateSquares();
        printGrid(board);
        int[] coord = poss.maxUnguessed(board);
//        System.out.print(coord[0]);
//        System.out.print("  ");
//        System.out.println(coord[1]);// 7, 8

    }
    public static void printGrid(double[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.printf("%.4f ", grid[j][i]); // 2 decimal places
            }
            System.out.println();
        }
    }

    csvReader file = new csvReader("lines.csv");

//    public int[] main(){
//
//        int[] res = maxUnguessed(iterateSquares());
//
////        System.out.println("Result: ");
////        System.out.println(res[0]);
////        System.out.println(res[1]);
////        System.out.println("End");
//        return res;
//    }
    public double[][] iterateSquares(){
        squarePossibilities[] ships = new squarePossibilities[]{
                new squarePossibilities(2,0),
                new squarePossibilities(3,1),
                new squarePossibilities(3,2),
                new squarePossibilities(4,3),
                new squarePossibilities(5,4)
        };
        //-1 miss; 0: small ship (identified); 1: ... 4: large ship (identified); 5: no guess made
        //the first loop takes a list <int> of all the coords of past guesses and makes an array of
        //the board with the above index values.
//        In addition it classifies the ships: identified; unidentified; or comlete
        int[][] board = new int[10][10];
        List<squarePossibilities>[][] allSquares = new ArrayList[10][10];
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(board[i], NOGUESSMADE); //We assume no guess has been made until we check in next section
        }

        List<squarePossibilities> unidentified = new java.util.ArrayList<>();  // this is for ships where none of it has been hit
        List<squarePossibilities> identified = new java.util.ArrayList<>(); //this is for ships that have been partly sunk
        List<squarePossibilities> complete = new java.util.ArrayList<>(); //this is for sunk ships
        for (int[]guess : GameStats.guessesList){//makes list and counts identified squares
            board[guess[0]][guess[1]] = guess[2];
            allSquares[guess[0]][guess[1]] = new ArrayList<>();
            if (guess[2] != MISS && guess[2] != NOGUESSMADE){ //this will be true if the guess is a hit
                ships[guess[2]].identifiedSquares++;

            }
        }

        for (int row = 0; row < allSquares.length; row++) {//initiate
            for (int col = 0; col < allSquares[row].length; col++) {
                allSquares[col][row] = new ArrayList<>();
            }
        }


        for (squarePossibilities ship : ships){//adding each ship to the respective lists.
            ship.calculated = false;
            ship.complete = false;
            ship.quantity = 0;
            if (ship.identifiedSquares == ship.length){
                complete.add(ship);//adding each ship to the respective lists.
                ship.complete = true;
            }else if (ship.identifiedSquares > 0){
                identified.add(ship);

            }else{
                unidentified.add(ship);
            }
        }

        for (int row = 0; row < allSquares.length; row++) {
            for (int col = 0; col < allSquares[row].length; col++) {
                if (board[col][row] == NOGUESSMADE) { //No guess
                    for (squarePossibilities ship: unidentified){//Only iterate through unidentified ships here

                        boolean allUnguessedRow = true;
                        boolean allUnguessedCol = true;
                        for (int OffSet = 1; OffSet <ship.length; OffSet++){
                            if ((row+ OffSet)> 9 || (board[col][row + OffSet] != NOGUESSMADE)) {
                                allUnguessedRow = false;
                            }
                            if ((col+OffSet > 9) || board[col + OffSet][row] != NOGUESSMADE) {
                                allUnguessedCol = false;
                            }
                        }
                        if (allUnguessedRow){
                            ship.quantity++;
                            for (int rowOffSet = 0; rowOffSet<ship.length; rowOffSet++){
                                allSquares[col][row+rowOffSet].add(ship);
                            }
                        }
                        if (allUnguessedCol){
                            ship.quantity++;
                            for (int colOffSet = 0; colOffSet<ship.length; colOffSet++){
                                allSquares[col+colOffSet][row].add(ship);
                            }
                        }
                    }

                }else if (board[col][row] != MISS){//Hit



                    int shipIndex = board[col][row];
                    squarePossibilities  ship = ships[shipIndex];
                    if (!ship.complete  && !ship.calculated){
                        ship.calculated = true;
//                        System.out.println("Calculating hit...1");
//                        System.out.println(ship.ship);
                        boolean allUnguessedRow;
                        boolean allUnguessedCol;





                        for (int OffSet = -(ship.length-1); OffSet <= 0; OffSet++){

//                            System.out.println("Calculating hit...2");
//                            System.out.println(ship.ship);
                            int rowAmount = 0;
                            int colAmount = 0;
                            allUnguessedRow = true;
                            allUnguessedCol = true;
                            for (int shipVal = 0; shipVal < ship.length; shipVal++){
                                int rowVal = row+OffSet+shipVal;
                                int colVal = col+OffSet+shipVal;


                                //ROW
                                if ((rowVal > 9 ||rowVal < 0) ||
                                        (board[col][rowVal] != NOGUESSMADE && board[col][rowVal] != ship.ship)){
                                    allUnguessedRow = false;
                                }//if it is out of range
                                else if (board[col][rowVal]==ship.ship) {
                                    // or if it is not a no guess (it must be a hit to get here
                                    rowAmount++;
//                                    System.out.println("Calculating hit...3");
//                                    System.out.println(ship.ship);
                                }

                                //COL
                                if ((colVal > 9 ||colVal < 0) ||
                                        (board[colVal][row] != NOGUESSMADE && board[colVal][row] != ship.ship)){
                                    allUnguessedCol = false;
                                }//if it is out of range
                                else if (board[colVal][row]==ship.ship) {
                                    // or if it is not a no guess (it must be a hit to get here
                                    colAmount++;
                                }
                            }
//                            System.out.println("Vals:");
//                            System.out.println(colAmount);
//                            System.out.println(rowAmount);
//                            System.out.println(ship.identifiedSquares);
                            if (allUnguessedRow && rowAmount == ship.identifiedSquares){
                                ship.quantity++;
//                                System.out.println("Calculating hit...4");
//                                System.out.println(ship.ship);
                                for (int rowOffSet = 0; rowOffSet<ship.length; rowOffSet++){
                                    allSquares[col][row+rowOffSet+OffSet].add(ship);
                                }
                            }

                            if (allUnguessedCol && colAmount == ship.identifiedSquares){

                                ship.quantity++;
                                for (int colOffSet = 0; colOffSet<ship.length; colOffSet++){
                                    allSquares[col+colOffSet+OffSet][row].add(ship);
                                }
                            }

                        }



                    }
                }//Otherwise it is a miss
            }
        }


        //Use array <>[][] list of each possibility and or them together per square
        double[][] boardResults = new double[10][10];
        for (int row = 0; row< allSquares.length; row++) {
            for (int col = 0; col< allSquares[row].length; col++) {
                List<squarePossibilities> square = allSquares[col][row];
                double[] possibilities = new double[square.size()];
                for (int i = 0; i<square.size(); i++){

                    possibilities[i] = ((double)square.get(i).length)/((double)square.get(i).quantity);
                }
                double prob;
                if (mainPanel.additionMethod){
                    prob = Probability.orADDITION(possibilities);
                }else{
                    prob = Probability.orADDITION(possibilities);
                }
                if ((row+col)%2 == 0){
                    prob*=mainPanel.evenSquareIncrease;
                }
                boardResults[col][row] = prob;
            }
        }


        return boardResults;
    }


    @Override
    public int[] Guess() {
        return maxUnguessed(iterateSquares());
    }
    private int[] maxUnguessed(double[][] probabilities){
        boolean[][] guessed = new boolean[10][10];
        for (int[]guess : GameStats.guessesList) {//makes list and counts identified squares

            guessed[guess[0]][guess[1]] = true;
        }
        double maxProbability = 0.0;
        int bestRow = 0;
        int bestCol = 0;
        for (int row = 0; row <= 9; row++){
            for (int col = 0; col <= 9; col++){
                if (!guessed[col][row]){
//                    System.out.print(probabilities[bestRow][bestCol]);
//                    System.out.print(" compared with ");
//                    System.out.println(maxProbability);
                    if (probabilities[col][row] > maxProbability){

                        bestRow = row;
                        bestCol = col;
                        maxProbability = probabilities[bestCol][bestRow];
                    }
                }

            }
        }
        return new int[]{bestCol, bestRow};
    }

//    public squarePossibilities[] getShips() {
//        return ships;
//    }

//    public void setShips(squarePossibilities[] ships) {
//        this.ships = ships;
//    }
}
