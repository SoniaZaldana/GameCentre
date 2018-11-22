package fall2018.csc2017.GameCentre.Simon;

import java.util.LinkedList;


public class GameQueue<E> {

    LinkedList<E> list;
    int size;

    public GameQueue() {
        this.list = new LinkedList();
        this.size = 0;
    }

    public void add(E tile) {
        list.add(tile);
        this.size++;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public E remove() {
        return list.remove();
    }
}
