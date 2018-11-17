package fall2018.csc2017.GameCentre.MineSweeper;

import fall2018.csc2017.GameCentre.Tile;

public class SweeperTile extends Tile {
    private boolean bomb;
    private boolean isMarked;
    public SweeperTile(int id)
    {
        super(id);
    }
    public SweeperTile(int id, boolean hasBomb)
    {
        super(id);
        this.bomb = hasBomb;

    }
    public boolean isMarked() {
        return isMarked;
    }
    /**
     *
     * @param marked represents whether the tile is marked(user thinkts it has a bom"
     */
    public void mark(boolean marked) {
        isMarked = marked;
    }

    public boolean hasBomb(){
        return bomb;
    }
    public void setBomb(){
        bomb=true;
    }
}
