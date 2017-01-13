// Student: Josh, Tsung Chi, Shih
// CruzID/CruzID#: jtshih/1436140
// Class: CMPS 101
// Date: 2/9/2015
// Filename: ListTest.java 
// Description: Test file for the functions of List.java

public class ListTest {

	public static void main(String args[])
	{
		//i started this Test before attempting Matrix.java that is why it is in this format. 
		List row1 = new List();
		List row2 = new List();
		List row3 = new List();
		row1.append("1 1.0");
		row1.append("2 2.0");
		row1.append("3 3.0");
		row2.append("1 4.0");
		row2.append("2 5.0");
		row2.append("3 6.0");
		row3.append("1 7.0");
		row3.append("2 8.0");
		row3.append("3 9.0");
		
		List[] matrix = {row1, row2, row3};
		System.out.println("1: "+matrix[0].toString());
		System.out.println("2: "+matrix[1].toString());
		System.out.println("3: "+matrix[2].toString());
		
		//testing the ALTERED functions
		//front()
		System.out.println(matrix[0].front());
		
		//back()
		System.out.println(matrix[0].back());
		
		//moveTo() & getElement & getIndex
		matrix[0].moveTo(0);
		System.out.println("Element: "+matrix[0].getElement()+"; Index: "+matrix[0].getIndex());
		
		//moveNext() x2 and then movePrev()
		matrix[0].moveNext();
		matrix[0].moveNext();
		System.out.println("Element: "+matrix[0].getElement()+"; Index: "+matrix[0].getIndex());
		matrix[0].movePrev();
		System.out.println("Element: "+matrix[0].getElement()+"; Index: "+matrix[0].getIndex());
		
		//equals
		System.out.println(matrix[0].equals(row1));
		System.out.println(matrix[0].equals(row2));
		
		//insertBefore, keep in mind this does NOT insert numerically
		matrix[0].insertBefore("9 123.0");
		System.out.println("Element: "+matrix[0].getElement()+"; Index: "+matrix[0].getIndex());
		System.out.println("1: "+matrix[0].toString());
		
		//insertAfter, keep in mind this does NOT insert numerically
		matrix[0].insertAfter("10 321.0");
		System.out.println("Element: "+matrix[0].getElement()+"; Index: "+matrix[0].getIndex());
		System.out.println("1: "+matrix[0].toString());
		
		System.out.println();
		System.out.println("toString: "+matrix[0].toString());
		System.out.println("toString: "+matrix[1].toString());
		System.out.println("toString: "+matrix[2].toString());
	}
}
