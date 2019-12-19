/* Name : Sean Hua
 * Date: December 17, 2019
 * Description: Subclass for the calvary troop (exteds from the combatant class)
 */

public class Calvary extends Combantant {
  //Fields
  private boolean charge;
  private double armour;
  
  //Constructor
  public Calvary (double health, double attack, double range, boolean team, double speed, boolean charge, double armour) {
    super(600, 100, 5, team, 50);
    this.charge = true;
    this.armour = 50;
  }
  
  //Get methods
  public boolean getCharge () {
    return this.charge;
  }
  public double getArmour () {
    return this.armour;
  }
  
  //Set methods
  public void setCharge (boolean charge) {
    this.charge = charge;
  }
  public void setArmour (double armour) {
    this.armour = armour;
  }
}
  