package programme;


public class programmePrincipal {

    /**
     * Programme principal, lance la vue du programme
     *
     * @param args, inutilisé
     */
    public static void main(String[] args){

        // lance le view-controller (à compléter)
        Thread t = new Thread(new vue.CadrePrincipal());
        t.start();

    }

}