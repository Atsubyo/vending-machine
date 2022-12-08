package application.vendingData;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import application.item.Drink;
import application.item.Item;
import application.item.Snack;

public class Vending {
	
	public ArrayList<String> directory;
	public ArrayList<Item> itemStock;
	public ArrayList<Queue<Item>> slots;
	private int maxCap;
	
	// for testing only
	private void printData(String[] data) {
		for (String str: data) {
			System.out.print(str + ", ");
		}
		System.out.println();
	}
	
	public Vending(ArrayList<String> data) {
		directory = new ArrayList<String>();
		slots = new ArrayList<Queue<Item>>();
		itemStock = new ArrayList<Item>();
		maxCap = 8;
		loadItem(data);
	}
	public Vending(ArrayList<String> data, int maxCap) {
		directory = new ArrayList<String>();
		slots = new ArrayList<Queue<Item>>();
		itemStock = new ArrayList<Item>();
		this.maxCap = maxCap;
		loadItem(data);
	}
	
	public Item parseData(String data) {
		String[] dataArr = data.split(", ");
//		printData(dataArr);
		Item newItem = null;
		if (dataArr.length > 0) {
			if (dataArr[0].equals("Drink")) {
				newItem = new Drink(Float.parseFloat(dataArr[3]), dataArr[4], dataArr[1], Float.parseFloat(dataArr[2]), dataArr[0], Integer.parseInt(dataArr[5]));
			} else if (dataArr[0].equals("Snack")) {
				newItem = new Snack(Float.parseFloat(dataArr[3]), Boolean.parseBoolean(dataArr[4]), dataArr[1], Float.parseFloat(dataArr[2]), dataArr[0], Integer.parseInt(dataArr[5]));
			} else {
				System.out.println("parseData: Invalid Item Type: " + dataArr[0] + "\nExiting Program...");
				System.exit(0);
			}
		}
		return newItem;
	}
	
	// overloaded debugging load
	public void loadItem(Item newItem) {
		System.out.println("Manually Loading: " + newItem);
		String itemName = newItem.getName();
		ArrayList<Integer> productList = findProduct(itemName);
		if (productList.isEmpty()) {
			if (slots.size() >= maxCap) {
				System.out.println("Max Capacity Reached: [" + maxCap + "]");
				System.out.println(itemName + " Not Loaded Into Machine...");
				return;
			}
			directory.add(newItem.getName());
			itemStock.add(newItem);
			Queue<Item> q = new LinkedList<Item>();
			q.add(newItem);
			slots.add(q);
		} else {
			int min = Integer.MAX_VALUE;
			for (int i : productList) {
				Queue<Item> q = slots.get(i);
				if (q.size() == 0) {
					q.add(newItem);
				} else {
					if (q.size() < min) {
						min = i;
					}
				}
			}
			slots.get(min).add(newItem);			
		}
	}
	
	public void loadItem(ArrayList<String> data) {
		int idx = 0;
		for (String str : data) {
			if (slots.size() >= maxCap) {
				System.out.println("Max Capacity Reached: " + maxCap);
				System.out.println(data.size() - idx + " Item(s) Not Loaded Into Machine...");
				break;
			}
			++idx;
			Item newItem = parseData(str);
			directory.add(newItem.getName());
			itemStock.add(newItem);
			Queue<Item> q = new LinkedList<Item>();
			for (int i = 0; i < newItem.getItemCount(); ++i) {
				q.add(newItem);
			}
			System.out.println("File Loading: x" + newItem.getItemCount() + " " + newItem.getName());
			slots.add(q);
		}
	}
	
	// overloaded debugging unload
	public void unloadItem(String itemName) {
		System.out.println("Manually Unloading: " + itemName);
		ArrayList<Integer> productList = findProduct(itemName);
		if (productList.isEmpty()) {
			System.out.println("No Existing [" + itemName + "] | Nothing was Unloaded...");
		} else {
			int max = Integer.MIN_VALUE;
			int maxIndex = 0;
			for (int i : productList) {
				Queue<Item> q = slots.get(i);
				if (!q.isEmpty()) {
					if (q.size() > max) {
						max = q.size();
						maxIndex = i;
					}
				}
			}
			if (slots.get(maxIndex).isEmpty()) {
				System.out.println("No Existing [" + itemName + "] | Nothing was Unloaded...");
			} else {
				System.out.println("Successfully Unloaded " + itemName + "...");
				slots.get(maxIndex).remove();
			}
		}
	}
	
	private void unloadItems(int index) {
		if (index >= directory.size()) {
			System.out.println("File Unloading: Invalid Index: [" + index + "] | Nothing was Unloaded...");
			return;
		}
		String itemName = directory.get(index);
		ArrayList<Integer> productList = findProduct(itemName);
		for (int i : productList) {
			Queue<Item> q = slots.get(i);
			if (!q.isEmpty()) {
				System.out.println("File Unloading: " + itemName);
				q.remove();
				return;
			}
		}
		System.out.println("File Unloading: No Existing [" + itemName + "] | Nothing was Unloaded...");
	}
	
	public void unloadItems(ArrayList<Integer> inputs) {
		for (int input : inputs) {
			unloadItems(input);
		}
	}
	
	public ArrayList<Integer> findProduct(String itemName) {
		ArrayList<Integer> productList = new ArrayList<Integer>();
		for (int i = 0; i < slots.size(); ++i) {
			if (directory.get(i).equals(itemName)) {
				productList.add(i);
			}
		}
		return productList;
	}

	public void restock() {
		System.out.println("\nRestocking...");
		boolean restocked = false;
		for (int i = 0; i < slots.size(); ++i) {
			if (slots.get(i).size() < maxCap) {
				System.out.println("Restocked x" + (maxCap - slots.get(i).size()) + " " + directory.get(i));
			}
			while (slots.get(i).size() < maxCap) {
				restocked = true;
				slots.get(i).add(itemStock.get(i));
			}
		}
		if (!restocked) {
			System.out.println("Vending Machine Full | No Items Restocked...");
		}
	}
	
	public int getMaxCap() { return maxCap; }
	public void setMaxCap(int maxCap) { this.maxCap = maxCap; }

	public void displayItems() {
		System.out.println(toString());
	}

	@Override
	public String toString() {
		String output = "Directory = " + directory + "\nSlots     = ";
		int count = 0;
		for (Queue<Item> q : slots) {
			output += "[";
			if (count < 10) {
				output += "0" + count++ + "] ";;
			} else {
				output += count++ + "] ";;
			}
			for (Item item : q) {
				output += item.toString() + " | ";
			}
			output += "\n            ";
		}
		return output;
	}
}
