
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Class which instantiates the MainMenu and starts the game.
 */
public class Game {
    public void startGame(Stage window) {
        window.setTitle("HUBBM DuckHunt");
        window.getIcons().add(new Image("assets/favicon/1.png"));
        MainMenu mainMenu = new MainMenu(window);
        window.setScene(mainMenu.getMainMenu());
        window.show();
    }
}