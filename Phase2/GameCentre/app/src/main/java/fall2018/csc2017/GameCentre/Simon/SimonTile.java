package fall2018.csc2017.GameCentre.Simon;

import fall2018.csc2017.GameCentre.Tile;

class SimonTile extends Tile {

    /**
     * Instantiates a Simon Tile object with a specific id
     * @param  id
     */
    private int color;
    SimonTile(int id) {
        super(id);
    }

    /**
     * Default constructor for Simon Tile
     */
    SimonTile() {
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}
