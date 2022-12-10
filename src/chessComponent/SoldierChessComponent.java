package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SoldierChessComponent extends ChessComponent {

    public SoldierChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) throws IOException {
        super(chessboardPoint, location, chessColor, clickController, size);
        Image image13 = ImageIO.read(new File("./image1/7.png"));
        Image image14 = ImageIO.read(new File("./image1/14.png"));
        if (this.getChessColor() == ChessColor.RED) {
            name = "兵";
            this.picture = image14;
        } else {
            name = "卒";
            this.picture = image13;
        }
        point=1;
        level=-1;
        canEatCannon=false;
    }
    public static int point=1;

}
