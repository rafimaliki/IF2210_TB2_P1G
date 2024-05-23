package tb2.p1g.harvestmooncombat.Models;

import java.sql.SQLOutput;
import java.util.Random;

import javafx.scene.layout.Pane;
import tb2.p1g.harvestmooncombat.Controllers.GameScreenController;


public class SeranganBeruang implements Runnable {
    private int countdown;
    private static final int rows = 4;
    private static final int cols = 5;
    private static int startRow;
    private static int startCol;
    private static int endRow;
    private static int endCol;
    private volatile boolean bearAttack = false;
    private Pane beruangBox;
    private volatile boolean readyAttack = false;



    public SeranganBeruang(Pane beruangBox) {
        this.beruangBox = beruangBox;
        generateAttackArea();
        Random random = new Random();
        countdown = random.nextInt(10,15); // Menghasilkan angka antara 30 hingga 60 deti
    }

    @Override
    public void run() {
        bearAttack = true;
        this.beruangBox.setVisible(true);
        int startX = 11;
        int startY = 87;

        int beruangBoxX = (int) beruangBox.getLayoutX();
        int beruangBoxY = (int) beruangBox.getLayoutY();

        int startRow = SeranganBeruang.getStartRow();
        int startCol = SeranganBeruang.getStartCol();
        int endRow = SeranganBeruang.getEndRow();
        int endCol = SeranganBeruang.getEndCol();

        boolean isHorizontal;

        if (endRow-startRow == 1){
            isHorizontal = true;
        } else {
            isHorizontal = false;
        }

        if (isHorizontal){
            beruangBox.setPrefWidth(237);
            beruangBox.setPrefHeight(201);
        } else {
            beruangBox.setPrefWidth(160);
            beruangBox.setPrefHeight(291);
        }
        System.out.println("start col, start row: " + startCol + ", " + startRow);
        beruangBox.setLayoutX(startX + startCol * 80-5);
        beruangBox.setLayoutY(startY + startRow * 100-5);

        while (countdown > 0) {
            System.out.println("Countdown: " + countdown);
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
        this.beruangBox.setVisible(false);
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
//
//        GameScreenController.getBeruangBox().setVisible(false);
            this.beruangBox.setVisible(false);




    }

    public boolean checkTrapCard(Player player){
        Ladang_Logic ladang = player.getLadang();
        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                Kartu kartu = ladang.getKartu(i, j);
                if (kartu != null) {

                    if (kartu.getEfekItem().contains("TRAP")) {
                        System.out.println("Serangan beruang terhenti oleh trap card" + i + " " + j);
                        //simpan kartu beruang di ladang player
                        //cari ladang kosong di area trap jika tidak ada iterasi seluruh ladang
                        boolean flag = false;
                        for (int k = startRow; k <= endRow; k++) {
                            for (int l = startCol; l <= endCol; l++) {
                                if (ladang.getKartu(k, l) == null) {
                                    System.out.println("BERUANG MENINGGAL " + k + " " + l);
                                    ladang.getLadang().get(k).set(l, new KartuHewan("BERUANG"));
                                    flag = true;
                                    return true;
                                }
                            }
                        }
                        if (!flag){
                            for (int k = 0; k < ladang.getLadang().size(); k++) {
                                for (int l = 0; l < ladang.getLadang().get(k).size(); l++) {
                                    if (ladang.getKartu(k, l) == null) {
                                        System.out.println("BERUANG MENINGGAL " + k + " " + l);
                                        ladang.getLadang().get(k).set(l, new KartuHewan("BERUANG"));
                                        break;
                                    }
                                }
                            }
                        }
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

    public static int getEndCol() {
        return SeranganBeruang.endCol;
    }

    public static int getEndRow() {
        return SeranganBeruang.endRow;
    }

    public static int getStartCol() {
        return SeranganBeruang.startCol;
    }

    public static int getStartRow() {
        return SeranganBeruang.startRow;
    }

    public boolean isBearAttack() {
        return bearAttack;
    }

    public boolean isReadyAttack() {
        return readyAttack;
    }

    public void setReadyAttack(boolean readyAttack) {
        this.readyAttack = readyAttack;
    }

    public void setBeruangBox(Pane beruangBox) {
        this.beruangBox = beruangBox;
    }
}
