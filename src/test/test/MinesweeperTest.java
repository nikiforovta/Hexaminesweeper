import org.junit.Test;
import sweeper.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class MinesweeperTest {

    @Test
    public void equals() {
        Coord test1 = new Coord(1, 0);
        Coord test2 = new Coord(1, 0);
        String test3 = "1, 0";
        assertEquals(test1, test2);
        assertEquals(test2, test1);
        assertNotEquals(test1, test3);
        assertNotEquals(test2, test3);
    }

    @Test
    public void getNextNumberBox() {
        assertEquals(Box.NUM3, Box.NUM2.getNextNumberBox());
        assertEquals(Box.NUM5, Box.NUM4.getNextNumberBox());
        assertEquals(Box.BOMB, Box.NUM6.getNextNumberBox());
    }

    @Test
    public void inRange() {
        new Game(7, 7, 85);
        assertTrue(Ranges.inRange(new Coord(5, 5)));
        assertTrue(Ranges.inRange(new Coord(0, 0)));
        assertFalse(Ranges.inRange(new Coord(-3, 2)));
        assertFalse(Ranges.inRange(new Coord(2, 85)));
        assertFalse(Ranges.inRange(new Coord(7, 7)));
    }

    @Test
    public void getCoordsAround() {
        new Game(7, 7, 85);
        assertEquals(new ArrayList<>(Arrays.asList(new Coord(4, 5), new Coord(5, 4), new Coord(5, 6),
                new Coord(6, 4), new Coord(6, 5), new Coord(6, 6))), Ranges.getCoordsAround(new Coord(5, 5)));
        assertEquals(new ArrayList<>(Arrays.asList(new Coord(0, 1), new Coord(0, 2), new Coord(0, 3),
                new Coord(1, 1), new Coord(1, 3), new Coord(2, 2))), Ranges.getCoordsAround(new Coord(1, 2)));
        assertEquals(new ArrayList<>(Arrays.asList(new Coord(0, 1), new Coord(1, 0))),
                Ranges.getCoordsAround(new Coord(0, 0)));
    }

    @Test
    public void fixBombsCount() {
        new Game(4, 4, 3);
        assertEquals(3, Game.getBombs());
        new Game(7, 7, 85);
        assertEquals(24, Game.getBombs());
        new Game(6, 6, 24);
        assertEquals(18, Game.getBombs());
    }
}
