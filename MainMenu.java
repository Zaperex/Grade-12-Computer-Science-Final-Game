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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    newGameButton.setLayoutX(150);
    newGameButton.setLayoutY(300);
    //Give the button a function when it is pressed
    newGameButton.setOnAction(e -> newGameButtonClicked(primaryStage));
    
    //Create a button that will load the previous game
    Button loadGameButton = new Button("Load Game");
    //Sets the position of the button
    loadGameButton.setLayoutX(350);
    loadGameButton.setLayoutY(300);
    //Give the button a function when it is pressed
    loadGameButton.setOnAction(e -> loadGameButtonClicked());
    
    Pane root = new Pane();
    
    Scene mainMenu = new Scene(root, 600, 600);
    root.getChildren().addAll(rect1, rect2, newGameButton, loadGameButton);
    primaryStage.setTitle("Game");
    primaryStage.setScene(mainMenu);
    primaryStage.show();
  }
  //Method that will start a new game
  public void newGameButtonClicked (Stage primaryStage) {
    
    Pane root = new Pane();
    Scene scene2 = new Scene(root, 675, 975);
    Button[][] button = new Button[7][7];
    
    for (int i = 0; i < 7; i++) {
      for (int j = 0; j < 7; j++) {
        button[i][j] = new Button("");
        button[i][j].setPrefSize(75, 75);
        button[i][j].setLayoutX(i*75 + 75);
        button[i][j].setLayoutY(j*75 + 75);
        
        if (i == 0 && j == 0) {
          button[i][j].setOnAction(e -> {
            System.out.println("enemy base");
          });
        }
        else if (i == 6 && j == 6) {
          button[i][j].setOnAction(e -> {
            System.out.println("home base");});
        }
        root.getChildren().add(button[i][j]);
      }
    }
    
    primaryStage.setScene(scene2);
    primaryStage.show();
    
  }
  //Method that will load the previous game
  public void loadGameButtonClicked () {
    
  }
  
  public static void main(String[] args) {
    launch(args);
  }
}