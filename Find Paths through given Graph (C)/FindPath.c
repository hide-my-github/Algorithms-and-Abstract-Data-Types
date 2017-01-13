// Student: Josh, Tsung Chi, Shih
// CruzID/CruzID#: jtshih/1436140
// Class: CMPS 101
// Date: 2/26/2015
// Filename: FindPath.c

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
	int n;
	int vertices;
	int verO;
	int verT;
	List L = newList();
	Graph G = NULL;

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
			phase = 1;
		}
	  else if(phase == 1){ //phase 1 begins. creating adj list
		sscanf(strHold, "%d %d", &verO, &verT);
		if(verO != 0 && verT != 0)
			addEdge(G, verO, verT);
		else{
			printGraph(out, G);
			phase = 2;
			fprintf(out, "\n");
		}
	  }
	  else{ //phase 2. getDist and getPath
		sscanf(strHold, "%d %d", &verO, &verT);
		if(verO == 0 && verT == 0)
			break;
		
		BFS(G, verO);
		n = getDist(G, verT);
		if(n == INF)
			fprintf(out, "The distance from %d to %d is infinity\n", verO, verT);
		else
			fprintf(out, "The distance from %d to %d is %d\n", verO, verT, n);
		getPath(L, G, verT);
		if(length(L) == 0) //would definitely prefer a isZero(L) function here I don't like manipulating the black box in client
			fprintf(out, "No %d-%d path exists\n", verO, verT);
		else{
			fprintf(out, "The shortest %d-%d path is: ", verO, verT);
			printList(out, L);
			fprintf(out, "\n");
			clear(L);
		}
		fprintf(out, "\n");
	  }
   }
    freeList(&L);
	freeGraph(&G);
	/* close files */
	fclose(in);
	fclose(out);

	return(0);
}