package paket;

//import weka.*;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;;


public class Unos {
	
	static Instances data;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		

}
	
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
	
		System.out.print(data);
		return data;
		
	}
	
}
