
/**
 The Scrolling class provides a utility method for checking the scroll count difference between
 the right scroll count and the left scroll count.
*/

 public class Scrolling {
/**
 Calculates the scroll count difference between the right scroll count and the left scroll count.
 @param rightScrollCount The right scroll count.
 @param leftScrollCount The left scroll count.
 @return The scroll count difference, modulo 32.
 */
public static int ScrollCountChecker(int rightScrollCount, int leftScrollCount) {
        if (rightScrollCount > leftScrollCount) {
        return Math.abs(rightScrollCount - leftScrollCount) % 32;
        } else if (rightScrollCount == leftScrollCount) {
        return 0;
        } else {
        return Math.abs(leftScrollCount - rightScrollCount) % 32;
        }
        }
        }