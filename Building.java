/* Name: Frank Kong
 * Date: December 17, 2019
 * Description: Building class
 */

public class Building extends Combatant{
  private int goldProduction = 100; //All buildings generate 100 gold per turn 
  private boolean barrier = false; //Barrier boolean

  public Building(String name, double health, double attack, double range, double maxHP, String team, int[] coords, String imageFileName){
    super(name, health, attack, range, 30, 30, maxHP, team, coords, imageFileName);
    //Buildings all have 30 defense
  }
  //Setter
  public void setBarrier(boolean barrier){
    this.barrier = barrier;
  }
  //Getter
  public int getGoldProduction(){
    return goldProduction;
  }
  public boolean getBarrier(){
    return barrier;
  }
  
  //Repair method
  public boolean repair(double goldBalance){
    //Repair will cost 40 gold to perform
    if (goldBalance >= 40){
      //If healing will make building go past max health
      if ((getHealth() + maxHP/10) > maxHP){
        setHealth(maxHP);
        return true; //Repair is successful so return true
      }
      else {
        //Heals 10% of building health
        setHealth(getHealth() + maxHP/10);
        return true; //Repair is successful so return true
      }
    }
    //If not enough gold, return false as repair is not successful
    return false;
  }
      
 
}
  