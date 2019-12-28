/* Name : Sean Hua
 * Date: December 17, 2019
 * Description: Subclass for the calvary troop (extends from the combatant class)
 */

public class Calvary extends Combatant{
  public static int calvaryPrice = 500;
  //Constructor
  public Calvary (boolean team, int[] coords) {
    super("Calvary", 500, 80, 5, 50, 10, team, 50, 6, coords);
    //Calvary has 500HP, 80 ATK, 5 RNG, 50 DEF, 10 PENETRATE, 50 SPD, 6 TILE MVMT
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
  
