package abstracts.dummy;

import abstracts.TextReader;

public class DummyTextReader extends TextReader {

	public DummyTextReader(String filename) {
		super(filename);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String chapter(int chapter) {
		// TODO Auto-generated method stub
		return "abcdefg";
	}

	@Override
	public String entireBook() {
		// TODO Auto-generated method stub
		return "abcdefghijkl";
	}

}
