/* Name: Sean Hua
 * Date: 2019/12/27
 * Description: The animations for the game
 **/
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;


import javafx.geometry.Point2D;
import javafx.beans.value.ObservableBooleanValue;
import java.util.concurrent.Callable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//every 5 pixel is 1 meter
//start 500 pixels away from each other


//figure out a way to get my fields into animations class

//figure out how to make the shape stop midway or change the movement 
//Make the rectangle a red square
//test the fading with images

public class Animations extends Application {
  
  
  public void start (Stage primaryStage) {
    
    //Rectangle that will show up when the shapes collide
    Image hitMarker1 = new Image("kapow.png");
    //Sets the colour of the rectangle
    ImageView ivHit1 = new ImageView();
    ivHit1.setImage(hitMarker1);
    ivHit1.setFitWidth(75);
    ivHit1.setPreserveRatio(true);
    ivHit1.setLayoutX(50);
    ivHit1.setLayoutY(200);
    
    //This is the transition for the shape (rectangle) that will show up when two shapes collide
    TranslateTransition attackTransition1 = new TranslateTransition();
    //Set how long one cycle will take
    attackTransition1.setDuration(Duration.seconds(4));
    //Set the node (what troop will have the transition)
    attackTransition1.setNode(ivHit1);
    //Set the end position of the troop
    attackTransition1.setToX(500);
    attackTransition1.setToY(0);
    //Make the troop be able to go back to its previous position (goes in both directions)
    attackTransition1.setAutoReverse(true);
    //Sets the number of cycles it will complete
    attackTransition1.setCycleCount(5);
    //Starts the transition
    attackTransition1.play();
    
    //The first "troop"
    Image knight = new Image("knight.png");
    ImageView iv1 = new ImageView();
    iv1.setImage(knight);
    iv1.setFitWidth(100);
    iv1.setPreserveRatio(true);
    iv1.setLayoutX(50);
    iv1.setLayoutY(250);
    
    
    //The troop's movement (it will move from left to right corner of the window)
    TranslateTransition transition1 = new TranslateTransition();
    //Sets how long it will take to finish 1 cycle
    transition1.setDuration(Duration.seconds(4));
    //Sets the node 
    transition1.setNode(iv1);
    //Sets the end position
    transition1.setToX(500);
    transition1.setToY(0);
    //Makes the troop go in both direction (forward and reverse)
    transition1.setAutoReverse(true);
    //Sets the number of cycles
    transition1.setCycleCount(5);
    //Starts the transition
    transition1.play();
    
    Image hitMarker2 = new Image("kapow.png");
    ImageView ivHit2 = new ImageView();
    ivHit2.setImage(hitMarker2);
    ivHit2.setFitWidth(75);
    ivHit2.setPreserveRatio(true);
    ivHit2.setLayoutX(550);
    ivHit2.setLayoutY(200);
    
    //This is the transition for the shape (rectangle) that will show up when two shapes collide
    TranslateTransition attackTransition2 = new TranslateTransition();
    //Sets how long it takes to complete 1 cycle of the transition
    attackTransition2.setDuration(Duration.seconds(4));
    //Set the node
    attackTransition2.setNode(ivHit2);
    //Set end position
    attackTransition2.setToX(-550);
    attackTransition2.setToY(0);
    //Make the troop be able to move in both reverse and forward direction
    attackTransition2.setAutoReverse(true);
    //Sets the number of cycles 
    attackTransition2.setCycleCount(5);
    //Starts the transition
    attackTransition2.play();
    
    //The second "Troop"
    Image troop2 = new Image("archer.png");
    //Sets the colour
    ImageView iv2 = new ImageView();
    iv2.setImage(troop2);
    iv2.setFitWidth(100);
    iv2.setPreserveRatio(true);
    
    iv2.setLayoutX(500);
    iv2.setLayoutY(250);
    
    //The troop's movement (it will move from left to right corner of the window)
    TranslateTransition transition2 = new TranslateTransition();
    //Sets how long it takes for 1 cycle to be completed
    transition2.setDuration(Duration.seconds(4));
    //Set the node
    transition2.setNode(iv2);
    //Set the end position
    transition2.setToX(-550);
    transition2.setToY(0);
    //Make the troop move in both forward and backwards direction
    transition2.setAutoReverse(true);
    //Set the number of cycles
    transition2.setCycleCount(5);
    //Start the transition
    transition2.play();
    
    //Checks if the two shapes collide by checking the position of the shape (checks if the x values of the troop changes)
    //obs is the x coordinate (the thing that is being observed/referenced 
    // so if the x coordinate changes, then the lambda below will be executed
    //Oldvalue is the old x coordinate
    //NewValue is the new x coordinate
    iv1.translateXProperty().addListener((obs, oldValue, newValue) -> {
      //x is the x coordinate of the troop
      //y is the y coordinate of the troop
      double x1 = (iv1.getLayoutX() + iv1.translateXProperty().doubleValue());
      double x2 = (iv2.getLayoutX() + iv2.translateXProperty().doubleValue());
      double y1 = (iv1.getLayoutY() - iv1.translateYProperty().doubleValue());
      double y2 = (iv2.getLayoutY() - iv2.translateYProperty().doubleValue());
      //dx is the distance for x values between the troops
      //dy is the distance for y values between the troops
      double dx = x2 - x1;
      double dy = y2 - y1;
      //distance is the overall distance between the two troops
      double distance = Math.sqrt(dx * dx + dy * dy);
      System.out.println("circle1 x : " + (iv1.getLayoutX() + iv1.translateXProperty().doubleValue()));
      System.out.println("circle1 y : " + (iv1.getLayoutY() + iv1.translateYProperty().doubleValue()));
      System.out.println("circle2 x : " + (iv2.getLayoutX() - iv2.translateXProperty().doubleValue()));
      System.out.println("circle2 y : " + (iv2.getLayoutY() - iv2.translateYProperty().doubleValue()));
      System.out.println("distance : " + distance);
      //knightStopRange is used to determine when/how far away the knight will stop
      double knightStopRange = 150;
      //If another troop is in range with the knight
      if (distance <= knightStopRange) {
        //Make rect1 visible
        ivHit1.setOpacity(1);
//        Stop the first troop
        transition1.stop();
        attackTransition1.stop();
      } 
      else {
        //make rect1 invisible
        ivHit1.setOpacity(0);
      }
    });
    
    //Checks if the two shapes collide by checking the position of the shape
    iv2.translateXProperty().addListener((obs, oldValue, newValue) -> {
      //x is the x coordinate of the troop
      //y is the y coordinate of the troop
      double x1 = (iv1.getLayoutX() + iv1.translateXProperty().doubleValue());
      double x2 = (iv2.getLayoutX() + iv2.translateXProperty().doubleValue());
      double y1 = (iv1.getLayoutY() - iv1.translateYProperty().doubleValue());
      double y2 = (iv2.getLayoutY() - iv2.translateYProperty().doubleValue());
      //dx is the distance for x values between the troops
      //dy is the distance for y values between the troops
      double dx = x2 - x1;
      double dy = y2 - y1;
      //distance is the overall distance between the two troops
      double distance = Math.sqrt(dx * dx + dy * dy);
      System.out.println("circle1 x : " + (iv1.getLayoutX() + iv1.translateXProperty().doubleValue()));
      System.out.println("circle1 y : " + (iv1.getLayoutY() + iv1.translateYProperty().doubleValue()));
      System.out.println("circle2 x : " + (iv2.getLayoutX() - iv2.translateXProperty().doubleValue()));
      System.out.println("circle2 y : " + (iv2.getLayoutY() - iv2.translateYProperty().doubleValue()));
      System.out.println("distance : " + distance);
      //archerStopRange is used to determine when/how far away the archer will stop
      double archerStopRange = 350;
      //If the another troop is in range of the archer
      if (distance <= archerStopRange) {
        //Make ivHit2 visible
        ivHit2.setOpacity(1);
        //Stop the first troop
        transition2.stop();
        attackTransition2.stop();
      } 
      else {
        //make ivHit2 invisible
        ivHit2.setOpacity(0);
        //Start or continue transition
      }
    });
    
    
    Pane root = new Pane();
    root.getChildren().addAll(iv1, iv2, ivHit1, ivHit2); 
    
    Scene scene = new Scene(root, 600, 600);
    
    primaryStage.setTitle("Animations!");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  
  public static void main (String[] args) {
    launch(args);
  }
}