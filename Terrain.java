/* Name: Sean Hua
 * Date: 2019/12/21 
 * Description: Terrain class that is used for the game's map
 * */

public class Terrain {
  //Fields
  private Building building;
  private Combatant troop;
  private String type;
  private double distanceRequired;
  
  //Contructor
  public Terrain (Building building, Combatant troop, String type, double distanceRequired) {
    this.building = building;
    this.troop = troop;
    this.type = type;
    this.distanceRequired = distanceRequired;
  }
  //Getters
  public Building getBuilding () {
    return this.building;
  }
  public Combatant getTroop () {
    return this.troop;
  }
  public String getType () {
    return this.type;
  }
  public double getDistanceRequired () {
    return this.distanceRequired;
  }
  //Setters
  public void setBuilding (Building building) {
    this.building = building;
  }
  public void setTroop (Combatant troop) {
    this.troop = troop;
  }
  public void setType (String type) {
    this.type = type;
  }
  public void setDistanceRequired (double distanceRequired) {
    this.distanceRequired = distanceRequired;
  }
}
