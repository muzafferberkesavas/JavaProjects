import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Class that is responsible for adding ducks and handling Key events for Level 3.
 */
public class Level3 extends Level {
    public Level3(Stage window, int numOfDucks) {
        super(numOfDucks, window);

        Text levelText = new Text("Level 3/6");
        levelText.setFont(Font.font("Arial", FontWeight.BOLD, DuckHunt.SCALE * 8)); // Set the font and size as desired
        levelText.setTranslateY(DuckHunt.SCALE * 8);
        levelText.setTranslateX(DuckHunt.WIDTH * 0.42);
        levelText.setFill(Color.ORANGE);

        // Create the ducks to add them to the level scene
        Duck duck1 = new HorizontalDuck(100 * DuckHunt.SCALE, 16.6 * DuckHunt.SCALE, 3, 0, "black");
        Duck duck2 = new HorizontalDuck(66 * DuckHunt.SCALE, 66 * DuckHunt.SCALE, -3, 0, "red");

        // Add them to the duck list
        getDuckList().add(duck1);
        getDuckList().add(duck2);

        // Add their hitboxes to the level scene
        getRoot().getChildren().add(0, duck1.getHitbox());
        getRoot().getChildren().add(0, duck2.getHitbox());
        getRoot().getChildren().add(levelText);

        // Handle events
        getLevel().setOnKeyPressed(event1 -> {
            if (isEnableEnter()) {
                if (isLevelPassed()) {
                    if (event1.getCode() == KeyCode.ENTER) {
                        SoundPlayer.stopSound(SoundPlayer.levelComplete);
                        window.setScene(new Level4(window, 3).getLevel());
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