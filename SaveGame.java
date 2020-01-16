/* Name: Frank Kong
 * Date: December 30, 2019
 * Description: Just a class to place my save methods for now (Will not be included in final game)
 */
import java.io.*;
import java.util.*;

class SaveGame{
  static String currentDirPath = System.getProperty("user.dir"); //Finds the path of the working directory
  static String saveFolderPath = currentDirPath + "\\save"; //Stores the path of the main folder
  static File saveFolder = new File(saveFolderPath); //Creates a new file
  
  //Initializes Folder
  public static void initializeFolder(){
    if (! saveFolder.exists()){
      saveFolder.mkdir();
    }
  }
  //Troop Saving
  public static void saveTroops(ArrayList<Troop> troops, String team) throws IOException{
    String fileName;
    if (team == "P1"){
      fileName = "player_1_troop_save.csv";
    }
    else{
      fileName = "player_2_troop_save.csv";
    }
    PrintWriter troopWriter = new PrintWriter(new FileOutputStream((new File(saveFolderPath + "\\" + fileName)), false));
    
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
        troopWriter.write(tempTroop.getCoords()[0] + "," + tempTroop.getCoords()[1] + "\n");
      }
    }
    catch (Exception e){
      System.out.println(e);
    }
    finally{
      troopWriter.close(); //Closes Writer
    }
  }
  //Building Save
  public static void saveBuildings(ArrayList<Building> buildings, String team) throws IOException{
    String fileName;
    if (team == "P1"){
      fileName = "player_1_building_save.csv";
    }
    else{
      fileName = "player_2_building_save.csv";
    }
    PrintWriter buildWriter = new PrintWriter(new FileOutputStream((new File(saveFolderPath + "\\" + fileName)), false));
    
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
          buildWriter.write(tempBuild.getCoords()[0] + "," + tempBuild.getCoords()[1] + "\n");
        }
        else{
          //Stores name of building
          buildWriter.write(tempBuild.getName() + ",");
          //Stores health of building
          buildWriter.write(tempBuild.getHealth() + ",");
          //Stores team of building
          buildWriter.write(tempBuild.getTeam() + ",");
          //Stores coordinates of building
          buildWriter.write(tempBuild.getCoords()[0] + "," + tempBuild.getCoords()[1] + "\n");
        }
      }
      
    }
    
    catch (Exception e){
      System.out.println(e);
    }
    finally{
      buildWriter.close(); //Closes Writer
    }
  }
  
//Saves everything else
  public static void save(ArrayList<Building> buildings, String turn, String team) throws IOException{
    PrintWriter otherInfoWriter = new PrintWriter(new FileOutputStream(new File(saveFolderPath + "\\board_state_save.csv"), false));
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
        otherInfoWriter.write(tempBuild.getCoords()[0] + "," + tempBuild.getCoords()[1] + "\n");
      }
      //Stores whose turn it is currently (last line will always be what turn it is)
      if (turn == "P1"){
        otherInfoWriter.write("P1 Turn");
      }
      else{
        otherInfoWriter.write("P2 Turn");
      }
    }
    
    
    catch (Exception e){
      System.out.println(e);
    }
    finally{
      otherInfoWriter.close(); //Closes Writer
    }
  }
  
  public static void loadTroops(ArrayList<Troop> troops, File file) throws IOException{
    BufferedReader troopReader = new BufferedReader(new FileReader(file));
    String line;
    String[] tempArr;
    try{
      //While file still has unread lines
      while ((line = troopReader.readLine()) != null){
        tempArr = line.split(","); //Splits line into String array
        int x = Integer.parseInt(tempArr[4]); //X coordinates
        int y = Integer.parseInt(tempArr[5]); //Y Coordinates
        int[] coordinates = {x, y}; //Stores the coordinates into an int array
        //If troop is a footman
        if (tempArr[0].equals("Footman")){
          troops.add(new Footman(tempArr[2], coordinates)); //Creates object in correct coordinates
          troops.get(troops.size()-1).setHealth(Double.parseDouble(tempArr[1])); //Sets health to what they were at
          troops.get(troops.size()-1).setAction(Boolean.parseBoolean(tempArr[3])); //Sets action boolean
        }
        //If troop is an archer
        else if (tempArr[0].equals("Archer")){
          troops.add(new Archer(tempArr[2], coordinates)); //Creates object in correct coordinates
          troops.get(troops.size()-1).setHealth(Double.parseDouble(tempArr[1])); //Sets health to what they were at
          troops.get(troops.size()-1).setAction(Boolean.parseBoolean(tempArr[3])); //Sets action boolean
        }
        //If troop is a knight
        else if (tempArr[0].equals("Knight")){
          troops.add(new Knight(tempArr[2], coordinates)); //Creates object in correct coordinates
          troops.get(troops.size()-1).setHealth(Double.parseDouble(tempArr[1])); //Sets health to what they were at
          troops.get(troops.size()-1).setAction(Boolean.parseBoolean(tempArr[3])); //Sets action boolean
        }
        //If troop is a crossbowmen
        else if (tempArr[0].equals("CrossbowMen")){
          troops.add(new CrossbowMen(tempArr[2], coordinates)); //Creates object in correct coordinates
          troops.get(troops.size()-1).setHealth(Double.parseDouble(tempArr[1])); //Sets health to what they were at
          troops.get(troops.size()-1).setAction(Boolean.parseBoolean(tempArr[3])); //Sets action boolean
        }
        //If troop is a cavalry
        else if (tempArr[0].equals("Cavalry")){
          troops.add(new Cavalry(tempArr[2], coordinates)); //Creates object in correct coordinates
          troops.get(troops.size()-1).setHealth(Double.parseDouble(tempArr[1])); //Sets health of troop
          troops.get(troops.size()-1).setAction(Boolean.parseBoolean(tempArr[3])); //Sets action boolean
        }
      }
    }
    catch (Exception e){
      System.out.println(e);
    }
    finally{
      troopReader.close(); //Closes Reader
    }
  }
  
  public static void loadBuildings(ArrayList<Building> buildings, File file) throws IOException{
    BufferedReader buildReader = new BufferedReader(new FileReader(file));
    String line;
    String[] tempArr;
    try{
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
        }
        //If building is Gold Mine
        else if (tempArr[0].equals("Gold Mine")){      
          int x = Integer.parseInt(tempArr[3]); //X coordinates
          int y = Integer.parseInt(tempArr[4]); //Y Coordinates   
          int[] coordinates = {x, y}; //Stores the coordinates into an int array      
          buildings.add(new GoldMine(tempArr[2], coordinates)); //Creates object in correct coordinates   
          buildings.get(buildings.size()-1).setHealth(Double.parseDouble(tempArr[1])); //Sets health of building
        }
      }
    }
    catch (Exception e){
      System.out.println(e);
    }
    finally{
      buildReader.close(); //Closes Reader
    }
  }
  
  public static String load(ArrayList<Building> unclaimedBuildings, File file) throws IOException{
    BufferedReader reader = new BufferedReader(new FileReader(file));
    String line;
    String[] tempArr;
    try{
      //While file still has unread lines
      while((line = reader.readLine()) != null){
        if (line.equals("P1 Turn")){
          reader.close();
          return "P1";
        }
        else if (line.equals("P2 Turn")){
          reader.close();
          return "P2";
        }
        else{
          tempArr = line.split(","); //Splits line into String array
          int x = Integer.parseInt(tempArr[3]); //X coordinates
          int y = Integer.parseInt(tempArr[4]); //Y Coordinates
          int[] coordinates = {x, y}; //Stores the coordinates into an int array
          unclaimedBuildings.add(new GoldMine(tempArr[2], coordinates)); //Creates object in correct coordinates
          unclaimedBuildings.get(unclaimedBuildings.size()-1).setHealth(Double.parseDouble(tempArr[1])); //Sets health of building
        }
      }
    }
    catch(Exception e){
      System.out.println(e);
    }
    finally{
      reader.close();
      //Returns player 1 if no player turn was returned
      return "P1";
    }
    
    
  }
  
}









