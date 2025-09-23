package inf353 ;

import java.io.IOException;


public class Indexation {
	public String repertoire;
	public String lieuGD;
	public String lieuMat;
	public DictionnaireCreux grosDico;
	public String lieudicDoc;
	public Indexation(String repertoire, String lieuGD, String lieuMat, String lieudicDoc) {
		this.lieuGD= lieuGD;
		this.lieuMat= lieuMat;
		this.grosDico=new DictionnaireCreux(500000);
		this.repertoire= repertoire;
		this.lieudicDoc=lieudicDoc;
	}
		

public void matrice() throws IOException {
	
	MatriceIndex matrico =  (MatriceIndex) new MatriceIndexCreuse(); 
		dictionnaireDocuments a= new dictionnaireDocuments(); 
		Dictionnaire dicoFichier=a.creationDesFichiers(repertoire);
	    ((DictionnaireCreux) dicoFichier).sauver(lieudicDoc);
		if (dicoFichier!=null) {
		int i = 0;
		while (i!=dicoFichier.nbMots()) {// cette boucle sert à parcourir tous les fichiers du Corpus un par un
			try {
			LecteurDocumentNaif lect=new LecteurDocumentNaif(dicoFichier.motIndice(i)) ;
			lect.demarrer();
			while(!lect.finDeSequence()) {
				String m= lect.elementCourant().toLowerCase().trim();
				this.grosDico.ajouterMot(m);
				matrico.incremente(i, grosDico.indiceMot(m));
				lect.avancer();
			}
			i=i+1;
			}catch (IOException e) {
				
				e.printStackTrace();
			}
		  }
		}
		
		grosDico.sauver(lieuGD);
		
		matrico.sauver(lieuMat);
	
		
				 
		
	}

	public static void main(String args[]) throws IOException {
		final long startTime = System.nanoTime();
		 if (args.length==4) {
		 Indexation e= new Indexation (args[0],args[1],args[2],args[3]);
		 e.matrice();
		 	 double duration = System.nanoTime() - startTime;
			 double tempsecond=(duration/1000000000);
			 System.out.println("l'indexation à pris"+tempsecond+"secondes");
	     }
		 else {System.out.println("Entrez un nombre d'arguments valide");
	       }
	}
 
		
}

	