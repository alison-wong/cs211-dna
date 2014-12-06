package abstracts;

/* Constructs Suffixtrees out of chapters 
 * Finds LCS using the suffixtree
 * */

import abstracts.dummy.DummySuffixTree;
import real.BookReader;

public class Run {
	public static void main(String[] args){
		DummySuffixTree dummySuffixTree = new DummySuffixTree(args[0]);
		BookReader bookReader = new BookReader("src/book" + args[1] + ".txt");
		
		String chapterText = bookReader.chapter(Integer.parseInt(args[2]));
		String lcs = dummySuffixTree.LCS(chapterText);
		System.out.println(lcs);
	}
}

//
//need to do : 
//- Set up how to run from command line 
//- Write the bash scripts to set up jobs for cluster

// Presentation: 
// Size of the book. How long is the book? Count of the nodes in the tree. 
// Interesting observation on the analysis 


// 	Time function: 

//		while(true){
//			System.out.println(System.currentTimeMillis());
//		}