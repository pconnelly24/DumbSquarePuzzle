import java.security.acl.Group;

public class Tester {
    public static void main(String[] args) {
        int[][] whites = {};
        int[][] blues = {{3, 0}, {3, 3}, {5, 4}};
        Board testBoard = new Board(6, whites, blues);
        testBoard.Solve();
        System.out.println(testBoard.printBoard());
    }
} 