/* Name: Sean Hua
 * Date: 2019/12/21 
 * Description: Terrain class that is used for the game's map
 * */

public class Terrain {
  //Fields
  private Building building;
  private Troop troop;
  private String type;
  private double distanceRequired;
  private int[] coords = new int[2];
  //Contructor
  public Terrain (String type, int[] coords) {
    this.type = type;
    //Preset Stats for Swamp
    if (type.equals("Swamp")){
      this.distanceRequired = 2;
    }
    //Preset Stats for Mountains
    else if (type.equals("Mountains")){
      this.distanceRequired = 1000000;
    }
    //Preset Stats for all other tiles
    else{
      this.distanceRequired = 1;
    }
    this.coords = coords;
  }
  //Getters
  public int[] getCoords(){
    return coords;
  }
  public Building getBuilding(){
    return building;
  }
  public Troop getTroop () {
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
  public void setTroop (Troop troop) {
    this.troop = troop;
  }
  public void setType (String type) {
    this.type = type;
  }
  public void setDistanceRequired (double distanceRequired) {
    this.distanceRequired = distanceRequired;
  }
  
}