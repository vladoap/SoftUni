package interfaces;

public interface ReversedListIntf<E> extends Iterable<E> {

    void add(E element);
    E removeAt(int index);
    E get(int index);
    int capacity();
    int size();


}
