package abstracts;

/* Constructs Suffixtrees out of chapters 
 * Finds LCS using the suffixtree
 * */

import abstracts.dummy.DummySuffixTree;
import real.BookReader;
import real.QuadSuffix;

public class Run {
	public static void main(String[] args){
		
		
		BookReader bookReader1 = new BookReader("src/book"+ args[0]+".txt");
		String s1 = bookReader1.chapter(1);
		
		
		
		System.out.println();
		QuadSuffix quadSuffix = new QuadSuffix("this is great");
		
//		BookReader bookReader = new BookReader("src/book" + args[1] + ".txt");		
//		String chapterText = bookReader.chapter(Integer.parseInt(args[2]));
		
		System.out.println("test");
		
		String testString ="Pendragon made war";
//		System.out.println(quadSuffix.isSubstring("this is "));
		System.out.println(quadSuffix.LCS("thissss"));
		
//		System.out.println(chapterText);
//		String lcs = dummySuffixTree.LCS(chapterText);
//		System.out.println(lcs);
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