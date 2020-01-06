/* Name: Frank Kong
 * Date: December 17, 2019
 * Description: Combatant class (parent class) for all the combatants: Includes buildings and troops
 */
import java.util.*;

//MAKE SURE TO REDUCE SPEED ON TROOPS (THEY'RE TOO FAST)

public class Combatant{
  //Fields
  protected String name; //Name of the combatant
  protected double health; //Health
  protected double attack; //Attack
  protected double range; //Attack Range
  protected boolean team; //Team of combatant
  protected double speed; //Speed of combatant (needed in buildings to simplify combat calculations)
  protected int[] coords = new int[2]; //Coordinates of combatant
  protected double defense; //Defense of combatant (Reduces damage taken)
  protected double penetration; //Penetration damage of combatant (Ignore defense)
  //Constructor
  //REMEMBER TO ADD COORDINATES TO CONSTRUCTOR
  public Combatant(String name, double health, double attack, double range, double defense, double penetration,
                   boolean team, double speed, int[] coords){
    this.name = name;
    this.health = health;
    this.attack = attack;
    this.range = range;
    this.defense = defense;
    this.penetration = penetration;
    this.team = team;
    this.speed = speed;
    this.coords = coords;
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
  public void setTeam(boolean team){
    this.team = team;
  }
  public void setCoords(int[] coords){
    this.coords = coords;
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
  public boolean getTeam(){
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
  

}

