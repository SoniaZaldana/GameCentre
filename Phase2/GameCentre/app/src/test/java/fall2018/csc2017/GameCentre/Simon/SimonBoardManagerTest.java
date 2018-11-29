package fall2018.csc2017.GameCentre.Simon;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SimonBoardManagerTest {
    private SimonBoardManager boardManager;
    List<SimonTile> listTiles;
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getGameQueue() {
        boolean equality = false;
        boardManager = new SimonBoardManager(1,2);
        GameQueue<SimonTile> queue = new GameQueue<>();
        SimonTile simonTile = new SimonTile();
        queue.add(simonTile);
        boardManager.getGameQueue().add(simonTile);
        equality = equal(queue, boardManager.getGameQueue());
        assertEquals(true, equality);
    }

    private boolean equal(GameQueue<SimonTile> thisQueue, GameQueue<SimonTile> otherQueue) {
        boolean equality = true;
        if (thisQueue.getSize() != otherQueue.getSize()){
            return false;
        }
        while(!thisQueue.isEmpty()){
            equality = thisQueue.remove().compareTo(otherQueue.remove()) == 0;
        }
        return equality;
    }

    @Test
    public void calculateScore() {
        boardManager = new SimonBoardManager(1,2);
        int score = boardManager.calculateScore(10);
        assertEquals(225, score);
    }

    @Test
    public void randomizer() {
        createValidTileList();
        SimonTilesBoard board = new SimonTilesBoard(2, listTiles);
        boardManager = new SimonBoardManager(board, 1);
        SimonTile randomTile = boardManager.randomizer();
        assertEquals(true, listTiles.contains(randomTile));

    }

    private void createValidTileList() {
        listTiles = new ArrayList<>();
        for (int x = 1; x != 5; x++) {
            SimonTile tile = new SimonTile(x);
            listTiles.add(tile);
        }
    }
}