import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Class that is responsible for setting the backgrounds to levels
 */
public class BackgroundManager {
    private static final List<ImageView> foregroundList = new LinkedList<>();
    private static final List<Background> backgroundList = new LinkedList<>();
    private static final ListIterator<Background> backgroundIterator;
    private static final ListIterator<ImageView> foregroundIterator;
    private static Background currentBackground;
    private static ImageView currentForeground;
    private static double translateX = 0.0; // Store the current horizontal translation

    static {
        for (int i = 0; i < 6; i++) {
            BackgroundImage background = new BackgroundImage(new Image(String.format("assets/background/%d.png", i+1)),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT, new BackgroundSize(DuckHunt.WIDTH, DuckHunt.HEIGHT,
                    false, false, false, false));
            backgroundList.add(new Background(background));
        }
        backgroundIterator = backgroundList.listIterator();
        currentBackground = backgroundIterator.next();

        for (int i = 0; i < 6; i++) {
            ImageView imageView = new ImageView(new Image(String.format("assets/foreground/%d.png", i+1)));
            imageView.setFitWidth(DuckHunt.WIDTH);
            imageView.setFitHeight(DuckHunt.HEIGHT);
            foregroundList.add(imageView);
        }
        foregroundIterator = foregroundList.listIterator();
        currentForeground = foregroundIterator.next();
    }

    /**
     * Sets next background in Config Menu
     * @param pane pane in Config Menu
     */
    public static void setNextBackground(Pane pane) {
        currentBackground = IteratorHelper.getNext(backgroundIterator, currentBackground);
        currentForeground = IteratorHelper.getNext(foregroundIterator, currentForeground);
        pane.setBackground(currentBackground);
    }

    /**
     * Sets previous background in Config Menu
     * @param pane pane in Config Menu
     */
    public static void setPrevBackground(Pane pane) {
        currentBackground = IteratorHelper.getPrev(backgroundIterator, currentBackground);
        currentForeground = IteratorHelper.getPrev(foregroundIterator, currentForeground);
        pane.setBackground(currentBackground);
    }

    /**
     * Sets the current background image to levels root pane
     * @param pane Levels root pane
     */
    public static void setCurrentBackground(Pane pane) {
        pane.setBackground(currentBackground);
    }

    /**
     * Sets the current foreground image to levels root pane
     * @param pane Levels root pane
     */
    public static void setCurrentForeground(Pane pane) {
        pane.getChildren().add(currentForeground);
    }

    /**
     * Resets the background image and foreground image to initial ones.
     */
    public static void reset() {
        while (backgroundIterator.hasPrevious()) {
            backgroundIterator.previous();
        }
        currentBackground = backgroundIterator.next();

        while (foregroundIterator.hasPrevious()) {
            foregroundIterator.previous();
        }
        currentForeground = foregroundIterator.next();
    }

    public static void updateScrolling(double mouseX, double sceneWidth) {
        double leftEdge = sceneWidth  * 0.1; // Adjust as needed
        double rightEdge = sceneWidth * 0.9; // Adjust as needed
        double scrollSpeed = 5.0; // Adjust as needed

        if (mouseX < leftEdge) {
            translateX -= scrollSpeed;
        }
        else if (mouseX > rightEdge) {
            translateX += scrollSpeed;
        }


        currentForeground.setTranslateX(translateX);
    }
}
