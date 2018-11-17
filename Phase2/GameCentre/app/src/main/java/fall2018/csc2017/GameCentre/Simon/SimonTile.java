package fall2018.csc2017.GameCentre.Simon;

import fall2018.csc2017.GameCentre.Tile;

class SimonTile extends Tile {
    /**
     * The colour of this tile
     */
    private String colour;

    /**
     * Instantiates a Simon Tile object with a specific id
     * @param  id
     */
    SimonTile(int id) {
        super(id);
    }

    /**
     * Default constructor for Simon Tile
     */
    SimonTile() {

    }

    /**
     * Returns the colour of a certain tile
     * @return String colour
     */
    String getColour(){
        return this.colour;
    }
}
