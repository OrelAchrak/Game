package game.characters;
import game.map.Position;
import java.util.concurrent.ThreadLocalRandom;

public class Warrior extends PlayerCharacter implements game.combat.PhysicalAttacker, game.combat.MeleeFighter {
    private int defence;

    public Warrior(String name, Position pos, int defence) {
        super(name, pos);
        if (defence < 0 || defence > 120) throw new IllegalArgumentException();
        this.defence = defence;
    }

    @Override
    public void attack(game.combat.Combatant target) {
        fightClose(target);
    }

    @Override
    public void fightClose(game.combat.Combatant target) {
        target.receiveDamage(calculateDamage(), this);
    }

    private int calculateDamage() {
        return getPower();
    }

    @Override
    public boolean isCriticalHit() {
        return ThreadLocalRandom.current().nextDouble() < 0.1;
    }

    @Override
    public boolean isInMeleeRange(Position self, Position other) {
        return self.distanceTo(other) <= 1;
    }

    @Override
    public void receiveDamage(int amount, game.combat.Combatant source) {
        double reduction = Math.min(0.6, defence / 200.0);
        super.receiveDamage((int)Math.round(amount * (1 - reduction)), source);
    }
}
