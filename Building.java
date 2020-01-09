/* Name: Frank Kong
 * Date: December 17, 2019
 * Description: Building class
 */

public class Building extends Combatant{
  private int goldProduction = 100; //All buildings generate 100 gold per turn 
  public Building(String name, double health, double attack, double range, String team, int[] coords, String imageFileName){
    super(name, health, attack, range, 30, 0, team, 0, coords, imageFileName);
    //Buildings all have 30 defense
  }
  //Getter
  public int getGoldProduction(){
    return goldProduction;
  }
 
}