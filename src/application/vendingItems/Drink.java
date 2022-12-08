package application.vendingItems;

public class Drink extends Item{
	
	protected float ounces;
	protected String drinkType;
	
	public Drink(float ounces, String drinkType,
				 String itemName, float itemCalories, String itemType, int itemCount) {
		super(itemName, itemCalories, itemType, itemCount);
		this.ounces = ounces;
		this.drinkType = drinkType;
	}
	public Drink(float ounces, String drinkType, String itemName, float itemCalories, String itemType) {
		super(itemName, itemCalories, itemType, 1);
		this.ounces = ounces;
		this.drinkType = drinkType;
	}

	// getters and setters
	public float getOunces() { return ounces; }
	public void setOunces(float ounces) { this.ounces = ounces; }
	public String getDrinkType() { return drinkType; }
	public void setDrinkType(String drinkType) { this.drinkType = drinkType; }
	@Override
	public String toString() {
		return super.getName() + " [oz: " + ounces + ", Type: " + drinkType + "]";
	}
	
}
