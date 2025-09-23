package inf353;


import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LecteurDocTest {
	 LecteurDocumentNaif lecteur;



	 @Test
	    public void testDemarrer() throws IOException {
				lecteur = new LecteurDocumentNaif("src/test/resources/demio.txt");// ici veuillez s'il vous plaît mettre le chemin de votre fichier

	        lecteur.demarrer();
	        assertEquals("Bonjour", lecteur.elementCourant());
	    }

	    @Test
	    public void testAvancer() throws IOException {
				lecteur = new LecteurDocumentNaif("src/test/resources/demio.txt");// ici veuillez s'il vous plaît mettre le chemin de votre fichier

	        lecteur.demarrer();
	        lecteur.avancer();
	        assertEquals("la", lecteur.elementCourant());
	        lecteur.avancer();
	        assertEquals("famille", lecteur.elementCourant());
	    }

	    @Test
	    public void testFinDeSequence() throws IOException {
				lecteur = new LecteurDocumentNaif("src/test/resources/demio.txt");// ici veuillez s'il vous plaît mettre le chemin de votre fichier
	        lecteur.demarrer();
	        while (!lecteur.finDeSequence()) {
	            lecteur.avancer();
	        }
	        assertTrue(lecteur.finDeSequence());
	    }

	    @Test
	    public void testElementCourant() {
	    	try {
				lecteur = new LecteurDocumentNaif("src/test/resources/demio.txt");// ici veuillez s'il vous plaît mettre le chemin de votre fichier
			} catch (IOException e) {

				e.printStackTrace();
			}
	        lecteur.demarrer();
	        assertEquals("Bonjour", lecteur.elementCourant());
	        lecteur.avancer();
	        lecteur.avancer();
	        lecteur.avancer();
	        assertEquals("comment", lecteur.elementCourant());

	        lecteur.avancer();
	        assertEquals("youpi", lecteur.elementCourant());
	        lecteur.avancer();
	        assertFalse(lecteur.finDeSequence());
	    }







}
