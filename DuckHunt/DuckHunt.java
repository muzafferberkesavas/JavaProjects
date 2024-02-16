import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Driver class that runs the game and holds the SCALE and VOLUME variables that scale the game
 * and change the volume.
 */
public class DuckHunt extends Application {
    public static final double SCALE = 3;
    public static final double VOLUME = 0.025;
    public static final double WIDTH = SCALE * 256;
    public static final double HEIGHT = SCALE * 240;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        Game game = new Game();
        game.startGame(window);
    }
}