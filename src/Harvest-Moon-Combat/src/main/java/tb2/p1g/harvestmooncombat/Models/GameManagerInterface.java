package tb2.p1g.harvestmooncombat.Models;

public interface GameManagerInterface {

    /**
     * Prosedur memulai permainan
     */
    public void startGame();

    /**
     * Prosedur mengakhiri permainan
     */
    public void endGame();

    /**
     * Prosedur mengganti giliran pemain
     */
    public void nextTurn();

    /**
     * Prosedur memeriksa kemenangan pemain
     */
    public void checkWin();

}
