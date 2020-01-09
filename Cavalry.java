/* Name: Sean Hua and Frank Kong
 * Date: December 17, 2019
 * Description: Subclass for the Cavalry troop (extends from the combatant class)
 */
public class Cavalry extends Troop{
  public static int cavalryPrice = 500;
  //Constructor
  public Cavalry(String team, int[] coords) {
    super("Cavalry", 500, 80, 5, 50, 10, team, 15, 6, coords);
    //Calvary has 500HP, 80 ATK, 5 RNG, 50 DEF, 10 PENETRATE, 15 SPD, 6 TILE MVMT
  }
  //Setters
  public static void setCavalryPrice(int price){
    cavalryPrice = price;
  }
  //Getters
  public static int getCavalryPrice(){
    return cavalryPrice;
  }
}
  
