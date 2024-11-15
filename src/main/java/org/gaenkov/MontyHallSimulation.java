package org.gaenkov;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MontyHallSimulation {
    private static final int SIMULATIONS = 1000;

    public void runSimulation() {
        Map<Integer, GameResult> results = new HashMap<>();
        Random random = new Random();

        for (int i = 0; i < SIMULATIONS; i++) {
            boolean win = playGame(random);
            results.put(i, new GameResult(i, win));
        }

        printStatistics(results);
    }

    private boolean playGame(Random random) {
        int chosenDoor = random.nextInt(3);
        int carDoor = random.nextInt(3);
        int revealedDoor;

        do {
            revealedDoor = random.nextInt(3);
        } while (revealedDoor == chosenDoor || revealedDoor == carDoor);

        int switchedDoor;
        do {
            switchedDoor = random.nextInt(3);
        } while (switchedDoor == chosenDoor || switchedDoor == revealedDoor);

        return switchedDoor == carDoor;
    }

    private void printStatistics(Map<Integer, GameResult> results) {
        long wins = results.values().stream().filter(GameResult::isWin).count();
        long losses = results.size() - wins;

        System.out.println("Всего игр: " + results.size());
        System.out.println("Побед: " + wins);
        System.out.println("Поражений: " + losses);
    }
}
