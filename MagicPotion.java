package kingdomquest;

public class MagicPotion extends Item {
    private int bonusDamage;
    
    public MagicPotion(){
        this("Magic Potion", 10);
    }
    
    public MagicPotion(String name, int damage){
        super(name);
        if(damage > 0){
            this.bonusDamage = damage;
        } else {
            this.bonusDamage = 10;
        }
    }
    
    @Override
    public boolean use(PlayerCharacter character){
        if(character == null){
            System.out.println("Invalid character");
            return false;
        }
        if(!character.isAlive()){
            System.out.println("Defeated character cannot use it");
            return false;
        }
        if(character.getTempDamage() > 0){
            System.out.println("Magic effect already applied");
            return false;
        }
        if(character.setTempDamage(this.bonusDamage)){   
            System.out.println(character.getName() + " Received " + this.bonusDamage + " Bonus Damage.");
            return true;
        }
        
        return false;
        
        
    }
    
    public int getBonusDamage(){
        return this.bonusDamage;
    }
    
    @Override
    public void showDetails(){
        super.showDetails();
        System.out.println("Bonus Damage: " + this.getBonusDamage());
    }
    
}
