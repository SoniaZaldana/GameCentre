package fall2018.csc2017.GameCentre.SlidingTiles;
import java.util.HashMap;
import java.util.Map;



public class InputValidator {
    public static Map<Boolean, String> controllerChoosingInput(String dimensionSize, String undoMaxText) {
        Map<Boolean, String> validator = new HashMap<>();
        try {
            Integer dimension = Integer.parseInt(dimensionSize);
            if (dimension > 9) {
                validator.put(false, "Please enter a valid dimension less than 10!");
            } else if (dimension <= 1) {
                validator.put(false, "Too easy :) Try a harder dimension!");
            } else {
                try {
                    Integer undoMax = Integer.parseInt(undoMaxText);
                    if (undoMax <= 0) {
                        validator.put(false, "Please enter an undo number greater than 0");
                    }
                } catch (NumberFormatException e) {
                    validator.put(false, "Please enter a valid undo number!");
                }
            }
        } catch (NumberFormatException e) {
            validator.put(false, "Please enter a valid dimension!");
        }
        if(validator.size() == 0){
            validator.put(true, "Valid");
        }
        return validator;
    }
}
