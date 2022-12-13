package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * 表示黑红车
 */
public class horseChessComponent extends ChessComponent {
    public horseChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) throws IOException {
        super(chessboardPoint, location, chessColor, clickController, size);
        Image image9 = ImageIO.read(new File("./image1/11.png"));
        Image image10 = ImageIO.read(new File("./image1/4.png"));
        if (this.getChessColor() == ChessColor.RED) {
            name = "傌";
            this.picture = image9;
        } else {
            name = "馬";
            this.picture = image10;
        }
        level=5;
        canEatCannon=true;
    }
    public static int point =5;
}