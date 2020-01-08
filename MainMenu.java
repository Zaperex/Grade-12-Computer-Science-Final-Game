/* Name: Sean Hua
 * Date: 2019/12/30
 * Description: Main Menu for the game
 * */


//Still have to create a back to main menu button


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
import java.util.*;

public class MainMenu extends Application {
  Pane root = new Pane();
  Scene mainMenu = new Scene(root, 600, 600);
  public static boolean turn = true; //Boolean value for whose turn it is. (P1 = True) (P2 = False)
  
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
    //Give the button a function when it is pressed
    newGameButton.setOnAction(e -> newGameButtonClicked(primaryStage));
    
    //Create a button that will load the previous game
    Button loadGameButton = new Button("Load Game");
    //Sets the position of the button
    loadGameButton.relocate(225, 450);
    //Sets the size of the button
    loadGameButton.setPrefSize(150, 25);
    //Give the button a function when it is pressed
    loadGameButton.setOnAction(e -> loadGameButtonClicked());
    
    //Create a button that will show the instructions
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
    
    root.getChildren().addAll(rect1, rect2, title, buttonLayout);
    primaryStage.setTitle("Game");
    primaryStage.setScene(mainMenu);
    primaryStage.show();
  }
  //Method that will start a new game
  public void newGameButtonClicked (Stage primaryStage) {
    
    Pane root = new Pane();
    Scene gameScene = new Scene(root, 1050, 675);
    
    //Create a 2D array of buttons (represents the map)
    Button[][] button = new Button[7][7];
    
    //USED FOR TESTING ONLY *************************
    Terrain[][] terrain = new Terrain[7][7];
    
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
    
//    Button archerButton = new Button("Archer");----------------------------------------------------------------------------------------------------
//    Button knightButton = new Button("Knight");
//    Button cbmButton = new Button("Crossbow Men");
//    Button footManButton = new Button("Foot Man");
//    Button calvaryButton = new Button("Calvary");
//    
//    HBox troopButtonLayout = new HBox(15);
//    troopButtonLayout.relocate(640, 550);
//    troopButtonLayout.getChildren().addAll(archerButton, knightButton, cbmButton, footManButton, calvaryButton);
    
    //Populate the 2d array of buttons
    for (int i = 0; i < 7; i++) {
      for (int j = 0; j < 7; j++) {
        //Create a new button 
        button[j][i] = new Button("");
        //Set the size of the button
        button[j][i].setPrefSize(75, 75);
        //Puts the button in the correct spot of the window
        button[j][i].setLayoutX(i*75 + 75);
        button[j][i].setLayoutY(j*75 + 75);
        
        //Array used to store the coordinates of the button
        int[] coords = {i, j}; //Based on arraylist orientation with y controlling which row and x controlling which column
        int x = j;
        int y = i;
        
        if (j == 0 && i == 0) {
          terrain[j][i] = new Terrain("Swamp", coords);
          terrain[j][i].setBuilding(new Building("Home base", 100.0, 50.0, 100.0, true, coords));
        }
        else if (j == 6 && i == 6){
          
          //VALUES USED FOR TESTING *******************************
          terrain[j][i] = new Terrain("Plain", coords);
          terrain[j][i].setBuilding(new Building("Enemy Base", 100.0, 50.0, 100.0, false, coords));
          terrain[j][i].setTroop(new Archer(false, coords));
        }
        else if (j == 4 && i == 4){
          terrain[j][i] = new Terrain("Plain", coords);
          terrain[j][i].setTroop(new Knight(true, coords));
        }
        else if (j == 3 && i == 5){
          terrain[j][i] = new Terrain("Plain", coords);
          terrain[j][i].setTroop(new Knight(true, coords));
        }
        else{
          terrain[j][i] = new Terrain("Plain", coords);
          
        }
        //Give the button a function
        // - when the button is pressed, the lambda expression will be executed
        button[j][i].setOnAction(e -> { 
          
          //Remove the previous boxes and labels to prevent any overlapping
          root.getChildren().removeAll(buildingBox, buildingInfo, troopBox, troopInfo, terrainBox, terrainInfo);
          
          //Checks if a building exists on the selected button/tile
          if (terrain[y][x].getBuilding() != null) {
            //Call the checkBuilding method
            checkBuilding(terrain, buildingBox, buildingInfo, x, y, root);
          }
          //Checks if a troop exists on the selected button/tile
          if (terrain[y][x].getTroop() != null) {
            //Call the checkTroop method
            checkTroop(terrain, troopBox, troopInfo, x, y, root);
            ArrayList<int[]> possibleMoves = terrain[y][x].getTroop().findMoves(terrain);
            int[] moves = new int[2];
            for (int k = 0; k < possibleMoves.size(); k++) {
              moves = possibleMoves.get(k);
              System.out.println(moves[0] + " " + moves[1]);
              button[moves[0]][moves[1]].setStyle("-fx-background-color: red;" + "-fx-text-fill: white");
            } 
            
//button.setStyle(null);
            
          }
          //Call the check terrain method
          checkTerrain(terrain, terrainBox, terrainInfo, x, y, root);
          
          if ((x == 0 && y == 0 || x == 6 && y == 6) && terrain[y][x].getBuilding().getTeam() == turn) {
            Label recruitmentLabel = new Label("Recruitment:");
            
            Button archerButton = new Button("Archer");
            Button knightButton = new Button("Knight");
            Button cbmButton = new Button("Crossbow Men");
            Button footManButton = new Button("Foot Man");
            Button calvaryButton = new Button("Calvary");
            
            HBox troopButtonLayout = new HBox(15);
            troopButtonLayout.getChildren().addAll(archerButton, knightButton, cbmButton, footManButton, calvaryButton);
            
            VBox recruitmentLayout = new VBox();
            recruitmentLayout.relocate(640, 550);
            recruitmentLayout.getChildren().add(troopButtonLayout);
            
            
            //Checks level of the castle (3 is the max level)
            if (terrain[y][x].getBuilding() instanceof Castle) {
              if (((Castle)terrain[y][x].getBuilding()).getLevel() < 3){
                Button upgradeButton = new Button("Upgrade: " + Castle.getUpgradeCost());
                recruitmentLayout.getChildren().add(upgradeButton);
              }
            }
            
            root.getChildren().add(recruitmentLayout);
          }
        });
        
        //Creates the board/map by adding the button
        root.getChildren().addAll(button[j][i]);
      }
    }
//    root.getChildren().add(troopButtonLayout); --------------------------------------------------------------------------
    
    primaryStage.setScene(gameScene);
    primaryStage.show();
    
  }
  //Method that will load the previous game
  public void loadGameButtonClicked () {
    
    
    
    
    
    
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
    
    primaryStage.setScene(mainMenu);
    
  }
  
  public void checkBuilding (Terrain[][] terrain, Rectangle buildingBox, Label buildingInfo, int x, int y, Pane root) {
    
    //Sets the position of the buildingBox
    buildingBox.relocate(675, 75);
    //Set the opacity
    buildingBox.setOpacity(0.5);
    //If the building is on the enemy team
    if (terrain[y][x].getBuilding().getTeam() == false) {
      //Set the color of the buildingBox to red
      buildingBox.setFill(Color.RED);
    }
    //If the building is on the player's team
    else {
      //Set the color of the buildingBox to blue
      buildingBox.setFill(Color.BLUE);
    }
    //Gather information about the building
    buildingInfo.setText("Building\n" + 
                         "Name: " + terrain[y][x].getBuilding().getName() + "\n" +
                         "Health: " + terrain[y][x].getBuilding().getHealth() + "\n" + 
                         "Attack: " + terrain[y][x].getBuilding().getAttack() + "\n" + 
                         "Range: " + terrain[y][x].getBuilding().getRange());
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
    if (terrain[y][x].getTroop().getTeam() == false) {
      //Set the troopBox to red
      troopBox.setFill(Color.RED);
    }
    //If the troop is on the player's team
    else {
      //Set the troopBox to blue
      troopBox.setFill(Color.BLUE);
    }
    //Gather information about the troop
    troopInfo.setText("Troop\n" + "Name: " + terrain[y][x].getTroop().getName() + "\n" + 
                      "Health: " + terrain[y][x].getTroop().getHealth() + "\n" + 
                      "Attack: " + terrain[y][x].getTroop().getAttack() + "\n" + 
                      "Range: " + terrain[y][x].getTroop().getRange());
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
    terrainInfo.setText("Terrain type: " + terrain[y][x].getType());
    terrainInfo.relocate(800, 425);
    root.getChildren().addAll(terrainBox, terrainInfo);
  }
  
  public static void main(String[] args) {
    launch(args);
  }
}
