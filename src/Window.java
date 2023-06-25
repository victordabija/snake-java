import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Window extends JFrame implements ActionListener {
    private Snake snake;

    private final Timer timer;

    public Window() {
        super();

        snake = new Snake();
        add(snake);

        timer = new Timer(Util.delay, this);
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
        if (snake.isEndGame()) {
            timer.stop();

            Object[] options = {"OK"};
            JOptionPane.showOptionDialog(this, "Score " + snake.getSnakeLength(), "You Lost", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            System.exit(0);
        }
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
