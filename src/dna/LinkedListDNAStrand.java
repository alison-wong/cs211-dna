package dna;

/**
 * This implementation keeps the DNA in several nodes and
 * uses String methods to manipulate the DNA.
 * 
 * @date Apr 11
 * @author Alison Wong
 */

public class LinkedListDNAStrand extends DnaStrand {
	
	DNAStrandNode head;	//Node @ beginning of a LinkedListDNAStrand
	DNAStrandNode tail; //Node @ end of a LinkedListDNAStrand
	int size;
	
	/**
	 * Create a new DNA strand
	 * 
	 * Creates the first node of the LinkedListDNAStrand, & fills its DATA with the dnaSequence.
	 * Sets the head (first node) to be the first node.
	 * Sets the tail (last node) to be the first node (only one node, so it's the first & last node.)
	 * Sets the size to be 1.
	 * 
	 * @param dna the sequence of nucleotides that make up the DNA.  The only 
	 *    characters legal in dna are A, C, T, and G.
	 */
	public LinkedListDNAStrand(String dna) {
		super(dna); //sets dnaSequence to the dna.
		head = new DNAStrandNode(dna); 
		tail = head;  
		
		size = 1; //initial size of list is 1 (1 node long)
	
	}
	
	
	

	/**
	 *  Cut this strand at every occurrence of enzyme, replacing every occurrence
	 * of enzyme with splicee.
	 * 
	 * dnaSequence is stored in a node and manipulated using String methods and LinkedListDNAStrand methods.
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
			return new LinkedListDNAStrand(dnaSequence);
		}

		
		//1) Makes a LinkedListDNAStrand Linked_List with the dnaSequence in a single node.
		//Will become the final DnaStrand
		LinkedListDNAStrand Linked_List= new LinkedListDNAStrand(dnaSequence);
		 		
		
		//temporary substring placeholder.
		 String a;	 
		 
		 //Stores original dnaSequence. First Node of Linked_List.
		 String Original_Sequence = Linked_List.head.DATA;
		
		 //keeps the index of the Enzyme
		 int indexOfEnzyme = Original_Sequence.indexOf(enzyme);
		 
		 
		 while (indexOfEnzyme >= 0)
		 {
			 //1. a = substring from beginning of Original_Sequenceto before enzyme.
			 a = Original_Sequence.substring(0,indexOfEnzyme);
			 
			//2. Update original_sequence as the substring after enzyme.
			Original_Sequence = Original_Sequence.substring(indexOfEnzyme + enzyme.length(), Original_Sequence.length());

			 //add a and then splicee to linked list.
			Linked_List.append(a);
			Linked_List.append(splicee); 
			
			//4. Update index.
			indexOfEnzyme = Original_Sequence.indexOf(enzyme);
		 }
			//5. add original_sequence (the remaining part after the last enzyme-replacement) to the Final_Sequence.
		 	Linked_List.append(Original_Sequence);
		 	
		 	//remove the now empty first node of Linked_List.
		 	Linked_List.head = Linked_List.head.next;
		 	Linked_List.size = Linked_List.size-1; //update size

		 	Linked_List.dnaSequence = Linked_List.toString(); 
		 	
		 	
		 	return Linked_List;
	}
	
	
	

	/**Append
	 * 	adds a value to the end of a linked list.
	 * 
	 * @param value must be a String.
	 */
	public void append(String value)
	{
		
		//creates a node that holds the value
		//and links it to the current tail node.
		this.tail.next = new DNAStrandNode(value);
		
		//tail is the newest added Node.
		tail = tail.next;
		
		//updates size
		size = size + 1;
		
//		Updates dnaSequence of the LinkedListDNAStrand to add on the new value
		dnaSequence = dnaSequence + value;
		
		
	}
	
	
	
	/**toString()
	 * 
	 * walks the linked list and produces string value of List
	 * by concatenating the strings of each value in each node together. 
	 * 
	 * Takes no Parameters in, returns a String representing value
	 * of List combined (values of all nodes in the list.)
	 */
	
	public String toString()
	{
		//until the value of next is null:
		
		String FinishedString = "";
		DNAStrandNode current_node = head;

		for (int i = 0; i < size; i++)
			{
				FinishedString = FinishedString + current_node.DATA;
				if (i == (size - 1))
					return FinishedString;
				else
					current_node = current_node.next;
			}
		
		return ""; //required.
		
		//You will call this method from your test program, but not from your benchmark program. 
		
	}
}
