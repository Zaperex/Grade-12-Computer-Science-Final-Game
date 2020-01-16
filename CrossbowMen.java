/* Name: Frank Kong
 * Date: December 18, 2019
 * Description: CrossbowMen class
 */

public class CrossbowMen extends Troop{
  private static int crossbowMenPrice = 200;
  private String attackName = "Crossbow Bolt Shot"; //Name of basic attack
  private String specialAttack = "Ballista Shot"; //name of special attack
  public CrossbowMen(String team, int[] coords){
    super("CrossbowMen", 400, 75, 60, 25, 25, 300, team, 2, coords, "Crossbowman.png");
    //Crossbowmen have 300HP, 75DMG, 60 RNG, 25 DEF, 25 BASEDEF, 300 MAX HP, 2 TILE MVMT
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
  
  //Special move for Crossbowmen
  public double specialAttack(Combatant opponent){
    //Sets special meter back to 0
    setSpecialMeter(0);
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