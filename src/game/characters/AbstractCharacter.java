package game.characters;

import game.map.Position;
import game.core.GameEntity;
import game.combat.Combatant;
import java.util.Random;

/**
 * מחלקת AbstractCharacter מייצגת דמות בסיסית במשחק.
 */
public abstract class AbstractCharacter implements GameEntity, Combatant {
    private Position position;
    private int health;
    private int power;
    private double evasionChance;
    private Random rand;

    public AbstractCharacter(Position position) {
        this.position = position;
        this.health = 100;
        this.power = new Random().nextInt(11) + 4;
        this.evasionChance = 0.25;
        this.rand = new Random();
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position newPos) {
        this.position = newPos;
    }

    @Override
    public String getDisplaySymbol() {
        return "C";
    }

    @Override
    public void setVisible(boolean visible) {}

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = Math.max(0, Math.min(health, 100));
    }

    @Override
    public boolean tryEvade() {
        return rand.nextDouble() < evasionChance;
    }

    @Override
    public void receiveDamage(int amount, Combatant source) {
        if (!tryEvade()) {
            this.health = Math.max(0, this.health - amount);
        }
    }

    @Override
    public void heal(int amount) {
        this.health = Math.min(100, this.health + amount);
    }

    @Override
    public boolean isDead() {
        return health <= 0;
    }

    @Override
    public int getPower() {
        return power;
    }

    public void setPower(int newPower) {
        this.power = newPower;
    }
}