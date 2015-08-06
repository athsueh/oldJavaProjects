# Alan Hsueh, athsueh@ucsc.edu

JAVASRC    = GameOfLife.java Grid.java Cell.java
ALLSOURCE  = ${JAVASRC} Makefile README.txt
MAINCLASS  = GameOfLife
CLASSES    = ${JAVASRC:.java=.class}
JARCLASSES = ${CLASSES}
JARFILE    = GameOfLife
SUBMITDIR  = cmps101-mw.w13 prog1

all : ${JARFILE}

${JARFILE} : ${CLASSES}
	echo Main-class: ${MAINCLASS} >Manifest
	jar cvfm ${JARFILE} Manifest ${JARCLASSES}
	- rm Manifest
	chmod +x ${JARFILE}

%.class : %.java
	javac $<

clean :
	- rm ${JARCLASSES}

spotless : clean
	- rm ${JARFILE}

ci : ${ALLSOURCE}
	checksource ${ALLSOURCE}
	cid + ${ALLSOURCE}

submit : ${ALLSOURCE}
	submit ${SUBMITDIR} ${ALLSOURCE}

again : ${ALLSOURCE}
	make spotless ci all lis
