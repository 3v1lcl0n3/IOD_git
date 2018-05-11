package paket;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
			
//nalazi module koji nema vise u drugoj verziji
			
			Set<String> datprva = new HashSet<String>();
			Set<String> datdruga = new HashSet<String>();
			
			List<String> moduli1 = new ArrayList<String>();
			List<String> moduli2 = new ArrayList<String>();
			
			for (String s : f1) {
				if (!s.contains("FP") && !s.contains("@RELATION") && !s.contains("@ATTRIBUTE") && !s.contains("@DATA") ){
					String[] parts = s.split("\\,");
			    	moduli1.add(parts[0]);
					
				}
			}
			
			for (String s : f2) {
				if (!s.contains("FP") && !s.contains("@RELATION") && !s.contains("@ATTRIBUTE") && !s.contains("@DATA") ){
					String[] parts = s.split("\\,");
			    	moduli2.add(parts[0]);
					
				}
			}
			
			
			Set<String> rez = new HashSet<String>();
			//nalazi module koje su promjenjene u drugoj verziji dataseta
			for (Iterator<String> iterator = f2.iterator(); iterator.hasNext();) {
			    String s =  iterator.next();
			    if (!s.contains("FP") && !s.contains("@RELATION") && !s.contains("@ATTRIBUTE") ){
			    	iterator.remove();
			    	rez.add(s+",FP");
			    }   
			    
			    int i = 0;
			    if(!s.contains(moduli1.get(i))){
			    	rez.add(s);
			    }
			    
			}
			
			for(int i = 0; i < moduli1.size();i++){
				
				for(int j = 0; j < moduli2.size();j++){
					//System.out.println("usporedujem" + moduli1.get(i) + "sa" + moduli2.get(i));
					if(moduli1.get(i) == moduli2.get(j)){
						moduli1.remove(i);
					}
					
				}
				
			}
			
			System.out.println("trebalo bi samo");
			System.out.println(moduli1);
			System.out.println("jedan element");
			
			/*
			for (Iterator<String> iterator = f1.iterator(); iterator.hasNext();) {
			    String s =  iterator.next();
			    if (!s.contains("FP") && !s.contains("@RELATION") && !s.contains("@ATTRIBUTE") && !s.contains("@DATA") ){
			    	String temp = iterator.toString();
			    	iterator.remove();
			    	String[] parts = temp.split("\\,");
			    	moduli1.add(parts[0]);
			    	
			    }   
			    
			}
			*/
			/*for (Iterator<String> iterator = f2.iterator(); iterator.hasNext();) {
			    String s =  iterator.next();
			    if (!s.contains("FP") && !s.contains("@RELATION") && !s.contains("@ATTRIBUTE") && !s.contains("@DATA") ){
			    	String temp = iterator.toString();
			    	iterator.remove();
			    	String[] parts = temp.split("\\,");
			    	moduli2.add(parts[0]);
			    	
			    }   
			    
			}
			*/
			System.out.println(moduli1);
			System.out.println("novi moduli");
			System.out.println(moduli2);
			
			
			
			
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