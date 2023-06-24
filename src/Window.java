import javax.swing.*;
import java.awt.event.*;

public class Window extends JFrame implements ActionListener {
    private final Snake snake;

    public Window() {
        super();

        snake = new Snake();
        add(snake);

        Timer timer = new Timer(Util.delay, this);
        timer.start();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(Util.width, Util.height);
        setTitle(Util.title);
        setLocationRelativeTo(null);
        setVisible(true);

        listenDirections();
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();
    }

    /**
     * Listens to key presses and changes snake's direction
     */
    public void listenDirections() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                int keyCode = e.getKeyCode();
                if (keyCode == 37 && !snake.getDirection().equals(Util.RIGHT)) {
                    snake.setDirection(Util.LEFT);
                    return;
                }

                if (keyCode == 39 && !snake.getDirection().equals(Util.LEFT)) {
                    snake.setDirection(Util.RIGHT);
                    return;
                }

                if (keyCode == 38 && !snake.getDirection().equals(Util.DOWN)) {
                    snake.setDirection(Util.UP);
                    return;
                }

                if (keyCode == 40 && !snake.getDirection().equals(Util.UP)) {
                    snake.setDirection(Util.DOWN);
                    return;
                }
            }
        });
    }
}
