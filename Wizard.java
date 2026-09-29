package kingdomquest;

public class Wizard extends PlayerCharacter {
    
    public Wizard(){
        this("Unnamed Wizard");
    }
    
    public Wizard(String name){
        super(name, 90, 22, 1);
    }
    
    @Override
    public int attack(){
        return this.getBaseDamage() + this.getWeaponDamage() + this.getTempDamage() + 8;
    }
    
    @Override
    public String getType(){
        return "Wizard";
    }
    
}
