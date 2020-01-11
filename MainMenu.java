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
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import java.util.*;

public class MainMenu extends Application {
  private Pane root = new Pane();
  private Scene mainMenu = new Scene(root, 600, 600);
  private boolean turn = true; //Boolean value for whose turn it is. (P1 = True) (P2 = False)
  private int[] firstMoveCoords = new int[2];
  private boolean firstClick = true;
  private ArrayList<int[]> possibleMoves;  
  private ArrayList<int[]> possibleAttacks; 
  private boolean playerTurn = true; //True = player1, false = player2
  private Label turnLabel = new Label();
  private Combatant player1;  
  private Combatant player2;  
  private Pane combatRoot = new Pane();

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
  public void newGameButtonClicked (Stage primaryStage) {
    combatScreen();
  }
  //Method that will start a new game
  public void newGameButtonClicked1 (Stage primaryStage) {
    
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
    
    boolean attacker = true;
    int[] attackerCoords = new int[2];
    int[] defenderCoords = new int[2];
    
    
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
    for (int j = 0; j < 7; j++) {
      for (int i = 0; i < 7; i++) {
        //Create a new button 
        button[j][i] = new Button("");
        //Set the size of the button
        button[j][i].setPrefSize(75, 75);
        //Puts the button in the correct spot of the window
        button[j][i].setLayoutX(i*75 + 75); 
        button[j][i].setLayoutY(j*75 + 75);
        
        //Array used to store the coordinates of the button
        int[] coords = {i, j}; //Based on arraylist orientation with y controlling which row and x controlling which column
        int x = i;
        int y = j;
        
        if (y == 0 && x == 0) {
          terrain[j][i] = new Terrain("Swamp", coords);
          terrain[j][i].setBuilding(new Castle("P1", coords));
        }
        else if (y == 6 && x == 6){
          
          //VALUES USED FOR TESTING *******************************
          terrain[j][i] = new Terrain("Plain", coords);
          terrain[j][i].setBuilding(new Castle("P2", coords));
        }
        else if (y == 3 && x == 6){
          terrain[j][i] = new Terrain("Plain", coords);
          terrain[j][i].setTroop(new Archer("P2", coords));
        }
        else if (y == 3 && x == 4){
          terrain[j][i] = new Terrain("Plain", coords);
          terrain[j][i].setTroop(new Knight("P1", coords));
        }
        else if (y == 3 && x == 5){
          terrain[j][i] = new Terrain("Plain", coords);
          terrain[j][i].setTroop(new Knight("P1", coords));
        }
        else{
          terrain[j][i] = new Terrain("Plain", coords); 
        }
        
        
        
        
        
        
        //Give the button a function
        // - when the button is pressed, the lambda expression will be executed
        button[j][i].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
          public void handle(MouseEvent e) {
            
            System.out.println("CLICK COUNT: " + e.getClickCount());
            System.out.println(x + ", " + y);
            //Remove the previous boxes and labels to prevent any overlapping
            root.getChildren().removeAll(buildingBox, buildingInfo, troopBox, troopInfo, terrainBox, terrainInfo);
            
            //Checks if a building exists on the selected button/tile
            if (terrain[y][x].getBuilding() != null) {
              //Call the checkBuilding method
              checkBuilding(terrain, buildingBox, buildingInfo, x, y, root);
            }
            if (terrain[y][x].getTroop() != null) {
              //Call the checkTroop method
              checkTroop(terrain, troopBox, troopInfo, x, y, root);
            }
            //Call the check terrain method
            checkTerrain(terrain, terrainBox, terrainInfo, x, y, root);
            
            System.out.println("Top First Click: " + firstClick);
            
            if (e.getClickCount() == 2 && button[y][x].getStyle().equals("-fx-background-color: red;" + "-fx-text-fill: white")) {
              
//              combatScreen(terrain);
              combatScreen();
            }
            else if (e.getClickCount() == 1) {
              
              if (firstClick == true && terrain[y][x].getTroop() != null) {
                
                
                firstMoveCoords[0] = x;
                firstMoveCoords[1] = y;
                
//                System.out.println("CLICK COUNT: " + e.getClickCount());
//                System.out.println(x + ", " + y);
//                //If the user single clicks
//                if (e.getClickCount() == 1) {
//                  attackerCoords[1] = x;
//                  attackerCoords[0] = y;
//                }
//                //If the user double clicks
//                else {
//                  defenderCoords[1] = x;
//                  defenderCoords[0] = y;
//                  combatScreen(terrain);
//                }            
                
                //Checks if a troop exists on the selected button/tile
                if (terrain[y][x].getTroop() != null) {
                  for (int l = 0; l < 7; l++) {
                    for (int m = 0; m < 7; m++) {
                      button[l][m].setDisable(true);
                    }
                  }
                  possibleMoves = terrain[y][x].getTroop().findMoves(terrain);
                  int[] move = new int[2];
                  for (int k = 0; k < possibleMoves.size(); k++) {
                    move = possibleMoves.get(k);
                    button[move[1]][move[0]].setStyle("-fx-background-color: blue;" + "-fx-text-fill: white");
                    button[move[1]][move[0]].setDisable(false);
                  }                 
                }
                
                if ((x == 0 && y == 0 || x == 6 && y == 6) && terrain[x][y].getBuilding() != null &&
                    terrain[y][x].getBuilding().getTeam() != null && terrain[y][x].getBuilding().getTeam().equals(turn)) {
                  Label recruitmentLabel = new Label("Recruitment:");
                  
                  Button archerButton = new Button("Archer");
                  Button knightButton = new Button("Knight");
                  Button cbmButton = new Button("Crossbow Men");
                  Button footManButton = new Button("Foot Man");
                  Button calvaryButton = new Button("Cavalry");
                  
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
                firstClick = false;
              }
              else if (firstClick == false && terrain[y][x].getTroop() == null) {
//                possibleMoves = terrain[firstMoveCoords[1]][firstMoveCoords[0]].getTroop().findMoves(terrain);
                for (int l = 0; l < 7; l++) {
                  for (int m = 0; m < 7; m++) {
                    button[l][m].setDisable(false);
                  }
                }
                int[] move = new int[2];
                for (int k = 0; k < possibleMoves.size(); k++) {
                  move = possibleMoves.get(k);
                  button[move[1]][move[0]].setStyle(null);
                }
                
                
                terrain[y][x].setTroop(terrain[firstMoveCoords[1]][firstMoveCoords[0]].getTroop());
                button[firstMoveCoords[1]][firstMoveCoords[0]].setGraphic(null);
                Image image = new Image(terrain[y][x].getTroop().getImageName(), 60, 60, false, false);
                button[y][x].setGraphic(new ImageView(image));
                terrain[firstMoveCoords[1]][firstMoveCoords[0]].getTroop().actualMove(y, x);
                
                possibleAttacks = terrain[y][x].getTroop().findAttacks(terrain);
                if (possibleAttacks.size() > 0) {
                  for (int l = 0; l < 7; l++) {
                    for (int m = 0; m < 7; m++) {
                      button[l][m].setDisable(true);
                    }
                  }
                }
                int[] attack = new int[2];
                for (int k = 0; k < possibleAttacks.size(); k++) {
                  attack = possibleAttacks.get(k);
                  button[attack[1]][attack[0]].setStyle("-fx-background-color: red;" + "-fx-text-fill: white");
                  button[attack[1]][attack[0]].setDisable(false);
                }
                firstClick = true;
              }
            }
          }
        });
        
        //Give the button a function
        // - when the button is pressed, the lambda expression will be executed
//        button[j][i].addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
//          public void handle(MouseEvent e) {
//            System.out.println("x: " + e.getX() + " y: " + e.getY());
//          }
//        });
        
        //Creates the board/map by adding the button
        root.getChildren().add(button[j][i]);
        //Checks if there is a troop on that tile
        if (terrain[j][i].getTroop() != null) {
          //Adds the image of the troop onto the tile
          Image image = new Image(terrain[j][i].getTroop().getImageName(), 60, 60, false, false);
          button[j][i].setGraphic(new ImageView(image));    
          
        }
        //Checks if there is a building 
        if (terrain[j][i].getBuilding() != null) {
          //Adds the image of the building onto the tile
          Image image = new Image(terrain[j][i].getBuilding().getImageName(), 60, 60, false, false);
          button[j][i].setGraphic(new ImageView(image));          
        }
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
    if (terrain[y][x].getBuilding().getTeam().equals("P2")) {
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
    if (terrain[y][x].getTroop().getTeam().equals("P2")) {
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
  public void combatScreen () {
    
    turnLabel.relocate(100, 50);
    turnLabel.setText("Player 1's Turn");
    
    player1 = new Archer("P1", firstMoveCoords);
    player2 = new Knight("P2", firstMoveCoords);
    
    Image player1Image = new Image(player1.getImageName(), 80, 80, false, false);
    ImageView player1IV = new ImageView(player1Image);
    player1IV.relocate(50, 150);
    
    Image player2Image = new Image(player2.getImageName(), 80, 80, false, false);
    ImageView player2IV = new ImageView(player2Image);
    player2IV.relocate(350, 150);
    
    Label player1Info = new Label(player1.getTeam() + " health: " + player1.getHealth());
    player1Info.relocate(350, 100);
    Label player2Info = new Label(player2.getTeam() + " health: " + player2.getHealth());
    player2Info.relocate(450, 100);
    
    Button attackButton = new Button("Attack");
    attackButton.setOnAction(e -> attackButtonClicked(player1, player2, player1Info, player2Info));
    Button blockButton = new Button("Block");
    blockButton.setOnAction(e -> blockButtonClicked(player1, player2, player1Info, player2Info));
    Button dodgeButton = new Button("Dodge");
    dodgeButton.setOnAction(e -> dodgeButtonClicked(player1, player2, player1Info, player2Info));
    Button specialButton = new Button("Special");
    specialButton.setOnAction(e -> specialButtonClicked(player1, player2, player1Info, player2Info));
    
    HBox combatButtonLayout = new HBox(10);
    combatButtonLayout.getChildren().addAll(attackButton, blockButton, dodgeButton, specialButton);
    combatButtonLayout.relocate(125, 300);
    
    System.out.println(player1.getTeam() + " health: " + player1.getHealth());
    System.out.println(player2.getTeam() + " health: " + player2.getHealth());
    
    combatRoot.getChildren().addAll(combatButtonLayout, player1IV, player2IV, turnLabel, player1Info, player2Info);
    
    Scene combatScene = new Scene(combatRoot, 600, 400);
    
    Stage combatStage = new Stage();
    combatStage.setScene(combatScene);
    combatStage.show();
  }
  public void attackButtonClicked (Combatant player1, Combatant player2, Label player1Info, Label player2Info) {
    if (playerTurn == true) {
      turnLabel.setText("Player 2's Turn");
      double damage = player1.attack(player2);
      player2.setHealth(player2.getHealth() - damage);
      playerTurn = false;
    }
    else {
      turnLabel.setText("Player 1's Turn");
      double damage = player2.attack(player1);
      player1.setHealth(player1.getHealth() - damage);
      player1Info.setText(player1.getTeam() + " health: " + player1.getHealth());
      player2Info.setText(player2.getTeam() + " health: " + player2.getHealth());
      playerTurn = true;
    }
    /*
    if (player1.getHealth() <= 0) {
      combatRoot.remove(player1);
    }
    else if (player2.getHealth() <= 0) {
      combatRoot.remove(player1);
    }
    */
  }
  public void blockButtonClicked (Combatant player, Combatant opponent, Label player1Info, Label player2Info) {
    
  }
  public void dodgeButtonClicked (Combatant player, Combatant opponent, Label player1Info, Label player2Info) {
    
  }
  public void specialButtonClicked (Combatant player, Combatant opponent, Label player1Info, Label player2Info) {
    
  }
  
  public static void main(String[] args) {
    launch(args);
  }
}
