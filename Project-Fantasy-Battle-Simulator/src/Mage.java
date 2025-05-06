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
