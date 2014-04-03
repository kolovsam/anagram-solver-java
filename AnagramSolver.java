public class AnagramSolver {
	public static void main(String[] args) {
		Solver dict1 = new Solver( "dict1", "anagram1");
		Solver dict2 = new Solver("dict2", "anagram2");

		dict1.solve();
		dict2.solve();
	}
}