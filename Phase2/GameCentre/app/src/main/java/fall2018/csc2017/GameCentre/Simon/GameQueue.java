package fall2018.csc2017.GameCentre.Simon;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;


public class GameQueue<E>  extends Observable implements Serializable, Iterable<E> {
    /**
     * LinkedList representing the queue
     */
    private List<E> gameList;

    public int getSize() {
        return size;
    }

    /**
     * The size of this queue
     */
    private int size;


    /**
     * Instantiates an object of a game queue with a linked gameList and it's corresponding size
     */
    GameQueue() {
        this.gameList = new LinkedList<>();
        this.size = 0;
    }

    /**
     * Adds an object to the back of the queue and increments size
     * @param tile - tile to be added to the queue
     */
    void add(E tile) {
        gameList.add(tile);
        this.size++;
        setChanged();
        notifyObservers();
    }

    /**
     * Checks whether the queue is empty
     * @return boolean whether queue is empty
     */
    boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Returns the object at the front of the queue and removes it from queue
     * @return object in front of queue
     */
    E remove() {
        this.size--;
        return gameList.remove(0);
    }
    E get(int index){
        return gameList.get(index);
    }
    /**
     * @return an iterator of class GameQUeue.
     */
    @NonNull
    @Override
    public ListIterator<E> iterator() {
        return new GameQueueIterator() {
        };
    }

    /**
     * Represents the iterator for class GameQueue.
     */
    private class GameQueueIterator implements ListIterator<E>, Serializable {
        /**
         * current index in the queue
         */
        private int currentIndex = 0;

        /**
         *
         * @return the previous element
         */
        @Override
        public E previous() {
            currentIndex-=1;
            E prev = gameList.get(currentIndex);
            return prev;
        }

        @Override
        public boolean hasNext() {
            return currentIndex< gameList.size();
        }

        @Override
        public E next() {
            E nextElement = gameList.get(currentIndex);
            currentIndex += 1;
            return nextElement;
        }

        @Override
        public boolean hasPrevious() {
            return 0 < currentIndex;
        }

        @Override
        public int nextIndex() {
            return currentIndex+1;
        }

        @Override
        public int previousIndex() {
            return currentIndex - 1;
        }

        @Override
        public void remove() {
        }

        @Override
        public void set(E e) {
        }

        @Override
        public void add(E e) {
        }
    }
    }
