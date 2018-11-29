package fall2018.csc2017.GameCentre.MineSweeper;

import java.util.Random;
import fall2018.csc2017.GameCentre.Tile;

/**
 * A tile for the game minesweeper
 */
public class SweeperTile extends Tile {

    /**
     * whether tile has a bomb or not
     */
    private boolean bomb;
    /**
     * whether the user marks the tile as having a bomb.
     */
    private boolean flagged;
    /**
     * number of bombs around the tile. Default is -1, which means undeclared.
     */
    private int bombsAround=-1;

    private boolean isBombExploded = false;

    /**
     * The type of bomb
     */
    private BombTypes BombType;

    /**
     * Instantiates sweeper tile object and assigns it a random bomb type
     * @param hasBomb - whether tile contains a bomb
     */
    SweeperTile(boolean hasBomb) {
        this.bomb = hasBomb;
        if (hasBomb) {
            Random rand = new Random();
            int n = rand.nextInt(10);
            if (n < 5) {
                this.BombType = BombTypes.SMALL;
            } else if (n < 8){
                this.BombType = BombTypes.BIG;
            } else{
                this.BombType = BombTypes.TIMED;
            }
        } else {
            this.BombType = null;
        }
    }

    /**
     * BombType Getter. If no bomb, gives the string "none".
     * @return The type of bomb
     */
    public BombTypes getBombType() {
        return BombType;
    }

    SweeperTile() {}

    /**
     *
     * @return whether tile is marked by user as having a bomb.
     */
    boolean isFlagged() {
        return this.flagged;
    }
    /**
     *
     * @param flag represents whether the tile is marked(user thinks it has a bomb
     */
    void flag(boolean flag) {
        this.flagged = flag;
    }

    /**
     * returns whether a tile contains a bomb
     * @return boolean
     */
    boolean hasBomb() {
        return this.bomb;
    }
    public void setTileToNotFlaged(){
        this.flagged = false;
    }
    public void setTileToFlaged() {
        this.flagged = true;
    }
    public void setBombExploded() {
        this.isBombExploded = true;
    }

    /**
     * Sets whether a tile contains a bomb
     * @param bomb - boolean
     */
    void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    /**
     * Returns the number of bombs around this tile
     * @return int
     */
    int getBombsAround() {
        return this.bombsAround;
    }

    /**
     * Sets the number of bombs around this tile
     * @param bombsAround - integer number of bombs
     */
    void setBombsAround(int bombsAround) {
        this.bombsAround = bombsAround;
    }

    public boolean isBombExploded() {
        return isBombExploded;
    }
}
