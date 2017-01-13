// Student: Josh, Tsung Chi, Shih
// CruzID/CruzID#: jtshih/1436140
// Class: CMPS 101
// Date: 1/14/2015
// Filename: List.java
// Description: List file that has private nodes. ADT: doubly linked list.

public class List {
	
	//CURSOR TOGGLE
    private boolean cursorToggle = true;

	private class Node {
        private int data;
        private Node next;
        private Node prev;
        Node(int data)
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
        	return String.valueOf(data); 
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
    	if(root == end) 						//fix1
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
    int front()
    {
    	if(root == null)
    		throw new RuntimeException("Precondition Not Met");
    	return root.data;
    }
    int back()
    {
    	if(end==null)
    		throw new RuntimeException("Precondition Not Met");
    	return end.data;
    }
    int getElement()
    {
       	if(length()>0 && getIndex() >= 0)
       		return cursor.data;
       	else
       		throw new RuntimeException("Precondition Not Met");
    }
    boolean equals(List L)
    {
    	Node listWalker = root;
    	Node list2Walker = L.root;
    	
    	if(length() != L.length())
    		return false;
    	for(int i=0; i<length(); i++)
    	{
    		if(listWalker.data != list2Walker.data)
    			return false;
    			
    		listWalker = listWalker.next;
    		list2Walker = list2Walker.next;
    	}
    	return true;
    }
    //MANIPULATION FUNCTIONS**********************************************************************
    void clear()
    {
    	root = null;
    	end = null;
    }
    void moveTo(int i)
    {
    	if(root == null)
    		throw new RuntimeException("Precondition Not Met");
//    	System.out.println("bloop");
    	if(i>=0 || i<=length()-1)
    	{
    		Node walker = root;
	    	for(int x=0; x<i; x++)
	    		walker = walker.next;
	    	cursor = walker;
	    	cursorIndex = i;
//	    	System.out.println(": "+i);
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
    void prepend(int a)
    {
    	Node temp = new Node(a);
    	if(root==null)
    	{
    		end = temp;
    	}
    	else
    	{
//    		System.out.println("BKAHDKAHFSDFJSF");
    		temp.next = root;
	    	root.prev = temp;
    	}
    	root = temp;
    }
    void append(int a)
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
    void insertBefore(int a)
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
			
			if(cursor.prev == null)
				root = temp;
			else
				cursor.prev.next = temp;
			walker.prev = temp;
    	}
    	

		cursorIndex++;
    }
    void insertAfter(int a)
    {
    	if(length()<0)
    		throw new RuntimeException("Precondition Not Met");
    	if(getIndex() < 0)
    		throw new RuntimeException("Precondition Not Met");
    	
    	Node walker = cursor;
    	walker=walker.next;
    	
    	if(walker != end)
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
    	Node temp = root;
    	root = root.next;
    	root.prev = null;
    	temp.next = null;
    	temp = root;
//    	for(int i=0; i<length(); i++)
//    	{
//    		temp.index = i;
//    		temp = temp.next;
//    	}
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
    		System.out.println("hello");
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
    	spit += walker.data;
    	walker = walker.next;
		if(cursor == root && cursorToggle == true)
			spit += "<";
    	while(walker != null)
    	{
    		spit +=" "+walker.data;
    		if(walker == cursor && cursorToggle == true) 
    			spit += "<";

    		walker = walker.next;
    	}
    	return spit;
    }
    List copy()
    {
    	List bloop = new List();
    	Node walker = root;
    	while(walker != null)
    	{
    		bloop.append(walker.data);
    		walker = walker.next;
    	}
    	return bloop;
    }
    List concat(List L)
    {
    	List bloop = new List();
    	bloop = copy();
    	bloop.end.next = L.root;
    	L.root.prev = bloop.end;
    	return bloop;
    }
}
