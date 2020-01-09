/* Name: Frank Kong
 * Date: December 17, 2019
 * Description: Combatant class (parent class) for all the combatants: Includes buildings and troops
 */
import java.util.*;
import javafx.scene.image.Image;

//MAKE SURE TO REDUCE SPEED ON TROOPS (THEY'RE TOO FAST)

public class Combatant{
  //Fields
  protected String name; //Name of the combatant
  protected double health; //Health
  protected double attack; //Attack
  protected double range; //Attack Range
  protected String team; //Team of combatant
  protected double speed; //Speed of combatant (needed in buildings to simplify combat calculations)
  protected int[] coords = new int[2]; //Coordinates of combatant
  protected double defense; //Defense of combatant (Reduces damage taken)
  protected double penetration; //Penetration damage of combatant (Ignore defense)
  protected boolean stunned; //Boolean for whether troop is stunned or not
  protected int specialMeter = 0; //Special meter of character
  protected Image img; //Image of combatant
  protected String imageFileName; //Name of image file
  //Constructor
 
  public Combatant(String name, double health, double attack, double range, double defense, double penetration,
                   String team, double speed, int[] coords, String imageFileName){
    this.name = name;
    this.health = health;
    this.attack = attack;
    this.range = range;
    this.defense = defense;
    this.penetration = penetration;
    this.team = team;
    this.speed = speed;
    this.coords = coords;
    this.imageFileName = imageFileName;
  }
  
  //Setters
  public void setSpeed(double speed){
    this.speed = speed;
  }
  public void setHealth(double health){
    this.health = health;
  }
  public void setAttack(double attack){
    this.attack = attack;
  }
  public void setRange(double range){
    this.range = range;
  }
  public void setTeam(String team){
    this.team = team;
  }
  public void setDefense(double defense){
    this.defense = defense;
  }
  public void setCoords(int[] coords){
    this.coords = coords;
  }
  public void setStun(boolean stunned){
    this.stunned = stunned;
  }
  public void setSpecialMeter(int specialMeter){
    this.specialMeter = specialMeter;
  }
  public void setImageName(String imageFileName){
    this.imageFileName = imageFileName;
  }
  
  //Getters
  public String getName(){
    return name;
  }
  public double getHealth(){
    return health;
  }
  public double getAttack(){
    return attack;
  }
  public double getRange(){
    return range;
  }
  public double getSpeed(){
    return speed;
  }
  public String getTeam(){
    return team;
  }
  public double getDefense(){
    return defense;
  }
  public double getPenetration(){
    return penetration;
  }
  public int[] getCoords(){
    return coords;
  }
  public boolean getStun(){
    return stunned;
  }
  public int getSpecialMeter(){
    return specialMeter;
  }
  public String getImageName(){
    return imageFileName;
  }
  
  //Helper Method
  //Method that helps Combatants perform a basic attack
  public double attack(Combatant opponent){
    increaseSpecialMeter(); //Increases special meter for using a regular attack
    //Damage is calculated using attack and reduced by enemy defense
    double damage = attack*(1-opponent.getDefense());
    
    //If opponent is a troop
    if (opponent instanceof Troop){
      //If opponent is blocking
      if (((Troop)(opponent)).getBlock() == true){
        return 0;
      }
    }
    //If opponent is a building
    else if (opponent instanceof Building){
      //If opponent has barrier up
      if (((Building)(opponent)).getBarrier() == true){
        return 0;
      }
    }
    //If attack was not blocked
    return damage;
  }
  
  //Method that increases special meter
  public void increaseSpecialMeter(){
    //If special meter is not maxed yet (Max of 3)
    if (specialMeter < 3){
      //Increase special meter
      specialMeter++;
    }
  }

}

