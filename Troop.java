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
               String team, double speed, int moveDistance, int[] coords, String imageFileName){
    super(name, health, attack, range, defense, penetration, team, speed, coords, imageFileName);
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
  public boolean move(int x, int y){
    //Calculates distance of travel 
    int distance = Math.abs(x - coords[1]) + Math.abs(y - coords[0]);
    //Checks if coordinates goes out of bounds of the 7x7 board
    if (x < 0 || x >= 7 || y < 0 || y >= 7){
      return false;
    }
    
    //If distance is within the move limit
    else if (distance <= moveDistance){
      return true;
    }
    
    //If not within move distance
    else{
      return false;
    }
  }
  //Method that makes the actual move after user confirms they want to move there
  public void actualMove(int x, int y){  
    //Method is called after confirmation prompt returns true from GUI
    coords[0] = y;
    coords[1] = x;
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
    for (int x = 0; x < boardState.length; x++){
      for (int y = 0; y < boardState[x].length; y++){
        //Temporarily stores current tile in terrain
        terrain = boardState[y][x];
        //If move is within range and valid
        if (move(x, y) == true){
          
          //If tile already has a troop
          if (terrain.getTroop() != null){
            //Just continues on and doesn't add the coordinates into the arraylist
              continue;
          }
          
          
          //If tile has a building. 
          else if (terrain.getBuilding() != null){
            //Return true if it's a friend building
            if (terrain.getBuilding().getTeam() == getTeam()){
              //Stores coordinates of the terrain tile
              possibleMoves.add(terrain.getCoords());
            }
            //Returns true if it's an unclaimed building
            else if (terrain.getBuilding().getTeam().equals("None")){
              possibleMoves.add(terrain.getCoords());
            }
            //Returns false if it's an enemy building
            else{
               //Just continues on and doesn't add the coordinates into the arraylist
              continue;
            }
          }
          else{
            //Stores coordinates of the terrain tile
            possibleMoves.add(terrain.getCoords());
          }
        }
      }
    }
    return possibleMoves;
  }
  
  //Method that checks if tile is available to attack
  public boolean checkAttacks(int x, int y){
    //Calculates distance of travel 
    int distance = Math.abs(x - coords[1]) + Math.abs(y - coords[0]);
    //Checks if coordinates goes out of bounds of the 7x7 board
    if (x < 0 || x >= 7 || y < 0 || y >= 7){
      return false;
    }
    //If distance is within the attack range of 1
    else if (distance == 1){
      return true;
    }
    
    //If not within move distance
    else{
      return false;
    }
  }
  //Method that finds all possible tiles that troop can attack
  public ArrayList<int[]> findAttacks(Terrain[][] boardState){
    //Arraylist that stores all possible attacks
    ArrayList<int[]> possibleAttacks = new ArrayList<int[]>();
    Terrain terrain;
    
    for (int x = 0; x < boardState.length; x++){
      for (int y = 0; y < boardState[x].length; y++){
        terrain = boardState[y][x]; //Temporarily stores terrain tile
        //If move is valid
        if (checkAttacks(x, y) == true){
          //If there's an troop
          if (terrain.getTroop() != null){
            //If troop is on your side
            if (terrain.getTroop().getTeam().equals(getTeam())){
              continue; //Ignore tile and continues on
            }
            //Else if troop is on opponent's side
            else{
              //Add coordinates as a possible attack target
              possibleAttacks.add(terrain.getCoords());
            }
          }
          else if (terrain.getBuilding() != null){
            //If building is on your side
            if (terrain.getBuilding().getTeam().equals(getTeam())){
              continue; //Ignore tile and continues on
            }
            //If building is unclaimed
            else if (terrain.getBuilding().getTeam().equals("None")){
              continue; //Ignore tile and continues on
            }
            else{
              //Add coordinates as a possible attack targe
              possibleAttacks.add(terrain.getCoords());
            }
          }
        }
      }
    }
    return possibleAttacks;
  }
              
              
              
              
              
              
              
              
              
              
              
}