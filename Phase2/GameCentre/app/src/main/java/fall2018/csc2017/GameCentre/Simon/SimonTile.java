package fall2018.csc2017.GameCentre.Simon;

import fall2018.csc2017.GameCentre.Tile;

class SimonTile extends Tile {

    /**
     * This tile's color
      */
    private int color;

    /**
     * Instantiates a simon Tile object with a specific id
     * @param  id
     */
    SimonTile(int id) {
        super(id);
    }

    /**
     * Default constructor for simon Tile
     */
    SimonTile() {
    }

    /**
     * Sets the tile's color
     * @param color - integer representing color
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * Returns the tile's color
     * @return integer
     */
    public int getColor() {
        return color;
    }
}
