import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Class that is responsible for adding ducks and handling Key events for Level 1.
 */
public class Level1 extends Level{
    public Level1(Stage window, int numOfDucks) {
        // Calling the constructor of super class
        super(numOfDucks, window);

        // Setting level text
        Text levelText = new Text("Level 1/6");
        levelText.setFont(Font.font("Arial", FontWeight.BOLD, DuckHunt.SCALE * 8)); // Set the font and size as desired
        levelText.setTranslateY(DuckHunt.SCALE * 8);
        levelText.setTranslateX(DuckHunt.WIDTH * 0.42);
        levelText.setFill(Color.ORANGE);

        // Creating the duck
        Duck duck = new HorizontalDuck(66 * DuckHunt.SCALE, 33 * DuckHunt.SCALE, 2, 0, "blue");

        // Adding it to the duck list that is contained in super class
        getDuckList().add(duck);

        // Adding the hitbox of the duck to the scene
        getRoot().getChildren().add(0, duck.getHitbox());
        getRoot().getChildren().add(levelText);

        // Handling key events when level is passed or failed.
        getLevel().setOnKeyPressed(event1 -> {
            if (isEnableEnter()) {
                if (isLevelPassed()) {
                    // Goes to next level
                    if (event1.getCode() == KeyCode.ENTER) {
                        SoundPlayer.stopSound(SoundPlayer.levelComplete);
                        window.setScene(new Level2(window, 1).getLevel());
                    }
                } else {
                    // If level is failed
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