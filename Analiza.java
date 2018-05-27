package paket;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream.Filter;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class Analiza {

	public static void analiza(char nacin){
		/*
		Instances train = ;         // from somewhere
		Instances test = ;       // from somewhere
		
		//build model
	    NaiveBayes model=new NaiveBayes();
	    model.buildClassifier(train);
	    //use
	    Evaluation eval_train = new Evaluation(test);
	    eval_train.evaluateModel(model,test);
		
		*/
		
		//filter
        StringToWordVector Filter = new StringToWordVector();
        Classifier algoritam;
        algoritam = new NaiveBayes();
        if(GUI.odabraniAlgoritam == 0) {
        	algoritam = new NaiveBayes();
        	System.out.println("\nodabran NaiveBayes\n");
        }
        else if(GUI.odabraniAlgoritam == 1) {
        	algoritam = new BayesNet();
        	System.out.println("\nodabran BayesNet\n");
        }

        //training data
        Instances train = null;
		try {
			train = new Instances(new BufferedReader(new FileReader("Usporedba.arff")));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        int lastIndex = train.numAttributes() - 1;
        train.setClassIndex(lastIndex);
        try {
			Filter.setInputFormat(train);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			train = weka.filters.Filter.useFilter(train, Filter);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        //testing data
        Instances test = null;
		try {
			String src;
			if(nacin == 'n') src = "testni1.arff";
			else if(nacin == 'm') src = "testni2.arff";
			else src = "testni1.arff";
				
			test = new Instances(new BufferedReader(new FileReader(src)));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        test.setClassIndex(lastIndex);
        try {
			Filter.setInputFormat(test);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        Instances test2 = null;
		try {
			test2 = weka.filters.Filter.useFilter(test, Filter);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        
        double index = 0;
        try {
			algoritam.buildClassifier(train);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        for(int i=0; i<test2.numInstances(); i++) {
            //System.out.println(test.instance(i));
            
			try {
				
				index = algoritam.classifyInstance(test2.instance(i));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String className;
			if(nacin == 'n') className = train.attribute(49).value((int)index);
			else className = train.attribute(48).value((int)index);
          System.out.println(className);
            
        }
        
        //System.out.println("Pls dodi tu");
        
    }
		
		
}
