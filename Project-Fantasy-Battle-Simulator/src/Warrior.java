public class Warrior extends Character {

    // Character description
    private static final String description = "Warrior (Frontline Tank & Melee Fighter)";

    // Increases reaction speed to counter enemy moves.
    private int battleInstinct;

    // Increases resistance to status effects (stuns, poisons, debuffs).
    private int titansEndurance;

    // Reduces incoming physical damage
    private int armorMastery;

    // Grants bonus attack power when health drops below a certain threshold
    private int berserkerRage;

    // Allows blocking attacks and reducing damage from critical hits
    private int shieldDefence;


    // Spins in place, hitting all surrounding enemies
    public void abilityBladeCyclone(Object enemy) {}

    // Temporary immunity to stun and knockback effects.
    public void abilityUnbreakableWill() {}

    // Smashes the ground, creating shockwaves that damage and slow enemies.
    public void abilityEarthshatter(Object enemy) {}

    // Wields two massive weapons at once, doubling damage output.
    public void abilityTitansGrip(Object enemy) {}

    // Boosts attack power of nearby allies and intimidates enemies.
    public void abilityWarCry(Object enemy) {}
}
