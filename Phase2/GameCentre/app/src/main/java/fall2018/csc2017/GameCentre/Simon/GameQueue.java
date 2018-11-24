package fall2018.csc2017.GameCentre.Simon;

import java.io.Serializable;
import java.util.LinkedList;


public class GameQueue<E> implements Serializable {
    /**
     * LinkedList representing the queue
     */
    LinkedList<E> list;
    /**
     * The size of this queue
     */
    int size;

    /**
     * Instantiates an object of a game queue with a linked list and it's corresponding size
     */
    public GameQueue() {
        this.list = new LinkedList();
        this.size = 0;
    }

    /**
     * Adds an object to the back of the queue and increments size
     * @param tile - tile to be added to the queue
     */
    public void add(E tile) {
        list.add(tile);
        this.size++;
    }

    /**
     * Checks whether the queue is empty
     * @return boolean whether queue is empty
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Returns the object at the front of the queue and removes it from queue
     * @return object in front of queue
     */
    public E remove() {
        return list.remove();
    }
}
