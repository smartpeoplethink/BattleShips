public abstract class player {

    stats GameStats = new stats();
    public abstract int[] Guess();

    public void result(int[] guess, int hit) {
        if (hit != -1){
            GameStats.hitsMade++;
            GameStats.hitsList.add(new int[]{guess[0], guess[1], hit});
        }else{
            GameStats.missesMade++;
        }
        GameStats.roundsPlayed++;

        GameStats.guessesList.add(new int[]{guess[0], guess[1], hit, GameStats.guessesList.size()});


        
    }


}
