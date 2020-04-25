import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Stream;

public class Test_SudokuBoard {

    private static SudokuBoard sudokuBoard;
    private static final String dummyData =
            "080720000" +
            "900003080" +
            "000804269" +
            "000000608" +
            "538946002" +
            "006087530" +
            "009400000" +
            "003291857" +
            "100300940";

    @BeforeAll
    private static void initializeBoard(){
        sudokuBoard = new SudokuBoard();
    }


    @Nested
    public class InputTest{
        private final InputStream systemIn = System.in;
        private ByteArrayInputStream testIn;

        @BeforeEach
        public void testInput(String data){
            testIn = new ByteArrayInputStream(data.getBytes());
            System.setIn(testIn);
        }

        @AfterEach
        public void restoreSystemInput(){
            System.setIn(systemIn);
        }


        @Test
        public void testSudokuBoard(){
            testInput(dummyData);

            sudokuBoard.inputBoardValues();

        }

    }
}
