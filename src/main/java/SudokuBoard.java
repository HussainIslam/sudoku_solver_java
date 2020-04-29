import java.io.IOException;
import java.util.Scanner;

public class SudokuBoard {
    private Cell [][] board;
    private PrintBoard printBoard = new PrintBoard(this);

    public SudokuBoard() {
        this.board = new Cell[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                board[i][j] = new Cell();
            }
        }
    }

    public Cell getACell(int row, int column){
        return board[row][column];
    }

    public void inputBoardAsString(){
        //Scanner in = new Scanner(System.in);
        //System.out.print("Enter all digits from left to right without any space.");

        //hard
        /*String temp =
                "200830000" +
                "000006000" +
                "000700815" +
                "700400509" +
                "049300002" +
                "003000000" +
                "327010000" +
                "006040080" +
                "000600300";*/

        // easy
        //String temp = "080720000900003080000804269000000608538946002006087530009400000003291857100300940";
        // another easy
        String temp = "000086301000020658000500702102640587009002000540710000020090006800060005396070020";

        // medium
        //String temp = "000000000000093670080060001500620300008050000046000020090000037200000000000180006";
        //childish
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
    }

    public void inputBoardValues(){
        Scanner in = new Scanner(System.in);
        for (int row = 0; row < 9; row++){
            for (int column = 0; column < 9; column++){
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
        solveBoard(0, 0);
    }

    public void solveBoard(int row, int column){
        if (!isSolved()) {
            if(row < 9){
                if(column < 9){
                    boolean cellSolved = solveCell(row, column);
                    if(cellSolved){
                        solveBoard(row, column + 1);
                    }
                    else{
                        if (column != 0){
                            solveBoard(row, column - 1);
                        }
                        else{
                            if(row != 0){
                                solveBoard(row - 1, 8);
                            }
                        }
                    }
                }
                else{
                    solveBoard(row + 1, 0);
                }
            }
            else{
                solveBoard(0, 0);
            }
        }

    }


    private boolean solveCell(int row, int column){
        boolean rv = false;
        if (!board[row][column].getCellStatus()) {
            board[row][column].updateTemporaryValue();
            if(board[row][column].getFinalValue() != -1){
                if(!isValid(row, column)){
                    rv = solveCell(row, column);
                }
                else {
                    rv = true;
                }
            }
        }
        return rv;
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
        checkSmallerSquare(row, column, row - (row % 3), column - (column % 3));
    }

    private void checkSmallerSquare(int baseRow, int baseColumn, int currentRow, int currentColumn){
        if((currentRow - (baseRow - (baseRow % 3))) < 3){
            if ((currentColumn - (baseColumn - (baseColumn % 3))) < 3){
                if(board[currentRow][currentColumn].getCellStatus()){
                    board[baseRow][baseColumn].removeAPossibleValue(board[currentRow][currentColumn].getFinalValue());
                }
                checkSmallerSquare(baseRow, baseColumn, currentRow, currentColumn + 1);
            }
            else{
                checkSmallerSquare(baseRow, baseColumn, currentRow + 1, currentColumn - 3);
            }
        }
    }

    private boolean isValid(int row, int column){
        boolean rv = true;
        rv = isValidHorizontally(row, column, 0);
        if(rv){
            rv = isValidVertically(row, 0, column);
        }
        if(rv){
            rv = isValidSmallerSquare(row, column, row - (row % 3), column - (column % 3));
        }
        return rv;
    }

    private boolean isValidHorizontally(int row, int baseColumn, int currentColumn){
        boolean rv = true;
        if(currentColumn < 9){
            if (currentColumn != baseColumn) {
                if(     board[row][currentColumn].getFinalValue() != 0 &&
                        board[row][currentColumn].getFinalValue() == board[row][baseColumn].getFinalValue()){
                    rv =  false;
                }
                else{
                    rv = isValidHorizontally(row, baseColumn, currentColumn + 1);
                }
            }
            else{
                rv = isValidHorizontally(row, baseColumn, currentColumn + 1);
            }
        }
        return rv;
    }

    private  boolean isValidVertically(int row,int currentRow, int column){
        boolean rv = true;
        if(currentRow < 9){
            if (currentRow != row) {
                if (    board[currentRow][column].getFinalValue() != 0 &&
                        board[currentRow][column].getFinalValue() == board[row][column].getFinalValue()){
                    rv = false;
                }
                else{
                    rv = isValidVertically(row, currentRow + 1, column);
                }
            }
            else{
                rv = isValidVertically(row, currentRow + 1, column);
            }
        }
        return rv;
    }

    private boolean isValidSmallerSquare(int row, int column, int currentRow, int currentColumn){
        boolean rv = true;
        if((currentRow - (row - (row % 3))) < 3){
            if(currentColumn - (column - (column % 3)) < 3){
                if (currentRow != row &&  currentColumn != column) {
                    if(     board[currentRow][currentColumn].getFinalValue() != 0 &&
                            board[currentRow][currentColumn].getFinalValue()
                            == board[row][column].getFinalValue()){
                        rv = false;
                    }
                    else{
                        isValidSmallerSquare(row, column, currentRow, currentColumn + 1);
                    }
                }
                else{
                    isValidSmallerSquare(row, column, currentRow, currentColumn + 1);
                }
            }
            else{
                isValidSmallerSquare(row, column, currentRow + 1, column - (column % 3));
            }
        }
        return rv;
    }



}
