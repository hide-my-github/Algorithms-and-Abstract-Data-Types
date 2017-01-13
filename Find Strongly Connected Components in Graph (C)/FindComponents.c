// Student: Josh, Tsung Chi, Shih
// CruzID/CruzID#: jtshih/1436140
// Class: CMPS 101
// Date: 3/10/2015
// Filename: FindComponents.c

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include "Graph.h"

#define MAX_LEN 100

int main(int argc, char * argv[]){

	int phase = 0;
	FILE *in, *out;
	char line[MAX_LEN];
	char strHold[5];
	int vertices;
	int verO;
	int verT;
	int n = 0;
	int count = 0;
	List L = newList();
	List hold = newList();
	Graph G = NULL;
	Graph T = NULL;

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

	while( fgets(line, MAX_LEN, in) != NULL)  {
		strcpy(strHold, line);
		if(phase==0) //first line of text file
		{
			sscanf(strHold, "%d", &vertices);
			G = newGraph(vertices);
			for(int i=1; i<= vertices; i++)
			{
				append(L, i);
			}
			phase = 1;
			fprintf(out, "Adjacency list representation of G:");
			fprintf(out, "\n");
}
		else{ //phase 2. getDist and getPath
			sscanf(strHold, "%d %d", &verO, &verT);
			if(verO != 0 && verT != 0)
				addArc(G, verO, verT);
			else{
				printGraph(out, G);
				break;
				fprintf(out, "\n");
			}
			
		}
	}
	
	DFS(G, L);
	T = transpose(G);
	DFS(T, L);
	moveTo(L, 0);
	for(int i=1; i<= length(L); i++)
	{
		if(getParent(T, getElement(L)) == NIL)
			count++;
		moveNext(L);
	}
	fprintf(out, "\n");
	fprintf(out, "G contains %d strongly connected components:", count);
	moveTo(L, 0);
	for(int i=1; i<=length(L); i++)
	{
		if(getParent(T, getElement(L)) == NIL)
		{
			prepend(hold, getElement(L));
			moveTo(hold, 0);
		}
		else
		{
			insertAfter(hold, getElement(L));
			moveNext(hold);
		}
		moveNext(L);
	}
	moveTo(hold,0);
	for(int i=1; i<=length(hold); i++)
	{
		if(getParent(T, getElement(hold)) == NIL){
			fprintf(out, "\n");
			fprintf(out, "Component %d:",++n);
			fprintf(out, " %d ", getElement(hold));
		}
		else
		{
			fprintf(out, "%d ", getElement(hold));
		}
		moveNext(hold);
	}
	
    freeList(&L);
	freeList(&hold);
	freeGraph(&G);
	freeGraph(&T);
	/* close files */
	fclose(in);
	fclose(out);

	return(0);
}