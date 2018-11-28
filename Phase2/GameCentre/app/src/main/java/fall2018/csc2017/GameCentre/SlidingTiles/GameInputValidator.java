package fall2018.csc2017.GameCentre.SlidingTiles;
import java.util.HashMap;
import java.util.Map;

public class GameInputValidator {
    /**
     * Returns a hashmap with a key denoting if an input is valid and a matching value denoting
     * why this value might or might not be valid
     * @param dimensionSize - dimension of the board
     * @param undoMaxText - maximum number of undos
     * @return Hashmap
     */
    public static Map<Boolean, String> processInput(String dimensionSize, String undoMaxText) {
        Map<Boolean, String> validator = new HashMap<>();
        // Check dimension
        try {
            Integer dimension = Integer.parseInt(dimensionSize);
            if (dimension > 9) {
                validator.put(false, "Please enter a valid dimension less than 10!");
            } else if (dimension <= 1) {
                validator.put(false, "Too easy :) Try a harder dimension!");
            }

        } catch (NumberFormatException e) {
            validator.put(false, "Please enter a valid dimension!");
        }
        // Check undo
        try {
            Integer undoMax = Integer.parseInt(undoMaxText);
            if (undoMax <= 0) {
                validator.put(false, "Please enter an undo number greater than 0");
            }
        } catch (NumberFormatException e) {
            validator.put(false, "Please enter a valid undo number!");
        }

        
        if(validator.size() == 0){
            validator.put(true, "Valid");
        }
        return validator;
    }
}
