package kingdomquest;

public class Team {
    private PlayerCharacter[] characters;
    private int characterCount;
    private int activeIndex;
    
    public Team(){
        this.characters = new PlayerCharacter[3];
        this.characterCount = 0;
        this.activeIndex = 0;
    }
    
    public boolean addCharacter(PlayerCharacter character){
        if(character == null){
            System.out.println("Invalid Character");
            return false;
        }
        if(characterCount >= characters.length){
            System.out.println("Team is full");
            return false;
        }
        this.characters[this.characterCount] = character;
        this.characterCount++;
        System.out.println(character.getName() + " joined the team.");
        return true;
    }
    
    public void showTeam(){
        if(characterCount > 0){
            
            System.out.println("===== TEAM ======");
            
            int i = 0;
            while(i < characterCount){
                System.out.println("Character " + (i + 1));
                characters[i].showDetails();
                i++;
            }
            
            
        } else {
            System.out.println("Team is Empty!");
        }
    }
    
    public PlayerCharacter getActiveCharacter(){
        if(characterCount > 0){
            return characters[activeIndex];
        } else {
            return null;
        }
        
    }
    
    public PlayerCharacter getCharacter(int id){
        if(id < 0 || id >= characterCount){
            return null;
        }
        return characters[id];
    }
    
    public boolean changeActiveCharacter(int id){
        if(id < 0 || id >= characterCount){
            return false;
        }
        if(!characters[id].isAlive()){
            System.out.println("Character is not alive");
            return false;
        } else {
            activeIndex = id;
            System.out.println("Active character changed to: " + characters[id].getName());
            return true;
        }
    }
    
    public boolean isTeamDefeated(){
        int i = 0;
        int x = 0;
        while(i < characterCount){
            if(!characters[i].isAlive()){
                x++;
            }
            i++;
        }
        
        if(x == characterCount){
            return true;
        } else {
            return false;
        }
            
    }
    
    public boolean selectNextLivingCharacter(){
        if(characterCount == 0){
            System.out.println("The team is empty.");
            return false;
        }
        
        int nextIndex = activeIndex;
        int checkedCharacters = 0;
        
        while(checkedCharacters < characterCount - 1){
            nextIndex++;
            if(nextIndex >= characterCount){
                nextIndex = 0;
            }
            
            if(characters[nextIndex].isAlive()){
                activeIndex = nextIndex;
                System.out.println(characters[nextIndex].getName() + " will continue the battle");
                return true;
            }
            checkedCharacters++;
        }
        
        System.out.println("No other living characters were found.");
        return false;
    }
    
}
