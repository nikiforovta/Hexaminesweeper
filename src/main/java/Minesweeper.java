import sweeper.Box;
import sweeper.Coord;
import sweeper.Game;
import sweeper.Ranges;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Minesweeper extends JFrame {

    private Game game;

    private JPanel panel;
    private JLabel label;
    private int COLS = 9;
    private int ROWS = 9;
    private int BOMBS = 10;
    private final int IMAGE_SIZE = 50;
    private final int DELTA_X = 23;
    private final int DELTA_Y = 13;

    public static void main(String[] args) {
        new Minesweeper().initFrame();
    }

    private Minesweeper() {
        initDialog();
        game = new Game(COLS, ROWS, BOMBS);
        game.start();
        setImages();
        initLabel();
        initPanel();
        initFrame();
    }

    private void initDialog() {
        JPanel jpanel = new JPanel();
        String option = JOptionPane.showInputDialog(jpanel, "Введите размеры поля и количество мин через пробел \n" +
                        "*количество строк* *количество рядов* *количество мин*",
                "Настройка", JOptionPane.INFORMATION_MESSAGE);
        String[] input = option.split(" ");
        COLS = Integer.parseInt(input[0]);
        ROWS = Integer.parseInt(input[1]);
        BOMBS = Integer.parseInt(input[2]);

    }

    private void initLabel() {
        label = new JLabel(("Welcome!"));
        add(label, BorderLayout.SOUTH);
    }

    private void initPanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coord coord : Ranges.getAllCoords())
                    g.drawImage((Image) game.getBox(coord).image, coord.x * IMAGE_SIZE + DELTA_X * (coord.y % 2),
                            coord.y * (IMAGE_SIZE - DELTA_Y), this);
            }
        };
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    int x;
                    int y = 0;
                    for (int i = 1; i < 3 * ROWS; i += 3) {
                        if (i * IMAGE_SIZE / 4 < e.getY() && (i + 2) * IMAGE_SIZE / 4 > e.getY()) break;
                        y++;
                    }
                    if (y == ROWS && !(3 * ROWS * IMAGE_SIZE / 4 < e.getY() &&
                            (3 * ROWS + 2) * IMAGE_SIZE / 4 > e.getY()))
                        y = -1;
                    x = (e.getX() - DELTA_X * (y % 2)) / IMAGE_SIZE;
                    Coord coord = new Coord(x, y);
                    if (e.getButton() == MouseEvent.BUTTON1)
                        game.pressLeftButton(coord);
                    if (e.getButton() == MouseEvent.BUTTON2)
                        game.start();
                    if (e.getButton() == MouseEvent.BUTTON3)
                        game.pressRightButton(coord);
                    label.setText(getMessage()); }
                catch (NullPointerException ignored) {
                }
                    panel.repaint();
            }
        });
        panel.setPreferredSize(new Dimension(Ranges.getSize().x * IMAGE_SIZE + DELTA_X,
                Ranges.getSize().y * IMAGE_SIZE - DELTA_Y * (Ranges.getSize().y - 1)));
        add(panel);
    }

    private String getMessage() {
        switch (game.getState()) {
            case PLAYED:
                return "Think twice!";
            case BOMBED:
                return "YOU LOSE!";
            case WINNER:
                return "Congratulations!";
            default:
                return "Welcome!";
        }
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hexaminesweeper");
        setResizable(false);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        setIconImage(getImage("icon"));
    }

    private void setImages() {
        for (Box box : Box.values())
            box.image = getImage(box.name().toLowerCase());
    }

    private Image getImage(String name) {
        String filename = "img/" + name.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }
}
