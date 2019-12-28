/* Name: Frank Kong
 * Date: December 17, 2019
 * Description: Building class
 */

public class Building extends Combatant{
  
  public Building(String name, double health, double attack, double range, boolean team, int[] coords){
    super(name, health, attack, range, 50, 0, team, 0, 0, coords);
    //Buildings all have 50 defense
  }
  
}
  