package game.characters;

import game.combat.MagicElement;
import game.map.Position;

/**
 * Dragon עם אלמנט קסום.
 */
public class Dragon extends Enemy {
    private MagicElement element;

    public Dragon(Position position, MagicElement element, int loot) {
        super(position, loot);
        this.element = element;
    }

    public MagicElement getElement() {
        return element;
    }
}
