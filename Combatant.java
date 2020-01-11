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
  protected int[] coords = new int[2]; //Coordinates of combatant
  protected double defense; //Defense of combatant (Reduces damage taken)
  protected boolean stunned; //Boolean for whether troop is stunned or not
  protected int specialMeter = 0; //Special meter of character
  protected Image img; //Image of combatant
  protected String imageFileName; //Name of image file
  protected double baseDefense; //Base Defense (for resetting defense) 
  protected double maxHP; //Max health (for healing)
  //Constructor
 
  public Combatant(String name, double health, double attack, double range, double defense, double baseDefense,
                   double maxHP,String team, int[] coords, String imageFileName){
    this.name = name;
    this.health = health;
    this.attack = attack;
    this.range = range;
    this.defense = defense;
    this.baseDefense = baseDefense;
    this.maxHP = maxHP;
    this.team = team;
    this.coords = coords;
    this.imageFileName = imageFileName;
  }
  
  //Setters
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
  public String getTeam(){
    return team;
  }
  public double getDefense(){
    return defense;
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
    double damage = attack*(1-(opponent.getDefense()/100));
    
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
  public boolean increaseSpecialMeter(){
    //If special meter is not maxed yet (Max of 3)
    if (specialMeter < 3){
      //Increase special meter
      specialMeter++;
      return true; //Returns that special meter was increased
    }
    return false; //Returns that special meter was not increased
  }

}

