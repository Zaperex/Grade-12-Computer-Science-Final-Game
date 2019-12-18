/* Name: Frank Kong
 * Date: December 17, 2019
 * Description: Building class
 */

public class Building extends Combatant{
  private double defense;
  private int[] coords = new int[2];
  
  public Building(String name, double health, double attack, double range, boolean team, double defense){
    super(name, health, attack, range, team, 0, 0);
    this.defense = defense;
  }
  
}
  