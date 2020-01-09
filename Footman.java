// Name: Sean Hua and Frank Kong
// Date: 12/19/2019
// Description: Subcclass of the commbatant class for the footman troop
class Footman extends Troop{
  private static int footmenPrice = 50;
  private String attackName = "Jab";
  private String specialAttack = "Rocket Thrust";
  private double baseDefense = 0; //Base Defense
  
  public Footman(String team, int[] coords){
    super("Footman", 400, 60, 5, 0, 0, team, 10, 3, coords, "Footman.png");
    //Footmen have 400 HP, 60ATK, 5 RNG, 10 SPD, 3 TILE MVMT
  }
  //Setters
  public static void setFootMenPrice(int price){
    footmenPrice = price;
  }
  //Getters
  public static int getFootmenPrice(){
    return footmenPrice;
  }
  public String getSpecialAttack(){
    return specialAttack;
  }
  public String getAttackName(){
    return attackName;
  }
  //Resets Defense Stat
  public void resetDefense(){
    setDefense(baseDefense);
  }
  
  //Special attack for footmen
  public double specialAttack(Combatant opponent){
    double damage = attack*3;
    //If opponent is a troop
    if (opponent instanceof Troop){
      if (((Troop)(opponent)).getDodge() == true){
        //If opponent dodges, attack will "miss" and deal no damage
        return 0;
      }
    }
    //Else just return damage
    return damage;
  }
  
}