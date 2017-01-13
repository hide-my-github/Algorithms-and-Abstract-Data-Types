# Makefile for CMPS 101 pa1
#// Student: Josh, Tsung Chi, Shih
#// CruzID/CruzID#: jtshih/1436140
#// Class: CMPS 101
#// Date: 1/14/2015
#// Filename: Makefile
#// Description: Makefile for BST Program.

MAINCLASS 	= Lex
JAVAC 		= javac
JAVASRC 	= $(wildcard *.java)
SOURCES 	= $(JAVASRC) makefile README
CLASSES 	= $(patsubst %.java, %.class, $(JAVASRC))
JARCLASSES 	= $(patsubst %.class, %*.class, $(CLASSES))
JARFILE 	= $(MAINCLASS)
DEST       = cmps101-pt.w15 pa1
FILES      = README Makefile List.java ListClient.java Lex.java

all: $(JARFILE)
$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(JARCLASSES)
	chmod +x $(JARFILE)
	rm Manifest
%.class: %.java
	$(JAVAC) $<
clean:
	rm -f *.class $(JARFILE)
submit: ${FILES}
	submit ${DEST} ${FILES}

