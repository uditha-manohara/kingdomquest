package kingdomquest;

public class Warrior extends PlayerCharacter {
    
    public Warrior(){
        this("Unnamed Warrior");
    }
    
    public Warrior(String name){
        super(name, 120, 15, 1);
    }
    
    @Override
    public int attack(){
        return this.getBaseDamage() + this.getWeaponDamage() + this.getTempDamage() + 3;
    }
    
    @Override
    public String getType(){
        return "Warrior";
    }
    
}
