public class SudokuManager {
    public static void main(String[] args) {
        SudokuBoard sb = new SudokuBoard();
        PrintBoard pb = new PrintBoard(sb);
        sb.inputBoardAsString();
        System.out.println("Before Solving: ");
        pb.printSudokuBoard();
        sb.solveBoard();
        System.out.println("After Solving: ");
        pb.printSudokuBoard();
    }
}
