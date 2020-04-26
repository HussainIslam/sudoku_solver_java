public class PrintBoard {
    SudokuBoard board;

    public PrintBoard(SudokuBoard sudokuBoard) {
        this.board = sudokuBoard;
    }

    public void printSudokuBoard(){
        print(false);
    }

    public void printBoardStatus(){
        print(true);
    }

    private void print(boolean printStatus) {
        for(int row = 0; row < 9; row++){
            if(row % 3 == 0){
                printHorizontalBorder();
            }
            for (int column = 0; column < 9; column++){
                printInsideVerticalBorder(column);

                System.out.print(getValueForPrint(printStatus, row, column));

                printLastVerticalBorder(column);
            }
            System.out.println("");
            if(row == 8){
                printHorizontalBorder();
            }
        }
    }

    private String getValueForPrint(boolean printStatus, int row, int column) {
        String value;
        if (printStatus){
            value = board.getACell(row, column).getCellStatus() ? "1" : "0";
        }
        else{
            int cellValue = board.getACell(row, column).getFinalValue();
            value = cellValue == 0 ? " " : String.valueOf(cellValue);
        }
        return value;
    }


    private void printInsideVerticalBorder(int column) {
        if (column % 3 == 0) {
            System.out.print("| ");
        }
    }

    private void printLastVerticalBorder(int column) {
        if (column == 8) {
            System.out.print(" |");
        } else {
            System.out.print("  ");
        }
    }

    private void printHorizontalBorder() {
        for (int k = 0; k < 11 * 3; k++) {
            System.out.print("-");
        }
        System.out.println("");
    }


}
