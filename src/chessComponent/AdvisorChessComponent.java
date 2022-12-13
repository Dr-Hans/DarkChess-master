
package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class AdvisorChessComponent extends ChessComponent {
    public AdvisorChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) throws IOException {
        super(chessboardPoint, location, chessColor, clickController, size);
        Image image3 = ImageIO.read(new File("./image1/9.png"));
        Image image4 = ImageIO.read(new File("./image1/2.png"));
        if (this.getChessColor() == ChessColor.RED) {
            name = "仕";
            picture = image3;
        } else {
            name = "士";
            picture = image4;
        }
        level=9;
        canEatCannon=true;
    }
    public static int point=10;
}

