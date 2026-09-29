package kingdomquest;

public abstract class GameEntity {
    
    private String name;
    private int maxHealth;
    private int currentHealth;
    private int baseDamage;
    private int level;
    
    public GameEntity(){
        this("Unknown Entity", 100, 10, 1);
    }
    
    public GameEntity(String name, int maxHealth, int baseDamage, int level){
        
        if(name == null || name.trim().isEmpty()){
            this.name = "Unknown Entity";
        } else {
            this.name = name.trim();
        }
        
        if(maxHealth <= 0){
            this.maxHealth = 100;
        } else {
            this.maxHealth = maxHealth;
        }
        
        this.currentHealth = this.maxHealth;
        
        if(baseDamage < 0){
            this.baseDamage = 0;
        } else {
            this.baseDamage = baseDamage;
        }
        
        if(level <= 0){
            this.level = 1;
        }else {
            this.level = level;
        }
        
    }
    
    public abstract int attack();
    
    public int takeDamage(int damage){
        if(damage > 0){
            this.currentHealth = currentHealth - damage;
            
        }
        if(currentHealth <= 0){
            this.currentHealth = 0;
        }
        return this.currentHealth;
    }
    
    public int heal(int amount){
        
        if(amount > 0 && isAlive()){
            this.currentHealth = currentHealth + amount;
        }
        if(currentHealth > maxHealth){
            this.currentHealth = maxHealth;
        }
        return this.currentHealth;
    }
    
    public boolean isAlive(){
        return currentHealth > 0;
    }
    
    public void showDetails(){
        System.out.println("Name: " + name);
        System.out.println("Health: " + currentHealth + "/" + maxHealth);
        System.out.println("Base Damage: " + baseDamage);
        System.out.println("Level: " + level);
        System.out.println("Alive: " + isAlive());
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getMaxHealth(){
        return this.maxHealth;
    }
    
    public int getCurrentHealth(){
        return this.currentHealth;
    }
    
    public int getBaseDamage(){
        return this.baseDamage;
    }
    
    public int getLevel(){
        return this.level;
    }
    
    public void setName(String name){
        if(name != null && !name.trim().isEmpty()){
            this.name = name.trim();
        } else {
            System.out.println("Name cannot be empty");
        }
    }
    
    public void setMaxHealth(int maxHealth){
        if(maxHealth > 0){
            this.maxHealth = maxHealth;
            if(currentHealth > maxHealth){
                this.currentHealth = maxHealth;
            }
        } else {
            System.out.println("Maximum health cannot be 0 or lower");
        }
    }
    
    public void setCurrentHealth(int health){
        if(health >= 0 && health <= maxHealth){
            this.currentHealth = health;
        } else {
            System.out.println("Health must be between 0 and " + maxHealth);
        }
    }
    
    public void setBaseDamage(int damage){
        if(damage >= 0){
            this.baseDamage = damage;
        } else {
            System.out.println("Damage cannot be negative");
        }
    }
    
    public void setLevel(int level){
        if(level > 0){
            this.level = level;
        } else {
            System.out.println("Level must be more than 0");
        }
    }
    
}
