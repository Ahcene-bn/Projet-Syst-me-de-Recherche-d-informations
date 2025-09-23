package inf353 ;

import java.io.IOException;

public class Indexation {
    public String repertoire; // chemin du dossier contenant les documents à indexer
    public String lieuGD; // chemin pour sauvegarder le dictionnaire global
    public String lieuMat; // chemin pour sauvegarder la matrice d'index
    public DictionnaireCreux grosDico; // dictionnaire global des mots
    public String lieudicDoc; // chemin pour sauvegarder le dictionnaire des documents

    // Constructeur : initialise les chemins et crée un dictionnaire creux de grande taille
    public Indexation(String repertoire, String lieuGD, String lieuMat, String lieudicDoc) {
        this.lieuGD= lieuGD;
        this.lieuMat= lieuMat;
        this.grosDico=new DictionnaireCreux(500000); // dictionnaire global pour tous les mots
        this.repertoire= repertoire;
        this.lieudicDoc=lieudicDoc;
    }

    // Méthode principale pour créer la matrice d'indexation
    public void matrice() throws IOException {
        // Création d'une matrice d'index creuse (document x mot)
        MatriceIndex matrico =  (MatriceIndex) new MatriceIndexCreuse();
        // Création d'un dictionnaire de documents
        dictionnaireDocuments a= new dictionnaireDocuments();
        Dictionnaire dicoFichier = a.creationDesFichiers(repertoire);

        // Sauvegarde du dictionnaire de documents
        ((DictionnaireCreux) dicoFichier).sauver(lieudicDoc);

        if (dicoFichier != null) {
            int i = 0;
            // Boucle pour parcourir tous les fichiers du corpus un par un
            while (i != dicoFichier.nbMots()) {
                try {
                    // Lecture du document actuel
                    LecteurDocumentNaif lect = new LecteurDocumentNaif(dicoFichier.motIndice(i));
                    lect.demarrer();

                    // Parcours de tous les mots du document
                    while(!lect.finDeSequence()) {
                        String m = lect.elementCourant().toLowerCase().trim();
                        // Ajout du mot au dictionnaire global
                        this.grosDico.ajouterMot(m);
                        // Incrémentation de la matrice : doc i, mot m
                        matrico.incremente(i, grosDico.indiceMot(m));
                        lect.avancer();
                    }
                    i = i + 1;
                } catch (IOException e) {
                    // Gestion des erreurs de lecture de document
                    e.printStackTrace();
                }
            }
        }

        // Sauvegarde du dictionnaire global et de la matrice d'index
        grosDico.sauver(lieuGD);
        matrico.sauver(lieuMat);
    }

    // Méthode main pour lancer l'indexation depuis la ligne de commande
    public static void main(String args[]) throws IOException {
        final long startTime = System.nanoTime();
        if (args.length == 4) {
            // Instanciation avec les chemins fournis en arguments
            Indexation e = new Indexation(args[0], args[1], args[2], args[3]);
            e.matrice(); // création de la matrice et dictionnaire
            double duration = System.nanoTime() - startTime;
            double tempsecond = (duration / 1000000000);
            System.out.println("l'indexation à pris " + tempsecond + " secondes");
        } else {
            // Message si le nombre d'arguments est incorrect
            System.out.println("Entrez un nombre d'arguments valide");
        }
    }
}
