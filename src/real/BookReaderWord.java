package real;

/*
 * Reads in a book, converts it to lowercase, and separates it into its chapters.
 * Chapters in the book need to be marked by "CHAPTER"
 */

import abstracts.TextReader;

import java.util.ArrayList;
//import java.util.Scanner;
import java.io.*;

public class BookReaderWord extends TextReader
{
	public ArrayList<ArrayList<String>> book;
	
	public BookReaderWord(String filename)
	{
		super(filename);		//does nothing
		
		//read in book - set book to "" if not found
		BufferedReader input;
		book = new ArrayList<ArrayList<String>>();
		try
		{
			input = new BufferedReader(new FileReader(filename));
			System.out.println("Loaded book.");
		
			ArrayList<String> currentChapter = new ArrayList<String>();
			while (input.ready())
			{
				String next = input.readLine();
				if (next.length() > 0)
				{
					if (next.startsWith("CHAPTER") && !currentChapter.equals(""))
					{
						System.out.println(book.size() + ": " + currentChapter.size());
						book.add(currentChapter);
						currentChapter = new ArrayList<String>();
					}
					next = next.toLowerCase();
					StringBuilder word = new StringBuilder();
					for (int i = 0; i < next.length(); i++)
					{
						char c = next.charAt(i);
						if ((c >= 'a' && c <= 'z') || c == '\'' || c == '-')
							word.append(c);
						else
						{
							if (word.length() > 0)
								currentChapter.add(word.toString());
							word = new StringBuilder();
						}
					}
				}
			}
			if (currentChapter.size() > 0)
				book.add(currentChapter);
			
			input.close();
		}
		catch (Exception e)
		{
			System.out.println("Book \"" + filename + "\" not found.");
			book.add(new ArrayList<String>());
			return;
		}
		
	}

	public ArrayList<String> chapterList(int chapter)
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

	@Override
	public String chapter(int chapter)
	{
		return "";
	}	
}
