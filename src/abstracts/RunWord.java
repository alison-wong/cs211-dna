package abstracts;

/* Constructs Suffixtrees out of chapters 
 * Finds LCS using the suffixtree
 * */

import real.BookReaderWord;
import real.QuadSuffixWord;

public class RunWord {
	public static void main(String[] args)
	{
		int chapter1 = 12;
		int chapter2 = 9;
		BookReaderWord brw = new BookReaderWord("src/book1.txt");
		long time1 = System.currentTimeMillis();
		QuadSuffixWord qsw = new QuadSuffixWord(brw.chapterList(chapter1));
		long time2 = System.currentTimeMillis();
		System.out.println("It took " + (time2-time1)/1000.0 + " seconds.");
		
		for (String s : brw.chapterList(chapter1))
			System.out.print(s + " ");
		System.out.println("\n");
		
		for (String s : brw.chapterList(chapter2))
			System.out.print(s + " ");
		System.out.println("\n");
		
		time1 = System.currentTimeMillis();
		System.out.println(qsw.LCS(brw.chapterList(chapter2)));
		time2 = System.currentTimeMillis();
		System.out.println("It took " + (time2-time1)/1000.0 + " seconds.");
//		System.out.println("Best LCS: " + bestLCS);
//		int bestLength = 0;
//		String bestLCS = "";
//		for (String s2 : bookReader2.book)
//		{
//			String LCS = suffixTree.LCS(s2);
//			System.out.println("LCS: " + LCS);
//			if (bestLength < LCS.length())
//			{
//				bestLength = LCS.length();
//				bestLCS = LCS;
//			}
//		}
	}
}