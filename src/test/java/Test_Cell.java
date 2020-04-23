import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Test_Cell {
    private static Cell cell;

    @BeforeAll
    static void initializeCell(){
        cell = new Cell();
    }

    @Test
    void initialCellStatusTest(){
        assertFalse(cell.getCellStatus());
    }
}
