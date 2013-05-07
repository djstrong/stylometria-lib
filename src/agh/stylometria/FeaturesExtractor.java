package agh.stylometria;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import agh.stylometria.features.DlugoscSlow;
import agh.stylometria.features.Emotikony;
import agh.stylometria.features.Feature;
import agh.stylometria.features.FormyGrzecznosciowe;
import agh.stylometria.features.FunctionWords;
import agh.stylometria.features.IloscSlowZdan;
import agh.stylometria.features.Plec;
import agh.stylometria.features.Pokemony;
import agh.stylometria.features.Wulgaryzmy;
import agh.stylometria.features.ZnakiDiakrytyczne;
import agh.stylometria.features.ZnakiInterpunkcyjne;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class FeaturesExtractor {
	private static List<Feature> listOfFeatures = new ArrayList<Feature>();
	static {
		listOfFeatures.add(new DlugoscSlow());
		listOfFeatures.add(new Emotikony());
		listOfFeatures.add(new FormyGrzecznosciowe());
		listOfFeatures.add(new Plec());
		listOfFeatures.add(new Pokemony());
		listOfFeatures.add(new Wulgaryzmy());
		listOfFeatures.add(new ZnakiDiakrytyczne());
		listOfFeatures.add(new ZnakiInterpunkcyjne());
		listOfFeatures.add(new IloscSlowZdan());
		listOfFeatures.add(new FunctionWords());
	}

	public static Map<String, Double> features(String text) {
		Map<String, Double> features = new HashMap<String, Double>();

		Text t = new Text(text);
		//System.out.println(t);
		for (Feature f : listOfFeatures) {
			features.putAll(f.process(t));
		}

		// TODO: trzeba jeszcze cechy podzielic przez ilosc slow lub zdan

		// ilość poszczególnych znaków (raczej specjalnych)

		return features;
	}

	public static void main(String[] args) throws Exception {
		CSVReader reader = new CSVReader(new FileReader("test_comments.csv"));
	    String []nextLine;
	    List<String> header = new LinkedList();
	    CSVWriter writer = new CSVWriter(new FileWriter("test.csv"), ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.NO_ESCAPE_CHARACTER);
	    //ICsvMapWriter writer = new CsvMapWriter(new FileWriter("test.csv"),CsvPreference.STANDARD_PREFERENCE);
	    List<Map> featuresMaps  = new ArrayList();
	    List authors = new ArrayList();
	    while ((nextLine = reader.readNext()) != null) {
	    	Map f = features(nextLine[1].toString());
	    	authors.add(nextLine[0].toString());
	    	featuresMaps.add(f);
	    }
	    
	    //header
	    int max = 0;
	    Map headerMap = null;
	    for(Map m : featuresMaps){
	    	if(m.keySet().size()>max){
	    		max = m.keySet().size();
	    		headerMap = m;
	    	}
	    }
	    
    	for (Object k : headerMap.keySet()) {
		    		header.add(k.toString());
		}
    	header.add("author");
	    writer.writeNext(header.toArray(new String[0]));

	    for(int i=0;i<featuresMaps.size();i++){
	    	Map<String,Double> m = featuresMaps.get(i);
	    	List<String> entries = new LinkedList();
	    	
	    	for(String key : header){
	    		Object val = m.get(key)==null ? 0.0 : m.get(key);
	    		entries.add(val.toString());
	    	}
	    	entries.add(authors.get(i).toString());
	        writer.writeNext(entries.toArray(new String[0]));
	        
	    }
	    writer.close();
	    
		
	}
}
