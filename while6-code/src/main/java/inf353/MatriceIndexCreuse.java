package inf353;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class MatriceIndexCreuse implements MatriceIndex ,Serializable  {
	public Cellule[] indices;

	public MatriceIndexCreuse() {
		
	}
	

	@SuppressWarnings("unchecked")
	public MatriceIndexCreuse(String nomDeFichier) throws ClassNotFoundException {
		try {
			ObjectInputStream ob = new ObjectInputStream (new FileInputStream(nomDeFichier));
			matrice =(HashMap<Integer,Cellule>) ob.readObject();
			ob.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void sauver(String nomDeFichier) throws FileNotFoundException {
		try {
			ObjectOutputStream ob =new ObjectOutputStream (new FileOutputStream (nomDeFichier));
			ob.writeObject(matrice );
			ob.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public int val(int ndoc, int nterm) {
		int codedoc =Integer.hashCode(ndoc);	
		
	Cellule c =matrice.get(codedoc);
	while ((c!=null)&&(c.indice!=nterm)) {
		
		c=c.suivant;
	}
	if (c!=null) {
		return c.val;
	} else {
		return 0 ;
	}
	
	
	
	
	
	}

	@Override
	public void incremente(int ndoc, int nterm) {
		int codedoc =Integer.hashCode(ndoc);	
		Cellule c =matrice.get(codedoc);
	if (c!=null) {
		
		Cellule pred = null;
		
		while ((c!=null)&&(c.indice!=nterm)) {
			pred=c;
			c=c.suivant;
		}
		if (c!=null) {
			 c.val=c.val +1 ;
		} else {
	        Cellule h= new Cellule (1,nterm);
	        pred.suivant=h ;
		}
	} else {
		Cellule h= new Cellule (1,nterm);
		matrice.put(codedoc, h);
	}
		
		
		
			
		
	}

	@Override
	public void affecte(int ndoc, int nterm, int val) {
		int codedoc =Integer.hashCode(ndoc);	
		Cellule c =matrice.get(codedoc);
	if (c!=null) {
		
		Cellule pred = null;
		
		while ((c!=null)&&(c.indice!=nterm)) {
			pred=c;
			c=c.suivant;
		}
		if (c!=null) {
			 c.val= val ;
		} else {
	        Cellule h= new Cellule (val,nterm);
	        pred.suivant=h ;
		}
	} else {
		Cellule h= new Cellule (val,nterm);
		matrice.put(codedoc, h);
	}
		
		
	}

}
