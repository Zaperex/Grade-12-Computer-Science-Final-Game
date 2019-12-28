//PLEASE BE MORE SPECIFIC ON YOUR COMMENTING

import javafx.animation.TranslateTransition;
import javafx.animation.FadeTransition;
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
public class Animations extends Application {
  
  public void start (Stage primaryStage) {
    
    //Rectangle that will show up when the shapes collide
    Rectangle rect1 = new Rectangle();
    rect1.setFill(Color.ORANGE);
    rect1.setWidth(50);
    rect1.setHeight(15);
    rect1.relocate(50, 50);
    
    //This is the transition for the shape (rectangle) that will show up when two shapes collide
    TranslateTransition attackTransition1 = new TranslateTransition();
    attackTransition1.setDuration(Duration.seconds(2));
    attackTransition1.setNode(rect1);
    attackTransition1.setToX(500);
    attackTransition1.setToY(500);
    attackTransition1.setAutoReverse(true);
    attackTransition1.setCycleCount(5);
    attackTransition1.play();
    
    //The first "troop"
    Circle character1 = new Circle();
    character1.setFill(Color.BLUE);
    character1.setRadius(25);
    character1.relocate(50, 50);
    
    //Checks if the two shapes collide by checking the position of the shape
    character1.translateXProperty().addListener((obs, oldValue, newValue) -> {
      if ((newValue.intValue() >= 250) && (newValue.intValue() <= 275)) {
        rect1.setOpacity(1);
      } else {
        rect1.setOpacity(0);
      }
    });
    character1.translateYProperty().addListener((obs, oldValue, newValue) -> {
      if ((newValue.intValue() >= 250) && (newValue.intValue() <= 275)) {
        rect1.setOpacity(1);
      } else {
        rect1.setOpacity(0);
      }
    });
    
    //The troop's movement (it will move from left to right corner of the window)
    TranslateTransition transition1 = new TranslateTransition();
    transition1.setDuration(Duration.seconds(2));
    transition1.setNode(character1);
    transition1.setToX(500);
    transition1.setToY(500);
    transition1.setAutoReverse(true);
    transition1.setCycleCount(5);
    transition1.statusProperty().addListener((obs, oldValue, newValue) -> {
        System.out.println(newValue);
    });
    transition1.play();
    
    
    //Second rectangle for the second troop (Appears when the two shapes/troops collide)
    Rectangle rect2 = new Rectangle();
    rect2.setFill(Color.ORANGE);
    rect2.setWidth(50);
    rect2.setHeight(15);
    rect2.relocate(500, 500);
    
    //This is the transition for the shape (rectangle) that will show up when two shapes collide
    TranslateTransition attackTransition2 = new TranslateTransition();
    attackTransition2.setDuration(Duration.seconds(2));
    attackTransition2.setNode(rect2);
    attackTransition2.setToX(-500);
    attackTransition2.setToY(-500);
    attackTransition2.setAutoReverse(true);
    attackTransition2.setCycleCount(5);
    attackTransition2.play();
    
    //The second "Troop"
    Circle character2 = new Circle();
    character2.setFill(Color.RED);
    character2.setRadius(25);
    character2.relocate(500, 500);
    
    //Checks if the two shapes collide by checking the position of the shape
    character2.translateXProperty().addListener((obs, oldValue, newValue) -> {
      if ((newValue.intValue() <= -250) && (newValue.intValue() > -275)) {
        rect2.setOpacity(1);
      } else {
        rect2.setOpacity(0);
      }
    });
    character2.translateYProperty().addListener((obs, oldValue, newValue) -> {
      if ((newValue.intValue() <= -250) && (newValue.intValue() > -275)) {
        rect2.setOpacity(1);
      } else {
        rect2.setOpacity(0);
      }
    });
    
    //The troop's movement (it will move from left to right corner of the window)
    TranslateTransition transition2 = new TranslateTransition();
    transition2.setDuration(Duration.seconds(2));
    transition2.setNode(character2);
    transition2.setToX(-500);
    transition2.setToY(-500);
    transition2.setAutoReverse(true);
    transition2.setCycleCount(5);
    transition2.play();
    
    Pane root = new Pane();
    root.getChildren().addAll(character1, character2, rect1, rect2); 
    
    Scene scene = new Scene(root, 600, 600);
    
    primaryStage.setTitle("Animations!");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  
  public static void main (String[] args) {
    launch(args);
  }
}
