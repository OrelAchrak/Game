package game.characters;

import game.combat.PhysicalAttacker;
import game.combat.MeleeFighter;
import game.map.Position;
import game.combat.Combatant;
import java.util.concurrent.ThreadLocalRandom;



public class Orc extends Enemy implements PhysicalAttacker, MeleeFighter {
    private double resistance;

    public Orc(Position pos, double resistance, int loot) {
        super(pos, 50, loot);
        if (resistance < 0 || resistance > 0.5)
            throw new IllegalArgumentException();
        this.resistance = resistance;
    }

    public double getResistance() {
        return resistance;
    }

    @Override
    public void attack(Combatant target) {
        fightClose(target);
    }

    @Override
    public void fightClose(Combatant target) {
        target.receiveDamage(getPower(), this);
    }

    @Override
    public boolean isInMeleeRange(Position self, Position other) {
        return self.distanceTo(other) <= 1;
    }

    @Override
    public void receiveDamage(int amount, game.combat.Combatant source) {
        if (source instanceof game.combat.MagicAttacker) {
            amount = (int) Math.round(amount * (1 - resistance));
        }
        super.receiveDamage(amount, source);
    }

    @Override
    public boolean isCriticalHit() {
        return ThreadLocalRandom.current().nextDouble() < 0.1;
    }
}
