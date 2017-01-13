// Student: Josh, Tsung Chi, Shih
// CruzID/CruzID#: jtshih/1436140
// Class: CMPS 101
// Date: 2/9/2015
// Filename: Matrix.java
// Description: Matrix that holds an array of Lists. Along with a private class Entry

public class Matrix {
	private class Entry
	{
		private int colm;
		private double data;
		Entry(int j, double x)
		{
			colm = j;
			data = x;
		}
		public String toString()
		{
			String s = "";
			s += Integer.toString(colm)+", "+Double.toString(data);
			return s;
		}
		public boolean equals(Object x)
		{
			if(x instanceof Entry)
			{
				Entry h = (Entry) x;
				if(colm == h.colm)
				{
					if(data == h.data)
						return true;
					else
						return false;
				}
				else
					return false;
			}
			else
				return false;
		}
	}
	private List[] row;
	// Constructor
	Matrix(int n) // Makes a new n x n zero Matrix. pre: n>=1
	{
    	if(n<1)
    		throw new RuntimeException("Constructor's Precondition Not Met");
        List[] matrix = new List[n+1];
        for(int i=0;i<=n;i++)
        {
        	matrix[i] = new List();
        }
        row = matrix;
	}
	// Access functions--------------------------------------------------------
	int getSize() // Returns n, the number of rows and columns of this Matrix
	{
		return row.length-1;
	}
	int getNNZ() // Returns the number of non-zero entries in this Matrix
	{
		int hold = 0;
		for(int i=1; i<getSize()+1; i++)
		{
			if(row[i].length() != 0)
			{
				row[i].moveTo(0);
				while(row[i].getIndex() != -1)
				{
					row[i].moveNext();
					hold++;
				}
			}
		}
		return hold;
	}
	public boolean equals(Object x) // overrides Object's equals() method
	{
		if(x instanceof Matrix)
		{
			Matrix H = (Matrix) x;
			for(int i=1; i<getSize()+1; i++)
			{
				if(!row[i].equals(H.row[i]))
					return false;
			}
			return true;
		}
		else
			return false;
	}
	// Manipulation procedures--------------------------------------------------
	void makeZero() // sets this Matrix to the zero state
	{
		for(int i=1; i<getSize()+1; i++)
		{
			if(row[i].length() > 0)
				row[i].clear();
		}
	}
	Matrix copy()// returns a new Matrix having the same entries as this Matrix
	{
		Matrix hold = new Matrix(getSize());
		for(int i=1; i<getSize()+1; i++)
		{
			if(row[i].length()>0)
				row[i].moveTo(0);
			while(row[i].getIndex() != -1)
			{
				hold.row[i].append(row[i].getElement());
				row[i].moveNext();
			}
		}
		return hold;
	}
	 // changes ith row, jth column of this Matrix to x
	 // pre: 1<=i<=getSize(), 1<=j<=getSize()
	void changeEntry(int i, int j, double x)
	{
		if(i < 1 || j < 1 || i>getSize()+1 || j>getSize()+1) //trying to change the entry of an index thats clearly out of bounds
			throw new RuntimeException("Precondition Not Met");
		Entry E = new Entry(j, x);
		//4 different cases of changeEntry
		if(row[i].length() > 0) //if something is within this row
		{
			row[i].moveTo(0);
			int column = ((Entry)row[i].getElement()).colm;
			while(column < j && row[i].getIndex() != -1)
			{
				row[i].moveNext();
				if(row[i].getIndex() != -1)
					column = ((Entry)row[i].getElement()).colm;
			}
			if(column == j)
			{
				System.out.println(row[i].toString());
				int hold = row[i].getIndex();
				row[i].delete(); //delete what is there, cursor also disappears
				if(x != 0)
				{
					if(row[i].length() == 0)  //cursor deleted was the first node
						row[i].prepend(E);
					else
					{
						row[i].moveTo(hold-1); //move cursor to the node prior to where the previous node was deleted
						if(hold-1 < 0)
							row[i].prepend(E);
						else
							row[i].insertAfter(E); //insertBefore it
					}
				}
			}
			else
			{
				if(row[i].getIndex() == -1) //moveNext() fell off the list
				{
					if(x != 0) // no delete is called here because j never matches with any existing entry.columns
						row[i].append(E);
				}
				else if(row[i].getIndex() == 0) //moveNext() never occurred because first entry.column is greater than j
				{
					if(x != 0)
						row[i].prepend(E);
					else
						row[i].deleteFront();
				}
				else //jumped to a place on the list. no delete is called here because j never matches with any existing entry.columns
				{
					if(x != 0)
						row[i].insertBefore(E);
				}
			}

		}
		else
		{
			if(x != 0)
				row[i].append(E);
		}
	}
	Matrix scalarMult(double x)// returns a new Matrix that is the scalar product of this Matrix with x
	{
		Matrix hold = new Matrix(getSize()+1);
		int insColm;
		double insData;
		if(x != 0)
		{
			for(int i=1; i<getSize()+1; i++)
			{
				if(row[i].length() > 0)
				{
					row[i].moveTo(0);
					while(row[i].getIndex() != -1)
					{
						insColm = (int) (((Entry)row[i].getElement()).colm);
						insData = (double) (((Entry)row[i].getElement()).data * x);
						Entry insEntry = new Entry(insColm, insData);
						hold.row[i].append(insEntry);
						row[i].moveNext();
					}
				}
			}
		}
		return hold;
	}
	 // returns a new Matrix that is the sum of this Matrix with M
	 // pre: getSize()==M.getSize()
	Matrix add(Matrix M)
	{
    	if(getSize() != M.getSize())
    		throw new RuntimeException("Precondition Not Met");
		Matrix hold = new Matrix(getSize());
		double insData;
		for(int i=1;i<getSize()+1;i++)
		{
			if(row[i].length() > 0)
				row[i].moveTo(0);
			if(M.row[i].length()>0)
				M.row[i].moveTo(0);
			while(row[i].getIndex() != -1 && M.row[i].getIndex() != -1) //while M and 'this' both HAVEN'T reached the end
			{
				if(((Entry)row[i].getElement()).colm == ((Entry)M.row[i].getElement()).colm) //M.row.colm == this.row.colm
				{
					insData = ((Entry)row[i].getElement()).data + ((Entry)M.row[i].getElement()).data;
					Entry insEntry = new Entry(((Entry)row[i].getElement()).colm, insData);
					hold.row[i].append(insEntry);
					if(M.row[i] == row[i])
						row[i].moveNext();
					else
					{
						row[i].moveNext();
						M.row[i].moveNext();
					}
				}
				else if(((Entry)row[i].getElement()).colm < ((Entry)M.row[i].getElement()).colm) //this.row.colm > M.row.colm, insert this first
				{
					Entry insEntry = new Entry(((Entry)row[i].getElement()).colm, ((Entry)row[i].getElement()).data);
					hold.row[i].append(insEntry);
					row[i].moveNext();
				}
				else //this.row.colm < M.row.colm, insert M first
				{
					Entry insEntry = new Entry(((Entry)M.row[i].getElement()).colm, ((Entry)M.row[i].getElement()).data);
					hold.row[i].append(insEntry);
					M.row[i].moveNext();
				}
			}
			if(row[i].getIndex() == -1 && M.row[i].getIndex() != -1) //if this finished walking, but not M
			{
				while(M.row[i].getIndex() != -1)
				{
					Entry insEntry = new Entry(((Entry)M.row[i].getElement()).colm, ((Entry)M.row[i].getElement()).data);
					hold.row[i].append(insEntry);
					M.row[i].moveNext();
				}
			}
			else if(row[i].getIndex() != -1 && M.row[i].getIndex() == -1) //if M finished walking, but not 'this'
			{
				while(row[i].getIndex() != -1)
				{
					Entry insEntry = new Entry(((Entry)row[i].getElement()).colm, ((Entry)row[i].getElement()).data);
					hold.row[i].append(insEntry);
					row[i].moveNext();
				}
			}
		}
		return hold;
	}
	 // returns a new Matrix that is the difference of this Matrix with M
	 // pre: getSize()==M.getSize()
	Matrix sub(Matrix M)
	{
		if(getSize() != M.getSize())
    		throw new RuntimeException("Precondition Not Met");
		Matrix hold = new Matrix(getSize());
		if(row == M.row)
			return hold;
		double insData;
		for(int i=1;i<getSize()+1;i++)
		{
			if(row[i].length() > 0)
				row[i].moveTo(0);
			if(M.row[i].length()>0)
				M.row[i].moveTo(0);
			while(row[i].getIndex() != -1 && M.row[i].getIndex() != -1) //while M and 'this' both HAVEN'T reached the end
			{
				if(((Entry)row[i].getElement()).colm == ((Entry)M.row[i].getElement()).colm) //M.row.colm == this.row.colm
				{
					insData = ((Entry)row[i].getElement()).data - ((Entry)M.row[i].getElement()).data;
					Entry insEntry = new Entry(((Entry)row[i].getElement()).colm, insData);
					if(insData != 0)
						hold.row[i].append(insEntry);
					if(M.row[i] == row[i])
						row[i].moveNext();
					else
					{
						row[i].moveNext();
						M.row[i].moveNext();
					}
				}
				else if(((Entry)row[i].getElement()).colm < ((Entry)M.row[i].getElement()).colm) //this.row.colm > M.row.colm, insert this first
				{
					Entry insEntry = new Entry(((Entry)row[i].getElement()).colm, ((Entry)row[i].getElement()).data);
					hold.row[i].append(insEntry);
					row[i].moveNext();
				}
				else //this.row.colm < M.row.colm, insert M first
				{
					insData = 0 - ((Entry)M.row[i].getElement()).data;
					Entry insEntry = new Entry(((Entry)M.row[i].getElement()).colm, insData);
					hold.row[i].append(insEntry);
					M.row[i].moveNext();
				}
			}
			if(row[i].getIndex() == -1 && M.row[i].getIndex() != -1) //if this finished walking, but not M
			{
				while(M.row[i].getIndex() != -1)
				{
					insData = 0 - ((Entry)M.row[i].getElement()).data;
					Entry insEntry = new Entry(((Entry)M.row[i].getElement()).colm, insData);
					hold.row[i].append(insEntry);
					M.row[i].moveNext();
				}
			}
			else if(row[i].getIndex() != -1 && M.row[i].getIndex() == -1) //if M finished walking, but not 'this'
			{
				while(row[i].getIndex() != -1)
				{
					Entry insEntry = new Entry(((Entry)row[i].getElement()).colm, ((Entry)row[i].getElement()).data);
					hold.row[i].append(insEntry);
					row[i].moveNext();
				}
			}
		}
		return hold;
	}
	Matrix transpose()// returns a new Matrix that is the transpose of this Matrix
	{
		Matrix hold = new Matrix(getSize());
		for(int i=1; i<getSize()+1; i++)
		{
			if(row[i].length() > 0)
			{
				row[i].moveTo(0);
				while(row[i].getIndex() != -1)
				{
					Entry insEntry = new Entry(i, ((Entry)row[i].getElement()).data);
					hold.row[((Entry)row[i].getElement()).colm].append(insEntry);
					row[i].moveNext();
				}
			}
		}
		return hold;
	}
	 // returns a new Matrix that is the product of this Matrix with M
	 // pre: getSize()==M.getSize()
	Matrix mult(Matrix M)
	{
		if(getSize() != M.getSize())
			throw new RuntimeException("Precondition Not Met");
		Matrix hold = new Matrix(getSize());
		Matrix M_transpose = M.transpose();

		for(int i=1; i<getSize()+1; i++)
		{
			if(row[i].length() > 0)
			{
				row[i].moveTo(0);
				
				List left = new List();
				while(row[i].getIndex() != -1)
				{
					left.append(row[i].getElement());
					row[i].moveNext();
				}
				
				for(int j=1; j<getSize()+1; j++)
				{
					List right = new List();
					if(M_transpose.row[j].length() > 0)
						M_transpose.row[j].moveTo(0);
					while(M_transpose.row[j].getIndex() != -1)
					{
						right.append(M_transpose.row[j].getElement());
						M_transpose.row[j].moveNext();
					}
					
					if(left.length() > 0 && right.length() > 0)
					{
						double dotHold = dot(left,right);
						if(dotHold != 0)
						{
							Entry insEntry = new Entry( j , dotHold);
							hold.row[i].append(insEntry);
						}
					}
					
				}
			}
//				System.out.println("Press Any Key To Continue...");
//				new java.util.Scanner(System.in).nextLine();
		}
		return hold;
	}
	private static double dot(List P, List Q)
	{
		double hold = 0;
		P.moveTo(0); //no pre condition because if P or Q were empty, dot will never be called to begin with.
		Q.moveTo(0);
		while(P.getIndex() != -1 && Q.getIndex() != -1)
		{
			if(((Entry)P.getElement()).colm == ((Entry)Q.getElement()).colm)
			{
				hold += ((Entry)P.getElement()).data * ((Entry)Q.getElement()).data;
				P.moveNext();
				Q.moveNext();
			}
			else if(((Entry)P.getElement()).colm < ((Entry)Q.getElement()).colm)
				P.moveNext();
			else if (((Entry)P.getElement()).colm > ((Entry)Q.getElement()).colm)
				Q.moveNext(); 
			else
				throw new RuntimeException("Error in dot");
		}
		return hold;
	}
	// Other functions--------------------------------------------------------
//	public String toString(PrintWriter out) // overrides Object's toString() method
//	{
//		out.println();
//		for(int i=0; i<getSize()+1; i++)
//		{
//			if(row[i].length() > 0)
//				out.println((i)+": "+row[i].toString());
//		}
//		out.println();
//		return "";
//	}
	public String toString() // overrides Object's toString() method
	{
		String H = new String();
		for(int i=0; i<getSize()+1; i++)
		{
			if(row[i].length() > 0)
				H += i+": "+row[i].toString()+"&";
		}
		
		return H;
	}
}
