package view;

import javax.swing.*;
import java.awt.*;

public class ALabel extends JLabel {
    private Image image;

    public ALabel(Image image) {
        this.image = image;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = this.getWidth();
        int y = this.getHeight();
        g.drawImage(image, 0, 0, x, y, null);
    }
}
