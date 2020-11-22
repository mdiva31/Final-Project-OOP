import java.awt.*;
import javax.swing.*;

public class SnakeGame {
        public static void main (String[] args) {
        JFrame snake = new JFrame();
        snake.setTitle("Snake Game GUI Java");
        snake.setBounds(0,0, 2500, 2500);
        snake.setBackground(Color.DARK_GRAY);
        snake.setResizable(false);
        snake.setVisible(true);
        snake.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Board board = new Board();
        SnakeBody body = new SnakeBody();
        snake.add(board);
        }

}