package kingdomquest;

public class RevivalPotion extends Item {
    private int reviveHealth;
    
    public RevivalPotion(){
        this("Revival Potion", 50);
    }
    
    public RevivalPotion(String name, int reviveAmount){

        super(name);
        if(reviveAmount > 0){
            this.reviveHealth = reviveAmount;
        } else {
            this.reviveHealth = 50;
        }

    }
    
    @Override
    public boolean use(PlayerCharacter character){
        if(character == null){
            System.out.println("Invalid Character");
            return false;
        }
        if(character.isAlive()){
            System.out.println("Revive potion can only be used on dead characters");
            return false;
        }
        int restoredHealth = reviveHealth;
        
        if(restoredHealth > character.getMaxHealth()){
            restoredHealth = character.getMaxHealth();
        }
        character.setCurrentHealth(restoredHealth);
        System.out.println(character.getName() + " was revived with " + restoredHealth + " health.");
        return true;
        
    }
    
    public int getReviveHealth(){
        return this.reviveHealth;
    }
    
    @Override
    public void showDetails(){
        super.showDetails();
        System.out.println("Revival Health: " + this.reviveHealth);
    }
    
}
