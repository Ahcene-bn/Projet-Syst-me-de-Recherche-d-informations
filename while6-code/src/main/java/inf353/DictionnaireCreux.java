package inf353;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DictionnaireCreux implements Dictionnaire{
 public int N;
 public int id;
 public CelluleS[] tab;
 public DictionnaireCreux(int N) {
	this.N=N ;
	 
	 this.tab=new CelluleS[N];
			 
 }
 public void sauver(String fichier) throws IOException {
	try { BufferedWriter writer= new BufferedWriter(new FileWriter(fichier));
      int j=0;
		while(j!=nbMots()) {
			System.out.println(motIndice(j));
			writer.write(motIndice(j));
			writer.newLine();
			j++ ;
		}
		writer.close();
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	 
	 
 }
 public DictionnaireCreux (String fichier) throws IOException {
	this.N=100000000;
	tab=new CelluleS [N];
	BufferedReader reader =new BufferedReader (new FileReader(fichier));
	String ligne ;
	while( (ligne=reader.readLine())!=null) {
		 ajouterMot(ligne.trim());
	}
	reader.close();
 }
 
 
 
 
@Override
public void vider() {
	tab=new CelluleS[N];
	this.id =0;
	
	
}
@Override
public void ajouterMot(String m) {
	int idCourant=this.id ;
		    int inditab;
		    CelluleS cc;
		 
		    if(!contient(m)){
		        inditab=Math.abs(m.hashCode())%this.N;
		        cc=tab[inditab];
		        if(cc==null){
		            tab[inditab]=new CelluleS(m,idCourant,null);//on cree la tete de la liste
		        }
		        else{
		        	CelluleS cp=new CelluleS();
		            while(cc!=null){
		                cp=cc;
		                cc=cc.suivant;
		            }
		            cp.suivant=new CelluleS(m,idCourant,cp.suivant);//on ajoute au milieu 
		            
		        }
		        this.id=id+1;    
		    }
			
		}


@Override
public int indiceMot(String m) {
	
	if (contient(m)){
		int i=Math.abs(m.hashCode())%(this.N);
		CelluleS cc=tab[i];
		while((cc!=null) && !(cc.mot.equals(m))){
			cc=cc.suivant;
		}
		return cc.indice;
	}	
	else{
		
		return -1;
	}
}
@Override
public String motIndice(int i) {
	int cp=0;
	
	while(cp!=this.N){
		CelluleS cc=tab[cp];
		while((cc!=null) && (cc.indice!=i)){
			cc=cc.suivant;
		}
		if (cc!=null) {
			return cc.mot;
		}
		cp=cp+1;
	}
	return "";
	
}

@Override
public boolean contient(String m) {
    int inditab;
    CelluleS cc;
    inditab=(Math.abs(m.hashCode()))%(this.N);
    cc=tab[inditab];
    if(cc==null){
        return false;
    }
    else{
        while((cc!=null) && !(cc.mot.equals(m))){
            cc=cc.suivant;
        }
        return cc!=null;
    }
}


@Override
public int nbMots() {
	
	int cmp=0;
	int i=0;
  while (i!= this.N){
	 CelluleS cc=tab[i];
	  while(cc!=null){
		  cmp=cmp+1;
		  cc=cc.suivant;
	  }
	  i=i+1;
  }
  return cmp;
}
@Override
public boolean contientPrefixe(String p) {
	
	int ip=p.length();
	
	 int i = 0;
     while (i != N) {
          CelluleS ti=tab[i];
     
         while (ti != null) {
            
           if (ti.mot.length() >= ip) {
             int k = 0;
             while (k != p.length() && p.charAt(k) == ti.mot.charAt(k)) {
                 k++;
             }
             if (k == p.length()) {
                 return true;
             }
             ti=ti.suivant;
           }
         
         }
         i++;
   
    }
     return false;
}
@Override
public String plusLongPrefixeDe(String mot) {
	// En cours d'etre fait
	return null;
}

 
}


