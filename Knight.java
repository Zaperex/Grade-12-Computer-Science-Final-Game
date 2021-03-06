/* Name: Sean Hua and Frank Kong
 * Date: December 17, 2019
 * Description: Subclass for the Knight troop (exteds from the combatant class)
 */

public class Knight extends Troop {
  private static int knightPrice = 200;
  private String attackName = "Slash";
  private String specialAttack = "Shield Bash";
  private double baseDefense = 35; //Base Defense
  //Constructor
  public Knight(String team, int[] coords) {
    super("Knight", 500, 80, 5, 35, 35, 400, team, 2, coords, "Knight.png"); //Presets stats for Knight
    //Knights have 400HP, 90ATK, 5 RNG, 35 DEF, 35 Base Def, 400 Max HP, 2 TILE MVMT
  }
  //Setters
  public static void setKnightPrice(int price){
    knightPrice = price;
  }
  //Getters
  public static int getKnightPrice(){
    return knightPrice;
  }
  public String getSpecialAttack(){
    return specialAttack;
  }
  public String getAttackName(){
    return attackName;
  }
  
  //Special Attack for Knight
  public double specialAttack(Combatant opponent){
    //Sets special meter back to 0
    setSpecialMeter(0);
    double damage = attack*3;
    //If opponent is a troop
    if (opponent instanceof Troop){
      //If opponent dodges
      if (((Troop)(opponent)).getDodge() == true){
        return 0; //Returns no damage
      }
    }
    //Stuns opponent 
    opponent.setStun(true); //(BE SURE TO KEEP TRACK OF NUMBER OF TURNS STUNNED AND RESET BOOLEAN TO FALSE AFTER 1 TURN)
    return damage;
  }
  
}