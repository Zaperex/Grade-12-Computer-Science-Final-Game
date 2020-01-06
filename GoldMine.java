/* Name: Frank Kong
 * Date: January 6, 2020
 * Description: Gold Mine Class for gold mines
 */

public class GoldMine extends Building{
  //Constructor
  public GoldMine(boolean team, int[] coordinates){
    super("Gold Mine", 1500, 10, 5, team, coordinates);
    //Gold Mines have 1500HP, 10 ATK, 5 RNG, 30 DEF, 0 Penetration DMG, 0 SPD
  }
}