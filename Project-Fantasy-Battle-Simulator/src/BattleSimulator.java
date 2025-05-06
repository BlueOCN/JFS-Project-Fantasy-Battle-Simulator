import java.util.Scanner;

public class BattleSimulator {
    public static void main(String[] args) {
        // Main

        Warrior warrior = new Warrior("Warrior 1", 1);
        Archer archer = new Archer("Archer 1", 1);

        System.out.println("Warrior: " + warrior);
        System.out.println("Archer: " + archer);

        Warrior warriorCopy = warrior;
        Archer archerCopy = archer;

        boolean turnOwner = true;

        // Turn 1 warrior
        do {

            Scanner scanner = new Scanner(System.in);

            // Display stats
            warriorCopy.displayStats();
            archerCopy.displayStats();
            System.out.println(turnOwner);

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
                        archerCopy.useAbility(scanner, archerCopy);
                    }
                    turnOwner = !turnOwner;
                    break;
            }




        } while (true);



    }
}