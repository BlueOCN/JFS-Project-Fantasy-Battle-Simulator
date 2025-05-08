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
        System.out.println("\nInstinct takes over—the archer swiftly raises his bow, dodging to the side as \n" +
                           "an incoming strike barely grazes past. My keen reflexes kick in; I roll into\n" +
                           "cover and pull a defensive stance. Eyes scanning the battlefield, I brace myself, \n" +
                           "ready to counter as soon as my opponent reveals themselves.\n");
    }

    // Fires an arrow that ignores armor and deals bonus damage.
    public void abilityPiercingShot(Character enemy) {
        int bonusDamage = 1;
        int damage = super.getStrength() + bonusDamage;
        System.out.println("\tThe archer steadies their stance, exhaling slowly as he draws back the bowstring. PiercingShot charges—arcane energy crackling along the arrow’s shaft. With unwavering focus, he releases, the projectile slicing through the air with lethal precision. Eyes locked forward, he remains poised, ready for whatever unfolds after the arrow finds its mark.");
        try {
            enemy.setHealth(enemy.getHealth() - damage);
        } catch (InvalidHealthException e) {
            enemy.setHealth(0);
            System.out.println(enemy.getName() + " fainted!");
        }
    }

    // Teleports a short distance, evading attacks.
    public void abilityShadowStep() {
        System.out.println("\nThe archer exhales calmly, sensing danger closing in. ShadowStep awakens—a subtle shift, a flicker in the air. In an instant, they vanish, a blur of motion slipping into the shadows. Their footsteps make no sound, their presence undetectable. The attack arrives, but the archer is already elsewhere, unseen, untouched.");
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

        System.out.println("\nThe archer steadies their stance, breath controlled, eyes sharp. With practiced precision, he nocks multiple arrows, each humming with volatile energy. ExplosiveVolley ignites—fingers release, sending the projectiles streaking through the air. A fiery eruption follows, bursts of force rippling outward as the arrows detonate upon impact, engulfing the battlefield in chaos.");

        try {
            enemy.setHealth(enemyHealth);
        } catch (InvalidHealthException e) {
            System.out.println(enemy.getName() + "Fainted!");
            enemy.setHealth(0);
        }
    }

    // Temporarily increases accuracy and critical hit chance.
    public void abilityHawkEye() {
        System.out.println("\nThe archer narrows their eyes, pulse steady, breath controlled. HawkEye activates—time slows as their senses sharpen beyond mortal limits. Every detail crystallizes, every movement predicted. They spot the incoming strike before it lands, analyzing its trajectory with razor precision. With unshakable focus, they brace for the moment of impact.");
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
        System.out.println("\nThe archer steadies their grip, drawing a dark-tipped arrow from the quiver. A faint, toxic shimmer dances along its surface as VenomShot activates. With cold precision, they pull the bowstring taut, eyes locked forward. The arrow streaks through the air, leaving behind a faint green trail—silent, swift, and deadly.");
        try {
            enemy.setHealth(enemy.getHealth() - damage);
        } catch (InvalidHealthException e) {
            enemy.setHealth(0);
            System.out.println(enemy.getName() + "Fainted!");
        }
    }

    public void useAbility(int choice, Character enemy) {

        switch (choice){
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
                ", evasion=" + evasion +
                "} " + super.toString();
    }
}
