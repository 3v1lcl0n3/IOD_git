package paket;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class TestClass {

	@Test
	public void test_UnosSeta() {
		try {
			assertEquals("Ovo je testni ispis", Unos.unosSeta("testniarff.arff"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_Obrada_Nacin1() {
		GUI.odabraniAlgoritam = 0;
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
		
		assertEquals(ocekivani, obrada.obradi(file1, file2, 'n'));
	}
	
	@Test
	public void test_Obrada_Nacin2() {
		GUI.odabraniAlgoritam = 0;
		File file1 = new File("R2_0test.arff");
		File file2 = new File("R2_1test.arff");
		String ocekivani =
				"Broj modula u prvom datasetu: 5\n" + 
				"Broj modula u drugom datasetu: 7\n" + 
				"Broj promjenjenih modula: 3\n" + 
				"Broj nepromijenjenih modula: 1\n" + 
				"Broj modula koji su maknuti u novoj verziji: 1\n" + 
				"\n" + 
				"Broj FP kandidata(promjenjeni + maknuti): 3\n" + 
				"Broj NFP kandidata(nepromjenjeni moduli): 1\n" + 
				"";
		
		assertEquals(ocekivani, obrada.obradi(file1, file2, 'm'));
	}
	
	@Test
	public void test_Obrada_Nacin3() {
		GUI.odabraniAlgoritam = 0;
		File file = new File("R2_0test.arff");
		String ocekivani =
				"Broj modula u datasetu: 10\n" + 
				"\n" + 
				"Broj FP kandidata: 5\n" + 
				"Broj NFP kandidata: 5\n" + 
				"";
		
		assertEquals(ocekivani, obrada.obradi(file, '3'));
	}
	
	@Test
	public void test_Analiza_NaiveBayes_Nacin1() {
		GUI.odabraniAlgoritam = 0;
		File file1 = new File("R2_0test.arff");
		File file2 = new File("R2_1test.arff");
		obrada.obradi(file1, file2, 'n');
		String ocekivani =  "\n" +
				"Correctly Classified Instances          10              100      %\n" +
				"Incorrectly Classified Instances         0                0      %\n" +
				"Kappa statistic                          1     \n" + 
				"Mean absolute error                      0     \n" +
				"Root mean squared error                  0     \n" +
				"Relative absolute error                  0      %\n" +
				"Root relative squared error              0      %\n" +
				"Total Number of Instances               10     \n";
		assertEquals(ocekivani, Analiza.analiza('n'));
	}
	
	@Test
	public void test_Analiza_NaiveBayes_Nacin2() {
		GUI.odabraniAlgoritam = 0;
		File file1 = new File("R2_0test.arff");
		File file2 = new File("R2_1test.arff");
		obrada.obradi(file1, file2, 'm');
		String ocekivani =  "\n" +
				"Correctly Classified Instances           4              100      %\n" +
				"Incorrectly Classified Instances         0                0      %\n" +
				"Kappa statistic                          1     \n" + 
				"Mean absolute error                      0     \n" +
				"Root mean squared error                  0     \n" +
				"Relative absolute error                  0      %\n" +
				"Root relative squared error              0      %\n" +
				"Total Number of Instances                4     \n";
		assertEquals(ocekivani, Analiza.analiza('m'));
	}
	
	@Test
	public void test_Analiza_NaiveBayes_Nacin3() {
		GUI.odabraniAlgoritam = 0;
		File file = new File("R2_0test.arff");
		obrada.obradi(file, '3');
		String ocekivani =  "\n" +
				"Correctly Classified Instances           9               90      %\n" +
				"Incorrectly Classified Instances         1               10      %\n" +
				"Kappa statistic                          0.8   \n" + 
				"Mean absolute error                      0.1   \n" +
				"Root mean squared error                  0.3162\n" +
				"Relative absolute error                 20.0001 %\n" +
				"Root relative squared error             63.2456 %\n" +
				"Total Number of Instances               10     \n";
		assertEquals(ocekivani, Analiza.analiza('3'));
	}
	
	@Test
	public void test_Analiza_BayesNet_Nacin1() {
		GUI.odabraniAlgoritam = 1;
		File file1 = new File("R2_0test.arff");
		File file2 = new File("R2_1test.arff");
		obrada.obradi(file1, file2, 'n');
		String ocekivani = "\n" +
				"Correctly Classified Instances           9               90      %\n" +
				"Incorrectly Classified Instances         1               10      %\n" +
				"Kappa statistic                          0     \n" + 
				"Mean absolute error                      0.2091\n" +
				"Root mean squared error                  0.3022\n" +
				"Relative absolute error                 89.6104 %\n" +
				"Root relative squared error             98.3332 %\n" +
				"Total Number of Instances               10     \n";
		assertEquals(ocekivani, Analiza.analiza('n'));
	}
	
	@Test
	public void test_Analiza_BayesNet_Nacin2() {
		GUI.odabraniAlgoritam = 1;
		File file1 = new File("R2_0test.arff");
		File file2 = new File("R2_1test.arff");
		obrada.obradi(file1, file2, 'm');
		String ocekivani =  "\n" +
				"Correctly Classified Instances           4              100      %\n" +
				"Incorrectly Classified Instances         0                0      %\n" +
				"Kappa statistic                          1     \n" + 
				"Mean absolute error                      0     \n" +
				"Root mean squared error                  0     \n" +
				"Relative absolute error                  0      %\n" +
				"Root relative squared error              0      %\n" +
				"Total Number of Instances                4     \n";
		assertEquals(ocekivani, Analiza.analiza('m'));
	}
	
	@Test
	public void test_Analiza_BayesNet_Nacin3() {
		GUI.odabraniAlgoritam = 0;
		File file = new File("R2_0test.arff");
		obrada.obradi(file, '3');
		String ocekivani =  "\n" +
				"aCorrectly Classified Instances           9               90      %\n" +
				"Incorrectly Classified Instances         1               10      %\n" +
				"Kappa statistic                          0.8   \n" + 
				"Mean absolute error                      0.1   \n" +
				"Root mean squared error                  0.3162\n" +
				"Relative absolute error                 20.0001 %\n" +
				"Root relative squared error             63.2456 %\n" +
				"Total Number of Instances               10     \n";
		assertEquals(ocekivani, Analiza.analiza('3'));
	}
}
