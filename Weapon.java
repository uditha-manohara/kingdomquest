package kingdomquest;

public class Weapon {
    
    private String weaponName;
    private int extraDamage;
    private String requiredCharacterType;
    
    public Weapon(){
        this("Basic Weapon", 0, "Any");
    }
    
    public Weapon(String weaponName, int extraDamage, String requiredCharacterType){
        
        if(weaponName != null && !weaponName.trim().isEmpty()){
            this.weaponName = weaponName.trim();
        } else {
            this.weaponName = "Basic Weapon";
        }
        
        if(extraDamage < 0){
            this.extraDamage = 0;
        } else {
            this.extraDamage = extraDamage;
        }
        
        if(requiredCharacterType != null && !requiredCharacterType.trim().isEmpty()){
            this.requiredCharacterType = requiredCharacterType.trim();
        }else {
            this.requiredCharacterType = "Any";
        }
 
    }

    public String getWeaponName(){
        return this.weaponName;
    }
    
    public int getExtraDamage(){
        return this.extraDamage;
    }
    
    public String getRequiredCharacterType(){
        return this.requiredCharacterType;
    }
    
    public void showDetails(){
        System.out.println("Weapon: " + this.weaponName);
        System.out.println("Extra Damage: " + this.extraDamage);
        System.out.println("Required Character: " + this.requiredCharacterType);
    }
    
    public boolean canBeUsedBy(PlayerCharacter character){
        if(character == null){
            return false;
        }
        if(this.requiredCharacterType.equalsIgnoreCase("Any")){
            return true;
        }
        if(this.requiredCharacterType.equalsIgnoreCase(character.getType())){
            return true;
        }
        
        return false;
    }

    
}
