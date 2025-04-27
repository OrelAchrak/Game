package game.engine;

import game.characters.*;
import game.combat.CombatSystem;
import game.items.*;
import game.map.GameMap;
import game.map.Position;
import game.combat.MagicElement;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * GameRunner: entry point, builds map, initializes world, and runs the game loop.
 */
public class GameRunner {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CombatSystem combatSystem = new CombatSystem();

    public static void main(String[] args) {
        System.out.println("Welcome to the Fantasy Dungeon!");
        int rows = promptInt("Enter board rows (>=10): ", 10);
        int cols = promptInt("Enter board columns (>=10): ", 10);

        // choose character
        System.out.println("Choose your class: 1) Warrior  2) Mage  3) Archer");
        int choice = promptInt("Enter choice (1-3): ", 1, 3);
        System.out.print("Enter your character name: ");
        String name = scanner.nextLine().trim();

        // random start position
        Position start;
        GameMap map = new GameMap();
        PlayerCharacter player;
        do {
            start = new Position(ThreadLocalRandom.current().nextInt(rows), ThreadLocalRandom.current().nextInt(cols));
        } while (false); // no collisions yet
        switch (choice) {
            case 1: player = new Warrior(name, start, ThreadLocalRandom.current().nextInt(0,121)); break;
            case 2: player = new Mage(name, start, MagicElement.values()[ThreadLocalRandom.current().nextInt(4)]); break;
            default: player = new Archer(name, start, ThreadLocalRandom.current().nextDouble(0,0.81));
        }
        map.addEntity(start, player);

        // generate other cells
        for (int r = 0; r < rows; r++) for (int c = 0; c < cols; c++) {
            Position pos = new Position(r, c);
            if (pos.equals(start)) continue;
            double p = ThreadLocalRandom.current().nextDouble();
            if (p < 0.4) continue;
            else if (p < 0.7) {
                // enemy: equal chance Goblin, Orc, Dragon
                int t = ThreadLocalRandom.current().nextInt(3);
                Enemy e;
                if (t == 0) e = new Goblin(pos, ThreadLocalRandom.current().nextInt(0,81), ThreadLocalRandom.current().nextInt(50,151));
                else if (t == 1){
                    double resistance = ThreadLocalRandom.current().nextDouble(0, 0.51);
                    resistance = Math.min(resistance, 0.5);
                    e = new Orc(pos, ThreadLocalRandom.current().nextDouble(0,0.51), ThreadLocalRandom.current().nextInt(50,151));
                }
                else e = new Dragon(pos, MagicElement.values()[ThreadLocalRandom.current().nextInt(4)], ThreadLocalRandom.current().nextInt(100,201));
                map.addEntity(pos, e);
            } else if (p < 0.8) {
                map.addEntity(pos, new Wall(pos));
            } else {
                // potion: 15% health, 5% power
                if (ThreadLocalRandom.current().nextDouble() < 0.75) map.addEntity(pos, new Potion(pos));
                else map.addEntity(pos, new PowerPotion(pos));
            }
        }

        GameWorld world = new GameWorld(map);
        world.addPlayer(player);
        // also register all enemies/items for removal logic
        // skip explicit lists here

        GameEngine engine = new GameEngine(world, combatSystem, rows, cols);
        engine.start();
    }

    private static int promptInt(String msg, int min) {
        return promptInt(msg, min, Integer.MAX_VALUE);
    }
    private static int promptInt(String msg, int min, int max) {
        int v;
        while (true) {
            System.out.print(msg);
            try { v = Integer.parseInt(scanner.nextLine()); }
            catch (Exception ex) { continue; }
            if (v >= min && v <= max) return v;
        }
    }
}
