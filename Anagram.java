import java.util.ArrayList;

public class Anagram {
	// string of letters in alphabetical order, serves as the unique key
	// for each anagram class
	private String key;
	// the words in the anagram class
	private ArrayList<Word> words;

	public Anagram(String k) {
		key = k;
		words = new ArrayList<Word>();
	}

	public void addWord(Word w) {
		words.add(w);
	}

	/*
	 * Prints words in a comma seperated list.
	 */
	public String toString() {
		String wordString = words.get(0).getWord();
		for(int i = 1; i < words.size(); i++)
			wordString += ", " + words.get(i).getWord();

		return wordString;
	}
}