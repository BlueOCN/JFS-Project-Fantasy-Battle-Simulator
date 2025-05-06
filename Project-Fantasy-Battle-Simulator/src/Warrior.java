import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Scanner;

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

    public void attack(Character enemy) {
        // Enemy Health = Enemy Health - Warrior attack
        if(enemy.getHealth() <= 0) {
            throw new InvalidParameterException("Enemy health must be greater than 0");
        }

        if(super.getHealth() < 3) {
            enemy.setHealth(enemy.getHealth() - super.getStrength() + battleInstinct);
        } else {
            enemy.setHealth(enemy.getHealth() - super.getStrength());
        }
        System.out.println("\nWith a mighty roar, the warrior lunges forward, blade flashing in the moonlight.\n" +
                "The enemy barely has time to react before a crushing strike cleaves through their defense,\n" +
                "sending them staggering backward.");
    }

    public void defend(){
        super.setEndurance(getEndurance() + 1);
    }

    // Spins in place, hitting all surrounding enemies
    public void abilityBladeCyclone(Character enemy) {
        enemy.setHealth(enemy.getHealth() - 2);
    }

    // Temporary immunity to stun and knockback effects.
    public void abilityUnbreakableWill() {
        super.setEndurance(super.getEndurance() + 2);
    }

    // Smashes the ground, creating shockwaves that damage and slow enemies.
    public void abilityEarthshatter(Character enemy) {
        enemy.setHealth(enemy.getHealth() - 3);
    }

    // Wields two massive weapons at once, doubling damage output.
    public void abilityTitansGrip(Character enemy) {
        int damage = super.getStrength() * 2;
        enemy.setHealth(enemy.getHealth() - damage);
    }

    // Intimidates enemies, reduces ability.
    public void abilityWarCry(Character enemy) {
        enemy.setAgility(getAgility() - 1);
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
