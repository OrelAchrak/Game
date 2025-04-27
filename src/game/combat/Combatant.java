package game.combat;

import game.core.GameEntity;
import game.map.Position;

/**
 * Any entity that can participate in combat and has a position/display.
 */
public interface Combatant extends GameEntity {
    int getHealth();
    boolean setHealth(int health);
    void receiveDamage(int amount, Combatant source);
    void heal(int amount);
    boolean isDead();
    int getPower();
    boolean tryEvade();
}