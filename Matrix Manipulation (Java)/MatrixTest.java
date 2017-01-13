// Student: Josh, Tsung Chi, Shih
// CruzID/CruzID#: jtshih/1436140
// Class: CMPS 101
// Date: 2/9/2015
// Filename: MatrixTest.java
// Description: Test file for the functions of Matrix.java

public class MatrixTest {
	public static void main(String args[])
	{
		int n=10000;
	      Matrix A = new Matrix(n);
	      Matrix B = new Matrix(n);
	      
	      A.changeEntry(1,1,1); B.changeEntry(1,1,1);
	      A.changeEntry(1,2,2); B.changeEntry(1,2,0);
	      A.changeEntry(1,3,3); B.changeEntry(1,3,1);
	      A.changeEntry(2,1,4); B.changeEntry(2,1,0);
	      A.changeEntry(2,2,5); B.changeEntry(2,2,1);
	      A.changeEntry(2,3,6); B.changeEntry(2,3,0);
	      A.changeEntry(3,1,7); B.changeEntry(3,1,1);
	      A.changeEntry(3,2,8); B.changeEntry(3,2,1);
	      A.changeEntry(3,3,9); B.changeEntry(3,3,1);

	      
	      System.out.println(A.getNNZ());
	      System.out.println(A);
	      System.out.println(B.getNNZ());
	      System.out.println(B);
	      
	      
	      Matrix C = A.scalarMult(1.5);
	      System.out.println(C.getNNZ());
	      System.out.println(C);
	      
	      Matrix D = A.add(A);
	      System.out.println(D.getNNZ());
	      System.out.println(D);

	      Matrix E = A.sub(A);
	      System.out.println(E.getNNZ());
	      System.out.println(E);
	      
	      Matrix F = B.transpose();
	      System.out.println(F.getNNZ());
	      System.out.println(F);
	      
	      Matrix G = B.mult(B);
	      System.out.println(G.getNNZ());
	      System.out.println(G);
	      
	      Matrix H = A.copy();
	      System.out.println(H.getNNZ());
	      System.out.println(H);
	      System.out.println(A.equals(H));
	      System.out.println(A.equals(B));
	      System.out.println(A.equals(A));

	      A.makeZero();
	      System.out.println(A.getNNZ());
	      System.out.println(A);
	      
	      
	      //testing other parameters of changeEntry
      							B.changeEntry(2, 2, 0);
								B.changeEntry(2, 3, 20);
	      H.changeEntry(2, 2, 35);
	      						B.changeEntry(2, 2, 224);
	      						B.changeEntry(2, 1, 98);
	      H.changeEntry(1,3,76);
	      H.changeEntry(1,1,4);
	      H.changeEntry(1,1,96);
	      H.changeEntry(1,3,2);
	      
	      System.out.println(H.getNNZ());
	      System.out.println(H);
	      System.out.println(B.getNNZ());
	      System.out.println(B);
	      
	      //testing negative differences
	      Matrix Z = H.sub(B);
	      System.out.println(Z.getNNZ());
	      System.out.println(Z);
	      
	      //testing multiplying by 0
	      Matrix Y = Z.scalarMult(0);
	      System.out.println(Y.getNNZ());
	      System.out.println(Y);
	      
	      //testing transpose
	      Matrix X = Z.transpose();
	      System.out.println(X.getNNZ());
	      System.out.println(X);
	}
}
