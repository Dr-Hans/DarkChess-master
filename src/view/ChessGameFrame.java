package view;

import chessComponent.SquareComponent;
import controller.ClickController;
import controller.GameController;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
    public static String Cheating = "OFF";
    private final int WIDTH;
    private final int HEIGHT;
    public final int CHESSBOARD_SIZE;
    private Clip clip;
    private GameController gameController;
    private static JLabel statusLabel;
    private static JLabel statusLabel1;
    private static JLabel statusLabel2;
    private static JLabel statusLabel3;
    private static JLabel statusLabel4;
    private static JLabel statusLabel5;

    public static JLabel getStatusLabel4() {
        return statusLabel4;
    }

    public static void setStatusLabel4(JLabel statusLabel4) {
        ChessGameFrame.statusLabel4 = statusLabel4;
    }

    public static JLabel getStatusLabel5() {
        return statusLabel5;
    }

    public static void setStatusLabel5(JLabel statusLabel5) {
        ChessGameFrame.statusLabel5 = statusLabel5;
    }

    private JPanel panel0 = new JPanel();//开始界面


    public static JLabel getStatusLabel1() {
        return statusLabel1;
    }

    public static void setStatusLabel1(JLabel statusLabel1) {
        ChessGameFrame.statusLabel1 = statusLabel1;
    }

    public static JLabel getStatusLabel2() {
        return statusLabel2;
    }

    public static void setStatusLabel2(JLabel statusLabel2) {
        ChessGameFrame.statusLabel2 = statusLabel2;
    }

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
        addStoreButton(panel0);
        addRestartButton(panel0);
        addLoadButton(panel0);
        addcheatingButton(panel0);
        addundoButton(panel0);
        addRedScore(panel0);
        addBlackScore(panel0);
        addCheating(panel0);
        addLabel(panel0);
        addBlack(panel0);
        addRed(panel0);
        addStopMusicButton(panel0);

        Image image = new ImageIcon("./image1/background.png").getImage();
        JLabel jLabel = new ALabel(image);
        jLabel.setSize(WIDTH, HEIGHT);
        panel0.add(jLabel);
    }


    public static JLabel getStatusLabel3() {
        return statusLabel3;
    }

    public static void setStatusLabel3(JLabel statusLabel3) {
        ChessGameFrame.statusLabel3 = statusLabel3;
    }

    /**
     * 在游戏窗体中添加棋盘
     */
    private void addChessboard(JPanel panel) throws IOException {
        Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE / 2, CHESSBOARD_SIZE);
        gameController = new GameController(chessboard);
        chessboard.setLocation(HEIGHT / 10 + 90, HEIGHT / 10);
        panel.add(chessboard);
    }

    /**
     * 在游戏窗体中添加标签
     */
    File bgm = new File("./musics/fynz8-us285.wav");
    //        播放背景音乐




    private void addLabel(JPanel panel) {
        statusLabel = new JLabel("Reveal A Chess To Start!");
        statusLabel.setLocation(190 , 0);
        statusLabel.setSize(300, 60);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        panel.add(statusLabel);
    }

    private void addRedScore(JPanel panel){
        statusLabel1 = new JLabel("玩家1" );
        statusLabel1.setLocation(40 , 30);
        statusLabel1.setSize(300, 60);
        statusLabel1.setFont(new Font("Rockwell", Font.BOLD, 20));
        panel.add(statusLabel1);
    }
    private void addRed(JPanel panel){
        gameController.getChessboard().scoreList.add(0);
        gameController.getChessboard().scoreList.add(0);
        statusLabel4 = new JLabel("积分: 0" );
        statusLabel4.setLocation(40 ,  60);
        statusLabel4.setSize(300, 60);
        statusLabel4.setFont(new Font("Rockwell", Font.BOLD, 20));
        panel.add(statusLabel4);
    }
    private void addBlackScore(JPanel panel){
        statusLabel2 = new JLabel("玩家2");
        statusLabel2.setLocation(480 , 30);
        statusLabel2.setSize(300, 60);
        statusLabel2.setFont(new Font("Rockwell", Font.BOLD, 20));
        panel.add(statusLabel2);
    }

    private void addBlack(JPanel panel){
        statusLabel5 = new JLabel("积分: 0");
        statusLabel5.setLocation(480 , 60);
        statusLabel5.setSize(300, 60);
        statusLabel5.setFont(new Font("Rockwell", Font.BOLD, 20));
        panel.add(statusLabel5);
    }
    private void addCheating(JPanel panel){
        statusLabel3 = new JLabel("Cheating : OFF" );
        statusLabel3.setLocation(720 , 590);
        statusLabel3.setSize(300, 60);
        statusLabel3.setFont(new Font("Rockwell", Font.BOLD, 20));
        panel.add(statusLabel3);
    }
//hwl

    public void refreshLabel() {
        ChessGameFrame.getStatusLabel().setText("Reveal A Chess To Start!");
        getStatusLabel1().setText("玩家1");
        getStatusLabel2().setText("玩家2");
    }

    public static JLabel getStatusLabel() {
        return statusLabel;
    }

    /**
     * 在游戏窗体中增加一个按钮，如果按下的话就会显示Hello, world!
     */


    private void addRestartButton(JPanel panel) {
        ImageIcon imageIcon = new ImageIcon("./image1/restart.png");
        JButton button = new JButton(imageIcon);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(WIDTH / 5, HEIGHT / 4 - 20, Image.SCALE_DEFAULT));
        button.setLocation(WIDTH * 7 / 10 - 20 , 30 );
        button.setSize(200,200);
       button.setBorderPainted(false);

        button.setContentAreaFilled(false);
        button.setOpaque(false);
        add(button);
        button.addActionListener((e) -> {
            if (JOptionPane.showConfirmDialog(null, "是否重开游戏", "Restart", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                try {
                    gameController.restartGame();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                refreshLabel();
            };

        });
        panel.add(button);


    }


    private void addLoadButton(JPanel panel) {
        ImageIcon imageIcon = new ImageIcon("./image1/load.png");
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(140, 140, Image.SCALE_DEFAULT));

        JButton button = new JButton(imageIcon);
        button.setLocation(WIDTH * 6  / 10 + 50, HEIGHT / 10 + 150);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setSize(140,140);
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
        ImageIcon imageIcon = new ImageIcon("./image1/store.png");
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(140, 140 , Image.SCALE_DEFAULT));
        JButton storeButton = new JButton(imageIcon);
        storeButton.setLocation(WIDTH * 6 / 10 + 200, HEIGHT / 10 + 153);
        storeButton.setSize(140, 140);
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
    private void addcheatingButton(JPanel panel) {
        ImageIcon imageIcon = new ImageIcon("./image1/cheating.png");
        JButton button = new JButton(imageIcon);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(140, 140, Image.SCALE_DEFAULT));
        button.setLocation(WIDTH * 6 / 10 + 200, 400 );
        button.setSize(140,140);
        button.setBorderPainted(false);

        button.setContentAreaFilled(false);
        button.setOpaque(false);

        button.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, "是否开启Cheating模式", "请选择", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                Cheating = "ON";
                ChessGameFrame.getStatusLabel3().setText(String.format("Cheating : %s", ChessGameFrame.Cheating));
                SquareComponent.isCheating=true;
                repaint();
            }else{
                Cheating = "OFF";
                ChessGameFrame.getStatusLabel3().setText(String.format("Cheating : %s", ChessGameFrame.Cheating));
                SquareComponent.isCheating=false;
                repaint();
            }
        });



        add(button);

        panel.add(button);


    }



    private void addundoButton(JPanel panel) {
        ImageIcon imageIcon = new ImageIcon("./image1/undo.png");
        JButton button = new JButton(imageIcon);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(140,140, Image.SCALE_DEFAULT));
        button.setLocation(WIDTH * 6 / 10 + 50 , 400 );
        button.setSize(140,140);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        add(button);
        button.addActionListener((e) -> {

            if (JOptionPane.showConfirmDialog(this, "是否悔棋", "这是确认对话框", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                try {
                    gameController.getChessboard().undo();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                //写悔棋的方法
            };



        });

        panel.add(button);


    }
    private void playMusic(File file) {
        try {
            //创建相当于音乐播放器的对象
            Clip clip = AudioSystem.getClip();
            this.clip = clip;
            //将传入的文件转成可播放的文件
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(file);
            //播放器打开这个文件
            clip.open(audioInput);
            //clip.start();//只播放一次
            //循环播放
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //死循环不让主程序结束（swing可不用）
    /*
      while(true){
      }
    */
    }
    private void addStopMusicButton(JPanel panel) {
        ImageIcon imageIcon = new ImageIcon("./image1/da01d7380f6bb5b2eafc08a354a77339.png");
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(WIDTH / 18, HEIGHT / 15, Image.SCALE_DEFAULT));
        JButton stopMusicButton = new JButton(imageIcon);
        stopMusicButton.setLocation(WIDTH * 17 / 18, 0);
        stopMusicButton.setSize(WIDTH / 18, HEIGHT / 15);
        stopMusicButton.setBorderPainted(false);
        stopMusicButton.setContentAreaFilled(false);
        stopMusicButton.setOpaque(false);
//        JButton stopMusicButton = new JButton("♬");
//        stopMusicButton.setFont(new Font("Rockwell", Font.BOLD, 20));
//        stopMusicButton.setSize(WIDTH / 18, HEIGTH / 15);
//        stopMusicButton.setLocation(WIDTH * 17 / 18, 0);
//        stopMusicButton.setBorderPainted(false);
//        stopMusicButton.setBackground(new Color(205, 183, 159));
        stopMusicButton.addActionListener((e) -> {
            if (clip.isRunning()) {
                clip.stop();
            } else {
                clip.start();
            }
        });
        panel.add(stopMusicButton);
    }





}
