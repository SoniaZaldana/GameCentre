package fall2018.csc2017.GameCentre.MineSweeper;

import fall2018.csc2017.GameCentre.Tile;

import java.util.Random;

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
    private int bombsAround = -1;

    /**
     * The type of bomb
     */
    private String BombType;


    public SweeperTile(boolean hasBomb) {
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
    public String getBombType() {
        return BombType;
    }



    public SweeperTile() {
    }

    /**
     * @return whether tile is marked by user as having a bomb.
     */
    public boolean isFlagged() {
        return flagged;
    }

    /**
     * @param flag represents whether the tile is marked(user thinks it has a bomb
     */
    public void flag(boolean flag) {
        flagged = flag;
    }

    public boolean hasBomb() {
        return bomb;
    }

    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    public int getBombsAround() {
        return bombsAround;
    }

    public void setBombsAround(int bombsAround) {
        this.bombsAround = bombsAround;
    }
}
