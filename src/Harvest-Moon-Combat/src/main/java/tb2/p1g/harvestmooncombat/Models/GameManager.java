package tb2.p1g.harvestmooncombat.Models;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class GameManager {
    private boolean isRunning;
    public static int currentPlayerIdx;
    private SeranganBeruang seranganBeruang;
    private Thread seranganThread;
    private  Ladang_Logic currentLadang;
    private  DeckAktif currentDeck;

    private static GameManager instance;

    public static List<Player> players = new ArrayList<Player>();

    private GameManager() {
        this.isRunning = false;
        currentPlayerIdx = 0;
        this.seranganBeruang = null;

    }
    public static synchronized GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }
    public Player getCurrentPlayer(){
        return players.get(currentPlayerIdx);
    }
    public  void inverseLadang(){
        setLadang(players.get((currentPlayerIdx + 1) % 2).getLadang());
    }
    public void setDeckAktif(DeckAktif deckAktif){
        currentDeck = deckAktif;
    }
    public  DeckAktif getDeckAktif(){
        return currentDeck;
    }
    public  void setLadang(Ladang_Logic ladan){
        currentLadang = ladan;
    }
    public Ladang_Logic getCurrentLadang(){
        return currentLadang;
    }



    public void startGame(){
        isRunning = true;
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        players.add(player1);
        players.add(player2);
        setLadang(getCurrentPlayer().getLadang());
        setDeckAktif(getCurrentPlayer().getDeckAktif());
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
        setLadang(getCurrentPlayer().getLadang());
        setDeckAktif(getCurrentPlayer().getDeckAktif());

        // chance of bear attack
        Random random = new Random();
        int chance = random.nextInt(5);
        if (chance == 0){
            System.out.println("Serangan beruang!");
            seranganBeruang();
        }
    }

    public void seranganBeruang() {
        seranganBeruang = new SeranganBeruang(); // Membuat instans SeranganBeruang
        seranganThread = new Thread(seranganBeruang);
        seranganThread.start();
    }

    public boolean isBearAttackInProgress() {
        if (seranganBeruang != null) {
            return seranganBeruang.isBearAttack();
        }
        System.out.println("Bear attack is not in progress");
        return false;
    }


}