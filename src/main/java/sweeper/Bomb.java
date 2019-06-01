package sweeper;

class Bomb {

    private Matrix bombMap;
    private int totalBombs;

    Bomb(int totalBombs) {
        this.totalBombs = totalBombs;
        fixBombsCount();
    }

    void start() {
        bombMap = new Matrix(Box.ZERO);
        for (int i = 0; i < totalBombs; i++) {
            placeBomb();
        }
    }

    Box get(Coord coord) {
        return bombMap.get(coord);
    }

    private void fixBombsCount() {
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y / 2;
        if (totalBombs > maxBombs) totalBombs = maxBombs;
    }

    private void placeBomb() {
        while (true) {
            Coord coord = Ranges.getRandomCoord();
            if (Box.BOMB == bombMap.get(coord)) continue;
            bombMap.set(coord, Box.BOMB);
            incNumbersAroundBomb(coord);
            break;
        }
    }

    private void incNumbersAroundBomb(Coord coord) {
        for (Coord around : Ranges.getCoordsAround(coord))
            if (Box.BOMB != bombMap.get(around))
                bombMap.set(around, bombMap.get(around).getNextNumberBox());
    }

    int getTotalBombs() {
        return totalBombs;
    }

    void restart(Coord coord, int cols, int rows) {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                Coord coord1 = new Coord(i, j);
                bombMap.set(coord1, Box.ZERO);
            }
        }
        for (int i = 0; i < totalBombs; i++) placeBomb(coord);
    }

    private void placeBomb(Coord coord) {
        while (true) {
            Coord coord1 = Ranges.getRandomCoord();
            if (!coord.equals(coord1)) {
                if (Box.BOMB == bombMap.get(coord1)) continue;
                bombMap.set(coord1, Box.BOMB);
                incNumbersAroundBomb(coord1);
            }
            break;
        }
    }
}