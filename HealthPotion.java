package kingdomquest;

public class HealthPotion extends Item{
    private int healAmount;
    
    public HealthPotion(){
        this("Health Potion", 40);
    }
    
    public HealthPotion(String name, int amount){
        super(name);
        if(amount > 0){
            this.healAmount = amount;
        }else {
            this.healAmount = 40;
        }
    }
    
    @Override
    public boolean use(PlayerCharacter character){
        if(character == null){
            System.out.println("Invalid Character");
            return false;
        }
        if(!character.isAlive()){
            System.out.println("Health potion cannot revive dead characters");
            return false;
        }
        if(character.getCurrentHealth() == character.getMaxHealth()){
            System.out.println("Health is already full");
            return false;
        }
        
        int oldHealth = character.getCurrentHealth();
        character.heal(this.healAmount);
        System.out.println(character.getName() + "'s health increased by " + (character.getCurrentHealth() - oldHealth));
        return true;
        
    }
    
    public int getHealAmount(){
        return this.healAmount;
    }
    
    @Override
    public void showDetails(){
        super.showDetails();
        System.out.println("Healing amount: " + this.healAmount);
    }
    
}
