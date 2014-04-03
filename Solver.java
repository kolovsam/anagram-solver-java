import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import java.io.*;

public class Solver {
	private ArrayList<Word> d;	// contains the dictionary as Word objects
	private ArrayList<Anagram> anagrams; // list of anagram classes
	private MergeSorter ms;
	private String inputFile;	// name of dictionary file, ex. "dict1" 
	private String outputFile;	// name of file to output to, ex. "anagram1"

	public Solver(String f, String output) {
		d = new ArrayList<Word>();
		anagrams = new ArrayList<Anagram>();
		ms = new MergeSorter();
		inputFile = f;
		outputFile = output;
	}

	/*
	 * Runs all of the Solver methods to solve the input file
	 * for its anagram classes.
	 */
	public void solve() {
		System.out.println("Solving " + inputFile + "...");
		readInDict();
		sortDict();
		makeAnagrams();
		outputToFile();
	}

	/*
	 * Reads in the input file word-by-word and adds it as a Word
	 * object to d.
	 * Run Time = n*llogl
	 */
	public void readInDict() {
		System.out.println("Reading in words...");
		try {
			Scanner scanner = new Scanner(new File(inputFile));
			String word;
			// while ther are more words in the file...
			while(scanner.hasNextLine()) {
				word = scanner.nextLine();
				// creating a Word also sorts it
				Word w = new Word(word);
				d.add(w);
			}
			scanner.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/*
	 * Runs mergeSort on the ArrayList of Word objects.
	 * Run Time = nlogn
	 */
	public void sortDict() {
		System.out.println("Sorting dict...");
		d = ms.mergeSort(d);
	}

	/*
	 * Extracts the anagram classes from the ArrayList of Word objects
	 * Run Time = n
	 */
	public void makeAnagrams() {
		System.out.println("Making anagrams...");
		// set first key
		String key = d.get(0).getSWord();
	
		// add first anagram
		anagrams.add(new Anagram(key));
	
		int ai = 0; // anagrams index counter
		int di = 0; // dictionary index counter
	
		// loop through d
		while(di < d.size()) {
			// current word has same key as current anagram
			if(d.get(di).getSWord().equals(key)) {
				// add Word to anagram class
				anagrams.get(ai).addWord(d.get(di));
				// move to next word in the dictionary
				di++;
			}
			else {
				// reached next anagram class in the ArrayList
				key = d.get(di).getSWord();
				// create new anagram class for the new key
				anagrams.add(new Anagram(key));
				// increment anagrams index
				ai++;
			}
		}
	}

	/*
	 * Produce output files containing the angram classes.
	 */
	public void outputToFile() {
		try {
			PrintWriter writer = new PrintWriter(outputFile);
			printAnagrams(writer);
			writer.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/*
	 * Print the "sorted word" of each word in ArrayList arr.
	 */
	public String printWords(ArrayList<Word> arr) {
		String s = "";
		for(int i = 0; i < arr.size(); i++)
			s += arr.get(i).getSWord() + ", ";
		return s;
	}

	/*
	 * Print the anagrams classes to a file.
	 */
	public void printAnagrams(PrintWriter w) {
		for(int i = 0; i < anagrams.size(); i++)
			w.println(anagrams.get(i).toString());
	}
}