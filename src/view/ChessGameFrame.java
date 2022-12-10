package view;

import controller.GameController;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * 这个类表示游戏窗体，窗体上包含：
 * 1 Chessboard: 棋盘
 * 2 JLabel:  标签
 * 3 JButton： 按钮
 */
public class ChessGameFrame extends JFrame {
    private final int WIDTH;
    private final int HEIGHT;
    public final int CHESSBOARD_SIZE;
    private GameController gameController;
    private static JLabel statusLabel;

    private JPanel panel0 = new JPanel();//开始界面
    private JPanel panel1 = new JPanel();//游戏界面

    public ChessGameFrame(int width, int height) throws IOException {
        setTitle("2022 CS109 Project by 韩汶霖 and 马焌柠"); //设置标题
        this.WIDTH = width;
        this.HEIGHT = height;
        this.CHESSBOARD_SIZE = HEIGHT * 4 / 5;

        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); // Center the window.
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        panel0.setSize(WIDTH, HEIGHT);
        panel0.setLayout(null);

        getContentPane().add(panel0);
        setVisible(true);



        addChessboard(panel0);
        addLabel(panel0);
        addStoreButton(panel0);
        addRestartButton();
        addLoadButton(panel0);

        Image image = new ImageIcon("./image1/background.png").getImage();
        JLabel jLabel = new ALabel(image);
        jLabel.setSize(WIDTH, HEIGHT);
        panel0.add(jLabel);
    }


    /**
     * 在游戏窗体中添加棋盘
     */
    private void addChessboard(JPanel panel) throws IOException {
        Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE / 2, CHESSBOARD_SIZE);
        gameController = new GameController(chessboard);
        chessboard.setLocation(HEIGHT / 10, HEIGHT / 10);
        panel.add(chessboard);
    }

    /**
     * 在游戏窗体中添加标签
     */
    private void addLabel(JPanel panel) {
        statusLabel = new JLabel("Reveal A Chess To Start!");
        statusLabel.setLocation(WIDTH * 3 / 5, HEIGHT / 10);
        statusLabel.setSize(300, 60);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        panel.add(statusLabel);
    }

    public void refreshLabel() {
        ChessGameFrame.getStatusLabel().setText("Reveal A Chess To Start!");
    }

    public static JLabel getStatusLabel() {
        return statusLabel;
    }

    /**
     * 在游戏窗体中增加一个按钮，如果按下的话就会显示Hello, world!
     */

    private void addRestartButton() {
        JButton button = new JButton("Restart Game");
        button.addActionListener((e) -> {
            JOptionPane.showMessageDialog(this, "Restart");
            try {
                gameController.restartGame();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            refreshLabel();
        });
        button.setLocation(WIDTH * 3 / 5, HEIGHT / 10 + 120);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }


    private void addLoadButton(JPanel panel) {
        ImageIcon imageIcon = new ImageIcon("./image1/load game image.png");
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(WIDTH / 4, HEIGHT / 10, Image.SCALE_DEFAULT));

        JButton button = new JButton(imageIcon);
        button.setLocation(WIDTH * 3 / 5, HEIGHT / 10 + 240);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setSize(WIDTH / 4, HEIGHT / 10);
        // button.setSize(180, 60);
        //button.setFont(new Font("Rockwell", Font.BOLD, 20));
        // button.setBackground(Color.LIGHT_GRAY);
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click load");
//            String path = JOptionPane.showInputDialog(this, "Input load path here");

            // 创建一个默认的文件选取器
            JFileChooser fileChooser = new JFileChooser();

            // 设置默认显示的文件夹为当前文件夹
            fileChooser.setCurrentDirectory(new File("."));

            // 设置文件选择的模式（只选文件、只选文件夹、文件和文件均可选）
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            // 设置是否允许多选
            fileChooser.setMultiSelectionEnabled(false);

            // 添加可用的文件过滤器（FileNameExtensionFilter 的第一个参数是描述, 后面是需要过滤的文件扩展名 可变参数）
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("json", "json"));
            // 设置默认使用的文件过滤器
            fileChooser.setFileFilter(new FileNameExtensionFilter("txt", "txt"));

            // 打开文件选择框（线程将被阻塞, 直到选择框被关闭）
            int result = fileChooser.showOpenDialog(panel);

            if (result == JFileChooser.APPROVE_OPTION) {
                // 如果点击了"确定", 则获取选择的文件路径
                File file = fileChooser.getSelectedFile();

                // 如果允许选择多个文件, 则通过下面方法获取选择的所有文件
                // File[] files = fileChooser.getSelectedFiles();
                final JTextArea msgTextArea = new JTextArea(10, 30);
                msgTextArea.setLineWrap(true);
                panel.add(msgTextArea);
                msgTextArea.append("打开文件: " + file.getAbsolutePath() + "\n\n");

                if (gameController.loadGameFromFile(file)) {
                    getContentPane().add(panel0);
                    setVisible(true);
                    validate();
                    repaint();
                }
            }
        });
        panel.add(button);
    }

    private void addStoreButton(JPanel panel) {
        ImageIcon imageIcon = new ImageIcon("./image1/da01d7380f6bb5b2eafc08a354a77339.png");
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(WIDTH / 5, HEIGHT * 3 / 38, Image.SCALE_DEFAULT));
        JButton storeButton = new JButton(imageIcon);
        storeButton.setLocation(WIDTH * 7 / 10, HEIGHT * 12 / 19);
        storeButton.setSize(WIDTH / 5, HEIGHT * 3 / 38);
        storeButton.setBorderPainted(false);
        storeButton.setContentAreaFilled(false);
        storeButton.setOpaque(false);
//        JButton storeButton = new JButton("Store");
//        storeButton.setFont(new Font("Rockwell", Font.BOLD, 20));
//        storeButton.setSize(WIDTH / 5, HEIGTH * 3 / 38);
//        storeButton.setLocation(WIDTH * 7 / 10, HEIGTH * 12 / 19);
//        storeButton.setBorderPainted(false);
//        storeButton.setBackground(new Color(205, 183, 159));
        storeButton.addActionListener(e -> {
            System.out.println("Click store");
            /*
             * 选择文件保存路径
             */
            // 创建一个默认的文件选取器
            JFileChooser fileChooser = new JFileChooser();

            // 设置打开文件选择框后默认输入的文件名
            fileChooser.setSelectedFile(new File("D:\\ChessDemo\\ChessDemo\\resource\\Chessboard"));

            // 打开文件选择框（线程将被阻塞, 直到选择框被关闭）
            int result = fileChooser.showSaveDialog(panel);

            if (result == JFileChooser.APPROVE_OPTION) {
                // 如果点击了"保存", 则获取选择的保存路径
                File file = fileChooser.getSelectedFile();
                final JTextArea msgTextArea = new JTextArea(10, 30);
                msgTextArea.setLineWrap(true);
                panel.add(msgTextArea);
                msgTextArea.append("保存到文件: " + file.getAbsolutePath() + "\n\n");
                String path = file.getPath();
                gameController.storeGameFromFile(path);
            }
        });
        panel.add(storeButton);
    }



}
