package inf353;

public class CelluleS {

    public String mot;
    public int indice;
    public CelluleS suivant ;

    public CelluleS () {
        super();
    }

    public CelluleS (String mot ,int indice) {
        this.mot=mot;
        this.indice=indice;
        this.suivant =null ;

    }

    public CelluleS (String mot ,int indice, CelluleS suivant ) {
        this.mot=mot;
        this.indice=indice;
        this.suivant =suivant ;

    }

    public CelluleS (CelluleS suivant ) {
        this.suivant=suivant ;
    }
}


