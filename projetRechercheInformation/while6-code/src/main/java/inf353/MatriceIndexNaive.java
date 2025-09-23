package inf353;

import java.io.*; 
import java.util.ArrayList;
import java.util.List;

public class MatriceIndexNaive implements MatriceIndex {
    int[][] matrice;
     int nbreDeDocuments;
    int nbreDeMots;

    
    public MatriceIndexNaive(int nbrDeDocuments, int nbrDeMots) {
     this.nbreDeDocuments = nbrDeDocuments;
        this.nbreDeMots = nbrDeMots;
        this.matrice = new int[nbrDeDocuments][nbrDeMots];
    }

    
    public MatriceIndexNaive(String nomFichier) throws IOException, ClassNotFoundException {
    	
    	ObjectInputStream on=new ObjectInputStream(new FileInputStream(nomFichier));
    	matrice =(int[][]) on.readObject();
    	nbreDeDocuments =matrice.length ;
    	nbreDeMots=matrice[0].length;
    	
    	
    	
    }

    

    
    // on ira copier la matrice ds un fichier juste le fichier binaire choisi à cause du poids du fichier 
    @Override
    public void sauver(String nomDeFichier)  {
    	ObjectOutputStream s;
		try {
			s = new ObjectOutputStream(new FileOutputStream(nomDeFichier));
			s.writeObject(matrice);
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    	
    }

    
    @Override
    public int val(int ndoc, int nterm) {
        return matrice[ndoc][nterm];
    }

      
    @Override
    public void incremente(int ndoc, int nterm) {
        matrice[ndoc][nterm] = matrice[ndoc][nterm] +1 ;
    }

    // Méthode pour affecter une valeur à un élément de la matrice juste en faisant la correspondance 
    @Override
    public void affecte(int ndoc, int nterm, int val) {
        matrice[ndoc][nterm] = val;
    }
    
    
    
    
}

    
