import java.security.InvalidParameterException;
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

        try {
            enemy.setHealth(enemyHealth);

        } catch (InvalidHealthException e) {
            System.out.println(enemy.getName() + "Fainted!");
            enemy.setHealth(0);
        }

    }

    // Instantly regenerates mana and boosts spell power.
    public void abilityArcaneSurge() {
        try {
            setManaRegeneration(manaRegeneration + 1);
        } catch (InvalidManaRegenerationException e) {
            setManaRegeneration(100);
        }
    }

    // Rewinds time slightly, restoring health and undoing recent debuffs.
    public void abilityChronoShift() {
        try {
            super.setHealth(super.getHealth() + 1);
        } catch (InvalidHealthException e) {
            super.setHealth(100);
        }
    }

    // Creates illusions to confuse enemies.
    public void abilityMirrorImage(Character enemy) {
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

        try {
            enemy.setHealth(enemyHealth);

        } catch (InvalidHealthException e) {
            System.out.println(enemy.getName() + "Fainted!");
            enemy.setHealth(0);
        }

    }

    public void useAbility(Scanner scanner, Character enemy) {
        System.out.println();
        System.out.print("What do you wan to do? (Choose 1-5): ");
        switch (scanner.nextInt()){
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

}
