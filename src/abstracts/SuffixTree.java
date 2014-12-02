package abstracts;


public abstract class SuffixTree {
	public SuffixTree(String text) {
	}
	public abstract String LCS(String text);
	public abstract String LCS(SuffixTree suffixTree);
}
