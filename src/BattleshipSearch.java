import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BattleshipSearch {
    private StrategySearchMethod strategySearchMethod;
    private int[] inputGrid = new int[16];

    public BattleshipSearch() {
        strategySearchMethod = new HorizontalSweepSearch(); 
    }

    public static void main(String[] args) throws IOException {
        BattleshipSearch battleship = new BattleshipSearch();
        ArrayList<String> inputLines = new ArrayList<>();
        FileInputStream fileStream = new FileInputStream("C:\\Users\\srikar reddy\\eclipse-workspace\\BattleShip\\src\\input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileStream));
        String inputLine;
        while ((inputLine = br.readLine()) != null) {
            inputLines.add(inputLine);
        }
        br.close();

        for (int i = 0; i < inputLines.size(); i++) {
            int[][] points = new int[25][25];
            int cellsCount = 0;
            points = battleship.gridConversion(inputLines.get(i));
            System.out.println("Game: " + (i + 1));

            battleship.setStrategy(new HorizontalSweepSearch());
            cellsCount = battleship.searchPoints(points);
            if (cellsCount > 0) {
                battleship.printStatus("Horizontal Sweep Search", cellsCount);
            }

            battleship.setStrategy(new RandomSearchShip());
            cellsCount = battleship.searchPoints(points);
            if (cellsCount > 0) {
                battleship.printStatus("Random Search", cellsCount);
            }

            battleship.setStrategy(new StrategicSearchShip());
            cellsCount = battleship.searchPoints(points);
            if (cellsCount > 0) {
                battleship.printStatus("Strategic Search", cellsCount);
            }
        }
    }

    public void setStrategy(StrategySearchMethod strategy) {
        strategySearchMethod = strategy;
    }

    public int searchPoints(int[][] coordinates) {
        return strategySearchMethod.search(coordinates);
    }

    public int[][] gridConversion(String input) {
        int[][] points = new int[25][25];
        String[] inputX = input.replace("(", "").split("\\)");
        int index = 0;
        for (int i = 0; i < inputX.length; i++) {
            String[] inputY = inputX[i].split(",");
            inputGrid[index] = Integer.parseInt(inputY[0]);
            inputGrid[index + 1] = Integer.parseInt(inputY[1]);
            index = index + 2;
            if (i < 5)
                points[Integer.parseInt(inputY[0])][Integer.parseInt(inputY[1])] = 1;
            else
                points[Integer.parseInt(inputY[0])][Integer.parseInt(inputY[1])] = 2;
        }
        return points;
    }

    public void printStatus(String strategy, int noOfCells) {
        System.out.println("Strategy: " + strategy);
        System.out.println("Number of cells searched: " + (noOfCells-1));
        System.out.println("Carrier found: (" + inputGrid[0] + "," + inputGrid[1] + ") to (" + inputGrid[8] + ","
                + inputGrid[9] + ") Submarine found: (" + inputGrid[10] + "," + inputGrid[11] + ") to ("
                + inputGrid[14] + "," + inputGrid[15] + ")");
    }
}
