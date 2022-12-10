package view;


import chessComponent.*;
import model.*;
import controller.ClickController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.util.*;
import java.util.List;

/**
 * 这个类表示棋盘组建，其包含：
 * SquareComponent[][]: 4*8个方块格子组件
 */
public class Chessboard extends JComponent {


    private static final int ROW_SIZE = 8;
    private static final int COL_SIZE = 4;



    private final SquareComponent[][] squareComponents = new SquareComponent[ROW_SIZE][COL_SIZE];
    //todo: you can change the initial player
    protected ChessColor currentColor=ChessColor.RED ;
    public ArrayList<List<String>> recordChessBoard = new ArrayList<>();
    public ArrayList<String> chessRoundStep=new ArrayList<>();


    //all chessComponents in this chessboard are shared only one model controller
    public final ClickController clickController = new ClickController(this);
    private final int CHESS_SIZE;


    public Chessboard(int width, int height) throws IOException {
        setLayout(null); // Use absolute layout.
        setSize(width + 2, height);
        CHESS_SIZE = (height - 6) / 8;
        SquareComponent.setSpacingLength(CHESS_SIZE / 12);
        System.out.printf("chessboard [%d * %d], chess size = %d\n", width, height, CHESS_SIZE);
        initAllChessOnBoard();
        recordChessBoard.add(getChessStringList());
    }

    public SquareComponent[][] getChessComponents() {
        return squareComponents;
    }

    public ChessColor getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(ChessColor currentColor) {
        this.currentColor = currentColor;
    }

    public void initiateEmptyChessboard() {
        for (int i = 0; i < squareComponents.length; i++) {
            for (int j = 0; j < squareComponents[i].length; j++) {
                putChessOnBoard(new EmptySlotComponent(new ChessboardPoint(i, j), calculatePoint(i, j), clickController, CHESS_SIZE));
            }
        }
    }

    /**
     * 将SquareComponent 放置在 ChessBoard上。里面包含移除原有的component及放置新的component
     *
     * @param squareComponent
     */
    public void putChessOnBoard(SquareComponent squareComponent) {
        int row = squareComponent.getChessboardPoint().getX(), col = squareComponent.getChessboardPoint().getY();
        if (squareComponents[row][col] != null) {
            remove(squareComponents[row][col]);
        }
        add(squareComponents[row][col] = squareComponent);
    }

    /**
     * 交换chess1 chess2的位置
     *
     * @param chess1
     * @param chess2
     */
    public void swapChessComponents(SquareComponent chess1, SquareComponent chess2) {
        // Note that chess1 has higher priority, 'destroys' chess2 if exists.
        if (!(chess2 instanceof EmptySlotComponent)) {
            remove(chess2);
            add(chess2 = new EmptySlotComponent(chess2.getChessboardPoint(), chess2.getLocation(), clickController, CHESS_SIZE));
        }
        chess1.swapLocation(chess2);
        int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
        squareComponents[row1][col1] = chess1;
        int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
        squareComponents[row2][col2] = chess2;


        //只重新绘制chess1 chess2，其他不变
        chess1.repaint();
        chess2.repaint();
    }


    //FIXME:   Initialize chessboard for testing only.
    public void initAllChessOnBoard() throws IOException {
        //2.同类型棋数相等
        //3.随机排列
        Random random = new Random();

        int count2 = 0;
        SquareComponent squareComponent;
        ArrayList<Integer> randomNumber = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            randomNumber.add(i);
        }
        Collections.shuffle(randomNumber);
        for (int i = 0; i < squareComponents.length; i++) {
            for (int j = 0; j < squareComponents[i].length; j++) {
                ChessColor color;

                if (random.nextInt(2) == 0) {
                    squareComponent = new ChariotChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, clickController, CHESS_SIZE);
                } else {
                    squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE);
                }

                switch (randomNumber.get(count2)) {
                    case 0 -> squareComponent = new GeneralChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, clickController, CHESS_SIZE);
                    case 31 -> squareComponent = new GeneralChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE);
                    case 1, 3, 5, 7, 9 -> squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, clickController, CHESS_SIZE);
                    case 11, 13, 15, 17, 19 -> squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE);
                    case 2, 4 -> squareComponent = new ChariotChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE);
                    case 6, 8 -> squareComponent = new ChariotChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, clickController, CHESS_SIZE);
                    case 10, 12 -> squareComponent = new AdvisorChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE);
                    case 14, 16 -> squareComponent = new AdvisorChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, clickController, CHESS_SIZE);
                    case 18, 20 -> squareComponent = new CannonChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE);
                    case 22, 24 -> squareComponent = new CannonChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, clickController, CHESS_SIZE);
                    case 26, 28 -> squareComponent = new horseChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE);
                    case 30, 21 -> squareComponent = new horseChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, clickController, CHESS_SIZE);
                    case 23, 25 -> squareComponent = new MinisterChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, clickController, CHESS_SIZE);
                    case 27, 29 -> squareComponent = new MinisterChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE);
                }
                count2++;
                squareComponent.setVisible(true);
                putChessOnBoard(squareComponent);
            }

        }
        repaint();

    }

    private void initAdvisorOnBoard(int row, int col, ChessColor color) throws IOException {
        SquareComponent chesscomponent = new AdvisorChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chesscomponent.setVisible(true);
        putChessOnBoard(chesscomponent);
    }
    private void initCannonOnBoard(int row, int col, ChessColor color) throws IOException {
        SquareComponent chesscomponent = new CannonChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chesscomponent.setVisible(true);
        putChessOnBoard(chesscomponent);
    }
    private void initChariotOnBoard(int row, int col, ChessColor color) throws IOException {
        SquareComponent chesscomponent = new ChariotChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chesscomponent.setVisible(true);
        putChessOnBoard(chesscomponent);
    }
    private void initGeneralOnBoard(int row, int col, ChessColor color) throws IOException {
        SquareComponent chesscomponent = new GeneralChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chesscomponent.setVisible(true);
        putChessOnBoard(chesscomponent);
    }
    private void initHorseOnBoard(int row, int col, ChessColor color) throws IOException {
        SquareComponent chesscomponent = new horseChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chesscomponent.setVisible(true);
        putChessOnBoard(chesscomponent);
    }
    private void initMinisterOnBoard(int row, int col, ChessColor color) throws IOException {
        SquareComponent chesscomponent = new MinisterChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chesscomponent.setVisible(true);
        putChessOnBoard(chesscomponent);
    }
    private void initSoldierOnBoard(int row, int col, ChessColor color) throws IOException {
        SquareComponent chesscomponent = new SoldierChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chesscomponent.setVisible(true);
        putChessOnBoard(chesscomponent);
    }

    /**
     * 绘制棋盘格子
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            g.drawImage(ImageIO.read(new File("src/chessComponent/15.png")),2,0,288,576,null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }



    /**
     * 将棋盘上行列坐标映射成Swing组件的Point
     *
     * @param row 棋盘上的行
     * @param col 棋盘上的列
     * @return
     */
    public Point calculatePoint(int row, int col) {
        return new Point(col * CHESS_SIZE + 3, row * CHESS_SIZE + 3);
    }

    /**
     * 通过GameController调用该方法
     *
     * @param chessData
     */
    public boolean loadGameAndRun(List<String> chessData) throws IOException {
        recordChessBoard=new ArrayList<>();
        chessRoundStep=new ArrayList<>();
        ClickController.scoreList=new ArrayList<>();
        clickController.eatenHistory=new ArrayList<>();
        initiateEmptyChessboard();
        for(int i=0;i<8;i++){
            for(int j=0;j<4;j++){
                if(chessData.get(i).charAt(j)=='S'){
                    initAdvisorOnBoard(i,j,ChessColor.BLACK);
                }else if(chessData.get(i).charAt(j)=='P'){
                    initCannonOnBoard(i,j,ChessColor.BLACK);
                }else if(chessData.get(i).charAt(j)=='C'){
                    initChariotOnBoard(i,j,ChessColor.BLACK);
                }else if(chessData.get(i).charAt(j)=='J'){
                    initGeneralOnBoard(i,j,ChessColor.BLACK);
                }else if(chessData.get(i).charAt(j)=='M'){
                    initHorseOnBoard(i,j,ChessColor.BLACK);
                }else if(chessData.get(i).charAt(j)=='X'){
                    initMinisterOnBoard(i,j,ChessColor.BLACK);
                }else if(chessData.get(i).charAt(j)=='B'){
                    initSoldierOnBoard(i,j,ChessColor.BLACK);
                }else if(chessData.get(i).charAt(j)=='s'){
                    initAdvisorOnBoard(i,j,ChessColor.RED);
                }else if(chessData.get(i).charAt(j)=='p'){
                    initCannonOnBoard(i,j,ChessColor.RED);
                }else if(chessData.get(i).charAt(j)=='c'){
                    initChariotOnBoard(i,j,ChessColor.RED);
                }else if(chessData.get(i).charAt(j)=='j'){
                    initGeneralOnBoard(i,j,ChessColor.RED);
                }else if(chessData.get(i).charAt(j)=='m'){
                    initHorseOnBoard(i,j,ChessColor.RED);
                }else if(chessData.get(i).charAt(j)=='x'){
                    initMinisterOnBoard(i,j,ChessColor.RED);
                }else if(chessData.get(i).charAt(j)=='b'){
                    initSoldierOnBoard(i,j,ChessColor.RED);
                }
                squareComponents[i][j].repaint();
            }
        }//初始化棋盘
        recordChessBoard.add(getChessStringList());
        int number=0;
        for(int i=9;i<chessData.size()-1;i++){
            if(chessData.get(i).length()==2){
                if(squareComponents[chessData.get(i).charAt(0)-48][chessData.get(i).charAt(1)-48].isReversal()){
                    JOptionPane.showMessageDialog(null, "The chess has been reversed!", "错误编码： 105", JOptionPane.ERROR_MESSAGE);
                    return false;
                }else{
                    number++;
                    squareComponents[chessData.get(i).charAt(0)-48][chessData.get(i).charAt(1)-48].setReversal(true);
                    squareComponents[chessData.get(i).charAt(0)-48][chessData.get(i).charAt(1)-48].repaint();
                    if(number==1){
                        setCurrentColor(squareComponents[chessData.get(i).charAt(0)-48][chessData.get(i).charAt(1)-48].getChessColor());
                    }
                    if(!(squareComponents[chessData.get(i).charAt(0)-48][chessData.get(i).charAt(1)-48] instanceof EmptySlotComponent)){
                        chessRoundStep.add(String.format("%d%d\n",squareComponents[chessData.get(i).charAt(0)-48][chessData.get(i).charAt(1)-48].getChessboardPoint().getX(),squareComponents[chessData.get(i).charAt(0)-48][chessData.get(i).charAt(1)-48].getChessboardPoint().getY()));
                        setCurrentColor(getCurrentColor() == ChessColor.BLACK ? ChessColor.RED : ChessColor.BLACK);
                        ChessGameFrame.getStatusLabel().setText(String.format("%s's TURN", getCurrentColor().getName()));
                    }


                }
            }else if(chessData.get(i).length()==4){
                if(!(squareComponents[chessData.get(i).charAt(0)-48][chessData.get(i).charAt(1)-48].isReversal()&&(squareComponents[chessData.get(i).charAt(2)-48][chessData.get(i).charAt(3)-48].isReversal())||(squareComponents[chessData.get(i).charAt(0)-48][chessData.get(i).charAt(1)-48] instanceof CannonChessComponent))){
                    JOptionPane.showMessageDialog(null, "You cannot move the chess that hasn't been turned over!", "错误编码： 105", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                if(!(squareComponents[chessData.get(i).charAt(0)-48][chessData.get(i).charAt(1)-48].getChessColor()!=squareComponents[chessData.get(i).charAt(2)-48][chessData.get(i).charAt(3)-48].getChessColor()||(squareComponents[chessData.get(i).charAt(0)-48][chessData.get(i).charAt(1)-48] instanceof CannonChessComponent&&(!squareComponents[chessData.get(i).charAt(2)-48][chessData.get(i).charAt(3)-48].isReversal())))){
                    JOptionPane.showMessageDialog(null, "You cannot eat same color chess!", "错误编码： 105", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                if(squareComponents[chessData.get(i).charAt(0)-48][chessData.get(i).charAt(1)-48].canMoveTo(squareComponents,squareComponents[chessData.get(i).charAt(2)-48][chessData.get(i).charAt(3)-48].getChessboardPoint(),squareComponents[chessData.get(i).charAt(0)-48][chessData.get(i).charAt(1)-48],squareComponents[chessData.get(i).charAt(0)-48][chessData.get(i).charAt(1)-48].getChessboardPoint())){
                   clickController.eatenHistory.add(squareComponents[chessData.get(i).charAt(2)-48][chessData.get(i).charAt(3)-48]);
                    chessRoundStep.add(String.format("%d%d%d%d\n",squareComponents[chessData.get(i).charAt(0)-48][chessData.get(i).charAt(1)-48].getChessboardPoint().getX(),squareComponents[chessData.get(i).charAt(0)-48][chessData.get(i).charAt(1)-48].getChessboardPoint().getY(),squareComponents[chessData.get(i).charAt(2)-48][chessData.get(i).charAt(3)-48].getChessboardPoint().getX(),squareComponents[chessData.get(i).charAt(2)-48][chessData.get(i).charAt(3)-48].getChessboardPoint().getY()));
                    swapChessComponents(squareComponents[chessData.get(i).charAt(0)-48][chessData.get(i).charAt(1)-48],squareComponents[chessData.get(i).charAt(2)-48][chessData.get(i).charAt(3)-48]);
                    clickController.addPoint();
                    clickController.swapPlayer();

                }else{
                    JOptionPane.showMessageDialog(null, "Cannot move to that place!", "错误编码： 105", JOptionPane.ERROR_MESSAGE);
                    return false;
                }

            }
        }
        return true;
    }
    public List<String> getChessStringList(){
        List<String> chessStringList=new ArrayList<>();
        for(int i=0;i<8;i++){
            StringBuilder builder=new StringBuilder();
            for(int j=0;j<4;j++){
                if(squareComponents[i][j].getChessColor()== ChessColor.BLACK){
                    if(squareComponents[i][j] instanceof AdvisorChessComponent){
                        builder.append('S');
                    }else if(squareComponents[i][j] instanceof CannonChessComponent){
                        builder.append('P');
                    }else if(squareComponents[i][j] instanceof ChariotChessComponent){
                        builder.append('C');
                    }else if(squareComponents[i][j] instanceof GeneralChessComponent){
                        builder.append('J');
                    }else if(squareComponents[i][j] instanceof horseChessComponent){
                        builder.append('M');
                    }else if(squareComponents[i][j] instanceof MinisterChessComponent){
                        builder.append('X');
                    }else if(squareComponents[i][j] instanceof SoldierChessComponent){
                        builder.append('B');
                    }
                }else if (squareComponents[i][j].getChessColor()== ChessColor.RED){
                    if(squareComponents[i][j] instanceof AdvisorChessComponent){
                        builder.append('s');
                    }else if(squareComponents[i][j] instanceof CannonChessComponent){
                        builder.append('p');
                    }else if(squareComponents[i][j] instanceof ChariotChessComponent){
                        builder.append('c');
                    }else if(squareComponents[i][j] instanceof GeneralChessComponent){
                        builder.append('j');
                    }else if(squareComponents[i][j] instanceof horseChessComponent){
                        builder.append('m');
                    }else if(squareComponents[i][j] instanceof MinisterChessComponent){
                        builder.append('x');
                    }else if(squareComponents[i][j] instanceof SoldierChessComponent){
                        builder.append('b');
                    }
                }else{
                    builder.append('_');
                }
            }
            chessStringList.add(builder+"\n");
        }//棋局导入完毕
        chessStringList.add("ENDUP\n");//表示棋局导入完毕，开始输入步骤。

        return chessStringList;
    }


}
