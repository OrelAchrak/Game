package game.engine;

import game.characters.*;
import game.combat.CombatSystem;
import game.items.GameItem;
import game.items.Interactable;
import game.map.GameMap;
import game.map.Position;

import java.util.List;
import java.util.Scanner;

/**
 * Manages the main game loop: initialization, player turns, and combat resolution.
 */
public class GameEngine {
    private final GameWorld world;
    private final CombatSystem combatSystem;
    private final Scanner scanner = new Scanner(System.in);
    private boolean isRunning = false;
    private final int rows, cols;


    public GameEngine(GameWorld world,
                      CombatSystem combatSystem,
                      int rows, int cols) {
        if (world == null || combatSystem == null) {
            throw new IllegalArgumentException("World and CombatSystem must not be null");
        }
        if (rows < 1 || cols < 1) {
            throw new IllegalArgumentException("Board dimensions must be positive");
        }
        this.world = world;
        this.combatSystem = combatSystem;
        this.rows = rows;
        this.cols = cols;
    }


    /**
     * Starts the game loop.
     */
    public void start() {
        isRunning = true;
        while (isRunning) {
            runTurn();
        }
        System.out.println("Game over. Thanks for playing!");
    }

    /**
     * Executes one player turn: display map, prompt movement, handle interaction/combat.
     */
    private void runTurn() {
        PlayerCharacter player = world.getPlayers().get(0);
        if (player.isDead()) {
            isRunning = false;
            System.out.println("You have perished...");
            return;
        }

        displayMap();
        System.out.print("Move (W/A/S/D): ");
        String input = scanner.nextLine().trim().toUpperCase();
        Position current = player.getPosition();
        Position target = null;
        switch (input) {
            case "W": target = new Position(current.getRow()-1, current.getCol()); break;
            case "S": target = new Position(current.getRow()+1, current.getCol()); break;
            case "A": target = new Position(current.getRow(), current.getCol()-1); break;
            case "D": target = new Position(current.getRow(), current.getCol()+1); break;
            default:
                System.out.println("Invalid input.");
                return;
        }

        // check map bounds
        try {
            // check map bounds
            if (target.getRow() < 0 || target.getRow() >= rows ||
                    target.getCol() < 0 || target.getCol() >= cols) {
                System.out.println("Can't move outside the board.");
                return;
            }

        } catch (Exception e) {
            System.out.println("Can't move outside the board.");
            return;
        }

        List<GameItem> items = world.getMap().getEntities(target).stream()
                .filter(e -> e instanceof GameItem)
                .map(e -> (GameItem)e)
                .toList();
        List<?> entities = world.getMap().getEntities(target);

        // if enemy
        for (Object obj : entities) {
            if (obj instanceof game.characters.Enemy) {
                Enemy enemy = (Enemy)obj;
                combatSystem.resolveCombat(player, enemy);
                if (enemy.isDead()) {
                    world.getMap().removeEntity(target, enemy);
                    System.out.println("Enemy defeated!");
                }
                if (player.isDead()) {
                    isRunning = false;
                    System.out.println("You were slain...");
                }
                return;
            }
        }

        // if item
        for (Object obj : entities) {
            if (obj instanceof Interactable interactable
                    && obj instanceof game.items.GameItem gi) {
                // רק פריטים גלויים
                if (!gi.isVisible()) continue;
                if (interactable.interact(player)) {
                    world.getMap().removeEntity(target, (game.core.GameEntity)gi);
                    System.out.println("Interacted with " + gi.getClass().getSimpleName());
                    return;
                }
            }
        }

        // if wall or still blocked
        if (!world.getMap().getEntities(target).isEmpty()) {
            System.out.println("Blocked!");
            return;
        }

        // move player
        world.getMap().removeEntity(current, player);
        player.setPosition(target);
        world.getMap().addEntity(target, player);

        // update visibility for all
        for (Object obj : world.getItems()) {
            if (obj instanceof game.core.GameEntity) {
                game.core.GameEntity ge = (game.core.GameEntity)obj;
                boolean visible = ge.getPosition().distanceTo(player.getPosition()) >= 2;
                ge.setVisible(visible);
            }
        }
        for (Enemy e : world.getEnemies()) {
            boolean visible = e.getPosition().distanceTo(player.getPosition()) >= 2;
            e.setVisible(visible);
        }
    }

    /**
     * Displays the map grid with visible entities.
     */
    private void displayMap() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Position pos = new Position(r, c);
                List<?> list = world.getMap().getEntities(pos);
                if (list.isEmpty()) {
                    System.out.print(".");
                } else {
                    Object top = list.get(0);
                    if (top instanceof game.core.GameEntity ge && ge.isVisible()) {
                        System.out.print(ge.getDisplaySymbol());
                    } else {
                        System.out.print("#");
                    }
                }
            }
            System.out.println();
        }
    }
}
