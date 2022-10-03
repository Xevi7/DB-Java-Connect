package items;

public class Beverage extends Item {
	private int withIce;
	
	public Beverage(String itemId, String itemName, int itemPrice, String itemType, int withIce) {
		super(itemId, itemName, itemPrice, itemType);
		this.withIce = withIce;
	}

	@Override
	public void display() {
		System.out.printf("| %-20s | %-16s | %-8d | %-16s | %-7s|\n", this.getItemName(), this.getItemType(), this.getItemPrice(), "-", this.getIce());
	}

	public String getIce() {
		if(this.getWithIce() == 1) {
			return "Yes";
		}
		return"No";
	}
	
	public int getWithIce() {
		return withIce;
	}

	public void setWithIce(int withIce) {
		this.withIce = withIce;
	}

}
