import java.security.acl.Group;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Tester {
    public static void main(String[] args) {
        int[][] whites = {};
        int[][] blues = {};
        Board testBoard = new Board(6, whites, blues);
        testBoard.Solve();
        System.out.println(testBoard.printBoard());
    }
} 