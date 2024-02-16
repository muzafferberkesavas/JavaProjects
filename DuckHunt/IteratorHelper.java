import java.util.ListIterator;

/**
 * Class that has the generic IteratorHelper method which takes in an ListIterator and iterates over it.
 */
public class IteratorHelper {
    public static <T> T getNext(ListIterator<T> iterator, T currentItem)  {
        if (iterator.hasNext()) {
            T item = iterator.next();
            if (currentItem == item) {
                if (iterator.hasNext()) {
                    item = iterator.next();
                    return item;
                }
            } else {
                return item;
            }
        }
        while (iterator.hasPrevious()) {
            iterator.previous();
        }
        return iterator.next();
    }

    public static <T> T getPrev(ListIterator<T> iterator, T currentItem)  {
        if (iterator.hasPrevious()) {
            T item = iterator.previous();
            if (currentItem == item) {
                if (iterator.hasPrevious()) {
                    item = iterator.previous();
                    return item;
                }
            } else {
                return item;
            }
        }
        while (iterator.hasNext()) {
            iterator.next();
        }
        return iterator.previous();
    }
}