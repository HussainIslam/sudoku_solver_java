public class SudokuManager {
    public static void main(String[] args) {
        SudokuBoard sb = new SudokuBoard();
        sb.inputBoardAsString();
        System.out.println("Before Solving: ");
        sb.printSudokuBoard(0,0);
        sb.solveBoard();
        System.out.println("After Solving: ");
        sb.printSudokuBoard(0, 0);
        //sb.printBoardStatus(0,0);
    }
}
