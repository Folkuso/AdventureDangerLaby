package physique;

import java.util.Random;

public class Direction {

    public static final int HAUT = 0;
    public static final int BAS = 1;
    public static final int GAUCHE = 2;
    public static final int DROITE = 3;


    static Random nbrAleat = new Random();

    public static int directionOpposee(int direction) {

        //ne sait pas si peut retourner meme variable ou doit retourner une nouvelle

        switch(direction) {

            case HAUT:
                direction = BAS;
                break;
            case BAS:
                direction = HAUT;
                break;
            case GAUCHE:
                direction = DROITE;
                break;
            case DROITE:
                direction  = GAUCHE;
                break;
        }


        return direction;
    }

    public static Position directionAPosition(int direction) {

        Position p = new Position();

        switch(direction)
        {

            case HAUT:
                p.setI(-1);
                p.setJ(0);
                break;
            case BAS:
                p.setI(1);
                p.setJ(0);
                break;
            case GAUCHE:
                p.setI(0);
                p.setJ(-1);
                break;
            case DROITE:
                p.setI(0);
                p.setJ(1);
                break;
        }

        return p;
    }


    public static int positionADirection(Position p) {

        int direction;

        if(p.getI() == -1 && p.getJ() == 0)
            direction = HAUT;
        else if(p.getI() == 1 && p.getJ() == 0)
            direction = BAS;
        else if(p.getI() == 0 && p.getJ() == -1)
            direction = GAUCHE;
        else if(p.getI() == 0 && p.getJ() == 1)
            direction = DROITE;
        else
            direction = -1;


        return direction;
    }

    public static int obtenirDirAlea(){

        int direction;

        direction = nbrAleat.nextInt(4);

        return direction;
    }
}
