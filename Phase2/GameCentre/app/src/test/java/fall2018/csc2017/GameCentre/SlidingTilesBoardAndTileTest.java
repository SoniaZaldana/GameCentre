package fall2018.csc2017.GameCentre;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.GameCentre.SlidingTiles.MovementControllerSliding;
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
        List<Tile> tiles = makeTiles(dimension);
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
     * Test whether swapping two tiles makes a solved board unsolved.
     */
    @Test
    public void testIsSolved() {
        setUpCorrect(2,2);
        MovementControllerSliding movementController = new MovementControllerSliding(boardManager);
        assertEquals(true, movementController.isGameFinished());
        swapFirstTwoTiles();
        assertEquals(false, movementController.isGameFinished());
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

    /**
     * Test whether isValidHelp works.
     */
    @Test
    public void testIsValidTap() {
        //TODO maybe check different sizes
        setUpCorrect(4,4);
        MovementControllerSliding movementController = new MovementControllerSliding(boardManager);
        assertEquals(true, movementController.isValidTap(11));
        assertEquals(true, movementController.isValidTap(14));
        assertEquals(false, movementController.isValidTap(10));
        assertEquals(false, movementController.isValidTap(15));

    }
    /**
     * Test if touchMove works
     */
    @Test
    public void testTouchMove(){
        setUpCorrect(4,4);
        MovementControllerSliding movementController = new MovementControllerSliding(boardManager);
        boardManager.getBoard().swapTiles(3, 3, 3, 2);
        ArrayList<ArrayList<Tile>> actualTiles = boardManager.getBoard().getAllTiles();
        ArrayList<Integer> tileNum = new ArrayList<>();
        for(ArrayList<Tile> array:actualTiles){
            for(Tile t:array){
                tileNum.add(t.getId());
            }
        }
        ArrayList<Integer> expectedValues = new ArrayList<>();
        for(int i =1; i<=14; i++){
            expectedValues.add(i);
        }
        expectedValues.add(16);
        expectedValues.add(15);
        assertArrayEquals(expectedValues.toArray(),tileNum.toArray());
        movementController.touchMove(15);
        assertEquals(true, movementController.isGameFinished());


    }
    @Test
    public void testSlidingTilesBoard(){
        setUpCorrect(5,2 );
        assertEquals(4, boardManager.getBoard().getBlankId());
        setUpCorrect(5,3 );
        assertEquals(9, boardManager.getBoard().getBlankId());
    }


}

