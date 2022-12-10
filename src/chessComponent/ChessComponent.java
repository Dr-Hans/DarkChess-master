package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * 表示棋盘上非空棋子的格子，是所有非空棋子的父类
 */
public class ChessComponent extends SquareComponent{
    protected String name;// 棋子名字：例如 兵，卒，士等

    public Image picture;

    protected ChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        super(chessboardPoint, location, chessColor, clickController, size);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            g.drawImage(ImageIO.read(new File("./image1/16.png")),this.getWidth() / 4 - 10, this.getHeight() * 2 / 3 - 40,this.getWidth() - 2 * spacingLength - 5 ,this.getHeight() - 2 * spacingLength - 5 ,null);
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (isReversal) {
            //g.setColor(this.getChessColor().getColor());
            //g.setFont(CHESS_FONT);
           //g.drawString(this.name, this.getWidth() / 4, this.getHeight() * 2 / 3);
            g.drawImage(this.picture,this.getWidth() / 4 - 16, this.getHeight() * 2 / 3 - 43,this.getWidth() - 2 * spacingLength,this.getHeight() - 2 * spacingLength,null);

            //绘制棋子被选中时状态
            if (isSelected()) {
                g.setColor(Color.RED);//选中棋子出现的边框颜色
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(4f));
                g2.drawRect(spacingLength, spacingLength, getWidth() - 2 * spacingLength, getHeight() - 2 * spacingLength);
            }
        }
    }
}

