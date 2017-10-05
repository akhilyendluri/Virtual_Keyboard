import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.sun.org.apache.xml.internal.security.keys.content.KeyValue;

import java.util.Map.Entry;

public class Levenshtein_Distance {
	
	Dictionary d;
	
	public ArrayList<String> findMatch(String word, String prunedWord){
		ArrayList<String> matches = null;
		matches = calculateDistance(findWords(word),word);
		return matches;
	}
	
	private HashMap<String,Integer> findWords(String word){
		//System.out.println("findwordsv  ==== "+word);
		HashMap<String, Integer> matches = new HashMap<>();
		try {
			d = new Dictionary();
			for(Map.Entry<String, Integer> entry : d.loadDictionary.entrySet()) {
				if(entry.getKey().charAt(0) == word.charAt(0) && entry.getKey().charAt(entry.getKey().length()-1) == word.charAt(word.length()-1)) {
					//System.out.println("words = "+entry.getKey()+" \t "+entry.getValue());
					if(isSubSequence(entry.getKey(), word,  entry.getKey().length(), word.length())) {
						matches.put(entry.getKey(),entry.getValue());					
					} else {
						//System.out.println("not a subsequence");
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			System.out.println("File Not Found");
		}		
		return matches;
	}
	
	private ArrayList<String> calculateDistance( HashMap<String, Integer> words, String userWord ) {
		//System.out.println("In Calculate Distance");
		ArrayList<String> list = new ArrayList<>();
		HashMap<String,Integer> sortWords = new HashMap<>();
		int count = 0;
		Set<String> keys = words.keySet();
		for(String frequency : keys) {			
			int v = levenshteinDistance(frequency, userWord);
			if(v!=-1) {
				sortWords.put(frequency,levenshteinDistance(frequency, userWord));
			}
			//System.out.println("word = "+words.get(frequency)+" Count = "+levenshteinDistance(frequency, userWord));
		}		
		
		Map<String, Integer> sortedWords = sortValue(sortWords);
		list = sortFrequency(sortedWords);
		return list;
	}
	
	private static <String, Integer>  List<String> createListFromMapEntries (Map<String, Integer> map){
        return map.keySet().stream().collect(Collectors.toList());
    }
	
	private ArrayList<String> sortFrequency(Map<String, Integer>map){
		Map<String,Integer> map2 = map;
		ArrayList<String> a = new ArrayList<>();
		List<String> keyVal = createListFromMapEntries(map);
		for(int k = 0;k<keyVal.size();k++) {
			//System.out.println("key = "+keyVal.get(k));
			String addWord;
			int change = 0;
			do {
				change = 0;
				addWord = keyVal.get(k);
				for(int h = k+1;h<keyVal.size();h++) {
					if(map.get(keyVal.get(k)) == map.get(keyVal.get(h))) {
						if(d.loadDictionary.get(keyVal.get(k)) < d.loadDictionary.get(keyVal.get(h))) {
							addWord = keyVal.get(h);
							change = 1;
						}
					} else {
						break;
					}
				}
				//System.out.println(addWord);
				a.add(addWord);
				if(addWord == keyVal.get(k)) {
					k--;
				}
				keyVal.remove(addWord);				
			}while(change == 1);			
		}
		return a;
	}
	
	private Map<String, Integer> sortValue(HashMap<String, Integer>map){
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
        	//System.out.println("sortedMap Word = "+entry.getKey()+" distance = "+entry.getValue());
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
	}
	
	/*
	 * The below method was referenced from 
	 * http://www.sanfoundry.com/java-program-implement-levenshtein-distance-computing-algorithm/
	 * 
	 */
	
	private int levenshteinDistance(String a, String b) {
		a = a.toLowerCase();
		b = b.toLowerCase();
		int[] costs = new int[b.length() + 1];
		for (int j = 0; j < costs.length; j++)
			costs[j] = j;
		for (int i = 1; i <= a.length(); i++)
		{
			costs[0] = i;
			int nw = i - 1;
			for (int j = 1; j <= b.length(); j++)
			{
				int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]),
						a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
				nw = costs[j];
				costs[j] = cj;
			}
		}
		return costs[b.length()];
	}
	
	/*
	 * The below method was referenced from
	 * http://www.geeksforgeeks.org/given-two-strings-find-first-string-subsequence-second/
	 * 
	 */
	
	boolean isSubSequence(String str1, String str2, int m, int n)
    {
        int j = 0;
         
        // Traverse str2 and str1, and compare current character 
        // of str2 with first unmatched char of str1, if matched 
        // then move ahead in str1
        for (int i=0; i<n&&j<m; i++)
            if (str1.charAt(j) == str2.charAt(i))
                j++;
 
        // If all characters of str1 were found in str2
        return (j==m); 
    }

}
