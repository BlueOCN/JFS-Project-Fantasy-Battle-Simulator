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
        abilities.add("Accuracy");
        abilities.add("Focus");
        abilities.add("Stealth");
        abilities.add("Multi-Shot");
        abilities.add("Evasion");
        super.setAbilities(abilities);
    }

    public void attack(Character enemy) {
        // Enemy Health = Enemy Health - Warrior attack
        if(enemy.getHealth() <= 0) {
            throw new InvalidParameterException("Enemy health must be greater than 0");
        }

        if(super.getHealth() < 3) {
            enemy.setHealth(enemy.getHealth() - super.getStrength() + focus);
        } else {
            enemy.setHealth(enemy.getHealth() - super.getStrength());
        }
        System.out.println("\nFrom the shadows, the archer draws, holding their breath.\n" +
                "A single arrow whizzes through the air, striking true—piercing armor\n" +
                "and finding its mark with deadly precision.");
    }

    public void defend(){
        super.setEndurance(getEndurance() + 1);
    }

    // Fires an arrow that ignores armor and deals bonus damage.
    public void abilityPiercingShot(Object enemy) {}

    // Teleports a short distance, evading attacks.
    public void abilityShadowStep() {}

    // Shoots a barrage of arrows that detonate on impact.
    public void abilityExplosiveVolley(Object enemy) {}

    // Temporarily increases accuracy and critical hit chance.
    public void abilityHawkEye() {}

    // Poisons enemies, dealing damage over time.
    public void abilityVenomShot(Object enemy) {}

    public void useAbility(Scanner scanner, Character enemy) {
        System.out.println();
        System.out.print("What do you wan to do? (Choose 1-5): ");
        switch (scanner.nextInt()){
            case 1:
                System.out.println("abilityBladeCyclone");
                abilityPiercingShot(enemy);
                break;
            case 2:
                System.out.println("abilityUnbreakableWill");
                abilityShadowStep();
                break;
            case 3:
                System.out.println("abilityEarthshatter");
                abilityExplosiveVolley(enemy);
                break;
            case 4:
                System.out.println("abilityTitansGrip");
                abilityHawkEye();
                break;
            case 5:
                System.out.println("abilityWarCry");
                abilityVenomShot(enemy);
                break;
        }

    }
}
