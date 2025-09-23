package inf353;


import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;




public class DicoCreuxTest {
	
		 Dictionnaire dictionnaire = new DictionnaireCreux(10); // Initialise avec une taille de 10
		    

		    @Test
		    public void testVider() {
		        dictionnaire.ajouterMot("test");
		        dictionnaire.vider();
		        assertEquals(0, dictionnaire.nbMots());
		    }

		    @Test
		    public void testAjouterMot() {
		        dictionnaire.ajouterMot("test");
		        dictionnaire.ajouterMot("boss") ;
		        assertEquals(2, dictionnaire.nbMots());
		       assertTrue(dictionnaire.contient("test"));
		    }

		    @Test
		    public void testIndiceMot() {
		    	dictionnaire.vider();
		        dictionnaire.ajouterMot("test");
		        dictionnaire.ajouterMot("while");
		        dictionnaire.ajouterMot("code");
		       assertEquals(1, dictionnaire.indiceMot("while"));
		        
		    }

		    @Test
		    public void testMotIndice() {
		        dictionnaire.ajouterMot("test");
		        dictionnaire.ajouterMot("test");
		        dictionnaire.ajouterMot("while6code");
		        assertEquals("test", dictionnaire.motIndice(0));
		    }

		    @Test
		    public void testContient() {
		        dictionnaire.ajouterMot("test");
		        dictionnaire.ajouterMot("aboudou");
		        assertFalse(dictionnaire.contient("while6code"));
		        assertFalse(dictionnaire.contient("nonexistent"));
		        assertTrue(dictionnaire.contient("test"));
		    }

		    @Test
		    public void testNbMots() {
		    	dictionnaire.vider();
		        dictionnaire.ajouterMot("test");
		        dictionnaire.ajouterMot("exemple");
		        assertEquals(2, dictionnaire.nbMots());
		    }

		    @Test
		    public void testContientPrefixe() {
		        dictionnaire.ajouterMot("test");
		        dictionnaire.ajouterMot("exemple");
		        assertTrue(dictionnaire.contientPrefixe("te"));
		        assertFalse(dictionnaire.contientPrefixe("exm"));
		    }

		    /*@Test
		    public void testPlusLongPrefixeDe() {
		        dictionnaire.ajouterMot("test");
		        dictionnaire.ajouterMot("testing");
		        assertEquals("testing", dictionnaire.plusLongPrefixeDe("testing"));
		        assertEquals("", dictionnaire.plusLongPrefixeDe("nonexistent")); // ceci marche parce que en realisant la méthode o s'asssure de retourner le caractere vide 
		    }*/
		    
		    
		    @Test
		    public void testplusavancé () {
		    	dictionnaire.vider();
		    	dictionnaire.ajouterMot("while6code");
		    	dictionnaire.vider();
		    	assertEquals(0 , dictionnaire.nbMots());
		    	
		    	dictionnaire.vider();
		    	dictionnaire.ajouterMot("while6code");
		    	dictionnaire.ajouterMot("testeur");
		    	assertTrue(dictionnaire.contient("testeur"));
		    	dictionnaire.ajouterMot("while6cde");
		    	assertEquals(3 ,dictionnaire.nbMots());
		    	assertEquals(true ,dictionnaire.contientPrefixe("while"));
		    	assertTrue(!dictionnaire.contient("algo"));
		    	assertEquals(3 ,dictionnaire.nbMots());
		    	
		    	
		    	
		    }
		    

		    
		}