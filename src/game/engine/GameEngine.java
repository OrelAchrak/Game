package game.engine;

import game.map.GameMap;
import game.map.Position;
import game.characters.PlayerCharacter;
import game.combat.CombatSystem;

import java.util.Scanner;

/**
 * לולאת המשחק הראשית עם מנגנון אירועים תואם ל-API הקיים
 */
public class GameEngine {
    private GameMap map;
    private PlayerCharacter player;
    private CombatSystem combat;
    private EventManager eventManager;
    private boolean running;
    private final Scanner scanner;

    public GameEngine() {
        this.scanner = new Scanner(System.in);
        this.eventManager = new EventManager();
    }

    /**
     * אתחול המשחק: מפה, שחקן ומערכת קרב
     */
    public void init() {
        // טען את המפה
        map = new GameMap();

        // קבע מיקום התחלתי לדמות השחקן והוסף למפה
        Position startPos = new Position(0, 0);
        player = new PlayerCharacter("Hero", startPos);
        map.addEntity(startPos, player);

        // אתחול מערכת קרב
        combat = new CombatSystem();

        // רישום מאזינים לאירועים
        eventManager.register(MoveEvent.class, event -> {
            Position current = event.getEntity().getPosition();
            Position newPos;
            switch (event.getDirection()) {
                case UP:    newPos = new Position(current.getRow() - 1, current.getCol()); break;
                case DOWN:  newPos = new Position(current.getRow() + 1, current.getCol()); break;
                case LEFT:  newPos = new Position(current.getRow(), current.getCol() - 1); break;
                case RIGHT: newPos = new Position(current.getRow(), current.getCol() + 1); break;
                default:    newPos = current;
            }
            // העברת הישויות במפה
            map.removeEntity(current, event.getEntity());
            map.addEntity(newPos, event.getEntity());
            event.getEntity().setPosition(newPos);
        });

        eventManager.register(AttackEvent.class, event -> {
            // דוגמה לקריאה למערכת הקרב
            System.out.println("Attack initiated by " + event.getEntity().getDisplaySymbol());
        });
    }

    /**
     * update: קלט מהמשתמש והפצת האירועים
     */
    public void update() {
        if (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            fireInputEvent(input);
        }
        // אין קריאה ל-update במפה או בקרב לפי ה-API
    }

    /**
     * render: הצגה בלבד
     */
    public void render() {
        Position pos = player.getPosition();
        System.out.println("Player at: (" + pos.getRow() + ", " + pos.getCol() + ")");
        System.out.println("Health: " + player.getHealth());
    }

    /**
     * המרת פקודות קלט לאירועים
     */
    private void fireInputEvent(String input) {
        switch (input.toLowerCase()) {
            case "w":      eventManager.fire(new MoveEvent(player, MoveEvent.Direction.UP)); break;
            case "s":      eventManager.fire(new MoveEvent(player, MoveEvent.Direction.DOWN)); break;
            case "a":      eventManager.fire(new MoveEvent(player, MoveEvent.Direction.LEFT)); break;
            case "d":      eventManager.fire(new MoveEvent(player, MoveEvent.Direction.RIGHT)); break;
            case "attack": eventManager.fire(new AttackEvent(player)); break;
            case "quit":   running = false; break;
            default:        System.out.println("Unknown command: " + input);
        }
    }

    /**
     * התחל את המשחק
     */
    public void run() {
        init();
        running = true;
        while (running) {
            update();
            render();
        }
        scanner.close();
        System.out.println("Game Over!");
    }

    public static void main(String[] args) {
        new GameEngine().run();
    }
}
