package game.characters;

import game.items.GameItem;
import game.core.Inventory;
import game.map.Position;


public class PlayerCharacter extends AbstractCharacter {
    private String name;
    private Inventory inventory = new Inventory();
    private int treasurePoints = 0;

    public PlayerCharacter(String name, Position pos) {
        super(pos);
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name must not be empty");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean addToInventory(GameItem item) {
        return inventory.addItem(item);
    }

    public boolean usePotion() {
        for (Object obj : inventory.getItems()) {
            if (obj instanceof game.items.Potion) {
                if (((game.items.Potion) obj).interact(this)) return true;
            }
        }
        return false;
    }

    public boolean usePowerPotion() {
        for (Object obj : inventory.getItems()) {
            if (obj instanceof game.items.PowerPotion) {
                if (((game.items.PowerPotion) obj).interact(this)) return true;
            }
        }
        return false;
    }

    public boolean updateTreasurePoint(int amount) {
        if (amount < 0) return false;
        treasurePoints += amount;
        return true;
    }

    public int getTreasurePoints() {
        return treasurePoints;
    }

    public boolean addPotion(game.items.Potion p) {
        return addToInventory(p);
    }

    public boolean addPowerPotion(game.items.PowerPotion p) {
        return addToInventory(p);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerCharacter)) return false;
        if (!super.equals(o)) return false;
        PlayerCharacter that = (PlayerCharacter) o;
        return treasurePoints == that.treasurePoints && name.equals(that.name) && inventory.equals(that.inventory);
    }

    @Override
    public String toString() {
        return "PlayerCharacter{" +
                "name='" + name + '\'' +
                ", position=" + getPosition() +
                ", health=" + getHealth() +
                ", power=" + getPower() +
                ", treasurePoints=" + treasurePoints +
                '}';
    }
}
