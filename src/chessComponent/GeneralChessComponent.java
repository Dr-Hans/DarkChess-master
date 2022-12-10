package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GeneralChessComponent extends ChessComponent {
    public GeneralChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) throws IOException {
        super(chessboardPoint, location, chessColor, clickController, size);
        Image image7 = ImageIO.read(new File("./image1/8.png"));
        Image image8 = ImageIO.read(new File("./image1/1.png"));
        if (this.getChessColor() == ChessColor.RED) {
            name = "帥";
            this.picture = image7;
        } else {
            name = "将";
            this.picture = image8;
        }

        level=10000;
        canEatCannon=true;
    }
    public static int point=30;
}
