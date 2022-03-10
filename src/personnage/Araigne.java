package personnage;

import physique.Position;

public class Araigne extends AbstractCreature {

    public Araigne(Position pos){
        super(pos);
    }

    public void seDeplacer(int direction){
        super.seDeplacer(direction);
        super.seDeplacer(direction);
    }

}