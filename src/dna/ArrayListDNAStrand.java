package dna;

import java.util.ArrayList;



/**
 *  This is an implementation of a DNA strand where the DNA se-
 *  quence may be broken across multiple elements in an ArrayList.
 *  
 *  @author Alison Wong
 *  @date March 10, 2011
 */
public class ArrayListDNAStrand extends DnaStrand {

	/**
	 * Create a new Array List DNA strand
	 * @param dna the sequence of nucleotides that make up the DNA.  The only 
	 *    characters legal in the dna are A, C, T, and G.
	 */
	
	public ArrayListDNAStrand (String s)
	{
		super(s);
	}
	
	
	
	/**
	 * Cut this strand at every occurrence of enzyme, replacing every occurrence
	 * of enzyme with splicee.
	 * 
	 * stores dnaSequence in an ArrayList element and manipulates it using ArrayList methods
	 * 
	 * @param enzyme
	 *            is the pattern/strand searched for and replaced
	 * @param splicee
	 *            is the pattern/strand replacing each occurrence of enzyme
	 * @return the new strand leaving the original strand unchanged.
	 */
	@Override
	public DnaStrand cutAndSplice(String enzyme, String splicee) {
		
		if (enzyme == "")
		{
			return new ArrayListDNAStrand(dnaSequence);
		}
		
		//Stores the Final dnaSequence (before it is converted to a String)
		ArrayList<String> PreFinal_Sequence = new ArrayList<String>();
		
		
		String a; //temporary substring placeholder.
		String Original_Sequence = dnaSequence; //stores the original dnaSequence
		
		 //keeps the index of the Enzyme
		int indexOfEnzyme = Original_Sequence.indexOf(enzyme);
 
	
		while (Original_Sequence.indexOf(enzyme) >= 0)
		{
			//1. a = substring from beginning of Original_Sequence to before enzyme.
			a = Original_Sequence.substring(0, indexOfEnzyme);

			
			//2. Update original_sequence as the substring after enzyme.
			Original_Sequence = Original_Sequence.substring(indexOfEnzyme + enzyme.length(), Original_Sequence.length());

			
			//3. Add a to Final_Sequence, then the splicee.
			PreFinal_Sequence.add(a);
			PreFinal_Sequence.add(splicee);
			
			//4. Update index.
			indexOfEnzyme = Original_Sequence.indexOf(enzyme);
		}
		
		//5. add original_sequence (the remaining part after the last enzyme-replacement) to the Final_Sequence.
		PreFinal_Sequence.add(Original_Sequence);
		
		//Convert to String
		StringBuilder buildmon = new StringBuilder(dnaSequence.length());  //StringBuilder is made. 
		
		for (int i = 0; i < PreFinal_Sequence.size();i++) //StringBuilder adds the elements of the array on, in order.
			
			buildmon.append(PreFinal_Sequence.get(i));
		
		buildmon.trimToSize();
		String Final_Sequence = buildmon.toString(); //Uses StringBuilder's toString method to convert to String. 
		
		return new ArrayListDNAStrand(Final_Sequence);
	}
	
	


}
