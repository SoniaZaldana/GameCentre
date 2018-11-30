package fall2018.csc2017.GameCentre.Score;

import org.junit.Test;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class TextFileManagerTest {

    @Test
    public void getUsernameAndScoresMap() {
        String[] fileArray = {"user1, 400", "user2, 200", "user3, 300"};
        Map map = TextFileManager.getUsernameAndScoresMap(fileArray);
        Set keySet = map.keySet();
        assertTrue(keySet.contains("user1"));
        assertTrue(keySet.contains("user2"));
        assertTrue(keySet.contains("user3"));
        Collection values = map.values();
        assertTrue(values.contains((double) 400));
        assertTrue(values.contains((double) 200));
        assertTrue(values.contains((double) 300));
    }

    @Test
    public void isHighScore(){
        String[] fileArray = {"user1, 400", "user2, 200", "user3, 300"};
        assertTrue(TextFileManager.isHighScore(fileArray, "user1", 500));
        assertTrue(TextFileManager.isHighScore(fileArray, "user5", 500));
    }
}