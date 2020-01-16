// Name: Sean Hua and Frank Kong
// Date: 12/19/2019
// Description: Subcclass of the commbatant class for the footman troop
class Footman extends Troop{
  private static int footmenPrice = 50;
  private String attackName = "Jab";
  private String specialAttack = "Rocket Thrust";
  
  public Footman(String team, int[] coords){
    super("Footman", 300, 50, 5, 20, 20, 200, team, 3, coords, "Footman.png");
    //Footmen have 200 HP, 50 ATK, 5 RNG, 20 DEF, 20 BASEDEF, 200 MAX HP, 3 TILE MVMT
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
  
  //Special attack for footmen
  public double specialAttack(Combatant opponent){
    //Sets special meter back to 0
    setSpecialMeter(0);
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