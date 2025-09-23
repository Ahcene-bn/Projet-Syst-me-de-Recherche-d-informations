package inf353;




import java.io.File;


public class dictionnaireDocuments {
    int T = 6000;
    String repertoire;
    public DictionnaireCreux Corpus;


    public dictionnaireDocuments() {
        /*
         * Corpus est le resultat attendu avec la liste des fichiers normalement
         */
        this.Corpus = new DictionnaireCreux(1000);

    }

    public DictionnaireCreux creationDesFichiers(String repertoire) {
        File rep = new File(repertoire);

        if (rep.isDirectory() && !rep.isHidden()) {
            Parcours(rep);
        } else {
            System.out.println("veuillez saisir un répertoire valide qui contients les documents s'il vous plaît ");

        }


        return Corpus;

    }


    private void Parcours(File repertoire) {
        File[] listesfichiers = repertoire.listFiles();
        if (listesfichiers != null) {
            for (File fich : listesfichiers) {
                if (fich.isFile() && !fich.isHidden()) {

                    Corpus.ajouterMot(fich.getAbsolutePath());//nous ajoutons ici le fichier dans la liste

                } else if (fich.isDirectory()) {
                    try {
                        Parcours(fich);
                    } catch (StackOverflowError rep) {
                    }


                }
            }

        }
    }
    
}
