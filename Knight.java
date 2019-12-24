/* Name : Sean Hua
 * Date: December 17, 2019
 * Description: Subclass for the Knight troop (exteds from the combatant class)
 */

public class Knight extends Combatant {
  private double armour;
  
  //Constructor
  public Knight(boolean team) {
    super("Knight", 500, 90, 1, 35, 0, team, 10, 3); //Presets stats for Knight
    //Knights have 500HP, 90ATK, 1 RNG, 35 DEF, 10 SPD, 3 TILE MVMT
  }
}
