/* Name: Frank Kong
 * Date: December 30, 2019
 * Description: Just a class to place my save methods for now (Will not be included in final game)
 */
import java.io.*;
import java.util.*;

class SaveGame{
  //Player 1 info
  ArrayList<Troop> player1Troops; //Stores all player 1 troops
  ArrayList<Building> player1Buildings; //Stores all player 1 buildings
  //Player 2 info
  ArrayList<Troop> player2Troops; //Stores all player 2 troops
  ArrayList<Building> player2Buildings; //Stores all player 2 buildings
  //Other info
  ArrayList<Building> unclaimedBuildings; //Stores any unclaimed buildings
  boolean turn; //Stores whose turn it is
  
  public SaveGame(ArrayList<Troop> player1Troops, ArrayList<Building> player1Buildings, ArrayList<Troop> player2Troops,
                  ArrayList<Building> player2Buildings, ArrayList<Building> unclaimedBuildings, boolean turn){
    this.player1Troops = player1Troops;
    this.player1Buildings = player1Buildings;
    this.player2Troops = player2Troops;
    this.player2Buildings = player2Buildings;
    this.unclaimedBuildings = unclaimedBuildings;
    this.turn = turn;
  }
  //Troop Saving
  public void saveTroops(ArrayList<Troop> troops, boolean team) throws IOException{
    String fileName;
    if (team){
      fileName = "player_1_troop_save.csv";
    }
    else{
      fileName = "player_2_troop_save.csv";
    }
    PrintWriter troopWriter = new PrintWriter(new File(fileName));
    
    try{
      for (int i = 0; i < troops.size(); i++){
        //Temporary Object
        Troop tempTroop = troops.get(i);
        //Stores name of troop
        troopWriter.write(tempTroop.getName() + ",");
        //Stores health of troop
        troopWriter.write(tempTroop.getHealth() + ",");
        //Stores team of troop
        troopWriter.write(tempTroop.getTeam() + ",");
        //Stores whether troop can still move on the turn
        troopWriter.write(tempTroop.getAction() + ",");
        //Stores coordinates of troop
        troopWriter.write(tempTroop.getCoords()[0] + "," + tempTroop.getCoords()[1]);
      }
    }
    catch (Exception e){
      System.out.println("An Exception has occured in save method");
    }
    finally{
      troopWriter.close(); //Closes Writer
    }
  }
  //Building Save
  public void saveBuildings(ArrayList<Building> buildings, boolean team) throws IOException{
    String fileName;
    if (team){
      fileName = "player_1_building_save.csv";
    }
    else{
      fileName = "player_2_building_save.csv";
    }
    PrintWriter buildWriter = new PrintWriter(new File(fileName));
    
    try{
      for (int i = 0; i < buildings.size(); i++){
        //Temporary Object
        Building tempBuild = buildings.get(i);
        
        //Checks if Building Object is actually a Castle Object
        if (tempBuild instanceof Castle){
          //Stores name of building
          buildWriter.write(tempBuild.getName() + ",");
          //Stores health of building
          buildWriter.write(tempBuild.getHealth() + ",");
          //Stores team of building
          buildWriter.write(tempBuild.getTeam() + ",");
          //Stores level of Castle (Utilizes Casting to get parent class to utilize SubClass Methods)
          buildWriter.write(((Castle)tempBuild).getLevel() + ",");
          //Stores amount of gold player has (Utilizes Casting to get parent class to utilize SubClass Methods)
          buildWriter.write(((Castle)tempBuild).getGold() + ",");
          //Stores coordinates of building
          buildWriter.write(tempBuild.getCoords()[0] + "," + tempBuild.getCoords()[0] + "\n");
        }
        else{
          //Stores name of building
          buildWriter.write(tempBuild.getName() + ",");
          //Stores health of building
          buildWriter.write(tempBuild.getHealth() + ",");
          //Stores team of building
          buildWriter.write(tempBuild.getTeam() + ",");
          //Stores coordinates of building
          buildWriter.write(tempBuild.getCoords()[0] + "," + tempBuild.getCoords()[0] + "\n");
        }
      }
      
    }
    
    catch (Exception e){
      System.out.println("An Exception has occured in save method");
    }
    finally{
      buildWriter.close(); //Closes Writer
    }
  }
  
//Saves everything else
  public void save(ArrayList<Building> buildings, boolean turn, boolean team) throws IOException{
    PrintWriter otherInfoWriter = new PrintWriter(new File("board_state_save.csv"));
    try{
      for (int i = 0; i < buildings.size(); i++){
        //Temporary Object
        Building tempBuild = buildings.get(i);
        //Stores name of building
        otherInfoWriter.write(tempBuild.getName() + ",");
        //Stores health of building
        otherInfoWriter.write(tempBuild.getHealth() + ",");
        //Stores team of building
        otherInfoWriter.write(tempBuild.getTeam() + ",");
        //Stores coordinates of building
        otherInfoWriter.write(tempBuild.getCoords()[0] + "," + tempBuild.getCoords()[0]);
        
        //Stores whose turn it is currently (last line will always be what turn it is)
        if (turn){
          otherInfoWriter.write("P1 Turn");
        }
        else{
          otherInfoWriter.write("P2 Turn");
        }
      }
    }
    
    catch (Exception e){
      System.out.println("An Exception has occured in save method");
    }
    finally{
      otherInfoWriter.close(); //Closes Writer
    }
  }
  
  public void loadTroops(Terrain[][] boardState, ArrayList<Troop> troops, String fileName) throws IOException{
    BufferedReader troopReader = new BufferedReader(new FileReader(fileName));
    String line;
    String[] tempArr;
    //While file still has unread lines
    while ((line = troopReader.readLine()) != null){
      tempArr = line.split(","); //Splits line into String array
      int x = Integer.parseInt(tempArr[3]); //X coordinates
      int y = Integer.parseInt(tempArr[4]); //Y Coordinates
      int[] coordinates = {x, y}; //Stores the coordinates into an int array
      //If troop is a footman
      if (tempArr[0].equals("Footman")){
        troops.add(new Footman(tempArr[2], coordinates)); //Creates object in correct coordinates
        troops.get(troops.size()-1).setHealth(Double.parseDouble(tempArr[1])); //Sets health to what they were at
        boardState[x][y].setTroop(troops.get(troops.size()-1)); //Stores troop into it's terrain tile
      }
      //If troop is an archer
      else if (tempArr[0].equals("Archer")){
        troops.add(new Archer(tempArr[2], coordinates)); //Creates object in correct coordinates
        troops.get(troops.size()-1).setHealth(Double.parseDouble(tempArr[1])); //Sets health to what they were at
        boardState[x][y].setTroop(troops.get(troops.size()-1)); //Stores troop into it's terrain tile
      }
      //If troop is a knight
      else if (tempArr[0].equals("Knight")){
        troops.add(new Knight(tempArr[2], coordinates)); //Creates object in correct coordinates
        troops.get(troops.size()-1).setHealth(Double.parseDouble(tempArr[1])); //Sets health to what they were at
        boardState[x][y].setTroop(troops.get(troops.size()-1)); //Stores troop into it's terrain tile
      }
      //If troop is a crossbowmen
      else if (tempArr[0].equals("CrossbowMen")){
        troops.add(new CrossbowMen(tempArr[2], coordinates)); //Creates object in correct coordinates
        troops.get(troops.size()-1).setHealth(Double.parseDouble(tempArr[1])); //Sets health to what they were at
        boardState[x][y].setTroop(troops.get(troops.size()-1)); //Stores troop into it's terrain tile
      }
      //If troop is a cavalry
      else if (tempArr[0].equals("Cavalry")){
        troops.add(new Cavalry(tempArr[2], coordinates)); //Creates object in correct coordinates
        troops.get(troops.size()-1).setHealth(Double.parseDouble(tempArr[1])); //Sets health of troop
        boardState[x][y].setTroop(troops.get(troops.size()-1)); //Stores troop into it's terrain tile
      }
    }
    troopReader.close(); //Closes Reader
  }
  
  public void loadBuildings(Terrain[][] boardState, ArrayList<Building> buildings, String fileName) throws IOException{
    BufferedReader buildReader = new BufferedReader(new FileReader(fileName));
    String line;
    String[] tempArr;
    //While file still has unread lines
    while ((line = buildReader.readLine()) != null){
      tempArr = line.split(","); //Splits line into String array
      //If building is a Castle
      if (tempArr[0].equals("Castle")){
        int x = Integer.parseInt(tempArr[5]); //X coordinates
        int y = Integer.parseInt(tempArr[6]); //Y Coordinates
        int[] coordinates = {x, y}; //Stores the coordinates into an int array
        buildings.add(new Castle(tempArr[2], coordinates)); //Creates object in correct coordinates
        buildings.get(buildings.size()-1).setHealth(Double.parseDouble(tempArr[1])); //Sets health of building
        ((Castle)(buildings.get(buildings.size()-1))).setLevel(Integer.parseInt(tempArr[3])); //Sets level of Castle
        ((Castle)(buildings.get(buildings.size()-1))).setGold(Integer.parseInt(tempArr[4])); //Sets gold for player
        boardState[x][y].setBuilding(buildings.get(buildings.size()-1)); //Stores building into it's terrain tile
      }
      //If building is Gold Mine
      else if (tempArr[0].equals("Gold Mine")){
        int x = Integer.parseInt(tempArr[3]); //X coordinates
        int y = Integer.parseInt(tempArr[4]); //Y Coordinates
        int[] coordinates = {x, y}; //Stores the coordinates into an int array
        buildings.add(new GoldMine(tempArr[2], coordinates)); //Creates object in correct coordinates
        buildings.get(buildings.size()-1).setHealth(Double.parseDouble(tempArr[1])); //Sets health of building
        boardState[x][y].setBuilding(buildings.get(buildings.size()-1)); //Stores building into it's terrain tile
      }
    }
    buildReader.close(); //Closes Reader
  }
  
  public boolean load(Terrain[][] boardState, ArrayList<Building> unclaimedBuildings, String fileName) throws IOException{
    BufferedReader reader = new BufferedReader(new FileReader(fileName));
    String line;
    String[] tempArr;
    //While file still has unread lines
    while((line = reader.readLine()) != null){
      //If player 1 turn found in file
      if (line.equals("P1 Turn")){
        reader.close();
        //Returns true
        return true;
      }
      //If player 2 turn found in file
      else if (line.equals("P2 Turn")){
        reader.close();
        //Returns false
        return false;
      }
      else{
        tempArr = line.split(","); //Splits line into String array
        int x = Integer.parseInt(tempArr[3]); //X coordinates
        int y = Integer.parseInt(tempArr[4]); //Y Coordinates
        int[] coordinates = {x, y}; //Stores the coordinates into an int array
        unclaimedBuildings.add(new GoldMine(tempArr[2], coordinates)); //Creates object in correct coordinates
        unclaimedBuildings.get(unclaimedBuildings.size()-1).setHealth(Double.parseDouble(tempArr[1])); //Sets health of building
        boardState[x][y].setBuilding(unclaimedBuildings.get(unclaimedBuildings.size()-1)); //Stores building into it's terrain tile
      }
    }
    reader.close();
    //Returns player 1 if no player turn was returned
    return true;
    
    
  }
}

    
    
    
    
    
    
    
    
