/* Name: Frank Kong
 * Date: December 23, 2019
 * Description: Combat class that helps keep track of all stats and conditions during a combat phase
 */
import java.util.*; //Imports all Java util packages including Timer and TimerTask

public class Combat{
  //Fields (Fields are static as only one combat phase can happen at a time so we'll just change the variables each time)
  static double distance = 100; //Distance between 2 forces start at 100 units
  static Terrain terrain;
  //Constructor
  public Combat(Terrain terrain){
    this.terrain = terrain;
  }
  
  //Getters
  public static Terrain getTerrain(){
    return terrain;
  }
  public static double getDistance(){
    return distance;
  }
  
  //Setters
  public static void setTerrain(Terrain landscape){
    terrain = landscape;
  }
  public static void setDistance(double distanceLeft){
    distance = distanceLeft;
  }
  
  
  
  //Combat Method utilizes built in Multi Threading in Timer Package
  public Combatant fight(Combatant attacker, Combatant defender, Terrain terrain){
    Combatant victor = null; //Variable to store victor of the battle
    Timer attackTimer = new Timer(); //Creates timer and thread for attackers
    Timer defenderTimer = new Timer(); //Creates timer and thread for defenders
    TimerTask attackTask = new CombatTask(attacker, defender); //Initializes Task for attackers
    TimerTask defendTask = new CombatTask(defender, attacker); //Initializes Task for defenders
    attackTimer.schedule(attackTask, 0, 100); //Performs actions for attackers every 0.1 seconds
    defenderTimer.schedule(defendTask, 0, 100); //Performs actions for defenders every 0.1 seconds
    
    //If both sides' HPs are 0 or below 
    if (attacker.getHealth() <= 0 && defender.getHealth() <= 0){
      victor = null; //No Victor
    }
    //If Defender's HP is 0 or below
    else if (defender.getHealth() <= 0){
      victor = attacker; //Attacker wins
    }
    //If Attacker's HP is 0 or below
    else if (attacker.getHealth() <= 0){
      victor = defender; //Defender wins
    }
    distance = 100; //Reset distance for next battle
    
    return victor;
  } 
}
