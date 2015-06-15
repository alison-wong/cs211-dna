package dna;

/**
 * Interface for DNA/strand experiments
 * 
 * @author Owen Astrachan
 * @date February, 2008
 * @author Barbara Lerner Small modifications
 * @date March 25, 2010
 */
public abstract class DnaStrand {

	protected String dnaSequence;

	/**
	 * Create a new DNA strand
	 * @param dna the sequence of nucleotides that make up the DNA.  The only 
	 *    characters legal in the dna are A, C, T, and G.
	 */
	public DnaStrand (String dna) {
		dnaSequence = dna;
	}

	/**
	 * Cut this strand at every occurrence of enzyme, replacing every occurrence
	 * of enzyme with splicee.
	 * 
	 * @param enzyme
	 *            is the pattern/strand searched for and replaced
	 * @param splicee
	 *            is the pattern/strand replacing each occurrence of enzyme
	 * @return the new strand leaving the original strand unchanged.
	 */
	public abstract DnaStrand cutAndSplice(String enzyme, String splicee);

	/**
	 * Returns the number of nucleotides/base-pairs in this strand.
	 * 
	 * @return the number of base-pairs in this IDnaStrand
	 */
	public long size() {
		return dnaSequence.length();
	}

	@Override
	public String toString() {
		return dnaSequence;
	}

}
