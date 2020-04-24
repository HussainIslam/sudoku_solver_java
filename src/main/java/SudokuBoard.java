import java.io.IOException;
import java.util.Scanner;

public class SudokuBoard {
    Cell [][] board;

    public SudokuBoard() {
        this.board = new Cell[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                board[i][j] = new Cell();
            }
        }
    }

    public void printSudokuBoard(int currentRow, int currentColumn){

        for(int row = 0; row < 9; row++){
            if(row % 3 == 0){
                for(int k = 0; k < 11 * 3; k++){
                    System.out.print("-");
                }
                System.out.println("");
            }
            for (int column = 0; column < 9; column++){
                if(column % 3 == 0){
                    System.out.print("| ");
                }
                String characterCode = "";
                if(row == currentRow && column == currentColumn){
                    characterCode = "\033[37;40;5m";
                } else{
                    characterCode = "\033[0m";
                }
                System.out.print(characterCode + board[row][column].getFinalValue());

                characterCode = "\033[0m";

                if(column == 8){
                    System.out.print(characterCode +" |");
                } else{
                    System.out.print(characterCode +"  ");
                }
            }
            System.out.println("");
            if(row == 8){
                for(int k = 0; k < 11 * 3; k++){
                    System.out.print("-");
                }
                System.out.println("");
            }
        }
    }

    public void inputBoardValues(){
        Scanner in = new Scanner(System.in);
        ProcessBuilder processBuilder = new ProcessBuilder();
        for (int row = 0; row < 9; row++){
            for (int column = 0; column < 9; column++){
                printSudokuBoard(row, column);
                System.out.print("Value for cell " + (row + 1) + " - " +(column + 1) +": ");
                board[row][column].updateCellValue(in.nextInt());
                try {
                    Runtime.getRuntime().exec("clear");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
