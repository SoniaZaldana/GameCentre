package fall2018.csc2017.GameCentre;

public class SwapableBoard extends Board{
    public void swapTiles(int row1, int col1, int row2, int col2) {
        Tile tempValue = getTile(row1, col1);
        setTile(row1,col1, getTile(row2, col2));
        setTile(row2,col2, tempValue);
    }
}
