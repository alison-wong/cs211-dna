package abstracts;

import abstracts.dummy.DummySuffixTree;
/* Constructs Suffixtrees out of chapters 
 * Finds LCS using the suffixtree
 * */

public class Run {
	public static void main(String[] args){
		DummySuffixTree dummySuffixTree = new DummySuffixTree("book1.txt");
		System.out.println("hey "+ args[0]+" "+args[1]);		
	}
}
