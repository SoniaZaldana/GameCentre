package fall2018.csc2017.GameCentre.Simon;


import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.Random;

import fall2018.csc2017.GameCentre.BoardManager;

public class SimonBoardManager extends BoardManager<SimonTilesBoard> {

    private int undo;
    private Queue<SimonTile> gameQueue;

    public SimonBoardManager(SimonTilesBoard board, int undo) {
        super(board);
        this.undo = undo;
        this.gameQueue = new Queue<SimonTile>() {
            //TODO: a queue is an abstract class, so to implement a queue of simon tiles, you need to implement the following methods.
            @Override
            public boolean add(SimonTile simonTile) {
                return false;
            }

            @Override
            public boolean offer(SimonTile simonTile) {
                return false;
            }

            @Override
            public SimonTile remove() {
                return null;
            }

            @Override
            public SimonTile poll() {
                return null;
            }

            @Override
            public SimonTile element() {
                return null;
            }

            @Override
            public SimonTile peek() {
                return null;
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @NonNull
            @Override
            public Iterator<SimonTile> iterator() {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(@NonNull T[] a) {
                return null;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(@NonNull Collection<? extends SimonTile> c) {
                return false;
            }

            @Override
            public boolean removeAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        };
        // TODO instantiate gameUndoStack, but I am not sure about what size yet.
    }

    public Queue<SimonTile> getGameQueue(){
        return this.gameQueue;
    }

    //TODO: evaluate if this way to calculate score will suffice
    @Override
    public int calculateScore(int moves) {
        return moves * 10;
    }

    // TODO: Randomizer should return a random tile from all tiles in order to display it
    ArrayList<SimonTile> randomizer() {
        ArrayList<ArrayList<SimonTile>> simonList = this.getBoard().getAllTiles();
        Random rand = new Random();
        int index = rand.nextInt(simonList.size());
        return simonList.get(index);
    }


    // TODO method populate stack. This method would take a single element x
    // TODO Then it would push this element onto the stack.
    void populateQueue() {

    }
}

