package real;

/*
 * Reads in a book, converts it to lowercase, and separates it into its chapters.
 * Chapters in the book need to be marked by "CHAPTER"
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
		
		StringBuilder currentChapter = new StringBuilder();
		while (input.hasNext())
		{
			String next = input.nextLine();
			if (next.length() > 0)
			{
				if (next.startsWith("CHAPTER") && !currentChapter.equals(""))
				{
					System.out.println(book.size() + ": " + currentChapter.length());
					book.add(currentChapter.toString());
					currentChapter = new StringBuilder();
				}
				next = next.toLowerCase();
				for (int i = 0; i < next.length(); i++)
					if ((next.charAt(i) >= 'a' && next.charAt(i) <= 'z') || next.charAt(i) == ' ')
						currentChapter.append(next.charAt(i));
				currentChapter.append(" ");
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
