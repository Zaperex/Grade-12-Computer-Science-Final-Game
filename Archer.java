/* Name : Sean Hua
 * Date: December 17, 2019
 * Description: Subclass for the archer troop (exteds from the combatant class)
 */

public class Archer extends Combatant {
  private double projectileSpeed;
  
  //Constructor
  public Archer (boolean team) {
    super(300, 45, 40, team, 30);
    this.projectileSpeed = 100;
  }
  
  //Get method
  public double getProjectileSpeed(){
    return projectileSpeed;
  }
}