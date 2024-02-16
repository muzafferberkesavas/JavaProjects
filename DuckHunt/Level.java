import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

/**
 * Parent class that is responsible for levels.
 */
public class Level {
    public static int completedLevels = 0;
    private int numOfBullets;
    private int hitDucks = 0;
    private final Scene level;
    private final Pane root;
    private boolean enableMouseClick = true;
    private final Stage window;
    private boolean enableEnter = false;
    private boolean levelPassed = false;
    private final List<Duck> duckList = new ArrayList<>();

    /**
     * Constructor of the level. Sets the background, foreground and cursor. Places the texts to the level.
     * @param numOfDucks Number of ducks that are in the level.
     * @param window Window that scene is going to be shown.
     */
    public Level(int numOfDucks, Stage window) {
        this.window = window;
        this.numOfBullets = numOfDucks * 3;

        Text youWin = new Text("YOU WIN!");
        youWin.setFont(Font.font("Arial", FontWeight.BOLD, DuckHunt.SCALE * 15)); // Set the font and size as desired
        youWin.setTranslateY(DuckHunt.HEIGHT * 0.43);
        youWin.setTranslateX(DuckHunt.WIDTH * 0.35);
        youWin.setFill(Color.TRANSPARENT);
        youWin.setMouseTransparent(true);

        Text playNextLevel = new Text("Press ENTER to play next level");
        playNextLevel.setFont(Font.font("Arial", FontWeight.BOLD, DuckHunt.SCALE * 15)); // Set the font and size as desired
        playNextLevel.setTranslateY(DuckHunt.HEIGHT * 0.5);
        playNextLevel.setFill(Color.TRANSPARENT);
        playNextLevel.setTranslateX(DuckHunt.WIDTH * 0.088);
        playNextLevel.setMouseTransparent(true);

        Text ammoLeft = new Text(String.format("Ammo Left: %d", getNumOfBullets()));
        ammoLeft.setFont(Font.font("Arial", FontWeight.BOLD, DuckHunt.SCALE * 8)); // Set the font and size as desired
        ammoLeft.setTranslateY(DuckHunt.SCALE * 8);
        ammoLeft.setTranslateX(DuckHunt.WIDTH * 0.78);
        ammoLeft.setFill(Color.ORANGE);
        ammoLeft.setMouseTransparent(true);

        Text gameOver = new Text("GAME OVER!");
        gameOver.setFont(Font.font("Arial", FontWeight.BOLD, DuckHunt.SCALE * 15)); // Set the font and size as desired
        gameOver.setTranslateY(DuckHunt.HEIGHT * 0.43);
        gameOver.setTranslateX(DuckHunt.WIDTH * 0.35);
        gameOver.setFill(Color.TRANSPARENT);
        gameOver.setMouseTransparent(true);

        Text playAgain = new Text("Press ENTER to play again");
        playAgain.setFont(Font.font("Arial", FontWeight.BOLD, DuckHunt.SCALE * 15)); // Set the font and size as desired
        playAgain.setTranslateY(DuckHunt.HEIGHT * 0.5);
        playAgain.setFill(Color.TRANSPARENT);
        playAgain.setTranslateX(DuckHunt.WIDTH * 0.13);
        playAgain.setMouseTransparent(true);

        Text exit = new Text("Press ESC to exit");
        exit.setFont(Font.font("Arial", FontWeight.BOLD, DuckHunt.SCALE * 15)); // Set the font and size as desired
        exit.setTranslateY(DuckHunt.HEIGHT * 0.57);
        exit.setFill(Color.TRANSPARENT);
        exit.setTranslateX(DuckHunt.WIDTH * 0.26);
        exit.setMouseTransparent(true);

        Text gameComplete = new Text("You have completed the game!");
        gameComplete.setFont(Font.font("Arial", FontWeight.BOLD, DuckHunt.SCALE * 15)); // Set the font and size as desired
        gameComplete.setTranslateY(DuckHunt.HEIGHT * 0.43);
        gameComplete.setTranslateX(DuckHunt.WIDTH * 0.08);
        gameComplete.setFill(Color.TRANSPARENT);
        gameComplete.setMouseTransparent(true);

        root = new Pane();

        level = new Scene(root, DuckHunt.WIDTH, DuckHunt.HEIGHT);

        // Activated when mouse is clicked
        level.setOnMouseClicked(event -> {
            // if mouse click is enabled
            if (enableMouseClick) {
                // Play gunshot sound
                SoundPlayer.playSound(SoundPlayer.gunshot);
                // Decrement the number of bullets
                setNumOfBullets(getNumOfBullets() - 1);
                // Update the ammo left in scene
                ammoLeft.setText(String.format("Ammo Left: %d", getNumOfBullets()));

                // Get the coordinates where mouse was clicked
                double clickX = event.getX();
                double clickY = event.getY();

                /*
                 for each duck in the list, check if coordinates of the mouse click is contained within the hitbox
                 of the duck.
                 */
                for (Duck duck : duckList) {
                    Pane hitbox = duck.getHitbox();
                    // if click is contained in hitbox
                    if (clickX >= hitbox.getTranslateX() && clickX <= hitbox.getTranslateX() + DuckMaker.HITBOX_SIZE &&
                            clickY >= hitbox.getTranslateY() && clickY <= hitbox.getTranslateY() + DuckMaker.HITBOX_SIZE ) {
                        // and duck is not already hit
                        if (!duck.isHit()) {
                            // duck dies and number of hit ducks is incremented
                            incrementDuckHit();
                            duck.setHit(true);
                            duck.die();
                        }
                    }
                }
                // if amount of hit ducks are equal to number of ducks that are on the level
                if (getHitDucks() == numOfDucks) {
                    enableMouseClick = false;       // Mouse is disabled and ENTER is enabled
                    enableEnter = true;
                    setLevelPassed(true);           // Level is passed
                    incrementCompletedLevels();
                    // if every level is completed
                    if (completedLevels == 6) {
                        completedLevels = 0;
                        gameComplete.setFill(Color.ORANGE);
                        playAgain.setFill(Color.ORANGE);
                        exit.setFill(Color.ORANGE);
                        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.75 * 2), event2 -> {
                            playAgain.setFill(Color.ORANGE);
                            exit.setFill(Color.ORANGE);
                        }),
                                new KeyFrame(Duration.seconds(0.75), event2 -> {
                                    playAgain.setFill(Color.TRANSPARENT);
                                    exit.setFill(Color.TRANSPARENT);
                                }));
                        timeline.setCycleCount(Timeline.INDEFINITE);
                        timeline.play();
                        SoundPlayer.playSound(SoundPlayer.gameComplete);
                    }
                    // There are still some levels to play
                    else {
                        youWin.setFill(Color.ORANGE);
                        playNextLevel.setFill(Color.ORANGE);
                        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.75 * 2), event2 -> playNextLevel.setFill(Color.ORANGE)),
                                new KeyFrame(Duration.seconds(0.75), event2 -> playNextLevel.setFill(Color.TRANSPARENT)));
                        timeline.setCycleCount(Timeline.INDEFINITE);
                        timeline.play();
                        SoundPlayer.playSound(SoundPlayer.levelComplete);
                    }
                }
                // Level is failed and game is over
                else if (getNumOfBullets() == 0) {
                    completedLevels = 0;
                    enableMouseClick = false;
                    setEnableEnter(true);
                    gameOver.setFill(Color.ORANGE);
                    playAgain.setFill(Color.ORANGE);
                    exit.setFill(Color.ORANGE);
                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.75 * 2), event3 -> {
                        playAgain.setFill(Color.ORANGE);
                        exit.setFill(Color.ORANGE);
                    }),
                            new KeyFrame(Duration.seconds(0.75), event3 -> {
                                playAgain.setFill(Color.TRANSPARENT);
                                exit.setFill(Color.TRANSPARENT);
                            }));
                    timeline.setCycleCount(Timeline.INDEFINITE);
                    timeline.play();
                    SoundPlayer.playSound(SoundPlayer.gameOver);
                }
            }
        });

        // Set the cursor, foreground and background
        BackgroundManager.setCurrentBackground(root);
        BackgroundManager.setCurrentForeground(root);
        CursorManager.setCurrentCursor(level);
        root.getChildren().addAll(youWin, playNextLevel, ammoLeft, gameOver, playAgain, exit, gameComplete);
    }

    /**
     * Starts the game from level 1 without changing the cursor and background.
     */
    public void playAgain() {
        SoundPlayer.stopSound(SoundPlayer.gameOver);
        Level1 level1 = new Level1(window, 1);
        window.setScene(level1.getLevel());
    }

    /**
     * Returns to main menu. Once the game is returned to the main menu, cursor and backgrounds are reset.
     */
    public void returnToMainMenu() {
        SoundPlayer.stopSound(SoundPlayer.gameOver);
        MainMenu mainMenu = new MainMenu(window);
        window.setScene(mainMenu.getMainMenu());
    }

    /**
     * Returns true if ENTER key is enabled.
     * @return true if ENTER key is enabled, false if is disabled.
     */
    public boolean isEnableEnter() {
        return enableEnter;
    }

    /**
     * Disables or enables the ENTER key.
     * @param enableEnter setting true enables ENTER key, false disables.
     */
    public void setEnableEnter(boolean enableEnter) {
        this.enableEnter = enableEnter;
    }

    /**
     * Returns true if level is passed.
     * @return true if level is passed, false otherwise.
     */
    public boolean isLevelPassed() {
        return levelPassed;
    }

    /**
     * Sets the boolean to true if level is passed and vice versa.
     * @param levelPassed status of level passed
     */
    public void setLevelPassed(boolean levelPassed) {
        this.levelPassed = levelPassed;
    }

    /**
     * Increments the hitDucks variable.
     */
    public void incrementDuckHit() {
        hitDucks += 1;
    }

    /**
     * @return int hitDucks variable
     */
    public int getHitDucks() {
        return hitDucks;
    }

    /**
     * @return Returns the level scene that is contained within class.
     */
    public Scene getLevel() {
        return level;
    }

    /**
     * @return Returns the number of bullets left in the level.
     */
    public int getNumOfBullets() {
        return numOfBullets;
    }

    /**
     * Sets the number of bullets to passed value.
     * @param numOfBullets value to set numOfBullets
     */
    public void setNumOfBullets(int numOfBullets) {
        this.numOfBullets = numOfBullets;
    }

    /**
     * @return Returns the root pane of the level scene.
     */
    public Pane getRoot() {
        return root;
    }

    /**
     * @return Returns the list that holds the ducks in the level.
     */
    public List<Duck> getDuckList() {
        return duckList;
    }

    /**
     * Increments the completedLevels variable with one when a level is passed.
     */
    public void incrementCompletedLevels() {
        completedLevels += 1;
    }

}