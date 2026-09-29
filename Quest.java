package kingdomquest;

public class Quest {

    private String questName;
    private String description;
    private int requiredVictories;
    private int currentVictories;
    private int rewardExperience;
    private Item rewardItem;
    private boolean completed;
    private boolean rewardClaimed;

    public Quest() {
        this("Unknown Quest", "No Description", 1, 0, null);
    }

    public Quest(String name, String description, int requiredVictories, int rewardsExp, Item rewardItem) {
        if (name == null || name.trim().isEmpty()) {
            this.questName = "Unknown Quest";
        } else {
            this.questName = name.trim();
        }

        if (description == null || description.trim().isEmpty()) {
            this.description = "No Description";
        } else {
            this.description = description.trim();
        }

        if (requiredVictories <= 0) {
            this.requiredVictories = 1;
        } else {
            this.requiredVictories = requiredVictories;
        }

        if (rewardsExp < 0) {
            this.rewardExperience = 0;
        } else {
            this.rewardExperience = rewardsExp;
        }

        this.rewardItem = rewardItem;
        this.currentVictories = 0;
        this.completed = false;
        this.rewardClaimed = false;
    }

    public boolean recordVictory() {
        if (this.completed) {
            System.out.println("Quest is already completed.");
            return false;
        }

        this.currentVictories++;
        System.out.println("Quest Progress: " + this.currentVictories + "/" + this.requiredVictories);

        if (currentVictories >= requiredVictories) {
            this.completed = true;
            System.out.println("Quest: " + this.questName + " was completed!");
            return true;
        }

        return false;

    }

    public boolean claimReward(PlayerCharacter character, Inventory inventory) {
        if (!this.completed) {
            System.out.println("Quest is not completed.");
            return false;
        }

        if (this.rewardClaimed) {
            System.out.println("Reward was already claimed.");
            return false;
        }

        if (character == null) {
            System.out.println("Invalid character.");
            return false;
        }

        if (this.rewardItem != null) {

            if (inventory == null) {
                System.out.println("Invalid inventory.");
                return false;
            }

            if (!inventory.add(this.rewardItem)) {
                System.out.println("The reward item could not be added.");
                return false;
            }
        }

        if (this.rewardExperience > 0) {
            character.gainExperience(this.rewardExperience);
        }

        this.rewardClaimed = true;

        System.out.println("Quest reward claimed.");
        return true;
    }

    public String getQuestName() {
        return this.questName;
    }

    public String getDescription() {
        return this.description;
    }

    public int getRequiredVictories() {
        return this.requiredVictories;
    }

    public int getCurrentVictories() {
        return this.currentVictories;
    }

    public int getRewardExperience() {
        return this.rewardExperience;
    }

    public Item getRewardItem() {
        return this.rewardItem;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public boolean isRewardClaimed() {
        return this.rewardClaimed;
    }

    public void showDetails() {
        System.out.println("======= QUEST INFO =======");
        System.out.println("Name: " + this.questName);
        System.out.println("Description: " + this.description);
        System.out.println("Progress: " + this.currentVictories + "/" + this.requiredVictories);
        System.out.println("Reward XP: " + this.rewardExperience);
        if (this.rewardItem != null) {
            System.out.println("Reward Item: " + this.rewardItem.getItemName());
        } else {
            System.out.println("Reward Item: None");
        }
        String questStatus = this.completed ? "Completed" : "In Progress";
        System.out.println("Status: " + questStatus);
        System.out.println("Reward Claimed: " + this.rewardClaimed);
    }

}
