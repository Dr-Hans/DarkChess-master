package controller;

import chessComponent.*;

import model.ChessColor;
import view.ChessGameFrame;
import view.Chessboard;


import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 这个类主要完成由窗体上组件触发的动作。
 * 例如点击button等
 * ChessGameFrame中组件调用本类的对象，在本类中的方法里完成逻辑运算，将运算的结果传递至chessboard中绘制
 */
public class GameController {
    private Chessboard chessboard;
    public List<String> initialChessList=new ArrayList<>();

    public Chessboard getChessboard() {
        return chessboard;
    }

    public void setChessboard(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public GameController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public boolean loadGameFromFile(File file) {
        String path = file.getPath();
        boolean canImport=true;
        try {
            if(!path.endsWith(".txt")){
                JOptionPane.showMessageDialog(null, "File is not txt file,please use correct format!", "错误编码： 101", JOptionPane.ERROR_MESSAGE);
                canImport=false;

            }
            List<String> chessData = Files.readAllLines(Path.of(path));
            int count=0;
            int pos=0;
            for (int i=0;i<chessData.size();i++) {
                if (chessData.get(i).equals("ENDUP")) {
                    count++;
                    pos=i;
                    break;
                }
            }

            if(count==0){
                JOptionPane.showMessageDialog(null, "Miss ENDUP format!", "错误编码： 102", JOptionPane.ERROR_MESSAGE);
                canImport = false;
            }

                if (pos!=8&&count!=0) {
                    JOptionPane.showMessageDialog(null, "Chessboard length should be 8!", "错误编码： 102", JOptionPane.ERROR_MESSAGE);
                    canImport = false;
                }

                for (int i = 0; i < 8; i++) {
                    if (chessData.get(i).length() != 4) {
                        JOptionPane.showMessageDialog(null, "Chessboard width should be 4!", "错误编码： 102", JOptionPane.ERROR_MESSAGE);
                        canImport = false;
                    }
                }

            List<Character> chessList = Arrays.asList('S', 'P', 'C', 'J', 'M', 'X', 'B', 's', 'p', 'c', 'j', 'm', 'x','b','_');
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 4; j++) {
                    if (!chessList.contains(chessData.get(i).charAt(j))) {
                        JOptionPane.showMessageDialog(null, "The format of ChessTypes is wrong!", "错误编码： 103", JOptionPane.ERROR_MESSAGE);
                        canImport = false;
                        break;
                    }
                }
            }
            int count1=0;
            int count2=0;
            int count3=0;
            int count4=0;
            int count5=0;
            int count6=0;
            int count7=0;
            int count8=0;
            int count9=0;
            int count10=0;
            int count11=0;
            int count12=0;
            int count13=0;
            int count14=0;
            int count15=0;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 4; j++) {
                    if(chessData.get(i).charAt(j)=='S'){
                        count1++;
                    }else if(chessData.get(i).charAt(j)=='P'){
                        count2++;
                    }else if(chessData.get(i).charAt(j)=='C'){
                        count3++;
                    }else if(chessData.get(i).charAt(j)=='J'){
                        count4++;
                    }else if(chessData.get(i).charAt(j)=='M'){
                        count5++;
                    }else if(chessData.get(i).charAt(j)=='X'){
                        count6++;
                    }else if(chessData.get(i).charAt(j)=='B'){
                        count7++;
                    }else if(chessData.get(i).charAt(j)=='s'){
                        count8++;
                    }else if(chessData.get(i).charAt(j)=='p'){
                        count9++;
                    }else if(chessData.get(i).charAt(j)=='c'){
                        count10++;
                    }else if(chessData.get(i).charAt(j)=='j'){
                        count11++;
                    }else if(chessData.get(i).charAt(j)=='m'){
                        count12++;
                    }else if(chessData.get(i).charAt(j)=='x'){
                        count13++;
                    }else if(chessData.get(i).charAt(j)=='b'){
                        count14++;
                    }
                }
            }
            while(true){
                if(count1!=2){
                    JOptionPane.showMessageDialog(null, "The number of black Advisor is wrong!", "错误编码： 103", JOptionPane.ERROR_MESSAGE);
                    canImport = false;
                    break;
                }
                if(count2!=2){
                    JOptionPane.showMessageDialog(null, "The number of black cannon is wrong!", "错误编码： 103", JOptionPane.ERROR_MESSAGE);
                    canImport = false;
                    break;
                }
                if(count3!=2){
                    JOptionPane.showMessageDialog(null, "The number of black chariot is wrong!", "错误编码： 103", JOptionPane.ERROR_MESSAGE);
                    canImport = false;
                    break;
                }
                if(count4!=1){
                    JOptionPane.showMessageDialog(null, "The number of black general is wrong!", "错误编码： 103", JOptionPane.ERROR_MESSAGE);
                    canImport = false;
                    break;
                }
                if(count5!=2){
                    JOptionPane.showMessageDialog(null, "The number of black horse is wrong!", "错误编码： 103", JOptionPane.ERROR_MESSAGE);
                    canImport = false;
                    break;
                }
                if(count6!=2){
                    JOptionPane.showMessageDialog(null, "The number of black minister is wrong!", "错误编码： 103", JOptionPane.ERROR_MESSAGE);
                    canImport = false;
                    break;
                }
                if(count7!=5){
                    JOptionPane.showMessageDialog(null, "The number of black soldier is wrong!", "错误编码： 103", JOptionPane.ERROR_MESSAGE);
                    canImport = false;
                    break;
                }
                if(count8!=2){
                    JOptionPane.showMessageDialog(null, "The number of red Advisor is wrong!", "错误编码： 103", JOptionPane.ERROR_MESSAGE);
                    canImport = false;
                    break;
                }
                if(count9!=2){
                    JOptionPane.showMessageDialog(null, "The number of red cannon is wrong!", "错误编码： 103", JOptionPane.ERROR_MESSAGE);
                    canImport = false;
                    break;
                }
                if(count10!=2){
                    JOptionPane.showMessageDialog(null, "The number of red chariot is wrong!", "错误编码： 103", JOptionPane.ERROR_MESSAGE);
                    canImport = false;
                    break;
                }
                if(count11!=1){
                    JOptionPane.showMessageDialog(null, "The number of red general is wrong!", "错误编码： 103", JOptionPane.ERROR_MESSAGE);
                    canImport = false;
                    break;
                }
                if(count12!=2){
                    JOptionPane.showMessageDialog(null, "The number of red horse is wrong!", "错误编码： 103", JOptionPane.ERROR_MESSAGE);
                    canImport = false;
                    break;
                }
                if(count13!=2){
                    JOptionPane.showMessageDialog(null, "The number of red minister is wrong!", "错误编码： 103", JOptionPane.ERROR_MESSAGE);
                    canImport = false;
                    break;
                }
                if(count14!=5){
                    JOptionPane.showMessageDialog(null, "The number of red soldier is wrong!", "错误编码： 103", JOptionPane.ERROR_MESSAGE);
                    canImport = false;
                    break;
                }
            }


            if (!chessData.get(chessData.size() - 1).equals("H") && !chessData.get(chessData.size() - 1).equals("R")) {
                JOptionPane.showMessageDialog(null, "Missing next player!", "错误编码： 104", JOptionPane.ERROR_MESSAGE);
                canImport = false;
            }
            if(chessData.size()>9){
                for(int i=pos+1;i<chessData.size()-1;i++){
                    if(chessData.get(i).length()!=2&&chessData.get(i).length()!=4){
                        JOptionPane.showMessageDialog(null, "Wrong format of moving steps!", "错误编码： 105", JOptionPane.ERROR_MESSAGE);
                        canImport = false;
                        break;
                    }
                }
            }






            if(canImport){

                if(chessboard.loadGameAndRun(chessData)){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void restartGame() throws IOException {
        chessboard.initAllChessOnBoard();
        chessboard.recordChessBoard.set(0,chessboard.getChessStringList());
        chessboard.chessRoundStep=new ArrayList<>();
        ClickController.clickNumber=0;
        chessboard.scoreList.set(0,0);
        chessboard.scoreList.set(1,0);
        chessboard.eatenHistory=new ArrayList<>();
        ChessGameFrame.getStatusLabel().setText("\"Reveal A Chess To Start!\"");

    }
    public List<String> getChessStringList(){
        List<String> chessStringList=new ArrayList<>();
        for(int i=0;i<8;i++){
            StringBuilder builder=new StringBuilder();
            for(int j=0;j<4;j++){
               if(chessboard.getChessComponents()[i][j].getChessColor()== ChessColor.BLACK){
                   if(chessboard.getChessComponents()[i][j] instanceof AdvisorChessComponent){
                       builder.append('S');
                   }else if(chessboard.getChessComponents()[i][j] instanceof CannonChessComponent){
                       builder.append('P');
                   }else if(chessboard.getChessComponents()[i][j] instanceof ChariotChessComponent){
                       builder.append('C');
                   }else if(chessboard.getChessComponents()[i][j] instanceof GeneralChessComponent){
                       builder.append('J');
                   }else if(chessboard.getChessComponents()[i][j] instanceof horseChessComponent){
                       builder.append('M');
                   }else if(chessboard.getChessComponents()[i][j] instanceof MinisterChessComponent){
                       builder.append('X');
                   }else if(chessboard.getChessComponents()[i][j] instanceof SoldierChessComponent){
                       builder.append('B');
                   }
               }else if (chessboard.getChessComponents()[i][j].getChessColor()== ChessColor.RED){
                   if(chessboard.getChessComponents()[i][j] instanceof AdvisorChessComponent){
                       builder.append('s');
                   }else if(chessboard.getChessComponents()[i][j] instanceof CannonChessComponent){
                       builder.append('p');
                   }else if(chessboard.getChessComponents()[i][j] instanceof ChariotChessComponent){
                       builder.append('c');
                   }else if(chessboard.getChessComponents()[i][j] instanceof GeneralChessComponent){
                       builder.append('j');
                   }else if(chessboard.getChessComponents()[i][j] instanceof horseChessComponent){
                       builder.append('m');
                   }else if(chessboard.getChessComponents()[i][j] instanceof MinisterChessComponent){
                       builder.append('x');
                   }else if(chessboard.getChessComponents()[i][j] instanceof SoldierChessComponent){
                       builder.append('b');
                   }
               }else{
                   builder.append('_');
               }
            }
            chessStringList.add(builder+"\n");
        }//棋局导入完毕
        chessStringList.add("ENDUP");//表示棋局导入完毕，开始输入步骤。

        return chessStringList;
    }
    public List<String> getCurrentColor(){
        List<String> chessStringList=new ArrayList<>();
        if (chessboard.getCurrentColor() == ChessColor.BLACK) {
            chessStringList.add("H\n");
        } else {
            chessStringList.add("R\n");
        }
        return chessStringList;
    }
    public void storeGameFromFile(String path) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bWriter = new BufferedWriter(fileWriter);
            for (String line : chessboard.recordChessBoard.get(0)
            ) {
                bWriter.write(line);
            }
            for (String line : chessboard.chessRoundStep
            ) {
                bWriter.write(line);
            }
            for (String line : getCurrentColor()
            ) {
                bWriter.write(line);
            }
            bWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void exitGame(){
        System.exit(0);
    }
}

