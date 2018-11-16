package fall2018.csc2017.GameCentre;

public class SweeperTile extends Tile {
    boolean bomb;
    boolean isMarked;
    SweeperTile(int id)
    {
        super(id);
    }
    SweeperTile(int id, boolean hasBomb)
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

    boolean hasBomb(){
        return bomb;
    }
    void setBomb(){
        bomb=true;
    }
}
