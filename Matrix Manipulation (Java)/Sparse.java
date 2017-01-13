// Student: Josh, Tsung Chi, Shih
// CruzID/CruzID#: jtshih/1436140
// Class: CMPS 101
// Date: 2/9/2015
// Filename: Sparse.java
// Description: executable file to read/write txt files.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Sparse {
	public static void main(String[] args) {
//    	String[] targs = new String[2];
//    	targs[0] = "matrixIn.txt";
//    	targs[1] = "matrixOut.txt";
	
        String inputFile = "";
	try{
		inputFile = args[0];
	}catch (ArrayIndexOutOfBoundsException e){
         throw new RuntimeException("No input file detected");
      }
        String outputFile = "";
	try{
		outputFile = args[1];
	}catch (ArrayIndexOutOfBoundsException e){
         throw new RuntimeException("No output file detected");}

        try{
        	if(args[2] != null)
        	throw new IllegalArgumentException("Error! Too many arguments");
        }catch (ArrayIndexOutOfBoundsException e){}
        
        
        try {
			processFile(inputFile, outputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	static void processFile(String inFile, String outFile) throws IOException { //plain read file and put contents into ADT
		
    	//read file, put contents from least to greatest Column order into ADT, then write to another file
    	Scanner scan = new Scanner (new File(inFile));
    	PrintWriter out = new PrintWriter(new FileWriter(outFile));
    	String details = scan.nextLine();
    	String[] detailSegments = details.split(" ");
    	int n = Integer.parseInt(detailSegments[0]); //if n = 3 -> 3x3 Matrices
    	details = scan.nextLine(); //skip the break-line
    	Matrix A = new Matrix(n+1);
    	Matrix B= new Matrix(n+1);
    	Matrix decoy = A;
        int row, col;
        double data;
        String[] insertSegments;
        String[] H_deli;
    	details = scan.nextLine();
        while(scan.hasNextLine())
        {
        	if(details.isEmpty())
        	{
        		details = scan.nextLine();
        		decoy = B;
        		insertSegments = details.split(" ");
            	row = Integer.parseInt(insertSegments[0]);
            	col = Integer.parseInt(insertSegments[1]);
            	data = Double.parseDouble(insertSegments[2]);  
            	decoy.changeEntry(row, col, data);
        	}
        	else
        	{
	        	insertSegments = details.split(" ");
	        	row = Integer.parseInt(insertSegments[0]);
	        	col = Integer.parseInt(insertSegments[1]);
	        	data = Double.parseDouble(insertSegments[2]);  
	        	decoy.changeEntry(row, col, data);	
        	}
        	details = scan.nextLine();
        }
        insertSegments = details.split(" ");
    	row = Integer.parseInt(insertSegments[0]);
    	col = Integer.parseInt(insertSegments[1]);
    	data = Double.parseDouble(insertSegments[2]);
    	decoy.changeEntry(row, col, data);

    	out.println("A has "+A.getNNZ()+" non-zero entries:");
    	H_deli = A.toString().split("&");
    	for(int i=0; i < H_deli.length; i++)
    	{
    		if(!H_deli[i].isEmpty())
    			out.println(H_deli[i]);
    	}
    	out.println();

    	out.println("B has "+B.getNNZ()+" non-zero entries:");
    	H_deli = B.toString().split("&");
    	for(int i=0; i < H_deli.length; i++)
    	{
    		if(!H_deli[i].isEmpty())
    			out.println(H_deli[i]);
    	}
    	out.println();

    	out.println("(1.5)*A =");
    	H_deli = A.scalarMult(1.5).toString().split("&");
    	for(int i=0; i < H_deli.length; i++)
    	{
    		if(!H_deli[i].isEmpty())
    			out.println(H_deli[i]);
    	}
    	out.println();
    	
    	out.println("A+B =");
    	H_deli = A.add(B).toString().split("&");
    	for(int i=0; i < H_deli.length; i++)
    	{
    		if(!H_deli[i].isEmpty())
    			out.println(H_deli[i]);
    	}
    	out.println();

    	out.println("A+A =");
    	H_deli = A.add(A).toString().split("&");
    	for(int i=0; i < H_deli.length; i++)
    	{
    		if(!H_deli[i].isEmpty())
    			out.println(H_deli[i]);
    	}
    	out.println();

    	out.println("B-A =");
    	H_deli = B.sub(A).toString().split("&");
    	for(int i=0; i < H_deli.length; i++)
    	{
    		if(!H_deli[i].isEmpty())
    			out.println(H_deli[i]);
    	}
    	out.println();

    	out.println("A-A =");
    	H_deli = A.sub(A).toString().split("&");
    	for(int i=0; i < H_deli.length; i++)
    	{
    		if(!H_deli[i].isEmpty())
    			out.println(H_deli[i]);
    	}
    	out.println();

    	out.println("Transpose(A) =");
    	H_deli = A.transpose().toString().split("&");
    	for(int i=0; i < H_deli.length; i++)
    	{
    		if(!H_deli[i].isEmpty())
    			out.println(H_deli[i]);
    	}
    	out.println();

    	out.println("A*B =");
    	H_deli = A.mult(B).toString().split("&");
    	for(int i=0; i < H_deli.length; i++)
    	{
    		if(!H_deli[i].isEmpty())
    			out.println(H_deli[i]);
    	}
    	out.println();
    	
    	out.println("B*B =");
    	H_deli = B.mult(B).toString().split("&");
    	for(int i=0; i < H_deli.length; i++)
    	{
    		if(!H_deli[i].isEmpty())
    			out.println(H_deli[i]);
    	}
    	
		scan.close();
		out.close();
    }
}
