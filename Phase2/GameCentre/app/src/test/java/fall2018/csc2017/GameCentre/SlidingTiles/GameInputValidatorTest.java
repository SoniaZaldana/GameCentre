package fall2018.csc2017.GameCentre.SlidingTiles;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class GameInputValidatorTest {
    String dimension;
    String undo;


    @Test
    public void processInput() {
        // Testing dimension too small and correct undo
        dimension = "1";
        undo = "2";
        Map<Boolean, String> modelMap = new HashMap<>();
        modelMap.put(false, "Too easy :) Try a harder dimension!");
        assertEquals(modelMap, GameInputValidator.processInput(dimension, undo));

        // Testing dimension too big and correct undo
        dimension  = "10";
        modelMap = new HashMap<>();
        modelMap.put(false, "Please enter a valid dimension less than 10!");
        assertEquals(modelMap, GameInputValidator.processInput(dimension, undo));

        // Testing undo too small and correct dimension
        dimension = "5";
        undo = "0";
        modelMap = new HashMap<>();
        modelMap.put(false, "Please enter an undo number greater than 0");
        assertEquals(modelMap, GameInputValidator.processInput(dimension, undo));

        // Testing correct dimension and correct undo
        undo = "2";
        modelMap = new HashMap<>();
        modelMap.put(true, "Valid");
        assertEquals(modelMap, GameInputValidator.processInput(dimension, undo));

    }
}