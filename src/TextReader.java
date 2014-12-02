
public abstract class TextReader {
	String text;	// contains the whole text 
	public TextReader(String filename) {
	}
	public abstract String chapter(int chapter);
	public abstract String entireBook();
}
