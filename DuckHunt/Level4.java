import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Class that is responsible for adding ducks and handling Key events for Level 4.
 */
public class Level4 extends Level{
    public Level4(Stage window, int numOfDucks) {
        super(numOfDucks, window);

        Text levelText = new Text("Level 4/6");
        levelText.setFont(Font.font("Arial", FontWeight.BOLD, DuckHunt.SCALE * 8)); // Set the font and size as desired
        levelText.setTranslateY(DuckHunt.SCALE * 8);
        levelText.setTranslateX(DuckHunt.WIDTH * 0.42);
        levelText.setFill(Color.ORANGE);

        // Creating two diagonal and one horizontal duck
        Duck duck1 = new DiagonalDuck(DuckHunt.WIDTH * 0.2, DuckHunt.HEIGHT * 0.8, 2, -2, "black");
        Duck duck2 = new DiagonalDuck(DuckHunt.WIDTH * 0.8, DuckHunt.HEIGHT * 0.2, -2, 2, "blue");
        Duck duck3 = new HorizontalDuck(DuckHunt.WIDTH * 0.3, DuckHunt.HEIGHT * 0.1, -3, 0, "red");

        // Adding them to the duck list
        getDuckList().add(duck1);
        getDuckList().add(duck2);
        getDuckList().add(duck3);

        // Adding the hitboxes
        getRoot().getChildren().add(0, duck1.getHitbox());
        getRoot().getChildren().add(0, duck2.getHitbox());
        getRoot().getChildren().add(0, duck3.getHitbox());
        getRoot().getChildren().add(levelText);

        // Handle events
        getLevel().setOnKeyPressed(event1 -> {
            if (isEnableEnter()) {
                if (isLevelPassed()) {
                    if (event1.getCode() == KeyCode.ENTER) {
                        SoundPlayer.stopSound(SoundPlayer.levelComplete);
                        window.setScene(new Level5(window, 4).getLevel());
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
