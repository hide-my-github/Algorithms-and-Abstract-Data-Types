// Student: Josh, Tsung Chi, Shih
// CruzID/CruzID#: jtshih/1436140
// Class: CMPS 101
// Date: 1/23/2015
// Filename: List.c


#include<stdio.h>
#include<stdlib.h>
#include "List.h"

//structs

//private NodeObj type
typedef struct NodeObj{
	int data;
	struct NodeObj* next;
	struct NodeObj* prev;
} NodeObj;

// private Node type
typedef NodeObj* Node;

// private ListObj type
typedef struct ListObj{
   Node front;
   Node back;
   Node cursor;
   int cursorIndex;
} ListObj;

//constructors-destructors

// newNode()
// Returns reference to new Node object. Initializes next and data fields.
// Private.
Node newNode(int data){
   Node N = malloc(sizeof(NodeObj));
   N->data = data;
   N->next = NULL;
   N->prev = NULL;
   return(N);
}

// freeNode()
// Frees heap memory pointed to by *pN, sets *pN to NULL.
// Private.
void freeNode(Node* pN){
   if( pN!=NULL && *pN!=NULL ){
      free(*pN);
      *pN = NULL;
   }
}

//newList()
//Returns reference to the new empty List object.
List newList(void){
	List L;
	L = malloc(sizeof(ListObj));
	L->front = L->back = L->cursor = NULL;
	return(L);
}

// freeList()
// Frees all heap memory associated with List *pL, and sets *pl to NULL.S
void freeList(List* pL){
   if(pL!=NULL && *pL!=NULL) { 
      while(length(*pL) != 0) { 
         deleteFront(*pL); 
      }
      free(*pL);
      *pL = NULL;
   }
}

//Access functions -----------------------------------------------------------
int length(List L){
   if( L->front ==NULL ){
      return 0;
   }
   int i=0;
   Node walker = L->front;
   i = 1;
   while(walker != L->back)
   {
      walker = walker->next;
      i++;
   }
   return i;
}

int getIndex(List L){
   if(L->cursor == NULL)
      return -1;
   else{
      Node N = L->front;
      int i = 0;
      while(N != L->cursor)
      {
         i++;
	 N = N->next;
      }
      return i;
   }
}

int front(List L){
   if(length(L) < 0){
      printf("front() error! Empty List!\n");
      exit(1);
   }
   return L->front->data;
}

int back(List L){
   if(length(L) < 0){
      printf("back() error! Empty List!\n");
      exit(1);
   }
   return L->back->data;
}

int getElement(List L){
   if(length(L) < 0){
      printf("getElement() error! Empty List!\n");
      exit(1);
   }
   if(L->cursor == NULL){
      printf("getElement() error! Cursor isn't on the list!\n");
      exit(1);
   }
   return L->cursor->data;
}

int equals(List A, List B){
   Node aWalker = A->front;
   Node bWalker = B->front;
   while(aWalker != NULL && bWalker != NULL){
      if(aWalker->data != bWalker->data)
         return 0;
      aWalker = aWalker->next;
      bWalker = bWalker->next;
   }
   if(bWalker != NULL)
      return 0;
   if(aWalker != NULL)
      return 0;
   return 1;
}

//Manipulation functions------------------------------------------------------
void clear(List L){
   Node N = NULL;
   while(N!=L->back){
      deleteFront(L);
      N = L->front;
   }
   if(N==L->back){
      L->front = L->back = L->cursor = NULL;
      freeNode(&N);
   }
   else{
      printf("List Error: calling clear()\n");
      exit(1);
   }
}
void moveTo(List L, int i){
   if(i < 0 || i >= length(L))
	L->cursor = NULL;
   else{
      Node N = L->front;
      for(int j = 0; j<i; j++){
         N = N->next;
      }
      L->cursor = N;
   }
}
void movePrev(List L){
   if(getIndex(L) == -1)
      return;
   moveTo(L, getIndex(L)-1);
}
void moveNext(List L){
   if(getIndex(L) == -1)
      return;
   moveTo(L, getIndex(L)+1);
}
void prepend(List L, int data){
   Node N = newNode(data);
   if (length(L) == 0){
      L->front = L->back = N;
   }
   else{
      N->next = L->front;
      L->front->prev = N;
      L->front = N;
   }
}
void append(List L, int data){
   Node N = newNode(data);
   
   if (length(L) == 0){
      L->front = L->back = N;
   }
   else{
      L->back->next = N;
      N->prev = L->back;
      L->back = N;
   }
}
void insertBefore(List L, int data){
   if(getIndex(L)==-1){
      printf("Cursor does not exist! Can't insertBefore()\n");
      exit(1);
   }
   if(length(L)<=0){
      printf("List does not exist! Can't insertBefore()\n");
      exit(1);
   }
   if(L->cursor == L->front){
      prepend(L, data);
      return;
   }
   Node N = newNode(data);
   Node before = L->cursor->prev;
   before->next = N;
   N->prev = before;
   N->next = L->cursor;
   L->cursor->prev = N;
}
void insertAfter(List L, int data){
   if(getIndex(L)==-1){
      printf("Cursor does not exist! Can't insertAfter()\n");
      exit(1);
   }
   if(length(L)<=0){
      printf("List does not exist! Can't insertAfter()\n");
      exit(1);
   }
   if(L->cursor == L->back){
      append(L, data);
      return;
   }
   Node N = newNode(data);
   Node after = L->cursor->next;
   L->cursor->next = N;
   N->prev = L->cursor;
   N->next = after;
   after->prev = N;
}
void deleteFront(List L){
   Node N = NULL;
   if( length(L) <= 0 ){
      printf("Empty List deleteFront() Error!\n");
      exit(1);
   }
   N = L->front;
   if(L->cursor == N)
      L->cursor = NULL;
   if( length(L)>1 ) {
      L->front = L->front->next; 
      L->front->prev = NULL;
   }
   else { 
      L->front = L->back = NULL;
   }

   freeNode(&N);
}
void deleteBack(List L){
   Node N = NULL;
   if( length(L) <= 0 ){
      printf("Empty List deleteFront() Error!\n");
      exit(1);
   }
   N = L->back;
   if(L->cursor == N)
      L->cursor = NULL;
   if(length(L)>1){
      L->back = L->back->prev;
      L->back->next = NULL;
   }
   else{
      L->back = L->front = NULL;
   }
   freeNode(&N);
}
void delete(List L){
   if(getIndex(L)==-1){
      printf("Cursor does not exist! Can't delete()\n");
      exit(1);
   }
   if(length(L)<=0){
      printf("List does not exist! Can't delete()\n");
      exit(1);
   }
   Node before = L->cursor->prev;
   before->next = L->cursor->next;
   L->cursor->next->prev = before;
   L->cursor->next = L->cursor->prev = NULL;
   Node N = L->cursor;
   freeNode(&N);
   L->cursor = NULL;   
}
//Other operations -----------------------------------------------------------
void printList(FILE* out, List L){
   Node N = NULL;
   for(N = L->front; N != NULL; N = N->next){
//      if(N == L->cursor)
//         printf(">");
      printf("%d ", N->data);
   }
}
List copyList(List L){
   List T = newList();
   Node N = L->front;
   for(int i=0; i<length(L); i++){
      append(T, N->data);
      N = N->next;
   }
   return T;
}
List concatList(List A, List B){
   List T = newList();
   Node N = A->front;
   Node Nb = B->front;
   for(int i=0; i<length(A)+length(B); i++){
      if(i<length(A)){
         append(T, N->data);
	 N = N->next;
      }
      else{
         append(T, Nb->data);
	 Nb = Nb->next;
      }
   }
   return T;
}