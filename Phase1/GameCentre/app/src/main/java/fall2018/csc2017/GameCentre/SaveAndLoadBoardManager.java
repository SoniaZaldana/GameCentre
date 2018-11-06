package fall2018.csc2017.GameCentre;

import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static android.content.Context.MODE_PRIVATE;


/**
 * Handles Saving and Loading the boardManager
 */
public class SaveAndLoadBoardManager {

    /**
     * Load the board manager from fileName.
     *
     * @param fileName the name of the file
     */
    public static BoardManager loadFromFile(Context context, String fileName) {
        BoardManager boardManager = null;
        try {
            InputStream inputStream = context.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                boardManager = (BoardManager) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
        return boardManager;
    }

    /**
     * Save the board manager to fileName.
     *
     * @param fileName the name of the file
     */
    public static void saveToFile(Context context, String fileName, BoardManager boardManager) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(boardManager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
