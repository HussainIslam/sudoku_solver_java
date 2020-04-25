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
                int currValue = board[row][column].getFinalValue();
                System.out.print(characterCode + (currValue == 0 ? " " : currValue));

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

    public void inputBoardAsString(){
        //Scanner in = new Scanner(System.in);
        //System.out.print("Enter all digits from left to right without any space.");

        String temp = "080720000" +
                "900003080" +
                "000804269" +
                "000000608" +
                "538946002" +
                "006087530" +
                "009400000" +
                "003291857" +
                "100300940";
        /*
        String temp = "478309120" +
                "009128754" +
                "251070308" +
                "304782905" +
                "900630240" +
                "712905860" +
                "826090471" +
                "043067009" +
                "500814032";
        */
        for (int row = 0; row < 9; row++){
            for(int column = 0; column < 9; column++){
                int startIndex = row * 9 + column;
                board[row][column].inputCellValue(Integer.parseInt(temp.substring(startIndex, startIndex + 1)));
            }
        }
        //printSudokuBoard(0, 0);
        //printBoardStatus(0,0);
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

    public void solveBoard(){
        do {
            for (int row = 0; row < 9; row++){
                for (int column = 0; column < 9; column++){
                    solveCell(row, column);
                }
            }
            //printBoardStatus(0, 0);
        } while (!isSolved());
    }


    private void solveCell(int row, int column){
        if (!board[row][column].getCellStatus()) {
            checkHorizontal(row, column);
            checkVertical(row, column);
            checkSmallerSquare(row, column);
        }
    }

    private boolean isSolved(){
        boolean rv = true;
        for(int row = 0; row < 9; row++){
            for (int column = 0; column < 9; column++){
                rv = board[row][column].getCellStatus();
                if(!rv){
                    break;
                }
            }
            if(!rv){
                break;
            }
        }
        return rv;
    }

    private void checkHorizontal(int row, int column){
        checkHorizontal(row, 0, column);
    }

    private void checkHorizontal(int row, int currentColumn, int baseColumn){
        if (currentColumn < 9) {
            if(board[row][currentColumn].getCellStatus()){
                board[row][baseColumn].removeAPossibleValue(board[row][currentColumn].getFinalValue());
            }
            checkHorizontal(row, currentColumn + 1, baseColumn);
        }
    }

    private void checkVertical(int row, int column){
        checkVertical(row, 0, column);
    }

    private void checkVertical(int baseRow, int currentRow, int column) {
        if (currentRow < 9) {
            if (board[currentRow][column].getCellStatus()) {
                board[baseRow][column].removeAPossibleValue(board[currentRow][column].getFinalValue());
            }
            checkVertical(baseRow, currentRow + 1, column);
        }
    }

    private void checkSmallerSquare(int row, int column){
        int currentRow = row - (row % 3);
        int currentColumn = column - (column % 3);
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if(board[currentRow + i][currentColumn + j].getCellStatus()){
                    board[row][column].removeAPossibleValue(board[currentRow + i][currentColumn + j].getFinalValue());
                }
            }
        }

    }

    public void printBoardStatus(int currentRow, int currentColumn){
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
                System.out.print(characterCode + (board[row][column].getCellStatus()? 1 : 0));

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

}
