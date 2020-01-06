/* Name: Frank Kong
 * Date: January 5, 2020
 * Description: Troop class that includes all troop movements and actions.
 */
import java.util.*;

public class Troop extends Combatant{
  //Fields
  private boolean action = true; //Boolean for if the troop has not moved or not
  private int moveDistance; //How many tiles combatant can move
  
  public Troop(String name, double health, double attack, double range, double defense, double penetration,
               boolean team, double speed, int moveDistance, int[] coords){
    super(name, health, attack, range, defense, penetration, team, speed, coords);
    this.moveDistance = moveDistance;
  }
  
  //Setters
  public void setAction(boolean action){
    this.action = action;
  }
  //Getters
  public boolean getAction(){
    return action;
  }
  
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
