package fall2018.csc2017.GameCentre.Score;

import android.content.Context;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import static java.lang.Integer.valueOf;

/**
 * A class dedicated to writing to and reading from text files
 */
class TextFileManager {

    /**
     * Returns a hash map containing coupled values for the contents of fileName
     * @param fileName - the file from which we are pulling the contents from
     * @return A hashmap containing coupled values <user><score> or <game><score>
     */
    static HashMap<String, String> getValue(Context context, String fileName) {
        HashMap<String, String> usernamesAndScores =  new LinkedHashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(context.getFilesDir(), fileName)));
            String line;
            while ((line = reader.readLine()) != null) {
                int index = line.indexOf(",");
                String user = line.substring(1, index);
                String score = line.substring(index + 1, line.length() - 1);
                usernamesAndScores.put(user, score);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usernamesAndScores;
    }

    /**
     * Saves scores into the corresponding text files (either the userScore.txt or game.txt)
     *
     * @param fileName      - File in which we are saving into
     * @param firstVariable - The corresponding variable to which score is saved
     *                      either user or game
     * @param scoreSave     - The score that will be saved
     */
    static void saveToFile(Context context, String fileName, String firstVariable, int scoreSave) {
        String entry = "[" + firstVariable + "," + scoreSave + "]";
        File scoreFile = new File(context.getFilesDir(), fileName);
        FileWriter fr;
        try {
            if (isHighScore(context, fileName, firstVariable, scoreSave)) {
                deletePreviousHighScore(context, fileName, firstVariable);
                fr = new FileWriter(scoreFile, true);
                fr.write(entry + "\n");
                fr.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks whether the new score obtained is a high score for the given user
     *
     * @param fileName  - file we are checking for previous high score (if any)
     * @param userScore - the user's new score achieved
     * @return whether it is a new high score or not
     */
    static boolean isHighScore(Context context, String fileName, String targetValue, int userScore) {
        boolean highScore = false;
        boolean userExists = false;
        String line;
        BufferedReader reader;
        int index;
        int scoreSaved;
        try {
            reader = new BufferedReader(new FileReader(new File(context.getFilesDir(), fileName)));
            // Checks whether file is empty, because empty file implies automatically a new high score
            if ((line = reader.readLine()) == null) {
                highScore = true;
            }
            reader.close();
            reader = new BufferedReader(new FileReader(new File(context.getFilesDir(), fileName)));
            while ((line = reader.readLine()) != null) {
                index = line.indexOf(",");
                if (line.substring(1, index).equals(targetValue)) {
                    userExists = true;
                    scoreSaved = valueOf(line.substring(index + 1, line.length() - 1));
                    if (userScore > scoreSaved) {
                        highScore = true;
                    }
                }
            }
            reader.close();
            if (!userExists){
                highScore = true;
            }
        } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", fileName);
            e.printStackTrace();
        }
        return highScore;
    }

    /**
     * Deletes the previous high score in the existing files for game and user scores
     *
     * @param fileName    - file from which we are deleting previous high score
     * @param targetValue - the score we are trying to find to delete that entry in the file
     */
    static void deletePreviousHighScore(Context context, String fileName, String targetValue) {
        String line;
        int index;
        BufferedReader reader;
        File fixedFile = new File(context.getFilesDir(), fileName + "temp");
        FileWriter fr;
        try {
            reader = new BufferedReader(new FileReader(new File(context.getFilesDir(), fileName)));
            fr = new FileWriter(fixedFile);
            while ((line = reader.readLine()) != null) {
                index = line.indexOf(",");
                if (!line.substring(1, index).equals(targetValue)) {
                    fr.write(line + "\n");
                }
            }
            fr.close();
            reader.close();
            File oldFile = new File(context.getFilesDir(), fileName);
            oldFile.delete();
            fixedFile.renameTo(new File(context.getFilesDir(), fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
