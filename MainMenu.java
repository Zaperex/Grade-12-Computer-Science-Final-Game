/* Name: Sean Hua
 * Date: 2019/12/30
 * Description: Main Menu for the game
 * */

//First make the troops only be able to do 1 move per turn
// -flip the boolean after they move and cannot attack
// -flip the boolean after combat (when continue is pressed) flip attacker's troop to false
//At the start of each turn, go through the respective player's tropp arraylist (p1Troops, p2Troops) and set all action booleans to true;


//Claiming buildings:
// - player will move to a stop
// add additional check to check if there is a building with a team of "None"
//if so, automatically call the claim  building method
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.animation.TranslateTransition;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import java.util.*;
import java.io.*;

public class MainMenu extends Application {
  private Pane primaryRoot = new Pane();
  private Scene primaryScene = new Scene(primaryRoot, 600, 600);
  public static String turn = "P1"; //String value for whose turn it is
  private int[] previousMoveCoords = new int[2];
  private boolean firstClick = true;
  private ArrayList<int[]> possibleMoves;  
  private ArrayList<int[]> possibleAttacks; 
  private boolean playerTurn = true; //True = attacker, false = defender
  private Label turnLabel = new Label();
  private Troop attacker;  
  private Combatant defender;  
  private Pane combatRoot = new Pane();
  private String attackAction;
  private String defendAction;
  private Combatant combatWinner;
  VBox recruitmentLayout = new VBox(15); //Create a layout for the overall recruitment layout
  
  //Create a 2D array of buttons (represents the map)
    Button[][] button = new Button[7][7];
    
  public static Terrain[][] terrain = new Terrain[7][7];
  public static ArrayList<Troop> p1Troops = new ArrayList<Troop>();
  public static ArrayList<Troop> p2Troops = new ArrayList<Troop>();
  public static ArrayList<Building> p1Buildings = new ArrayList<Building>();
  public static ArrayList<Building> p2Buildings = new ArrayList<Building>();
  public static ArrayList<Building> unclaimedBuildings = new ArrayList<Building>();
  
  
  
  public void start (Stage primaryStage) throws Exception {
    
    //Create a rectangle
    Rectangle rect1 = new Rectangle(0, 0, 600, 1);
    //Set the colour to grey
    rect1.setFill(Color.GREY);
    
    //Create another rectangle
    Rectangle rect2 = new Rectangle(0, 599, 600, 1);
    //Set the colour to yellow
    rect2.setFill(Color.BEIGE);
    
    //Transitioin for the first rectangle
    ScaleTransition rect1T = new ScaleTransition(Duration.seconds(1), rect1);
    //Sets the Y position to 600
    rect1T.setToY(600);
    //Repeats the transition one time
    rect1T.setCycleCount(1);
    rect1T.play();
    
    //Transition for the second rectangle
    ScaleTransition rect2T = new ScaleTransition(Duration.seconds(1), rect2);
    //Set the Y position to 600
    rect2T.setToY(600);
    //Repeats the transition one time
    rect2T.setCycleCount(1);
    rect2T.play();
    
    Label title = new Label("SEAN FRANK GAME");
    title.setMinWidth(200);
    title.setMinHeight(100);
    title.relocate(225, 100);
    
    //Create a button that will be used to start a new game
    Button newGameButton = new Button("New Game");
    //Sets the position of the button
    newGameButton.relocate(225, 400);
    //Sets the size of the button
    newGameButton.setPrefSize(150, 25);
    Button loadGameButton = new Button("Load Button");
    
    
    //Give the button a function when it is pressed
    newGameButton.setOnAction(e -> newGameButtonClicked(primaryStage));
    
    try{
      loadGameButton = createLoadButton();
    }
    catch(Exception e){
      System.out.println("IOException has occured");
    }

//    Create a button that will show the instructions
    Button instructionsButton = new Button("Instructions");
    //Sets the position of the button
    instructionsButton.relocate(225, 500);
    //Sets the size of the button
    instructionsButton.setPrefSize(150, 25);
    //Give the button a function when it is pressed
    instructionsButton.setOnAction(e -> instructionsButtonClicked(primaryStage));
    
    VBox buttonLayout = new VBox();
    buttonLayout.getChildren().addAll(newGameButton, loadGameButton, instructionsButton);
    buttonLayout.relocate(200, 400);
    buttonLayout.setSpacing(10);
    
    primaryRoot.getChildren().addAll(rect1, rect2, title, buttonLayout);
    primaryStage.setTitle("Game");
    primaryStage.setScene(primaryScene);
    primaryStage.show();
  }
  
  //Method that will start a new game
  public void newGameButtonClicked (Stage primaryStage){
    
    mainMethods.newGame(terrain, p1Troops, p2Troops, p1Buildings, p2Buildings, unclaimedBuildings);
    
    Pane root = new Pane();
    Scene gameScene = new Scene(root, 1050, 675);
    
    //Creates a box for information about the building
    Rectangle buildingBox = new Rectangle(300, 150);
    
    //Create a label for the building's information
    Label buildingInfo = new Label();
    
    //Create a rectangle for the troop's information
    Rectangle troopBox = new Rectangle(300, 150);
    
    //Create a label for the troop's information
    Label troopInfo = new Label();
    
    //Create a rectangle for the terrain's information
    Rectangle terrainBox = new Rectangle(300, 30);
    
    //Create a label for the terrain's information
    Label terrainInfo = new Label();
    
    Button saveButton = new Button("Save Game");
    saveButton.relocate(950, 550);
//    saveButton.setOnAction(e ->);
    
//    boolean attacker = true;
    int[] attackerCoords = new int[2];
    int[] defenderCoords = new int[2];
    
    //Populate the 2d array of buttons
    for (int i = 0; i < 7; i++) {
      for (int j = 0; j < 7; j++) {
        //Create a new button 
        button[i][j] = new Button("");
        //Set the size of the button
        button[i][j].setPrefSize(75, 75);
        //Puts the button in the correct spot of the window
        button[i][j].setLayoutX(i*75 + 75); 
        button[i][j].setLayoutY(j*75 + 75);
        
        //Array used to store the coordinates of the button
        int[] coords = {i, j}; //Based on arraylist orientation with y controlling which row and x controlling which column
        int x = i;
        int y = j;
       
       
        if (y == 0 && x == 3){
          terrain[i][j] = new Terrain("Plain", coords);
          terrain[i][j].setTroop(new Archer("P2", coords));
        }
        else if (y == 3 && x == 4){
          terrain[i][j] = new Terrain("Plain", coords);
          terrain[i][j].setTroop(new Footman("P1", coords));
        }
        else if (y == 3 && x == 5){
          terrain[i][j] = new Terrain("Plain", coords);
          terrain[i][j].setTroop(new Knight("P1", coords));
        }
        
        
        //Give the button a function
        // - when the button is pressed, the lambda expression will be executed
        button[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
          public void handle(MouseEvent e) {
            
            try{
              root.getChildren().remove(recruitmentLayout);
            }
            catch (Exception error){
              System.out.println("Button doesn't exist");
            }
            
            System.out.println("CLICK COUNT: " + e.getClickCount());
            System.out.println(x + ", " + y);
            //Remove the previous boxes and labels to prevent any overlapping
            root.getChildren().removeAll(buildingBox, buildingInfo, troopBox, troopInfo, terrainBox, terrainInfo);
            //Checks if a building exists on the selected button/tile
            if (terrain[x][y].getBuilding() != null) {
              //Call the checkBuilding method
              checkBuilding(terrain, buildingBox, buildingInfo, x, y, root);
              //If a the home base (castle) of the player's team is clicked
              if (terrain[x][y].getBuilding().getTeam().equals(turn)){
                if (terrain[x][y].getBuilding() instanceof Castle && terrain[x][y].getBuilding().getTeam().equals(turn)) {
                  
                  System.out.println("RECRUITMENT");
                  Label recruitmentLabel = new Label("Recruitment:"); //Create a label for recruitment
                  
                  Button archerButton = new Button("Archer"); //Create a button to recruit an archer
                  Button knightButton = new Button("Knight"); //Create a button to recruit a knight
                  Button cbmButton = new Button("Crossbow Men"); //Create a button to recruit a crossbow men
                  Button footManButton = new Button("Foot Man"); //Create a button to recruit a footman
                  Button cavalryButton = new Button("Cavalry"); //Create a button to recruit cavalry
                  
                  HBox troopButtonLayout = new HBox(15); //Create a layout for the buttons
                  //Add all of the buttons to the layout
                  troopButtonLayout.getChildren().addAll(archerButton, knightButton, cbmButton, footManButton, cavalryButton);
                  
//                  VBox recruitmentLayout = new VBox(15); //Create a layout for the overall recruitment layout
                  recruitmentLayout.relocate(640, 500); //Set the location of the layout
                  recruitmentLayout.getChildren().addAll(recruitmentLabel, troopButtonLayout); //Add the label and the buttons to the layout
                  
                  
                  /*
                   archerButton.setOnAction();
                   knightButton.setOnAction();
                   cbmButton.setOnAction();
                   footManButton.setOnAction();
                   cavalryButton.setOnAction();                   
                   */
                  
                  //Checks if there is a castle
                  //Checks level of the castle (3 is the max level)
                  if (((Castle)terrain[x][y].getBuilding()).getLevel() < 3){
                    Button upgradeButton = new Button("Upgrade: Costs " +  Castle.getUpgradeCost() + " gold");
                    recruitmentLayout.getChildren().add(upgradeButton);
                  }
                  
                  root.getChildren().add(recruitmentLayout);
                }
              }
            }
            if (terrain[x][y].getTroop() != null) {
              //Call the checkTroop method
              System.out.println("Troop is present here!");
              checkTroop(terrain, troopBox, troopInfo, x, y, root);
            }
            //Call the check terrain method
            checkTerrain(terrain, terrainBox, terrainInfo, x, y, root);
            
            System.out.println("Top First Click: " + firstClick);
            
            //If the user clicks on a red tile, it means that they are choosing another troop/building to fight
            if (firstClick == true && button[x][y].getStyle().equals("-fx-background-color: red;" + "-fx-text-fill: white")) {
              //If the selected button is a building
              if (terrain[x][y].getBuilding() != null) {
                defender = terrain[x][y].getBuilding(); //Store the building as the defender
              }
              //If the selected button is a troop
              else {
                defender = terrain[x][y].getTroop(); //Store the troop as the defender
              }
              System.out.println("first move x: " + previousMoveCoords[0]);
              System.out.println("first move y: " + previousMoveCoords[1]);
              //Checks if the previous selected combatant is a troop
              if (terrain[previousMoveCoords[0]][previousMoveCoords[1]].getTroop() != null) {
                attacker = terrain[previousMoveCoords[0]][previousMoveCoords[1]].getTroop(); //Set the troop as the attacker
              }
              
              //Calls the combatScreen method, which will show a pop up window where the attacker and defender will fight
              combatScreen();
              
              //Used to store the coordinates of the troop that loses the fight
              int[] loserCoords = new int[2];
              
              //If the defender loses
              if (combatWinner == defender) {
                System.out.println("WINNER IS DEFENDER");
                loserCoords = attacker.getCoords(); //Sets the attacker's coords as the loserCoords
              }
              //If the attacker loses
              else {
                System.out.println("WINNER IS ATTACKER");
                loserCoords = defender.getCoords(); //Sets the defender's coords as the loserCoords
              }
              
              button[loserCoords[0]][loserCoords[1]].setGraphic(null); //Deletes the loser's picture 
              terrain[loserCoords[0]][loserCoords[1]].setTroop(null); //Deletes the object of the loser
              
              //Loops through the entire board of buttons
              for (int l = 0; l < 7; l++) {
                for (int m = 0; m < 7; m++) {
                  button[l][m].setDisable(false); //Enable the button so that it is clickable
                  button[l][m].setStyle(null); //Unhighlight the button 
                }
              }
            }
            //If the player selects/clicks on a troop on the board
            else if (firstClick == true && terrain[x][y].getTroop() != null) {
              
              if (terrain[x][y].getTroop().getTeam().equals(turn)){
                //Keeps track of the initial position of the troop
                previousMoveCoords[0] = x; //Save their x-coordinate
                previousMoveCoords[1] = y; //Save their y-coordinate
                
                
                //Loops through the board of buttons
                for (int l = 0; l < 7; l++) {
                  for (int m = 0; m < 7; m++) {
                    button[l][m].setDisable(true); //Disable every button on the board 
                  }
                }
                //possibleMoves is used to store an ArrayList of integer arrays of possible coordinates that the selected troop can move to
                possibleMoves = terrain[x][y].getTroop().findMoves(terrain); //Calls findMoves, which returns the ArrayList
                int[] move = new int[2];//Stores the int array stored inside of the ArrayList
                for (int k = 0; k < possibleMoves.size(); k++) {
                  move = possibleMoves.get(k); //Save the coordinates of where the troop is allowed to travel to
                  button[move[0]][move[1]].setStyle("-fx-background-color: blue;" + "-fx-text-fill: white"); //Highlight the button
                  button[move[0]][move[1]].setDisable(false); //Enable the button 
                }                 
                
//              //If a the home base (castle) of the player's team is clicked
//              if (terrain[x][y].getBuilding() instanceof Castle && terrain[x][y].getBuilding() != null &&
//                  terrain[x][y].getBuilding().getTeam() != null && terrain[x][y].getBuilding().getTeam().equals(turn)) {
//                
//                Label recruitmentLabel = new Label("Recruitment:"); //Create a label for recruitment
//                
//                Button archerButton = new Button("Archer"); //Create a button to recruit an archer
//                Button knightButton = new Button("Knight"); //Create a button to recruit a knight
//                Button cbmButton = new Button("Crossbow Men"); //Create a button to recruit a crossbow men
//                Button footManButton = new Button("Foot Man"); //Create a button to recruit a footman
//                Button cavalryButton = new Button("Cavalry"); //Create a button to recruit cavalry
//                
//                HBox troopButtonLayout = new HBox(15); //Create a layout for the buttons
//                //Add all of the buttons to the layout
//                troopButtonLayout.getChildren().addAll(archerButton, knightButton, cbmButton, footManButton, cavalryButton);
//                
//                VBox recruitmentLayout = new VBox(); //Create a layout for the overall recruitment layout
//                recruitmentLayout.relocate(640, 550); //Set the location of the layout
//                recruitmentLayout.getChildren().addAll(recruitmentLabel, troopButtonLayout); //Add the label and the buttons to the layout
//                
//                //Checks if there is a castle
//                  //Checks level of the castle (3 is the max level)
//                  if (((Castle)terrain[x][y].getBuilding()).getLevel() < 3){
//                    Button upgradeButton = new Button("Upgrade: " + Castle.getUpgradeCost());
//                    recruitmentLayout.getChildren().add(upgradeButton);
//                  }
//                
//                root.getChildren().add(recruitmentLayout);
//              }
                firstClick = false;
              }
            }
            //If the user is clicking a second time and is clicking on a button without a troop
            //Means that they are choosing a location to move to
            else if (firstClick == false) {
              //Loops through the board 
              for (int l = 0; l < 7; l++) {
                for (int m = 0; m < 7; m++) {
                  button[l][m].setDisable(false); //Enable the button
                }
              }
              
              int[] move = new int[2]; //Since possibleMoves is an ArrayList of int arrays, move is used to store the int arrays
              //Loops through the ArrayList of possible moves
              for (int k = 0; k < possibleMoves.size(); k++) {
                move = possibleMoves.get(k); //Store the int array of possibleMoves to move
                System.out.println(move[0] + "," + move[1]);
                button[move[0]][move[1]].setStyle(null); //Unhighlight the previously highlighted buttons
              }
              
              //Move the troop from the previous move, to the recently selected button
              terrain[x][y].setTroop(terrain[previousMoveCoords[0]][previousMoveCoords[1]].getTroop()); 
              //Deletes the image from the previous button
              button[previousMoveCoords[0]][previousMoveCoords[1]].setGraphic(null);
              //If terrain that troop moves from has a building on it
              if (terrain[previousMoveCoords[0]][previousMoveCoords[1]].getBuilding() != null) {
                //Make sure to reinitialize the image
                Image castleImage = new Image(terrain[previousMoveCoords[0]][previousMoveCoords[1]].getBuilding().getImageName(), 60, 60, false, false);
                button[previousMoveCoords[0]][previousMoveCoords[1]].setGraphic(new ImageView(castleImage));
              }
              //Create a new image for the troop that was moved
              Image image = new Image(terrain[x][y].getTroop().getImageName(), 60, 60, false, false);
              //Set an image for the recently selected button
              button[x][y].setGraphic(new ImageView(image));
              //Update the troop's coordinates by calling the actualMove method from the Troop class
              terrain[previousMoveCoords[0]][previousMoveCoords[1]].getTroop().actualMove(x, y, terrain);
              //Delete the previous troop by setting it to null
              terrain[previousMoveCoords[0]][previousMoveCoords[1]].setTroop(null);
              
              //possibleAttacks an ArrayList that will store coordinates that the troop is allowed to attack
              possibleAttacks = terrain[x][y].getTroop().findAttacks(terrain);
              //Checks if there are any possible choices (size is > 0)
              if (possibleAttacks.size() > 0) {
                //Loops through the board of buttons
                for (int l = 0; l < 7; l++) {
                  for (int m = 0; m < 7; m++) {
                    button[l][m].setDisable(true); //Disable all buttons
                  }
                }
                //Since possibleAttacis 8s ah ArrayList of int arrays, attack will store these int arrays
                int[] attack = new int[2];
                //Loops through the possibleAttacks ArrayList
                for (int k = 0; k < possibleAttacks.size(); k++) {
                  attack = possibleAttacks.get(k); //Store the int array into attack
                  //highlight the button
                  button[attack[0]][attack[1]].setStyle("-fx-background-color: red;" + "-fx-text-fill: white");
                  button[attack[0]][attack[1]].setDisable(false); //Enable the button
                }
              }
              firstClick = true; //Update the firstClick boolean
              //Store the troop's position
              previousMoveCoords[0] = x; //Save the x-coordinate
              previousMoveCoords[1] = y; //Save the y-coordinate
            }
          }
        });
        
        //Creates the board/map by adding the button
        root.getChildren().add(button[i][j]);
        //Checks if there is a troop on that tile
        if (terrain[i][j].getTroop() != null) {
          //Adds the image of the troop onto the tile
          Image image = new Image(terrain[i][j].getTroop().getImageName(), 60, 60, false, false);
          button[i][j].setGraphic(new ImageView(image));    
          
        }
        //Checks if there is a building 
        if (terrain[i][j].getBuilding() != null) {
          //Adds the image of the building onto the tile
          Image image = new Image(terrain[i][j].getBuilding().getImageName(), 60, 60, false, false);
          button[i][j].setGraphic(new ImageView(image));          
        }
      }
    }
    
//    root.getChildren().add(troopButtonLayout); --------------------------------------------------------------------------
    root.getChildren().add(saveButton);
    primaryStage.setScene(gameScene);
    primaryStage.show();
    
  }
  public void initializeBoard (Button[][] button, Terrain[][] terrain) {
    for (int i = 0; i < terrain.length; i++) {
      for (int j = 0; j < terrain[0].length; j++) {
        
        if (terrain[i][j].getTroop() != null) {
          Image troopImage = new Image(terrain[i][j].getTroop().getImageName(), 60, 60, false, false);
          button[i][j].setGraphic(new ImageView(troopImage));
        }
        else if (terrain[i][j].getBuilding() != null) {
          Image buildingImage = new Image(terrain[i][j].getBuilding().getImageName(), 60, 60, false, false);
          button[i][j].setGraphic(new ImageView(buildingImage));
        }
        
      }
    }
  }
  //Method that will load the previous game
  public void loadGameButtonClicked(){
    try{
      mainMethods.loadGame(mainMethods.saveFolderPath, terrain, p1Troops, p2Troops, p1Buildings, p2Buildings, unclaimedBuildings);
    }
    catch(Exception e){
      System.out.println("An IOException has occured");
    }
    
  }
  public Button createLoadButton () throws Exception{
    //Create a button that will load the previous game
    Button loadGameButton = new Button("Load Game");
    //Sets the position of the button
    loadGameButton.relocate(225, 450);
    //Sets the size of the button
    loadGameButton.setPrefSize(150, 25);
    try{
    //Give the button a function when it is pressed
    loadGameButton.setOnAction(e -> loadGameButtonClicked());
    }
    catch(Exception e){
      System.out.println("An IOException has occured");
    }
    return loadGameButton;
  }
  //Method that will bring the user to an instructions page/window
  public void instructionsButtonClicked (Stage primaryStage) {
    
    Label instructions = new Label("1. first instruction\n" + "2. second instruction\n" + "3. third instruction\n");
    
    Button menuButton = new Button("Return To Menu");
    menuButton.relocate(50, 500);
    menuButton.setOnAction(e -> menuButtonClicked(primaryStage));
    
    Pane root = new Pane();
    root.getChildren().addAll(instructions, menuButton);
    
    Scene instructionsScene = new Scene(root, 600, 600);
    
    primaryStage.setScene(instructionsScene);
    primaryStage.show();
    
  }
  public void menuButtonClicked (Stage primaryStage) {
    
    primaryStage.setScene(primaryScene);
    
  }
  
  public void checkBuilding (Terrain[][] terrain, Rectangle buildingBox, Label buildingInfo, int x, int y, Pane root) {
    
    //Sets the position of the buildingBox
    buildingBox.relocate(675, 75);
    //Set the opacity
    buildingBox.setOpacity(0.5);
    //If the building is on the enemy team
    if (terrain[x][y].getBuilding().getTeam().equals("P2")) {
      //Set the color of the buildingBox to red
      buildingBox.setFill(Color.RED);
    }
    //If the building is on the player's team
    else if (terrain[x][y].getBuilding().getTeam().equals("P1")){
      //Set the color of the buildingBox to blue
      buildingBox.setFill(Color.BLUE);
    }
    else{
      //Set the color of the buildingBox to yellow
      buildingBox.setFill(Color.YELLOW);
    }
    System.out.println("buildingInfo.setText\n");
    //Gather information about the building
    buildingInfo.setText("Building\n" + 
                         "Name: " + terrain[x][y].getBuilding().getName() + "\n" +
                         "Health: " + terrain[x][y].getBuilding().getHealth() + "\n" + 
                         "Attack: " + terrain[x][y].getBuilding().getAttack() + "\n" + 
                         "Range: " + terrain[x][y].getBuilding().getRange());
    //Set the position of the building's information
    buildingInfo.relocate(800, 100);
    
    root.getChildren().addAll(buildingBox, buildingInfo);
  }
  public void checkTroop (Terrain[][] terrain, Rectangle troopBox, Label troopInfo, int x, int y, Pane root) {
    
    //Set the position of the troopBox
    troopBox.relocate(675, 250);
    //Set the opacity
    troopBox.setOpacity(0.5);
    //If the troop is on the enemy team
    if (terrain[x][y].getTroop().getTeam().equals("P2")){
      //Set the troopBox to red
      troopBox.setFill(Color.RED);
    }
    //If the troop is on the player's team
    else {
      //Set the troopBox to blue
      troopBox.setFill(Color.BLUE);
    }
    //Gather information about the troop
    troopInfo.setText("Troop\n" + "Name: " + terrain[x][y].getTroop().getName() + "\n" + 
                      "Health: " + terrain[x][y].getTroop().getHealth() + "\n" + 
                      "Attack: " + terrain[x][y].getTroop().getAttack() + "\n" + 
                      "Range: " + terrain[x][y].getTroop().getRange());
    //Set the position of the label
    troopInfo.relocate(800, 275);
    
    root.getChildren().addAll(troopBox, troopInfo);
  }
  public void checkTerrain (Terrain[][] terrain, Rectangle terrainBox, Label terrainInfo, int x, int y, Pane root) {
    
    //Set the position of the terrainBox
    terrainBox.relocate(675, 425);
    //Set the color of the terrainBox
    terrainBox.setFill(Color.TRANSPARENT);
    terrainBox.setStroke(Color.BLACK);
    terrainInfo.setText("Terrain type: " + terrain[x][y].getType());
    terrainInfo.relocate(800, 425);
    root.getChildren().addAll(terrainBox, terrainInfo);
  }
  public void combatScreen () {
    combatRoot = new Pane();
    
    Label attackerStun = new Label();
    attackerStun.relocate(50, 150);
    Label defenderStun = new Label();
    defenderStun.relocate(400, 150);
    
    turnLabel.relocate(225, 50);
    turnLabel.setText("Attacker's Turn");
    
    Image attackerImage = new Image(attacker.getImageName(), 80, 80, false, false);
    ImageView attackerIV = new ImageView(attackerImage);
    attackerIV.relocate(50, 150);
    
    Image defenderImage = new Image(defender.getImageName(), 80, 80, false, false);
    ImageView defenderIV = new ImageView(defenderImage);
    defenderIV.relocate(400, 150);
    
    Label attackerInfo = new Label("Attacker Health: " + attacker.getHealth() + "\n" + 
                                   "Special Meter: " + attacker.getSpecialMeter() + "/3" + "\n" +
                                   "Defense: " + attacker.getDefense());
    attackerInfo.relocate(50, 250);
    Label defenderInfo = new Label("Defender Health: " + defender.getHealth() + "\n" + 
                                   "Special Meter: " + defender.getSpecialMeter() + "/3" + "\n" +
                                   "Defense: " + defender.getDefense());
    defenderInfo.relocate(400, 250);
    
    Button attackerButton1 = new Button("Attack");
    Button attackerButton2 = new Button("Block");
    Button attackerButton3 = new Button("Dodge");
    Button attackerButton4 = new Button("Special Attack");
    
    attackerButton4.setDisable(true);
    
    Button defenderButton1 = new Button();
    Button defenderButton2 = new Button();
    Button defenderButton3 = new Button();
    Button defenderButton4 = new Button();
    
    defenderButton1.setDisable(true);
    defenderButton2.setDisable(true);
    defenderButton3.setDisable(true);
    defenderButton4.setDisable(true);
    
    HBox attackerButtonLayout = new HBox(10);
    attackerButtonLayout.relocate(50, 325);
    attackerButtonLayout.getChildren().addAll(attackerButton1, attackerButton2, attackerButton3, attackerButton4);
    
    HBox defenderButtonLayout = new HBox(10);
    defenderButtonLayout.relocate(400, 325);
    defenderButtonLayout.getChildren().addAll(defenderButton1, defenderButton2, defenderButton3, defenderButton4);
    
    Label combatLogLabel = new Label();
    
    if (defender instanceof Building) {
      defenderButton1.setText("Attack");
      defenderButton1.setOnAction(e -> defenderButton1Clicked(attackerInfo, defenderInfo, 
                                                              attackerButton1, attackerButton2, attackerButton3, attackerButton4,
                                                              defenderButton1, defenderButton2, defenderButton3, defenderButton4, 
                                                              combatLogLabel, attackerStun, defenderStun, attackerIV, defenderIV, attackerButtonLayout, defenderButtonLayout));
      defenderButton2.setText("Magic Barrier");
      defenderButton2.setOnAction(e -> defenderButton2Clicked(attackerInfo, defenderInfo, 
                                                              attackerButton1, attackerButton2, attackerButton3, attackerButton4,
                                                              defenderButton1, defenderButton2, defenderButton3, defenderButton4,
                                                              combatLogLabel, attackerStun, defenderStun, attackerIV, defenderIV, attackerButtonLayout, defenderButtonLayout));
      defenderButton3.setText("Repair");
      defenderButton3.setOnAction(e -> defenderButton3Clicked(attackerInfo, defenderInfo, 
                                                              attackerButton1, attackerButton2, attackerButton3, attackerButton4,
                                                              defenderButton1, defenderButton2, defenderButton3, defenderButton4, 
                                                              combatLogLabel, attackerStun, defenderStun, attackerIV, defenderIV, attackerButtonLayout, defenderButtonLayout));
      defenderButton4.setText("Special Attack");
      defenderButton4.setOnAction(e -> defenderButton4Clicked(attackerInfo, defenderInfo, 
                                                              attackerButton1, attackerButton2, attackerButton3, attackerButton4,
                                                              defenderButton1, defenderButton2, defenderButton3, defenderButton4, 
                                                              combatLogLabel, attackerStun, defenderStun, attackerIV, defenderIV, attackerButtonLayout, defenderButtonLayout));
    }
    else {
      defenderButton1.setText("Attack");
      defenderButton1.setOnAction(e -> defenderButton1Clicked(attackerInfo, defenderInfo, 
                                                              attackerButton1, attackerButton2, attackerButton3, attackerButton4,
                                                              defenderButton1, defenderButton2, defenderButton3, defenderButton4, 
                                                              combatLogLabel, attackerStun, defenderStun, attackerIV, defenderIV, 
                                                              attackerButtonLayout, defenderButtonLayout));
      defenderButton2.setText("Block");
      defenderButton2.setOnAction(e -> defenderButton2Clicked(attackerInfo, defenderInfo, 
                                                              attackerButton1, attackerButton2, attackerButton3, attackerButton4,
                                                              defenderButton1, defenderButton2, defenderButton3, defenderButton4, 
                                                              combatLogLabel, attackerStun, defenderStun, attackerIV, defenderIV, attackerButtonLayout, defenderButtonLayout));
      defenderButton3.setText("Dodge");
      defenderButton3.setOnAction(e -> defenderButton3Clicked(attackerInfo, defenderInfo, 
                                                              attackerButton1, attackerButton2, attackerButton3, attackerButton4,
                                                              defenderButton1, defenderButton2, defenderButton3, defenderButton4, 
                                                              combatLogLabel, attackerStun, defenderStun, attackerIV, defenderIV, attackerButtonLayout, defenderButtonLayout));
      defenderButton4.setText("Special Attack");
      defenderButton4.setOnAction(e -> defenderButton4Clicked(attackerInfo, defenderInfo, 
                                                              attackerButton1, attackerButton2, attackerButton3, attackerButton4,
                                                              defenderButton1, defenderButton2, defenderButton3, defenderButton4, 
                                                              combatLogLabel, attackerStun, defenderStun, attackerIV, defenderIV, attackerButtonLayout, defenderButtonLayout));
    }
    
    attackerButton1.setOnAction(e -> attackerButton1Clicked(attackerInfo, defenderInfo, 
                                                            attackerButton1, attackerButton2, attackerButton3, attackerButton4,
                                                            defenderButton1, defenderButton2, defenderButton3, defenderButton4, 
                                                            combatLogLabel, attackerStun, defenderStun, attackerIV, defenderIV, attackerButtonLayout, defenderButtonLayout));
    attackerButton2.setOnAction(e -> attackerButton2Clicked(attackerInfo, defenderInfo, 
                                                            attackerButton1, attackerButton2, attackerButton3, attackerButton4,
                                                            defenderButton1, defenderButton2, defenderButton3, defenderButton4, 
                                                            combatLogLabel, attackerStun, defenderStun, attackerIV, defenderIV, attackerButtonLayout, defenderButtonLayout));
    attackerButton3.setOnAction(e -> attackerButton3Clicked(attackerInfo, defenderInfo, 
                                                            attackerButton1, attackerButton2, attackerButton3, attackerButton4,
                                                            defenderButton1, defenderButton2, defenderButton3, defenderButton4, 
                                                            combatLogLabel, attackerStun, defenderStun, attackerIV, defenderIV, attackerButtonLayout, defenderButtonLayout));  
    attackerButton4.setOnAction(e -> attackerButton4Clicked(attackerInfo, defenderInfo, 
                                                            attackerButton1, attackerButton2, attackerButton3, attackerButton4,
                                                            defenderButton1, defenderButton2, defenderButton3, defenderButton4, 
                                                            combatLogLabel, attackerStun, defenderStun, attackerIV, defenderIV, attackerButtonLayout, defenderButtonLayout));
    
    combatRoot.getChildren().addAll(attackerButtonLayout, defenderButtonLayout, 
                                    attackerIV, defenderIV, turnLabel, attackerInfo, defenderInfo, combatLogLabel, attackerStun, defenderStun);
    
    
    Scene combatScene = new Scene(combatRoot, 1000, 400);
    Stage combatStage = new Stage();
    combatStage.setScene(combatScene);
    combatStage.showAndWait();
  }
  public void combatCheck (Label attackerInfo, Label defenderInfo, 
                           Button attackerButton1, Button attackerButton2, Button attackerButton3, Button attackerButton4, 
                           Button defenderButton1, Button defenderButton2, Button defenderButton3, Button defenderButton4, 
                           Label combatLogLabel, Label attackerStun, Label defenderStun, ImageView attackerIV, ImageView defenderIV, HBox attackerButtonLayout, HBox defenderButtonLayout) {
    
    if (playerTurn == true) {
      attackerButton1.setDisable(false);
      attackerButton2.setDisable(false);
      attackerButton3.setDisable(false);
      attackerButton4.setDisable(false);
      
      defenderButton1.setDisable(true);
      defenderButton2.setDisable(true);
      defenderButton3.setDisable(true);
      defenderButton4.setDisable(true);
    }
    else {
      attackerButton1.setDisable(true);
      attackerButton2.setDisable(true);
      attackerButton3.setDisable(true);
      attackerButton4.setDisable(true);
      
      defenderButton1.setDisable(false);
      defenderButton2.setDisable(false);
      defenderButton3.setDisable(false);
      defenderButton4.setDisable(false);
    }
    
    ArrayList<String> combatLog = new ArrayList<String>();
    
    if (attackAction != null && defendAction != null) {
      int gold = 0; //Stores defender's gold
      if (defender.getTeam().equals("P1")){ //If defender is player 1's team
        //Check the player 1 arraylist for a castle
        for (int i = 0; i < p1Buildings.size(); i++){
          //If Building is a Castle
          if (p1Buildings.get(i) instanceof Castle){
            gold = ((Castle)p1Buildings.get(i)).getGold(); //Stores gold of player's castle
            break; //Breaks out since there is only 1 castle
          }
        }
      }
      else if (defender.getTeam().equals("P2")){ //If defender is on player 2's team
        //Check the player 2 arraylist for a castle
        for (int i = 0; i < p2Buildings.size(); i++){
          //If Building is a Castle
          if (p2Buildings.get(i) instanceof Castle){
            gold = ((Castle)p2Buildings.get(i)).getGold(); //Stores gold of player's castle
            break; //Breaks out since there is only 1 castle
          }
        }
      }
      
      combatLog = Combat.combatPhase(attacker, defender, attackAction, defendAction, gold);
      displayCombatLog(combatLog, combatLogLabel);
      attackerInfo.setText("Attacker Health: " + attacker.getHealth() + "\n" + 
                           "Special Meter: " + attacker.getSpecialMeter() + "/3" + "\n" + 
                           "Defense: " + attacker.getDefense());
      defenderInfo.setText("Defender Health: " + " health: " + defender.getHealth() + "\n" + 
                           "Special Meter: " + defender.getSpecialMeter() + "/3" + "\n" + 
                           "Defense: " + defender.getDefense());
      
      defenderCombatCheck(attackerInfo, defenderInfo, 
                          attackerButton1, attackerButton2, attackerButton3, attackerButton4, 
                          defenderButton1, defenderButton2, defenderButton3, defenderButton4, 
                          combatLogLabel, attackerStun, defenderStun, attackerIV, defenderIV, attackerButtonLayout, defenderButtonLayout);
      
      attackerCombatCheck(attackerInfo, defenderInfo, 
                          attackerButton1, attackerButton2, attackerButton3, attackerButton4, 
                          defenderButton1, defenderButton2, defenderButton3, defenderButton4, 
                          combatLogLabel, attackerStun, defenderStun, attackerIV, defenderIV, attackerButtonLayout, defenderButtonLayout);
      
      
      attackAction = null;
      defendAction = null;
    } 
  }
  public void attackerButton1Clicked (Label attackerInfo, Label defenderInfo, 
                                      Button attackerButton1, Button attackerButton2, Button attackerButton3, Button attackerButton4, 
                                      Button defenderButton1, Button defenderButton2, Button defenderButton3, Button defenderButton4, 
                                      Label combatLogLabel, Label attackerStun, Label defenderStun, ImageView attackerIV, ImageView defenderIV, HBox attackerButtonLayout, HBox defenderButtonLayout) {
    attackAction = attackerButton1.getText();
    
    turnLabel.setText("Defender's turn");
    playerTurn = false;
    
    combatCheck(attackerInfo, defenderInfo, 
                attackerButton1, attackerButton2, attackerButton3, attackerButton4, 
                defenderButton1, defenderButton2, defenderButton3, defenderButton4, 
                combatLogLabel, attackerStun, defenderStun, attackerIV, defenderIV, attackerButtonLayout, defenderButtonLayout);
    
    defenderCombatCheck(attackerInfo, defenderInfo, 
                        attackerButton1, attackerButton2, attackerButton3, attackerButton4, 
                        defenderButton1, defenderButton2, defenderButton3, defenderButton4, 
                        combatLogLabel, attackerStun, defenderStun, attackerIV, defenderIV, attackerButtonLayout, defenderButtonLayout);
    
  }
  public void attackerButton2Clicked (Label attackerInfo, Label defenderInfo, 
                                      Button attackerButton1, Button attackerButton2, Button attackerButton3, Button attackerButton4, 
                                      Button defenderButton1, Button defenderButton2, Button defenderButton3, Button defenderButton4, 
                                      Label combatLogLabel, Label attackerStun, Label defenderStun, ImageView attackerIV, ImageView defenderIV, HBox attackerButtonLayout, HBox defenderButtonLayout) {
    
    attackAction = attackerButton2.getText();
    
    turnLabel.setText("Defender's turn");
    playerTurn = false;
    
    combatCheck(attackerInfo, defenderInfo, 
                attackerButton1, attackerButton2, attackerButton3, attackerButton4, 
                defenderButton1, defenderButton2, defenderButton3, defenderButton4, 
                combatLogLabel, attackerStun, defenderStun, attackerIV, defenderIV, attackerButtonLayout, defenderButtonLayout);
    defenderCombatCheck(attackerInfo, defenderInfo, 
                        attackerButton1, attackerButton2, attackerButton3, attackerButton4, 
                        defenderButton1, defenderButton2, defenderButton3, defenderButton4, 
                        combatLogLabel, attackerStun, defenderStun, attackerIV, defenderIV, attackerButtonLayout, defenderButtonLayout);
  }
  public void attackerButton3Clicked (Label attackerInfo, Label defenderInfo, 
                                      Button attackerButton1, Button attackerButton2, Button attackerButton3, Button attackerButton4, 
                                      Button defenderButton1, Button defenderButton2, Button defenderButton3, Button defenderButton4, 
                                      Label combatLogLabel, Label attackerStun, Label defenderStun, ImageView attackerIV, ImageView defenderIV, HBox attackerButtonLayout, HBox defenderButtonLayout) {
    
    attackAction = attackerButton3.getText();
    
    turnLabel.setText("Defender's turn");
    playerTurn = false;
    
    combatCheck(attackerInfo, defenderInfo, 
                attackerButton1, attackerButton2, attackerButton3, attackerButton4, 
                defenderButton1, defenderButton2, defenderButton3, defenderButton4, 
                combatLogLabel, attackerStun, defenderStun, attackerIV, defenderIV, attackerButtonLayout, defenderButtonLayout);
    defenderCombatCheck(attackerInfo, defenderInfo, 
                        attackerButton1, attackerButton2, attackerButton3, attackerButton4, 
                        defenderButton1, defenderButton2, defenderButton3, defenderButton4, 
                        combatLogLabel, attackerStun, defenderStun, attackerIV, defenderIV, attackerButtonLayout, defenderButtonLayout);
  }
  public void attackerButton4Clicked (Label attackerInfo, Label defenderInfo, 
                                      Button attackerButton1, Button attackerButton2, Button attackerButton3, Button attackerButton4, 
                                      Button defenderButton1, Button defenderButton2, Button defenderButton3, Button defenderButton4, 
                                      Label combatLogLabel, Label attackerStun, Label defenderStun, ImageView attackerIV, ImageView defenderIV, HBox attackerButtonLayout, HBox defenderButtonLayout) {
    
    attackerButton4.setDisable(true);
    
    attackAction = attackerButton4.getText();
    
    turnLabel.setText("Defender's turn");
    playerTurn = false;
    
    combatCheck(attackerInfo, defenderInfo, 
                attackerButton1, attackerButton2, attackerButton3, attackerButton4, 
                defenderButton1, defenderButton2, defenderButton3, defenderButton4, 
                combatLogLabel, attackerStun, defenderStun, attackerIV, defenderIV, attackerButtonLayout, defenderButtonLayout);
    defenderCombatCheck(attackerInfo, defenderInfo, 
                        attackerButton1, attackerButton2, attackerButton3, attackerButton4, 
                        defenderButton1, defenderButton2, defenderButton3, defenderButton4, 
                        combatLogLabel, attackerStun, defenderStun, attackerIV, defenderIV, attackerButtonLayout, defenderButtonLayout);
  }
  public void defenderButton1Clicked (Label attackerInfo, Label defenderInfo, 
                                      Button attackerButton1, Button attackerButton2, Button attackerButton3, Button attackerButton4, 
                                      Button defenderButton1, Button defenderButton2, Button defenderButton3, Button defenderButton4, 
                                      Label combatLogLabel, Label attackerStun, Label defenderStun, ImageView attackerIV, ImageView defenderIV, HBox attackerButtonLayout, HBox defenderButtonLayout) {
    
    defendAction = defenderButton1.getText();
    
    turnLabel.setText("Attacker's turn");
    playerTurn = true;
    
    combatCheck(attackerInfo, defenderInfo, 
                attackerButton1, attackerButton2, attackerButton3, attackerButton4, 
                defenderButton1, defenderButton2, defenderButton3, defenderButton4, 
                combatLogLabel, attackerStun, defenderStun, attackerIV, defenderIV, attackerButtonLayout, defenderButtonLayout);
    attackerCombatCheck(attackerInfo, defenderInfo, 
                        attackerButton1, attackerButton2, attackerButton3, attackerButton4, 
                        defenderButton1, defenderButton2, defenderButton3, defenderButton4, 
                        combatLogLabel, attackerStun, defenderStun, attackerIV, defenderIV, attackerButtonLayout, defenderButtonLayout);
  }
  public void defenderButton2Clicked (Label attackerInfo, Label defenderInfo, 
                                      Button attackerButton1, Button attackerButton2, Button attackerButton3, Button attackerButton4, 
                                      Button defenderButton1, Button defenderButton2, Button defenderButton3, Button defenderButton4, 
                                      Label combatLogLabel, Label attackerStun, Label defenderStun, ImageView attackerIV, ImageView defenderIV, HBox attackerButtonLayout, HBox defenderButtonLayout) {
    
    defendAction = defenderButton2.getText();
    
    turnLabel.setText("Attacker's turn");
    playerTurn = true;
    
    combatCheck(attackerInfo, defenderInfo, 
                attackerButton1, attackerButton2, attackerButton3, attackerButton4, 
                defenderButton1, defenderButton2, defenderButton3, defenderButton4, 
                combatLogLabel, attackerStun, defenderStun, attackerIV, defenderIV, attackerButtonLayout, defenderButtonLayout);
    attackerCombatCheck(attackerInfo, defenderInfo, 
                        attackerButton1, attackerButton2, attackerButton3, attackerButton4, 
                        defenderButton1, defenderButton2, defenderButton3, defenderButton4, 
                        combatLogLabel, attackerStun, defenderStun, attackerIV, defenderIV, attackerButtonLayout, defenderButtonLayout);
  }
  public void defenderButton3Clicked (Label attackerInfo, Label defenderInfo, 
                                      Button attackerButton1, Button attackerButton2, Button attackerButton3, Button attackerButton4, 
                                      Button defenderButton1, Button defenderButton2, Button defenderButton3, Button defenderButton4,
                                      Label combatLogLabel, Label attackerStun, Label defenderStun, ImageView attackerIV, ImageView defenderIV,
                                      HBox attackerButtonLayout, HBox defenderButtonLayout) {
    
    defendAction = defenderButton3.getText();
    
    turnLabel.setText("Attacker's turn");
    playerTurn = true;
    
    combatCheck(attackerInfo, defenderInfo, 
                attackerButton1, attackerButton2, attackerButton3, attackerButton4, 
                defenderButton1, defenderButton2, defenderButton3, defenderButton4, 
                combatLogLabel, attackerStun, defenderStun, attackerIV, defenderIV, attackerButtonLayout, defenderButtonLayout);
    attackerCombatCheck(attackerInfo, defenderInfo, 
                        attackerButton1, attackerButton2, attackerButton3, attackerButton4, 
                        defenderButton1, defenderButton2, defenderButton3, defenderButton4, 
                        combatLogLabel, attackerStun, defenderStun, attackerIV, defenderIV, attackerButtonLayout, defenderButtonLayout);
  }
  public void defenderButton4Clicked (Label attackerInfo, Label defenderInfo, 
                                      Button attackerButton1, Button attackerButton2, Button attackerButton3, Button attackerButton4, 
                                      Button defenderButton1, Button defenderButton2, Button defenderButton3, Button defenderButton4, 
                                      Label combatLogLabel, Label attackerStun, Label defenderStun, ImageView attackerIV, ImageView defenderIV,
                                      HBox attackerButtonLayout, HBox defenderButtonLayout) {
    
    defenderButton4.setDisable(true);
    
    defendAction = defenderButton4.getText();
    
    turnLabel.setText("Attacker's turn");
    playerTurn = true;
    
    combatCheck(attackerInfo, defenderInfo, 
                attackerButton1, attackerButton2, attackerButton3, attackerButton4, 
                defenderButton1, defenderButton2, defenderButton3, defenderButton4, 
                combatLogLabel, attackerStun, defenderStun, attackerIV, defenderIV, attackerButtonLayout, defenderButtonLayout);
    attackerCombatCheck(attackerInfo, defenderInfo, 
                        attackerButton1, attackerButton2, attackerButton3, attackerButton4, 
                        defenderButton1, defenderButton2, defenderButton3, defenderButton4, 
                        combatLogLabel, attackerStun, defenderStun, attackerIV, defenderIV, attackerButtonLayout, defenderButtonLayout);
  }
  public void attackerCombatCheck (Label attackerInfo, Label defenderInfo, 
                                   Button attackerButton1, Button attackerButton2, Button attackerButton3, Button attackerButton4, 
                                   Button defenderButton1, Button defenderButton2, Button defenderButton3, Button defenderButton4, 
                                   Label combatLogLabel, Label attackerStun, Label defenderStun, ImageView attackerIV, ImageView defenderIV, 
                                   HBox attackerButtonLayout, HBox defenderButtonLayout) {
    
    System.out.println("attacker stun: " + attacker.getStun());
    if (attacker.getStun() == true && attacker.getHealth() > 0) {
      attackerStun.setText("STUNNED");
    }
    else {
      attackerStun.setText("");
    }
    
    if (attacker.getSpecialMeter() == 3 && playerTurn == true) {
      attackerButton4.setDisable(false);
    }
    else {
      attackerButton4.setDisable(true);
    }
    
    System.out.println("Attacker Health: " + attacker.getHealth());
    if (attacker.getHealth() <= 0) {
      combatWinner = defender;
      turnLabel.setText("Defender wins");
      combatRoot.getChildren().removeAll(attackerButtonLayout, defenderButtonLayout, attackerIV);
      Button continueButton = new Button("Continue");
      continueButton.relocate(800, 300);
      continueButton.setOnAction(e -> {
        // get a handle to the stage
        Stage stage = (Stage) continueButton.getScene().getWindow();
        stage.close();
        attackerButtonLayout.getChildren().clear();
        defenderButtonLayout.getChildren().clear();
        combatRoot.getChildren().removeAll(combatRoot.getChildren());
      });
      combatRoot.getChildren().add(continueButton);
    }
  }
  public void defenderCombatCheck (Label attackerInfo, Label defenderInfo, 
                                   Button attackerButton1, Button attackerButton2, Button attackerButton3, Button attackerButton4, 
                                   Button defenderButton1, Button defenderButton2, Button defenderButton3, Button defenderButton4, 
                                   Label combatLogLabel, Label attackerStun, Label defenderStun, ImageView attackerIV, ImageView defenderIV, HBox attackerButtonLayout, HBox defenderButtonLayout) {
    
    System.out.println("defender stun: " + defender.getStun());
    if (defender.getStun() == true && defender.getHealth() > 0) {
      defenderStun.setText("STUNNED");
    }
    else {
      defenderStun.setText("");
    }
    
    if (defender.getSpecialMeter() == 3 && playerTurn == false) {
      defenderButton4.setDisable(false);
    }
    else {
      defenderButton4.setDisable(true);
    }
    
    System.out.println("Defender Health: " + defender.getHealth());
    if (defender.getHealth() <= 0) {
      if (defender instanceof Troop){
        combatWinner = attacker;
        combatRoot.getChildren().removeAll(attackerButtonLayout, defenderButtonLayout, defenderIV);
        turnLabel.setText("Attacker wins");
        Button continueButton = new Button("Continue");
        continueButton.relocate(800, 300);
        continueButton.setOnAction(e -> {
          // get a handle to the stage
          Stage stage = (Stage) continueButton.getScene().getWindow();
          stage.close();
          attackerButtonLayout.getChildren().clear();
          defenderButtonLayout.getChildren().clear();
          combatRoot.getChildren().removeAll(combatRoot.getChildren());
        });
        combatRoot.getChildren().add(continueButton);
      }
      else if (defender instanceof GoldMine){
        defender.setTeam("None");
      }
      else if (defender instanceof Castle){
        //Put variable or do something to indicate that player loses or wins since castle was destroyed
      }
    }
  }
  public void archerButtonClicked () {
    
  }
  public void knightButtonClicked () {
    
  }
  public void footManButtonClicked () {
    
  }
  public void cavalryButtonClicked () {
    
  }
  public void cbbButtonClicked () {
    
  }
  
  public void displayCombatLog (ArrayList<String> combatLog, Label combatLogLabel) {
    String temp = "";
    for (int i = 0; i < combatLog.size(); i++) {
      temp = temp + combatLog.get(i) + "\n";
    }
    combatLogLabel.setText("Combat Log:" + "\n" + temp);
    combatLogLabel.relocate(500, 100);
  }
  
  public static void main(String[] args) {
    launch(args);
  }
}
