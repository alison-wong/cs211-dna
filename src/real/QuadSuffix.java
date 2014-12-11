package real;
import java.util.ArrayList;
import java.lang.StringBuilder;

import abstracts.SuffixTree;

public class QuadSuffix extends SuffixTree {

	public QuadSuffix(String text) {
		super(text); //does nothing
		init(text);		
	}

	public class Node {
		public Node parent;
		public String name;
		public ArrayList<Node> children;
		public DSNode ds;
		public Node (Node p, String n)
		{
			this.parent = p;
			this.name = n;
			this.children = new ArrayList<Node>();
		}
		public Node findChild(char c)
		{
			for (Node n : children)
			{
				if (n.name.charAt(0) == c)
					return n;
			}
			return null;
		}
	}    



	public class DSNode {
		public Node parent;
		public String name;
		public DSNode (Node p) {
			this.parent = p;
			this.name = "$";
		}
	}

	public Node addChild(Node p, char c) {
		String s = String.valueOf(c);
		Node newNode = new Node(p,s);
		return newNode;
	}

	public void addDS(Node p) {
		//Adds dollar sign child (suffix marker on s. tree)
		DSNode newDSNode = new DSNode(p);
		p.ds = newDSNode;
		numDS++;
		DSNodes.add(newDSNode);
	}

	Node root = new Node(null,"");
	int numDS = 0;
	ArrayList<DSNode> DSNodes = new ArrayList<DSNode>();
	String myString;

	//	    public static void main(String Args[]) {
	//		String s = "abca";
	//		QuadSuffixTree qst = new QuadSuffixTree();
	//		qst.init(s);
	//		//System.out.println(qst.isSubstring("x"));
	//		//System.out.println(qst.isSubstring("bc"));
	//		//System.out.println(qst.isSubstring("abca"));
	//		//System.out.println(qst.isSubstring("abc"));
	//		//System.out.println(qst.isSubstring("c"));
	//		//System.out.println(qst.isSubstring("bcx"));
	//	    }

	public void init(String s) {
		myString = s;
		int length = s.length();

		for ( int i = 0; i < length; i++ ) {
//			System.out.println(i);
		    String a = s.substring(0,i+1);
		    int l = a.length();
		    for ( int x = 0; x < l; x++ ) {
			String b = s.substring(i-x,i+1);
			dfsAdd(b);
		    }
		}

		rmInvalidNodes(length);
	}

	public void rmInvalidNodes(int length) {
		//Remove invalid dollar sign nodes
		//System.out.println(numDS);
		for ( DSNode x : DSNodes ) {
			x.parent.ds = null;
		}
		DSNodes = new ArrayList<DSNode>();
		numDS = 0;
		String[] suffixes = new String[length];

		//Fill out suffix array
		int c = 0;
		for ( int x = length - 1; x >= 0; x-- ) {
			String s = myString.substring(x,length);
			//System.out.println(s);
			suffixes[c] = s;
			c++;
		}

		for ( int x = 0; x < length; x++ ) {
			String s = suffixes[x];
			addDSleaf(s);
		}

		//System.out.println(numDS);
	}

	public void addDSleaf(String s) {
		s = new StringBuilder(s).reverse().toString();
		Node p = root;
		int c = 0;
		while ( true ) {
			if ( c == s.length() ) {
				addDS(p);
				break;
			}

			for ( Node x : p.children ) {
				if ( x.name.charAt(0) == s.charAt(c) ) {
					p = x; 
				}
			}
			c++;  
		}
	}

	public void dfsAdd(String b) {
		int i = 0;
		dfsAdd(b,i,root);
	}

	public void dfsAdd(String b, int i, Node p) {
		if ( p == null || i > b.length() - 1 ) {
			return;
		}

		char c = b.charAt(i);

		//loop through children to find the char c
		boolean contains = false;
		for ( Node x : p.children ) {
			if ( x.name.charAt(0) == c ) {
				contains = true;
				dfsAdd(b,++i,x);
			}
		}

		if ( !contains ) {
			//add child then recur
			Node x = addChild(p,c);
			p.children.add(x);
			addDS(x);
		}
	}

	public boolean isSubstring(String s) {
		//returns whether input string is a valid substring of myString
		Node p = root;

		int c = 0;
		while( c != s.length() ) {
			boolean containsChar = false;
			for ( Node x : p.children ) {
				if ( x.name.charAt(0) == s.charAt(c) ) {
					p = x;
					containsChar = true;
					break;
				}
				p = x;
			}
			if ( containsChar == false ) {
				return false;
			}
			c++;
		}
		return true;
	}

	@Override
	public String LCS(String text)
	{
		int start = 0;
		int end = 0;
		int bestStart = 0;
		int bestEnd = 0;
		int bestLength = 0;
		Node curr = root;

		while (end < text.length())
		{
			char c = text.charAt(end);
			curr = curr.findChild(c);
			if (curr == null)
			{
				int length = end - start;
				if (length > bestLength)
				{
					bestLength = length;
					bestStart = start;
					bestEnd = end;
				}
				if (length == 0)
					end++;
				start = end;
				curr = root;
			}
			else
				end++;
		}
		if (end - start > bestLength)
			return text.substring(start, end);
		else
			return text.substring(bestStart, bestEnd);
	}

	@Override
	public String LCS(SuffixTree suffixTree) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
}
