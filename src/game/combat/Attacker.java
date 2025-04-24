package game.combat;

import game.core.GameEntity;

/**
 * ממשק Attacker: מבוסס Combatant, מאפשר בדיקת יכולת התקפה וביצועה.
 */
public interface Attacker extends Combatant {
    /**
     * בודק האם התוקף יכול לתקוף את הישות הנתונה (לפי טווח, אלמנט, וכו').
     */
    boolean canAttack(GameEntity target);

    /**
     * מבצע את פעולת ההתקפה עצמה (כולל חישובי נזק).
     */
    void performAttack(Combatant target);
}