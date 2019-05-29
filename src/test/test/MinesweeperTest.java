import org.junit.Test;
import sweeper.Coord;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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

}
