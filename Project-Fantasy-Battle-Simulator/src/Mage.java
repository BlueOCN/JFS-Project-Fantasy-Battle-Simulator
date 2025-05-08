import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Scanner;

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

        // Add abilities
        ArrayList<String> abilities = new ArrayList<>();
        abilities.add("Firestorm");
        abilities.add("Arcane Surge");
        abilities.add("Chrono-Shift");
        abilities.add("Mirror Image");
        abilities.add("Void Rift");
        super.setAbilities(abilities);
    }

    public int getMysticWisdom() {
        return mysticWisdom;
    }

    public void setMysticWisdom(int mysticWisdom) {
        this.mysticWisdom = mysticWisdom;
    }

    public int getManaRegeneration() {
        return manaRegeneration;
    }

    public void setManaRegeneration(int manaRegeneration) {
        if (manaRegeneration < 0 || manaRegeneration > 100) {
            throw new InvalidManaRegenerationException("Mana regeneration must be between 0 and 100");
        }
        this.manaRegeneration = manaRegeneration;
    }

    public int getElementalMastery() {
        return elementalMastery;
    }

    public void setElementalMastery(int elementalMastery) {
        this.elementalMastery = elementalMastery;
    }

    public int getArcaneBarrier() {
        return arcaneBarrier;
    }

    public void setArcaneBarrier(int arcaneBarrier) {
        if (arcaneBarrier < 0 || arcaneBarrier > 100) {
            throw new InvalidArcaneBarrierException("Arcane Barrier must be between 0 and 100");
        }
        this.arcaneBarrier = arcaneBarrier;
    }

    public int getSpellChanneling() {
        return spellChanneling;
    }

    public void setSpellChanneling(int spellChanneling) {
        this.spellChanneling = spellChanneling;
    }

    public void attack(Character enemy) {

        int damage, defensePoints, attackPoints, enemyHealth;

        // Verify enemy health is greater than 0
        enemyHealth = enemy.getHealth();
        if(enemyHealth <= 0) {
            throw new InvalidHealthException("Enemy health must be greater than 0");
        }

        // Calculate enemy defense after boosts or deboosts
        defensePoints = enemy.getEndurance() + enemy.getEnduranceBuffs();

        // Calculate attack points after boosts or deboosts
        attackPoints = super.getIntelligence() + mysticWisdom + spellChanneling;

        // Calculate resulting damage
        damage = attackPoints - defensePoints;
        if (damage < 0) {
            damage = 0;
        }

        // Calculate enemy health after attack
        enemyHealth = enemyHealth - damage;

        try {
            enemy.setHealth(enemyHealth);
        } catch (InvalidHealthException e) {
            System.out.println(e.getMessage());
            System.out.println(enemy.getName() + "Fainted!");
            enemy.setHealth(0);
        }

        System.out.println("\nHands crackling with energy, the mage utters an incantation. \n" +
                "Fire erupts from their fingertips, spiraling into a blazing inferno \n" +
                "that engulfs their foe in searing heat, leaving only smoldering embers.");
    }

    public void defend(){
        try {
            setArcaneBarrier(arcaneBarrier + 1);
        } catch (InvalidArcaneBarrierException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Sensing danger, He summons a shimmering barrier, ancient runes pulsing in the air. His pulse quickens. The shield is up, but the idea sends a chilling realization—He may not withstand the next blow.");
    }

    // Calls down a rain of fire, dealing area damage.
    public void abilityFirestorm(Character enemy){

        int damage, defensePoints, attackPoints, enemyHealth;

        // Verify enemy health is greater than 0
        enemyHealth = enemy.getHealth();
        if(enemyHealth <= 0) {
            throw new InvalidHealthException("Enemy health must be greater than 0");
        }

        // Calculate enemy defense after boosts or deboosts
        defensePoints = enemy.getEndurance() + enemy.getEnduranceBuffs();

        // Calculate attack points after boosts or deboosts
        attackPoints = super.getIntelligence() + mysticWisdom + elementalMastery + spellChanneling;

        // Calculate resulting damage
        damage = attackPoints - defensePoints;
        if (damage < 0) {
            damage = 0;
        }

        // Calculate enemy health after attack
        enemyHealth = enemyHealth - damage;

        System.out.println("\nThe mage raises their staff, flames flickering at their fingertips. Firestorm ignites—swirling embers fuse into a towering inferno. With a sharp incantation, they unleash the blaze, fire roaring outward in a relentless surge. Heat distorts the air as the spell engulfs the battlefield, consuming everything in its raging path.");

        try {
            enemy.setHealth(enemyHealth);

        } catch (InvalidHealthException e) {
            System.out.println(enemy.getName() + "Fainted!");
            enemy.setHealth(0);
        }

    }

    // Instantly regenerates mana and boosts spell power.
    public void abilityArcaneSurge() {
        System.out.println("\nThe mage steadies their stance, hands crackling with raw energy. ArcaneSurge ignites—a surge of mystical force courses through their veins, amplifying their power beyond mortal limits. Runes blaze in the air as magic floods the battlefield. With unwavering focus, they brace for the incoming strike, their aura pulsing with unstoppable arcane might.");
        try {
            setManaRegeneration(manaRegeneration + 1);
        } catch (InvalidManaRegenerationException e) {
            setManaRegeneration(100);
        }
    }

    // Rewinds time slightly, restoring health and undoing recent debuffs.
    public void abilityChronoShift() {
        System.out.println("\nThe mage’s pulse steadies, fingers tracing unseen patterns in the air. ChronoShift awakens—time bends, warping in shimmering waves. Their movements blur, skipping between moments, the world slowing around them. Eyes flash with knowing precision as they reposition, slipping through fractured seconds, ready for the strike that has yet to land.");
        try {
            super.setHealth(super.getHealth() + 1);
        } catch (InvalidHealthException e) {
            super.setHealth(100);
        }
    }

    // Creates illusions to confuse enemies.
    public void abilityMirrorImage(Character enemy) {
        System.out.println("\nThe mage’s fingers weave through the air, ancient words whispered under their breath. MirrorImage flickers to life—a shimmering distortion bends reality around them. Illusions manifest, perfect replicas shifting unpredictably. Their true form vanishes among the spectral echoes, unseen, untouchable. As the spell settles, only deception remains, ready to confound.");
        try {
            enemy.setAgility(enemy.getAgility() - 1);
        } catch (InvalidAgilityTooLowException e) {
            enemy.setAgility(0);
        }
    }

    // Opens a dimensional tear, pulling enemies into the abyss for heavy damage.
    public void abilityVoidRift(Character enemy) {

        int damage, defensePoints, attackPoints, enemyHealth;

        // Verify enemy health is greater than 0
        enemyHealth = enemy.getHealth();
        if(enemyHealth <= 0) {
            throw new InvalidHealthException("Enemy health must be greater than 0");
        }

        // Calculate enemy defense after boosts or deboosts
        defensePoints = enemy.getEndurance() + enemy.getEnduranceBuffs();

        // Calculate attack points after boosts or deboosts
        attackPoints = super.getIntelligence() + mysticWisdom + spellChanneling + elementalMastery;

        // Calculate resulting damage
        damage = attackPoints - defensePoints;
        if (damage < 0) {
            damage = 0;
        }

        // Calculate enemy health after attack
        enemyHealth = enemyHealth - damage;

        System.out.println("\nThe mage’s hands weave through the air, ancient sigils glowing at their fingertips. VoidRift pulses—a tear in reality forms, swirling with darkness. Energy spirals outward, devouring light and sound in its wake. With a final command, the mage unleashes its force, the abyss surging forward to consume all caught in its path.");

        try {
            enemy.setHealth(enemyHealth);

        } catch (InvalidHealthException e) {
            System.out.println(enemy.getName() + "Fainted!");
            enemy.setHealth(0);
        }

    }

    public void useAbility(int choice, Character enemy) {

        switch (choice){
            case 1:
                abilityFirestorm(enemy);
                break;
            case 2:
                abilityArcaneSurge();
                break;
            case 3:
                abilityChronoShift();
                break;
            case 4:
                abilityMirrorImage(enemy);
                break;
            case 5:
                abilityVoidRift(enemy);
                break;
        }

    }

    public int getEnduranceBuffs(){
        return this.arcaneBarrier;
    }

    @Override
    public String toString() {
        return "Mage{" +
                "mysticWisdom=" + mysticWisdom +
                ", manaRegeneration=" + manaRegeneration +
                ", elementalMastery=" + elementalMastery +
                ", arcaneBarrier=" + arcaneBarrier +
                ", spellChanneling=" + spellChanneling +
                "} " + super.toString();
    }
}
