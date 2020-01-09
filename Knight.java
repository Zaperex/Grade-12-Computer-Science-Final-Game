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
    super("Knight", 500, 90, 1, 35, 0, team, 5, 2, coords); //Presets stats for Knight
    //Knights have 500HP, 90ATK, 1 RNG, 35 DEF, 5 SPD, 2 TILE MVMT
  }
  //Setters
  public static void setKnightPrice(int price){
    knightPrice = price;
  }
  //Getters
  public static int getKnightPrice(){
    return knightPrice;
  }
  //Resets Defense Stat
  public void resetDefense(){
    setDefense(baseDefense);
  }
  
  //Special Attack for Knight
  public double specialAttack(Combatant opponent){
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