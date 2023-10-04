public class StrategicSearchShip implements StrategySearchMethod {
    public int search(int[][] grid) {
        int cellCount = 0;
        int carrierCount = 0;
        int submarineCount = 0;
        int end = grid.length - 1;

        for (int i = 0; i <= grid.length / 2; i++) {
            for (int j = 0; j < grid.length; j++) {
                cellCount++;

                int r1 = i + 4;
                int r2 = i + 2;
                int r3 = j + 4;
                int r4 = j + 2;

                if (carrierCount == 0 && (grid[i][j] == 1 || grid[end - i][j] == 1)) {
                    if ((r3 < 25 && grid[i][r3] == 1) || grid[r1][j] == 1) {
                        carrierCount = 1;
                    } else if ((r3 < 25 && grid[end - i][r3] == 1) || grid[end - r1][j] == 1) {
                        carrierCount = 1;
                    }
                }

                if (submarineCount == 0 && (grid[i][j] == 2 || grid[end - i][j] == 2)) {
                    if ((r4 < 25 && grid[i][r4] == 2) || grid[r2][j] == 2) {
                        submarineCount = 1;
                    } else if ((r4 < 25 && grid[end - i][r4] == 2) || grid[end - r2][j] == 2) {
                        submarineCount = 1;
                    }
                }

                if (carrierCount == 1 && submarineCount == 1) {
                    return cellCount;
                }
            }
        }
        return 0;
    }
}
