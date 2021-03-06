/* Name : Sean Hua
 * Date: December 17, 2019
 * Description: Subclass for the archer troop (extends from the combatant class)
 */

public class Archer extends Troop {
  private static int archerPrice = 50;
  private String attackName = "Arrow Shot"; //Name of Basic Attack
  private String specialAttack = "Triple Shot"; //Name of special attack
  //Constructor
  public Archer (String team, int[] coords) {
    super("Archer", 200, 50, 40, 15, 15, 150, team, 3, coords, "Archer.png");
    //Archers have 150 HP, 50 ATK, 15 DEF, 15 BASEDEF, 150 MAX HP, 40 RNG, 3 TILE MVMT
  }
  //Setters
  public static void setArcherPrice(int price){
    archerPrice = price;
  }
  //Getters
  public static int getArcherPrice(){
    return archerPrice;
  }
  public String getSpecialAttack(){
    return specialAttack;
  }
  public String getAttackName(){
    return attackName;
  }
  
  //Special attack for archer
  public double specialAttack(Combatant opponent){
    //Sets special meter back to 0
    setSpecialMeter(0);
    double damage = attack*3; //Damage is 3 times that of a normal attack and will ignore armour
    //If opponent is a troop
    if (opponent instanceof Troop){
      if (((Troop)(opponent)).getDodge() == true){
        //If opponent dodges, attack will "miss" and deal no damage
        return 0;
      }
    }
    //Else just return damage
    return damage;
  }
  
}