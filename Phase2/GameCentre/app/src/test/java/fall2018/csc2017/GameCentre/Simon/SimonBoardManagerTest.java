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
        boardManager = new SimonBoardManager(1,2);
        GameQueue<SimonTile> queue = new GameQueue<>();
        assertEquals(queue, boardManager.getGameQueue());
    }

    @Test
    public void calculateScore() {
        boardManager = new SimonBoardManager(1,2);
        int score = boardManager.calculateScore(10);
        assertEquals(100, score);
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
        SimonTile tile1 = new SimonTile(1);
        SimonTile tile2 = new SimonTile(2);
        SimonTile tile3 = new SimonTile(3);
        SimonTile tile4 = new SimonTile(4);
        listTiles = new ArrayList<>();
        listTiles.add(tile1);
        listTiles.add(tile2);
        listTiles.add(tile3);
        listTiles.add(tile4);
    }
}