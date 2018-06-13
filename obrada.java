package paket;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.google.common.collect.Sets;



public class obrada {

	private static String removeBugCount(String s) {
		s = s.substring(0, s.lastIndexOf(','));
		return s;
	}
	
	private static int findModul(String modul, HashSet<String> set) {
		int ret = 0;
		for(String s: set) {
			if(modul.compareTo(s) == 0) {
				ret = 1;
				break;
			}
		}
		return ret;
	}
	
	private static int findBugCount(String modul) {
		String bugs = modul.substring(modul.lastIndexOf(',') + 1);
		int cnt = -1;
		try {
			cnt = Integer.parseInt(bugs);
		}
		catch(NumberFormatException e) {
			System.out.println("Greska kod nalazenja bug counta u '" + bugs + "'");
		}
		return cnt;
	}
	
	private static HashSet<String> removeBuggy(HashSet<String> set) {
		List<String> toRemove = new ArrayList<String>();
		for(String s: set) {
			if(s.lastIndexOf(',') != -1 && !s.contains("@RELATION") && !s.contains("@ATTRIBUTE") && !s.contains("@DATA")) {
				/*String bugs = s.substring(s.lastIndexOf(',') + 1);
				int bugova = 0;
				try {
					bugova = Integer.parseInt(bugs);
				}
				catch(NumberFormatException e) {
					System.out.println("Greska kod nalazenja bug counta");
				}*/
				int bugova = findBugCount(s);
				if(bugova > 0) {
					toRemove.add(s);
				}
			}
		}
		for(String s: toRemove) {
			if(set.remove(s)) {
				System.out.println("Maknut " + s);
			}
		}
		return set;
		
	}
	
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
	
	
	public static String obradi(File file) {
		return obradi(file, file, '3');
	}
	
	public static String obradi(File file, char nacin) {
		return obradi(file, file, nacin);
	}
	
	public static String obradi(File file1, File file2) {
		return obradi(file1, file2, 'n');
	}
	
	@SuppressWarnings("deprecation")
	public static String obradi(File file1, File file2, char nacin) {
		
		HashSet<String> f1 = null;
		HashSet<String> f2 = null;
		try {
			
			f1 = new HashSet<String>(FileUtils.readLines(file1));
			f2 = new HashSet<String>(FileUtils.readLines(file2));
			
			if(nacin == 'm') {
				f1 = removeBuggy(f1);
				f2 = removeBuggy(f2);
			}
			
			int FP3 = 0, NFP3 = 0, nModula3 = 0;
			ArrayList<String> nacin3_rez = new ArrayList<String>();
			
			String report = "";
			
			if(nacin == '3') {
				for (String s: f1) {
					if(s.lastIndexOf(',') != -1 && !s.contains("@RELATION") && !s.contains("@ATTRIBUTE") && !s.contains("@DATA")) {
						int bugova = findBugCount(s);
						s = s.substring(s.indexOf(',') + 1);
						if(bugova == 0) {
							nacin3_rez.add(removeBugCount(s) + ",NFP");
							NFP3++;
						}
						else if(bugova > 0){
							nacin3_rez.add(removeBugCount(s) + ",FP");
							FP3++;
						}
						else {
						}
						nModula3++;
					}
				}
				
				report = "Broj modula u datasetu: " + nModula3 + "\n\n";
				report += "Broj FP kandidata: " + FP3 + "\n";
				report += "Broj NFP kandidata: " + NFP3 + "\n";
			}
			int aaaaa = 0;
			ArrayList<String> NFP_isti = new ArrayList<String>();
			if(nacin == 'm') {
				//način 2: dodaj sve koji su nepromijenjeni kao NFP
				for (String s: f1) {
					if(s.lastIndexOf(',') != -1 && !s.contains("@RELATION") && !s.contains("@ATTRIBUTE") && !s.contains("@DATA")) {
						System.out.println("Trazim " + s + " u f2");
						if(findBugCount(s) == 0 && findModul(s, f2) == 1) {
							//writer.println(s + ",NFP");
							NFP_isti.add(removeBugCount(s) + ",NFP");
							System.out.println("Dodan u izlaz: " + s + ",NFP");
						}
					}
				}
			}
			
			List<String> f1_s = FileUtils.readLines(file1);
			List<String> f2_s = FileUtils.readLines(file2);
			
			//f1.removeAll(f2);
			
			
			//f1.removeAll(f3);
			
			//f2.addAll(f1);
			//SetView<String> intersection = Sets.intersection(f1,f2);
			
			
			Set<String> datprva = new HashSet<String>();
			Set<String> datdruga = new HashSet<String>();
			
			List<String> moduli1 = new ArrayList<String>();
			List<String> moduli2 = new ArrayList<String>();
			
			PrintWriter writer = new PrintWriter("Usporedba.arff", "UTF-8");
			writer.println("@RELATION Usporedba");
			writer.println();
			
			int nKlasifikatora=1, nModula1=0, nModula2=0, nJednakih=0, nRazlicitih=0, nFP=0, nNFP=0;
			

			for(int i=0; i<f1_s.size(); i++) {
				String s = f1_s.get(i);
				boolean provjera = true;
				if(nacin == 'm' || nacin == 'n' || nacin == '3') {
					provjera = !s.contains("bug_cnt");
				}
				if(s.contains("@ATTRIBUTE") && provjera && !s.contains("module")) {
					writer.println(s);
					System.out.println("Dodan u izlaz: " + s);
					nKlasifikatora++;
				}
			}
			
			for (String s : f1) {
				if (!s.contains("FP") && !s.contains("@RELATION") && !s.contains("@ATTRIBUTE")&& !s.contains("@DATA") && !s.isEmpty()){
					String[] parts = s.split("\\,");
			    	moduli1.add(parts[0]);
					System.out.println("Dodan u moduli1: " + parts[0]);
					nModula1++;
				}
			}
			writer.println("@ATTRIBUTE class {FP,NFP}");
			writer.println();
			System.out.println();
			
			for (String s : f2) {
				if (!s.contains("FP") && !s.contains("@RELATION") && !s.contains("@ATTRIBUTE") && !s.contains("@DATA") && !s.isEmpty()){
					String[] parts = s.split("\\,");
			    	moduli2.add(parts[0]);
			    	System.out.println("Dodan u moduli2: " + parts[0]);
			    	nModula2++;
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
					nJednakih++;
				}
			}
			
			
			writer.println("@DATA");
			writer.println();
			
			if(nacin == '3') {
				for(String s: nacin3_rez) {
					writer.println(s);
				}
				writer.close();
			}
				
			if(nacin != '3') {
				List <String> tempmod2 = new ArrayList<String>();
				tempmod2.addAll(moduli2);
				List <String> tempmod1 = new ArrayList<String>();
				tempmod1.addAll(moduli1);
				tempmod2.removeAll(tempmod1);
				System.out.println("Trebao bi bit jedan element:" + tempmod2);
				
				if(nacin == 'm') {
					for(String s : NFP_isti) {
						//s = removeBugCount(s);
						s = s.substring(s.indexOf(',') + 1);
						System.out.println("pisem: " + s);
						writer.println(s);
					}
				}
				
				List<String> rez = new ArrayList<String>();
				//nalazi module koje su promjenjene u drugoj verziji dataseta
				//System.out.println("a ne znam vise boze pomozi");
				
				for (Iterator<String> iterator = f3.iterator(); iterator.hasNext();) {
				    String s =  iterator.next();
				    if(!s.contains("@RELATION") && !s.contains("@ATTRIBUTE") && !s.contains(tempmod2.get(0))&& !s.contains(tempmod2.get(1))) {
				    	if(nacin == 'm') {
				    		int breakflag=1;
				    		for(String s2 : f1) {
				    			if(s2.contains(s.substring(0, s.indexOf(',')))) {
				    				breakflag = 0;
				    				System.out.println("Nađen " + s.substring(0, s.indexOf(',')) + " u f1");
				    				break;
				    			}
				    		}
				    		if(breakflag == 1) continue;
				    	}
				    	String write;
				    	if(nacin == 'm' || nacin == 'n' || nacin == '3') {
				    		System.out.print("Index1: " + s.lastIndexOf(',') + " ");
				    		if(s.lastIndexOf(',') != -1) {
				    			s = s.substring(0, s.lastIndexOf(','));
				    		}
				    	}
				    	if (nacin != 'm' && !s.contains("FP")) {
				    		iterator.remove();
				    		System.out.print("Index2: " + (s.indexOf(',') + 1) + " ");
				    		write = s.substring(s.indexOf(',') + 1)+",FP";
				    		System.out.println("Dodajem u rez: " + write);
					    	//rez.add(write);
					    	writer.println(write);
					    	nRazlicitih++;
					    	nFP++;
					    	
				    	}
				    	/*
				    	else {
				    		write = s.substring(s.indexOf(',') + 1);
				    		rez.add(write);
					    	writer.println(write);
				    	}*/
				    }
				}
				if(nacin == 'm') {
					for(String s1 : f1) {
						if(s1.contains("@ATTRIBUTE") || s1.contains("@RELATION") || s1.contains("@DATA") || s1.isEmpty()) continue;
						for(String s2 : f2) {
							if(s2.contains("@ATTRIBUTE") || s2.contains("@RELATION") || s2.contains("@DATA") || s2.isEmpty()) continue;
		    				if((s1.substring(0, s1.indexOf(','))).equals((s2.substring(0, s2.indexOf(','))))) {
		    					if(!s1.equals(s2)) {
		    						nFP++;
				    				//System.out.print(" indexznj: " + s1.indexOf(',') + " ");
		    						s1 = s1.substring(s1.indexOf(',') + 1);
		    						//System.out.println(s1);
		    						String write = removeBugCount(s1) + ",FP";
		    						writer.println(write);
							    	nRazlicitih++;
		    					}
		    				}
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
				
				//nRazlicitih = moduli1.size();
				moduli1.removeAll(moduli2);
				//nJednakih = nRazlicitih - moduli1.size();
				//nRazlicitih = moduli1.size();
				
				System.out.println("moduli1 nakon brisanja duplikata (trebalo bi ih biti dva):");
				System.out.println(moduli1);
				
				for (String s: f1){
					
					for(int i = 0;i < moduli1.size();i++){
						if(s.contains(moduli1.get(i)) && !s.contains("@ATTRIBUTE")){
							if(nacin == 'n' || nacin == '3') {
								if(s.lastIndexOf(',') != -1) {
									s = s.substring(0, s.lastIndexOf(','));
								}
					    		String write = s.substring(s.indexOf(',') + 1) + ",FP";
					    		//rez.add(write);
					    		writer.println(write);
					    		nFP++;
					    	}
							
						}
					}
				}
				
				System.out.println("Datoteke koje nisu promjenjene");
				System.out.println(jednaki_mod);
				
				if(nacin != 'm') {
					for(int i = 0; i < jednaki_mod.size();i++){
						if(nacin == 'm' || nacin == 'n' || nacin == '3') {
							jednaki_mod.set(i, jednaki_mod.get(i).substring(0, jednaki_mod.get(i).lastIndexOf(',')));
			    		}
						String write = jednaki_mod.get(i).substring(jednaki_mod.get(i).indexOf(',') + 1)+",NFP";
						//rez.add(write);
			    		writer.println(write);
			    		nNFP++;
					}
				}
				
				writer.close();
				
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
				
				//for (String eachString : rez)
				//{
				    //System.out.println(eachString);
				//}
				
				report = "Broj modula u prvom datasetu: " + nModula1 + "\n";
				report += "Broj modula u drugom datasetu: " + nModula2 + "\n";
				report += "Broj promjenjenih modula: " + nRazlicitih + "\n";
				report += "Broj nepromijenjenih modula: " + jednaki_mod.size() + "\n";
				report += "Broj modula koji su maknuti u novoj verziji: " + moduli1.size() + "\n\n";
				int FP_cand = nRazlicitih + moduli1.size();
				report += "Broj FP kandidata(promjenjeni + maknuti): " + nFP + "\n";
				report += "Broj NFP kandidata(nepromjenjeni moduli): " + jednaki_mod.size() + "\n";
			}

				
			
			Analiza.analiza(nacin);
			return report;
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		
		return null;
	}

}
