package application.vendingData;

import application.vendingItems.*;
import application.consoleColors.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class Vending {
	
	public ArrayList<String> directory;
	public ArrayList<Item> itemStock;
	public ArrayList<Queue<Item>> slots;
	private int maxCap;
	
	// for testing only
	private void printData(String[] data) {
		for (int i = 0; i < data.length; ++i) {
			System.out.print(data[i]);
			if (i != data.length - 1) {
				System.out.print(", ");
			}
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
		printData(dataArr);
		try {
			if (dataArr[0].equals("Drink")) {
				Item newItem = new Drink(Float.parseFloat(dataArr[3]), dataArr[4], dataArr[1], Float.parseFloat(dataArr[2]), dataArr[0], Integer.parseInt(dataArr[5]));
				return newItem;
			} else if (dataArr[0].equals("Snack")) {
				Item newItem = new Snack(Float.parseFloat(dataArr[3]), Boolean.parseBoolean(dataArr[4]), dataArr[1], Float.parseFloat(dataArr[2]), dataArr[0], Integer.parseInt(dataArr[5]));
				return newItem;
			} else {
				System.out.println(ConsoleColors.RED + "Invalid Item Type: [" + dataArr[0] + "] | Item not Processed..." + ConsoleColors.RESET);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(ConsoleColors.RED + "No Data To Be Processed..." + ConsoleColors.RESET);
		}
		return null;
	}
	
	// manual load
	public void loadItem(Item newItem) {
		System.out.print(ConsoleColors.CYAN + "Manually Loading: " + ConsoleColors.RESET + newItem + " | ");
		String itemName = newItem.getName();
		ArrayList<Integer> productList = findProduct(itemName);
		if (productList.isEmpty()) {
			if (slots.size() >= maxCap) {
				System.out.println(ConsoleColors.RED + "Max Capacity Reached: [" + maxCap + "] | [" + itemName + "] Not Loaded Into Machine..." + ConsoleColors.RESET);
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
		System.out.println(ConsoleColors.GREEN + "Successfully Added..." + ConsoleColors.RESET);
	}
	
	public void loadItem(ArrayList<String> data) {
		int idx = 0;
		for (String str : data) {
			if (slots.size() >= maxCap) {
				System.out.println(ConsoleColors.RED + "Max Capacity Reached: [" + maxCap + "] | " + (data.size() - idx) + " Item(s) Not Loaded Into Machine..." + ConsoleColors.RESET);
				break;
			}
			++idx;
			Item newItem = parseData(str);
			try {
				directory.add(newItem.getName());
				itemStock.add(newItem);
				Queue<Item> q = new LinkedList<Item>();
				for (int i = 0; i < newItem.getItemCount(); ++i) {
					q.add(newItem);
				}
				System.out.println(ConsoleColors.CYAN + "File Loading: " + ConsoleColors.GREEN + "x" + newItem.getItemCount() + " [" + newItem.getName() + "]" + ConsoleColors.RESET);
				slots.add(q);
			} catch (NullPointerException e) {
				continue;
			}
		}
	}
	
	// manual unload
	public void unloadItem(String itemName) {
		System.out.print(ConsoleColors.CYAN + "Manually Unloading: " + ConsoleColors.RESET + itemName + " | ");
		ArrayList<Integer> productList = findProduct(itemName);
		try {
			int max = slots.get(productList.get(0)).size();
			int maxIndex = productList.get(0);
			for (int i : productList) {
				Queue<Item> q = slots.get(i);
				if (q.size() > max) {
					max = q.size();
					maxIndex = i;
				}
			}
			if (slots.get(maxIndex).isEmpty()) {
				System.out.println(ConsoleColors.RED + "No Existing [" + itemName + "] | Nothing was Unloaded..." + ConsoleColors.RESET);
			} else {
				System.out.println(ConsoleColors.GREEN + "Successfully Unloaded " + itemName + "..." + ConsoleColors.RESET);
				slots.get(maxIndex).remove();
			}
		} catch (IndexOutOfBoundsException e){
			System.out.println(ConsoleColors.RED + "No Existing [" + itemName + "] | Nothing was Unloaded..." + ConsoleColors.RESET);
		}
	}
	
	private void unloadItems(int index) {
		try {
			String itemName = directory.get(index);
			ArrayList<Integer> productList = findProduct(itemName);
			for (int i : productList) {
				Queue<Item> q = slots.get(i);
				if (!q.isEmpty()) {
					System.out.println(ConsoleColors.CYAN + "File Unloading: " + ConsoleColors.GREEN + itemName + ConsoleColors.RESET);
					q.remove();
					return;
				}
			}
			System.out.println(ConsoleColors.CYAN + "File Unloading:" + ConsoleColors.RED + " No Existing [" + itemName + "] | Nothing was Unloaded..." + ConsoleColors.RESET);
		} catch (IndexOutOfBoundsException e) {
			System.out.println(ConsoleColors.CYAN + "File Unloading:" + ConsoleColors.RED + " Invalid Index: [" + index + "] | Nothing was Unloaded..." + ConsoleColors.RESET);
			return;
		}
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
				System.out.println(ConsoleColors.GREEN + "Restocked x" + (maxCap - slots.get(i).size()) + " [" + directory.get(i) + "]" + ConsoleColors.RESET);
			}
			while (slots.get(i).size() < maxCap) {
				restocked = true;
				slots.get(i).add(itemStock.get(i));
			}
		}
		if (!restocked) {
			System.out.println(ConsoleColors.RED + "Vending Machine Full | No Items Restocked..." + ConsoleColors.RESET);
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
			output += ConsoleColors.PURPLE + "[";
			if (count < 10) {
				output += "0" + count++ + "] " + ConsoleColors.RESET;
			} else {
				output += count++ + "] " + ConsoleColors.RESET;
			}
			for (Item item : q) {
				output += item.toString() + ConsoleColors.PURPLE + " | " + ConsoleColors.RESET;
			}
			output += "\n            ";
		}
		return output;
	}
}
