import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Scanner;

public class Archer extends Character{

    // Character description
    private static final String description = "Archer (Ranged Combat & Precision Strikes)";

    // Boosts precision for long-range shots
    private int accuracy;

    // Enhances critical hit chance and bow handling
    private int focus;

    // Reduces visibility to enemies, making ambushes more effective
    private int stealth;

    // Grants the ability to fire multiple arrows at once
    private int multiShot;

    // Enhances dodge rate against incoming attacks
    private int evasion;

    Archer(String name, int level) {
        super();
        super.setName(name);
        super.setLevel(level);
        super.setDescription(description);
        this.accuracy = level;
        this.focus = level;
        this.stealth = level;
        this.multiShot = level;
        this.evasion = level;

        // Add abilities
        ArrayList<String> abilities = new ArrayList<>();
        abilities.add("Piercing Shot");
        abilities.add("Shadow Step");
        abilities.add("Explosive Volley");
        abilities.add("Hawk Eye");
        abilities.add("Venom Shot");
        super.setAbilities(abilities);
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        if (accuracy < 0 || accuracy > 100) {
            throw new InvalidAccuracyException("Accuracy must be between 0 and 100");
        }
        this.accuracy = accuracy;
    }

    public int getFocus() {
        return focus;
    }

    public void setFocus(int focus) {
        if (focus < 0 || focus > 100) {
            throw new InvalidFocusException("Focus must be between 0 and 100");
        }
        this.focus = focus;
    }

    public int getStealth() {
        return stealth;
    }

    public void setStealth(int stealth) {
        this.stealth = stealth;
    }

    public int getMultiShot() {
        return multiShot;
    }

    public void setMultiShot(int multiShot) {
        this.multiShot = multiShot;
    }

    public int getEvasion() {
        return evasion;
    }

    public void setEvasion(int evasion) {
        if (evasion < 0){
            throw new InvalidEvasionTooLowException("Evasion must be greater or equal to 0");
        } else if (evasion > 100) {
            throw new InvalidEvasionTooHighException("Evasion mus tbe smaller or equal to 100");
        }
        this.evasion = evasion;
    }

    public void attack(Character enemy) {

        int damage, defensePoints, attackPoints, enemyHealth;

        // Verify enemy health is greater than 0
        enemyHealth = enemy.getHealth();
        if(enemyHealth <= 0) {
            throw new InvalidParameterException("Enemy health must be greater than 0");
        }

        // Calculate enemy defense after boosts or deboosts
        defensePoints = enemy.getEndurance() + enemy.getEnduranceBuffs();

        // Calculate attack points after boosts or deboosts
        attackPoints = super.getStrength() + focus + accuracy;

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

        System.out.println("\nFrom the shadows, the archer draws, holding their breath.\n" +
                "A single arrow whizzes through the air, striking true—piercing armor\n" +
                "and finding its mark with deadly precision.");
    }

    public void defend(){
        try {
            super.setEndurance(getEndurance() + 1);
        } catch (InvalidEnduranceTooHighException e) {
            System.out.println(e.getMessage());
        }
    }

    // Fires an arrow that ignores armor and deals bonus damage.
    public void abilityPiercingShot(Character enemy) {
        int bonusDamage = 1;
        int damage = super.getStrength() + bonusDamage;

        try {
            enemy.setHealth(enemy.getHealth() - damage);
        } catch (InvalidHealthException e) {
            enemy.setHealth(0);
            System.out.println(enemy.getName() + " fainted!");
        }
    }

    // Teleports a short distance, evading attacks.
    public void abilityShadowStep() {
        try {
            setEvasion(getEvasion() + 1);
        } catch (InvalidEvasionTooHighException e) {
            setEvasion(100);
            System.out.println(e.getMessage());
        }
    }

    // Shoots a barrage of arrows that detonate on impact.
    public void abilityExplosiveVolley(Character enemy) {

        int damage, defensePoints, attackPoints, enemyHealth;


        // Verify enemy health is greater than 0
        enemyHealth = enemy.getHealth();
        if(enemyHealth <= 0) {
            throw new InvalidHealthException("Enemy health must be greater than 0");
        }

        // Calculate enemy defense after boosts or deboosts
        defensePoints = enemy.getEndurance() + enemy.getEnduranceBuffs();

        // Calculate attack points after boosts or deboosts
        int barrageSize = (int) (Math.random() * 4) + 1;
        attackPoints = super.getStrength() + focus + accuracy + barrageSize;

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

    // Temporarily increases accuracy and critical hit chance.
    public void abilityHawkEye() {
        try {
            setAccuracy(getAccuracy() + 1);
            setFocus(getFocus() + 1);
        } catch (InvalidAccuracyException e) {
            System.out.println(e.getMessage());
            setAccuracy(100);
        } catch (InvalidFocusException e) {
            System.out.println(e.getMessage());
            setFocus(100);
        }
    }

    // Poisons enemies, dealing damage over time.
    public void abilityVenomShot(Character enemy) {
        int damage = super.getStrength() + 1;
        try {
            enemy.setHealth(enemy.getHealth() - damage);
        } catch (InvalidHealthException e) {
            enemy.setHealth(0);
            System.out.println(enemy.getName() + "Fainted!");
        }
    }

    public void useAbility(Scanner scanner, Character enemy) {
        System.out.println();
        System.out.print("What do you wan to do? (Choose 1-5): ");
        switch (scanner.nextInt()){
            case 1:
                abilityPiercingShot(enemy);
                break;
            case 2:
                abilityShadowStep();
                break;
            case 3:
                abilityExplosiveVolley(enemy);
                break;
            case 4:
                abilityHawkEye();
                break;
            case 5:
                abilityVenomShot(enemy);
                break;
        }

    }

    public int getEnduranceBuffs(){
        return stealth + evasion;
    }

    @Override
    public String toString() {
        return "Archer{" +
                "accuracy=" + accuracy +
                ", focus=" + focus +
                ", stealth=" + stealth +
                ", multiShot=" + multiShot +
                ", evasion=" + evasion +
                "} " + super.toString();
    }
}
