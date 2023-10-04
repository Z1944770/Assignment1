import java.util.Random;

public class RandomSearchShip implements StrategySearchMethod {
    public int search(int[][] grid) {
        int cellCount = 0;
        int carrierCount = 0;
        int submarineCount = 0;
        boolean[][] searched = new boolean[grid.length][grid.length];
        Random random = new Random();

        while (carrierCount < 5 || submarineCount < 3) {
            int row = random.nextInt(grid.length);
            int col = random.nextInt(grid.length);

            if (!searched[row][col]) {
                cellCount++;

                if (grid[row][col] == 1) {
                    carrierCount++;
                } else if (grid[row][col] == 2) {
                    submarineCount++;
                }

                searched[row][col] = true;
            }
        }

        return cellCount;
    }
}
