// Name: Sean Hua
// Date: 12/19/2019
// Description: Subcclass of the commbatant class for the footman troop
class Footman extends Combatant{
  
  public Footman(boolean team){
    super("Footman", 400, 60, 5, 0, 0, team, 40, 3);'
    //Footmen have 400 HP, 60ATK, 5 RNG, 40 SPD, 3 TILE MVMT
  }
  
}
