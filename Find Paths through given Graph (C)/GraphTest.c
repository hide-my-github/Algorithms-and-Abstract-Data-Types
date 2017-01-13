// Student: Josh, Tsung Chi, Shih
// CruzID/CruzID#: jtshih/1436140
// Class: CMPS 101
// Date: 2/26/2015
// Filename: GraphTest.c

#include<stdio.h>
#include<stdlib.h>
#include"Graph.h"

int main(int argc, char* argv[]){
	int n = 12;
	Graph G = NULL;
	List L = newList();
	G = newGraph(n);
	//printf("order: %d\n", getOrder(G));
	//printf("size: %d\n", getSize(G));
addEdge(G, 1, 3);
addEdge(G, 1, 4);
addEdge(G, 2, 4);
addEdge(G, 3, 4);
addEdge(G, 3, 5);
addEdge(G, 4, 5);
addEdge(G, 5, 6);
addEdge(G, 7, 8);
addEdge(G, 7, 9);
addEdge(G, 7, 10);
addEdge(G, 9, 11);
addEdge(G, 9, 12);
addEdge(G, 11, 12);
//connecting two objects in Graph G
//addEdge(G, 6, 11);
	//printf("size: %d\n", getSize(G));
	printGraph(stdout, G);
	BFS(G, 5);
	n = getDist(G, 7);
	if(n == INF)
		printf("The distance from 5 to 7 is infinity\n");
	else
		printf("The distance from 5 to 7 is %d\n", n);
	getPath(L, G, 7);
	
	n = getDist(G, 1);
	if(n == INF)
		printf("The distance from 5 to 1 is infinity\n");
	else
		printf("The distance from 5 to 1 is %d\n", n);
	getPath(L, G, 1);
	
	n = getDist(G, 8);
	if(n == INF)
		printf("The distance from 5 to 8 is infinity\n");
	else
		printf("The distance from 5 to 8 is %d\n", n);
	getPath(L, G, 8);
	
	//printGraph(stdout, G);
	freeGraph(&G);
	freeList(&L);
	return (0);
}