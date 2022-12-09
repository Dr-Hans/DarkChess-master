package controller;


import squareComponent.*;
import model.ChessColor;
import model.ChessboardPoint;
import view.ChessGameFrame;
import view.Chessboard;


import java.util.ArrayList;
import java.util.List;

public class ClickController {
    protected static int clickNumber=0;
    private final Chessboard chessboard;
    private SquareComponent first;
    public ArrayList<SquareComponent> eatenHistory = new ArrayList<>();
    public static List<Integer> scoreList=new ArrayList<>();



    public ClickController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public void onClick(SquareComponent squareComponent) {
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
                eatenHistory.add(squareComponent);
                chessboard.chessRoundStep.add(String.format("%d%d%d%d\n",first.getChessboardPoint().getX(),first.getChessboardPoint().getY(),squareComponent.getChessboardPoint().getX(),squareComponent.getChessboardPoint().getY()));
                chessboard.swapChessComponents(first, squareComponent);
                addPoint();
                winGame();
                chessboard.clickController.swapPlayer();
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
            squareComponent.setReversal(true);
            System.out.printf("onClick to reverse a chess [%d,%d]\n", squareComponent.getChessboardPoint().getX(), squareComponent.getChessboardPoint().getY());
            squareComponent.repaint();
            if(clickNumber==1){
                chessboard.setCurrentColor(squareComponent.getChessColor());
            }
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

    private boolean handleSecond(SquareComponent squareComponent) {

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
        ChessGameFrame.getStatusLabel().setText(String.format("%s's TURN", chessboard.getCurrentColor().getName()));
    }
    public void winGame(){
        for(int i=0;i<2;i++){
            if(scoreList.get(i)>=60){
                switch (i){
                    case 0:System.out.print("RED WIN!");

                    break;
                    case 1:System.out.print("BLACK WIN!");

                }

            }
        }

    }
    public void addPoint(){
        scoreList.add(0);
        scoreList.add(0);
        if(chessboard.getCurrentColor()==ChessColor.RED){
            if(eatenHistory.get(eatenHistory.size()-1) instanceof AdvisorChessComponent){
                scoreList.set(0,scoreList.get(0)+AdvisorChessComponent.point);
            }
            if(eatenHistory.get(eatenHistory.size()-1) instanceof CannonChessComponent){
                scoreList.set(0,scoreList.get(0)+CannonChessComponent.point);
            }
            if(eatenHistory.get(eatenHistory.size()-1) instanceof ChariotChessComponent){
                scoreList.set(0,scoreList.get(0)+ChariotChessComponent.point);
            }
            if(eatenHistory.get(eatenHistory.size()-1) instanceof GeneralChessComponent){
                scoreList.set(0,scoreList.get(0)+GeneralChessComponent.point);
            }
            if(eatenHistory.get(eatenHistory.size()-1) instanceof horseChessComponent){
                scoreList.set(0,scoreList.get(0)+horseChessComponent.point);
            }
            if(eatenHistory.get(eatenHistory.size()-1) instanceof MinisterChessComponent){
                scoreList.set(0,scoreList.get(0)+MinisterChessComponent.point);
            }
            if(eatenHistory.get(eatenHistory.size()-1) instanceof SoldierChessComponent){
                scoreList.set(0,scoreList.get(0)+SoldierChessComponent.point);
            }
        }else{
            if(eatenHistory.get(eatenHistory.size()-1) instanceof AdvisorChessComponent){
                scoreList.set(1,scoreList.get(1)+AdvisorChessComponent.point);
            }
            if(eatenHistory.get(eatenHistory.size()-1) instanceof CannonChessComponent){
                scoreList.set(1,scoreList.get(1)+CannonChessComponent.point);
            }
            if(eatenHistory.get(eatenHistory.size()-1) instanceof ChariotChessComponent){
                scoreList.set(1,scoreList.get(1)+ChariotChessComponent.point);
            }
            if(eatenHistory.get(eatenHistory.size()-1) instanceof GeneralChessComponent){
                scoreList.set(1,scoreList.get(1)+GeneralChessComponent.point);
            }
            if(eatenHistory.get(eatenHistory.size()-1) instanceof horseChessComponent){
                scoreList.set(1,scoreList.get(1)+horseChessComponent.point);
            }
            if(eatenHistory.get(eatenHistory.size()-1) instanceof MinisterChessComponent){
                scoreList.set(1,scoreList.get(1)+MinisterChessComponent.point);
            }
            if(eatenHistory.get(eatenHistory.size()-1) instanceof SoldierChessComponent){
                scoreList.set(1,scoreList.get(1)+SoldierChessComponent.point);
            }
        }

    }
}

