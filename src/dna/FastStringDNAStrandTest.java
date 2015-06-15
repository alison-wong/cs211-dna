package dna;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests cutAndSplice in the  FastStringDNA class
 */

public class FastStringDNAStrandTest {
	

		@Before
		public void setUp() throws Exception {
		}
		
		/**
		 * Test Case does all the necessary work to test the
		 * function cut and Splice, given a String.
		 * 
		 * Prints out dnaSequence of created object g.
		 * 		1. validates dnaSequence has been correctly spliced.
		 * 		2. validates that TestString has successfully been created.
		 * 		3. check that it works with toString
		 * 
		 * String before the dnaSequence is "g1 : " (in which 1 can be modified as per whichever
		 * case number is given to the function.
		 * 
		 * @param TestCaseNumber is any integer from one on, to enumerate which test
		 * @param testString is the dnaSequence used in the test
		 * @param enzyme is the string cutAndSplice searches for in the testString
		 * @param replacement is the string that replaces enzyme in the testString
		 * 
		 * @return String which represents the recombinant DNA after cutAndSplice has been used on 
		 * testString with the enzyme as enzyme and replacement as splicee
		*/
		String TestCase(int TestCaseNumber, String testString, String enzyme, String replacement)
		{
			String TestString = testString;
			FastStringDNAStrand s = new FastStringDNAStrand (TestString);
			DnaStrand g = s.cutAndSplice(enzyme, replacement);
			
			return "g" + TestCaseNumber + " : " + g.dnaSequence;

		}
		
		/** Test Cases
		 * testcutAndSplice tests the cutAndSplice function in the FastStringDNAStrand Class
		 * 
		 * takes in nothing, returns nothing
		 */
		@Test
		public void testcutAndSplice() {
			
			/*
			 * For Test Cases 1 through 10, 
			 * enzyme = "decisions"
			 * replacement = "*&*"
			 */
			
			//Test case 1: the enzyme string is at index 0.  Enzyme is in the TestString string more than once.
				//g should print out "g1 : *&* ate *&* had *&* headache *&* foozeball"
			assertEquals("Something's Wrong Here","g1 : *&* ate *&* had *&* headache *&* foozeball",TestCase(1,"decisions ate decisions had decisions headache decisions foozeball", "decisions",  "*&*"));

			//Test Case 2: There is nothing in the TestString string.  Enzyme string is not included.
				//g should print out "g2 : " 
			assertEquals("Something's Wrong Here","g2 : ",TestCase(2,"", "decisions", "*&*")) ;
			
			//Test Case 3: The enzyme string is in the middle of the TestString string, multiple times.
				//g should print out "g3 : ate *&* had *&* headache *&* foozeball"
			assertEquals("Something's Wrong Here","g3 : ate *&* had *&* headache *&* foozeball",TestCase(3, "ate decisions had decisions headache decisions foozeball", "decisions",  "*&*"));
			
			//Test Case 4: The TestString string does not include the enzyme string and is not empty. 
			//TestString String is short.
				//g should print out "g4 : 0" 
			assertEquals("Something's Wrong Here","g4 : 0",TestCase(4, "0", "decisions",  "*&*"));

			
			//Test Case 5: TestString string is very long.  Enzyme string is placed in multiple places within.
				//g should print out "g5 : *&* ate *&* had *&* headache *&* foozeball*&* ate *&* had *&* headache *&* foozeball*&* ate *&* had *&* headache *&* foozeball*&* ate *&* had *&* headache *&* foozeball*&* ate *&* had *&* headache *&* foozeball*&* ate *&* had *&* headache *&* foozeball*&* ate *&* had *&* headache *&* foozeball*&* ate *&* had *&* headache *&* foozeball*&* ate *&* had *&* headache *&* foozeball*&* ate *&* had *&* headache *&* foozeball*&* ate *&* had *&* headache *&* foozeball"
			assertEquals("Something's Wrong Here",
					"g5 : *&* ate *&* had *&* headache *&* foozeball*&* ate *&* had *&* headache *&* foozeball*&* ate *&* had *&* headache *&* foozeball*&* ate *&* had *&* headache *&* foozeball*&* ate *&* had *&* headache *&* foozeball*&* ate *&* had *&* headache *&* foozeball*&* ate *&* had *&* headache *&* foozeball*&* ate *&* had *&* headache *&* foozeball*&* ate *&* had *&* headache *&* foozeball*&* ate *&* had *&* headache *&* foozeball*&* ate *&* had *&* headache *&* foozeball"
	,TestCase(5,  "decisions ate decisions had decisions headache decisions foozeballdecisions ate decisions had decisions headache decisions foozeballdecisions ate decisions had decisions headache decisions foozeballdecisions ate decisions had decisions headache decisions foozeballdecisions ate decisions had decisions headache decisions foozeballdecisions ate decisions had decisions headache decisions foozeballdecisions ate decisions had decisions headache decisions foozeballdecisions ate decisions had decisions headache decisions foozeballdecisions ate decisions had decisions headache decisions foozeballdecisions ate decisions had decisions headache decisions foozeballdecisions ate decisions had decisions headache decisions foozeball", "decisions",  "*&*"));
			
			//Test Case 6: TestString string has enzyme string @ end. 
				//g should print out: "g6 : ate *&*"
			assertEquals("Something's Wrong Here", "g6 : ate *&*", TestCase(6, "ate decisions", "decisions",  "*&*"));
			
			//Test Case 7: TestString string is enzyme string.
				//g should print out: "g7 : *&*"
			assertEquals("Something's Wrong Here","g7 : *&*", TestCase(7, "decisions", "decisions",  "*&*"));
			
			//Test case 8: enzyme string is in TestString String one time only & not in front or end
				//g should print out: "g8 : ate *&* food"
			assertEquals("Something's wrong here", "g8 : ate *&* food", TestCase(8, "ate decisions food", "decisions",  "*&*"));
			
			//Test case 9: enzyme is not in the TestString String.  TestString string of medium size.
				//g should print out: "g9 : ate of foozeball eleventy Seven dumplings yum"
			assertEquals("Something's Wrong Here", "g9 : ate of foozeball eleventy Seven dumplings yum", TestCase(9, "ate of foozeball eleventy Seven dumplings yum", "decisions",  "*&*"));

			//Test case 10: TestString is made up of enzymes.
				//g should print out: "g10 : *&**&**&**&*"
			assertEquals("Something's Wrong Here",  "g10 : *&**&**&**&*" , TestCase(10, "decisionsdecisionsdecisionsdecisions", "decisions", "*&*"));
			
			// Test Case 11 deals with the enzyme being empty. & the replacement and TestString being non-empty.
				//g should print out "ate decisions had decisions headache decisions foozeball"
					assertEquals("Something's Wrong Here", "g11 : ate decisions had decisions headache decisions foozeball", TestCase(11, "ate decisions had decisions headache decisions foozeball", "","*&*"));
			
			 	String TestString = "ate decisions had decisions headache decisions foozeball";
				SlowStringDNAStrand s = new SlowStringDNAStrand (TestString);
				DnaStrand g = s.cutAndSplice("", "*&*");
			
				
				System.out.println("g 11a : " + g.dnaSequence);
			
			 
			 //Test Case 12 deals with the replacement being empty and the replacement and TestString being non-empty.
			 	//g should print out "g12 : ate decisions had decisions headache decisions foozeball"
				assertEquals("Something's Wrong Here", "g12 : ate decisions had decisions headache decisions foozeball", TestCase(12, "ate decisions had decisions headache decisions foozeball", "",  "*&*"));
			 
			
			//Test Case 13 deals with the enzyme and replacement both being empty. 
			 	//g should print out "g13 : ate decisions had decisions headache decisions foozeball"
			 assertEquals("Something's Wrong Here", "g13 : ate decisions had decisions headache decisions foozeball", TestCase(13, "ate decisions had decisions headache decisions foozeball", "",  ""));
			

			 // Test Case 14 deals with all three being empty.
			 	//g should print out "g14 : "
			 assertEquals("Something's Wrong Here", "g14 : ",TestCase(14, "", "",  ""));	
		}
}
		


