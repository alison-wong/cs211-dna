package real;
import java.util.ArrayList;
import java.lang.StringBuilder;

import abstracts.SuffixTree;

public class QuadSuffixWord extends SuffixTree {

	public QuadSuffixWord(ArrayList<String> text) {
		super(""); //does nothing
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
		public Node findChild(String c)
		{
			for (Node n : children)
			{
				if (n.name.equals(c))
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

	public Node addChild(Node p, String c) {
		Node newNode = new Node(p,c);
		return newNode;
	}

	public void addDS(Node p) {
		//Adds dollar sign child (suffix marker on s. tree)
		DSNode newDSNode = new DSNode(p);
		p.ds = newDSNode;
		numDS++;
		DSNodes.add(newDSNode);
	}

	public Node root = new Node(null,"");
	int numDS = 0;
	ArrayList<DSNode> DSNodes = new ArrayList<DSNode>();
	public ArrayList<String> myString;

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

	public void init(ArrayList<String> text) {
		myString = text;
		int length = text.size();

		for ( int i = 0; i < length; i++ ) {
//			System.out.println(i);
		    for ( int x = 0; x < i + 1; x++ ) {
		    	dfsAdd(i-x,i+1);
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

		for ( int x = 0; x < length; x++ ) {
			addDSleaf(x, length);
		}

		//System.out.println(numDS);
	}

	public void addDSleaf(int x, int length) {
		Node p = root;
		int c = 0;
		while ( true ) {
			if ( c == length - x ) {
				addDS(p);
				break;
			}

			for ( Node n : p.children ) {
				if ( n.name.equals(myString.get(x)) ) {
					p = n; 
				}
			}
			c++;  
		}
	}

	public void dfsAdd(int start, int end) {
		int i = start;
		dfsAdd(start, end, i, root);
	}

	public void dfsAdd(int start, int end, int i, Node p) {
		if ( p == null || i > end - 1)
		{
			return;
		}

		String c = myString.get(i);

		//loop through children to find the char c
		boolean contains = false;
		for ( Node x : p.children ) {
			if ( x.name.equals(c)) {
				contains = true;
				dfsAdd(start,end,++i,x);
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

	public String LCS(ArrayList<String> text)
	{
		int start = 0;
		int end = 0;
		int length = 0;
		int bestStart = 0;
		int bestEnd = 0;
		int bestLength = 0;
		Node curr = root;
		
		while (end < text.size())
		{
			String c = text.get(end);
			curr = curr.findChild(c);
			if (curr == null)
			{
				if (length > bestLength)
				{
					bestLength = length;
					bestStart = start;
					bestEnd = end;
				}
				if (length == 0)
					end++;
				length = 0;
				start = end;
				curr = root;
			}
			else
			{
				length += text.get(end).length() + 1;
				end++;
			}
		}
		
		StringBuilder result = new StringBuilder();
		if (end - start > bestLength)
		{
			bestStart = start;
			bestEnd = end;
		}
		while (bestStart < bestEnd)
		{
			result.append(text.get(bestStart) + " ");
			bestStart++;
		}
		return result.toString();
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

	@Override
	public String LCS(String text) {
		// TODO Auto-generated method stub
		return "";
	}
}
