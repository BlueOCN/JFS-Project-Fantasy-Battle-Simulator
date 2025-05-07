import java.util.Scanner;

public class BattleSimulator {
    public static void main(String[] args) {


        Warrior warrior = new Warrior("Warrior 1", 1);
        Archer archer = new Archer("Archer 1", 1);
        Mage mage = new Mage("Mage 1", 1);

        Warrior warriorCopy = warrior;
        Archer archerCopy = archer;
        Mage mageCopy = mage;

        System.out.println("Welcome to Battle Simulator!");
        System.out.println("1. Warrior");
        System.out.println("2. Archer");
        System.out.println("3. Mage");
        System.out.print("Choose your character (1 to 3): ");
        Scanner scanner2 = new Scanner(System.in);
        int characterSelection = scanner2.nextInt();


        boolean turnOwner = true;

        // Turn 1 warrior
        do {

            Scanner scanner = new Scanner(System.in);

            // Display stats
            displayStats(warriorCopy, archerCopy);

            // Prompt for an action (attack, defend, special abilities)
            System.out.print("\n1. Attack\t2. Defend\t3. Special Ability");
            System.out.print("\nWhat do you wan to do? (Choose 1-3): ");
            int actionChoice = scanner.nextInt();
            switch (actionChoice) {
                case 1:
                    if(turnOwner) {
                        warriorCopy.attack(archerCopy);
                    } else {
                        archerCopy.attack(warriorCopy);
                    }
                    turnOwner = !turnOwner;
                    break;
                case 2:
                    if(turnOwner) {
                        warriorCopy.defend();
                    } else {
                        archerCopy.defend();
                    }
                    turnOwner = !turnOwner;
                    break;
                case 3:
                    if(turnOwner) {
                        warriorCopy.displayAbilities();
                        warriorCopy.useAbility(scanner, archerCopy);
                    } else {
                        archerCopy.displayAbilities();
                        archerCopy.useAbility(scanner, warriorCopy);
                    }
                    turnOwner = !turnOwner;
                    break;
            }

            if (warriorCopy.getHealth() == 0 || archerCopy.getHealth() == 0) {
                displayStats(warriorCopy, archerCopy);
                System.out.print("\nBattle End!\n");
                break;
            }


        } while (true);



    }

    public static void displayStats(Character warrior, Character archer) {
        System.out.printf(
                "\n%-50s %30s %50s\n" +
                        "%-50s %30s %50s\n" +
                        "%-50s %30s %50s\n" +
                        "%-50s %30s %50s\n" +
                        "%-50s %30s %50s\n" +
                        "%-50s %30s %50s\n" +
                        "%-50s %30s %50s\n",
                warrior.getName(), "", archer.getName(),
                warrior.getDescription(), "", archer.getDescription(),
                "Level " + warrior.getLevel(), "", archer.getLevel() + " Level",
                "Health " + warrior.getHealth(), "", archer.getHealth() + " Health",
                "Strength " + warrior.getStrength(), "", archer.getStrength() + " Strength",
                "Endurance " + warrior.getEndurance(), "", archer.getEndurance() + " Endurance",
                "Agility " + warrior.getAgility(), "", archer.getAgility() + " Agility"
        );
    }

}