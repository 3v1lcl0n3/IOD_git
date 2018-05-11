package paket;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.google.common.collect.Sets;



public class obrada {

	
	
	public static Set<String> obradi(String data1, String data2) {
		
		/*
		String s1 = "Can Hello, my name is John.";
		String s2 = "Can you tell me your name?";
		
		Splitter splitter = Splitter.onPattern("\\W").trimResults().omitEmptyStrings();
		Set<String> intersection = Sets.intersection(//
		        Sets.newHashSet(splitter.split(data1)), //
		        Sets.newHashSet(splitter.split(data2)));
		return intersection;
		*/
		
		
		String[] a = data1.split(" "); 
		Set<String> hashSet1 = new HashSet<String>(Arrays.asList(a));
		
		String[] b = data2.split(" "); 
		Set<String> hashSet2 = new HashSet<String>(Arrays.asList(b));
		
		String[] c = "jedan dva tri cetiri".split(" ");
		Set<String> jedan = new HashSet<String>(Arrays.asList(c));
		
		String[] d = "jedan dva tri pet".split(" ");
		Set<String> dva = new HashSet<String>(Arrays.asList(d));
		
		//Splitter splitter = Splitter.onPattern("\\W").trimResults().omitEmptyStrings();
		//Set<String> intersection = Sets.intersection(hashSet1,hashSet2);
		
		Set<String> intersection = Sets.intersection(jedan, dva); //intersection / hashSet2
		Set<String> difference = Sets.difference(hashSet1, intersection);
		

		
		//hashSet2.removeAll(hashSet1);
		//jedan.removeAll(dva);
		//hashSet1.removeAll(hashSet2);
		return hashSet1;
		
	}
	
	
	public static Set<String> obradi(File file1, File file2) {
		
		try {
			
			@SuppressWarnings("deprecation")
			HashSet<String> f1 = new HashSet<String>(FileUtils.readLines(file1));
			
			@SuppressWarnings("deprecation")
			HashSet<String> f2 = new HashSet<String>(FileUtils.readLines(file2));
			
			HashSet<String> f3 = f2;
			
			f2.removeAll(f1);
			
			//f1.removeAll(f2);
			
			
			//f1.removeAll(f3);
			
			//f2.addAll(f1);
			//SetView<String> intersection = Sets.intersection(f1,f2);
			
			Set<String> rez = new HashSet<String>();
			
			for (Iterator<String> iterator = f2.iterator(); iterator.hasNext();) {
			    String s =  iterator.next();
			    if (!s.contains("FP")){
			    	iterator.remove();
			    	rez.add(s+",FP");
			    }   
			}
			
			for (String eachString : rez)
			{
			    System.out.println(eachString);
			    System.out.println("\n");
			}
			
			return f2;
		}
		catch (Exception e) {
			System.out.println("a ne znam");
		}
		
		
		
		return null;
	}
	
	
}