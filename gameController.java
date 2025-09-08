import java.util.List;



public class gameController {

    stats GameStats = new stats();
    public boolean playRound(player player, List<int[]> ships){
        boolean hit = false;
        int[] res = player.guess();
        if (functions.inList(res[0], res[1], ships)){
            hit = true;
        }
        player.result(res, hit);
        if (hit){
            GameStats.hitsMade++;
        }else{
            GameStats.missesMade++;
        }
        GameStats.roundsPlayed++;

        System.out.println(GameStats.hitsMade);
        return hit;
    }
    public void playGame(player player, List<int[]> ships){
        while (GameStats.hitsMade< 14){ //17 = 5+4+3+3+2
            playRound(player, ships);
        }

        System.out.println("Guesses Made: ");
        System.out.print(GameStats.roundsPlayed);


        System.out.println("Hits Made:");
        System.out.print(GameStats.hitsMade);

    }

}


