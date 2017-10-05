/*
 * Sample code for CS 2610 Homework 1
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Dictionary {
	public ArrayList<String> wtable;
	public ArrayList<Integer> ftable;
	public HashMap<String, Integer> loadDictionary;

	public Dictionary() throws FileNotFoundException {
		System.out.println("Loading dictionary...");
		
		wtable = new ArrayList<String>();
		ftable = new ArrayList<Integer>();
		//URL url = Dictionary.class.getResource("wordf.txt");
		InputStream is = getClass().getResourceAsStream("wordf.txt");
		//System.out.println("url = "+url.getPath());
		Scanner input = new Scanner(is);
		String buffer = new String("");
		loadDictionary = new HashMap<>();
		while(input.hasNext()){	
			buffer = input.nextLine();
			Scanner scan = new Scanner(buffer).useDelimiter(",");
			loadDictionary.put(scan.next(), scan.nextInt());
			//wtable.add(scan.next());
			//ftable.add(scan.nextInt());
		}
		
		input.close();
		System.out.println("Dictionary Loaded.");
	}
}
