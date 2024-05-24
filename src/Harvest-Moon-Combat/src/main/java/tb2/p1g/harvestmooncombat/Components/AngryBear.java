package tb2.p1g.harvestmooncombat.Components;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Random;

public class AngryBear {
    private static int bearCount = 0;
    public static void addRandomBear(AnchorPane root) {

        Pane bear = new Pane();
        bear.getStyleClass().add("bear");

        int size;
        //random size between values with random
        Random random = new Random();
        size = random.nextInt(100) + 25;
        bear.setPrefSize(size, size);

        // set x pos
        bear.setLayoutX(random.nextInt((int) root.getWidth() - size));
        bear.setLayoutY(random.nextInt((int) root.getHeight() - size));

        // bear random rotation
        bear.setRotate(random.nextInt(360));

        root.getChildren().add(bear);
        AngryBear.movePaneInSnakePattern(bear, root);

        bearCount++;
    }

    private static void movePaneInSnakePattern(Pane bear, AnchorPane root) {
        Random random = new Random();
        final int[] direction = {0};
        final boolean[] angry = {false};

        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(0.01), event -> {

                bear.setRotate(bear.getRotate() + 5);

                if (random.nextDouble() < 0.01) {
                    angry[0] = !angry[0];
                    bear.getStyleClass().removeAll("bear", "angrybear");
                    bear.getStyleClass().add(angry[0] ? "angrybear" : "bear");

//                    if (random.nextDouble() < 0.1 && bearCount < 100) {
//                        AngryBear.addRandomBear(root);
//                        System.out.println("Bear count: " + bearCount);
//                    }
                }

                double x = bear.getLayoutX();
                double y = bear.getLayoutY();

                // Move in the current direction
                switch (direction[0]) {
                    case 0: // right
                        x += 5;
                        break;
                    case 1: // down
                        y += 5;
                        break;
                    case 2: // left
                        x -= 5;
                        break;
                    case 3: // up
                        y -= 5;
                        break;
                }

                if (x < 0) x = 0;
                if (y < 0) y = 0;
                if (x > root.getWidth() - bear.getWidth()) x = root.getWidth() - bear.getWidth();
                if (y > root.getHeight() - bear.getHeight()) y = root.getHeight() - bear.getHeight();

                bear.setLayoutX(x);
                bear.setLayoutY(y);

                if (random.nextDouble() < 0.1) { // 10% chance to change direction
                    direction[0] = (direction[0] + 1 + random.nextInt(3)) % 4;
                }
            })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
