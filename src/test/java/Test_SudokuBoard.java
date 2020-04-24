import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Test_SudokuBoard {

    private static SudokuBoard sudokuBoard;

    @BeforeAll
    void initializeBoard(){
        sudokuBoard = new SudokuBoard();
    }

    @Test
    void initialBoardCellStatus(){
        for (int row = 0; row < 9; row++){
            for (int column = 0; column < 9; column++){

            }
        }
    }
}
