// Student: Josh, Tsung Chi, Shih
// CruzID/CruzID#: jtshih/1436140
// Class: CMPS 101
// Date: 3/10/2015
// Filename: GraphTest.c

#include<stdio.h>
#include<stdlib.h>
#include"List.h"
#include"Graph.h"


int main(int argc, char* argv[]){
    int i, n=8;
    List S = newList();
    Graph G = newGraph(n);
    Graph T=NULL, C=NULL;

    for(i=1; i<=n; i++) append(S, i);

    addArc(G, 1,2);
    addArc(G, 2,4);
    addArc(G, 3,1);
    addArc(G, 4,1);
    addArc(G, 4,5);
    addArc(G, 4,6);
    addArc(G, 5,2);
    addArc(G, 5,7);
    addArc(G, 6,4);
    addArc(G, 7,3);
    addArc(G, 7,6);
	printGraph(stdout, G);
 
	printf("\n");
	printf("Size: %d\n", getSize(G));
	printf("Order: %d\n", getOrder(G));
	
	DFS(G, S);
    printf("\n");
    printf("x:  d  f  p\n");
    for(i=1; i<=n; i++){
       printf("%d: %2d %2d %2d\n", i, getDiscover(G, i), getFinish(G, i), getParent(G, i));
    }
    printf("\n");
    printList(stdout, S); //should be 8 1 2 4 5 7 6 3
    printf("\n");
	
    printf("\n");
	T = transpose(G);
    printf("\n");
	printGraph(stdout, T);
	
	C = copyGraph(G);
    printf("\n");
	printGraph(stdout, C);
    printf("\n");
	
	makeNull(C);
    printf("\n");
	printGraph(stdout, C);
    printf("\n");
	printf("Size: %d\n", getSize(C));
	printf("Order: %d\n", getOrder(C));
	
    freeList(&S);
    freeGraph(&G);
    freeGraph(&T);
    freeGraph(&C);
    return(0);
}

