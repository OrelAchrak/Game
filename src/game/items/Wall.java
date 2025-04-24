package game.items;

import game.map.Position;

/**
 * קיר חסם תנועה.
 */
public class Wall extends GameItem {
    public Wall(Position position) {
        super(position, true, "Wall");
    }
    // לא מממשת interact
}