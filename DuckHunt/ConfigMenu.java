import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ConfigMenu {
    private Scene configMenu;

    public ConfigMenu(Stage window, Scene mainMenu) {
        VBox pane = new VBox(DuckHunt.SCALE * 0.1);
        pane.setAlignment(Pos.TOP_CENTER);
        BackgroundManager.setCurrentBackground(pane);

        Text navigateText = new Text("USE ARROW KEYS TO NAVIGATE");
        navigateText.setFont(Font.font("Arial", FontWeight.BOLD, DuckHunt.SCALE * 8)); // Set the font and size as desired
        navigateText.setFill(Color.ORANGE);

        Text startText = new Text("PRESS ENTER TO START");
        startText.setFont(Font.font("Arial", FontWeight.BOLD, DuckHunt.SCALE * 8)); // Set the font and size as desired
        startText.setFill(Color.ORANGE);

        Text exitText = new Text("PRESS ESC TO EXIT");
        exitText.setFont(Font.font("Arial", FontWeight.BOLD, DuckHunt.SCALE * 8)); // Set the font and size as desired
        exitText.setFill(Color.ORANGE);

        pane.getChildren().addAll(navigateText, startText, exitText);

        configMenu = new Scene(pane, DuckHunt.WIDTH, DuckHunt.HEIGHT);

        configMenu.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER:
                    SoundPlayer.stopSound(SoundPlayer.titleTheme);
                    SoundPlayer.playSound(SoundPlayer.introTheme);
                    while (SoundPlayer.introTheme.isPlaying()) {
                        assert true;
                    }
                    window.setScene(new Level1(window, 1).getLevel());
                    break;
                case ESCAPE:
                    window.setScene(mainMenu);
                    break;
                case UP:
                    CursorManager.setNextImage((Pane) configMenu.getRoot());
                    CursorManager.setNextCursor();
                    break;
                case DOWN:
                    CursorManager.setPrevImage((Pane) configMenu.getRoot());
                    CursorManager.setPrevCursor();
                    break;
                case LEFT:
                    BackgroundManager.setPrevBackground((Pane) configMenu.getRoot());
                    break;
                case RIGHT:
                    BackgroundManager.setNextBackground((Pane) configMenu.getRoot());
                    break;
            }
        });
    }

    public Scene getConfigMenu() {
        return configMenu;
    }
}