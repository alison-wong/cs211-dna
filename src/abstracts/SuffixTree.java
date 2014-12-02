package abstracts;


public abstract class SuffixTree {
	public SuffixTree(String text) {
	}
	public abstract String LCS(String text);
	public abstract String LCS(SuffixTree suffixTree);
	public abstract int size();		// returns the total number of nodes in the tree 
}

// Increase heap memory : java -Xmx1200 Suffix
