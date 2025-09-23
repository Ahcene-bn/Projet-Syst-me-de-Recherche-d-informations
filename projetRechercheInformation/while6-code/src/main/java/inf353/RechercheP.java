package inf353 ;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class RechercheP {
    public int N = 5000; // taille maximale par défaut (non utilisé directement plus bas)
    public String req; // chemin vers le fichier de requêtes
    public String lieuGD; // chemin vers le dictionnaire global
    public String lieuMat; // chemin vers la matrice globale
    public DictionnaireCreux dictio; // dictionnaire principal (documents)
    public DictionnaireCreux antidico ; // dictionnaire des mots à ignorer
    public Dictionnaire dicDoc; // dictionnaire des documents
    public String lieufich; // chemin du fichier résultat
    public String lieuDoc; // chemin vers les documents

    // Constructeur : initialise les attributs et charge le dictionnaire de documents
    public RechercheP(String lieuMat, String lieuGD, String req, String lieufich, String lieuDoc) throws IOException {
        this.req = req;
        this.lieuGD = lieuGD;
        this.lieuMat = lieuMat;
        this.lieufich = lieufich;
        this.lieuDoc = lieuDoc;
        this.dictio = new DictionnaireCreux(lieuDoc);
    }

    // Méthode principale de calcul des résultats
    public void resultat() throws IOException, ClassNotFoundException {
        // dictionnaire des mots à exclure (antidictionnaire)
        antidico = new DictionnaireCreux ("C://perso files/tests/antidic.txt/");

        // Chargement de la matrice globale et du dictionnaire global
        MatriceIndex matrice = new MatriceIndexCreuse(lieuMat);
        DictionnaireCreux dico = new DictionnaireCreux(lieuGD);

        // Lecture des requêtes depuis un fichier et écriture des résultats
        try (BufferedReader reader = new BufferedReader(new FileReader(this.req));
             BufferedWriter writer = new BufferedWriter(new FileWriter(lieufich))) {

            int o =91 ; // identifiant initial de la requête
            String[] requetes = new String[100]; // stockage des requêtes (max 100)
            MatriceIndexCreuse[] matricereq = new MatriceIndexCreuse[100]; // stockage éventuel des matrices de requêtes
            int p = 0; // compteur de requêtes
            String ligne;

            // Lecture ligne par ligne des requêtes
            while ((ligne = reader.readLine()) != null) {
                System.out.println("actuellement chargement de la matricereq " + p);
                MatriceIndexCreuse mat = new MatriceIndexCreuse();
                requetes[p] = ligne;
                /*
                // Ancien code pour incrémenter la matrice de requête (désactivé)
                String[] mots = ligne.split("[,;:§?!'=\" \\-_./\\\\| \\n\\r]+");
                for (String mot : mots) {
                    int indice = dico.indiceMot(mot.toLowerCase().trim());
                    mat.incremente(0, indice);
                }
                matricereq[p] = mat;
                */
                p++;
            }

            int nbDocs = dictio.nbMots(); // nombre de documents
            int k=0; // index de requête en cours

            // Boucle sur toutes les requêtes lues
            while (k !=p) {
                System.out.println("actuellement calcul des pertinences avec la req " + k);
                double[] lstPrt = new double[nbDocs]; // scores de pertinence par document
                String[] tabo = requetes[k].split("[,;:§?!'=\" \\-_./\\\\| \\n\\r]+"); // découpage de la requête en mots
                int[] motsIndices = new int[tabo.length];

                // Récupération des indices des mots de la requête dans le dictionnaire global
                for (int r = 0; r < tabo.length; r++) {
                    motsIndices[r] = dico.indiceMot(tabo[r]);
                }

                // Calcul de l'IDF pour chaque mot du dictionnaire global
                int a =0;
                int N =dico.nbMots() ;
                double[] idf = new double[N];
                while (a!=N) {
                    int ok =0 ;
                    int df=0 ; // document frequency
                    while (ok !=dictio.nbMots()) {
                        if ((matrice.val(ok, a)) !=0) {
                            df ++;
                        }
                        ok++ ;
                    }
                    idf[a] = 1 + Math.log(N / df) ; // formule IDF
                    a++ ;
                }

                // Calcul du score de pertinence pour chaque document
                for (int j = 0; j < nbDocs; j++) {
                    double prod = 0;
                    for (int m : motsIndices) {
                        // Contribution uniquement si le mot est présent et pas dans l'antidictionnaire
                        if( (matrice.val(j, m)!=0) && !(antidico.contient(dico.motIndice(m)))) {
                            prod = prod + idf[m]*matrice.val(j, m);
                        }
                    }
                    lstPrt[j] = prod; // pertinence du document j
                }

                // Sélection des 50 documents les plus pertinents
                double[] dmax = new double[50];
                int[] dind = new int[50];

                for (int t = 0; t < 50; t++) {
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
                    lstPrt[ind] = -1; // empêche ce doc d’être repris une deuxième fois
                }

                // Écriture des 50 meilleurs résultats dans le fichier de sortie
                int f=0;
                while (f!=50) {
                    writer.write(o+"\tQ0\t"+baseName(dictio.motIndice(dind[f]))+"\t"+(f+1)+"\t"+dmax[f]+"\tSTANDARD\n");
                    f++;
                }

                k++; // prochaine requête
                o++; // incrémentation de l’ID de requête
            }
        }
    }

    // Méthode utilitaire pour extraire le nom du fichier à partir d’un chemin (Windows style avec "\")
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

    // Méthode main pour lancer le programme
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final long startTime = System.nanoTime();
        if (args.length == 5) {
            // Instanciation avec les chemins donnés en arguments
            RechercheP e = new RechercheP(args[0],args[1],args[2],args[3],args[4]);
            e.resultat();
            double duration = System.nanoTime() - startTime;
            double tempsecond=(duration/1000000000);
            System.out.println("la recherche vient de prendre "+tempsecond+"ptn de secondes");
        } else {
            System.out.println("Entrez un nombre d'arguments valide");
        }
    }
}
