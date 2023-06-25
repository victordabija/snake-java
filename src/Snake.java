import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Snake extends JPanel {

    private Square food;
    private final ArrayList<Square> snake = new ArrayList<>();
    private String direction;

    private boolean endGame = false;

    public Snake() {
        snake.add(new Square(7 * Square.squareSize, 7 * Square.squareSize));
        snake.add(new Square(6 * Square.squareSize, 7 * Square.squareSize));

        direction = Util.RIGHT;
        food = generateFood();
    }

    private void init(Graphics g) {
        Square head = snake.get(0);
        int headX = head.getX();
        int headY = head.getY();

        checkEndgame();

        Graphics2D g2d = (Graphics2D) g;

        drawSnake(g2d);
        drawFood(g2d);

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
            snake.remove(snake.size() - 1);
        } else {
            food = generateFood();
        }

        snake.add(0, new Square(headX, headY));
    }

    public void checkEndgame() {
        Square head = snake.get(0);
        int headX = head.getX();
        int headY = head.getY();


        if (headX > (Util.cols - 1) * Square.squareSize || headY > (Util.rows - 2) * Square.squareSize || headX < 0 || headY < 0) {
            endGame = true;
            return;
        }

        for (int i = 1; i < snake.size(); i++) {
            if (headX == snake.get(i).getX() && headY == snake.get(i).getY()) {
                endGame = true;
                return;
            }
        }
    }

    public void drawSnake(Graphics2D g) {
        for (Square square : snake) {
            drawSquare(square, g, Util.snakeColor);
        }
    }

    public void drawFood(Graphics2D g) {
        drawSquare(food, g, Util.foodColor);
    }

    public Square generateFood() {
        Square food = new Square(Util.getRandom(), Util.getRandom());

        if (isInSnakeBody(food)) {
            return generateFood();
        }

        return food;
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

    public boolean isEndGame() {
        return endGame;
    }

    public boolean isInSnakeBody(Square square) {
        for (Square s : snake) {
            if (square.getX() == s.getX() && square.getY() == s.getY()) {
                return true;
            }
        }

        return false;
    }

    public int getSnakeLength() {
        return snake.size() - 1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Util.backgroundColor);
        init(g);
    }
}
