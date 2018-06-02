package paket;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class TestClass {

	@Test
	public void test1() {
		Unos testUnos = new Unos();
		try {
			assertEquals("Ovo je testni ispis", testUnos.unosSeta("testniarff.arff"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test //R2_0test.arff
	public void test2() {
		Unos testUnos = new Unos();
		try {
			assertEquals("Ovo je testni ispis", testUnos.unosSeta("R2_0test.arff"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test3() {
		obrada testObrada = new obrada();
		File file1 = new File("R2_0test.arff");
		File file2 = new File("R2_1test.arff");
		String ocekivani =
				"Broj modula u prvom datasetu: 10\n" + 
				"Broj modula u drugom datasetu: 10\n" + 
				"Broj promjenjenih modula: 7\n" + 
				"Broj nepromijenjenih modula: 1\n" + 
				"Broj modula koji su maknuti u novoj verziji: 2\n" + 
				"\n" + 
				"Broj FP kandidata(promjenjeni + maknuti): 9\n" + 
				"Broj NFP kandidata(nepromjenjeni moduli): 1\n" + 
				"";
		
		assertEquals(ocekivani, testObrada.obradi(file1, file2, 'n'));
	}
	
}
