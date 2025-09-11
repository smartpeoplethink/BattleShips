import java.io.IOException;
import java.util.List;



public class gameController {

    stats GameStats;
    private int playRound(player player, List<int[]> ships){
        int hit;
        int[] res = player.Guess();
        hit = functions.inList(res[0], res[1], ships);
        player.result(res, hit);

        System.out.println("Guesses Made: ");
        System.out.println(GameStats.roundsPlayed);
        if (GameStats.roundsPlayed == 10 || GameStats.roundsPlayed == 15){
            game.screenSetUp(ships, player.GameStats.guessesList);
        }
        System.out.println();
        return hit;
    }
    public void playGame(player player, List<int[]> ships){
        GameStats = player.GameStats;
        while (GameStats.hitsMade< 17){ //17 = 5+4+3+3+2
            playRound(player, ships);
        }

        System.out.println("Guesses Made: ");
        System.out.print(GameStats.roundsPlayed);


        System.out.println("Hits Made:");
        System.out.print(GameStats.hitsMade);

    }

}


