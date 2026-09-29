package kingdomquest;

public class Inventory {
    private Weapon[] weapons;
    private Item[] items;
    private int weaponCount;
    private int itemCount;
    
    public Inventory(){
        this.weapons = new Weapon[5];
        this.items = new Item[10];
        this.weaponCount = 0;
        this.itemCount = 0;
    }
    
    public boolean add(Weapon weapon){
        if(weapon == null){
            System.out.println("Invalid Weapon");
            return false;
        }
        if(weaponCount >= weapons.length){
            System.out.println("Weapon inventory is full");
            return false;
        }
        weapons[weaponCount] = weapon;
        weaponCount++;
        System.out.println(weapon.getWeaponName() + " added to inventory.");
        return true;
    }
    
    public boolean add(Item item){
        if(item == null){
            System.out.println("Invalid Item");
            return false;
        }
        if(itemCount >= items.length){
            System.out.println("Item inventory is full");
            return false;
        }
        items[itemCount] = item;
        itemCount++;
        System.out.println(item.getItemName() + " added to inventory");
        return true;
    }
    
    public void showWeapons(){
        if(weaponCount == 0){
            System.out.println("No Weapon");
        }else{
            System.out.println("==== WEAPON INVENTORY =====");
            int i = 0;
            while(i < weaponCount){
                System.out.println("Weapon " + (i+1));
                weapons[i].showDetails();
                i++;
            }
        }
    }
    
    public void showItems(){
        if(itemCount == 0){
            System.out.println("There are no items");
        } else {
            System.out.println("==== ITEM INVENTORY ====");
            int i = 0;
            while(i < itemCount){
                System.out.println("Item " + (i+1));
                items[i].showDetails();
                i++;
            }
        }
    }
    
    public Weapon getWeapon(int index){
        if(index < 0 || index >= weaponCount){
            return null;
        }else{
            return weapons[index];
        }
    }
    
    public Item getItem(int index){
        if(index < 0 || index >= itemCount){
            return null;
        } else {
            return items[index];
        }
    }
    
    public boolean useItem(int index, PlayerCharacter character){
        
        Item item = this.getItem(index);
        
        
        if(item == null){
            System.out.println("Invalid item selection");
            return false;
        }
        
        if(!item.use(character)){
            return false;
        } else {
            int i = index;
            while(i < itemCount - 1){
                items[i] = items[i + 1];
                i++;
            }
            itemCount--;
            items[itemCount] = null;
            
            System.out.println(item.getItemName() + " was removed from inventory");
            return true;
        }
        
        
        
    }
    
    public boolean equipWeapon(int index, PlayerCharacter character){
        Weapon weapon = this.getWeapon(index);
        if(weapon == null){
            System.out.println("Invalid weapon selection");
            return false;
        }
        if(character == null){
            System.out.println("Invalid character");
            return false;
        }
        
        return character.equipWeapon(weapon);
    }
    
    public int getWeaponCount(){
        return this.weaponCount;
    }
    
    public int getItemCount(){
        return this.itemCount;
    }
    
}
