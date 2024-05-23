package tb2.p1g.harvestmooncombat.Models;

import javafx.scene.layout.Pane;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class GameManager {
    private boolean isRunning;
    public static int currentPlayerIdx;
    private SeranganBeruang seranganBeruang;
    private Thread seranganThread;
    private Ladang_Logic currentLadang;
    private DeckAktif currentDeck;
    private boolean isViewLawan;
    private Pane beruangBox;
    private int turnNumber;
    private Player winner;

    private static GameManager instance;

    private List<Player> players = new ArrayList<Player>();

    private GameManager() {
        this.isRunning = false;
        currentPlayerIdx = 0;
        this.seranganBeruang = null;
        this.turnNumber = 0;
        this.winner = null;

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

    public  Player getPlayerOne(){
        return this.players.getFirst();
    }
    public Player getPlayerTwo(){
        return this.players.getLast();
    }

    public void startGame(){
        isRunning = true;
        Toko toko = new Toko();
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        players.add(player1);
        players.add(player2);
        setLadang(getCurrentPlayer().getLadang());
        setDeckAktif(getCurrentPlayer().getDeckAktif());
        isViewLawan = false;
        this.turnNumber = 1;
    }

    public void setViewLawan(){
        isViewLawan = !isViewLawan;
    }
    public boolean getViewLawan(){
        return  isViewLawan;
    }

    public void endGame(){
        isRunning = false;
    }

    public void checkWin(){
        if (this.turnNumber == 20){
            Player winner = null;
            if (players.get(0).getGulden() > players.get(1).getGulden()){
                winner = players.getFirst();
            } else if (players.get(0).getGulden() < players.get(1).getGulden()){
                winner = players.getLast();
            }
            if (winner != null){
                System.out.println("Player " + winner.getNama() + " wins!");
            } else {
                System.out.println("Tidak ada yang menang");
            }
            this.winner = winner;
            this.endGame();
        }
    }

    public void nextTurn(Pane beruangBox){
        this.checkWin();

        if (this.isRunning) {
            currentPlayerIdx = (currentPlayerIdx + 1) % 2;
            Player player1 = players.get(0);
            Player player2 = players.get(1);
            player1.tumbuhkanTanaman();
            player2.tumbuhkanTanaman();
            setLadang(getCurrentPlayer().getLadang());
            setDeckAktif(getCurrentPlayer().getDeckAktif());
            isViewLawan = false;
            this.turnNumber++;
        }

        // chance of bear attack

    }

    public void initBearAttack() {
        Random random = new Random();
        int chance = random.nextInt(5);
        chance = 0;
        if (chance == 0){
            System.out.println("Serangan beruang!");
            seranganBeruang(this.beruangBox);
        }
    }

    public void seranganBeruang(Pane beruangBox) {
        seranganBeruang = new SeranganBeruang(beruangBox); // Membuat instans SeranganBeruang
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

    public void setReadyAttack(boolean readyAttack) {
        if (seranganBeruang != null) {
            seranganBeruang.setReadyAttack(readyAttack);
        }
    }

    public void setBeruangBox(Pane beruangBox) {
        this.beruangBox = beruangBox;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    public List<Player> getPlayers(){
        return players;
    }

    public boolean getIsRunning(){
        return isRunning;
    }
}