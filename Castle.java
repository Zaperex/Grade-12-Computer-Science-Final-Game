/* Name: Frank Kong
 * Date: December 28, 2019
 * Description: Castle class that extends the Building Class and represents the main base where troops are hired
 */

class Castle extends Building{
  private static int upgradeCost = 1000; //Cost it takes to upgrade castle
  private int gold; //The amount of gold your castle stores that you use to recruit troops
  private int level; //Level of castle (Max Lvl is 3)
  public Castle(String team, int[] coords){
    super("Castle", 3000, 50, 50, team, coords);
    //Castle has 3000HP, 50ATK, 50 RNG, 30 DEF, 0 Penetration Dmg, 0 SPD
  }
  
  //Setters
  public void setGold(int gold){
    this.gold = gold;
  }
  public void setLevel(int level){
    this.level = level;
  }
  public static void setUpgradeCost(int cost){
    upgradeCost = cost;
  }
  //Getters
  public int getGold(){
    return gold;
  }
  public int getLevel(){
    return level;
  }
  public static int getUpgradeCost(){
    return upgradeCost;
  }
  
  public void upgrade(){
    if (checkUpgrade()){
      gold -= upgradeCost;
      level++;
      upgradeCost += 1000;
    }
  }
    
  
  //Checks if you can upgrade the Castle
  public boolean checkUpgrade(){
    //If you're already maxed
    if (level == 3){
      //Cannot upgrade since already max level
      return false;
    }
    //If you're lvl 2 and have enough gold
    else if (level == 2 && gold >= upgradeCost){
      //Requirements met for upgrade
      return true;
    }
    //If you're lvl 1 and have enough gold
    else if (level == 1 && gold >= upgradeCost){
      //Requirements met for upgrade
      return true;
    }
    //If you do not have enough gold
    else{
      //Not enough gold to upgrade
      return false;
    }
  }
  
  //Method that recruits troops, will create a new object and return it. It will then be added into an arraylist
  public Combatant recruit(Terrain[][] boardState, String troop){
    int[] coordinates = super.getCoords(); //Stores coordinates of building
    Terrain terrain = boardState[coordinates[0]][coordinates[1]]; //Stores terrain of castle
    //If there is a troop on the tile, return nothing since there is no space to spawn a troop
    if (!(terrain.getTroop().equals(null))){
      return null;
    }
    //If you want to recruit footmen and you have enough gold
    else if (troop.equals("Footmen") && gold >= Footman.getFootmenPrice()){
      gold -= Footman.getFootmenPrice(); //Reduces gold count
      return new Footman(super.getTeam(), coordinates); //Spawns troop
    }
    //If you want to recruit archer and you have enough gold
    else if (troop.equals("Archer") && gold >= Archer.getArcherPrice()){
      gold -= Archer.getArcherPrice(); //Reduces gold count
      return new Archer(super.getTeam(), coordinates); //Spawns troop
    }
    //If you want to recruit knight, have enough gold and castle is at least lvl 2
    else if (troop.equals("Knight") && gold >= Knight.getKnightPrice() && level >= 2){
      gold -= Knight.getKnightPrice(); //Reduces gold count
      return new Knight(super.getTeam(), coordinates); //Spawns troop
    }
    //If you want to recruit crossbowmen, have enough gold and castle is at least lvl 2
    else if (troop.equals("CrossbowMen") && gold >= CrossbowMen.getCrossbowMenPrice() && level >= 2){
      gold -= CrossbowMen.getCrossbowMenPrice(); //Reduces gold count
      return new CrossbowMen(super.getTeam(), coordinates); //Spawns troop
    }
    //If you want to recruit calvary, have enough gold and castle is lvl 3 (max)
    else if (troop.equals("Calvary") && gold >= Calvary.getCalvaryPrice() && level == 3){
      gold -= Calvary.getCalvaryPrice(); //Reduces gold count
      return new Calvary(super.getTeam(), coordinates); //Spawns troop
    }
    //If you do not have enough gold or castle is not high level enough
    else{
      return null;
    }
  }
}

  
  
  
  
