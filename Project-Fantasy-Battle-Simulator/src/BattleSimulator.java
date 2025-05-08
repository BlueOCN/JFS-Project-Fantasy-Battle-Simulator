import java.util.Scanner;

public class BattleSimulator {
    public static void main(String[] args) {

        int turnNumber = 1;
        boolean turnOwner = true;

        Character player1 = null;
        Character player2 = null;

        System.out.println("Welcome to Battle Simulator!");
        System.out.println("1. Warrior");
        System.out.println("2. Archer");
        System.out.println("3. Mage");
        System.out.print("Choose your character (1 to 3): ");
        Scanner scanner2 = new Scanner(System.in);
        int characterSelection = scanner2.nextInt();

        if (characterSelection == 1) {
            player1 = new Warrior("Warrior 1", 1);
        } else if (characterSelection == 2) {
            player1 = new Archer("Archer 1", 1);
        } else if (characterSelection == 3) {
            player1 = new Mage("Mage 1", 1);
        }


        // Random Character for player 2
        int randomInt = (int) (Math.random() * 3) + 1;
        if (randomInt == 1) {
            player2 = new Warrior("Warrior 1", 1);
        } else if (randomInt == 2) {
            player2 = new Archer("Archer 1", 1);
        } else if (randomInt == 3) {
            player2 = new Mage("Mage 1", 1);
        }

        // Battle Loop
        do {

            int actionChoice;
            Scanner scanner = new Scanner(System.in);

            // Display stats
            System.out.print("\nTurn number: " + turnNumber);
            if (turnOwner) {
                System.out.println("\t Player 1 Turn");
            } else {
                System.out.println("\t Player 2 Turn");
            }
            displayStats(player1, player2);

            if(turnOwner) {
                // Prompt for an action (attack, defend, special abilities)
                System.out.print("\n1. Attack\t2. Defend\t3. Special Ability");
                System.out.print("\nWhat do you wan to do? (Choose 1-3): ");
                actionChoice = scanner.nextInt();
            } else {
                // Random action for player 2
                actionChoice = (int) (Math.random() * 3) + 1;
            }

            // Take Action
            takeAction(player1, player2, actionChoice, turnOwner, scanner);

            // End turn
            turnOwner = !turnOwner;

            // Increment turn counter
            turnNumber++;

            //  Check for end battle conditions
            if (player1.getHealth() == 0 || player2.getHealth() == 0) {
                displayStats(player1, player2);
                System.out.print("\nBattle End!\n");
                break;
            }


        } while (true);

    }

    public static <T extends Character> void takeAction(T player1, T player2, int action, boolean turnOwner, Scanner scanner ) {
        switch (action) {
            case 1:     // Attack
                if(turnOwner) {
                    player1.attack(player2);
                } else {
                    player2.attack(player1);
                }
                break;

            case 2:     // Defend
                if(turnOwner) {
                    player1.defend();
                } else {
                    player2.defend();
                }
                break;

            case 3:     // Special Abilities
                if(turnOwner) {
                    player1.displayAbilities();

                    System.out.println();
                    System.out.print("What do you wan to do? (Choose 1-5): ");
                    player1.useAbility(scanner.nextInt(), player2);

                } else {
                    // Random Ability for player 2
                    int totalAbilities = player2.getAbilities().size();
                    int randomInt = (int) (Math.random() * totalAbilities) + 1;
                    player2.useAbility(randomInt, player1);
                }
                break;
        }

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
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