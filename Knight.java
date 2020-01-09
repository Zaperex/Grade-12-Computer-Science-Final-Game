/* Name: Sean Hua and Frank Kong
 * Date: December 17, 2019
 * Description: Subclass for the Knight troop (exteds from the combatant class)
 */

public class Knight extends Troop {
  private static int knightPrice = 200;
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
}