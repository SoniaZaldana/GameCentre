package fall2018.csc2017.GameCentre.Simon;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimonTileTest {
    private SimonTile simonTile;

    @Before
    public void setUp() throws Exception {
        simonTile = new SimonTile(2);
    }

    @Test
    public void setColor() {
        simonTile.setColor(10);
        assertEquals(10, simonTile.getColor());
    }

    @Test
    public void getColor() {
        simonTile.setColor(20);
        assertEquals(20, simonTile.getColor());
    }
}