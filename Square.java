public class Square {
    
    public enum State{
        EMPTY,
        WHITE,
        BLUE
    }

    // Start EMPTY by default
    private State state = State.EMPTY;

    private boolean canWhite;
    private boolean canBlue;

    public boolean setState(State inState){
        state = inState;
        // Just to be safe incase I need to check for stuff later
        return true;
    }

    public State getState(){
        return state;
    }

    public boolean getCanWhite(){
        return canWhite;
    }
    public boolean getCanBlue(){
        return canBlue;
    }
    public void setCanBlue(){
        canWhite = true;
    }
    public void setCanWhite(){
        canBlue = true;
    }
    public void resetCan(){
        canWhite = false;
        canBlue = false;
    }
}
