/* Name: Frank Kong
 * Date: January 12, 2020
 * Description: Combat Class to store the new Combat methods
 */
import java.util.*;

public class Combat{
 
  //Combat method that returns a combat log stored inside a String arraylist
  public static ArrayList<String> combatPhase(Troop attacker, Combatant defender, String attackAction, String defendAction, double goldCount){
    //Arraylist that stores the combat log
    ArrayList<String> combatLog = new ArrayList<String>();
    //First attacker will be combatant with the longer range, if ranges are the same, then attacker goes first
    boolean firstAction = attacker.getRange() >= defender.getRange();
    
    //Section to unstun characters that have been stunned for a turn
    if (attacker.getStunCounter() == 1){
      attacker.setStun(false);
    }
    if (defender.getStunCounter() == 1){
      defender.setStun(false);
    }
    
    //This section makes sure all non damaging actions get performed first
    //Makes sure attacker is not stunner
    if (attacker.getStun() == false){
      //Checks if attacker blocks or dodges
      if (attackAction.equals("Block")){
        attacker.setBlock(true); //Sets block to true
        //Stores what happened inside combat log
        combatLog.add(attacker.getTeam() + " " + attacker.getName() + " blocked");
      }
      else if (attackAction.equals("Dodge")){
        attacker.setDodge(true); //Sets Dodge to true
        //Stores what happened inside combat log
        combatLog.add(attacker.getTeam() + " " + attacker.getName() + " dodged");
      }
    }
    //Makes sure defender is not stunned
    if (defender.getStun() == false){
      //If Defender is a troop
      if (defender instanceof Troop){
        //Checks if defender blocks or dodges
        if (defendAction.equals("Block")){
          ((Troop)defender).setBlock(true); //Sets block to true
          //Stores what happened inside combat log
          combatLog.add(defender.getTeam() + " " + defender.getName() + " blocked");
        }
        if (defendAction.equals("Dodge")){
          ((Troop)defender).setDodge(true); //Sets Dodge to true
          //Stores what happened inside combat log
          combatLog.add(defender.getTeam() + " " + defender.getName() + " dodged");
        }
      }
      //If Defender is a building
      else if (defender instanceof Building){
        //Checks if defender puts up a magic barrier or repairs itself
        if (defendAction.equals("Magic Barrier")){
          ((Building)defender).setBarrier(true); //Sets barrier to true
          //Stores what happened inside combat log
          combatLog.add(defender.getTeam() + " " + defender.getName() + " put up a Magic Barrier");
        }
        if (defendAction.equals("Repair")){
          //Stores what happened inside combat log
          combatLog.add(defender.getTeam() + " " + defender.getName() + " repaired itself for " + ((Building)defender).repair(goldCount));
        }
      } 
    }
    
    //If attacker performs an action first
    if (firstAction){
      //Performs attacker attacks if they decided to attack
      attackerCombat(attacker, defender, attackAction, combatLog);
      //Checks if defender is still alive
      if (defender.getHealth() > 0){
        defenderCombat(defender, attacker, defendAction, combatLog);
      }
      else{
        //If defender has died
        combatLog.add(defender.getTeam() + " " + defender.getName() + " has died");
        combatLog.add(attacker.getTeam() + " " + attacker.getName() + " has won");
        resetActions(attacker, defender); //Resets Actions
        return combatLog; //Returns combat log
      }
    }
    //If defender performs an action first
    else if (firstAction == false){
      //Performs defender attacked if they decided to defend
      defenderCombat(defender, attacker, defendAction, combatLog);
      //Checks if attacker is still alive
      if (attacker.getHealth() > 0){
        attackerCombat(attacker, defender, attackAction, combatLog);
      }
      else{
        //If attacker has died
        combatLog.add(attacker.getTeam() + " " + attacker.getName() + " has died");
        combatLog.add(defender.getTeam() + " " + defender.getName() + " has won");
        resetActions(attacker, defender); //Resets actions
        return combatLog; //Returns combat log
      }
    }
    //Resets actions
    resetActions(attacker, defender);
    //Returns combat log after turn is over
    return combatLog;
    
    
  }
  
  //Attacker Combat Method ==========================================================================================================
  public static void attackerCombat(Troop attacker, Combatant defender, String attackAction, ArrayList<String> combatLog){
    if (attacker.getStun() == false){
      double damage;
      //Attacker Basic Attacks =============================================================================================================
      if (attackAction.equals("Attack")){
        damage = attacker.attack(defender); //Calls damage method and calculates damage
        //Damages defender if they are not blocking
        defender.setHealth(defender.getHealth() - damage);
        //If attacker is a footman
        if (attacker instanceof Footman){
          if (damage > 0){
            //Stores what happened inside combat log
            combatLog.add(attacker.getTeam() + " " + attacker.getName() + " used " + ((Footman)attacker).getAttackName() + 
                          " and dealt " + damage + " damage to " + defender.getTeam() + " " + defender.getName());
          }
          //If attack did not connect
          else{
            //Stores what happened inside combat log
            combatLog.add(attacker.getTeam() + " " + attacker.getName() + " used " + ((Footman)attacker).getAttackName() + 
                          " but was blocked");
          }
        }
        //If attacker is an archer
        if (attacker instanceof Archer){
          if (damage > 0){
            //Stores what happened inside combat log
            combatLog.add(attacker.getTeam() + " " + attacker.getName() + " used " + ((Archer)attacker).getAttackName() + 
                          " and dealt " + damage + " damage to " + defender.getTeam() + " " + defender.getName());
          }
          //If attack did not connect
          else{
            //Stores what happened inside combat log
            combatLog.add(attacker.getTeam() + " " + attacker.getName() + " used " + ((Archer)attacker).getAttackName() + 
                          " but was blocked");
          }
        }
        //If attacker is a knight
        if (attacker instanceof Knight){
          if (damage > 0){
            //Stores what happened inside combat log
            combatLog.add(attacker.getTeam() + " " + attacker.getName() + " used " + ((Knight)attacker).getAttackName() + 
                          " and dealt " + damage + " damage to " + defender.getTeam() + " " + defender.getName());
          }
          //If attack did not connect
          else{
            //Stores what happened inside combat log
            combatLog.add(attacker.getTeam() + " " + attacker.getName() + " used " + ((Knight)attacker).getAttackName() + 
                          " but was blocked");
          }
        }
        //If attacker is a crossbowmen
        if (attacker instanceof CrossbowMen){
          if (damage > 0){
            //Stores what happened inside combat log
            combatLog.add(attacker.getTeam() + " " + attacker.getName() + " used " + ((CrossbowMen)attacker).getAttackName() + 
                          " and dealt " + damage + " damage to " + defender.getTeam() + " " + defender.getName());
          }
          //If attack did not connect
          else{
            //Stores what happened inside combat log
            combatLog.add(attacker.getTeam() + " " + attacker.getName() + " used " + ((CrossbowMen)attacker).getAttackName() + 
                          " but was blocked");
          }
        }
        //If attacker is a cavalry
        if (attacker instanceof Cavalry){
          if (damage > 0){
            //Stores what happened inside combat log
            combatLog.add(attacker.getTeam() + " " + attacker.getName() + " used " + ((Cavalry)attacker).getAttackName() + 
                          " and dealt " + damage + " damage to " + defender.getTeam() + " " + defender.getName());
          }
          //If attack did not connect
          else{
            //Stores what happened inside combat log
            combatLog.add(attacker.getTeam() + " " + attacker.getName() + " used " + ((Cavalry)attacker).getAttackName() + 
                          " but was blocked");
          }
        }
      }
      //Attacker Special Attacks =============================================================================================================
      else if (attackAction.equals("Special Attack")){
        //If attacker is a footmen
        if (attacker instanceof Footman){
          damage = ((Footman)attacker).specialAttack(defender); //Performs special attack and calculates damage and effects
          //Damages defender if they are not Dodging
          defender.setHealth(defender.getHealth() - damage);
          if (damage > 0){
            //Stores what happened inside combat log
            combatLog.add(attacker.getTeam() + " " + attacker.getName() + " used " + ((Footman)attacker).getSpecialAttack() + 
                          " and dealt " + damage + " damage to " + defender.getTeam() + " " + defender.getName());
          }
          //If attack did not connect
          else{
            //Stores what happened inside combat log
            combatLog.add(attacker.getTeam() + " " + attacker.getName() + " used " + ((Footman)attacker).getSpecialAttack() + 
                          " but missed");
          }
        }
        //If attacker is an archer
        else if (attacker instanceof Archer){
          damage = ((Archer)attacker).specialAttack(defender); //Performs special attack and calculates damage and effects
          //Damages defender if they are not Dodging
          defender.setHealth(defender.getHealth() - damage);
          if (damage > 0){
            //Stores what happened inside combat log
            combatLog.add(attacker.getTeam() + " " + attacker.getName() + " used " + ((Archer)attacker).getSpecialAttack() + 
                          " and dealt " + damage + " damage to " + defender.getTeam() + " " + defender.getName());
          }
          //If attack did not connect
          else{
            //Stores what happened inside combat log
            combatLog.add(attacker.getTeam() + " " + attacker.getName() + " used " + ((Archer)attacker).getSpecialAttack() + 
                          " but missed");
          }
        }
        //If attacker is a knight
        else if (attacker instanceof Knight){
          damage = ((Knight)attacker).specialAttack(defender); //Performs special attack and calculates damage and effects
          //Damages defender if they are not Dodging
          defender.setHealth(defender.getHealth() - damage);
          //If attack connected
          if (damage > 0){
            //Stores what happened inside combat log
            combatLog.add(attacker.getTeam() + " " + attacker.getName() + " used " + ((Knight)attacker).getSpecialAttack() + 
                          " and dealt " + damage + " damage to " + defender.getTeam() + " " + defender.getName() + " and stunned them");
          }
          //If attack did not connect
          else{
            //Stores what happened inside combat log
            combatLog.add(attacker.getTeam() + " " + attacker.getName() + " used " + ((Knight)attacker).getSpecialAttack() + 
                          " but missed");
          }
        }
        //If attacker is a crossbowmen
        else if (attacker instanceof CrossbowMen){
          damage = ((CrossbowMen)attacker).specialAttack(defender); //Performs special attack and calculates damage and effects
          //Damages defender if they are not Dodging
          defender.setHealth(defender.getHealth() - damage);
          if (damage > 0){
            //Stores what happened inside combat log
            combatLog.add(attacker.getTeam() + " " + attacker.getName() + " used " + ((CrossbowMen)attacker).getSpecialAttack() + 
                          " and dealt " + damage + " damage to " + defender.getTeam() + " " + defender.getName() + " and halved their defense");
          }
          //If attack did not connect
          else{
            //Stores what happened inside combat log
            combatLog.add(attacker.getTeam() + " " + attacker.getName() + " used " + ((CrossbowMen)attacker).getSpecialAttack() + 
                          " but missed");
          }
        }
        //If attacker is a Cavalry
        else if (attacker instanceof Cavalry){
          //Stores the health of the cavalry before it healed
          double oldHealth = attacker.getHealth();
          damage = ((Cavalry)attacker).specialAttack(defender); //Performs special attack and calculates damage and effects
         //Damages defender if they are not Dodging
          defender.setHealth(defender.getHealth() - damage);
          if (damage > 0){
            //If healing does not exceed max health
            if ((attacker.getHealth() + damage/2) < attacker.getMaxHP()){
              //Stores what happened inside combat log
              combatLog.add(attacker.getTeam() + " " + attacker.getName() + " used " + ((Cavalry)attacker).getSpecialAttack() + 
                            " and dealt " + damage + " damage to " + defender.getTeam() + " " + defender.getName() + "  and healed for "
                              + damage/2);
            }
            //If healing exceeds max health
            else{
              //Stores what happened inside combat log
              combatLog.add(attacker.getTeam() + " " + attacker.getName() + " used " + ((Cavalry)attacker).getSpecialAttack() + 
                            " and dealt " + damage + " damage to " + defender.getTeam() + " " + defender.getName() + "  and healed for "
                              + (attacker.getHealth() - oldHealth));
            }
          }
          //If attack did not connect
          else{
            //Stores what happened inside combat log
            combatLog.add(attacker.getTeam() + " " + attacker.getName() + " used " + ((Cavalry)attacker).getSpecialAttack() + 
                          " but missed");
            defender.setHealth(defender.getHealth() - damage);
          }
        }
      }
    }
    
    else {
      //Stores what happened inside combat log
      combatLog.add(attacker.getTeam() + " " + attacker.getName() + " was stunned and couldn't move");
      attacker.setStunCounter(1); //Sets stun counter to 1 since attacker was skipped 1 turn due to stun effect
    }
  }
  
  //Defender Combat Method ====================================================================================================================
  public static void defenderCombat(Combatant defender, Troop attacker, String defendAction, ArrayList<String> combatLog){
    if (defender.getStun() == false){
      double damage;
      //Defender Basic Attacks =============================================================================================================
      if (defendAction.equals("Attack")){
        damage = defender.attack(attacker); //Calls damage method and calculates damage
        //Damages attacker if they are not blocking
        attacker.setHealth(attacker.getHealth() - damage);
        
        //If defender is a footman
        if (defender instanceof Footman){
          if (damage > 0){
            //Stores what happened inside combat log
            combatLog.add(defender.getTeam() + " " + defender.getName() + " used " + ((Footman)defender).getAttackName() + 
                          " and dealt " + damage + " damage to " + attacker.getTeam() + " " + attacker.getName());
          }
          //If attack did not connect
          else{
            //Stores what happened inside combat log
            combatLog.add(defender.getTeam() + " " + defender.getName() + " used " + ((Footman)defender).getAttackName() + 
                          " but was blocked");
          }
        }
        //If defender is an archer
        if (defender instanceof Archer){
          if (damage > 0){
            //Stores what happened inside combat log
            combatLog.add(defender.getTeam() + " " + defender.getName() + " used " + ((Archer)defender).getAttackName() + 
                          " and dealt " + damage + " damage to " + attacker.getTeam() + " " + attacker.getName());
          }
          //If attack did not connect
          else{
            //Stores what happened inside combat log
            combatLog.add(defender.getTeam() + " " + defender.getName() + " used " + ((Archer)defender).getAttackName() + 
                          " but was blocked");
          }
        }
        //If defender is a knight
        if (defender instanceof Knight){
          if (damage > 0){
            //Stores what happened inside combat log
            combatLog.add(defender.getTeam() + " " + defender.getName() + " used " + ((Knight)defender).getAttackName() + 
                          " and dealt " + damage + " damage to " + attacker.getTeam() + " " + attacker.getName());
          }
          //If attack did not connect
          else{
            //Stores what happened inside combat log
            combatLog.add(defender.getTeam() + " " + defender.getName() + " used " + ((Knight)defender).getAttackName() + 
                          " but was blocked");
          }
        }
        //If defender is a crossbowmen
        if (defender instanceof CrossbowMen){
          if (damage > 0){
            //Stores what happened inside combat log
            combatLog.add(defender.getTeam() + " " + defender.getName() + " used " + ((CrossbowMen)defender).getAttackName() + 
                          " and dealt " + damage + " damage to " + attacker.getTeam() + " " + attacker.getName());
          }
          //If attack did not connect
          else{
            //Stores what happened inside combat log
            combatLog.add(defender.getTeam() + " " + defender.getName() + " used " + ((CrossbowMen)defender).getAttackName() + 
                          " but was blocked");
          }
        }
        //If defender is a cavalry
        if (defender instanceof Cavalry){
          if (damage > 0){
            //Stores what happened inside combat log
            combatLog.add(defender.getTeam() + " " + defender.getName() + " used " + ((Cavalry)defender).getAttackName() + 
                          " and dealt " + damage + " damage to " + attacker.getTeam() + " " + attacker.getName());
          }
          //If attack did not connect
          else{
            //Stores what happened inside combat log
            combatLog.add(defender.getTeam() + " " + defender.getName() + " used " + ((Cavalry)defender).getAttackName() + 
                          " but was blocked");
          }
        }
        //If defender is a Castle
        if (defender instanceof Castle){
          if (damage > 0){
            //Stores what happened inside combat log
            combatLog.add(defender.getTeam() + " " + defender.getName() + " used " + ((Castle)defender).getAttackName() + 
                          " and dealt " + damage + " damage to " + attacker.getTeam() + " " + attacker.getName());
          }
          //If attack did not connect
          else{
            //Stores what happened inside combat log
            combatLog.add(defender.getTeam() + " " + defender.getName() + " used " + ((Castle)defender).getAttackName() + 
                          " but was blocked");
          }
        }
        
        //If defender is a Gold Mine
        if (defender instanceof GoldMine){
          if (damage > 0){
            //Stores what happened inside combat log
            combatLog.add(defender.getTeam() + " " + defender.getName() + " used " + ((GoldMine)defender).getAttackName() + 
                          " and dealt " + damage + " damage to " + attacker.getTeam() + " " + attacker.getName());
          }
          //If attack did not connect
          else{
            //Stores what happened inside combat log
            combatLog.add(defender.getTeam() + " " + defender.getName() + " used " + ((GoldMine)defender).getAttackName() + 
                          " but was blocked");
          }
        }
      }
      
      //Defender Special Attacks =============================================================================================================
      else if (defendAction.equals("Special Attack")){
        //If defender is a footmen
        if (defender instanceof Footman){
          damage = ((Footman)defender).specialAttack(attacker); //Performs special attack and calculates damage and effects
          //Damages attacker if they are not Dodging
          attacker.setHealth(attacker.getHealth() - damage);
          if (damage > 0){
            //Stores what happened inside combat log
            combatLog.add(defender.getTeam() + " " + defender.getName() + " used " + ((Footman)defender).getSpecialAttack() + 
                          " and dealt " + damage + " damage to " + attacker.getTeam() + " " + attacker.getName());
          }
          //If attack did not connect
          else{
            //Stores what happened inside combat log
            combatLog.add(defender.getTeam() + " " + defender.getName() + " used " + ((Footman)defender).getSpecialAttack() + 
                          " but missed");
          }
        }
        //If defender is an archer
        else if (defender instanceof Archer){
          damage = ((Archer)defender).specialAttack(attacker); //Performs special attack and calculates damage and effects
         //Damages attacker if they are not Dodging
          attacker.setHealth(attacker.getHealth() - damage);
          if (damage > 0){
            //Stores what happened inside combat log
            combatLog.add(defender.getTeam() + " " + defender.getName() + " used " + ((Archer)defender).getSpecialAttack() + 
                          " and dealt " + damage + " damage to " + attacker.getTeam() + " " + attacker.getName());
          }
          //If attack did not connect
          else{
            //Stores what happened inside combat log
            combatLog.add(defender.getTeam() + " " + defender.getName() + " used " + ((Archer)defender).getSpecialAttack() + 
                          " but missed");
          }
        }
        //If defender is a knight
        else if (defender instanceof Knight){
          damage = ((Knight)defender).specialAttack(attacker); //Performs special attack and calculates damage and effects
          //Damages attacker if they are not Dodging
          attacker.setHealth(attacker.getHealth() - damage);
          //If attack connected
          if (damage > 0){
            //Stores what happened inside combat log
            combatLog.add(defender.getTeam() + " " + defender.getName() + " used " + ((Knight)defender).getSpecialAttack() + 
                          " and dealt " + damage + " damage to " + attacker.getTeam() + " " + attacker.getName() + " and stunned them");
          }
          //If attack did not connect
          else{
            //Stores what happened inside combat log
            combatLog.add(defender.getTeam() + " " + defender.getName() + " used " + ((Knight)defender).getSpecialAttack() + 
                          " but missed");
          }
        }
        //If defender is a crossbowmen
        else if (defender instanceof CrossbowMen){
          damage = ((CrossbowMen)defender).specialAttack(attacker); //Performs special attack and calculates damage and effects
         //Damages attacker if they are not Dodging
          attacker.setHealth(attacker.getHealth() - damage);
          if (damage > 0){
            //Stores what happened inside combat log
            combatLog.add(defender.getTeam() + " " + defender.getName() + " used " + ((CrossbowMen)defender).getSpecialAttack() + 
                          " and dealt " + damage + " damage to " + attacker.getTeam() + " " + attacker.getName() + " and halved their defense");
          }
          //If attack did not connect
          else{
            //Stores what happened inside combat log
            combatLog.add(defender.getTeam() + " " + defender.getName() + " used " + ((CrossbowMen)defender).getSpecialAttack() + 
                          " but missed");
          }
        }
        //If defender is a Cavalry
        else if (defender instanceof Cavalry){
          //Stores the health of the cavalry before it healed
          double oldHealth = defender.getHealth();
          damage = ((Cavalry)defender).specialAttack(attacker); //Performs special attack and calculates damage and effects
          //Damages attacker if they are not Dodging
          attacker.setHealth(attacker.getHealth() - damage);
          if (damage > 0){
            //Stores what happened inside combat log
            //If healing does not exceed max health
            if ((defender.getHealth() + damage/2) < defender.getMaxHP()){
              combatLog.add(defender.getTeam() + " " + defender.getName() + " used " + ((Cavalry)defender).getSpecialAttack() + 
                            " and dealt " + damage + " damage to " + attacker.getTeam() + " " + attacker.getName() + "  and healed for "
                              + damage/2);
            }
            //If healing exceeds max health
            else{
              //Stores what happened inside combat log
              combatLog.add(defender.getTeam() + " " + defender.getName() + " used " + ((Cavalry)defender).getSpecialAttack() + 
                            " and dealt " + damage + " damage to " + attacker.getTeam() + " " + attacker.getName() + "  and healed for "
                              + (attacker.getHealth() - oldHealth));
            }
          }
          //If attack did not connect
          else{
            //Stores what happened inside combat log
            combatLog.add(defender.getTeam() + " " + defender.getName() + " used " + ((Cavalry)defender).getSpecialAttack() + 
                          " but missed");
            defender.setHealth(defender.getHealth() - damage);
          }
        }
        //If defender is a Castle
        else if (defender instanceof Castle){
          damage = ((Castle)defender).specialAttack(attacker); //Performs special attack and calculates damage and effects
          //Damages attacker if they are not Dodging
          attacker.setHealth(attacker.getHealth() - damage);
          //If attack connected
          if (damage > 0){
            //Stores what happened inside combat log
            combatLog.add(defender.getTeam() + " " + defender.getName() + " used " + ((Castle)defender).getSpecialAttack() + 
                          " and dealt " + damage + " damage to " + attacker.getTeam() + " " + attacker.getName() + " and stunned them");
          }
          //If attack did not connect
          else{
            //Stores what happened inside combat log
            combatLog.add(defender.getTeam() + " " + defender.getName() + " used " + ((Castle)defender).getSpecialAttack() + 
                          " but missed");
          }
        }
        else if (defender instanceof GoldMine){
          double oldDefense = defender.getDefense(); //Stores the defense of the Gold Mine before it is increased
          damage = ((GoldMine)defender).specialAttack(attacker); //Performs special attack and calculates damage and effects
          //Damages attacker if they are not Dodging
          attacker.setHealth(attacker.getHealth() - damage);
          //If attack connected
          if (damage > 0){
            //Stores what happened inside combat log
            combatLog.add(defender.getTeam() + " " + defender.getName() + " used " + ((GoldMine)defender).getSpecialAttack() + 
                          " and dealt " + damage + " damage to " + attacker.getTeam() + " " + attacker.getName() + " and increased it's defense by " +
                          (defender.getDefense() - oldDefense));
          }
          //If attack did not connect
          else{
            //Stores what happened inside combat log
            combatLog.add(defender.getTeam() + " " + defender.getName() + " used " + ((GoldMine)defender).getSpecialAttack() + 
                          " but missed");
          }
        }
      }
    }
    else{
      //Stores what happened inside combat log
      combatLog.add(defender.getTeam() + " " + defender.getName() + " was stunned and couldn't move");
      defender.setStunCounter(1); //Sets stun counter to 1 since attacker was skipped 1 turn due to stun effect
    }
  }
  
  //Resets the dodge, block and/or barrier booleans of both sides for the next turn
  public static void resetActions(Troop attacker, Combatant defender){
    attacker.setDodge(false);
    attacker.setBlock(false);
    
    if (defender instanceof Building){
      ((Building)defender).setBarrier(false);
    }
    else{
      ((Troop)defender).setDodge(false);
      ((Troop)defender).setBlock(false);
    }
  }
}


