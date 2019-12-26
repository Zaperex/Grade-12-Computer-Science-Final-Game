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
  private int[] coords = new int[2];
  //Contructor
  public Terrain (Building building, Combatant troop, String type, double distanceRequired, int[] coords) {
    this.building = building;
    this.troop = troop;
    this.type = type;
    this.distanceRequired = distanceRequired;
    this.coords = coords;
  }
  //Getters
  public int[] getCoords(){
    return coords;
  }
  public Building getBuilding(){
    return building;
  }
  public Combatant getTroop () {
    return troop;
  }
  public String getType () {
    return type;
  }
  public double getDistanceRequired () {
    return distanceRequired;
  }
  //Setters
  public void setCoords(){
    this.coords = coords;
  }
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
