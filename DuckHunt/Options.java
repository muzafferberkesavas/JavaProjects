import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Options {
    public Scene configMenu;
    public static List<Image> crossHairs = new ArrayList<>();
    public List<BackgroundImage> backgrounds = new ArrayList<>();
    public List<Image> foregrounds = new ArrayList<>();
    public static int crosshairIndex = 0;
    public static int backgroundIndex = 0;
    private static final ImageView currentForeground = new ImageView();

    public static AudioClip introTheme = new AudioClip(new File("/assets/effects/Intro.mp3").toURI().toString());

    /**
     * Constructs a new Options object.
     *
     * @param stage The primary stage for the application.
     * @param scene The scene for the title screen.
     */
    public Options(Stage stage, Scene scene) {
        VBox pane = new VBox(DuckHunt.SCALE * 0.1);
        pane.setAlignment(Pos.TOP_CENTER);

        Text navigateText = new Text("USE ARROW KEYS TO NAVIGATE\n\tPRESS ENTER TO START\n\t   PRESS ESC TO EXIT");
        navigateText.setFont(Font.font("Arial", FontWeight.BOLD, DuckHunt.SCALE * 8));
        navigateText.setFill(Color.ORANGE);

        pane.getChildren().addAll(navigateText);

        configMenu = new Scene(pane, DuckHunt.SCALE * 256, DuckHunt.SCALE * 240);

        for (int i = 0; i < 7; i++) {
            Image crosshairImage = new Image("assets/crosshair/" + (i + 1) + ".png");
            crossHairs.add(crosshairImage);
        }

        for (int i = 0; i < 6; i++) {
            BackgroundImage backgroundImage = new BackgroundImage(
                    new Image(String.format("assets/background/%d.png", i + 1)),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT, new BackgroundSize(
                    DuckHunt.SCALE * 256, DuckHunt.SCALE * 240,
                    false, false, false, false
            )
            );
            Image foregroundImage = new Image("assets/foreground/" + (i + 1) + ".png");

            backgrounds.add(backgroundImage);
            foregrounds.add(foregroundImage);
        }

        ImageView crossHair = new ImageView();
        ImageView background = new ImageView();
        ImageView foreground = new ImageView();
        foreground.setTranslateY(-83 * DuckHunt.SCALE / 3);
        crossHair.setTranslateY(-500 * DuckHunt.SCALE / 3);

        foreground.setFitHeight(DuckHunt.SCALE * 240);
        foreground.setFitWidth(DuckHunt.SCALE * 256);

        crossHair.setFitWidth(DuckHunt.SCALE * 8);
        crossHair.setFitHeight(DuckHunt.SCALE * 8);

        crossHair.setImage(crossHairs.get(0));
        foreground.setImage(foregrounds.get(0));
        pane.setBackground(new Background(backgrounds.get(0)));

        pane.getChildren().add(background);
        pane.getChildren().add(foreground);
        pane.getChildren().add(crossHair);

        configMenu.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                TitleScreen.titleMusic.stop();
                introTheme.setVolume(DuckHunt.VOLUME);
                introTheme.play();
                while (introTheme.isPlaying()) {
                    // Wait until the intro theme finishes playing
                }
                Cursor crosshairCursor = new ImageCursor(crossHairs.get(crosshairIndex % 7));
                pane.setCursor(crosshairCursor);
                Level level1 = new Level(1, stage);
                level1.Level1(stage);
                stage.setScene(level1.level);
            } else if (event.getCode() == KeyCode.ESCAPE) {
                stage.setScene(scene);
            } else if (event.getCode() == KeyCode.UP) {
                crosshairIndex++;
                crossHair.setImage(crossHairs.get(crosshairIndex % 7));
            } else if (event.getCode() == KeyCode.DOWN) {
                if (crosshairIndex == 0) {
                    crosshairIndex = 6;
                } else {
                    crosshairIndex--;
                }
                crossHair.setImage(crossHairs.get(crosshairIndex % 7));
            } else if (event.getCode() == KeyCode.LEFT) {
                if (backgroundIndex == 0) {
                    backgroundIndex = 5;
                } else {
                    backgroundIndex--;
                }
                pane.setBackground(new Background(backgrounds.get(backgroundIndex % 6)));
                foreground.setImage(foregrounds.get(backgroundIndex % 6));
                foreground.setFitHeight(DuckHunt.SCALE * 240);
                foreground.setFitWidth(DuckHunt.SCALE * 256);
            } else if (event.getCode() == KeyCode.RIGHT) {
                backgroundIndex++;
                pane.setBackground(new Background(backgrounds.get(backgroundIndex % 6)));
                foreground.setImage(foregrounds.get(backgroundIndex % 6));
                foreground.setFitWidth(DuckHunt.SCALE * 256);
                foreground.setFitHeight(DuckHunt.SCALE * 240);
            }
        });
    }

    /**
     * Returns the current index of the background.
     *
     * @return The current background index.
     */
    public static int getCurrentBackgroundIndex() {
        return backgroundIndex;
    }

    /**
     * Sets the background of the pane to the specified image.
     *
     * @param pane The pane to set the background for.
     * @param image The image to set as the background.
     */
    public static void backgroundSetter(Pane pane, Image image) {
        BackgroundImage background = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, new BackgroundSize(DuckHunt.SCALE * 256, DuckHunt.SCALE * 240,
                false, false, false, false));
        Background newBackground = new Background(background);
        pane.setBackground(newBackground);
    }

    /**
     * Sets the foreground image to the specified image.
     *
     * @param foregroundImage The image to set as the foreground.
     */
    public static void setForegroundImage(Image foregroundImage) {
        currentForeground.setImage(foregroundImage);
    }

    /**
     * Sets the current foreground image on the pane.
     *
     * @param pane The pane to set the foreground image on.
     */
    public static void setCurrentForeground(Pane pane) {
        currentForeground.setFitHeight(DuckHunt.SCALE * 240);
        currentForeground.setFitWidth(DuckHunt.SCALE * 256);
        pane.getChildren().add(currentForeground);
    }

    /**
     * Returns the current cursor image view.
     *
     * @return The current cursor image view.
     */
    public static  Image getCursorImageView() {
        return crossHairs.get(crosshairIndex % 7);
    }
}
