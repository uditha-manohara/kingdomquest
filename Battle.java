package kingdomquest;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Battle {

    private Team team;
    private Inventory inventory;
    private Quest quest;

    public Battle(Team team, Inventory inventory, Quest quest) {
        this.team = team;
        this.inventory = inventory;
        this.quest = quest;
    }

    private int readNumber(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException exception) {
            System.out.println("Invalid input. Please enter a number.");

            scanner.nextLine();

            return -1;

        }
    }

    public boolean playerAttack(Enemy enemy) {
        if (enemy == null) {
            System.out.println("Invalid Enemy");
            return false;
        }
        if (!enemy.isAlive()) {
            System.out.println("Enemy is already defeated");
            return false;
        }

        if (this.team == null) {
            System.out.println("Invalid team.");
            return false;
        }

        PlayerCharacter activeCharacter = team.getActiveCharacter();

        if (activeCharacter == null) {
            System.out.println("No Active Character");
            return false;
        }
        if (!activeCharacter.isAlive()) {
            System.out.println("Active Character is defeated");
            return false;
        }

        int attackDamage = activeCharacter.attack();

        enemy.takeDamage(attackDamage);
        System.out.println(activeCharacter.getName() + " attacked " + enemy.getName() + " for " + attackDamage + " damage.");
        System.out.println(enemy.getName() + " HP: " + enemy.getCurrentHealth() + "/" + enemy.getMaxHealth());

        if (activeCharacter.getTempDamage() > 0) {
            activeCharacter.clearTempDamage();
        }

        if (!enemy.isAlive()) {
            System.out.println("Enemy defeated");
            return true;
        }

        return false;

    }

    public boolean enemyAttack(Enemy enemy) {
        if (enemy == null) {
            System.out.println("Invalid enemy");
            return false;
        }
        if (!enemy.isAlive()) {
            System.out.println("The enemy is already defeated.");
            return false;
        }
        if (this.team == null) {
            System.out.println("Invalid team.");
            return true;
        }

        PlayerCharacter activeCharacter = team.getActiveCharacter();

        if (activeCharacter == null) {
            System.out.println("There are no active characters.");
            return true;
        }

        if (!activeCharacter.isAlive()) {
            boolean nextActive = team.selectNextLivingCharacter();

            if (!nextActive) {
                System.out.println("The team was defeated.");
                return true;
            }

            activeCharacter = team.getActiveCharacter();

            System.out.println(activeCharacter.getName() + " is the new active character.");
        }

        int enemyDamage = enemy.attack();
        activeCharacter.takeDamage(enemyDamage);

        System.out.println(enemy.getName() + " dealt " + enemyDamage + " damage to " + activeCharacter.getName() + ".");
        System.out.println(activeCharacter.getName() + " HP: " + activeCharacter.getCurrentHealth() + "/" + activeCharacter.getMaxHealth());

        if (!activeCharacter.isAlive()) {
            System.out.println(activeCharacter.getName() + " was defeated.");

            boolean nextActive = team.selectNextLivingCharacter();

            if (nextActive) {
                activeCharacter = team.getActiveCharacter();

                System.out.println(activeCharacter.getName() + " is the new active character.");

                return false;
            }

            System.out.println("The team was defeated.");
            return true;
        }
        return false;

    }

    public boolean startBattle(Enemy enemy, Scanner scanner) {
        if (enemy == null) {
            System.out.println("Invalid enemy.");
            return false;
        }
        if (!enemy.isAlive()) {
            System.out.println("This enemy is already defeated.");
            return false;
        }
        if (scanner == null) {
            System.out.println("Invalid Scanner");
            return false;
        }
        if (this.team == null || this.team.isTeamDefeated()) {
            System.out.println("The team cannot battle");
            return false;
        }

        System.out.println();
        System.out.println("====================");
        System.out.println("   BATTLE STARTED");
        System.out.println("====================");
        System.out.println("Enemy: " + enemy.getName());
        System.out.println("Enemy HP: " + enemy.getCurrentHealth() + "/" + enemy.getMaxHealth());

        while (enemy.isAlive() && !team.isTeamDefeated()) {
            PlayerCharacter activeCharacter = team.getActiveCharacter();
            if (activeCharacter == null) {
                System.out.println("There is no active character.");
                return false;
            }

            System.out.println();
            System.out.println("===== BATTLE STATUS =====");
            System.out.println("Active Character: " + activeCharacter.getName());
            System.out.println(activeCharacter.getName() + " HP: " + activeCharacter.getCurrentHealth() + "/" + activeCharacter.getMaxHealth());
            System.out.println(enemy.getName() + " HP: " + enemy.getCurrentHealth() + "/" + enemy.getMaxHealth());

            System.out.println();
            System.out.println("1. Attack");
            System.out.println("2. Change Character");
            System.out.println("3. Use Item");
            System.out.println("4. Equip Weapon");
            System.out.println("5. View Details");
            System.out.print("Choose an action: ");
            int choice = readNumber(scanner);

            switch (choice) {

                case 1: {
                    boolean enemyDefeated = playerAttack(enemy);

                    if (enemyDefeated) {
                        break;
                    }

                    boolean teamDefeated = enemyAttack(enemy);

                    if (teamDefeated) {
                        return false;
                    }

                    break;
                }

                case 2: {
                    team.showTeam();

                    System.out.print("Select character number: ");
                    int characterNumber = readNumber(scanner);

                    int characterIndex = characterNumber - 1;

                    boolean changed
                            = team.changeActiveCharacter(characterIndex);

                    if (changed) {
                        PlayerCharacter newActiveCharacter
                                = team.getActiveCharacter();

                        System.out.println(
                                newActiveCharacter.getName()
                                + " is now the active character."
                        );

                        boolean teamDefeated = enemyAttack(enemy);

                        if (teamDefeated) {
                            return false;
                        }
                    }

                    break;
                }

                case 3: {
                    if (this.inventory == null) {
                        System.out.println("Invalid inventory.");

                    } else if (inventory.getItemCount() == 0) {
                        System.out.println("There are no items to use.");

                    } else {
                        inventory.showItems();

                        System.out.print("Select item number: ");
                        int itemNumber = readNumber(scanner);

                        int itemIndex = itemNumber - 1;

                        team.showTeam();

                        System.out.print(
                                "Select target character number: "
                        );

                        int characterNumber = readNumber(scanner);
                        int characterIndex = characterNumber - 1;

                        PlayerCharacter targetCharacter
                                = team.getCharacter(characterIndex);

                        if (targetCharacter == null) {
                            System.out.println(
                                    "Invalid character selection."
                            );

                        } else {
                            boolean itemUsed
                                    = inventory.useItem(
                                            itemIndex,
                                            targetCharacter
                                    );

                            if (itemUsed) {
                                boolean teamDefeated
                                        = enemyAttack(enemy);

                                if (teamDefeated) {
                                    return false;
                                }
                            }
                        }
                    }

                    break;
                }

                case 4: {
                    if (this.inventory == null) {
                        System.out.println("Invalid inventory.");

                    } else if (inventory.getWeaponCount() == 0) {
                        System.out.println(
                                "There are no weapons available."
                        );

                    } else {
                        inventory.showWeapons();
                        
                        System.out.print("Select weapon number: ");
                        
                        int weaponNumber = readNumber(scanner);
                        int weaponIndex = weaponNumber - 1;

                        team.showTeam();

                        System.out.print("Select character number: ");
                        
                        int characterNumber = readNumber(scanner);
                        int characterIndex = characterNumber - 1;

                        PlayerCharacter targetCharacter = team.getCharacter(characterIndex);
                        if (targetCharacter == null) {
                            System.out.println(
                                    "Invalid character selection."
                            );

                        } else {
                            boolean equipped = inventory.equipWeapon(weaponIndex, targetCharacter);

                            if (equipped) {
                                boolean teamDefeated = enemyAttack(enemy);

                                if (teamDefeated) {
                                    return false;
                                }
                            }
                        }
                    }

                    break;
                }

                case 5: {
                    System.out.println();
                    System.out.println("===== TEAM DETAILS =====");

                    team.showTeam();

                    if (this.inventory != null) {
                        inventory.showWeapons();
                        inventory.showItems();
                    }

                    if (this.quest != null) {
                        quest.showDetails();
                    }

                    break;
                }

                default: {
                    System.out.println("Invalid Selection");
                    break;
                }
            }
        }

        if (!enemy.isAlive()) {
            System.out.println();
            System.out.println(enemy.getName() + " was defeated!");
            System.out.println("Battle won!");

            PlayerCharacter winningCharacter = team.getActiveCharacter();

            if (winningCharacter != null) {
                int experienceReward = enemy.getLevel() * 20;
                winningCharacter.gainExperience(experienceReward);

                System.out.println(winningCharacter.getName() + " received " + experienceReward + " XP.");

                if (this.quest != null) {
                    if (!quest.isCompleted()) {
                        quest.recordVictory();
                    }
                    if (quest.isCompleted() && !quest.isRewardClaimed()) {
                        quest.claimReward(winningCharacter, inventory);
                    }
                }
            }

            return true;
        }

        System.out.println("Battle lost.");
        return false;

    }

}
