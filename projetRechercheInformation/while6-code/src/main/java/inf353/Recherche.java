package inf353 ;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class Recherche {
    public int N = 5000;
    public String req;
    public String lieuGD;
    public String lieuMat;
    public DictionnaireCreux dictio;
    public Dictionnaire dicDoc;
    public String lieufich;
    public String lieuDoc;

    public Recherche(String lieuMat, String lieuGD, String req, String lieufich, String lieuDoc) throws IOException {
        this.req = req;
        this.lieuGD = lieuGD;
        this.lieuMat = lieuMat;
        this.lieufich = lieufich;
        this.lieuDoc = lieuDoc;
        this.dictio = new DictionnaireCreux(lieuDoc);
    }

    public void resultat() throws IOException, ClassNotFoundException {
        MatriceIndex matrice = new MatriceIndexCreuse(lieuMat);
        DictionnaireCreux dico = new DictionnaireCreux(lieuGD);

        try (BufferedReader reader = new BufferedReader(new FileReader(this.req));
             BufferedWriter writer = new BufferedWriter(new FileWriter(lieufich))) {
            // en effet ici on essaie d'avoir directement toutes les matrices des requetes 
        	int o =91 ;
            String[] requetes = new String[100];
            MatriceIndexCreuse[] matricereq = new MatriceIndexCreuse[100];
            int p = 0;
            String ligne;

            while ((ligne = reader.readLine()) != null) {
                System.out.println("actuellement chargement de la matricereq " + p);
                MatriceIndexCreuse mat = new MatriceIndexCreuse();
                requetes[p] = ligne;
                String[] mots = ligne.split("[,;:§?!'=\" \\-_./\\\\| \\n\\r]+");

                for (String mot : mots) {
                    int indice = dico.indiceMot(mot.toLowerCase().trim());
                    
                        mat.incremente(0, indice);
                    
                }
                matricereq[p] = mat;
                p++;
            }

            int nbDocs = dictio.nbMots();
            int k=0;
            while (k !=p) {
                System.out.println("actuellement calcul des pertinences avec la req " + k);
                double[] lstPrt = new double[nbDocs];
                String[] tabo = requetes[k].split("[,;:§?!'=\" \\-_./\\\\| \\n\\r]+");
                int[] motsIndices = new int[tabo.length];

                for (int r = 0; r < tabo.length; r++) {
                    motsIndices[r] = dico.indiceMot(tabo[r]);
                }

                for (int j = 0; j < nbDocs; j++) {
                    double prod = 0;
                    for (int m : motsIndices) {
                    	
                    	    
                    	 //matrice.val(j, m
                            prod = prod + 1; 
                        
                    }
                    lstPrt[j] = prod;
                }

                double[] dmax = new double[150];
                int[] dind = new int[150];

                for (int t = 0; t < 150; t++) {
                    double max = -1;
                    int ind = 0;
                    for (int i = 0; i < lstPrt.length; i++) {
                        if (lstPrt[i] > max) {
                            max = lstPrt[i];
                            ind = i;
                        }
                    }
                    dmax[t] = max;
                    dind[t] = ind;
                    lstPrt[ind] = -1; //apparemment le code qui enlevait le maximum n'enlevait pas vraiment du coup celui ci est meilleur on l'enleve pas mais on s'assure qu'il n'interviendra plus jamais 
                }
                
                int f=0;
                while (f!=150) {
                    writer.write(o+"\tQ0\t"+baseName(dictio.motIndice(dind[f]))+"\t"+(f+1)+"\t"+dmax[f]+"\tSTANDARD\n");
                    f++;
                }
                
                k++;
                o++;
            }
        }
    }
    public String baseName(String chemin) {
   	   int ddt= 0;int drSlash=0;
   		
   	   while(ddt!=chemin.length()){
   			if(chemin.charAt(ddt)=='\\'){
   				drSlash=ddt;

   			}
   			ddt++;
   	  }
   		return " "+chemin.substring(drSlash+1,chemin.length());
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
    	final long startTime = System.nanoTime();
        if (args.length == 5) {
            Recherche e = new Recherche(args[0], args[1], args[2], args[3], args[4]);
            e.resultat();
            double duration = System.nanoTime() - startTime;
			 double tempsecond=(duration/1000000000);
			 System.out.println("la recherche vient de prendre "+tempsecond+"ptn de secondes");
        } else {
            System.out.println("Entrez un nombre d'arguments valide");
        }
    }
}

	