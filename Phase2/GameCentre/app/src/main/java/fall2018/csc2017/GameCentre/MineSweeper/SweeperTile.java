package fall2018.csc2017.GameCentre.MineSweeper;

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




    public SweeperTile( boolean hasBomb)
    {
        this.bomb = hasBomb;

    }
    public SweeperTile() {}

    /**
     *
     * @return whether tile is marked by user as having a bomb.
     */
    public boolean isFlagged() {
        return flagged;
    }
    /**
     *
     * @param flag represents whether the tile is marked(user thinks it has a bomb
     */
    public void flag(boolean flag) {
        flagged = flag;
    }

    public boolean hasBomb(){
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
