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
  
  //Constructor
  public CombatTask(Combatant combatant, Combatant opponent){
    this.combatant = combatant;
    this.opponent = opponent;
    terrain = Combat.getTerrain();
  }
  
  //Overide
  public void run(){
    //If combatant is not within attack range yet, it'll move forward
    if (Combat.getDistance() > combatant.getRange()){
      Combat.setDistance(Combat.getDistance() - combatant.getSpeed()/10); //Combatant will move every 0.1 seconds
    }
    //If combatant is in range and it is not dead (HP is not 0 or less) and it's opponent is also not dead
    else if (Combat.getDistance() <= combatant.getRange() && combatant.getHealth() > 0 && opponent.getHealth() > 0){
      //Damage dealt is attack*(1-defense of opponent) + penetration damage (unblockable damage)
      double damage = (1-opponent.getDefense())*combatant.getAttack() + combatant.getPenetration();
      opponent.setHealth(opponent.getHealth()-damage); //Opponent takes that damage
    }
    //If one of or both sides dies (Health reaches 0 or goes below)
    else if (opponent.getHealth() <= 0 || combatant.getHealth() <= 0){
      cancel(); //Cancels Timer Task
    }
  }
}