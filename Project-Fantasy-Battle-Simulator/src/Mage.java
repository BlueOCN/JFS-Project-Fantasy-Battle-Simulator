import java.security.InvalidParameterException;

public class Mage extends Character {

    // Character description
    private static final String description = "Mage (Spellcaster & Arcane Specialist)";

    // Improves magical crit rate and effectiveness of buffs/debuffs.
    private int mysticWisdom;

    // Speeds up magic recovery over time
    private int manaRegeneration;

    // Enhances damage from fire, ice, lightning, or other magical elements
    private int elementalMastery;

    // Provides magical shielding from attacks
    private int arcaneBarrier;

    // Increases the efficiency and potency of spells
    private int spellChanneling;

    Mage(String name, int level) {
        super();
        super.setName(name);
        super.setLevel(level);
        super.setDescription(description);
        this.mysticWisdom = level;
        this.manaRegeneration = level;
        this.elementalMastery = level;
        this.arcaneBarrier = level;
        this.spellChanneling = level;
    }

    public void attack(Character enemy) {
        // Enemy Health = Enemy Health - Warrior attack
        if(enemy.getHealth() <= 0) {
            throw new InvalidParameterException("Enemy health must be greater than 0");
        }

        if(super.getHealth() < 3) {
            enemy.setHealth(enemy.getHealth() - super.getStrength() + mysticWisdom + elementalMastery);
        } else {
            enemy.setHealth(enemy.getHealth() - super.getStrength());
        }
        System.out.println("\nHands crackling with energy, the mage utters an incantation. \n" +
                "Fire erupts from their fingertips, spiraling into a blazing inferno \n" +
                "that engulfs their foe in searing heat, leaving only smoldering embers.");
    }

    // Calls down a rain of fire, dealing area damage.
    public void abilityFirestorm(Object enemy){}

    // Instantly regenerates mana and boosts spell power.
    public void abilityArcaneSurge() {}

    // Rewinds time slightly, restoring health and undoing recent debuffs.
    public void abilityChronoShift() {}

    // Creates illusions to confuse enemies.
    public void abilityMirrorImage(Object enemy) {}

    // Opens a dimensional tear, pulling enemies into the abyss for heavy damage.
    public void abilityVoidRift(Object enemy) {}

}
