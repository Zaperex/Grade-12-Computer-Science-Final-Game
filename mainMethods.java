/* Name: Frank Kong
 * Date: January 14, 2020
 * Description: Class to store methods for main
 */
import java.util.*;
import java.io.*;

public class mainMethods{
  public static Terrain[][] terrain = new Terrain[7][7];
  public static ArrayList<Troop> p1Troops = new ArrayList<Troop>();
  public static ArrayList<Troop> p2Troops = new ArrayList<Troop>();
  public static ArrayList<Building> p1Buildings = new ArrayList<Building>();
  public static ArrayList<Building> p2Buildings = new ArrayList<Building>();
  public static ArrayList<Building> unclaimedBuildings = new ArrayList<Building>();
  public static String turn;
  
  static String currentDirPath = System.getProperty("user.dir"); //Finds the path of the working directory
  static String saveFolderPath = currentDirPath + "\\save"; //Stores the path of the main folder
  
  public static void newGame(Terrain[][] terrain, ArrayList<Troop> p1Troops, ArrayList<Troop> p2Troops, ArrayList<Building> p1Buildings,
                             ArrayList<Building> p2Buildings, ArrayList<Building> unclaimedBuildings){
    for (int i = 0; i < terrain.length; i++){
      for (int j = 0; j < terrain[i].length; j++){
        //Stores coordinates
        int x = i;
        int y = j;
        int[] coords = {x, y};
        terrain[i][j] = new Terrain("Plains", coords);
        
        if (x == 0 && y == 0){
          //Stores P2 Castle in Terrain
          terrain[i][j].setBuilding(new Castle("P2", coords));
          //Stores P2 Castle in P2 Building ArrayList
          p2Buildings.add(terrain[i][j].getBuilding()); 
          ((Castle)terrain[i][j].getBuilding()).setGold(150); //Initializes amount of gold player starts with
        }
        if (x == 6 && y == 6){
          //Stores P1 Castle in Terrain
          terrain[i][j].setBuilding(new Castle("P1", coords));
          //Stores P2 Castle in P2 Building ArrayList
          p1Buildings.add(terrain[i][j].getBuilding()); 
          ((Castle)terrain[i][j].getBuilding()).setGold(150); //Initializes amount of gold player starts with
        }
        if (x == 3 && y == 3){
          //Stores goldmine in terrain
          terrain[i][j].setBuilding(new GoldMine("None", coords));
          //Stores gold mine in unclaimed buildings arraylist
          unclaimedBuildings.add(terrain[i][j].getBuilding());
        }
        if (x == 0 && y == 4){
          //Stores goldmine in terrain
          terrain[i][j].setBuilding(new GoldMine("None", coords));
          //Stores gold mine in unclaimed buildings arraylist
          unclaimedBuildings.add(terrain[i][j].getBuilding());
        }
        if (x == 4 && y == 0){
          //Stores goldmine in terrain
          terrain[i][j].setBuilding(new GoldMine("None", coords));
          //Stores gold mine in unclaimed buildings arraylist
          unclaimedBuildings.add(terrain[i][j].getBuilding());
        }
      }
    }
  }
  
  
  public static void fillTroopTerrain(Terrain[][] terrain, ArrayList<Troop> troops){
    //Store troops into terrain arrays
    int x;
    int y;
    
    //Stores all troops into their respective terrain tiles
    for (int i = 0; i < troops.size(); i++){
      x = troops.get(i).getCoords()[0];
      y = troops.get(i).getCoords()[1];
      
      terrain[x][y].setTroop(troops.get(i));
    }
  }
  public static void fillBuildingTerrain(Terrain[][] terrain, ArrayList<Building> buildings){
    //Stores buildings into terrain arrays
    int x;
    int y;
    //Stores all troops into their respective terrain tiles
    for (int i = 0; i < buildings.size(); i++){
      x = buildings.get(i).getCoords()[0];
      y = buildings.get(i).getCoords()[1];
      
      terrain[x][y].setBuilding(buildings.get(i));
    }
  }
  
  public static void loadGame(String saveFolderPath, Terrain[][] terrain, ArrayList<Troop> p1Troops, ArrayList<Troop> p2Troops, ArrayList<Building> p1Buildings,
                              ArrayList<Building> p2Buildings, ArrayList<Building> unclaimedBuildings) throws IOException{
    File saveFolder = new File(saveFolderPath); //Creates new file using directory path
    if (saveFolder.isDirectory()){ //Checks if file is a directory (folder)
      //Can also use directory.listFiles() to create a File[] array
      String[] fileNames = saveFolder.list(); //Stores the name of all files inside directory into a 2D string array
      if (saveFolder.length() > 0){ //There will only be 5 saved files but will still check if directory is empty
        SaveGame.loadTroops(terrain, p1Troops, fileNames[0]); //Stores info into p1Troops array
        SaveGame.loadTroops(terrain, p2Troops, fileNames[1]); //Stores info into p2Troops array
        SaveGame.loadBuildings(terrain, p1Buildings, fileNames[2]); //Stores info into p1Buildings array
        SaveGame.loadBuildings(terrain, p2Buildings, fileNames[3]); //Stores info into p2Buildings array
        turn = SaveGame.load(terrain, unclaimedBuildings, fileNames[4]); //Stores info into unclaimedBuildings and returns who's turn it is
        //Adds troops and buildings into their respective terrain tiles
        fillTroopTerrain(terrain, p1Troops);
        fillTroopTerrain(terrain, p2Troops);
        fillBuildingTerrain(terrain, p1Buildings);
        fillBuildingTerrain(terrain, p2Buildings);
        fillBuildingTerrain(terrain, unclaimedBuildings);
      }
      else{
        p1Troops.clear();
        p2Troops.clear();
        p1Buildings.clear();
        p2Buildings.clear();
        unclaimedBuildings.clear();
        //If files are not there, then start a new game
        newGame(terrain, p1Troops, p2Troops, p1Buildings, p2Buildings, unclaimedBuildings);
      }
    }
  }
  //Method that helps recruit the troops and place them in the tile where they are recruited
  public static boolean recruitTroops(Terrain[][] terrain, String troopName, Castle castle){
    int[] coords = castle.getCoords(); //Gets the coordinates of the tile
    Troop troop = castle.recruit(terrain, troopName);
    //If troop recruitment was unsuccessful, return false
    if (troop.equals(null)){
      return false;
    }
    //Else, put the troop into the terrain's troop field
    else{
      terrain[coords[0]][coords[1]].setTroop(troop);
      return true;
    }
  }
}

