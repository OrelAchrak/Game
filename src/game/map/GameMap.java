package game.map;

import game.core.GameEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * מחלקת GameMap מנהלת רשת של ישויות במפה.
 */
public class GameMap {
    private Map<Position, List<GameEntity>> grid;

    public GameMap() {
        grid = new HashMap<>();
    }

    /**
     * מוסיפה ישות למיקום על המפה.
     */
    public void addEntity(Position pos, GameEntity entity) {
        grid.computeIfAbsent(pos, k -> new ArrayList<>()).add(entity);
    }

    /**
     * מסירה ישות ממיקום.
     * @return אמת אם הוסרה, אחרת שקר
     */
    public boolean removeEntity(Position pos, GameEntity entity) {
        List<GameEntity> list = grid.get(pos);
        if (list != null) {
            boolean removed = list.remove(entity);
            if (list.isEmpty()) grid.remove(pos);
            return removed;
        }
        return false;
    }

    /**
     * מחזירה את רשימת הישויות במיקום.
     */
    public List<GameEntity> getEntities(Position pos) {
        return grid.getOrDefault(pos, Collections.emptyList());
    }
}