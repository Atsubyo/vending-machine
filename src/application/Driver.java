package application;

import application.vendingItems.*;
import application.vendingData.*;

import java.util.ArrayList;


public class Driver {
	public static void main(String[] args) {
	    //load data from file
		String directoryFile = "directory_stock2";
		String inputFile = "input_all";
	    DataFile myData = new DataFile("../dataFiles/Directory/" + directoryFile + ".txt", 
	    							   "../dataFiles/Input/" + inputFile + ".txt");
	    
	    //initialize Vending machine with loaded data
	    ArrayList<String> myVending = myData.loadDirectory();
	    System.out.println();
	    ArrayList<Integer> mySelections = myData.loadSampleInput();
	    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Loading Vending Machine:");
	    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");

		// create machine and load items (from directory.txt)
	    Vending myMachine = new Vending(myVending);
		System.out.println();
	    
	    myMachine.loadItem(new Drink(8, "coffee", "Latte", 140, "Drink"));
	    myMachine.loadItem(new Drink(8, "coffee", "Latte", 140, "Drink"));
	    myMachine.loadItem(new Snack(10, false, "Cereal", 60, "Snack"));
	    myMachine.loadItem(new Snack(3, false, "Cheetos", 240, "Snack"));
	    System.out.println();
	    
	    // Test line to show items before removing initially
	    System.out.println("Vending Machine Original State:");
	    myMachine.displayItems();
	    
	    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Unloading Vending Machine:");
	    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
	    
	    // remove items (from input.txt)
	    myMachine.unloadItems(mySelections);
		System.out.println();

		// manually remove items
	    myMachine.unloadItem("Cheetos");
	    myMachine.unloadItem("Cheetos");
	    myMachine.unloadItem("Lays");
	    myMachine.unloadItem("AzT");
	    System.out.println();
	    
	    System.out.println("Vending Machine Final State: ");
	    //Final output to display after removing
	    myMachine.displayItems();
	}
}
