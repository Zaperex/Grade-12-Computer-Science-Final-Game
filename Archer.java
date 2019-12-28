/* Name : Sean Hua
 * Date: December 17, 2019
 * Description: Subclass for the archer troop (exteds from the combatant class)
 */

public class Archer extends Combatant {
  
  //Constructor
  public Archer (boolean team, int[] coords) {
    super("Archer", 300, 45, 40, 0, 0, team, 25, 5, coords);
    //Archers have 300 HP, 45 ATK, 40 RNG, 25 SPD, 5 TILE MVMT
  }
}