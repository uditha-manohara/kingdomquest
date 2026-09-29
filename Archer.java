package kingdomquest;

public class Archer extends PlayerCharacter {
    
    public Archer(){
        this("Unnamed Archer");
    }
    
    public Archer(String name){
        super(name, 100, 18, 1);
    }
    
    @Override
    public int attack(){
        return this.getBaseDamage() + this.getWeaponDamage() + this.getTempDamage() + 5;
    }
    
    @Override
    public String getType(){
        return "Archer";
    }
    
}
