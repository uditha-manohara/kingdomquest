package kingdomquest;

public abstract class Item implements Usable{
    
    private String itemName;
    
    public Item(){
        this("Unknown Item");
    }
    
    public Item(String name){
        if(name == null || name.trim().isEmpty()){
            this.itemName = "Unknown Item";
        } else {
            this.itemName = name.trim();
        }
    }
    
    public String getItemName(){
        return this.itemName;
    }
    
    public void showDetails(){
        System.out.println("Item name: " + this.itemName);
    }
    
}
