package application.item;

public class Item {
	
	protected String name;
	protected float calories;
	protected String itemType;
	protected int itemCount;
	
	public Item(String name, float calories, String itemType, int itemCount) {
		this.name = name;
		this.calories = calories;
		this.itemType = itemType;
		this.itemCount = itemCount;
	}
	public Item(String name, float calories, String itemType) {
		this.name = name;
		this.calories = calories;
		this.itemType = itemType;
		this.itemCount = 1;
	}

	public String getName() { return name; }
	public void setName(String itemName) { this.name = itemName; }
	public float getCalories() { return calories; }
	public void setCalories(float itemCalories) { this.calories = itemCalories; }
	public String getItemType() { return itemType; }
	public void setItemType(String itemType) { this.itemType = itemType; }
	public int getItemCount() { return itemCount; }
	public void setItemCount(int itemCount) { this.itemCount = itemCount; }
}
