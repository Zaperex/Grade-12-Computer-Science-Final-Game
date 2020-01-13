/* Name: Frank Kong
 * Date: December 18, 2019
 * Description: CrossbowMen class
 */

public class CrossbowMen extends Troop{
  private static int crossbowMenPrice = 200;
  private String attackName = "Crossbow Bolt Shot"; //Name of basic attack
  private String specialAttack = "Ballista Shot"; //name of special attack
  public CrossbowMen(String team, int[] coords){
    super("CrossbowMen", 500, 75, 60, 5, 5, 500, team, 4, coords, "Crossbowman.png");
    //Crossbowmen have 500HP, 75DMG, 60 RNG, 5 DEF, 5 BASEDEF, 500 MAX HP, 4 TILE MVMT
  }
  //Setters
  public static void setCrossbowMenPrice(int price){
    crossbowMenPrice = price;
  }
  //Getters
  public static int getCrossbowMenPrice(){
    return crossbowMenPrice;
  }
  public String getAttackName(){
    return attackName;
  }
  public String getSpecialAttack(){
    return specialAttack;
  }
  //Resets Defense Stat
  public void resetDefense(){
    setDefense(baseDefense);
  }
  
  //Special move for Crossbowmen
   public double specialAttack(Combatant opponent){
    double damage = attack*3;
    //If opponent is a troop
    if (opponent instanceof Troop){
      //If opponent dodges
      if (((Troop)(opponent)).getDodge() == true){
        return 0; //Deals no damage
      }
    }
    //If hits, then halve the opponent's defense for the rest of the battle (REMEMBER TO RESET DEFENSE AT THE END OF A BATTLE)
    opponent.setDefense(opponent.getDefense()*(0.5));
    return damage;
  }
    
}