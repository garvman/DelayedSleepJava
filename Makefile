JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	 DelayedSleep.java \
	 DelayedApplicationWindow.java \
	 DelayedApplicationLastChancePopup.java \

classes: $(CLASSES: .java=.class)


clean:
	$(RM) *.class *~
