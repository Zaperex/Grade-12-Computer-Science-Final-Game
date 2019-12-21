/* Name: Sean Hua
 * Date: 2019/12/21 
 * Description: Terrain class that is used for the game's map
 * */

public class Terrain {
  private Building building;
  private Combatant troop;
  private String type;
  private int distanceRequired;
  
  public Terrain (Building building, Combatant troop, String type, int distanceRequired) {
    this.building = building;
    this.troop = troop;
    this.type = type;
    this.distanceRequired = distanceRequired;
  }
  
  public Building getBuilding () {
    return this.building;
  }
  public Combatant getTroop () {
    return this.troop;
  }
  public String getType () {
    return this.type;
  }
  public int getDistanceRequired () {
    return this.distanceRequired;
  }
  
  public void setBuidling (Building building) {
    this.building = building;
  }
  public void setTroop (Combatant troop) {
    this.troop = troop;
  }
  public void setType (String type) {
    this.type = type;
  }
  public void setDistanceRequired (int distanceRequired) {
    this.distanceRequired = distanceRequired;
  }
}