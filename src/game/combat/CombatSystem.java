package game.combat;

import game.map.Position;
import game.core.GameEntity;
import game.characters.Enemy;
import game.characters.PlayerCharacter;

/**
 * Resolves combat between two Combatant entities.
 */
public class CombatSystem {
    /**
     * Resolves an attacker's action against a defender.
     */
    public void resolveCombat(Combatant attacker, Combatant defender) {
        if (attacker == null || defender == null || defender.isDead() || attacker.isDead()) {
            return;
        }
        // determine positions
        Position attackerPos = ((GameEntity) attacker).getPosition();
        Position defenderPos = ((GameEntity) defender).getPosition();
        // range check
        if (attacker instanceof MeleeFighter) {
            MeleeFighter mf = (MeleeFighter) attacker;
            if (!mf.isInMeleeRange(attackerPos, defenderPos)) {
                return;
            }
        } else if (attacker instanceof RangedFighter) {
            RangedFighter rf = (RangedFighter) attacker;
            if (!rf.isInRange(attackerPos, defenderPos)) {
                return;
            }
        }
        // evade check
        if (defender.tryEvade()) {
            return;
        }
        // damage calculation
        int damage;
        if (attacker instanceof MagicAttacker) {
            MagicAttacker ma = (MagicAttacker) attacker;
            double base = attacker.getPower() * 1.5;
            if (ma.isElementStrongerThan((MagicAttacker) defender)) {
                base *= 1.2;
            } else if (defender instanceof MagicAttacker) {
                MagicAttacker md = (MagicAttacker) defender;
                if (md.isElementStrongerThan(ma)) {
                    base *= 0.8;
                }
            }
            // orc resistance
            if (defender instanceof Enemy && defender.getClass().getSimpleName().equals("Orc")) {
                double resistance = ((game.characters.Orc) defender).getResistance();
                base *= (1 - resistance);
            }
            damage = (int) Math.round(base);
        } else {
            // physical attacker
            PhysicalAttacker pa = (PhysicalAttacker) attacker;
            damage = attacker.getPower();
            if (pa.isCriticalHit()) {
                damage *= 2;
            }
        }
        defender.receiveDamage(damage, attacker);
        // post-attack handling
        if (defender.isDead()) {
            if (defender instanceof Enemy) {
                ((Enemy) defender).defeat();
            } else if (defender instanceof PlayerCharacter) {
                // TODO: game over handling
            }
        }
    }
}
