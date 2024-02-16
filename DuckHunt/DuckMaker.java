import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


/**
 * Static class that is responsible for creating the hitboxes and the ImageView of the ducks.
 */
public class DuckMaker {
    public static final double HITBOX_SIZE = 33 * DuckHunt.SCALE;

    /**
     * Creates StackPane and puts the given ImageView inside of it.
     * @param duckImageView ImageView that is to be put inside of pane
     * @return Returns the hitbox that is created
     */
    public static Pane makeHitbox(ImageView duckImageView) {
        Pane hitbox = new StackPane(duckImageView);
        hitbox.setPrefSize(HITBOX_SIZE, HITBOX_SIZE);
        hitbox.setStyle("-fx-background-color: transparent");
        return hitbox;
    }

    /**
     * Creates the ImageView that is going to animate duck flying.
     * @return Returns the ImageView that shows the duck images.
     */
    public static ImageView makeImageView() {
        ImageView duckImageView = new ImageView();
        duckImageView.setFitWidth(Duck.DUCK_WIDTH);
        duckImageView.setFitHeight(Duck.DUCK_HEIGHT);
        return duckImageView;
    }
}
