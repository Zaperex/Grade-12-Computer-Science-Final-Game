/* Name: Sean Hua
 * Date: 2019/12/30
 * Description: Main Menu for the game
 * */

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
    
    //Creatre a button 
    Button startButton = new Button("Start Game");
    //Sets the initial position of the button
    startButton.setLayoutX(-50);
    startButton.setLayoutY(300);
    //Give the startButton a function (when it is clicked, it will call the startButtonClicked method)
    startButton.setOnAction(e -> startButtonClicked(primaryStage));
    
    //Transition  that will move the button to the right
    TranslateTransition moveButton = new TranslateTransition(Duration.seconds(2), startButton);
    //Sets where the button will move to
    moveButton.setToX(300);
    moveButton.setToY(0);
    //The transition will repeat once
    moveButton.setCycleCount(1);
    moveButton.play();
    
    Pane root = new Pane();
    
    Scene mainMenu = new Scene(root, 600, 600);
    root.getChildren().addAll(rect1, rect2, startButton);
    primaryStage.setTitle("hello");
    primaryStage.setScene(mainMenu);
    primaryStage.show();
  }
  //Method that wil change the scene and will bring the player to the game window
  public void startButtonClicked (Stage primaryStage) {
    //Doesn't do anything yet
  }
  
  public static void main(String[] args) {
    launch(args);
  }
}