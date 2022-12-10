package view;

import controller.GameController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Initial extends JFrame {
    private final int WIDTH;
    private final int HEIGHT;
    public final int Initial_SIZE;
    private GameController gameController;
    private static JLabel statusLabel;

    public Initial(int width, int height) {

        setTitle("Dark Chess"); //设置标题
        this.WIDTH = width;
        this.HEIGHT = height;
        this.Initial_SIZE = HEIGHT * 4 / 5;

        setBackground(Color.red);


        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); // Center the window.
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        addStartGame();
        addHumanvsAI();

    }


    private void addHumanvsAI() {
        JButton button = new JButton("P v E");
        button.setLocation(WIDTH * 3 / 5 - 400, HEIGHT / 10 + 300);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        button.setBackground(Color.LIGHT_GRAY);
        add(button);
    }

    private void addStartGame() {
        JButton button = new JButton("P v P");
        button.setLocation(WIDTH * 3 / 5 - 400, HEIGHT / 10 + 150);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        button.setBackground(Color.LIGHT_GRAY);
        add(button);

        button.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                ChessGameFrame mainFrame = null;
                try {
                    mainFrame = new ChessGameFrame(880, 720);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                mainFrame.setVisible(true);

            });

        });

    }

    public static class ImageComponent extends JComponent {
        Image paintImage;

        public ImageComponent(Image image) {
            this.setLayout(null);
            this.setFocusable(true);//Sets the focusable state of this Component to the specified value. This value overrides the Component's default focusability.
            this.paintImage = image;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(paintImage, 0, 0, 880, 720, this);
        }

    }
}
