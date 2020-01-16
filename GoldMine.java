/* Name: Frank Kong
 * Date: January 6, 2020
 * Description: Gold Mine Class for gold mines
 */

public class GoldMine extends Building{
  //Fields
  private String attackName = "Arrow Volley"; //Name of Basic Attack
  private String specialAttack = "Fireball"; //Name of special attack
  //Constructor
  public GoldMine(String team, int[] coordinates){
    super("Gold Mine", 750, 30, 5, 750, team, coordinates, "Gold Mine.png");
    //Gold Mines have 1500HP, 30 ATK, 5 RNG, 30 DEF, 30 BASEDEF, 1500 BASE HP

  }
  //Getters
  public String getSpecialAttack(){
    return specialAttack;
  }
  public String getAttackName(){
    return attackName;
  }
  
  
  //Special Attack for Gold Mine
  public double specialAttack(Combatant opponent){
    //Sets special meter back to 0
    setSpecialMeter(0);
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
}