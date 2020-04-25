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

    public void solveCell(int row, int column){

    }

    private void checkHorizontal(int row){
        checkHorizontal(row, 0);
    }

    private void checkHorizontal(int row, int column){
        if (column < 9) {
            if(board[row][column].getCellStatus()){
                checkHorizontal(row, column + 1);
            }
            else{
                for(int i = 0; i < 9; i++){
                    if(board[row][i].getCellStatus()){
                        board[row][column].removeAPossibleValue(board[row][i].getFinalValue());
                    }
                }
            }
        }
    }

    private void checkVertical(int column){
        checkVertical(0, column);
    }

    private void checkVertical(int row, int column){
        if(row < 9){
            if(board[row][column].getCellStatus()){
                checkVertical(row, column);
            }
            else{
                for (int i = 0; i < 9; i++){
                    if(board[i][column].getCellStatus()){
                        board[row][column].removeAPossibleValue(board[i][column].getFinalValue());
                    }
                }
            }
        }
    }

    private void checkSmallerSquare(int row, int column){
        int currentRow = row;
        int currentColumn = column;
        if(row % 3 > 0 || column % 3 > 0){
            currentRow = row - (row % 3);
            currentColumn = column - (column % 3);
        }
        for(int i = 0; i < 3; i++){
            currentRow += i;
            for (int j = 0; j < 3; j++){
                currentColumn += j;
                if(board[currentRow][currentColumn].getCellStatus()){
                    board[row][column].removeAPossibleValue(board[currentRow][currentColumn].getFinalValue());
                }
            }
        }

    }



}
