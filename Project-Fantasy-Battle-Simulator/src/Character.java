import java.util.ArrayList;
import java.util.UUID;

public class Character {

    private String id;
    private String name;
    private String description;
    private int level;
    private int health;
    private int strength;
    private int endurance;
    private int intelligence;
    private int agility;
    private ArrayList<String> abilities;

    Character() {
        this.id = UUID.randomUUID().toString();
        this.name = "Unknown";
        this.description = "Unknown";
        this.level = 1;
        this.health = 10;
        this.strength = 1;
        this.endurance = 1;
        this.intelligence = 1;
        this.agility = 1;
        this.abilities = new ArrayList<>();
    }

    Character(String name, int level, int health, int strength, int endurance, int intelligence, int agility) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.level = level;
        this.health = health;
        this.strength = strength;
        this.endurance = endurance;
        this.intelligence = intelligence;
        this.agility = agility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public ArrayList<String> getAbilities() {
        return this.abilities;
    }

    public void setAbilities(ArrayList<String> abilities) {
        this.abilities = abilities;
    }

    public void displayAbilities() {
        for (String ability : abilities){
            System.out.print(abilities.indexOf(ability) + 1 + ". " + ability + "\t");
        }
    }

    public void displayStats() {
        // Display stats
        System.out.println();
        System.out.println(name + "\t|\t" + description);
        System.out.println("Level: " + level);
        System.out.println("Health: " + health);
        System.out.println("Strength: " + strength);
        System.out.println("Endurance: " + endurance);
        System.out.println("Intelligence: " + intelligence);
        System.out.println("Agility: " + agility);
    }

    @Override
    public String toString() {
        return "Character{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", level=" + level +
                ", health=" + health +
                ", strength=" + strength +
                ", endurance=" + endurance +
                ", intelligence=" + intelligence +
                ", agility=" + agility +
                ", abilities=" + abilities +
                '}';
    }
}
