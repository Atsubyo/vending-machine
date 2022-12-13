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
