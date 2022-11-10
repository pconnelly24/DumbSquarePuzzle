import Square.State;

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

        int curPos = 0;
        while(curPos < squares.length && squares[i].getState() != Square.State.EMPTY){
            curPos++;
        }
        
        if(curPos == squares.length){
            return false;
        }

        recursion(curPos);

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

        // Try making the square White
        squares[inCurPos] = Square.State.WHITE;
        if(isValid(curState)){
            // update all the possibilities
            updateChance(curState);
        }
        recursion();

        // Try making the square Blue
        squares[inCurPos] = Square.State.BLUE;
        if(isValid(curState)){
            // update all the possibilities
            updateChance(curState);
        }
        recursion();

        // Reset the Square
        squares[inCurPos] = Square.State.EMPTY;
    }

    public boolean isValid(Square.State[] inLineStates){
        // This is what really needs to change :(
        for (int i = 0; i < squares.length; i++) {
            // Check if the square is not empty and if they aren't the same
            if(squares[i].getState() != Square.State.EMPTY && squares[i].getState() != inLineStates[i]){
                return false;
            }
        }
        return true;
    }

    public void updateChance(Square.State[] inLineStates){
        for (int i = 0; i < squares.length; i++) {
            switch (inLineStates[i]) {
                case WHITE:
                    squares[i].setCanWhite();
                    break;
                case BLUE:
                    squares[i].setCanWBlue();
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
