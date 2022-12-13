package vendingLogic;

public class Snack extends Item {

	protected float weight;
	protected boolean containsNuts;

	public Snack(float weight, boolean containsNuts,
				 String itemName, float itemCalories, String itemType, int itemCount) {
		super(itemName, itemCalories, itemType, itemCount);
		this.weight = weight;
		this.containsNuts = containsNuts;
	}

	public Snack(float weight, boolean containsNuts,
			 String itemName, float itemCalories, String itemType) {
	super(itemName, itemCalories, itemType);
	this.weight = weight;
	this.containsNuts = containsNuts;
}
	// getters and setters
	public float getWeight() { return weight; }
	public void setWeight(float weight) { this.weight = weight; }
	public boolean isContainsNuts() { return containsNuts; }
	public void setContainsNuts(boolean containsNuts) {	this.containsNuts = containsNuts; }
	@Override
	public String toString() {
		return super.getName() + " [wt: " + weight + ", Nuts: " + containsNuts + "]";
	}

}
