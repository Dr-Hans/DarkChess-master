package squareComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import java.awt.*;

public class AdvisorChessComponent extends chessComponent {
    public AdvisorChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        super(chessboardPoint, location, chessColor, clickController, size);
        if (this.getChessColor() == ChessColor.RED) {
            name = "仕";
        } else {
            name = "士";
        }
        level=9;
        canEatCannon=true;
    }
    public static int point=10;
}
