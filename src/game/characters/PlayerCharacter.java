package game.characters;

import game.map.Position;
import game.core.Inventory;
import game.items.GameItem;
import game.items.Potion;
import game.items.PowerPotion;

/**
 * מחלקת PlayerCharacter מייצגת דמות שחקן במשחק.
 */
public class PlayerCharacter extends AbstractCharacter {
    private String name;
    private Inventory<Potion> potions = new Inventory<>();
    private Inventory<PowerPotion> powerPotions = new Inventory<>();
    private Inventory<GameItem> items = new Inventory<>();

    public PlayerCharacter(String name, Position position) {
        super(position);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean addPotion(Potion p) {
        potions.addItem(p);
        return true;
    }

    public boolean addPowerPotion(PowerPotion p) {
        powerPotions.addItem(p);
        return true;
    }

    public boolean addItem(GameItem i) {
        items.addItem(i);
        return true;
    }

    public boolean usePotion() {
        Potion p = potions.fetchNext();
        if (p != null) {
            p.interact(this);
            return true;
        }
        return false;
    }

    public boolean usePowerPotion() {
        PowerPotion pp = powerPotions.fetchNext();
        if (pp != null) {
            pp.interact(this);
            return true;
        }
        return false;
    }

    public boolean updateTreasurePoint(int amount) {
        // implement method
        return true;
    }
}
