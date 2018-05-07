package paket;

import java.io.File;

//import weka.*;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;;


public class Unos {
	
	static Instances data;

	
	
	public static Instances unosSeta(String path) {
		
		try {
			DataSource source = new DataSource(path);
			data = source.getDataSet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(data.classIndex() == -1) {
		data.setClassIndex(data.numAttributes() - 1);
		
		}
	
		//System.out.print(data);
		return data;
		
	}
	
	public static File unosFile(String path) {
		File file = null;
		try {
			file = new File(path);
			
		}
		catch (Exception e){
			System.out.println("file ne postoji");
		}
		
		
		return file;
	}
	
}