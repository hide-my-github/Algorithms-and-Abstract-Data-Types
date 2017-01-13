// Student: Josh, Tsung Chi, Shih
// CruzID/CruzID#: jtshih/1436140
// Class: CMPS 101
// Date: 1/23/2015
// Filename: Lex.c

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include "List.h"

#define MAX_LEN 160

int main(int argc, char * argv[]){

   int count=0;
   FILE *in, *out;
   char line[MAX_LEN];
   char* token;
   char hold[MAX_LEN][MAX_LEN];
   List list = newList();

   // check command line for correct number of arguments
   if( argc != 3 ){
      printf("Usage: %s <input file> <output file>\n", argv[0]);
      exit(1);
   }

   // open files for reading and writing 
   in = fopen(argv[1], "r");
   out = fopen(argv[2], "w");
   if( in==NULL ){
      printf("Unable to open file %s for reading\n", argv[1]);
      exit(1);
   }
   if( out==NULL ){
      printf("Unable to open file %s for writing\n", argv[2]);
      exit(1);
   }

   /* read each line of input file, then count and print tokens */
   while( fgets(line, MAX_LEN, in) != NULL)  {
      count++;
      token = strtok(line, " \n");
      strcpy(hold[count-1], token);
   }
   hold[count][0] = '\0';
   append(list, 0);
      for(int i=1; i<count; i++)
	{
	    	moveTo(list, 0);
	    	for(;;)
	    	{
	    		if(strcmp(hold[i], hold[getElement(list)]) < 0) //goes through entire list
		    	{
		    		if(getIndex(list)==0)
		    			prepend(list, i);
		    		else
		    			insertBefore(list, i);
		    		break;
		    	}
	    		else
	    		{
	    			moveNext(list);
	    			if(getIndex(list) == -1)
	    			{
	    				append(list, i);
	    				break;
	    			}
	    		}
	    	}
	    }

   for(int i=0; i<count; i++){
      moveTo(list, i);
      fprintf(out, "%s\n", hold[getElement(list)]);
   }
   printList(stdout, list);
   printf("\n");
   /* close files */
   fclose(in);
   fclose(out);

   return(0);
}