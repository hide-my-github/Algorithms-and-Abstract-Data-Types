
#include<stdio.h>
#include<stdlib.h>
#include"List.h"

int main(int argc, char* argv[]){
	List A = newList();
	List B = newList();
	
	for(int i=1; i<=10; i++){
		append(A,i);
	}
	for(int j=1; j<10; j++){
		append(B,j);
	}
	printf("A: ");
	printList(stdout,A);
	printf("B: ");
	printList(stdout,B);
	printf("length(A): %d\n", length(A));

//	printf("HI");
	moveTo(A,2);
//	printf("BYE");
	printList(stdout,A);
	printf("getIndex(A): %d\n", getIndex(A));
	printf("front(A): %d\n", front(A));
	printf("back(A): %d\n", back(A));
	printf("getElement(A): %d\n", getElement(A));
	printf("equals(A,B): %d\n", equals(A,B));
	
	clear(A);
	printf("length(A): %d\n", length(A));
	printf("B: ");
	printList(stdout,B);
	printf("moveNext(B) x1\n");
	printf("B: ");
	printList(stdout,B);
	printf("moveTo(B,1)\n");
	moveTo(B,1);
	printf("B: ");
	printList(stdout,B);
	printf("moveNext(B) x4\n");
	moveNext(B);
	moveNext(B);
	moveNext(B);
	moveNext(B);
	printf("B: ");
	printList(stdout,B);
	printf("movePrev(B) x2\n");
	movePrev(B);
	movePrev(B);
	printf("B: ");
	printList(stdout,B);
	printf("prepending 91 and 92 to B\n");
	prepend(B,91);
	prepend(B,92);
	printf("B: ");
	printList(stdout,B);
	
	printf("insertAfter(B,55)\n");
	insertAfter(B,55);
	printf("B: ");
	printList(stdout,B);
	printf("insertBefore(B,44)\n");
	insertBefore(B,44);
	printf("B: ");
	printList(stdout,B);
	
	printf("deleteBack(B) x4\n");
	deleteBack(B);
	deleteBack(B);
	deleteBack(B);
	deleteBack(B);
	printf("B: ");
	printList(stdout,B);

	printf("delete(B)\n");
	delete(B);
	printf("B: ");
	printList(stdout,B);
	printf("moveTo(B,4) AND delete()\n");
	moveTo(B,4);
	delete(B);
	printf("B: ");
	printList(stdout,B);	
	
	printf("copied B to C\n");
	List C = copyList(B);
	printf("C: ");
	printList(stdout,C);
	
	printf("concat B & C to A\n");
	A = concatList(B,C);
	printf("A: ");
	printList(stdout,A);

	freeList(&A);
	freeList(&B);
	freeList(&C);
	printf("END END END\n");
   	return(0);
}
