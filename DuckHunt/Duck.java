import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that is responsible for duck flying animations, falling animations, and duck hitbox movement.
 */
public class Duck {
    public static Scale UP_LEFT = new Scale(-1, 1);
    public static Scale UP_RIGHT = new Scale(1, 1);
    public static final double DUCK_WIDTH = 27 * DuckHunt.SCALE;
    public static final double DUCK_HEIGHT = 31 * DuckHunt.SCALE;
    public static Map<String, Image[]> listMap = new HashMap<>();

    static {
        UP_RIGHT.setPivotX(DUCK_WIDTH * 0.5);
        UP_LEFT.setPivotX(DUCK_WIDTH * 0.5);

        UP_RIGHT.setPivotY(DUCK_HEIGHT * 0.5);
        UP_LEFT.setPivotY(DUCK_HEIGHT * 0.5);

        Image[] blackDuckImages = new Image[8];
        Image[] redDuckImages = new Image[8];
        Image[] blueDuckImages = new Image[8];


        for (int i = 0; i < 8; i++) {
            Image image = new Image(String.format("assets/duck_black/%d.png", i+1));
            blackDuckImages[i] = image;
        }

        for (int i = 0; i < 8; i++) {
            Image image = new Image(String.format("assets/duck_blue/%d.png", i+1));
            blueDuckImages[i] = image;
        }

        for (int i = 0; i < 8; i++) {
            Image image = new Image(String.format("assets/duck_red/%d.png", i+1));
            redDuckImages[i] = image;
        }

        listMap.put("black", blackDuckImages);
        listMap.put("red", redDuckImages);
        listMap.put("blue", blueDuckImages);
    }

    private double duckX;
    private double duckY;
    private double flyingDeltaX;
    private double flyingDeltaY;
    private final double fallingDeltaY = 2 * DuckHunt.SCALE;
    private ImageView imageView = DuckMaker.makeImageView();
    private final Pane hitbox = DuckMaker.makeHitbox(imageView);
    private boolean isHit = false;
    private Timeline flyingAnimation;
    private Timeline flyingMovement;
    private Timeline dyingAnimation;
    private Timeline fallingMovement;

    /**
     * Instantiating the duck with passed parameters
     * @param duckX The X coordinate where the duck is going to be put in the scene
     * @param duckY The Y coordinate where the duck is going to be put in the scene
     * @param deltaX Speed of the duck across X axis.
     * @param deltaY Speed of the duck across Y axis.
     * @param color Color of the duck
     */
    public Duck(double duckX, double duckY, double deltaX, double deltaY, String color) {
        this.duckX = duckX;
        this.duckY = duckY;
        this. flyingDeltaX = deltaX * DuckHunt.SCALE;
        this.flyingDeltaY = deltaY * DuckHunt.SCALE;
        hitbox.setTranslateX(duckX);
        hitbox.setTranslateY(duckY);

        // Reflecting the image of the duck according to its velocity direction.
        if (deltaX < 0) {
            imageView.getTransforms().add(UP_LEFT);
        } else {
            imageView.getTransforms().add(UP_RIGHT);
        }

        // Sets the dying animation of the duck.
        setDyingAnimation(new Timeline(
                new KeyFrame(Duration.ZERO, event -> {
                    getImageView().getTransforms().clear();
                    getImageView().getTransforms().add(getDeltaX() > 0 ? UP_RIGHT : UP_LEFT);
                    getImageView().setImage(Duck.listMap.get(color)[6]);
                }),
                new KeyFrame(Duration.seconds(0.3), event -> {
                    SoundPlayer.playSound(SoundPlayer.duckFall);
                    getImageView().setImage(Duck.listMap.get(color)[7]);
                    getFallingMovement().play();})
        ));

        // Sets the falling movement of the duck.
        setFallingMovement(new Timeline(
                new KeyFrame(Duration.millis(16), event -> {
                    setDuckY(getDuckY() + getFallingDeltaY());
                    if (DuckHunt.HEIGHT - getHitbox().getTranslateY() <= DuckMaker.HITBOX_SIZE) {
                        getFallingMovement().stop();
                    }
                    getHitbox().setTranslateY(getDuckY());
                })
        ));
        getFallingMovement().setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * Duck stops moving and plays dying animation while falling
     */
    public void die() {
        flyingAnimation.stop();
        flyingMovement.stop();
        dyingAnimation.play();
    }

    /**
     * @return Returns the X coordinate of the duck
     */
    public double getFlyingDeltaX() {
        return flyingDeltaX;
    }

    /**
     * @param flyingDeltaX Sets the Y coordinate of the duck.
     */
    public void setFlyingDeltaX(double flyingDeltaX) {
        this.flyingDeltaX = flyingDeltaX;
    }

    /**
     * @return Returns the flying animation
     */
    public Timeline getFlyingAnimation() {
        return flyingAnimation;
    }

    /**
     * Sets the flying animation
     * @param flyingAnimation Flying animation to set
     */
    public void setFlyingAnimation(Timeline flyingAnimation) {
        this.flyingAnimation = flyingAnimation;
    }

    /**
     * @return Returns the flying movement
     */
    public Timeline getFlyingMovement() {
        return flyingMovement;
    }

    /**
     * Sets the flying movement of the duck
     * @param flyingMovement flying movement to set
     */
    public void setFlyingMovement(Timeline flyingMovement) {
        this.flyingMovement = flyingMovement;
    }

    /**
     * @return Returns the flying animation
     */
    public Timeline getDyingAnimation() {
        return dyingAnimation;
    }

    /**
     * Sets the dying animation
     * @param dyingAnimation Dying animation to set
     */
    public void setDyingAnimation(Timeline dyingAnimation) {
        this.dyingAnimation = dyingAnimation;
    }

    /**
     * @return Returns the falling movement of the duck.
     */
    public Timeline getFallingMovement() {
        return fallingMovement;
    }

    /**
     * Sets the falling movement
     * @param fallingMovement Falling movement to set
     */
    public void setFallingMovement(Timeline fallingMovement) {
        this.fallingMovement = fallingMovement;
    }

    /**
     * Returns the falling speed of duck
     * @return
     */
    public double getFallingDeltaY() {
        return fallingDeltaY;
    }

    /**
     * @return Returns the X coordinate of the duck
     */
    public double getDuckX() {
        return duckX;
    }

    /**
     * Sets the X coordinate of the duck
     * @param duckX X coordinate to set
     */
    public void setDuckX(double duckX) {
        this.duckX = duckX;
    }

    /**
     * @return Returns the Y coordinate of the duck
     */
    public double getDuckY() {
        return duckY;
    }

    /**
     * Sets the Y coordinate of the duck
     * @param duckY Y coordinate to set
     */
    public void setDuckY(double duckY) {
        this.duckY = duckY;
    }

    /**
     * @return Returns the hitbox of the duck
     */
    public Pane getHitbox() {
        return hitbox;
    }

    /**
     * @return Return the velocity of the duck across X axis
     */
    public double getDeltaX() {
        return  flyingDeltaX;
    }

    /**
     * Sets the velocity of the duck along X axis.
     * @param deltaX velocity to set
     */
    public void setDeltaX(double deltaX) {
        this. flyingDeltaX = deltaX;
    }

    /**
     * @return Returns the velocity of the duck along Y axis.
     */
    public double getFlyingDeltaY() {
        return flyingDeltaY;
    }

    /**
     * Sets the velocity of the duck along Y axis
     * @param flyingDeltaY velocity to set
     */
    public void setFlyingDeltaY(double flyingDeltaY) {
        this.flyingDeltaY = flyingDeltaY;
    }

    /**
     * @return Returns the ImageView of the duck.
     */
    public ImageView getImageView() {
        return imageView;
    }

    /**
     * Sets the ImageView of the duck
     * @param imageView ImageView to set
     */
    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    /**
     * @return Hit status of the duck
     */
    public boolean isHit() {
        return isHit;
    }

    /**
     * Set to true when duck is hit
     * @param hit true when duck is hit
     */
    public void setHit(boolean hit) {
        isHit = hit;
    }
}
