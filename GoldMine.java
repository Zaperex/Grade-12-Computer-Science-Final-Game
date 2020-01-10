/* Name: Frank Kong
 * Date: January 6, 2020
 * Description: Gold Mine Class for gold mines
 */

public class GoldMine extends Building{
  //Fields
  private String attackName = "Arrow Volley"; //Name of Basic Attack
  private String specialAttack = "Fireball"; //Name of special attack
  private double baseDefense = 30; //Base Defense
  private double maxHP = 1500; //Max Health
  //Constructor
  public GoldMine(String team, int[] coordinates){
    super("Gold Mine", 1500, 10, 5, 1500, team, coordinates, "Gold Mine.png");
    //Gold Mines have 1500HP, 10 ATK, 5 RNG, 30 DEF, 30 BASEDEF, 1500 BASE HP

  }
  //Getters
  public String getSpecialAttack(){
    return specialAttack;
  }
  public String getAttackName(){
    return attackName;
  }
  
  //Repair method
  public boolean repair(double goldBalance){
    //Repair will cost 40 gold to perform
    if (goldBalance >= 40){
      //If healing will make building go past max health
      if ((getHealth() + maxHP/10) > maxHP){
        setHealth(maxHP);
        return true; //Repair is successful so return true
      }
      else {
        //Heals 10% of building health
        setHealth(getHealth() + maxHP/10);
        return true; //Repair is successful so return true
      }
    }
    //If not enough gold, return false as repair is not successful
    return false;
  }
  
  //Special Attack for Gold Mine
  public double specialMove(Combatant opponent){
    double damage = attack*3;
    if (opponent instanceof Troop){
      if (((Troop)(opponent)).getDodge() == true){
        //If opponent dodges, attack will "miss" and deal no damage
        return 0;
      }
    }
    //Increase defense by 20%
    setDefense(getDefense()*1.2);
    return damage;
  }
  
  //Resets Defense Stat
  public void resetDefense(){
    setDefense(baseDefense);
  }
}