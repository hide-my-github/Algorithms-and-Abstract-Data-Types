# Makefile for CMPS 101 pa1
#// Student: Josh, Tsung Chi, Shih
#// CruzID/CruzID#: jtshih/1436140
#// Class: CMPS 101
#// Date: 3/10/2015
#// Filename: Makefile

#------------------------------------------------------------------------------
#  Makefile for CMPS 101 Programming Assignment 5
#
#  make                     makes FindComponents
#  make GraphTest			makes GraphTest
#  make GraphClient         makes GraphClient
#  make clean               removes binaries
#  make checkFind           tests FindComponents for memory leaks on in3
#  make checkTest			tests GraphTest for memory leaks
#  make checkClient         tests GraphClient for memory leaks
#------------------------------------------------------------------------------

BASE_SOURCES   = Graph.c List.c
BASE_OBJECTS   = Graph.o List.o
HEADERS        = Graph.h List.h
COMPILE        = gcc -c -std=c99 -Wall
LINK           = gcc -o
REMOVE         = rm -f
MEMCHECK       = valgrind --leak-check=full

FindComponents : FindComponents.o $(BASE_OBJECTS)
	$(LINK) FindComponents FindComponents.o $(BASE_OBJECTS)

GraphClient : GraphClient.o $(BASE_OBJECTS)
	$(LINK) GraphClient GraphClient.o $(BASE_OBJECTS)
	
GraphTest : GraphTest.o $(BASE_OBJECTS)
	$(LINK) GraphTest GraphTest.o $(BASE_OBJECTS)

FindComponents.o : FindComponents.c $(HEADERS)
	$(COMPILE) FindComponents.c

GraphClient.o : GraphClient.c $(HEADERS)
	$(COMPILE) GraphClient.c

$(BASE_OBJECTS) : $(BASE_SOURCES) $(HEADERS)
	$(COMPILE) $(BASE_SOURCES)

clean :
	$(REMOVE) FindComponents GraphClient FindComponents.o GraphClient.o $(BASE_OBJECTS)

checkFind : FindComponents
	$(MEMCHECK) FindComponents in3 junk3

checkClient : GraphClient
	$(MEMCHECK) GraphClient
	
checkTest : GraphTest
	$(MEMCHECK) GraphTest