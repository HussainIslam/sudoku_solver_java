public class SudokuBoard {
    Cell [][] board;

    public SudokuBoard() {
        this.board = new Cell[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                board[i][j] = new Cell();
            }
        }
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

                System.out.print(board[row][column].getFinalValue());

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
