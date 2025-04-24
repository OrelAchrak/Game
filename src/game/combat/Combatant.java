package game.combat;

import game.items.Treasure;

/**
 * ממשק Combatant: ישות שניתן להילחם בה או שיכולה להיפגע/להתרפא.
 * כולל טיפול ב-dropLoot עם ברירת-מחדל.
 */
public interface Combatant {
    int getHealth();
    void setHealth(int health);
    void receiveDamage(int amount, Combatant source);
    void heal(int amount);
    boolean isDead();
    int getPower();
    boolean tryEvade();

    /**
     * אם הישות נפלה, תחזיר Treasure; אחרת null.
     */
    default Treasure dropLoot() {
        return null;
    }
}