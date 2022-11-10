public class Line {
    private enum Direction{
        HORIZONTAL,
        VERTICAL
    }

    public final Direction direction = Direction.HORIZONTAL;

    private Square[] squares;

    public Line(Square[] inSquares){
        this.squares = inSquares;
    }

    public boolean check(){
        boolean changed = false;

        recursion(0);

        for (Square square : squares) {
            // ^ is an XOR gate, checks if a square is can either be X or Filled but not both and confimrs if the square is empty
            if(square.getCanWhite() ^ square.getCanBlue() && square.getState() == Square.State.EMPTY){
                if(square.getCanWhite()){
                    square.setState(Square.State.WHITE);
                }
                else{
                    square.setState(Square.State.BLUE);
                }
                changed = true;
            }
            square.resetCan();
        }

        return changed;
    }

    public void recursion(int inCurPos){
        while(inCurPos < squares.length && squares[inCurPos].getState() != Square.State.EMPTY){
            inCurPos++;
        }
        
        if(inCurPos < squares.length){
            // Try making the square White
            squares[inCurPos].setState(Square.State.WHITE);
            recursion(inCurPos);

            // Try making the square Blue
            squares[inCurPos].setState(Square.State.BLUE);
            recursion(inCurPos);

            // Reset the Square
            squares[inCurPos].setState(Square.State.EMPTY);
        }
        else{
            if(isValid()){
                // update all the possibilities
                updateChance();
            }
        }
    }

    public boolean isValid(){
        // Total of each color
        int whiteTotal = 0;
        int blueTotal = 0;
        // How many of each color in a row
        int whiteCont = 0;
        int blueCont = 0;

        for (Square square : squares) {
            // Check if White or Blue
            switch (square.getState()) {
                case WHITE:
                    // Checks if there were two before it
                    if(whiteCont == 2){
                        return false;
                    }
                    // add the White square
                    whiteCont++;
                    whiteTotal++;
                    // reset the Blue's in a row
                    blueCont = 0;
                    break;
                case BLUE:
                    // Checks if there were two before it
                    if(blueCont == 2){
                        return false;
                    }
                    // Add the Blue square
                    blueCont++;
                    blueTotal++;
                    // Reset the White's in a row
                    whiteCont = 0;
                    break;
                default:
                    // AHHHHHHHHHH
                    break;
            }
        }
        // Check if there are too many White's or Blue's
        if(whiteTotal > squares.length / 2 || blueTotal > squares.length / 2){
            return false;
        }
        // Congratulations
        return true;
    }

    public void updateChance(){
        for (int i = 0; i < squares.length; i++) {
            switch (squares[i].getState()) {
                case WHITE:
                    squares[i].setCanWhite();
                    break;
                case BLUE:
                    squares[i].setCanBlue();
                    break;
                default:
                    // The world might end
                    break;
            }
        }
    }

    public Square[] getSquares(){
        return squares;
    }
}
