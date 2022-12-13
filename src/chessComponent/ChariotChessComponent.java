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
public class ChariotChessComponent extends ChessComponent {

    public ChariotChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) throws IOException {
        super(chessboardPoint, location, chessColor, clickController, size);
        Image image5 = ImageIO.read(new File("./image1/12.png"));
        Image image6 = ImageIO.read(new File("./image1/5.png"));
        if (this.getChessColor() == ChessColor.RED) {
            name = "俥";
            this.picture = image5;
        } else {
            name = "車";
            this.picture = image6;
        }
        level=7;
        canEatCannon=true;
    }
    public static int point=5;


}
