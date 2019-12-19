/* Name : Sean Hua
 * Date: December 17, 2019
 * Description: Subclass for the Knight troop (exteds from the combatant class)
 */

public class Knight extends Combatant {
  private double armour;
  
  //Constructor
  public Knight(boolean team, double armour) {
    super(500, 90, 1, team, 10, 3); //Presets stats for Knight
    this.armour = 35;
  }
  
  //Get method
  public double getArmour () {
    return this.armour;
  }
  
  //Set method
  public void setArmour (double armour) {
    this.armour = armour;
  }
}