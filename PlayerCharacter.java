package kingdomquest;

public abstract class PlayerCharacter extends GameEntity {

    private int experience;
    private Weapon equippedWeapon;
    private int tempDamage;
    
    public PlayerCharacter(){
        super();
        this.experience = 0;
        this.equippedWeapon = new Weapon();
        this.tempDamage = 0;
    }
    
    public PlayerCharacter(String name, int maxHealth, int baseDamage, int level){
        super(name, maxHealth, baseDamage, level);
        this.experience = 0;
        this.equippedWeapon = new Weapon();
        this.tempDamage = 0;
    }
    
    public abstract String getType();
    
    public int getExperience(){
        return this.experience;
    }
    
    public void gainExperience(int amount){
        if(amount > 0){
            this.experience = this.experience + amount;
            
            System.out.println(this.getName() + " gained " + amount + " XP");
            
            while(this.experience >= 80){
                this.experience = this.experience - 80;
                levelUp();
            }
            
            
            System.out.println("Current XP: " + this.experience);
        } else {
            System.out.println("Experience amount must be greater than 0.");
        }
    }
    
    private void levelUp(){
        this.setLevel(this.getLevel() + 1);
        this.setMaxHealth(this.getMaxHealth() + 10);
        this.setBaseDamage(this.getBaseDamage() + 2);
        this.setCurrentHealth(this.getMaxHealth());
        System.out.println(this.getName() + " leveled up to level " + this.getLevel() + "!");
    }
    
    @Override
    public void showDetails(){
        super.showDetails();
        System.out.println("Experience: " + this.experience + "/80");
        System.out.println("Weapon Equipped: " + equippedWeapon.getWeaponName());
        if(this.tempDamage > 0){
            System.out.println("Temporary Damage: " + this.tempDamage);
        }
    }
    
    public Weapon getEquippedWeapon(){
        return this.equippedWeapon;
    }
    
    public boolean equipWeapon(Weapon weapon){
        if(weapon == null){
            System.out.println("Invalid weapon");
            return false;
        }
        
        if(weapon.canBeUsedBy(this)){
            this.equippedWeapon = weapon;
            System.out.println(equippedWeapon.getWeaponName() + " is equipped by " + this.getName());
            return true;
        }
        
        System.out.println("This weapon cannot be used by this character");
        return false;
    }
    
    protected int getWeaponDamage(){
        return this.equippedWeapon.getExtraDamage();
    }
    
    public boolean setTempDamage(int damage){
        if(damage > 0){
            this.tempDamage = damage;
            return true;
        } else {
            return false;
        }
        
    }
    
    public int getTempDamage(){
        return this.tempDamage;
    }
    
    public void clearTempDamage(){
        this.tempDamage = 0;
    }
    
    
    
    
    
    
}
