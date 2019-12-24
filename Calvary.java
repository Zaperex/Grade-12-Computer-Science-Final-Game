/* Name : Sean Hua
 * Date: December 17, 2019
 * Description: Subclass for the calvary troop (extends from the combatant class)
 */

public class Calvary extends Combantant {
  //Constructor
  public Calvary (boolean team) {
    super(600, 100, 5, 50, 10, team, 50, 6);
    //Calvary has 600HP, 100 ATK, 5 RNG, 50 DEF, 10 PENETRATE, 50 SPD, 6 TILE MVMT
  }
}
  
