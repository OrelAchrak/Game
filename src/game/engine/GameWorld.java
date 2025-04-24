package game.engine;

import game.map.GameMap;
import game.map.Position;
import game.characters.PlayerCharacter;
import game.characters.Enemy;
import game.characters.Goblin;
import game.characters.Orc;
import game.characters.Dragon;
import game.items.GameItem;
import game.items.Potion;
import game.items.PowerPotion;
import game.items.Wall;
import game.combat.MagicElement;

import java.util.*;

public class GameWorld {
    private int rows;
    private int cols;
    private GameMap map;
    private List<PlayerCharacter> players;
    private List<Enemy> enemies;
    private List<GameItem> items;

    public GameWorld(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.map = new GameMap();
        this.players = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.items = new ArrayList<>();
        initRandomWorld();
    }

    private void initRandomWorld() {
        Random rnd = new Random();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Position pos = new Position(r, c);
                double roll = rnd.nextDouble();
                if (roll < 0.4) {
                    // empty
                } else if (roll < 0.7) {
                    spawnRandomEnemy(pos, rnd);
                } else if (roll < 0.8) {
                    spawnWall(pos);
                } else {
                    spawnRandomPotion(pos, rnd);
                }
            }
        }
    }

    private void spawnRandomEnemy(Position pos, Random rnd) {
        int type = rnd.nextInt(3);
        Enemy e;
        if (type == 0) {
            e = new Goblin(pos, rnd.nextInt(81), rnd.nextInt(101) + 50);
        } else if (type == 1) {
            e = new Orc(pos, rnd.nextDouble() * 0.5, rnd.nextInt(101) + 50);
        } else {
            MagicElement elem = MagicElement.values()[rnd.nextInt(MagicElement.values().length)];
            e = new Dragon(pos, elem, rnd.nextInt(101) + 50);
        }
        enemies.add(e);
        map.addEntity(pos, e);
    }

    private void spawnWall(Position pos) {
        Wall wall = new Wall(pos);
        items.add(wall);
        map.addEntity(pos, wall);
    }

    private void spawnRandomPotion(Position pos, Random rnd) {
        if (rnd.nextDouble() < 0.75) {
            Potion p = new Potion(pos);
            items.add(p);
            map.addEntity(pos, p);
        } else {
            PowerPotion pp = new PowerPotion(pos);
            items.add(pp);
            map.addEntity(pos, pp);
        }
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public GameMap getMap() { return map; }
    public List<PlayerCharacter> getPlayers() { return players; }
    public List<Enemy> getEnemies() { return enemies; }
    public List<GameItem> getItems() { return items; }
}
