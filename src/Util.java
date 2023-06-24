import java.awt.*;
import java.util.Random;

public class Util {
    public static final int delay = 200;
    public static final String title = "Snake";
    public static final int height = 750;
    public static final int width = 750;
    public static final int rows = 15;
    public static final int cols = 15;
    public static final Color backgroundColor = Color.decode("#242424");
    public static final String UP = "up";
    public static final String DOWN = "down";
    public static final String RIGHT = "right";
    public static final String LEFT = "left";
    public static final Color snakeColor = Color.cyan;
    public static final Color foodColor = Color.red;

    public static int getRandom() {
        int random = (int) (Math.random() * (height - Square.squareSize));
        return (int) (Math.floor((double) random / Square.squareSize) * Square.squareSize);
    }
}
