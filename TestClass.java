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
	
}
