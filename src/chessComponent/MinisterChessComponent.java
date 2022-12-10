package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MinisterChessComponent extends ChessComponent {
    public MinisterChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) throws IOException {
        super(chessboardPoint, location, chessColor, clickController, size);
        Image image11 = ImageIO.read(new File("./image1/10.png"));
        Image image12 = ImageIO.read(new File("./image1/3.png"));
        if (this.getChessColor() == ChessColor.RED) {
            name = "相";
            this.picture = image11;
        } else {
            name = "象";
            this.picture = image12;
        }
        level=8;
        canEatCannon=true;
    }
    public static int point =5;
}
