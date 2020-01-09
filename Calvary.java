/* Name: Sean Hua and Frank Kong
 * Date: December 17, 2019
 * Description: Subclass for the calvary troop (extends from the combatant class)
 */

public class Calvary extends Troop{
  public static int calvaryPrice = 500;
  //Constructor
  public Calvary (String team, int[] coords) {
    super("Calvary", 500, 80, 5, 50, 10, team, 15, 6, coords);
    //Calvary has 500HP, 80 ATK, 5 RNG, 50 DEF, 10 PENETRATE, 15 SPD, 6 TILE MVMT
  }
  //Setters
  public static void setCalvaryPrice(int price){
    calvaryPrice = price;
  }
  //Getters
  public static int getCalvaryPrice(){
    return calvaryPrice;
  }
}
  
