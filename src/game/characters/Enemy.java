package game.characters;

import game.items.Treasure;
import game.map.Position;

public abstract class Enemy extends AbstractCharacter {
    private int loot;

    protected Enemy(Position pos, int health, int loot) {
        super(pos);
        setHealth(health);
        this.loot = loot;
    }

    public int getLoot() {
        return loot;
    }

    public void defeat() {
        new Treasure(getPosition(), loot).interact(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Enemy)) return false;
        if (!super.equals(o)) return false;
        Enemy that = (Enemy) o;
        return loot == that.loot;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "position=" + getPosition() +
                ", health=" + getHealth() +
                ", power=" + getPower() +
                ", loot=" + loot +
                '}';
    }
}