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
			
			
			
			//f1.removeAll(f2);
			
			
			//f1.removeAll(f3);
			
			//f2.addAll(f1);
			//SetView<String> intersection = Sets.intersection(f1,f2);
			
			
			Set<String> datprva = new HashSet<String>();
			Set<String> datdruga = new HashSet<String>();
			
			List<String> moduli1 = new ArrayList<String>();
			List<String> moduli2 = new ArrayList<String>();
			
			for (String s : f1) {
				if (!s.contains("FP") && !s.contains("@RELATION") && !s.contains("@ATTRIBUTE") && !s.contains("@DATA") && !s.isEmpty()){
					String[] parts = s.split("\\,");
			    	moduli1.add(parts[0]);
					System.out.println("Dodan u moduli1: " + parts[0]);
				}
			}
			System.out.println();
			
			for (String s : f2) {
				if (!s.contains("FP") && !s.contains("@RELATION") && !s.contains("@ATTRIBUTE") && !s.contains("@DATA") && !s.isEmpty()){
					String[] parts = s.split("\\,");
			    	moduli2.add(parts[0]);
			    	System.out.println("Dodan u moduli2: " + parts[0]);
				}
			}
			
			HashSet<String> f3 = f2;
			@SuppressWarnings("deprecation")
			HashSet<String> f4 = new HashSet<String>(FileUtils.readLines(file2));
			
			
			
			f3.removeAll(f1);
			f4.retainAll(f1);
			
			List<String> jednaki_mod = new ArrayList<String>();
			
			for(String s: f4) {
				
				if(s.contains("java")){
					jednaki_mod.add(s);
				}
			}
			
			
			Set<String> rez = new HashSet<String>();
			//nalazi module koje su promjenjene u drugoj verziji dataseta
			for (Iterator<String> iterator = f3.iterator(); iterator.hasNext();) {
			    String s =  iterator.next();
			    if(!s.contains("@RELATION") && !s.contains("@ATTRIBUTE")) {
			    	if (!s.contains("FP")) {
			    		iterator.remove();
				    	rez.add(s+",FP");
			    	}
			    	else {
			    		rez.add(s);
			    	}
			    }
			}
			
			//nalazi module koji nema vise u drugoj verziji
			
			/*for(int i = 0; i < moduli1.size();i++){
				
				for(int j = 0; j < moduli2.size();j++){
					System.out.println("usporedujem " + moduli1.get(i) + " sa " + moduli2.get(j));
					if(moduli1.get(i).equals(moduli2.get(j))){
						System.out.println("isti su, brisem");
						//moduli1.remove(i);
						break;
						
					}
					
				}
				
			}
			*/
			List<String> moduli3 = new ArrayList<String>();
			for(String s : moduli1) {
				if(!moduli2.contains(s)) {
					moduli3.add(s);
				}
			}
			
			
			System.out.println();
			System.out.println("elementi koji se ne podudaraju:");
			System.out.println(moduli3);
			
			moduli1.removeAll(moduli2);
			
			System.out.println();
			System.out.println("moduli1 nakon brisanja duplikata (trebalo bi ih biti dva):");
			System.out.println(moduli1);
			
			for (String s: f1){
				
				for(int i = 0;i < moduli1.size();i++){
					if(s.contains(moduli1.get(i)) && !s.contains("@ATTRIBUTE")){
						rez.add(s+",REMOVED");
					}
				}
			}
			
			System.out.println("Datoteke koje nisu promjenjene");
			System.out.println(jednaki_mod);
			
			
			for(int i = 0; i < jednaki_mod.size();i++){
				rez.add(jednaki_mod.get(i)+", NFP");
			}
			
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
			//System.out.println(moduli1);
			//System.out.println("novi moduli");
			//System.out.println(moduli2);
			
			System.out.println();
			System.out.println("moduli koji su promijenjeni u drugoj verziji:");
			
			for (String eachString : rez)
			{
			    System.out.println(eachString);
			}
			
			return f2;
		}
		catch (Exception e) {
			System.out.println("a ne znam");
		}
		
		
		
		return null;
	}
	
	
}