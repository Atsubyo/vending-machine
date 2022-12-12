JFLAGS = -g -d
JC = javac
JV = java
.SUFFIXES: .java .class

.java.class:
        $(JC) $(JFLAGS) $*.java

MAIN_CLASSES = \
        ./src/application/Main.java \
		./src/application/vendingData/*.java \
		./src/application/vendingItems/*.java \
		./src/application/vendingData/*.java \
		./src/application/vendingControllers/vendingController.java \
		./src/application/vendingUI/*.java \
		./src/application/consoleColors/ConsoleColors.java

DRIVER_CLASSES = \
        ./src/application/Main.java \
		./src/application/vendingData/*.java \
		./src/application/vendingItems/*.java \
		./src/application/vendingData/*.java \
		./src/application/vendingControllers/vendingController.java \
		./src/application/vendingUI/*.java \
		./src/application/consoleColors/ConsoleColors.java

APP_MAIN = Main

DRIVER_MAIN = Driver

default: main_classes

build-app: main_classes

build-driver: driver_classes

run-app: main_classes
	$(JV) $(MAIN)

main_classes: $(MAIN_CLASSES:.java=.class)

driver_classes: $(DRIVER_CLASSES:.java=.class)

clean:
        $(RM) *.class