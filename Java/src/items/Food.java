package items;

public class Food extends Item {
	private String sauceName;
	
	public Food(String itemId, String itemName, int itemPrice, String itemType, String sauceName) {
		super(itemId, itemName, itemPrice, itemType);
		this.sauceName = sauceName;
	}

	@Override
	public void display() {
		System.out.printf("| %-20s | %-16s | %-8d | %-16s | %-7s|\n", this.getItemName(), this.getItemType(), this.getItemPrice(), this.getSauceName(), "-");
	}

	public String getSauceName() {
		return sauceName;
	}

	public void setSauceName(String sauceName) {
		this.sauceName = sauceName;
	}

}
