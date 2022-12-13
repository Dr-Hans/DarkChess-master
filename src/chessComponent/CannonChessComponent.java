package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CannonChessComponent extends ChessComponent {

    public CannonChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) throws IOException {
        super(chessboardPoint, location, chessColor, clickController, size);
        Image image1 = ImageIO.read(new File("./image1/13.png"));
        Image image2 = ImageIO.read(new File("./image1/6.png"));
        if (this.getChessColor() == ChessColor.RED) {
            name = "炮";
            this.picture = image1;
        } else {
            name = "砲";
            this.picture = image2;
        }

        canEatCannon = true;
    }
    public static int point=5;

    //1.炮可以吃任何棋（包括暗器）
    //2.炮必须借棋跳跃
    //3.跑不能单走
    public boolean canMoveTo(SquareComponent[][] chessboard, ChessboardPoint destination, SquareComponent lastPoint, ChessboardPoint start) {
        SquareComponent destinationChess = chessboard[destination.getX()][destination.getY()];
        lastPoint = chessboard[start.getX()][start.getY()];

        boolean emptyError;
        boolean lean;
        boolean goY=false;
        boolean goX=false;

        if (destinationChess instanceof EmptySlotComponent) {
            emptyError = false;
        } else {
            emptyError = true;
        }//炮不能走空
        if(Math.abs(destination.getX()-start.getX())==0||Math.abs(destination.getY()-start.getY())==0){
            lean=true;
        }else{
            lean=false;
        }//炮不走斜

        if(Math.abs(destination.getX()-start.getX())==0){
            int count=0;
            if(start.getY()<destination.getY()){
                for(int i= start.getY()+1;i<destination.getY();i++){
                    if(!(chessboard[destination.getX()][i] instanceof EmptySlotComponent)){
                        count++;
                    }
                }

            }else if(start.getY()>destination.getY()){
                for(int i= start.getY()-1;i>destination.getY();i--){
                    if(!(chessboard[destination.getX()][i] instanceof EmptySlotComponent)){
                        count++;
                    }
                }
            }
            if(count==1){
                goY=true;
            }

        }else if(destination.getY()-start.getY()==0){
            int count=0;
            if(start.getX()<destination.getX()){
                for(int i= start.getX()+1;i<destination.getX();i++){
                    if(!(chessboard[i][destination.getY()] instanceof EmptySlotComponent)){
                        count++;
                    }
                }
            }else if(start.getX()>destination.getX()){
                for(int i= start.getX()-1;i>destination.getX();i--){
                    if(!(chessboard[i][destination.getY()] instanceof EmptySlotComponent)){
                        count++;
                    }
                }
            }

            if(count==1){
                goX=true;
            }
        }//炮隔打



        return (emptyError&&lean&&(goX||goY));
        //todo: complete this method
    }

}