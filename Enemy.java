package kingdomquest;

public class Enemy extends GameEntity {
    
    public Enemy(){
        this("Unknown Enemy", 50, 8, 1);
    }
    
    public Enemy(String name, int maxHealth, int damage, int level){
        super(name, maxHealth, damage, level);
    }
    
    @Override
    public int attack(){
        return this.getBaseDamage();
    }
    
}
