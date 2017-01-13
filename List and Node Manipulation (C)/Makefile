#// Student: Josh, Tsung Chi, Shih
#// CruzID/CruzID#: jtshih/1436140
#// Class: CMPS 101
#// Date: 1/23/2015
#// Filename: Makefile

#------------------------------------------------------------------------------
# Makefile for CMPS 101 Programming Assignment 2
#
#       make                   makes Lex
#       make ListClient        makes ListClient
#
#------------------------------------------------------------------------------
Lex : Lex.o List.o
	gcc -o Lex Lex.o List.o
	
checkLex : Lex
	valgrind --leak-check=full Lex in3 junk3
	
Lex.o : Lex.c List.h
	gcc -c -std=c99 -Wall Lex.c

ListClient: ListClient.o List.o
	gcc -o ListClient ListClient.o List.o

ListClient.o : ListClient.c List.h
	gcc -c -std=c99 -Wall ListClient.c

List.o : List.h List.c
	gcc -c -std=c99 -Wall List.c

clean :
	rm -f Lex ListClient Lex.o ListClient.o List.o ListTest ListTest.o
 
