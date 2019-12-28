/* Name: Frank Kong
 * Date: December 17, 2019
 * Description: Building class
 */

public class Building extends Combatant{
  int goldProduction = 100; //All buildings generate 100 gold per turn 
  public Building(String name, double health, double attack, double range, boolean team, int[] coords){
    super(name, health, attack, range, 30, 0, team, 0, 0, coords);
    //Buildings all have 30 defense
  }
 
}
  