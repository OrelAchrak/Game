package game.engine;

import game.characters.PlayerCharacter;
import game.map.GameMap;
import game.combat.CombatSystem;
import java.util.List;

/**
 * Manages the main game loop: initialization, player turns, and combat resolution.
 */
public class GameEngine {
    private final GameWorld world;
    private final CombatSystem combatSystem;
    private boolean isRunning = false;

    /**
     * Constructs a GameEngine with provided world and combat system.
     * @param world the game world (must not be null)
     * @param combatSystem the combat resolver (must not be null)
     */
    public GameEngine(GameWorld world, CombatSystem combatSystem) {
        if (world == null || combatSystem == null) {
            throw new IllegalArgumentException("World and CombatSystem must not be null");
        }
        this.world = world;
        this.combatSystem = combatSystem;
    }

    /**
     * Starts the game loop. Initializes any required setup before entering turns.
     */
    public void start() {
        isRunning = true;
        while (isRunning) {
            runTurn();
        }
    }

    /**
     * Executes one full round of turns for all players.
     */
    private void runTurn() {
        List<PlayerCharacter> players = world.getPlayers();
        for (PlayerCharacter player : players) {
            if (player.isDead()) {
                continue;
            }
            // TODO: prompt and process movement or action for player
            // e.g., processMovement(player);
        }
        // TODO: check end-of-game conditions and possibly set isRunning=false
    }

    /**
     * Stops the game loop.
     */
    public void stop() {
        isRunning = false;
    }
}
