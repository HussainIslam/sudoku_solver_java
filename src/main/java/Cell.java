import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cell {
    private boolean cellStatus;
    private int finalValue;
    private List<Integer> possibleValues;

    public Cell() {
        this.cellStatus = false;
        this.finalValue = 0;
        this.possibleValues = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)) ;
    }

    public boolean getCellStatus(){
        return cellStatus;
    }

    public int getFinalValue(){
        return finalValue;
    }

    public List<Integer> getPossibleValues(){
        return possibleValues;
    }

    public void removeAPossibleValue(int removeValue){
        possibleValues.remove(Integer.valueOf(removeValue));
        if(possibleValues.size() == 1){
            updateCellValue(possibleValues.get(0));
        }
    }

    public void updateCellValue(int value){
        this.finalValue = value;
        this.cellStatus = true;
        this.possibleValues = new ArrayList<>();
    }

    public void updateTemporaryValue(){
        if(!cellStatus){
            this.finalValue = possibleValues.get(0);
        }
    }

    public void inputCellValue(int value){
        if(value != 0){
            updateCellValue(value);
        } else{
            this.finalValue = value;
        }
    }

}
