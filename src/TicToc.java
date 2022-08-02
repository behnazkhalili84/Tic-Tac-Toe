package com.sample.spring.session1;

import java.util.Scanner;

public class TicToc {
    private static String[][] board = new String[3][3];
    private static boolean xPosition=true;
    public static void main(String[] args) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = " ";
                count++;
            }
        }
        printStage(board);
        changePos(board,xPosition);

    }
    public static void printStage(String[][] board) {
        System.out.println("---------");
        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }


    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void changePos(String[][] board,boolean xPosition) {
        System.out.println("Enter the coordinates:");
        Scanner scanner = new Scanner(System.in);
        boolean oPosition=false;
        if(xPosition!=true){
            oPosition=true;
        }

        boolean isTrue = false;
        while (isTrue == false) {
            String inputChange = scanner.nextLine();
            String[] splitStr = inputChange.split("\\s+");
            String inputWithoutSpace = inputChange.replaceAll("\\s", "");
            if (isInteger(inputWithoutSpace) == false) {
                System.out.println("You should enter numbers!");
                continue;
            }
            //System.out.println(Integer. parseInt(splitStr[0]));
            // System.out.println(Integer.parseInt(splitStr[1]));
            int first = Integer.parseInt(splitStr[0]);
            int second = Integer.parseInt(splitStr[1]);
            //System.out.println(first+"first");
            //System.out.println(second+"second");
            if ((first > 3 || second > 3) || (first < 1 || second < 1)) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            if (board[first - 1][second - 1] == "X" || board[first - 1][second - 1] == "O") {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            if(board[first - 1][second - 1]!=" "){
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            if(xPosition){
                board[first - 1][second - 1] = "X";
                xPosition=false;
                oPosition=true;
            }
            else {
                board[first - 1][second - 1] = "O";
                xPosition=true;
                oPosition=false;
            }

            isTrue = true;
        }

        printStage(board);
        winnerIden(board,xPosition);
    }


    public static void winnerIden(String[][] board,boolean xPosition) {
        boolean notFinished = false;
        boolean xWins = false;
        boolean oWins = false;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == " ") {
                    notFinished = true;
                }
            }
        }
        String strX="XXX";
        String strO="OOO";
        String[] line=new String[8];
        line[0]=board[0][0]+board[0][1]+board[0][2];
        line[1]=board[1][0]+board[1][1]+board[1][2];
        line[2]=board[2][0]+board[2][1]+board[2][2];
        line[3]=board[0][0]+board[1][1]+board[2][2];
        line[4]=board[0][0]+board[1][0]+board[2][0];
        line[5]=board[0][1]+board[1][1]+board[2][1];
        line[6]=board[0][2]+board[1][2]+board[2][2];
        line[7]=board[2][0]+board[1][1]+board[0][2];
        for(int i=0;i<8;i++){
            if(line[i].equals(strX)){
                xWins=true;
            }
            else if(line[i].equals(strO)){
                oWins=true;
            }
        }
        if(xWins){
            System.out.println("X wins");
            return;
        } else if (oWins) {                             //O Wins
            System.out.println("O wins");
            return;
        }else if (notFinished && (!xWins && !oWins)) {        //notFinished
            changePos(board,xPosition);
        }
        else if (!notFinished && (!xWins && !oWins)){
            System.out.println("Draw");
        }
    }
}