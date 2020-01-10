/* Name: Sean Hua and Frank Kong
 * Date: December 17, 2019
 * Description: Subclass for the Cavalry troop (extends from the combatant class)
 */
public class Cavalry extends Troop{
  private static int cavalryPrice = 500;
  private String attackName = "Charge"; //Name of Basic Attack
  private String specialAttack = "Lifesteal Lance"; //Name of special attack
  //Constructor
  public Cavalry(String team, int[] coords) {
    super("Cavalry", 500, 80, 5, 50, 50, 500, team, 6, coords, "Cavalry.png");
    //Calvary has 500HP, 80 ATK, 5 RNG, 50 DEF, 50 BASEDEF, 500 MAX HP, 10 PENETRATE, 6 TILE MVMT
  }
  //Setters
  public static void setCavalryPrice(int price){
    cavalryPrice = price;
  }
  //Getters
  public static int getCavalryPrice(){
    return cavalryPrice;
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
  
  //Method to heal the Cavalry
  public void heal(double lifeGain){
    //If amount healed exceeeds max HP, then only heal till full
    if ((getHealth() + lifeGain) >= maxHP){
      setHealth(maxHP);
    }
    //Else just increase health by amount healed
    else{
      setHealth(health + lifeGain);
    }
  }
  //Cavalry Special Attack
   public double specialAttack(Combatant opponent){
    double damage = attack*3;
    double lifeSteal = damage/2; //Special attack will only health half of damage dealt
    
    //If opponent is a troop
    if (opponent instanceof Troop){
      //If opponent dodges, then  damage is 0
      if (((Troop)(opponent)).getDodge() == true){
        return 0;
      }
    }
    //If troop does not dodge or opponent is a building
    heal(lifeSteal); //Heal the cavalry with lifesteal
    return damage; //Returns damage dealt to opponent
  }
}

