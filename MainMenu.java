/* Name: Sean Hua
 * Date: 2019/12/30
 * Description: Main Menu for the game
 * */


//Still have to create a back to main menu button


import javafx.application.Application;
import javafx.scene.Scene;
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

public class MainMenu extends Application {
  
  public void start (Stage primaryStage) throws Exception {
    
    //Create a rectangle
    Rectangle rect1 = new Rectangle(0, 0, 600, 1);
    //Set the colour to blue
    rect1.setFill(Color.BLUE);
    
    //Create another rectangle
    Rectangle rect2 = new Rectangle(0, 599, 600, 1);
    //Set the colour to yellow
    rect2.setFill(Color.YELLOW);
    
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
    loadGameButton.setPrefSize(150,25);
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
    
    Pane root = new Pane();
    
    Scene mainMenu = new Scene(root, 600, 600);
    root.getChildren().addAll(rect1, rect2, newGameButton, loadGameButton, instructionsButton);
    primaryStage.setTitle("Game");
    primaryStage.setScene(mainMenu);
    primaryStage.show();
  }
  //Method that will start a new game
  public void newGameButtonClicked (Stage primaryStage) {
    
    Pane root = new Pane();
    Scene gameScene = new Scene(root, 1275, 675);
    
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
        int[] coords = {i, j};
        final int x = i;
        final int y = j;
        
        if (i == 0 && j == 0) {
          terrain[i][j] = new Terrain("Swamp", coords);
          terrain[i][j].setBuilding(new Building("Home base", 100.0, 50.0, 100.0, true, coords));
        }
        else {
        
          //VALUES USED FOR TESTING *******************************
          terrain[i][j] = new Terrain("Plain", coords);
          terrain[i][j].setBuilding(new Building("Enemy Base", 100.0, 50.0, 100.0, false, coords));
          terrain[i][j].setTroop(new Archer(true, coords));
        }
        //Give the button a function
        // - when the button is pressed, the lambda expression will be executed
        button[i][j].setOnAction(e -> { 
          
          //Remove the previous boxes and labels to prevent any overlapping
          root.getChildren().removeAll(buildingBox, buildingInfo, troopBox, troopInfo, terrainBox, terrainInfo);
          
          //Checks if a building exists on the selected button/tile
          if (terrain[x][y].getBuilding() != null) {
            //Call the checkBuilding method
            checkBuilding(terrain, buildingBox, buildingInfo, x, y, root);
          }
          //Checks if a troop exists on the selected button/tile
          if (terrain[x][y].getTroop() != null) {
            //Call the checkTroop method
            checkTroop(terrain, troopBox, troopInfo, x, y, root);
          }
          //Call the check terrain method
          checkTerrain(terrain, terrainBox, terrainInfo, x, y, root);
          
        });
        
        //Creates the board/map by adding the button
        root.getChildren().add(button[i][j]);
      }
    }
    
    primaryStage.setScene(gameScene);
    primaryStage.show();
    
  }
  //Method that will load the previous game
  public void loadGameButtonClicked () {
    
  }
  //Method that will bring the user to an instructions page/window
  public void instructionsButtonClicked (Stage primaryStage) {
    
    Label instructions = new Label("1. first instruction\n" + "2. second instruction\n" + "3. third instruction\n");
//    instructions.relocate(225, 300);
    
    Pane root = new Pane();
    root.getChildren().add(instructions);
    
    Scene instructionsScene = new Scene(root, 400, 300);
    
    primaryStage.setScene(instructionsScene);
    primaryStage.show();
    
  }
  
  public void checkBuilding (Terrain[][] terrain, Rectangle buildingBox, Label buildingInfo, int x, int y, Pane root) {
    
    //Sets the position of the buildingBox
    buildingBox.relocate(700, 100);
    //Set the opacity
    buildingBox.setOpacity(0.5);
    //If the building is on the enemy team
    if (terrain[x][y].getBuilding().getTeam() == false) {
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
                        "Health: " + terrain[x][y].getBuilding().getHealth() + "\n" + 
                        "Attack: " + terrain[x][y].getBuilding().getAttack() + "\n" + 
                        "Range: " + terrain[x][y].getBuilding().getRange());
    //Set the position of the building's information
    buildingInfo.relocate(800, 125);
    
    root.getChildren().addAll(buildingBox, buildingInfo);
  }
  public void checkTroop (Terrain[][] terrain, Rectangle troopBox, Label troopInfo, int x, int y, Pane root) {

    //Set the position of the troopBox
    troopBox.relocate(700, 300);
    //Set the opacity
    troopBox.setOpacity(0.5);
    //If the troop is on the enemy team
    if (terrain[x][y].getTroop().getTeam() == false) {
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
    troopInfo.relocate(800, 325);
    
    root.getChildren().addAll(troopBox, troopInfo);
  }
  public void checkTerrain (Terrain[][] terrain, Rectangle terrainBox, Label terrainInfo, int x, int y, Pane root) {
    
    //Set the position of the terrainBox
    terrainBox.relocate(700, 475);
    //Set the color of the terrainBox
    terrainBox.setFill(Color.TRANSPARENT);
    terrainBox.setStroke(Color.BLACK);
    terrainInfo.setText("Terrain type: " + terrain[x][y].getType());
    terrainInfo.relocate(800, 475);
    root.getChildren().addAll(terrainBox, terrainInfo);
  }
  
  public static void main(String[] args) {
    launch(args);
  }
}