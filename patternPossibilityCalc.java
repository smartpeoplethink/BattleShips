public class patternPossibilityCalc extends player {
    squarePossibilities ship0 = new squarePossibilities(2,0);
    squarePossibilities ship1 = new squarePossibilities(3,1);
    squarePossibilities ship2 = new squarePossibilities(3,2);
    squarePossibilities ship3 = new squarePossibilities(4,3);
    squarePossibilities ship4 = new squarePossibilities(5,4);
    csvReader file = new csvReader("lines.csv");
    squarePossibilities[][][] allSquares = new squarePossibilities[10][10][];
    public int[] main(){
        if (GameStats.hitsMade == 0){
            return file.line();
        }
        else{
            System.out.print("FOUND SHIP");
            throw new RuntimeException("Found ship");
        }
    }
    public void iterateSquares(){
        for (int row = 0; row < allSquares.length; row++) {
            for (int col = 0; col < allSquares[row].length; col++) {

            }
        }
    }
    @Override
    public int[] Guess() {
        return main();
    }
    public
}
