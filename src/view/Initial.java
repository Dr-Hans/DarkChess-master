package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Initial extends JFrame {

    private JPanel panel1 = new JPanel();//游戏界面
    private final int WIDTH;
    private final int HEIGHT;
    public final int Initial_SIZE;
    private GameController gameController;
    private static JLabel statusLabel;

    public Initial(int width, int height) throws IOException {

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

        addStartGame(panel1);
        addHumanvsAI(panel1);
        addTitle(panel1);

    }


    private void addHumanvsAI(JPanel panel) {
        ImageIcon imageIcon = new ImageIcon("./image1/pve.png");
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(WIDTH / 4, HEIGHT / 4, Image.SCALE_DEFAULT));

        JButton button = new JButton("P v E",imageIcon);
        button.setSize(200, 200);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setLocation(WIDTH * 3 / 5 + 50, HEIGHT / 10 + 250);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        button.setBackground(Color.LIGHT_GRAY);
        button.addActionListener(e -> {
            this.dispose();
        });
        add(button);
    }


    private void addTitle(JPanel panel){
        ImageIcon imageIcon = new ImageIcon("./image1/Title.png");
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(WIDTH / 2, HEIGHT / 4, Image.SCALE_DEFAULT));

        JButton button = new JButton(imageIcon);
        button.setLocation(WIDTH * 3 / 5 - 350, HEIGHT / 10 );
        button.setSize(500, 200);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        add(button);

    }
    private void addStartGame(JPanel panel) {
        ImageIcon imageIcon = new ImageIcon("./image1/pvp.png");
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(WIDTH / 4, HEIGHT / 4, Image.SCALE_DEFAULT));

        JButton button = new JButton("P v P", imageIcon);
        button.setLocation(WIDTH * 3 / 5 - 420, HEIGHT / 10 + 250);
        button.setSize(200, 200);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        button.setBackground(Color.LIGHT_GRAY);
        add(button);

        button.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                ChessGameFrame mainFrame = null;
                try {
                    mainFrame = new ChessGameFrame(1000, 720);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                mainFrame.setVisible(true);
                this.dispose();

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
