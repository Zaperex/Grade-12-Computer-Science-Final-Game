/* Name: Frank Kong
 * Date: December 17, 2019
 * Description: Combatant class (parent class) for all the combatants: Includes buildings and troops
 *              Includes many helper methods that allow combatant to perform actions such as move, capture, etc.
 */
import java.util.*;

public class Combatant{
  //Fields
  private String name; //Name of the combatant
  private double health; //Health
  private double attack; //Attack
  private double range; //Attack Range
  private boolean team; //Team of combatant
  private double speed; //Speed of combatant
  private int[] coords = new int[2]; //Coordinates of combatant
  private int moveDistance; //How many tiles combatant can move
  private double defense; //Defense of combatant (Reduces damage taken)
  private double penetration; //Penetration damage of combatant (Ignore defense)
  
  //Constructor
  //REMEMBER TO ADD COORDINATES TO CONSTRUCTOR
  public Combatant(String name, double health, double attack, double range, double defense, double penetration,
                   boolean team, double speed, int moveDistance, int[] coords){
    this.name = name;
    this.health = health;
    this.attack = attack;
    this.range = range;
    this.defense = defense;
    this.penetration = penetration;
    this.team = team;
    this.speed = speed;
    this.moveDistance = moveDistance;
    this.coords = coords;
  }
  
  //Setters
  public void setSpeed(double speed){
    this.speed = speed;
  }
  public void setHealth(double health){
    this.health = health;
  }
  public void setAttack(double attack){
    this.attack = attack;
  }
  public void setRange(double range){
    this.range = range;
  }
  public void setTeam(boolean team){
    this.team = team;
  }
  public void setCoords(int[] coords){
    this.coords = coords;
  }
  
  //Getters
  public String getName(){
    return name;
  }
  public double getHealth(){
    return health;
  }
  public double getAttack(){
    return attack;
  }
  public double getRange(){
    return range;
  }
  public double getSpeed(){
    return speed;
  }
  public boolean getTeam(){
    return team;
  }
  public double getDefense(){
    return defense;
  }
  public double getPenetration(){
    return penetration;
  }
  public int[] getCoords(){
    return coords;
  }
  
  //Implement when board is added
  
  //Helper Methods
  //Move method that checks if move is valid
  public boolean move(int x, int y, Terrain terrain){
    //Calculates distance of travel
    int distance = Math.abs(x - coords[0]) + Math.abs(y - coords[1]);
    //Checks if coordinates goes out of bounds of the 7x7 board
    if (x < 0 || x >= 7 || y < 0 || y >= 7){
      return false;
    }
    //If distance is within the move limit
    else if (distance <= moveDistance){
      return true;
    }
    //If tile already has a troop from your side
    else if (terrain.getTroop().getTeam() == getTeam()){
      return false;
    }
    //If not within move distance
    else{
      return false;
    }
  }
  //Method that makes the actual move after user confirms they want to move there
  public void actualMove(int x, int y){  
    //Method is called after confirmation prompt returns true from GUI
    coords[0] = x;
    coords[1] = y;
    //If tile already has an enemy troop on it, combat will be intiated in the main method
    //then winner will be the one who gets placed in the combatant slot of the terrain tile
  }
  
  //Method is called when capturing an unclaimed building (Null Team)
  public void captureBuilding(Terrain terrain){
    terrain.getBuilding().setTeam(team); //Sets the building team to that of the capturing troop
  }
  
  //Method that stores coordinates of all possible moves in an arraylist and returns it
  public ArrayList<int[]> findMoves(Terrain[][] boardState){
    //Arraylist that stores all possible coordinates combatant can move to
    ArrayList<int[]> possibleMoves = new ArrayList<int[]>();
    Terrain terrain;
    //Checks all tiles for possible moves
    for (int i = 0; i < 7; i++){
      for (int j = 0; j < 7; j++){
        //Temporarily stores current tile in terrain
        terrain = boardState[i][j];
        //If move is within range and valid
        if (move(i,j, terrain) == true){
          //Stores coordinates of the terrain tile
          possibleMoves.add(terrain.getCoords());
        }
      }
    }
    return possibleMoves;
  }
}

