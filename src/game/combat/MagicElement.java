package game.combat;

/**
 * אלמנטים של קסם והגדרת יתרון אלמנטלי.
 */
public enum MagicElement {
    FIRE, ICE, LIGHTNING, ACID;

    public boolean isStrongerThan(MagicElement other) {
        switch (this) {
            case FIRE: return other == ICE;
            case ICE: return other == LIGHTNING;
            case LIGHTNING: return other == ACID;
            case ACID: return other == FIRE;
            default: return false;
        }
    }
}