# Makefile for CMPS 101 pa1
#// Student: Josh, Tsung Chi, Shih
#// CruzID/CruzID#: jtshih/1436140
#// Class: CMPS 101
#// Date: 2/9/2015
#// Filename: Makefile


#------------------------------------------------------------------------------
#  Makefile for CMPS 101 pa3
#  Creates an executable jar file called Sparse.  This makefile will compile 
#  all .java files in your current working directory.  
#------------------------------------------------------------------------------

MAINCLASS  = Sparse
JAVAC      = javac 
JAVASRC    = $(wildcard *.java)
SOURCES    = $(JAVASRC) Makefile README
CLASSES    = $(patsubst %.java, %.class, $(JAVASRC))
JARCLASSES = $(patsubst %.class, %*.class, $(CLASSES))
JARFILE    = $(MAINCLASS)
DEST       = cmps101-pt.w15 pa3
FILES      = README Makefile List.java ListTest.java Matrix.java MatrixTest.java Sparse.java

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