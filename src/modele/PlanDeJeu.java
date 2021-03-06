package modele;

import java.util.ArrayList;

/**
 * Le plan de jeu est la classe qui supporte le modèle du programme.
 * Il contient:
 * 	- le donjon
 *  - le joueur
 *  - les créatures
 *
 * et actionne les mécaniques du jeu.
 *
 * Le plan de jeu est implémenté en Lazy Singleton
 *
 * @author Fred Simard | ETS
 * @version Hiver 2022 - TP2
 */

import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import donjon.Case;
import donjon.Configuration;
import donjon.Donjon;
import observer.MonObservable;
import observer.MonObserver;
import physique.Direction;
import physique.Position;
import personnage.*;

public class PlanDeJeu extends MonObservable implements MonObserver, Runnable {

	private Vector<AbstractCreature> listeCreatures = new Vector<>();
	
    private Donjon donjon;
    private boolean partieEnCours = false;
    private int niveauCourant = 0;
    private Random rand = new Random(System.currentTimeMillis());

    private static final PlanDeJeu instance = new PlanDeJeu();
    private static Thread t;
    
    private  Random nbrAleat = new Random(); 

    //Joueur ajouter
    private Joueur leJoueur ;

    /**
     * constructeur du plan de jeu
     */
    public PlanDeJeu() {
        partieEnCours = true;
        nouveauNiveau();
    }

    /**
     * méthode pour obtenir une référence au plan de jeu
     * @return l'instance
     */
    public static PlanDeJeu getInstance(){
        return instance;
    }

    /**
     * méthode pour obtenir une référence au donjon
     * @return référence au donjon
     */
    public Donjon getDonjon(){
        return this.donjon;
    }

    @Override
    /**
     * callback implémenté par l'observer
     */
    public void avertir() {

        // vérifie les règles du jeu
        validerEtatJeu();

        // avertie les observers du plan de jeu
        this.avertirLesObservers();
    }

    @Override
    /**
     * implémente la méthode run de Runnable
     */
    public void run() {

        // tant qu'une partie est en cours
        while(partieEnCours){

            // déplace toutes les créatures à compléter
        	for(int i=0; i< listeCreatures.size(); i++)
        	{
        		listeCreatures.get(i).seDeplacer(Direction.obtenirDirAlea());
        	}
            // attend X nombre de secondes
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(leJoueur.getCase().equals(donjon.getCaseDebutetFin()[1])){
                partieEnCours=false;
                partieGagne();
            }
        }
    }

    /**
     * méthode qui valide les règles du jeu
     */
    private void  validerEtatJeu(){


        // verifie si le joueur vient de trouver de l'équipement
        // oui, ajoute à l'inventaire

        // verifie s'il y a un combat
        // oui, fais la résolution du combat

        // verifie si le joueur est mort...
        // oui, partie perdu


    }

    /**
     * méthode qui lance un nouveau niveau
     */
    private void  nouveauNiveau(){
    	
        // la partie est toujours en cours
        partieEnCours = true;
        // crée un nouveau donjon
        this.donjon = new Donjon();
        initCreature();//Ajouter 2022-01-13
        initJoueur();
        // si la tâche qui gère les créature
        // n'a pas encore été lancé, la lance.
        if(t ==null){
            t = new Thread(this);
            t.start();
        }

    }

    /**
     * méthode qui gère une partie gagné
     */
    private void partieGagne(){

        // incrémente le compteur de niveau
        niveauCourant++;

        // obtient les configs
        Configuration config = Configuration.getInstance();
        int nbCols = (int)config.getConfig(Configuration.NB_COLONNES);
        int nbLignes = (int)config.getConfig(Configuration.NB_LIGNES);
        int nbCreatures = (int)config.getConfig(Configuration.NB_CREATURES);
        // mets à jours les configs
        config.setConfig(Configuration.NB_COLONNES,nbCols+1);
        config.setConfig(Configuration.NB_LIGNES,nbLignes+1);
        config.setConfig(Configuration.NB_CREATURES,nbCreatures+2);

        // lance un nouveau niveau
        nouveauNiveau();
    }

    /**`
     * gestion d'une partie perdu
     */
    private void partiePerdu(){

        // plus de partie en cours
        partieEnCours = false;

        // attend la fin de la tâche
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // remise à zéro du jeu
        Configuration.remiseAZero();
    }
    
    //M?thode qui initialise les cr?atures
    private void initCreature()
    {
    	int i;
    	
    	//r?f?rence aux cases du donjon
    	Case reference[][] = this.donjon.getCasesJeu();
    	//r?f?rence aux configurations du jeu
    	Configuration instance = Configuration.getInstance();
    	
    	//vide le vecteur cr?atures
        listeCreatures.clear();
    	
    	for(i = 0;i<instance.getConfig(Configuration.NB_CREATURES);i++)
    	{
    		Position position;
    		AbstractCreature creature = null;
    		int creatureType, x, y;
    		
            creatureType = nbrAleat.nextInt(3); //choisit un type de cr?ature al?atoirement
            
            x = nbrAleat.nextInt(instance.getConfig(Configuration.NB_LIGNES));
        	y = nbrAleat.nextInt(instance.getConfig(Configuration.NB_COLONNES));
        	position = new Position(x, y); //d?finit la position al?atoire
            
            if (creatureType == 0) //c'est une araignee
            {
            	creature = new Araigne(position); //d?finit le personnage avec la position al?atoire
            }
            
            else if (creatureType == 1) //c'est un dragon
            {
            	creature = new Dragon(position); //d?finit le personnage avec la position al?atoire
            }
            
            else if (creatureType == 2) //c'est un minotaure
            {
            	creature = new Minotaure(position); //d?finit le personnage avec la position al?atoire   
            }
            
            creature.attacherObserver(this);//Ajouter 2022-01-13
        	creature.setCase(reference[x][y]);//Ajouter 2022-01-13
        	listeCreatures.add(creature);
    	}
    }
    
    private void initJoueur() {
    	Case depart = this.donjon.getCaseDebutetFin()[0];
    	Position positionDepart = new Position(depart.getCopiePosition().getI(), depart.getCopiePosition().getJ());
    	
    	this.leJoueur = new Joueur(positionDepart);
    	this.leJoueur.attacherObserver(this);
    	this.leJoueur.setCase(depart);
    }
    
    //fonction rajoutee
    public Joueur getJoueur() {
        return this.leJoueur;
    }

    public Vector<AbstractCreature> getCreatures() {
    	return this.listeCreatures;
    }
}



















