package abstracts;

import abstracts.dummy.DummySuffixTree;
/* Constructs Suffixtrees out of chapters 
 * Finds LCS using the suffixtree
 * */
import abstracts.dummy.DummyTextReader;

public class Run {
	public static void main(String[] args){
		DummySuffixTree dummySuffixTree = new DummySuffixTree(args[0]);
		DummyTextReader dummyTextReader = new DummyTextReader(args[1]);
		String chapterText = dummyTextReader.chapter(Integer.parseInt(args[2]));
		String lcs = dummySuffixTree.LCS(chapterText);
		System.out.println(lcs);		
	}
}

//
//need to do : 
//- Set up how to run from command line 
//- Write the bash scripts to set up jobs for cluster