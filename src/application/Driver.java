package application;

import java.util.ArrayList;
import application.item.*;
import application.vendingData.DataFile;
import application.vendingData.Vending;

public class Driver {

	public static void main(String[] args) {
	    //load data from file
		String directoryFile = "directory_good2";
		String inputFile = "input_all";
	    DataFile myData = new DataFile("../dataFiles/Directory/" + directoryFile + ".txt", 
	    							   "../dataFiles/Input/" + inputFile + ".txt");
	    
	    //initialize Vending machine with loaded data
	    ArrayList <String> myVending = myData.loadDirectory();
	    System.out.println();
	    ArrayList <Integer> mySelections = myData.loadSampleInput();
	    System.out.println("________________________________________________________________________________________________________________________________________________");

	    Vending myMachine = new Vending(myVending);
	    
	    // manually adding some items with overloaded loadItem(<Item>)
	    myMachine.loadItem(new Drink(8, "coffee", "Latte", 140, "Drink"));
	    myMachine.loadItem(new Drink(8, "coffee", "Latte", 140, "Drink"));
	    myMachine.loadItem(new Snack(10, false, "Cereal", 60, "Snack"));
	    myMachine.loadItem(new Snack(3, false, "Cheetos", 240, "Snack"));
	    System.out.println();
	    
	    // Test line to show items before removing initially
	    System.out.println("Items originally there:");
	    // debug helper function, REALLY NEEDS toString()
	    myMachine.displayItems();
	    
	    System.out.println("________________________________________________________________________________________________________________________________________________");
	    
	    // remove items
	    myMachine.unloadItems(mySelections);
	    myMachine.unloadItem("Cheetos");
	    myMachine.unloadItem("Cheetos");
	    myMachine.unloadItem("Lays");
	    myMachine.unloadItem("AzT");
	    System.out.println();
	    
	    System.out.println("Items removed final count: ");

	    //Final output to display after removing
	    myMachine.displayItems(); //debug helper function, REALLY NEEDS toString()

	    /*****************/
	    // Above displayItems() call is fine, but the Vending machine's deconstructor
	    // should call that function since it's the LAST operation. Notice it could be
	    // done with a helper function that USES the toString()
	    // We will NOT call displayItems() in testing
	    /*****************/
	    
	}

}
