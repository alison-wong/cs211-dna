

public abstract class SuffixTree {
	public abstract SuffixTree createTree(String text);
	public abstract String LCS(String text);
	public abstract String LCS(SuffixTree suffixTree);
}
