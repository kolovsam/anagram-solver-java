public class Word {
	private String word;	// dictionary word
	private String sWord; 	// sorted word is the anagram key
	private MergeSorter ms;

	public Word(String w) {
		word = w;
		ms = new MergeSorter();
		sWord = ms.stringMergeSort(word);
	}

	public String getSWord() { return sWord; }
	public String getWord() { return word; }
}
