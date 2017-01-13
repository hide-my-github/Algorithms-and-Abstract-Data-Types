// Student: Josh, Tsung Chi, Shih
// CruzID/CruzID#: jtshih/1436140
// Class: CMPS 101
// Date: 2/9/2015
// Filename: List.java
// Description: List file that has private nodes. ADT: doubly linked list. Remodeled with Objects for Matrix (PA3)

public class List {
	
	//CURSOR TOGGLE
    private boolean cursorToggle = true;

	private class Node {
        private Object data;
        private Node next;
        private Node prev;
        Node(Object data)
        {
            this.data = data;
            next = null;
            prev = null;
        }
        private Node()
        {
        	next = null;
        	prev = null;
        }
		public String toString() 
        { 
        	return ("("+String.valueOf(data)+") "); 
        }
		public boolean equals(Node x)
		{
				return(data.equals(x.data));
		}
    }
    private Node root = null;
    private Node end = null;
    private Node cursor;
    private int cursorIndex;
    //CONSTRUCTOR********************************************************************************
    List()
    {
    	cursor = new Node();
    	cursorIndex = -1;
    }
    //ACCESS FUNCTIONS***************************************************************************
    int length()
    {
    	int i=0;
    	Node walker = root;
    	if(root == null) 					
    		return 0;
    	while(walker!=null)
    	{
    		walker = walker.next;
    		i++;
    	}
    	return i;
    }
    int getIndex() //cursor function
    {
    	return cursorIndex;
    }
    Object front()
    {
    	if(root == null)
    		throw new RuntimeException("Precondition Not Met");
    	return root.data;
    }
    Object back()
    {
    	if(end==null)
    		throw new RuntimeException("Precondition Not Met");
    	return end.data;
    }
    Object getElement()
    {
       	if(length()>0 && getIndex() >= 0)
       		return cursor.data;
       	else
       		throw new RuntimeException("Precondition Not Met");
    }
	public boolean equals(List x)
	{
//		if(x instanceof List)
//		{
			List h = (List) x;
			Node walker = root;
			for(Node Hwalker=h.root; Hwalker != null; Hwalker = Hwalker.next)
			{
				if(!walker.equals(Hwalker))
					return false;
				walker=walker.next;
			}
			return true;
//		}
//		else
//			return false;
	}
    //MANIPULATION FUNCTIONS**********************************************************************
    void clear()
    {
    	root = null;
    	end = null;
    	cursor = null;
    }
    void moveTo(int i)
    {
    	if(root == null)
    		throw new RuntimeException("Precondition Not Met");
    	if(i>=0 || i<=length()-1)
    	{
    		Node walker = root;
	    	for(int x=0; x<i; x++)
	    		walker = walker.next;
	    	cursor = walker;
	    	cursorIndex = i;
    	}
    }
    void movePrev()
    {
    	if(cursor==root)
    	{
    		Node blank = new Node(-1);
    		cursor = blank;
    		cursorIndex = -1;
    		return;
    	}
    	cursor = cursor.prev;
    	cursorIndex--;
    }
    void moveNext()
    {
    	if(cursor==end)
    	{
    		Node blank = new Node(-1);
    		cursor = blank;
    		cursorIndex = -1;
    		return;
    	}
    	cursor = cursor.next;
    	cursorIndex++;
    }
    void prepend(Object a)
    {
    	Node temp = new Node(a);
    	if(root==null)
    	{
    		end = temp;
    	}
    	else
    	{
    		temp.next = root;
	    	root.prev = temp;
    	}
    	root = temp;
    }
    void append(Object a)
    {
		Node temp = new Node(a);
    	if(root == null)//if list is empty
    		root = temp;
    	else
    	{
			temp.prev = end;
			end.next = temp;
    	}
		end = temp;
    }
    void insertBefore(Object a)
    {
    	if(length()<0)
    		throw new RuntimeException("Precondition Not Met");
    	if(getIndex() < 0)
    		throw new RuntimeException("Precondition Not Met");
    	if(cursor==root)
    		prepend(a);
    	else
    	{
    		Node walker = cursor;
	    	Node temp = new Node(a);
			temp.next = walker;
			temp.prev = walker.prev;
			cursor.prev.next = temp;
			walker.prev = temp;
    	}
		cursorIndex++;
    }
    void insertAfter(Object a)
    {
    	if(length()<0)
    		throw new RuntimeException("Precondition Not Met");
    	if(getIndex() < 0)
    		throw new RuntimeException("Precondition Not Met");
    	
    	Node walker = cursor;
    	walker=walker.next;
    	
    	if(cursor != end)
    	{
    		Node temp = new Node(a);
    		cursor.next = temp;
    		temp.prev = cursor;
    		walker.prev = temp;
    		temp.next = walker;
    	}
    	else
    	{
    		append(a);
    	}
    }
    void deleteFront()
    {
    	if(length()<0)
    		throw new RuntimeException("Precondition Not Met");
    	if(length() == 1)
    	{
    		clear();
    	}
    	else
    	{
	    	Node temp = root;
	    	root = root.next;
	    	root.prev = null;
	    	temp.next = null;
	    	temp = root;
    	}
    }
    void deleteBack()
    {
    	if(length()<0)
    		throw new RuntimeException("Precondition Not Met");
    	Node temp = end.prev;
    	temp.next = null;
    	end.prev = null;
    	end = temp;
    }
    void delete()
    {
    	Node walker = cursor;
    	walker = walker.next;
    	if(length()<0)
    		throw new RuntimeException("Precondition Not Met");
    	if(getIndex()<0)
    		throw new RuntimeException("Precondition Not Met");
    	
    	if(cursor == root)
    		deleteFront();
    	else if(cursor == end)
    		deleteBack();
    	else
    	{
	    	Node before = cursor.prev;
	    	before.next = walker;
	    	walker.prev = before;
	    	cursor.next = null;
	    	cursor.prev = null;
    	}
    }
    //OTHER METHODS ***************************************************
    public String toString()
    {
    	Node walker = root;
    	String spit = "";
    	
    	while(walker != null)
    	{
        	spit += walker.toString();
    		walker = walker.next;
    	}
    	return spit;
    }
//    List copy()
//    {
//    	List bloop = new List();
//    	Node walker = root;
//    	while(walker != null)
//    	{
//    		bloop.append(walker.data);
//    		walker = walker.next;
//    	}
//    	return bloop;
//    }
//    List concat(List L)
//    {
//    	List bloop = new List();
//    	bloop = copy();
//    	bloop.end.next = L.root;
//    	L.root.prev = bloop.end;
//    	return bloop;
//    }
}
