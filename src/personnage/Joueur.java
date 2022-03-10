package personnage;

import donjon.Case;
import physique.Direction;
import physique.Position;

public class Joueur extends AbstractPersonnage {

    public Joueur(Position pos){
        super(pos);
    }


    public void seDeplacer(int direction){
        // obtient une référence sur le voisin
        Case voisin = caseCourante.getVoisin(direction);

        // vérifie que le voisin est valide (ne l'est pas quand il y a un mur)
        if(voisin != null){

            // met à jour la position
            caseCourante = voisin;
            pos.additionnerPos(Direction.directionAPosition(direction));
            this.avertirLesObservers();
        }
    }

}