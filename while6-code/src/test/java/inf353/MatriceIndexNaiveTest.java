package inf353;


import static org.junit.Assert.assertEquals;

import java.io.IOException;
import org.junit.Test;

public class MatriceIndexNaiveTest {


	@Test
	public  void lecttest()  {
		
		System.out.println("voici le test  ");
		MatriceIndexNaive matrice =new MatriceIndexNaive(10,10);
		matrice.affecte(0,2, 20);
		assertEquals(0,matrice.matrice[5][5]);
		assertEquals(0,matrice.matrice[3][4]);
		
		assertEquals(20,matrice.matrice[0][2]);
	}
	
	
	
	@Test
	public void testavance() throws ClassNotFoundException, IOException {
		
		MatriceIndexNaive matric;
		
			
			matric = new MatriceIndexNaive(5,5);
			matric.affecte(0, 0, 3);
			matric.affecte(0, 1, 1);
			matric.sauver("bidule.txt");
        
         
			MatriceIndexNaive matrica =new MatriceIndexNaive("bidule.txt");
			assertEquals( 3,matrica.val(0, 0));
			assertEquals(1,matrica.val(0, 1));
	}
}
