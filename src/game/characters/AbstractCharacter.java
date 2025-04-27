package game.characters;

import game.combat.Combatant;
import game.core.GameEntity;
import game.map.Position;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Base abstract character implementing combatant and visual entity.
 */
public abstract class AbstractCharacter implements Combatant, GameEntity {
    private Position position;
    private int health = 100;
    private int power = ThreadLocalRandom.current().nextInt(4, 15);
    private double evasionChance = 0.25;
    private boolean visible = true;

    protected AbstractCharacter(Position position) {
        if (position == null) throw new IllegalArgumentException("Position must not be null");
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position newPos) {
        if (newPos != null) this.position = newPos;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public boolean setHealth(int health) {
        if (health < 0 || health > 100) return false;
        this.health = health;
        return true;
    }

    @Override
    public void receiveDamage(int amount, Combatant source) {
        if (tryEvade()) return;
        setHealth(Math.max(0, health - amount));
    }

    @Override
    public void heal(int amount) {
        setHealth(Math.min(100, health + amount));
    }

    @Override
    public boolean isDead() {
        return health <= 0;
    }

    @Override
    public int getPower() {
        return power;
    }

    /**
     * Allows public modification of power (by power potions).
     */
    public boolean setPower(int power) {
        if (power < 0) return false;
        this.power = power;
        return true;
    }

    @Override
    public boolean tryEvade() {
        return ThreadLocalRandom.current().nextDouble() < evasionChance;
    }

    @Override
    public String getDisplaySymbol() {
        return this.getClass().getSimpleName().substring(0, 1);
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Checks visibility state.
     */

    public boolean isVisible() {
        return visible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractCharacter)) return false;
        AbstractCharacter that = (AbstractCharacter) o;
        return health == that.health && power == that.power &&
                Double.compare(that.evasionChance, evasionChance) == 0 &&
                visible == that.visible && position.equals(that.position);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "position=" + position +
                ", health=" + health +
                ", power=" + power +
                ", visible=" + visible +
                '}';
    }
}