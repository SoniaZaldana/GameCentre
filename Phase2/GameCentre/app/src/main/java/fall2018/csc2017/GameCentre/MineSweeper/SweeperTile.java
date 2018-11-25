package fall2018.csc2017.GameCentre.MineSweeper;

import java.util.Random;

import fall2018.csc2017.GameCentre.Tile;

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
    /**
     * The type of bomb
     */
    private String BombType;


    SweeperTile(boolean hasBomb) {
        this.bomb = hasBomb;
        if (hasBomb) {
            Random rand = new Random();
            int n = rand.nextInt(10);
            if (n < 5) {
                this.BombType = "small";
            } else if (n < 8){
                this.BombType = "big";
            } else{
                this.BombType = "timed";
            }
        } else {
            this.BombType = "none";
        }
    }

    /**
     * BombType Getter. If no bomb, gives the string "none".
     * @return The type of bomb
     */
    String getBombType() {
        return this.BombType;
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

    boolean hasBomb(){
        return this.bomb;
    }

    void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    int getBombsAround() {
        return this.bombsAround;
    }

    void setBombsAround(int bombsAround) {
        this.bombsAround = bombsAround;
    }
}
