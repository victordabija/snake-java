import javax.swing.*;
import java.awt.*;
import java.sql.SQLInput;
import java.util.LinkedList;

public class Snake extends JPanel {

    private Square food;
    private final LinkedList<Square> snake = new LinkedList<>();
    private String direction;

    public Snake() {
        snake.addFirst(new Square(7 * Square.squareSize, 7 * Square.squareSize));
        snake.addFirst(new Square(7 * Square.squareSize, 6 * Square.squareSize));

        direction = Util.UP;
        food = new Square(Util.getRandom(), Util.getRandom());
    }

    private void init(Graphics g) {
        // todo endgame
        if (isEndgame()) {
            System.out.println("EndGame");
        }

        Graphics2D g2d = (Graphics2D) g;

        drawSnake(g2d);
        drawFood(g2d);

        Square head = snake.getFirst();
        int headX = head.getX();
        int headY = head.getY();

        if (Util.UP.equals(getDirection())) {
            headY -= Square.squareSize;
        }

        if (Util.DOWN.equals(getDirection())) {
            headY += Square.squareSize;
        }

        if (Util.RIGHT.equals(getDirection())) {
            headX += Square.squareSize;
        }

        if (Util.LEFT.equals(getDirection())) {
            headX -= Square.squareSize;
        }

        if (headX != food.getX() || headY != food.getY()) {
            snake.removeLast();
        } else {
            food = new Square(Util.getRandom(), Util.getRandom());
        }

        head.setX(headX);
        head.setY(headY);

        snake.addFirst(new Square(headX, headY));
    }

    private boolean isEndgame() {
        Square head = snake.getFirst();

        if (head.getX() > (Util.cols - 1) * Square.squareSize || head.getY() > (Util.rows - 1) * Square.squareSize || head.getX() < 0 || head.getY() < 0) {
            return true;
        }

        LinkedList<Square> newSnake = snake;
        newSnake.removeFirst();

        for (Square s : newSnake) {
            if (head.getX() == s.getX() && head.getY() == s.getY()) {
                return true;
            }
        }

        return false;
    }

    public void drawSnake(Graphics2D g) {
        for (Square square : snake) {
            drawSquare(square, g, Util.snakeColor);
        }
    }

    public void drawFood(Graphics2D g) {
        drawSquare(food, g, Util.foodColor);
    }

    public static void drawSquare(Square square, Graphics2D g2d, Color color) {
        g2d.setPaint(color);
        g2d.drawRect(square.getX(), square.getY(), Square.squareSize, Square.squareSize);
        g2d.fillRect(square.getX(), square.getY(), Square.squareSize, Square.squareSize);
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Util.backgroundColor);
//        drawSnake(g);
        init(g);
    }
}
