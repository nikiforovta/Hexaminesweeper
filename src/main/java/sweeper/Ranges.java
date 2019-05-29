package sweeper;

import java.util.ArrayList;
import java.util.Random;

public class Ranges {

    private static Coord size;
    private static ArrayList<Coord> allCoords;
    private static Random random = new Random();


    static void setSize(Coord _size) {
        size = _size;
        allCoords = new ArrayList<>();
        for (int y = 0; y < size.y; y++) {
            for (int x = 0; x < size.x; x++) {
                allCoords.add(new Coord(x, y));
            }
        }
    }


    public static Coord getSize() {
        return size;
    }

    public static ArrayList<Coord> getAllCoords() {
        return allCoords;
    }

    static boolean inRange(Coord coord) {
        return coord.x >= 0 && coord.x < size.x && coord.y >= 0 && coord.y < size.y;
    }

    static Coord getRandomCoord() {
        return new Coord(random.nextInt(size.x), random.nextInt(size.y));
    }

    static ArrayList<Coord> getCoordsAround(Coord coord) {
        Coord around;
        int remove = coord.y % 2;
        ArrayList<Coord> list = new ArrayList<>();
        for (int x = coord.x - 1; x <= coord.x + 1; x++)
            for (int y = coord.y - 1; y <= coord.y + 1; y++)
                if (inRange(around = new Coord(x, y)))
                    if (!around.equals(coord) &&
                            (remove == 1 && !around.equals(new Coord(coord.x - 1, coord.y - 1))
                                    && !around.equals(new Coord(coord.x - 1, coord.y + 1))
                                    || remove == 0 && !around.equals(new Coord(coord.x + 1, coord.y - 1))
                                    && !around.equals(new Coord(coord.x + 1, coord.y + 1))))
                        list.add(around);
        return list;
    }
}