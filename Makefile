<<<<<<< HEAD
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
=======
#  src/vendingLogic/DataFile.java src/vendingLogic/Drink.java src/vendingLogic/Item.java src/vendingLogic/Snack.java src/vendingLogic/Vending.java src/controllers/vendingController.java src/consoleColors/ConsoleColors.java
debug_classes: src/vendingLogic/*.java src/controllers/*.java src/consoleColors/*.java

arg1=directory_good1
arg2=input_all

build-debug: debug_classes
	javac -cp bin src/application/Debugger.java

run-debug: build-debug
	java -cp bin application/Debugger

run-debug-args: debug_classes
	java -cp bin application/Debugger $(arg1) $(arg2)

run-app:
	java --module-path "C:/JavaFX/javafx-sdk-19/lib" --add-modules=javafx.controls,javafx.fxml -jar VendingMachine.jar

default: run-app

clean: 
	$(RM) *.class
>>>>>>> master
