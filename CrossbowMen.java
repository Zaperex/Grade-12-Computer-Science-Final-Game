/* Name: Frank Kong
 * Date: December 18, 2019
 * Description: CrossbowMen class
 */

public class CrossbowMen extends Combatant{
  private static int crossbowMenPrice = 200;
  public CrossbowMen(boolean team, int[] coords){
    super("CrossbowMen", 500, 75, 60, 5, 25, team, 10, 4, coords);
    //Crossbowmen have 500HP, 75DMG, 60 RNG, 5 DEF, 25 PENTRATE, 10 SPD, 4 TILE MVMT
  }
  //Setters
  public static void setCrossbowMenPrice(int price){
    crossbowMenPrice = price;
  }
  //Getters
  public static int getCrossbowMenPrice(){
    return crossbowMenPrice;
  }
}