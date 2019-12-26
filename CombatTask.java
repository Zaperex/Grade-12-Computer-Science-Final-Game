/* Name: Frank Kong
 * Date: December 24, 2019
 * Description: CombatTask class that extends TimerTask class and provides a run method for the timer to use
 */
import java.util.*;

public class CombatTask extends TimerTask{
  //Fields
  Combatant combatant;
  Combatant opponent;
  Terrain terrain;
  int attackCounter = 10; //Takes 10 action phases to attack once
  double speedMultiplier = 1; //Speed is initially at 100%
  //Constructor
  public CombatTask(Combatant combatant, Combatant opponent){
    this.combatant = combatant;
    this.opponent = opponent;
    terrain = Combat.getTerrain();
  }
  
  @Override
  public void run(){
    //Halves movement speed in swamps
    if (terrain.getType().equals("Swamp")){
      speedMultiplier = 0.5; //Swamp terrain halves movement speed
    }
    //If combatant is not within attack range yet, it'll move forward
    if (Combat.getDistance() > combatant.getRange()){
      Combat.setDistance(Combat.getDistance() - (combatant.getSpeed()*speedMultiplier)/10); //Combatant will move every 0.1 seconds
      System.out.println("Distance = " + Combat.getDistance());
    }
    //If combatant is in range and it is not dead (HP is not 0 or less) and it's opponent is also not dead
    else if (Combat.getDistance() <= combatant.getRange() && combatant.getHealth() > 0 && opponent.getHealth() > 0){
      //Attack counter to make sure troop only attacks 1 time per second 
      //(since actions occur ever 0.1 seconds, then it'll require 10 actions to attack once)
      if (attackCounter == 10){
      //Damage dealt is attack*(1-defense of opponent) + penetration damage (unblockable damage)
      double damage = ((100-opponent.getDefense())/100)*combatant.getAttack() + combatant.getPenetration();
      opponent.setHealth(opponent.getHealth()-damage); //Opponent takes that damage
      attackCounter = 0; //Sets attack counter back to 0
      System.out.println(combatant.getName() + " dealt " + damage + " damage to " + opponent.getName());
      System.out.println(opponent.getName() + "HP: " + opponent.getHealth());
      }
      attackCounter++; //Adds 1 to attack counter
    }
    //If one of or both sides dies (Health reaches 0 or goes below)
    else if (opponent.getHealth() <= 0 || combatant.getHealth() <= 0){
      System.out.println("Battle is over");
      cancel(); //Cancels Timer Task
    }
  }
}
