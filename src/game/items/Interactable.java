package game.items;

import game.characters.PlayerCharacter;

/**
 * Defines an object in the game that can be interacted with by the player.
 */
public interface Interactable {
    /**
     * Executes interaction logic when a player character interacts with this item.
     *
     * @param c the player character interacting with the item (must not be null)
     * @return true if the interaction was successful, false otherwise
     */
    boolean interact(PlayerCharacter c);
}
