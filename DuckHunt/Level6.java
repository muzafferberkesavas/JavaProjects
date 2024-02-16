import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Level6 extends Level{
    public Level6(Stage window, int numOfDucks) {
        super(numOfDucks, window);

        Text levelText = new Text("Level 6/6");
        levelText.setFont(Font.font("Arial", FontWeight.BOLD, DuckHunt.SCALE * 8)); // Set the font and size as desired
        levelText.setTranslateY(DuckHunt.SCALE * 8);
        levelText.setTranslateX(DuckHunt.WIDTH * 0.42);
        levelText.setFill(Color.ORANGE);

        // Add 5 ducks
        Duck duck1 = new DiagonalDuck(DuckHunt.WIDTH * 0.2, DuckHunt.HEIGHT * 0.8, 2, -1, "black");
        Duck duck2 = new DiagonalDuck(DuckHunt.WIDTH * 0.8, DuckHunt.HEIGHT * 0.2, -2, 2, "black");
        Duck duck3 = new HorizontalDuck(DuckHunt.WIDTH * 0.3, DuckHunt.HEIGHT * 0.3, -4, 0, "blue");
        Duck duck4 = new HorizontalDuck(DuckHunt.WIDTH * 0.5, DuckHunt.HEIGHT * 0.5, 2.5, 0, "blue");
        Duck duck5 = new DiagonalDuck(DuckHunt.WIDTH * 0.5, DuckHunt.HEIGHT * 0.5, 2.5, -2.5, "red");

        // Add to list and their hitbox
        getDuckList().add(duck1);
        getDuckList().add(duck2);
        getDuckList().add(duck3);
        getDuckList().add(duck4);
        getDuckList().add(duck5);

        getRoot().getChildren().add(0, duck1.getHitbox());
        getRoot().getChildren().add(0, duck2.getHitbox());
        getRoot().getChildren().add(0, duck3.getHitbox());
        getRoot().getChildren().add(0, duck4.getHitbox());
        getRoot().getChildren().add(0, duck5.getHitbox());
        getRoot().getChildren().add(levelText);

        // Game is completed or game is over
        getLevel().setOnKeyPressed(event1 -> {
            if (isEnableEnter()) {
                if (isLevelPassed()) {
                    if (event1.getCode() == KeyCode.ENTER) {
                        SoundPlayer.stopSound(SoundPlayer.gameComplete);
                        window.setScene(new Level1(window, 1).getLevel());
                    } else if (event1.getCode() == KeyCode.ESCAPE) {
                        SoundPlayer.stopSound(SoundPlayer.gameComplete);
                        returnToMainMenu();
                    }
                } else {
                    if (event1.getCode() == KeyCode.ENTER) {
                        playAgain();
                    } else if (event1.getCode() == KeyCode.ESCAPE) {
                        returnToMainMenu();
                    }
                }
            }
        });
    }

}