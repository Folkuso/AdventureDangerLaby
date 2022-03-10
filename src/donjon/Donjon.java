package donjon;
import physique.*;

import java.util.Random;

public class Donjon {

	Case caseDepart;
    Case caseFin;
    Case[][] casesJeu;

    Random aleatoire = new Random();

    public Donjon() //Constructeur sans paramètres
    {
        //reference aux configurations
        Configuration instance = Configuration.getInstance();
        //initialise le tableau 2D a l'aide des dimensions provenant des configurations
        casesJeu =  new Case[instance.getConfig(Configuration.NB_LIGNES)][instance.getConfig(Configuration.NB_COLONNES)];

        //case depart choisie au hasard, 2 aleatoire 1 pour i et 1 pour j
        caseDepart = new Case(getPositionAlea());

        // produit le labyrinthe
        this.produireLabyrinthe();
        // assigne la fin
        this.caseFin.setFinNiveau(true);
    }

    public Case[] getCaseDebutetFin() //informatrice pour case de départ et case de fin
    {
        Case tab[] = new Case[2];

        tab[0] = this.caseDepart;
        tab[1] = this.caseFin;

        return tab;
    }

    public Case[][] getCasesJeu() //informatrice pour obtenir une référence sur le tableau 2D
    {
        return this.casesJeu;
    }

    public Position getPositionAlea() //retourne une une position, choisie aléatoirement à l’intérieur du donjon
    {
        Configuration instance = Configuration.getInstance();
        return new Position(aleatoire.nextInt(instance.getConfig(Configuration.NB_LIGNES)), aleatoire.nextInt(instance.getConfig(Configuration.NB_COLONNES)));
    }


    public int getNbVoisinsNonDeveloppe(Position p) //retourne le nombre de voisins pas développé autour de la case p
    {
    	//Modifier 
        int i, compte = 0;
        
        Case voisin;
        Position copieVoisin;
        Configuration instance = Configuration.getInstance();


        //pour toutes les directions
        for(i= 0 ; i< 4;i++)
        {
            //aTester.setVoisin(i, casesJeu[p.getI()][p.getJ()]);  pas besoin de set voisin ici
            voisin = casesJeu[p.getI()][p.getJ()].getVoisin(i);
            copieVoisin = voisin.getCopiePosition();
            if(copieVoisin.getI()>=0 && copieVoisin.getI()<instance.getConfig(Configuration.NB_LIGNES)
                    && copieVoisin.getJ() >= 0 && copieVoisin.getJ() < instance.getConfig(Configuration.NB_COLONNES))
            {
                if(!voisin.estDeveloppe())
                {
                    compte++;
                }
            }
        }
        return compte;
    }

    public Case getVoisinLibreAlea(Position p) //retourne aléatoirement un voisin libre de la case p
    {
    	//� corriger il y a pls erreurs
        int i;
        boolean trouver = false;
        Position voisin = p.clone();
        Configuration instance = Configuration.getInstance();

        //pour toutes les directions
        i = Direction.obtenirDirAlea();
        while (!casesJeu[voisin.getI()][voisin.getJ()].estDeveloppe())
        {
            if(voisin.getI()>=0 && voisin.getI()<instance.getConfig(Configuration.NB_LIGNES)
                    && voisin.getJ() >= 0 && voisin.getJ() < instance.getConfig(Configuration.NB_COLONNES))
            {
                if(!voisin.estDeveloppe())
                {
                    trouver = true;
                }
            }

            else
            {
                i = Direction.obtenirDirAlea();
            }
        }

        return aTester;
    }


    public Case getVoisinAlea(Position p) //retourne un voisin choisi aléatoirement (que le voisin soit développé ou pas)
    {
        Configuration instance = Configuration.getInstance();
        Position positionAlea;
        Case leVoisinAlea;

        int intDirection;

        do {

            intDirection = Direction.obtenirDirAlea();

            positionAlea = Direction.directionAPosition(intDirection);

            positionAlea.additionnerPos(p);

           //est-ce que je suis dans le donjon
        } while(!(positionAlea.getI()>=0 &&
        		positionAlea.getI()<instance.getConfig(Configuration.NB_LIGNES) &&
        		positionAlea.getJ() >= 0 && 
        		positionAlea.getJ() < instance.getConfig(Configuration.NB_COLONNES)));

        leVoisinAlea = new Case(positionAlea);

        return leVoisinAlea;
    }

    public void produireLabyrinthe()
    {
        Case aTesterC;
        Case voisinC;
        Position aTesterP;
        Position voisinP;
        int voisinDirection;
        int nbCaseDeveloppe =0;
        // developpe le labyrinthe a partir de la case depart
        // l'empile
        PileSChainee pile = new PileSChainee();
        pile.empiler(this.caseDepart);
        //tant que la pile n'est pas vide, continue
        while(!pile.estVide())
        {
            // prend la case du haut de la pile sans l’enlever
            aTesterC = (Case)pile.regarder();
            // obtient sa position
            aTesterP = aTesterC.getCopiePosition();
            // indique que cette case est maintenant developpee
            aTesterC.setDeveloppe(true);
            // verifie si cette case a un voisin non developpe
            nbCaseDeveloppe = this.getNbVoisinsNonDeveloppe(aTesterP);
            // oui, choisit une case non developpee voisine au hasard
            if(nbCaseDeveloppe >0)
            {
                // obtient la position du voisin
                voisinC = this.getVoisinLibreAlea(aTesterP);
                voisinP = voisinC.getCopiePosition();

                // calcul la direction du voisin
                // position voisin moins position case courante
                // -> position à direction
                voisinP.soustrairePos(aTesterP); // A partir de cet instant voisinP n'est pas la valeur de position exacte
                voisinDirection = Direction.positionADirection(voisinP);

                // ajoute a la case, comme voisin reciproque
                // appel a setVoisin pour les deux cases
                // note: la droite d'une case est la gauche de l'autre,
                // utiliser directionOpposee
                aTesterC.setVoisin(voisinDirection);
                voisinC.setVoisin(Direction.directionOpposee(voisinDirection));

                // ajoute le voisin à la pile
                pile.empiler(voisinC);
                // definit la fin comme etant la derniere case developpee
                this.caseFin = (Case)pile.regarder();
            }
            else
            {
                pile.depiler();
            }
        }
    }
}
