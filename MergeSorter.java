import java.util.ArrayList;

public class MergeSorter {
	/*
	 * Performs merge sort on string s.
	 * Returns string s sorted in alphabetical order
	 * Run Time = nlogn
	 */
	public String stringMergeSort(String s) {
		if(s.length() <= 1)
			return s;

		int mi = s.length() / 2;
		String left = "";
		String right = "";
		char[] w = s.toCharArray();
		for (int i = 0; i < w.length; i++) {
			if(i < mi)
				left += w[i];
			else
				right += w[i];
		}

		return stringMerge(stringMergeSort(left), stringMergeSort(right));
	}

	/*
	 * Merges strings 'left' and 'right'.
	 */
	public String stringMerge(String left, String right) {
		String sorted = ""; // new string
		int l = 0; 	// index for left string
		int r = 0;	// index for right string
		char[] lhalf = left.toCharArray();
		char[] rhalf = right.toCharArray();
		
		// compare letters in each string individually
		while (l < lhalf.length && r < rhalf.length) {
			if(lhalf[l] <= rhalf[r]) {
				sorted += lhalf[l];
				l++;
			}
			else {
				sorted += rhalf[r];
				r++;
			}
		}
	
		// still letters in left
		if(l < lhalf.length) {
			for(int i = l; i < lhalf.length; i++)
				sorted += lhalf[i];
		}
		// still letters in right
		else {
			for(int j = r; j < rhalf.length; j++)
				sorted += rhalf[j];
		}

		return sorted;
	}

	/*
	 * Performs Merge Sort an ArrayList of Word objects.
	 * Returns words sorted by Word.sWord.
	 * Run Time: nlogn
	 */
	public ArrayList<Word> mergeSort(ArrayList<Word> words) {
		if (words.size() < 2)	// base case
			return words;
	
		int mid = words.size()/2;	// set middle index

		/*** DIVIDE ***/
		ArrayList<Word> left = new ArrayList<Word>();
		ArrayList<Word> right = new ArrayList<Word>();
		
		// initialize left half
		for(int i = 0; i < mid; i++)
			left.add(words.get(i));
		// initialize right half
		for(int j = mid; j < words.size(); j++)
			right.add(words.get(j));

		/*** CONQUER ***/
		return merge(mergeSort(left), mergeSort(right));
	}

	/*
	 * Merges ArrayLists 'left' and 'right'.
	 */
	public ArrayList<Word> merge(ArrayList<Word> left, ArrayList<Word> right) {
		// new ArrayList to hold sorted array
		ArrayList<Word> sorted = new ArrayList<Word>();
		int l = 0;		// index for left array
		int r = 0;		// index for right array

		// compare Words in each array individually
		while(l < left.size() && r < right.size()) {
			//current words in each arraylist
			String lword = left.get(l).getSWord();
			String rword = right.get(r).getSWord();
			
			// word in left goes before word in right
			if(lword.compareTo(rword) <= 0) {
				sorted.add(left.get(l));
				// increment left index
				l++;	
			}
			// word in right goes before word in left
			else {
				sorted.add(right.get(r));
				// increment right index
				r++;	 
			}
		}
	
		// still Words in left
		if(l < left.size()) {
			for(int i = l; i < left.size(); i++)
				sorted.add(left.get(i));
		}
		// still Words in right
		else if(r < right.size()) {
			for(int j = r; j < right.size(); j++)
				sorted.add(right.get(j));
		}

		return sorted;
	}
}