package game.items;

import game.characters.PlayerCharacter;

/**
 * ממשק Interactable: אובייקט שניתן לבצע איתו פעולה.
 */
public interface Interactable {
    void interact(PlayerCharacter c);
}
