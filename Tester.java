public class Tester {
    public static void main(String[] args) {
        // int[][] whites = {{0, 1}, {0, 2}, {2, 1}, {2, 2}, {2, 5}, {4, 5}};
        // int[][] blues = {{3, 0}, {3, 3}, {5, 4}};
        int[][] whites = {{0, 1}, {0, 5}, {1, 3}, {2, 5}, {3, 2}, {3, 5}, {4, 2}};
        int[][] blues = {{3, 0}, {5, 0}};
        Board testBoard = new Board(6, whites, blues);
        testBoard.Solve();
        System.out.println(testBoard.printBoard());
    }
}
