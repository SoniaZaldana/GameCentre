package fall2018.csc2017.GameCentre.Score;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    static Map<String, Double> getValue(Context context, String fileName) {
        Map<String, Double> usernameAndScores =  new LinkedHashMap<>();
        try{
            String[] fileArray = getScoreFileAsArray(context, fileName);
            usernameAndScores = getUsernameAndScoresMap(fileArray);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return usernameAndScores;
    }

    /**
     * Returns the score file as a string array. Example: ["Nick,980.4", "Lyuba, 1000.3", "Suguru, 123"]
     * @param context context of the application
     * @param fileName name of file
     * @return
     * @throws IOException
     */
    @NonNull
    private static String[] getScoreFileAsArray(Context context, String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(context.getFilesDir(), fileName)));
        String fileString = reader.lines().collect(Collectors.joining());
        reader.close();
        String[] fileArray ;
        if(fileString.length() != 0){
            String splitRegex = "\\]\\[";
            fileArray = fileString.split(splitRegex);
            // remove the "[" from the first arrayElement
            String elFirst = fileArray[0];
            fileArray[0] = elFirst.substring(1);
            // remove the "]" from the last arrayElement
            String elLast = fileArray[fileArray.length-1];
            fileArray[fileArray.length-1] = elLast.substring(0, elLast.length()-1);


        }
        else{
            fileArray = new String[0];

        }
        return fileArray;
    }

    /**
     * Helper which returns the hashMap which contains the username and the score
     * @param fileArray array where each row is a username and a score with the format: "user, 980"
     * @return
     */
    static Map<String, Double> getUsernameAndScoresMap(String[] fileArray){
        int indexWithinUserandScoreList;
        int counter = 0;
        String line;
        List<String> usernames = new ArrayList<>();
        List<Double> scoresHighToLow = new ArrayList<>();
        Map<String, Double> usernameAndScores =  new LinkedHashMap<>();
        while (counter<fileArray.length) {
            line = fileArray[counter];
            int index = line.indexOf(",");
            String user = line.substring(0, index);
            Double score = Double.parseDouble(line.substring(index + 1, line.length()));
            // indexWithinUserandScoreList is the index at which to place the score inside the list.
            indexWithinUserandScoreList = 0;
            boolean foundIndex = false;
            while (indexWithinUserandScoreList < scoresHighToLow.size() && !foundIndex) {
                if (score < scoresHighToLow.get(indexWithinUserandScoreList)) {
                    indexWithinUserandScoreList++;
                } else {
                    foundIndex = true;
                }
            }
            scoresHighToLow.add(indexWithinUserandScoreList, score);
            usernames.add(indexWithinUserandScoreList, user);
            counter++;
        }
        indexWithinUserandScoreList = 0;
        while(indexWithinUserandScoreList<scoresHighToLow.size()) {
            usernameAndScores.put(usernames.get(indexWithinUserandScoreList), scoresHighToLow.get(indexWithinUserandScoreList));
            indexWithinUserandScoreList++;
        }
        return usernameAndScores;
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
    static boolean isHighScore(Context context, String fileName, String targetValue, int userScore){
        boolean highScore = false;
        try {
            String[] fileArray = getScoreFileAsArray(context, fileName);
            highScore = isHighScore(fileArray, targetValue, userScore);

        } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", fileName);
            e.printStackTrace();
        }
        return highScore;
    }

    static boolean isHighScore(String[] fileArray, String targetValue, int userScore){
        boolean highScore = false;
        int index;
        int scoreSaved;
        int counter = 0;
        boolean userExists=false;
        while (counter<fileArray.length) {
            String line = fileArray[counter];
            index = line.indexOf(",");
            if (line.substring(0, index).equals(targetValue)) {
                userExists = true;
                scoreSaved = valueOf(line.substring(index + 2, line.length()));
                if (userScore > scoreSaved) {
                    highScore = true;
                }
            }
            counter++;
        }
        if (!userExists){
            highScore = true;
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
