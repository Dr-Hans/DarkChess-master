import view.ChessGameFrame;
import view.Initial;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Initial mainFrame = new Initial(880, 720);
            mainFrame.setVisible(true);
            try {
                Image background = ImageIO.read(new File("./image1/background.png"));
                JComponent backgroundComponent = new Initial.ImageComponent(background);
                backgroundComponent.setSize(880,720);
                backgroundComponent.setLocation(0,0);
                mainFrame.add(backgroundComponent);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

    }
}
//悔棋https://blog.csdn.net/sinat_24994943/article/details/54895270?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_utm_term~default-0-54895270-blog-106924351.pc_relevant_landingrelevant&spm=1001.2101.3001.4242.1&utm_relevant_index=3