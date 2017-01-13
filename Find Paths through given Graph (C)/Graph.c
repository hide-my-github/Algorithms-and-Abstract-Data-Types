// Student: Josh, Tsung Chi, Shih
// CruzID/CruzID#: jtshih/1436140
// Class: CMPS 101
// Date: 2/26/2015
// Filename: Graph.c

#include <stdio.h>
#include <stdlib.h>
#include "Graph.h"
#include "List.h"

//structs

//private GraphObj type
typedef struct GraphObj{
	List* neighbors;
	int* color;
	int* parent;
	int* distance;
	int size; //# of edges in Graph
	int order; //# of vertices in Graph
	int source;
} GraphObj;

//typedef GraphObj* Graph;

/*** Constructors-Destructors ***/
Graph newGraph(int n){
	Graph G = malloc(sizeof(GraphObj));
	G->color = calloc(n+1, sizeof(int));
	G->parent = calloc(n+1, sizeof(int));
	G->distance = calloc(n+1, sizeof(int));
	G->size = 0;
	G->order = n;
	G->source = NIL;
	G->neighbors = calloc(n+1, sizeof(List));
	for(int i=0; i<=n; i++) //starts from 0 only because we need a holder there.
		G->neighbors[i] = newList();
	return (G);
}
void freeGraph(Graph* pG){
	if(pG!=NULL && *pG!=NULL){
		int hold = getOrder(*pG);
		makeNull(*pG);
		for(int i=0; i<=hold; i++)
			freeList(&((*pG)->neighbors[i]));
		free((*pG)->neighbors);
		free((*pG)->color);
		free((*pG)->parent);
		free((*pG)->distance);
		free(*pG);
		*pG = NULL;
	}
}

/*** Access functions ***/
int getSize(Graph G){
	return (G->size);
}
int getOrder(Graph G){
	return (G->order);
}
int getSource(Graph G){
	return (G->source);
}
int getParent(Graph G, int u){
	if(u < 1 || u > getOrder(G))
	{
		printf("getParent: u < 1 || u > getOrder(G)\n");
        exit(1);
	}
	return (G->parent[u]);
}
int getDist(Graph G, int u){
	if(u < 1 || u > getOrder(G))
	{
		printf("getDist: u < 1 || u > getOrder(G)\n");
        exit(1);
	}
	return (G->distance[u]);
}
void getPath(List L, Graph G, int u){
	if(getSource(G) == NIL)
	{
		printf("getPath: getSource(G) == NIL\n");
        exit(1);
	}
	if(u < 1 || u > getOrder(G))
	{
		printf("getPath: u < 1 || u > getOrder(G)\n");
        exit(1);
	}
	while(getParent(G, u) != NIL){
		prepend(L, u);
		u = getParent(G, u);
	}		
	if(u == G->source)
		prepend(L, u);
}

/*** Manipulation procedures ***/
void makeNull(Graph G){
	for(int i=1; i<=getOrder(G); i++){
		while(length(G->neighbors[i]) != 0)
			deleteBack(G->neighbors[i]);
	}
	G->size = 0;
}
void addEdge(Graph G, int u, int v){
	if(u < 1 || u > getOrder(G))
	{
		printf("addEdge: u < 1 || u > getOrder(G)\n");
        exit(1);
	}
	if(v < 1 || v > getOrder(G))
	{
		printf("addEdge: v < 1 || v > getOrder(G)\n");
        exit(1);
	}
	if(length(G->neighbors[u]) == 0)
		append(G->neighbors[u], v);
	else{
		moveTo(G->neighbors[u], 0);
		while(getIndex(G->neighbors[u]) != -1 && getElement(G->neighbors[u]) < v)
			moveNext(G->neighbors[u]);
		if(getIndex(G->neighbors[u]) == -1)
			append(G->neighbors[u], v);
		else
			insertBefore(G->neighbors[u], v);
	}
	

	if(length(G->neighbors[v]) == 0)
		append(G->neighbors[v], u);
	else{
		moveTo(G->neighbors[v], 0);
		while(getIndex(G->neighbors[v]) != -1 && getElement(G->neighbors[v]) < u)
			moveNext(G->neighbors[v]);
		if(getIndex(G->neighbors[v]) == -1)
			append(G->neighbors[v], u);
		else
			insertBefore(G->neighbors[v], u);
	}
	G->size++;
}
void addArc(Graph G, int u, int v){
	if(u < 1 || u > getOrder(G))
	{
		printf("addArc: u < 1 || u > getOrder(G)\n");
        exit(1);
	}
	if(v < 1 || v > getOrder(G))
	{
		printf("addArc: v < 1 || v > getOrder(G)\n");
        exit(1);
	}
	if(length(G->neighbors[u]) == 0)
		append(G->neighbors[u], v);
	else{
		moveTo(G->neighbors[u], 0);
		while(getIndex(G->neighbors[u]) != -1 && getElement(G->neighbors[u]) < v)
			moveNext(G->neighbors[u]);
		if(getIndex(G->neighbors[u]) == -1)
			append(G->neighbors[u], v);
		else
			insertBefore(G->neighbors[u], v);
	}
	G->size++;
}
void BFS(Graph G, int s){
	G->source = s;
	int hold;
	for(int i=1; i <= getOrder(G); i++){
		if(i != s){
			G->color[i] = 0; //0 is white
			G->distance[i] = INF;
			G->parent[i] = NIL;
		}
	}
	G->color[s] = 1;
	G->distance[s] = 0;
	G->parent[s] = NIL; 
	List Q = newList(); //initiate Queue
	append(Q, s); //enqueue(Q, s)
	while(length(Q) != 0){ //while Q != zero set
		hold = front(Q); //u = Dequeue(Q)
		deleteFront(Q);
		if(length(G->neighbors[hold]) != 0)
			moveTo(G->neighbors[hold], 0);
		while(getIndex(G->neighbors[hold]) != -1){ //for each v in u.neighbors
			if(G->color[getElement(G->neighbors[hold])] == 0){ //if v.color == white
				G->color[getElement(G->neighbors[hold])] = 1; //v.color = gray
				G->distance[getElement(G->neighbors[hold])] = G->distance[hold] + 1; //v.d = u.d + 1
				G->parent[getElement(G->neighbors[hold])] = hold; //v.parent = u
				append(Q, getElement(G->neighbors[hold])); //enqueue(Q, v)
			}
			moveNext(G->neighbors[hold]);
		}
		G->color[hold] = 2;
	}
	freeList(&Q);
}


/*** Other operations ***/
void printGraph(FILE* out, Graph G){
	for(int i=1; i<=getOrder(G); i++)
	{
		fprintf(out, "%d: ",i);
		if(length(G->neighbors[i]) != 0)
			printList(out ,G->neighbors[i]);
		fprintf(out, "\n");
	}
}

