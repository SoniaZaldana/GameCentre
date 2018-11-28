package fall2018.csc2017.GameCentre;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * A Tile in a sliding tiles puzzle.
 */
public class Tile implements Comparable<Tile>, Serializable {

    /**
     * The unique id.
     */
    private int id;


    /**
     * Return the tile id.
     *
     * @return the tile id
     */
    public int getId() {
        return id;
    }

    /**
     * A tile with an id
     *
     * @param id given id of the tile.
     */
    public Tile(int id) {
        this.id = id;
    }

    /**
     * Default constructor for tile
     */
    public Tile(){

    }

    /**
     * Set the tile id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(@NonNull Tile o) {
        return o.id - this.id;
    }
}
