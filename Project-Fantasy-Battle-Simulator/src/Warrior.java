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

        System.out.println("\nHe tightens the grip on his weapon, muscles coiled with controlled force. A deep breath steadies him—then, with explosive speed, He charges forward. His blade arcs through the air, aimed to strike with precision. Heart pounding, He commits to the attack, knowing only battle will decide the outcome.");
    }

    public void defend(){
        try {
            setShieldDefence(shieldDefence + 1);
        } catch (InvalidShieldDefenceException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("The warrior grips his shield, planting the feet firmly. A sudden instinct drives him to raise it—an incoming strike clashes against the steel, forcing him back. The armor absorbs the blow, but He hold his ground, muscles tensed. Eyes locked forward, He prepares for the next move, ready to counter.");
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

        System.out.println("\nMuscles tense, He grips his weapon tightly. With a fierce roar, He channels raw power, spinning into a whirlwind of steel—BladeCyclone unleashed! His blade slashes through the air in a devastating arc, cutting through space like a storm. Dust and energy swirl around him as he strikes with unrelenting force.");

        try {
            enemy.setHealth(enemyHealth);

        } catch (InvalidHealthException e) {
            System.out.println(enemy.getName() + "Fainted!");
            enemy.setHealth(0);
        }
    }

    // Temporary immunity to stun and knockback effects.
    public void abilityUnbreakableWill() {
        System.out.println("\nHe plants his feet firmly, gripping his weapon with unwavering resolve. Unbreakable Will surges through his veins—his body hardens, defying pain, fear, and fatigue. A battle cry erupts as his stance becomes immovable, his spirit indomitable. No force will break him. The enemy strikes. He does not falter. He endures.");
        try {
            super.setEndurance(super.getEndurance() + 2);
        } catch (InvalidEnduranceTooHighException e) {
            super.setEndurance(0);
            System.out.println("Endurance level is too high!");
        }
    }

    // Smashes the ground, creating shock waves that damage and slow enemies.
    public void abilityEarthshatter(Character enemy) {
        System.out.println("\nHe grips his weapon, feeling the raw energy build beneath his feet. With a mighty roar, He raises his blade and slam it into the ground—Earthshatter erupts! The battlefield trembles, shockwaves rip through the earth, sending debris and energy outward in a devastating blast. Power surges, unstoppable, as He unleashes destruction.");
        try {
            enemy.setHealth(enemy.getHealth() - 3);
        } catch (InvalidHealthException e) {
            enemy.setHealth(0);
            System.out.println(enemy.getName() + " fainted!");
        }
    }

    // Wields two massive weapons at once, doubling damage output.
    public void abilityTitansGrip(Character enemy) {
        System.out.println("\nHe tightens his grip, muscles straining as Titan’s Grip surges through him. His weapon feels like an extension of my will—unyielding, unstoppable. With a mighty swing, He wields its massive force, each strike backed by sheer dominance. The ground quakes beneath his power as He commits to the devastating blow.");
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
        System.out.println("\nHe draws a deep breath, feeling raw energy surge through him. WarCry erupts—a thunderous roar shakes the battlefield, a shockwave of sheer force rippling outward. His spirit ignites, muscles tensed, power swelling within. The ground trembles beneath his feet as He charges forward, fueled by unstoppable rage and unwavering might.");
        try {
            enemy.setAgility(getAgility() - 1);
        } catch (InvalidAgilityTooLowException e) {
            enemy.setAgility(0);
            System.out.println("Agility level is too low!");
        }
    }

    public void useAbility(int choice, Character enemy) {

        switch (choice){
            case 1:
                abilityBladeCyclone(enemy);
                break;
            case 2:
                abilityUnbreakableWill();
                break;
            case 3:
                abilityEarthshatter(enemy);
                break;
            case 4:
                abilityTitansGrip(enemy);
                break;
            case 5:
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
