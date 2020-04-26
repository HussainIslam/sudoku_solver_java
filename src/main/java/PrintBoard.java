public class PrintBoard {
    SudokuBoard board;

    public PrintBoard(SudokuBoard sudokuBoard) {
        this.board = sudokuBoard;
    }

    public void printSudokuBoard(){

        for(int row = 0; row < 9; row++){
            if(row % 3 == 0){
                for(int k = 0; k < 11 * 3; k++){
                    System.out.print("-");
                }
                System.out.println("");
            }
            for (int column = 0; column < 9; column++){
                if(column % 3 == 0){
                    System.out.print("| ");
                }
                int currValue = board.getACell(row, column).getFinalValue();
                System.out.print(currValue == 0 ? " " : currValue);

                if(column == 8){
                    System.out.print(" |");
                } else{
                    System.out.print("  ");
                }
            }
            System.out.println("");
            if(row == 8){
                for(int k = 0; k < 11 * 3; k++){
                    System.out.print("-");
                }
                System.out.println("");
            }
        }
    }

    public void printBoardStatus(){
        for(int row = 0; row < 9; row++){
            if(row % 3 == 0){
                for(int k = 0; k < 11 * 3; k++){
                    System.out.print("-");
                }
                System.out.println("");
            }
            for (int column = 0; column < 9; column++){
                if(column % 3 == 0){
                    System.out.print("| ");
                }
                System.out.print(board.getACell(row, column).getCellStatus()? 1 : 0);

                if(column == 8){
                    System.out.print(" |");
                } else{
                    System.out.print("  ");
                }
            }
            System.out.println("");
            if(row == 8){
                for(int k = 0; k < 11 * 3; k++){
                    System.out.print("-");
                }
                System.out.println("");
            }
        }
    }


}
