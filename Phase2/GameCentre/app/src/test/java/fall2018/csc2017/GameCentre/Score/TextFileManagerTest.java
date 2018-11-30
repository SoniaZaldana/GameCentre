package fall2018.csc2017.GameCentre.Score;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class TextFileManagerTest {

    @Test
    public void getUsernameAndScoresMap() {
        String[] fileArray = {"user1, 100", "user2, 200", "user3, 300"};
        Map map = TextFileManager.getUsernameAndScoresMap(fileArray);
        map.keySet

    }
}