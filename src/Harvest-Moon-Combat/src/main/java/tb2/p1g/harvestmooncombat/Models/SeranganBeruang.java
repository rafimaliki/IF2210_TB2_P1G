package tb2.p1g.harvestmooncombat.Models;

import java.sql.SQLOutput;
import java.util.Random;


public class SeranganBeruang implements Runnable {
    private int countdown;
    private static final int rows = 4;
    private static final int cols = 5;
    private int startRow;
    private int startCol;
    private int endRow;
    private int endCol;
    private volatile boolean bearAttack = false;



    public SeranganBeruang() {
        generateAttackArea();
        Random random = new Random();
        countdown = random.nextInt(10,15); // Menghasilkan angka antara 30 hingga 60 deti
    }

    @Override
    public void run() {
        bearAttack = true;
        while (countdown > 0) {
            System.out.println("Countdown: " + countdown);
            System.out.println(bearAttack);
            countdown--;
            try {
                Thread.sleep(1000); // Tidur selama 1 detik
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Player player = GameManager.getInstance().getCurrentPlayer();
        performAttack(player);
        bearAttack = false;
    }

    public void performAttack(Player player) {
        //Ambil ladang current palyer
        Ladang_Logic ladangPlayer = player.getLadang();

        if(checkTrapCard(player)){
            System.out.println("Serangan beruang terhenti oleh trap card");
            return;
        }

        //Serang aread yang sudah di generate
        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {

                if (ladangPlayer.getKartu(i, j) != null) {
                    System.out.println("Serangan beruang menghancurkan kartu " + ladangPlayer.getKartu(i, j).getNama() + " di posisi " + i + ", " + j);
                    ladangPlayer.getLadang().get(i).set(j, null);
                    ladangPlayer.displayLadang();
                }
                System.out.println("null" + i + " " + j);
            }
        }



    }

    public boolean checkTrapCard(Player player){
        Ladang_Logic ladang = player.getLadang();
        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                Kartu kartu = ladang.getKartu(i, j);
                if (kartu != null) {
                    if (kartu.getNama().equals("TRAP")){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void generateAttackArea() {
        Random random = new Random();

        // Menentukan ukuran area serangan beruang (2x3 atau 3x2)
        int attackRows = random.nextBoolean() ? 2 : 3;
        int attackCols = (attackRows == 2) ? 3 : 2;

        // Memastikan serangan berada dalam batasan matriks
        int maxRowStart = rows - attackRows;
        int maxColStart = cols - attackCols;

        startRow = random.nextInt(maxRowStart + 1);
        startCol = random.nextInt(maxColStart + 1);

        endRow = startRow + attackRows - 1;
        endCol = startCol + attackCols - 1;

        System.out.println("Bear attack area: " + startRow + ", " + startCol + " to " + endRow + ", " + endCol);
    }

    public int getEndCol() {
        return endCol;
    }

    public int getEndRow() {
        return endRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public int getStartRow() {
        return startRow;
    }

    public boolean isBearAttack() {
        return bearAttack;
    }
}
