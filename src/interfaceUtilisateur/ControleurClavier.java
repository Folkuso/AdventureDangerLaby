package interfaceUtilisateur;

/**
 * Cette classe est un listener pour les événements claviers utilisé pour contrôler
 * les éléments du jeu.
 *
 * Ces méthodes hérités ne sont pas utilisées:
 *	public void keyReleased(KeyEvent e) {}
 *  public void keyTyped(KeyEvent e) {}
 *
 * @author Fred Simard | ETS
 * @version Hiver 2022 - TP2
 */


import java.awt.event.*;
import java.util.Observable;

import modele.PlanDeJeu;
import physique.Direction;

public class ControleurClavier implements KeyListener{

    // référence au plan de jeu
    private PlanDeJeu planDeJeu;

    /**
     * Méthode pour attacher le plan de jeu
     * @param planDeJeu, référence au plan de jeu
     */
    public void attacherPlanDeJeu(PlanDeJeu planDeJeu){
        this.planDeJeu = planDeJeu;
    }

    /**
     * gestionnaire d'événement associé au clavier
     * @param e(KeyEvent), evénement clavier
     */
    @Override
    public void keyPressed(KeyEvent e) {

		/*
		// obtient une référence au joueur courant
		Joueur joueurControlle =  planDeJeu.getJoueur();

		// s'assure qu'un joueur a été initialisé
		if(joueurControlle != null){

			int keyCode = e.getKeyCode();

			// gestion de l'action en fonction de l'événement clavier
			switch(keyCode){

				case Touche.FLECHE_HAUT:

					break;
				case Touche.FLECHE_BAS:

					break;
				case Touche.FLECHE_GAUCHE:

					break;
				case Touche.FLECHE_DROITE:

					break;
			}
		}
		*/
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

}
