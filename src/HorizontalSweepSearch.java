public class HorizontalSweepSearch implements StrategySearchMethod {
    public int search(int[][] grid) {
        int cellCount = 0;
        int carrierCount = 0;
        int submarineCount = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                cellCount++;

                if (grid[i][j] == 1) {
                    carrierCount++;
                } else if (grid[i][j] == 2) {
                    submarineCount++;
                }

                if (carrierCount == 5 && submarineCount == 3) {
                    return cellCount;
                }
            }
        }

        return 0;
    }
}
