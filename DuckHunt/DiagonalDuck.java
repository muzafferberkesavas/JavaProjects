import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 * Subclass of duck that is moving across the diagonals.
 */
public class DiagonalDuck extends Duck{
    public static Rotate ROTATE_90_DEGREES = new Rotate();
    public static Rotate ROTATE_0_DEGREES = new Rotate();

    static {
        ROTATE_90_DEGREES.setAngle(90);
        ROTATE_90_DEGREES.setPivotX(DUCK_WIDTH * 0.5);
        ROTATE_90_DEGREES.setPivotY(DUCK_HEIGHT * 0.5);

        ROTATE_0_DEGREES.setAngle(0);
        ROTATE_0_DEGREES.setPivotX(DUCK_WIDTH * 0.5);
        ROTATE_0_DEGREES.setPivotY(DUCK_HEIGHT * 0.5);

    }

    /**
     * Instantiating the duck with passed parameters
     * @param duckX The X coordinate where the duck is going to be put in the scene
     * @param duckY The Y coordinate where the duck is going to be put in the scene
     * @param deltaX Speed of the duck across X axis.
     * @param deltaY Speed of the duck across Y axis.
     * @param color Color of the duck
     */
    public DiagonalDuck(double duckX, double duckY, double deltaX, double deltaY, String color) {
        super(duckX, duckY, deltaX, deltaY, color);
        if (getFlyingDeltaY() > 0) {
            getImageView().getTransforms().add(ROTATE_90_DEGREES);
        } else {
            getImageView().getTransforms().add(ROTATE_0_DEGREES);
        }

        setFlyingAnimation(new Timeline(
                new KeyFrame(Duration.seconds(0.2), event -> getImageView().setImage(Duck.listMap.get(color)[0])),
                new KeyFrame(Duration.seconds(0.4), event -> getImageView().setImage(Duck.listMap.get(color)[1])),
                new KeyFrame(Duration.seconds(0.6), event -> getImageView().setImage(Duck.listMap.get(color)[2]))));
        getFlyingAnimation().setCycleCount(Timeline.INDEFINITE);
        getFlyingAnimation().play();

        // Reflect and rotate in order to make duck fly in correct directions
        setFlyingMovement(new Timeline(new KeyFrame(new Duration(16), event -> {
            setDuckX(getDuckX() + getDeltaX());
            setDuckY(getDuckY() + getFlyingDeltaY());

            if (getDuckX() <= 0 || DuckHunt.WIDTH - getDuckX() <= DuckMaker.HITBOX_SIZE) {
                setDeltaX(getDeltaX() * -1);
                if (getDeltaX() > 0 && getFlyingDeltaY() < 0) {
                    getImageView().getTransforms().set(0, UP_RIGHT);
                    getImageView().getTransforms().set(1, ROTATE_0_DEGREES);
                } else if (getDeltaX() > 0 && getFlyingDeltaY() > 0) {
                    getImageView().getTransforms().set(0, UP_RIGHT);
                    getImageView().getTransforms().set(1, ROTATE_90_DEGREES);
                } else if (getDeltaX() < 0 && getFlyingDeltaY() < 0) {
                    getImageView().getTransforms().set(0, UP_LEFT);
                    getImageView().getTransforms().set(1, ROTATE_0_DEGREES);
                } else if (getDeltaX() < 0 && getFlyingDeltaY() > 0){
                    getImageView().getTransforms().set(0, UP_LEFT);
                    getImageView().getTransforms().set(1, ROTATE_90_DEGREES);
                }
            }

            if (getDuckY() <= 0 || DuckHunt.HEIGHT - getDuckY() <= DuckMaker.HITBOX_SIZE) {
                setFlyingDeltaY(getFlyingDeltaY() * -1);
                if (getDeltaX() > 0 && getFlyingDeltaY() < 0) {
                    getImageView().getTransforms().set(0, UP_RIGHT);
                    getImageView().getTransforms().set(1, ROTATE_0_DEGREES);
                } else if (getDeltaX() > 0 && getFlyingDeltaY() > 0) {
                    getImageView().getTransforms().set(0, UP_RIGHT);
                    getImageView().getTransforms().set(1, ROTATE_90_DEGREES);
                } else if (getDeltaX() < 0 && getFlyingDeltaY() < 0) {
                    getImageView().getTransforms().set(0, UP_LEFT);
                    getImageView().getTransforms().set(1, ROTATE_0_DEGREES);
                } else if (getDeltaX() < 0 && getFlyingDeltaY() > 0){
                    getImageView().getTransforms().set(0, UP_LEFT);
                    getImageView().getTransforms().set(1, ROTATE_90_DEGREES);
                }
            }

            getHitbox().setTranslateX(getDuckX());
            getHitbox().setTranslateY(getDuckY());
        })));
        getFlyingMovement().setCycleCount(Timeline.INDEFINITE);
        getFlyingMovement().play();
    }
}