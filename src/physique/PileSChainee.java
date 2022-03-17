package physique;

public class PileSChainee {
    private Noeud sommet;
    private int nbElements;



    public PileSChainee()
    {
        nbElements = 0;
        sommet = null;
    }


    public void empiler(Object object)
    {
        Noeud nouveau = new Noeud(this.sommet, object);
        this.sommet = nouveau;
        nbElements++;
    }

    public Object depiler()
    {
        Object rep = null;

        if(nbElements !=0)
        {
            rep = this.sommet.donnee;
            this.sommet = this.sommet.suivant;
            nbElements--;
        }

        return rep;
    }

    public Object regarder()
    {
        Object rep =null;

        if(nbElements >0)
        {
            rep = this.sommet.donnee;
        }

        return rep;
    }

    public boolean estVide() 
    {
    
    	return nbElements == 0 ;
    }

    private class Noeud
    {
        public Object donnee;
        public Noeud suivant;

        public Noeud(Noeud suivant, Object nouveau)
        {
            this.donnee = nouveau;
            this.suivant = suivant;
        }
    }

}
