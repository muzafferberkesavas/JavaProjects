import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Class that is responsible for changing of the cursor.
 */
public class CursorManager {
    private static final List<ImageCursor> imageCursorList = new ArrayList<>();
    private static final List<ImageView> imageViewList = new ArrayList<>();
    private static final ListIterator<ImageCursor> cursorIterator;
    private static final ListIterator<ImageView> imageIterator;
    public static ImageCursor currentCursor;
    public static ImageView currentImage;

    // Creating the images for cursors.
    static {
        for (int i = 0; i < 7; i++) {
            Image image = new Image(String.format("assets/crosshair/%d.png", i+1));
            ImageView imageView = new ImageView(image);
            imageView.setTranslateY((double) 180 * DuckHunt.SCALE / 2);
            imageView.setFitWidth(10 * DuckHunt.SCALE);
            imageView.setFitHeight(10 * DuckHunt.SCALE);
            ImageCursor imageCursor = new ImageCursor(image);
            imageViewList.add(imageView);
            imageCursorList.add(imageCursor);
        }
        cursorIterator = imageCursorList.listIterator();
        imageIterator = imageViewList.listIterator();
        currentImage = imageViewList.get(0);
        currentCursor = imageCursorList.get(0);
    }

    /**
     * Sets the current cursor to next cursor in the iterator
     */
    public static void setNextCursor() {
        currentCursor = IteratorHelper.getNext(cursorIterator, currentCursor);
    }

    /**
     * Sets the current cursor to previous cursor in the iterator.
     */
    public static void setPrevCursor() {
        currentCursor = IteratorHelper.getPrev(cursorIterator, currentCursor);
    }

    /**
     * Sets the current cursor to the scene.
     * @param scene Scene that cursor is going to be set
     */
    public static void setCurrentCursor(Scene scene) {
        scene.setCursor(currentCursor);
    }

    /**
     * Sets the cursor image in Config Menu
     * @param pane pane to set the image
     */
    public static void setCurrentImage(Pane pane) {
        pane.getChildren().add(currentImage);
    }

    /**
     * Sets it to next image
     * @param pane pane to set the image
     */
    public static void setNextImage(Pane pane) {
        currentImage = IteratorHelper.getNext(imageIterator, currentImage);
        pane.getChildren().set(pane.getChildren().size() - 1, currentImage);
    }

    /**
     * Sets it to previous image
     * @param pane pane to set the image
     */
    public static void setPrevImage(Pane pane) {
        currentImage = IteratorHelper.getPrev(imageIterator, currentImage);
        pane.getChildren().set(pane.getChildren().size() - 1, currentImage);
    }

    /**
     * Resets the cursors and images to initial ones
     */
    public static void reset() {
        while (cursorIterator.hasPrevious()) {
            cursorIterator.previous();
        }
        currentCursor = cursorIterator.next();

        while (imageIterator.hasPrevious()) {
            imageIterator.previous();
        }
        currentImage = imageIterator.next();
    }
}
