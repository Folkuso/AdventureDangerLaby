package physique;

public class Position
{
    //Membres donnant la valeur de la position
    private int i; //selon l'axe vertical
    private int j; //selon l'axe horizontal

    //Constructeurs sans arguments
    public Position()
    {
        this.i = 0;
        this.i = 0;
    }

    //Constructeurs avec arguments
    public Position(int i, int j) //Constructeur par paramètre qui reçoit des valeurs pour i et j
    {
        this.i = i;
        this.j = j;
    }

    public Position(Position p) //Constructeur par copie qui reçoit une référence sur une position
    {
        this.i = p.i;
        this.j = p.j;
    }

    //Getters
    public int getI() //Accesseur (lecture) de l'attribut i
    {
        return this.i;
    }

    public int getJ() //Accesseur (lecture) de l'attribut j
    {
        return this.j;
    }

    //Setters
    public void setI(int i) //Mutateur (écriture) de l'attribut i
    {
        this.i = i;
    }

    public void setJ(int j) //Mutateur (écriture) de l'attribut j
    {
        this.j = j;
    }

    //Méthodes
    public Position clone() //Copie profonde
    {
        return new Position(i,j);
    }

    public boolean equals(Object obj) //Comparaison profonde
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof Position)
        {
            Position cpt = (Position) obj;
            return this.i == cpt.i && this.j == cpt.j;
        }
        else
            return false;
    }

    public void additionnerPos(Position pos) //Reçoit une position et l'ajoute à la position initiale
    {
        this.i = this.i + pos.i;
        this.j = this.j + pos.j;
    }

    public void soustrairePos(Position pos) //Reçoit une position et la soustrait à la position initiale
    {
        this.i = this.i - pos.i;
        this.j = this.j - pos.j;
    }

    public void multiplierPos(Position pos) //Reçoit une position et la multiplie à la position initiale
    {
        this.i = this.i * pos.i;
        this.j = this.j * pos.j;
    }

    public void multiplierPos(double i, double j) //Reçoit deux doubles et les multiplie à la position initiale
    {
        this.i = this.i * (int)i;
        this.j = this.j * (int)j;
    }
}