import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;

/**

 The TitleScreen class represents the title screen of the DuckHunt game.

 It initializes and manages the graphical elements and user interactions of the title screen.
 */
public class TitleScreen {
    public Scene scene;
    public static AudioClip titleMusic;

    /**

     Constructs a new TitleScreen object.

     @param stage The primary stage of the application.
     */
    public TitleScreen(Stage stage) {
        Image titleImage = new Image("assets/welcome/1.png");

        BackgroundImage background = new BackgroundImage(titleImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, new BackgroundSize(DuckHunt.SCALE * 256, DuckHunt.SCALE * 240,
                false, false, false, false));

        VBox pane = new VBox(DuckHunt.SCALE * 3.5);
        pane.setAlignment(Pos.CENTER);
        pane.setBackground(new Background(background));

        Text flashingText = new Text("PRESS ENTER TO START\n PRESS ESC TO EXIT");
        flashingText.setFill(Color.ORANGE);
        flashingText.setFont(Font.font("Arial", FontWeight.BOLD, DuckHunt.SCALE * 18));
        flashingText.setTranslateY(DuckHunt.SCALE * 48);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.4), event -> flashingText.setFill(Color.ORANGE)),
                new KeyFrame(Duration.seconds(0.70), event -> flashingText.setFill(Color.TRANSPARENT)));

        titleMusic = new AudioClip(new File("assets/effects/Title.mp3").toURI().toString());
        titleMusic.setVolume(DuckHunt.VOLUME);
        titleMusic.setCycleCount(AudioClip.INDEFINITE);
        titleMusic.play();

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        pane.getChildren().addAll(flashingText);
        scene = new Scene(pane, DuckHunt.SCALE * 256, DuckHunt.SCALE * 240);

        scene.addEventHandler(KeyEvent.KEY_PRESSED, key-> {
            if (key.getCode() == KeyCode.ENTER){
                Options options = new Options(stage, scene);
                stage.setScene(options.configMenu);
            } else if (key.getCode() == KeyCode.ESCAPE) {
                stage.close();
            }
        });
    }
}







