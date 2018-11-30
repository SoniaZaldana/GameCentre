package fall2018.csc2017.GameCentre;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class BoardTest {
    private List<Tile> listTiles;

    private void createValidList(){
        listTiles = new ArrayList<>();
        for (int x = 0; x != 4; x++) {
            Tile tile = new Tile(x+1);
            listTiles.add(tile);
        }
    }

    @Test
    public void setDimension() {
        createValidList();
        Board board = new Board(2, listTiles);
        assertEquals(2, board.getDimension());
        board.setDimension(4);
        assertEquals(4, board.getDimension());
    }

    @Test
    public void getAllTiles() {

        createValidList();
        Board board = new Board(2, listTiles);
        Iterator<Tile> iter = listTiles.iterator();
        ArrayList<ArrayList<Tile>> myTiles = new ArrayList<>();
        // Populate myTiles
        for (int row = 0; row != board.getDimension(); row++) {
            ArrayList<Tile> rowTile = new ArrayList<>();
            for (int col = 0; col != board.getDimension(); col++) {
                rowTile.add(iter.next());
            }
            myTiles.add(rowTile);
        }
        // Compare myTiles and tiles in board
        ArrayList<ArrayList<Tile>> boardAllTiles = board.getAllTiles();
        assertEquals(myTiles, boardAllTiles);

    }


    @Test
    public void getDimension() {
        createValidList();
        Board board = new Board(3, listTiles);
        assertEquals(3, board.getDimension());
    }

    @Test
    public void getTile() {
        createValidList();
        Board board = new Board(2, listTiles);
        Tile tile = new Tile(1);
        Tile receivedTile = board.getTile(0,0);
        assertTrue(tile.compareTo(receivedTile) == 0);
    }

    @Test
    public void setTile() {
        createValidList();
        Board board = new Board(2, listTiles);
        Tile tile = new Tile(3);
        board.setTile(0,0, tile);
        Tile compareTile = board.getTile(0,0);
        assertTrue(compareTile.compareTo(tile) == 0);
    }

    @Test
    public void setTiles() {
        createValidList();
        Board board = new Board(2, listTiles);
        ArrayList<ArrayList<Tile>> myTiles = new ArrayList<>();
        board.setTiles(myTiles);
        assertEquals(myTiles, board.getAllTiles());
    }

    @Test
    public void getNumTiles() {
        createValidList();
        Board board = new Board(2, listTiles);
        assertEquals(4, board.getNumTiles());
    }

    @Test
    public void testToString() {
        createValidList();
        Board board = new Board(2, listTiles);
        String expectedResult = "tiles = [1, 2, 3, 4]";
        assertEquals(expectedResult, board.toString());
    }

    @Test
    public void iterator() {

    }
}