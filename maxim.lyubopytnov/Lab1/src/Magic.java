import java.util.Scanner;

public class Magic {
    public static int[][] grid = new int[rowSetGet()][colSetGet()];

    private static int rowSetGet() {
        System.out.println("Enter size row: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static int colSetGet() {
        System.out.println("Enter size col: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static int[][] fillArrayRandomAndPrint(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                grid[i][j] = (int) (1 + Math.random() * 9);
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        return grid;
    }

    public static boolean isMagic(int[][] grid, int row, int col) {
        int[] record = new int[10];
        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                if (grid[i][j] < 1 || grid[i][j] > 9 || record[grid[i][j]] > 0) {
                    return false;
                }
                record[grid[i][j]] = 1;
            }
        }
        int mainDiagonal = grid[row][col] + grid[row + 1][col + 1] + grid[row + 2][col + 2];
        int sideDiagonal = grid[row][col + 2] + grid[row + 1][col + 1] + grid[row + 2][col];
        if (mainDiagonal != sideDiagonal) {
            return false;
        }

        for (int i = 0; i < 3; i++) {
            if (grid[row + i][col] + grid[row + i][col + 1] + grid[row + i][col + 2] != mainDiagonal) {
                return false;
            }
            if (grid[row][col + i] + grid[row + 1][col + i] + grid[row + 2][col + i] != mainDiagonal) {
                return false;
            }
        }
        return true;
    }
}
