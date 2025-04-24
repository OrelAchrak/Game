package game.characters;

import game.items.Treasure;
import game.map.Position;

public abstract class Enemy extends AbstractCharacter {
    private int loot;

    public Enemy(Position position, int loot) {
        super(position);
        this.loot = loot;
    }

    public int getLoot() { return loot; }
    @Override
    public Treasure dropLoot() {
        return new Treasure(getPosition());
    }
}
