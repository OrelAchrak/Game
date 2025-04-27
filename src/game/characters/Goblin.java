package game.characters;

import game.combat.PhysicalAttacker;
import game.combat.MeleeFighter;
import game.map.Position;
import java.util.concurrent.ThreadLocalRandom;
import game.combat.Combatant;

public class Goblin extends Enemy implements PhysicalAttacker, MeleeFighter {
    private int agility;

    public Goblin(Position pos, int agility, int loot) {
        super(pos, ThreadLocalRandom.current().nextInt(1, 51), loot);
        if (agility < 0 || agility > 80) throw new IllegalArgumentException();
        this.agility = agility;
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
    public boolean tryEvade() {
        double gobEvade = Math.min(0.8, agility / 100.0);
        return ThreadLocalRandom.current().nextDouble() < gobEvade;
    }

    @Override
    public boolean isCriticalHit() {
        return ThreadLocalRandom.current().nextDouble() < 0.1;
    }
}