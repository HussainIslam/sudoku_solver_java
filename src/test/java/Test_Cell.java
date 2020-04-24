import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Test_Cell {
    private static Cell cell;

    @BeforeAll
    static void initializeCell(){
        cell = new Cell();
    }

    @Test
    @DisplayName("Initial Cell Status")
    void initialCellStatusTest(){
        assertFalse(cell.getCellStatus());
    }

    @Test
    @DisplayName("Initial Final Value")
    void initialFinalValueTest(){
        assertEquals(0, cell.getFinalValue());
    }

    @Test
    @DisplayName("Initial Possible Values")
    void initialPossibleValues(){
        List<Integer> expectedPossibleValues = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        List<Integer> actualPossibleValues = cell.getPossibleValues();
        assertEquals(expectedPossibleValues, actualPossibleValues);
    }

    @Nested
    class RemovePossibleValue{
        Cell innerCell;

        @BeforeEach
        void resetActualValues(){
            innerCell = new Cell();
        }

        @ParameterizedTest(name = "{0} removed")
        @ValueSource(ints ={ 1, 2, 3, 4, 5, 6, 7, 8, 9})
        void removeOneValue(int removeValue){
            List<Integer> expectedPossibleValues = new ArrayList<>();
            for(int i = 1; i <= innerCell.getPossibleValues().size(); i++){
                if (i != removeValue){
                    expectedPossibleValues.add(i);
                }
            }
            innerCell.removeAPossibleValue(Integer.valueOf(removeValue));
            assertEquals(expectedPossibleValues, innerCell.getPossibleValues());
        }

        @ParameterizedTest
        @CsvSource(value = {"1, 3, 5, 7", "2, 4, 6, 8", "3, 4, 5, 6", "1, 2, 8, 9"})
        void removeMultipleValues(String s1, String s2, String s3, String s4){
            List<Integer> expectedPossibleValues = new ArrayList<>();
            int n1 = Integer.parseInt(s1);
            int n2 = Integer.parseInt(s2);
            int n3 = Integer.parseInt(s3);
            int n4 = Integer.parseInt(s4);

            for(int i = 1; i <= innerCell.getPossibleValues().size(); i++){
                if(i != n1 && i != n2 && i != n3 && i != n4){
                    expectedPossibleValues.add(i);
                }
            }
            innerCell.removeAPossibleValue(n1);
            innerCell.removeAPossibleValue(n2);
            innerCell.removeAPossibleValue(n3);
            innerCell.removeAPossibleValue(n4);

            assertEquals(expectedPossibleValues, innerCell.getPossibleValues());
        }

        @Test
        void removeAllValues(){
            for(int i = 1; i <= 9; i++){
                innerCell.removeAPossibleValue(Integer.valueOf(i));
            }
            assertEquals(0,innerCell.getPossibleValues().size());
        }
    }


}
