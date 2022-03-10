package personnage;

/**
 * Classe abstraite d'un personnage d'un jeu
 * 
 * @author Fred Simard | ETS
 * @version Hiver 2022 - TP2
 */

import physique.Direction;
import physique.Position;

import java.util.Observable;

import dongon.Case;
import observer.MonObservable;

public abstract class AbstractPersonnage extends MonObservable {

	// propriétés d'un personnage
	protected Position pos;
	protected Case caseCourante;

	/**
	 * constructeur
	 * @param pos, position I,J dans le labyrinthe
	 */
	public AbstractPersonnage(Position pos){
		this.pos = pos;
	}
	
	/**
	 * méthode pour déplacer un personnage
	 * @param direction, direction du mouvement
	 */
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

	/**
	 * méthode d'accès pour la position du personnage
	 * @return la position du personnage (par copie)
	 */
	public Position getPos(){
		return new Position(pos);
	}

	/**
	 * mutatrice qui définit la case courante
	 * @param caseCourante, case du personnage
	 */
	public void setCase(Case caseCourante){
		this.caseCourante = caseCourante;
	}
	
	/**
	 * informatrice pour la case du personnage
	 * @return case courante
	 */
	public Case getCase(){
		return this.caseCourante;
	}

	
}
