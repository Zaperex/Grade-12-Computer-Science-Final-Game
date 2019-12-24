/* Name: Frank Kong
 * Date: December 17, 2019
 * Description: Combatant class (parent class) for all the combatants: Includes buildings and troops
 */
import java.util.*;

public class Combatant{
  //Fields
  private String name; //Name of the combatant
  private double health; //Health
  private double attack; //Attack
  private double range; //Attack Range
  private boolean team; //Team of combatant
  private double speed; //Speed of combatant
  private int[] coords = new int[2]; //Coordinates of combatant
  private int moveDistance; //How many tiles combatant can move
  private double defense; //Defense of combatant (Reduces damage taken)
  private double penetration; //Penetration damage of combatant (Ignore defense)
  
  //Constructor
  public Combatant(String name, double health, double attack, double range, double defense, double penetration,
                   boolean team, double speed, int moveDistance){
    this.name = name;
    this.health = health;
    this.attack = attack;
    this.range = range;
    this.defense = defense;
    this.penetration = penetration;
    this.team = team;
    this.speed = speed;
    this.moveDistance = moveDistance;
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
  
  //Implement when board is added
  /*
   //Helper Methods
   public boolean move(int x, int y){
   int distance = Math.abs(x - coords[0]) + Math.abs(y - coords[1]);
   if (distance <= moveDistance){
   actualMove(x, y);
   return true;
   }
   else{
   return false;
   }
   }
   
   public void actualMove(int x, int y){
   Scanner input = new Scanner(System.in);
   System.out.println("Are you sure you want to move here? Y/N");
   String response = input.nextLine();
   while !(response.toIgnoreCase().equals("Y") || response.toIgnoreCase().equals("N")){
   System.out.println("Are you sure you want to move here? Y/N");
   response = input.nextLine();
   }
   //If confirmed then move to spot
   if (response.toIgnoreCase().equals("Y")){
   coords[0] = x;
   coords[1] = y;
   }
   
   }
   */
}

