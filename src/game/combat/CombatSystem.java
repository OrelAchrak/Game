package game.combat;

import game.core.GameEntity;
import game.items.Treasure;


public class CombatSystem {

    public Treasure resolveCombat(Attacker attacker, Combatant defender) {
        if (!attacker.canAttack((GameEntity) defender)) {
            return null;
        }

        attacker.performAttack(defender);

        if (defender.isDead()) {
            return defender.dropLoot();
        }

        return null;
    }
}
