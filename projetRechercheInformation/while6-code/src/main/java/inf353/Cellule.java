package  inf353; 

import java.io.Serializable;

public class Cellule implements Serializable {
	
	public int val ,indice ;
	 public Cellule suivant ;
	
	public Cellule () {
		super();
	}
	
	public Cellule (int val ,int indice) {
		this.val=val;
		this.indice=indice; 
		this.suivant =null;
		
	
		
	}

	public Cellule (int val ,int indice, Cellule suivant ) {
		this.val=val ;
		this.indice=indice; 
		this.suivant =suivant ;
		
	}
	
	public Cellule (Cellule suivant ) {
		this.suivant=suivant ;
	}
	
	
	
	
	

}
