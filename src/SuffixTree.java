

public interface SuffixTree {
	public SuffixTree createTree(String text);
	public String LCS(String text);
	public String LCS(SuffixTree suffixTree);
}
