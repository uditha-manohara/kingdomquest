package kingdomquest;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=========================");
        System.out.println("      KINGDOM QUEST");
        System.out.println("=========================");
        
        Warrior warrior = new Warrior("Arion");
        Archer archer = new Archer("Lyra");
        Wizard wizard = new Wizard("Merlin");
        
        System.out.println();
        
        Team team = new Team();
        team.addCharacter(warrior);
        team.addCharacter(archer);
        team.addCharacter(wizard);
        
        System.out.println();
        
        Inventory inventory = new Inventory();
        
        Weapon ironSword = new Weapon("Iron Sword", 8, "Warrior");
        Weapon longBow = new Weapon("Long Bow", 7, "Archer");
        Weapon magicStaff = new Weapon("Magic Staff", 10, "Wizard");
        Weapon commonDagger = new Weapon("Common Dagger", 4, "Any");
        
        System.out.println();
        
        inventory.add(ironSword);
        inventory.add(longBow);
        inventory.add(magicStaff);
        inventory.add(commonDagger);
        
        HealthPotion healthPotion = new HealthPotion("Health Potion", 40);
        RevivalPotion revivalPotion = new RevivalPotion("Revival Potion", 50);
        MagicPotion magicPotion = new MagicPotion("Magic Potion", 10);
        
        System.out.println();
        
        inventory.add(healthPotion);
        inventory.add(revivalPotion);
        inventory.add(magicPotion);
        
        Item questReward = new RevivalPotion("Royal Revival Potion", 70);
        
        Quest quest = new Quest("Defender of the kingdom", "Defeat three enemies threatening the kingdom.", 3, 80, questReward);
        
        Battle battle = new Battle(team, inventory, quest);
        
        Enemy goblin = new Enemy("Goblin", 60, 8, 1);
        Enemy orc = new Enemy("Orc", 90, 12, 2);
        Enemy darkKnight = new Enemy("Dark Knight", 130, 16, 3);
        
        boolean goblinDefeated = battle.startBattle(goblin, scanner);
        
        if(!goblinDefeated){
            System.out.println();
            System.out.println("GAME OVER");
            scanner.close();
            return;
        }
        
        System.out.println();
        System.out.println("You defeated the Goblin!");
        System.out.println("========================");
        System.out.println("The Orc approaches...");
        
        boolean orcDefeated = battle.startBattle(orc, scanner);
        
        if(!orcDefeated){
            System.out.println();
            System.out.println("GAME OVER");
            scanner.close();
            return;
        }
        
        System.out.println();
        System.out.println("You defeated the Orc!");
        System.out.println("The Dark Knight approaches...");
        
        boolean darkKnightDefeated = battle.startBattle(darkKnight, scanner);
        
        if(!darkKnightDefeated){
            System.out.println();
            System.out.println("GAME OVER");
            scanner.close();
            return;
        }
        
        System.out.println();
        System.out.println("===============================");
        System.out.println("          KINGDOM SAVED!");
        System.out.println("===============================");
        System.out.println("All enemies were defeated.");
        
        System.out.println();
        quest.showDetails();
        
        System.out.println();
        System.out.println("===== FINAL TEAM =====");
        team.showTeam();
        
        scanner.close();
        

    }
}