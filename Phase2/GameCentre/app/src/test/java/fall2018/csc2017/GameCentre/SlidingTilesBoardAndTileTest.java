package fall2018.csc2017.GameCentre;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.GameCentre.SlidingTiles.SlidingBoardManager;
import fall2018.csc2017.GameCentre.SlidingTiles.SlidingTilesBoard;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SlidingTilesBoardAndTileTest {

    /** The board manager for testing. */
    private SlidingBoardManager boardManager;

    private List<Tile> tiles;

    /**
     * Make a set of tiles that are in order.
     * @return a set of tiles that are in order
     */
    private List<Tile> makeTiles(int dimension) {
        // Create the tiles
        List<Tile> tilesList = new ArrayList<>();
        final int numTiles = dimension * dimension;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tilesList.add(new Tile(tileNum + 1));
        }
        return tilesList;
    }

    /**
     * Make a solved SlidingTilesBoard.
     */
    private void setUpCorrect(int undoMax, int dimension) {
        tiles = makeTiles(dimension);
        SlidingTilesBoard board = new SlidingTilesBoard(dimension, tiles);
        boardManager = new SlidingBoardManager(undoMax, board);
    }

    /**
     * Shuffle a few tiles.
     */
    private void swapFirstTwoTiles() {
        boardManager.getBoard().swapTiles(0, 0, 0, 1);
    }


    /**
     * Test whether swapping the first two tiles works.
     */
    @Test
    public void testSwapFirstTwo() {
        setUpCorrect(2,2);
        assertEquals(1, boardManager.getBoard().getTile(0, 0).getId());
        assertEquals(2, boardManager.getBoard().getTile(0, 1).getId());
        boardManager.getBoard().swapTiles(0, 0, 0, 1);
        assertEquals(2, boardManager.getBoard().getTile(0, 0).getId());
        assertEquals(1, boardManager.getBoard().getTile(0, 1).getId());
    }

    /**
     * Test whether swapping the last two tiles works.
     */
    @Test
    public void testSwapLastTwo() {
        setUpCorrect(4,4);
        assertEquals(15, boardManager.getBoard().getTile(3, 2).getId());
        assertEquals(16, boardManager.getBoard().getTile(3, 3).getId());
        boardManager.getBoard().swapTiles(3, 3, 3, 2);
        assertEquals(16, boardManager.getBoard().getTile(3, 2).getId());
        assertEquals(15, boardManager.getBoard().getTile(3, 3).getId());
    }

    @Test
    public void testSlidingTilesBoard(){
        setUpCorrect(5,2 );
        assertEquals(4, boardManager.getBoard().getBlankId());
        setUpCorrect(5,3 );
        assertEquals(9, boardManager.getBoard().getBlankId());
    }
    @Test
    public void testStringReturn(){
        setUpCorrect(2,2 );
        String expectedResult = "tiles = [1, 2, 3, 4]";
        assertEquals(expectedResult,boardManager.getBoard().toString());
    }

    @Test
    public void testCalculateScore(){
        setUpCorrect(2, 2);
        assertEquals(975 ,boardManager.calculateScore(5));
    }

    @Test
    public void testUndo(){
        setUpCorrect(1, 2);
        Tile tile = (Tile) boardManager.getUndoStack().pop();
        assertFalse(boardManager.undo());
        boardManager.getUndoStack().push(new Tile(2));
        boardManager.getUndoStack().push(new Tile(3));
        assertTrue(boardManager.undo());
    }
    @Test
    public void testSolvable() {
        setUpCorrect(2, 2);
        boolean solvable = false;
        int inversions = 0;
        for (int x = 0; x != boardManager.getBoard().getNumTiles(); x++) {
            Tile currentTile = tiles.get(x);
            for (int y = x; y != boardManager.getBoard().getNumTiles(); y++) {
                Tile compareTile = tiles.get(y);
                if (currentTile.getId() > compareTile.getId()) {
                    inversions++;
                }
            }
        }
        //blank id is numtiles
        int blankId = boardManager.getBoard().getNumTiles();
        if ((isEven(boardManager.getBoard().getDimension()) && isEven(inversions)) ||
                (!isEven(boardManager.getBoard().getDimension())) &&
                        (blankOnOddRow(blankId, tiles, boardManager) == isEven(inversions))) {
            solvable = true;
        }
        assertTrue(solvable);
    }

    /**
     * Checkes whether blank tile is on odd row
     * @param blankId - blank tile id
     * @param tileList - list of tiles
     * @param boardManager - boardManager
     * @return
     */
    private boolean blankOnOddRow(int blankId, List<Tile> tileList, SlidingBoardManager boardManager) {
        int row = 1;
        int dimension = boardManager.getBoard().getDimension();
        for(int x = 0; x != tileList.size(); x++){
            if (tileList.get(x).getId() == blankId){
                if(row % 2 == 0){return false;}
                return true;
            }
            if((x + 1) % dimension == 0){row++;}
        }
        return false;
    }

    /**
     * Determines whether a quantity is an even number
     * @param x - given integer
     * @return
     */
    private boolean isEven(int x){
        return x%2 == 0;
    }

}

