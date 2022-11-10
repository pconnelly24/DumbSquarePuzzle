import Square.State;

public class Line {
    private enum Direction{
        HORIZONTAL,
        VERTICAL
    }

    public final Direction direction = Direction.HORIZONTAL;

    private Group[] groups;

    private Square[] squares;

    public Line(Group[] inGroups, Square[] inSquares){
        this.groups = inGroups;
        this.squares = inSquares;
    }

    public boolean check(){
        boolean changed = false;

        int[] startPos = new int[groups.length];

        // Sets up the last one
        startPos[startPos.length - 1] = squares.length - groups[groups.length - 1].getSize();
        // And all the others
        for (int i = startPos.length - 2; i >= 0; i--) {
            startPos[i] = startPos[i + 1] - groups[i].getSize() - 1;
        }

        // New Plan: Recursion Recursion Recursion
        recursion(startPos, 0);

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

    public void recursion(int[] inStartPos, int movingPlace){
        // Makes sure it doesn't get stuck
        if(movingPlace < inStartPos.length){
            int backEnd = 0;
            // If there is another block between it and the end
            if(movingPlace > 0){
                // Set the backEnd to the last block's start + its length + an X
                backEnd = inStartPos[movingPlace - 1] + groups[movingPlace - 1].getSize() + 1;
            }
            int initialStartingPlace = inStartPos[movingPlace];
            for (int i = inStartPos[movingPlace]; i >= backEnd; i--) {
                // Move back one
                inStartPos[movingPlace] = i;

                // Make a new state array
                Square.State[] curState = new Square.State[squares.length];
                int curIndex = 0;
                for (int j = 0; j < curState.length; j++) {
                    if(curIndex < inStartPos.length && j >= inStartPos[curIndex]){
                        if(j >= inStartPos[curIndex] + groups[curIndex].getSize() - 1){
                            curIndex++;
                        }
                        curState[j] = Square.State.FILLED;
                    }
                    else{
                        curState[j] = Square.State.X;
                    }
                }
                // Check if the current arrangment is valid
                if(isValid(curState)){
                    // update all the possibilities
                    updateChance(curState);
                }
                // Did somebody say recursion?
                recursion(inStartPos, movingPlace + 1);
            }
            // Move back for other recursions
            inStartPos[movingPlace] = initialStartingPlace;
        }
    }

    public boolean isValid(Square.State[] inLineStates){
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

    public Group[] getGroups(){
        return groups;
    }

    public Square[] getSquares(){
        return squares;
    }
}
