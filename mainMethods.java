/* Name: Frank Kong
 * Date: January 14, 2020
 * Description: Class to store methods for main
 */
import java.util.*;
import java.io.*;

public class mainMethods{

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
          ((Castle)terrain[i][j].getBuilding()).setLevel(1); //Initializes level of Castle
        }
        else if (x == 6 && y == 6){
          //Stores P1 Castle in Terrain
          terrain[i][j].setBuilding(new Castle("P1", coords));
          //Stores P2 Castle in P2 Building ArrayList
          p1Buildings.add(terrain[i][j].getBuilding()); 
          ((Castle)terrain[i][j].getBuilding()).setGold(150); //Initializes amount of gold player starts with
          ((Castle)terrain[i][j].getBuilding()).setLevel(1); //Initializes level of Castle
        }
        else if (x == 3 && y == 3){
          //Stores goldmine in terrain
          terrain[i][j].setBuilding(new GoldMine("None", coords));
          //Stores gold mine in unclaimed buildings arraylist
          unclaimedBuildings.add(terrain[i][j].getBuilding());
        }
        else if (x == 2 && y == 6){
          //Stores goldmine in terrain
          terrain[i][j].setBuilding(new GoldMine("None", coords));
          //Stores gold mine in unclaimed buildings arraylist
          unclaimedBuildings.add(terrain[i][j].getBuilding());
        }
        else if (x == 4 && y == 0){
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
  
  public static String loadGame(String saveFolderPath, Terrain[][] terrain, ArrayList<Troop> p1Troops, ArrayList<Troop> p2Troops, ArrayList<Building> p1Buildings,
                              ArrayList<Building> p2Buildings, ArrayList<Building> unclaimedBuildings) throws IOException{
    String playerTurn = "P1"; 
    try{
      File saveFolder = new File(saveFolderPath); //Creates new file using directory path
      if (saveFolder.isDirectory()){ //Checks if file is a directory (folder)
        //Can also use directory.listFiles() to create a File[] array
        String[] fileNames = saveFolder.list(); //Stores the name of all files inside directory into a 2D string array
        if (saveFolder.length() > 0){ //There will only be 5 saved files but will still check if directory is empty
          SaveGame.loadTroops(terrain, p1Troops, fileNames[0]); //Stores info into p1Troops array
          SaveGame.loadTroops(terrain, p2Troops, fileNames[1]); //Stores info into p2Troops array
          SaveGame.loadBuildings(terrain, p1Buildings, fileNames[2]); //Stores info into p1Buildings array
          SaveGame.loadBuildings(terrain, p2Buildings, fileNames[3]); //Stores info into p2Buildings array
          playerTurn = SaveGame.load(terrain, unclaimedBuildings, fileNames[4]); //Stores info into unclaimedBuildings and returns who's turn it is
          //Adds troops and buildings into their respective terrain tiles
          fillTroopTerrain(terrain, p1Troops);
          fillTroopTerrain(terrain, p2Troops);
          fillBuildingTerrain(terrain, p1Buildings);
          fillBuildingTerrain(terrain, p2Buildings);
          fillBuildingTerrain(terrain, unclaimedBuildings);
          return playerTurn; //Returns the turn of the player when the game was saved
        }
        else{
          p1Troops.clear();
          p2Troops.clear();
          p1Buildings.clear();
          p2Buildings.clear();
          unclaimedBuildings.clear();
          //If files are not there, then start a new game
          newGame(terrain, p1Troops, p2Troops, p1Buildings, p2Buildings, unclaimedBuildings);
          return playerTurn; //Returns player 1 as default
        }
      }
    }
    catch (IOException e){
      System.out.println("An IOException has occured");
    }
    finally{
      return playerTurn;
    }
  }
  //Method that helps recruit the troops and place them in the tile where they are recruited
  public static boolean recruitTroops(Terrain[][] terrain, String troopName, Castle castle){
    int[] coords = castle.getCoords(); //Gets the coordinates of the tile
    Troop troop = castle.recruit(terrain, troopName);
    //If troop recruitment was unsuccessful, return false
    if (troop == null){
      return false;
    }
    //Else, put the troop into the terrain's troop field
    else{
      terrain[coords[0]][coords[1]].setTroop(troop);
      return true;
    }
  }
  //Method that makes troop claim an unclaimed gold mine or building
  public static void claimBuilding(Terrain[][] terrain, ArrayList<Building> unclaimedBuildings, ArrayList<Building> playerBuildings, Troop troop){
    //Stores coords
    int x = troop.getCoords()[0];
    int y = troop.getCoords()[1];
    int[] coords = {x, y}; 
    
    for (int i = 0; i < unclaimedBuildings.size(); i++){
      //If Building in arraylist is equal to that of the building in the terrain object
      if (unclaimedBuildings.get(i).equals(terrain[x][y].getBuilding())){
        playerBuildings.add(unclaimedBuildings.get(i)); //Adds building to player's building arraylist
        troop.captureBuilding(unclaimedBuildings.get(i)); //Changes team of building to troop's
        unclaimedBuildings.remove(unclaimedBuildings.get(i)); //Removes building from the unclaimed building arraylist
      }  
    }
  }
    
  //Method that changes the turn and updates everything for the turn of the player
  public static String turnFlip(String playerTurn, Terrain[][] terrain, ArrayList<Troop> p1Troops, ArrayList<Troop> p2Troops, ArrayList<Building> p1Buildings,
                              ArrayList<Building> p2Buildings, ArrayList<Building> unclaimedBuildings){
    String newTurn;
    //If it's currently P1, 
    if (playerTurn.equals("P1")){
      newTurn = "P2";
      Castle castle = ((Castle)(terrain[6][6].getBuilding()));
      for (int i = 0; i < p1Troops.size(); i++){
        p1Troops.get(i).setAction(true); //Sets the action of each troop to true
      }
      //Adds gold income equal to the number of buildings times 100
      castle.setGold(castle.getGold() + p2Buildings.size()*100);
    }
    else {
      newTurn = "P1";
      Castle castle = ((Castle)(terrain[0][0].getBuilding()));
      for (int i = 0; i < p2Troops.size(); i++){
        p2Troops.get(i).setAction(true); //Sets the action of each troop to true
      }
      //Adds gold income equal to the number of buildings times 100
      castle.setGold(castle.getGold() + p1Buildings.size()*100);
    }
      
      return newTurn;
  }
  
  //Method that saves the state of the game
  public static void saveGame(String playerTurn, ArrayList<Troop> p1Troops, ArrayList<Troop> p2Troops, ArrayList<Building> p1Buildings,
                                ArrayList<Building> p2Buildings, ArrayList<Building> unclaimedBuildings) throws IOException{
    try{
      SaveGame.saveTroops(p1Troops, "P1");
      SaveGame.saveTroops(p2Troops, "P2");
      SaveGame.saveBuildings(p1Buildings, "P1");
      SaveGame.saveBuildings(p2Buildings, "P2");
      SaveGame.save(unclaimedBuildings, playerTurn, "None");
    }
    catch (Exception e){
      System.out.println("An exception has occured during saving");
    }
  }
    
}

