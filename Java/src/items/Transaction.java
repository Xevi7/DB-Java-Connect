package items;

public class Transaction {
	private String transactionId;
	private String itemId;
	private String itemName;
	private String customerName;
	private String customerEmail;
	private int quantity;
	
	public Transaction(String transactionId, String itemId, String itemName, String customerName, String customerEmail,
			int quantity) {
		super();
		this.transactionId = transactionId;
		this.itemId = itemId;
		this.itemName = itemName;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.quantity = quantity;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
