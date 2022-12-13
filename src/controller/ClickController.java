package controller;


import chessComponent.*;
import model.ChessColor;
import model.ChessboardPoint;
import view.ChessGameFrame;
import view.Chessboard;


import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClickController {
    public static int clickNumber=0;
    private final Chessboard chessboard;
    private SquareComponent first;
    private boolean isRed;

    public boolean isRed() {
        return isRed;
    }

    public void setRed(boolean red) {
        isRed = red;
    }

    public ClickController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public void onClick(SquareComponent squareComponent) throws IOException {

        //判断第一次点击

        if (first == null) {
            if (handleFirst(squareComponent)) {
                squareComponent.setSelected(true);
                first = squareComponent;
                first.repaint();
            }
        } else {
            if (first == squareComponent) { // 再次点击取消选取
                squareComponent.setSelected(false);
                SquareComponent recordFirst = first;
                first = null;
                recordFirst.repaint();
            } else if (handleSecond(squareComponent)&&(squareComponent.getChessColor()!=first.getChessColor()||!squareComponent.isReversal())) {
                //repaint in swap chess method.
                chessboard.eatenHistory.add(squareComponent);
                chessboard.chessRoundStep.add(String.format("%d%d%d%d\n",first.getChessboardPoint().getX(),first.getChessboardPoint().getY(),squareComponent.getChessboardPoint().getX(),squareComponent.getChessboardPoint().getY()));
                chessboard.swapChessComponents(first, squareComponent);
                addPoint();
                chessboard.clickController.swapPlayer();
                winGame();
                chessboard.chessBoardList.add(chessboard.getChessStringListUndo());
                ArrayList<SquareComponent> stand1=new ArrayList<>();
                for(SquareComponent chess: chessboard.eatenHistory){
                    stand1.add(chess);
                }
                chessboard.eatenLists.add(stand1);//以同样的方式计eaten
                ArrayList<Integer> stand=new ArrayList<>();
                stand.add(chessboard.scoreList.get(0));
                stand.add(chessboard.scoreList.get(1));
                chessboard.scoreLists.add(stand);
                first.setSelected(false);
                first = null;
            }else if((squareComponent.getChessColor()==first.getChessColor()&&squareComponent.isReversal())){
                first.setSelected(false);
                squareComponent.setSelected(true);
                SquareComponent recordFirst = first;
                recordFirst.repaint();
                first = squareComponent;
                first.repaint();
            }
        }
    }


    /**
     * @param squareComponent 目标选取的棋子
     * @return 目标选取的棋子是否与棋盘记录的当前行棋方颜色相同
     */

    private boolean handleFirst(SquareComponent squareComponent) {

        if (!squareComponent.isReversal()) {
            clickNumber++;
            if(clickNumber==1){
                chessboard.chessBoardList.add(chessboard.getChessStringListUndo());
                chessboard.eatenLists.add(chessboard.eatenHistory);
                chessboard.scoreLists.add(chessboard.scoreList);
                chessboard.setCurrentColor(squareComponent.getChessColor());
                ChessGameFrame.getStatusLabel1().setText(String.format("玩家1(%s)",chessboard.getCurrentColor().getName()));
                if(chessboard.getCurrentColor()==ChessColor.RED){
                    ChessGameFrame.getStatusLabel2().setText("玩家2(BLACK)");
                    isRed=false;
                }else if(chessboard.getCurrentColor()==ChessColor.BLACK){
                    ChessGameFrame.getStatusLabel2().setText("玩家2(RED)");
                    isRed=true;
                }
            }
            squareComponent.setReversal(true);
            chessboard.chessBoardList.add(chessboard.getChessStringListUndo());
            ArrayList<SquareComponent> stand1=new ArrayList<>();
            for(SquareComponent chess: chessboard.eatenHistory){
                stand1.add(chess);
            }
            chessboard.eatenLists.add(stand1);
            ArrayList<Integer> stand=new ArrayList<>();
            stand.add(chessboard.scoreList.get(0));
            stand.add(chessboard.scoreList.get(1));
            chessboard.scoreLists.add(stand);
            System.out.printf("onClick to reverse a chess [%d,%d]\n", squareComponent.getChessboardPoint().getX(), squareComponent.getChessboardPoint().getY());
            squareComponent.repaint();
            if(!(squareComponent instanceof EmptySlotComponent)){
                chessboard.chessRoundStep.add(String.format("%d%d\n",squareComponent.getChessboardPoint().getX(),squareComponent.getChessboardPoint().getY()));
                swapPlayer();
            }
            return false;
        }
        return squareComponent.getChessColor() == chessboard.getCurrentColor();
    }

    /**
     * @param squareComponent first棋子目标移动到的棋子second
     * @return first棋子是否能够移动到second棋子位置
     */

    private boolean handleSecond(SquareComponent squareComponent) throws IOException {

        if (!(first instanceof CannonChessComponent)) { //没翻开或空棋子，进入if
            if (!squareComponent.isReversal()) {
                //没翻开且非空棋子不能走
                if (!(squareComponent instanceof EmptySlotComponent)) {
                    return false;
                }
            }
            return squareComponent.getChessColor() != chessboard.getCurrentColor() &&
                    first.canMoveTo(chessboard.getChessComponents(), squareComponent.getChessboardPoint(), first, first.getChessboardPoint());
        } else {
            SquareComponent cannon = new CannonChessComponent(new ChessboardPoint(1, 1), chessboard.calculatePoint(1, 1), ChessColor.RED, chessboard.clickController, Chessboard.HEIGHT);
            return cannon.canMoveTo(chessboard.getChessComponents(), squareComponent.getChessboardPoint(), first, first.getChessboardPoint());
        }

    }

    public void swapPlayer() {
        chessboard.setCurrentColor(chessboard.getCurrentColor() == ChessColor.BLACK ? ChessColor.RED : ChessColor.BLACK);
        ChessGameFrame.getStatusLabel().setText(String.format("         %s's TURN", chessboard.getCurrentColor().getName()));
    }
    public void winGame(){
        for(int i=0;i<2;i++){
            if(chessboard.scoreList.get(i)>=60){
                switch (i){
                    case 0:
                        JOptionPane.showMessageDialog(null,"红方玩家获胜！" ,"游戏结束", JOptionPane.PLAIN_MESSAGE);
                    break;
                    case 1:
                    JOptionPane.showMessageDialog(null,"黑方玩家获胜！" ,"游戏结束", JOptionPane.PLAIN_MESSAGE);

                }
                try {
                    chessboard.initAllChessOnBoard();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ClickController.clickNumber=0;
                chessboard.scoreList.set(0,0);
                chessboard.scoreList.set(1,0);
                ChessGameFrame.getStatusLabel().setText("Reveal A Chess To Start!");
                ChessGameFrame.getStatusLabel1().setText("玩家1");
                ChessGameFrame.getStatusLabel2().setText("玩家2");
            }else{
               if(isRed){
                   ChessGameFrame.getStatusLabel4().setText(String.format("积分: %d", chessboard.scoreList.get(1)));
                   ChessGameFrame.getStatusLabel5().setText(String.format("积分: %d", chessboard.scoreList.get(0)));

               }else{
                   ChessGameFrame.getStatusLabel4().setText(String.format("积分: %d", chessboard.scoreList.get(0)));
                   ChessGameFrame.getStatusLabel5().setText(String.format("积分: %d", chessboard.scoreList.get(1)));
               }

            }

        }

    }

    public void addPoint(){
        if(chessboard.getCurrentColor()==ChessColor.RED){
            if(chessboard.eatenHistory.get(chessboard.eatenHistory.size()-1) instanceof AdvisorChessComponent){
               chessboard.scoreList.set(0,chessboard.scoreList.get(0)+AdvisorChessComponent.point);
            }
            if(chessboard.eatenHistory.get(chessboard.eatenHistory.size()-1) instanceof CannonChessComponent){
                chessboard.scoreList.set(0,chessboard.scoreList.get(0)+CannonChessComponent.point);
            }
            if(chessboard.eatenHistory.get(chessboard.eatenHistory.size()-1) instanceof ChariotChessComponent){
                chessboard.scoreList.set(0,chessboard.scoreList.get(0)+ChariotChessComponent.point);
            }
            if(chessboard.eatenHistory.get(chessboard.eatenHistory.size()-1) instanceof GeneralChessComponent){
                chessboard.scoreList.set(0,chessboard.scoreList.get(0)+GeneralChessComponent.point);
            }
            if(chessboard.eatenHistory.get(chessboard.eatenHistory.size()-1) instanceof horseChessComponent){
                chessboard.scoreList.set(0,chessboard.scoreList.get(0)+horseChessComponent.point);
            }
            if(chessboard.eatenHistory.get(chessboard.eatenHistory.size()-1) instanceof MinisterChessComponent){
                chessboard.scoreList.set(0,chessboard.scoreList.get(0)+MinisterChessComponent.point);
            }
            if(chessboard.eatenHistory.get(chessboard.eatenHistory.size()-1) instanceof SoldierChessComponent){
                chessboard.scoreList.set(0,chessboard.scoreList.get(0)+SoldierChessComponent.point);
            }
        }else{
            if(chessboard.eatenHistory.get(chessboard.eatenHistory.size()-1) instanceof AdvisorChessComponent){
                chessboard.scoreList.set(1,chessboard.scoreList.get(1)+AdvisorChessComponent.point);
            }
            if(chessboard.eatenHistory.get(chessboard.eatenHistory.size()-1) instanceof CannonChessComponent){
                chessboard.scoreList.set(1,chessboard.scoreList.get(1)+CannonChessComponent.point);
            }
            if(chessboard.eatenHistory.get(chessboard.eatenHistory.size()-1) instanceof ChariotChessComponent){
                chessboard.scoreList.set(1,chessboard.scoreList.get(1)+ChariotChessComponent.point);
            }
            if(chessboard.eatenHistory.get(chessboard.eatenHistory.size()-1) instanceof GeneralChessComponent){
                chessboard.scoreList.set(1,chessboard.scoreList.get(1)+GeneralChessComponent.point);
            }
            if(chessboard.eatenHistory.get(chessboard.eatenHistory.size()-1) instanceof horseChessComponent){
                chessboard.scoreList.set(1,chessboard.scoreList.get(1)+horseChessComponent.point);
            }
            if(chessboard.eatenHistory.get(chessboard.eatenHistory.size()-1) instanceof MinisterChessComponent){
                chessboard.scoreList.set(1,chessboard.scoreList.get(1)+MinisterChessComponent.point);
            }
            if(chessboard.eatenHistory.get(chessboard.eatenHistory.size()-1) instanceof SoldierChessComponent){
                chessboard.scoreList.set(1,chessboard.scoreList.get(1)+SoldierChessComponent.point);
            }
        }

    }

    public void putEatenChess(){

    }

}

