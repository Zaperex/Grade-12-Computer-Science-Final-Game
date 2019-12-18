/* Name: Frank Kong
 * Date: December 18, 2019
 * Description: CrossbowMen class that includes a penetration damage that ignores armour or defenses.
 */


public class CrossbowMen extends Combatant{
  private double penetration;
  private double projectileSpeed;
  public CrossbowMen(boolean team){
    super("CrossbowMen", 500, 75, 60, team, 10, 4);
    projectileSpeed = 300;
  }
  
  public void setPenetration(double penetration){
    this.penetration = penetration;
  }
  
  public double getPenetration(){
    return penetration;
  }
}