// Student: Josh, Tsung Chi, Shih
// CruzID/CruzID#: jtshih/1436140
// Class: CMPS 101
// Date: 3/10/2015
// Filename: Graph.c

#include <stdio.h>
#include <stdlib.h>
#include "Graph.h"

void visit(Graph G, int u, int* time, List S);

//structs
typedef struct GraphObj{
	List* neighbors;
	int* color;
	int* parent;
	int* discover;
	int* finish;
	int size; //# of edges in Graph
	int order; //# of vertices in Graph
	int source;
} GraphObj;


/*** Constructors-Destructors ***/
Graph newGraph(int n){
	Graph G = malloc(sizeof(GraphObj));
	G->color = calloc(n+1, sizeof(int));
	G->parent = calloc(n+1, sizeof(int));
	G->discover = calloc(n+1, sizeof(int));
	G->finish = calloc(n+1, sizeof(int));
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
		free((*pG)->discover);
		free((*pG)->finish);
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
int getParent(Graph G, int u){
	if(u < 1 || u > getOrder(G))
	{
		printf("getParent: u < 1 || u > getOrder(G)\n");
        exit(1);
	}
	return (G->parent[u]);
}
int getDiscover(Graph G, int u){
	if(u < 1 || u > getOrder(G))
	{
		printf("getDiscover: u < 1 || u > getOrder(G)\n");
        exit(1);
	}
	return (G->discover[u]);
}
int getFinish(Graph G, int u){
	if(u < 1 || u > getOrder(G))
	{
		printf("getFinish: u < 1 || u > getOrder(G)\n");
        exit(1);
	}
	return (G->finish[u]);
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
void DFS(Graph G, List S){
	if(length(S) != getOrder(G))
	{
		printf("DFS: length(S) != getOrder(G)\n");
        exit(1);
	}
	List hold = copyList(S);
	clear(S);
	for(int i=1; i <= getOrder(G); i++){
		G->color[i] = 0; //0 is white
		G->parent[i] = NIL;
	}
	int time = 0;
	moveTo(hold, 0);
	for(int i=1; i <= getOrder(G); i++){
		if(G->color[getElement(hold)] == 0){
			visit(G, getElement(hold), &time, S);
		}
		moveNext(hold);
	}
	freeList(&hold);
}
void visit(Graph G, int u, int* time, List S){
	(*time)++;
	G->discover[u] = *time;
	G->color[u] = 1;
	if(length(G->neighbors[u]) != 0)
	{
		moveTo(G->neighbors[u], 0);
		while(getIndex(G->neighbors[u]) != -1)
		{
			if(G->color[getElement(G->neighbors[u])] == 0)
			{
				G->parent[getElement(G->neighbors[u])] = u;
				visit(G, getElement(G->neighbors[u]), time, S);
			}
			moveNext(G->neighbors[u]);
		}
	}
	G->color[u] = 2;
	(*time)++;
	G->finish[u] = *time;
	prepend(S, u);
}
/*** Other operations ***/
Graph transpose(Graph G){
	Graph hold = newGraph(getOrder(G));
	for(int i=1; i<=getOrder(G); i++)
	{
		if(length(G->neighbors[i]) != 0)
			moveTo(G->neighbors[i], 0);
		while(getIndex(G->neighbors[i]) != -1)
		{
			append(hold->neighbors[getElement(G->neighbors[i])], i);
			moveNext(G->neighbors[i]);
		}
	}
	return hold;
}
Graph copyGraph(Graph G){
	Graph hold = newGraph(getOrder(G));
	for(int i=0; i<=getOrder(G); i++)
	{
		if(length(G->neighbors[i]) != 0)
			moveTo(G->neighbors[i], 0);
		while(getIndex(G->neighbors[i]) != -1)
		{
			append(hold->neighbors[i], getElement(G->neighbors[i]));
			moveNext(G->neighbors[i]);
		}
	}
	return hold;
}
void printGraph(FILE* out, Graph G){
	for(int i=1; i<=getOrder(G); i++)
	{
		fprintf(out, "%d: ",i);
		if(length(G->neighbors[i]) != 0)
			printList(out ,G->neighbors[i]);
		fprintf(out, "\n");
	}
}

