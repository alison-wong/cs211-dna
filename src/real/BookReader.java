package real;

/*
 * Reads in a book, converts it to lowercase, and separates it into its chapters.
 * Chapters in the book need to be marked by
 */

import abstracts.TextReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class BookReader extends TextReader
{
	public ArrayList<String> book;
	
	public BookReader(String filename)
	{
		super(filename);		//does nothing
		
		//read in book - set book to "" if not found
		Scanner input;
		book = new ArrayList<String>();
		try
		{
			input = new Scanner(new File(filename));
			System.out.println("Loaded book.");
		}
		catch (Exception e)
		{
			System.out.println("Book \"" + filename + "\" not found.");
			book.add("");
			return;
		}
		
		String currentChapter = "";
		while (input.hasNext())
		{
			String next = input.nextLine();
			if (next.length() > 0)
			{
				if (next.startsWith("CHAPTER") && !currentChapter.equals(""))
				{
					book.add(currentChapter);
					currentChapter = "";
				}
				currentChapter += next.toLowerCase();
			}
		}
		input.close();
		
	}

	public String chapter(int chapter)
	{
		return book.get(chapter);
	}

	public String entireBook()
	{
		String wholeBook = "";
		for (int i = 0; i < book.size(); i++)
		{
			wholeBook += book.get(i) + " ";
		}
		return wholeBook;
	}	
}
