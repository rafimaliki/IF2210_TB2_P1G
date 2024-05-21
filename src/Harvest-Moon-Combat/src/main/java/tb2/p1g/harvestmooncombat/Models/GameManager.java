package tb2.p1g.harvestmooncombat.Models;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class GameManager {
    private boolean isRunning;
    public static int currentPlayerIdx;
    private SeranganBeruang seranganBeruang;
    private Thread seranganThread;

    public static List<Player> players = new ArrayList<Player>();

    public GameManager() {
        this.isRunning = false;
        currentPlayerIdx = 0;
        this.seranganBeruang = null;
    }

    public void startGame(){
        isRunning = true;
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        players.add(player1);
        players.add(player2);
    }

    public void endGame(){
        isRunning = false;
    }

    public void nextTurn(){
        currentPlayerIdx = (currentPlayerIdx + 1) % 2;
        Player player1 = players.get(0);
        Player player2 = players.get(1);
        player1.tumbuhkanTanaman();
        player2.tumbuhkanTanaman();

        // chance of bear attack
        Random random = new Random();
        int chance = random.nextInt(5);
        if (chance == 0){
            seranganBeruang();
        }
    }

    public void seranganBeruang() {
        seranganThread = new Thread(new Runnable() {
            @Override
            public void run() {
                SeranganBeruang seranganBeruang = new SeranganBeruang();
                seranganBeruang.generateAttackArea();
                seranganBeruang.performAttack(players.get(currentPlayerIdx));
            }
        });

        seranganThread.start();
    }


}