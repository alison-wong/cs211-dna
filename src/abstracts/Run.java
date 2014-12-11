package abstracts;

/* Constructs Suffixtrees out of chapters 
 * Finds LCS using the suffixtree
 * */

import abstracts.dummy.DummySuffixTree;
import real.BookReader;
import real.QuadSuffix;

public class Run {
	public static void main(String[] args){		
//		testEasy();	
		BookReader bookReader1 = new BookReader(args[0]);
		
//		String s1 = bookReader1.chapter(Integer.parseInt(args[2]));
		String s1= bookReader1.chapter(4);
		System.out.println(s1);
//		System.out.println("Building suffix tree for chapter "+s1);
//				
//		int time1= (int) System.currentTimeMillis();
//				
//		QuadSuffix quadSuffix = new QuadSuffix(s1);
//		int time2= (int) System.currentTimeMillis();
//		
//		System.out.println("It took "+(time1-time2));
		
	}
	static void testEasy(){
		String s1="O LORD, bless this Thy hand grenade that with it Thou mayest blow Thine enemies to tiny bits, in Thy mercy.";
		System.out.println("Creating Suffix Tree for string : "+s1);
		int time1= (int) System.currentTimeMillis();
		QuadSuffix quadSuffix = new QuadSuffix(s1);
		int time2= (int) System.currentTimeMillis();
		System.out.println("Time it took :"+(time2-time1));
		String s2="Thy hand grenade";
		System.out.println("Querying : "+s2);		
		System.out.println(quadSuffix.isSubstring(s2));
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