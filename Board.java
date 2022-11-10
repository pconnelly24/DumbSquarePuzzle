public class Board {
    
    private int size;
    private Square[][] board;

    private Line[] rows;
    private Line[] columns;

    public Board(int inSize, int[][] inWhites, int[][] inBlues){
        this.size = inSize;
        board = new Square[size][size];

        // Fills the board with Squares
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Square();
            }
        }

        // Set up white squares
        for (int[] white : inWhites) {
            board[white[1]][white[0]].setState(Square.State.WHITE);
        }
        // Set up blue squares
        for (int[] blue : inBlues) {
            board[blue[1]][blue[0]].setState(Square.State.BLUE);
        }


        // Set up array lengths
        rows = new Line[size];
        columns = new Line[size];

        // Rows
        for (int i = 0; i < size; i++) {
            rows[i] = new Line(board[i]);
        }

        // Columns
        for (int i = 0; i < size; i++) {
            Square[] colSquare = new Square[size];
            for (int j = 0; j < colSquare.length; j++) {
                colSquare[j] = board[j][i];
            }
            columns[i] = new Line(colSquare);
        }
    }

    public void Solve(){
        // Solving plan!

        // New plan - check every possible alignment and see which are either always Filled or always X
        boolean changed = true;
        // Continues to loop while the board is still being changed
        while(changed){
            changed = false;
            boolean smallChange = false;
            // Looks through all of the rows
            for (Line row : rows) {
                smallChange = row.check();
                // Checks if something changed
                if(smallChange){
                    // Switches over to columns immedeatly
                    break;
                }
            }
            changed |= smallChange;
            smallChange = false;
            // Looks through all of the columns
            for (Line column : columns) {
                smallChange = column.check();
                // Checks if something changed
                if(smallChange){
                    // Switches back to rows immedeatly
                    break;
                }
            }
            changed |= smallChange;
            // break;
        }
    }

    // Print it!
    public String printBoard(){
        String outString = "";
        for (Square[] row : board) {
            for (Square square : row) {
                switch(square.getState()){
                    case EMPTY:
                        outString += "O ";
                    break;
                    case WHITE:
                        outString += "W ";
                    break;
                    case BLUE:
                        outString += "B ";
                    break;
                    default:
                        // How did we get here?
                        outString += "7 ";
                    break;
                }
            }
            outString +="\n";
        }
        return outString;
    }
}
