public class SudokuManager {
    public static void main(String[] args) {
        SudokuBoard sb = new SudokuBoard();
        sb.inputBoardAsString();
        sb.printSudokuBoard(0,0);
        sb.solveBoard();
        sb.printSudokuBoard(0, 0);
        //sb.printBoardStatus(0,0);
    }
}
