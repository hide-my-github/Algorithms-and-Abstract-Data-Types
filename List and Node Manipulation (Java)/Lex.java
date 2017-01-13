// Student: Josh, Tsung Chi, Shih
// CruzID/CruzID#: jtshih/1436140
// Class: CMPS 101
// Date: 1/14/2015
// Filename: Lex.java
// Description: Takes two arguments, inputFile and outputFile, respectively. Reads inputFile line by line and
//				makes/overwrites an outputFile of the inputFile that is Alphabetically sorted.

import java.io.*;
import java.util.Scanner;


public class Lex {
    static void processFile(String inFile, String outFile) throws IOException { //plain read file and put contents into ADT
    		
    	int numOfLines = 0;
    	//read file, put contents Alphabetically into ADT, then write to another file
    	Scanner scan = new Scanner (new File(inFile));
    	Scanner lineNum = new Scanner (new File(inFile));
    	PrintWriter out = new PrintWriter(new FileWriter(outFile));
        List list = new List();
        while(lineNum.hasNextLine())
        {
        	numOfLines++;
        	lineNum.nextLine();
        }
	    lineNum.close();
	    
    	String[] strArray = new String[numOfLines];
        for (int i = 0; i<numOfLines; i++) 
            strArray[i] = scan.nextLine();
        
	    list.append(0);
	    for(int i=1; i<numOfLines; i++)
	    {
	    	list.moveTo(0);
	    	for(;;)
	    	{
	    		if(strArray[i].compareTo(strArray[list.getIndex()]) < 0) //goes through entire list
		    	{
		    		if(list.getIndex()==0)
		    			list.prepend(i);
		    		else
		    			list.insertBefore(i);
		    		break;
		    	}
	    		else
	    		{
	    			list.moveNext();
	    			if(list.getIndex() == -1)
	    			{
	    				list.append(i);
	    				break;
	    			}
	    		}
	    	}
	    }

	    for(int i=0; i<numOfLines; i++)
	    {
	    	list.moveTo(i);
	    	out.println(strArray[list.getElement()]);
	    	System.out.print(strArray[list.getElement()]+" ");
	    }
	    System.out.println();
	    
		scan.close();
		out.close();
    }
    public static void main(String[] args) {
//    	String[] targs = new String[2];
//    	args[0] = "in.txt";
//    	args[1] = "out.txt";
	
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
}