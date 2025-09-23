package inf353;

public class DictionnaireNaif implements Dictionnaire {
    int lg = 80;
    int N;
    char[][] tableau;

    public DictionnaireNaif(int N) {
        this.N = N;
        this.tableau = new char[N][lg];
    }

    /**
     * é.i: qcq
     * é.f.: le dictionnaire est vide.
     */
    public void vider() {
        int l = 0;
        while (l != N) {
            tableau[l][0] = '\0';
            l = l + 1;
        }
    }

    /**
     * é.i.: le dictionnaire contient D0 (un ensemble de N mots).
     * é.f.: si m appartient à D0; le dictionnaire est inchangé
     * sinon, le dictionnaire contient D0 U {m}
     *
     * @param m
     */
    public void ajouterMot(String m) {
        int i = 0;
        if (!contient(m)) {
            while (i != N && tableau[i][0] != '\0') {
                i++;
            }
            if (i != N) {
                int j = 0;
                while (j != m.length()) {
                    tableau[i][j] = m.charAt(j);
                    j++;
                }
                tableau[i][j] = '\0';
            } else {
                int j = 0;
                while (j != m.length()) {
                    tableau[i][j] = m.charAt(j);
                    j++;
                }
                tableau[i][j] = '\0';
                this.N = N + 1;
            }
        }
    }

    /**
     * renvoie l'entier associé à m;
     * @param m
     * @return
     */
    public int indiceMot(String m) {
        int i = 0;
        if (contient(m)) {
            while (i != N) {
                int j = 0;
                int nbC = 0;
                while (tableau[i][j] != '\0') {
                    nbC++;
                    j++;
                }
                if (nbC == m.length()) {
                    int k = 0;
                    while (k != nbC && tableau[i][k] == m.charAt(k)) {
                        k++;
                    }
                    if (k == nbC) {
                        return i;
                    }
                }
                i++;
            }
        }
        return -1;
    }

    /**
     * renvoie le mot associé à l'entier i;
     * @param i l'indice du mot à renvoyer
     * @return
     */
    public String motIndice(int i) {
        int j = 0;
        String s = "";
        while (tableau[i][j] != '\0') {
            s = s + tableau[i][j];
            j++;
        }
        return s;
    }

    /**
     * renvoie vrai ssi m est dans le dictionnaire.
     * @param m
     * @return
     */
    public boolean contient(String m) {
        int i = 0;
        while (i != N) {
            int j = 0;
            int nbC = 0;
            while (tableau[i][j] != '\0') {
                nbC++;
                j++;
            }
            if (nbC == m.length()) {
                int k = 0;
                while (k != nbC && tableau[i][k] == m.charAt(k)) {
                    k++;
                }
                if (k == nbC) {
                    return true;
                }
            }
            i++;
        }
        return false;
    }

    /**
     * renvoie le nombre de mots de m.
     * @return
     */
    public int nbMots() {
        int i = 0;
        int nb = 0;
        while (i != N) {
            if (tableau[i][0] != '\0') {
                nb++;
            }
            i++;
        }
        return nb;
    }

    /**
     * vrai ssi il existe m dans D0 tel que il existe u tq m = p.u
     *
     * (vrai si un mot de D0 commence par p)
     * @param p le préfixe recherché
     * @return
     */
    public boolean contientPrefixe(String p) {
        int i = 0;
        while (i != N) {
            int j = 0;
            int nbC = 0;
            while (tableau[i][j] != '\0') {
                nbC++;
                j++;
            }
            if (nbC >= p.length()) {
                int k = 0;
                while (k != p.length() && p.charAt(k) == tableau[i][k]) {
                    k++;
                }
                if (k == p.length()) {
                    return true;
                }
            }
            i++;
        }
        return false;
    }

    /**
     * renvoie la chaîne de caractères s telle que
     * s est dans D0
     * et m commence par s
     * et il n'existe pas de chaîne s' ds D plus longue que s tq m commence par s.
     *
     * @param mot
     * @return
     */
    public String plusLongPrefixeDe(String mot) {
        if (contientPrefixe(mot)) {
            int i = 0;
            String plusLongPrefixe = "";
            int max = 0;

            while (i != N) {
                int j = 0;
                int nbC = 0;
                while (tableau[i][j] != '\0') {
                    nbC++;
                    j++;
                }
                if (nbC >= mot.length()) {
                    int k = 0;
                    while (k != mot.length() && mot.charAt(k) == tableau[i][k]) {
                        k++;
                    }
                    if (k == mot.length()) {
                        if (nbC > max) {
                            max = nbC;
                            plusLongPrefixe = motIndice(i);
                        }
                    }
                }
                i++;
            }
            return plusLongPrefixe;
        }
        return "";
    }
}
