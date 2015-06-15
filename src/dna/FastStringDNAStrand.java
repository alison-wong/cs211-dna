package dna;


/**
 * This implementation keeps the DNA in a StringBuilder and
 * uses String methods to manipulate the DNA.
 * 
 * @Author Alison Wong
 */
public class FastStringDNAStrand extends DnaStrand {

	/**
	 * Create a new DNA strand
	 * @param dna the sequence of nucleotides that make up the DNA.  The only 
	 *    characters legal in the dna are A, C, T, and G.
	 */
	public FastStringDNAStrand (String s)
	{
		super(s);
	}
	
	
	/**
	 *  Cut this strand at every occurrence of enzyme, replacing every occurrence
	 * of enzyme with splicee.
	 *
	 * dnaSequence is manipulated using StringBuilder methods.
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
			return new FastStringDNAStrand(dnaSequence);
		}
		
		/*Will store the Final dnaSequence*/ StringBuilder Final_Sequence = new StringBuilder(dnaSequence.length());
		
		
		/*temporary substring placeholder*/  String a;
		/*stores the Original Sequence*/	 String Original_Sequence = dnaSequence;
		/*keeps the index of the Enzyme*/	 int indexOfEnzyme = Original_Sequence.indexOf(enzyme);

		
		while (Original_Sequence.indexOf(enzyme) >= 0)
		{
			//1. a = substring from beginning of Original_Sequence to before enzyme.
			a = Original_Sequence.substring(0, indexOfEnzyme);
			
			//2. Update original_sequence as the substring after enzyme.
			Original_Sequence = Original_Sequence.substring(indexOfEnzyme + enzyme.length(), Original_Sequence.length());

			//3. Add a to Final_Sequence, then the splicee.
			Final_Sequence.append(a);
			Final_Sequence.append(splicee);
			
			//4. Update index.
			indexOfEnzyme = Original_Sequence.indexOf(enzyme);
		}
		//5. add original_sequence (the remaining part after the last enzyme-replacement) to the Final_Sequence.
		Final_Sequence.append(Original_Sequence);
		
		Final_Sequence.trimToSize();
		
		return new FastStringDNAStrand(Final_Sequence.toString());
	}

}
