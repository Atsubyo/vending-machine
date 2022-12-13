package controllers;

import application.App;
import vendingLogic.*;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.TextAlignment;
import javafx.util.Pair;

public class vendingController {
	@FXML Button item1, item2, item3, item4, item5, item6, item7, item8, restock_bt;
	@FXML Label qty1, qty2, qty3, qty4, qty5, qty6, qty7, qty8, item_out;
	@FXML ListView<String> lst_v;

	Vending myMachine;
	ObservableList<String> items = FXCollections.observableArrayList();

	ArrayList<Pair<Button, Label>> buttons = new ArrayList<>();

	public void initialize() {
		lst_v.setEditable(false);
		lst_v.setMouseTransparent(true);
		lst_v.setFocusTraversable(false);
		initializeData();
		initializeItemButtons();
	}

	public void initializeData() {
		String directoryFile;
		if (App.parameters.size() >= 1) {
			directoryFile = App.parameters.get(0);
		} else {
			directoryFile = "directory_good1";
		}
	    DataFile myData = new DataFile("../dataFiles/Directory/" + directoryFile + ".txt");
	    ArrayList<String> myVending = myData.loadDirectory();

	    myMachine = new Vending(myVending);
	    for (String item : myMachine.directory) {
			items.add(item);
		}

	    lst_v.setItems(items);
	}

	public void initializeItemButtons() {
		item1.setOnAction(e -> purchaseItem(0));
		item2.setOnAction(e -> purchaseItem(1));
		item3.setOnAction(e -> purchaseItem(2));
		item4.setOnAction(e -> purchaseItem(3));
		item5.setOnAction(e -> purchaseItem(4));
		item6.setOnAction(e -> purchaseItem(5));
		item7.setOnAction(e -> purchaseItem(6));
		item8.setOnAction(e -> purchaseItem(7));
		restock_bt.setOnAction(e -> restockItems());

		buttons.add(new Pair<>(item1, qty1));
		buttons.add(new Pair<>(item2, qty2));
		buttons.add(new Pair<>(item3, qty3));
		buttons.add(new Pair<>(item4, qty4));
		buttons.add(new Pair<>(item5, qty5));
		buttons.add(new Pair<>(item6, qty6));
		buttons.add(new Pair<>(item7, qty7));
		buttons.add(new Pair<>(item8, qty8));

		for (int i = 0; i < myMachine.directory.size(); ++i) {
			buttons.get(i).getKey().setText(myMachine.directory.get(i));
			buttons.get(i).getValue().setText(String.valueOf(myMachine.slots.get(i).size()));
			buttons.get(i).getKey().setTextAlignment(TextAlignment.CENTER);
			buttons.get(i).getValue().setTextAlignment(TextAlignment.CENTER);
			buttons.get(i).getKey().wrapTextProperty().setValue(true);
			buttons.get(i).getValue().wrapTextProperty().setValue(true);
		}
	}

	public void purchaseItem(int itemNum) {
		String itemName = myMachine.directory.get(itemNum);
		System.out.println("\nPurchasing " + itemName + "...");
		ArrayList<Integer> productList = myMachine.findProduct(itemName);
		if (productList.isEmpty()) {
			return;
		} else {
			myMachine.unloadItem(itemNum);
			int totalQuantity = 0;
			for (int i : productList) {
				int itemQuantity = myMachine.slots.get(i).size();
				buttons.get(i).getValue().setText(String.valueOf(itemQuantity));
				item_out.setText(itemName);
				if (itemQuantity <= 0) {
					buttons.get(i).getKey().setDisable(true);
				}
				totalQuantity += itemQuantity;
			}
			System.out.println("x" + totalQuantity + " " + itemName + " Remaining");
		}
	}

	public void restockItems() {
		myMachine.restock();
		for (int i = 0; i < myMachine.directory.size(); ++i) {
			buttons.get(i).getKey().setDisable(false);
			buttons.get(i).getValue().setText(String.valueOf(myMachine.slots.get(i).size()));
		}
	}
}
