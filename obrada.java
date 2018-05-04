package paket;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;

public class obrada {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static Set<String> obradi(String data1, String data2) {
		
		//String s1 = "Hello, my name is John.";
		//String s2 = "Can you tell me your name?";
		/*Splitter splitter = Splitter.onPattern("\\W").trimResults().omitEmptyStrings();
		Set<String> intersection = Sets.intersection(//
		        Sets.newHashSet(splitter.split(data1)), //
		        Sets.newHashSet(splitter.split(data2)));
		return intersection;
		*/
		
		String[] a = data1.split(";"); 
		Set<String> hashSet1 = new HashSet<String>(Arrays.asList(a));
		
		String[] b = data1.split(";"); 
		Set<String> hashSet2 = new HashSet<String>(Arrays.asList(b));
		
		
		/*Splitter splitter = Splitter.onPattern("\\W").trimResults().omitEmptyStrings();*/
		Set<String> intersection = Sets.intersection(hashSet1,hashSet2);
		
		//Set<String> nosection = Sets.difference(hashSet1,intersection);
		
		//hashSet1.removeAll(hashSet2);
		return intersection;
	
		
		
		
		
	}
}
