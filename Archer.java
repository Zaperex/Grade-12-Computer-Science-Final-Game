/* Name : Sean Hua
 * Date: December 17, 2019
 * Description: Subclass for the archer troop (exteds from the combatant class)
 */

public class Archer extends Troop {
  private static int archerPrice = 50;
  //Constructor
  public Archer (String team, int[] coords) {
    super("Archer", 300, 45, 40, 0, 0, team, 10, 3, coords);
    //Archers have 300 HP, 45 ATK, 40 RNG, 10 SPD, 3 TILE MVMT
  }
  //Setters
  public static void setArcherPrice(int price){
    archerPrice = price;
  }
  //Getters
  public static int getArcherPrice(){
    return archerPrice;
  }
  
  
}