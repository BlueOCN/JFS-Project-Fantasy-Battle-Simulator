import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Scanner;

public class Warrior extends Character {

    // Character description
    private static final String description = "Warrior (Frontline Tank & Melee Fighter)";

    // Increases reaction speed to counter enemy moves.
    private int battleInstinct;

    // Increases resistance to special abilities.
    private int titansEndurance;

    // Reduces incoming physical damage
    private int armorMastery;

    // Grants bonus attack power when health drops below a certain threshold
    private int berserkerRage;

    // Allows blocking attacks and reducing damage from critical hits
    private int shieldDefence;

    // Constructors

    Warrior() {
        super();
        super.setDescription(description);
        this.battleInstinct = 1;
        this.titansEndurance = 1;
        this.armorMastery = 1;
        this.berserkerRage = 1;
        this.shieldDefence = 1;
    }

    Warrior(String name, int level) {
        super();
        super.setName(name);
        super.setLevel(level);
        super.setDescription(description);
        this.battleInstinct = level;
        this.titansEndurance = level;
        this.armorMastery = level;
        this.berserkerRage = level;
        this.shieldDefence = level;

        // Add abilities
        ArrayList<String> abilities = new ArrayList<>();
        abilities.add("Blade Cyclone");
        abilities.add("Unbreakable Will");
        abilities.add("Earth Shatter");
        abilities.add("Titans Grip");
        abilities.add("WarCry");
        super.setAbilities(abilities);

    }

    Warrior(int battleInstinct, int titansEndurance, int armorMastery, int berserkerRage, int shieldDefence) {
        super();
        super.setDescription(description);
        this.battleInstinct = battleInstinct;
        this.titansEndurance = titansEndurance;
        this.armorMastery = armorMastery;
        this.berserkerRage = berserkerRage;
        this.shieldDefence = shieldDefence;
    }

    // Getters and Setters


    public int getBattleInstinct() {
        return battleInstinct;
    }

    public void setBattleInstinct(int battleInstinct) {
        this.battleInstinct = battleInstinct;
    }

    public int getTitansEndurance() {
        return titansEndurance;
    }

    public void setTitansEndurance(int titansEndurance) {
        this.titansEndurance = titansEndurance;
    }

    public int getArmorMastery() {
        return armorMastery;
    }

    public void setArmorMastery(int armorMastery) {
        this.armorMastery = armorMastery;
    }

    public int getBerserkerRage() {
        return berserkerRage;
    }

    public void setBerserkerRage(int berserkerRage) {
        this.berserkerRage = berserkerRage;
    }

    public int getShieldDefence() {
        return shieldDefence;
    }

    public void setShieldDefence(int shieldDefence) {
        if( shieldDefence < 0 || shieldDefence > 100) {
            throw new InvalidShieldDefenceException("Shield Defence must be between 0 and 100");
        }
        this.shieldDefence = shieldDefence;
    }

    // Methods
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
        if(super.getHealth() < 3) {
            attackPoints = (super.getStrength() + berserkerRage);
        } else {
            attackPoints = super.getStrength();
        }

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

        System.out.println("\nWith a mighty roar, the warrior lunges forward, blade flashing in the moonlight.\n" +
                "The enemy barely has time to react before a crushing strike cleaves through their defense,\n" +
                "sending them staggering backward.");
    }

    public void defend(){
        try {
            setShieldDefence(shieldDefence + 1);
        } catch (InvalidShieldDefenceException e) {
            System.out.println(e.getMessage());
        }
    }

    // Spins in place, hitting all surrounding enemies
    public void abilityBladeCyclone(Character enemy) {

        int damage, defensePoints, attackPoints, enemyHealth;

        // Verify enemy health is greater than 0
        enemyHealth = enemy.getHealth();
        if(enemyHealth <= 0) {
            throw new InvalidHealthException("Enemy health must be greater than 0");
        }

        // Calculate enemy defense after boosts or deboosts
        defensePoints = enemy.getEndurance() + enemy.getEnduranceBuffs();

        // Calculate attack points after boosts or deboosts
        attackPoints = super.getStrength() + 2;

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

    // Temporary immunity to stun and knockback effects.
    public void abilityUnbreakableWill() {
        try {
            super.setEndurance(super.getEndurance() + 2);
        } catch (InvalidEnduranceTooHighException e) {
            super.setEndurance(0);
            System.out.println("Endurance level is too high!");
        }
    }

    // Smashes the ground, creating shock waves that damage and slow enemies.
    public void abilityEarthshatter(Character enemy) {
        try {
            enemy.setHealth(enemy.getHealth() - 3);
        } catch (InvalidHealthException e) {
            enemy.setHealth(0);
            System.out.println(enemy.getName() + " fainted!");
        }
    }

    // Wields two massive weapons at once, doubling damage output.
    public void abilityTitansGrip(Character enemy) {
        try {
            int damage = super.getStrength() * 2;
            enemy.setHealth(enemy.getHealth() - damage);
        } catch (InvalidHealthException e) {
            enemy.setHealth(0);
            System.out.println(enemy.getName() + " fainted!");
        }
    }

    // Intimidates enemies, reduces ability.
    public void abilityWarCry(Character enemy) {
        try {
            enemy.setAgility(getAgility() - 1);
        } catch (InvalidAgilityTooLowException e) {
            enemy.setAgility(0);
            System.out.println("Agility level is too low!");
        }
    }

    public void useAbility(Scanner scanner, Character enemy) {
        System.out.println();
        System.out.print("What do you wan to do? (Choose 1-5): ");
        switch (scanner.nextInt()){
            case 1:
                System.out.println("abilityBladeCyclone");
                abilityBladeCyclone(enemy);
                break;
            case 2:
                System.out.println("abilityUnbreakableWill");
                abilityUnbreakableWill();
                break;
            case 3:
                System.out.println("abilityEarthshatter");
                abilityEarthshatter(enemy);
                break;
            case 4:
                System.out.println("abilityTitansGrip");
                abilityTitansGrip(enemy);
                break;
            case 5:
                System.out.println("abilityWarCry");
                abilityWarCry(enemy);
                break;
        }

    }

    public int getEnduranceBuffs(){
        return this.battleInstinct + this.armorMastery;
    }


    @Override
    public String toString() {
        return "Warrior{" +
                "battleInstinct=" + battleInstinct +
                ", titansEndurance=" + titansEndurance +
                ", armorMastery=" + armorMastery +
                ", berserkerRage=" + berserkerRage +
                ", shieldDefence=" + shieldDefence +
                "} " + super.toString();
    }
}
