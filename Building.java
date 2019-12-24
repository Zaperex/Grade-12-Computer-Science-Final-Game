/* Name: Frank Kong
 * Date: December 17, 2019
 * Description: Building class
 */

public class Building extends Combatant{

  private int[] coords = new int[2];
  
  public Building(String name, double health, double attack, double range, boolean team){
    super(name, health, attack, range, 50, 0, team, 0, 0);
    //Buildings all have 50 defense
  }
  
}
