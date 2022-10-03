package main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import DbConnect.Connect;
import items.Beverage;
import items.Food;
import items.Item;
import items.Transaction;

public class Main {
	Connect con = Connect.getConnection();
	Scanner scan = new Scanner(System.in);
	Random rand = new Random();
	Vector<Item> itemVec = new Vector<>();
	Vector<Transaction> transVec = new Vector<>();
	
	void initialize() {
		String sql = "SELECT * FROM sodadrink";
		ResultSet rs = con.executeQuery(sql);
		try {
			while(rs.next()) {
				itemVec.addElement(new Beverage(rs.getString("itemID"), rs.getString("itemName"), rs.getInt("itemPrice"), "Beverage", rs.getInt("withIce")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql = "SELECT * FROM wing";
		rs = con.executeQuery(sql);
		try {
			while(rs.next()) {
				itemVec.addElement(new Food(rs.getString("itemID"), rs.getString("itemName"), rs.getInt("itemPrice"), "Food", rs.getString("SauceName")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql = "SELECT * FROM transaction";
		rs = con.executeQuery(sql);
		try {
			while(rs.next()) {
				int len = itemVec.size();
				int idx = 0;
				for(int i=0; i<len; i++) {
					if(itemVec.get(i).getItemId().equals(rs.getString("itemId"))) {
						idx = i;
					}
				}
				transVec.addElement(new Transaction(rs.getString("transactionID"), rs.getString("itemId"), itemVec.get(idx).getItemName(), rs.getString("customerName"), rs.getString("customerEmail"), rs.getInt("quantity")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void view() {
		System.out.println("=========================================================================================");
		System.out.println("| No. | Item Name            | Food / Beverage  | Price    | Sauce            | Ice     |");
		System.out.println("=========================================================================================");
		int idx=0;
		for (Item item : itemVec) {
			idx += 1;
			System.out.printf("| %3d ", idx);
			item.display();			
		}
		System.out.println("=========================================================================================");
	}
	
	int validation(String tempEmail) {
		if(tempEmail.length() < 10) {
			return 0;
		}
		String cek = "@gmail.com";
		int idx1 = cek.length();
		int idx2 = tempEmail.length();
		while(idx1 > 0) {
			if(tempEmail.charAt(--idx2) != cek.charAt(--idx1)) {
				return 0;
			}
		}
		
		return 1;
	}
	
	void menu1() {
		view();
		int put;
		do {
			System.out.print("Input Item[1-" + itemVec.size() + "]: ");
			try {
				put = scan.nextInt();
				scan.nextLine();
			} catch (Exception e) {
				put = 0;
				scan.nextLine();
			}
		}while(put < 1 || put > itemVec.size());
		
		String tempName;
		do {
			System.out.print("Input Customer Name[5 - 20] (Inclusive): ");
			tempName = scan.nextLine();
		}while(tempName.length() < 5 || tempName.length() > 20);
		
		String tempEmail;
		do {
			System.out.print("Input Customer Email[must end with '@gmail.com']: ");
			tempEmail = scan.nextLine();
		}while(validation(tempEmail) == 0);
		
		int tempQuantity;
		do {
			System.out.print("Input Quantity[must be more than zero]: ");
			try {
				tempQuantity = scan.nextInt();
				scan.nextLine();
			} catch (Exception e) {
				tempQuantity = 0;
				scan.nextLine();
			}
		}while(tempQuantity < 1);
		
		int tempPrice;
		if(itemVec.elementAt(put-1).getItemType().equals("Beverage")) {
			Beverage b = (Beverage) itemVec.elementAt(put-1);
			if(b.getWithIce() == 1) {
				tempPrice = (b.getItemPrice() + 2000) * tempQuantity;
			}
			else {
				tempPrice = (b.getItemPrice()) * tempQuantity;
			}
		}
		else {
			Food f = (Food) itemVec.elementAt(put-1);
			tempPrice = f.getItemPrice() * tempQuantity;
		}
		
		String tempId="TI";
		Integer num;
		for(int i=0; i<3; i++) {
			num = rand.nextInt(9);
			tempId += num.toString();
		}
		
		String sql = "INSERT INTO transaction VALUES(?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		try {
			ps.setString(1, tempId);
			ps.setString(2, itemVec.get(put-1).getItemId());
			ps.setString(3, tempName);
			ps.setString(4, tempEmail);
			ps.setInt(5, tempQuantity);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		transVec.addElement(new Transaction(tempId, itemVec.get(put-1).getItemId(), itemVec.get(put-1).getItemName(), tempName, tempEmail, tempQuantity));
		
		System.out.println("==============================================");
		System.out.println("Detail Transaction");
		System.out.println("==============================================");
		System.out.println("Customer: " + tempName);
		System.out.println("Email: " + tempEmail);
		System.out.println("Item: " + itemVec.get(put-1).getItemName());
		if(itemVec.get(put-1).getItemType() == "Beverage") {
			System.out.println("With Ice: " + ((Beverage)itemVec.get(put-1)).getIce());
		}
		else {
			System.out.println("Sauce: " + ((Food)itemVec.get(put-1)).getSauceName());
		}
		System.out.println("Quantity: " + tempQuantity);
		System.out.println("Price: " + itemVec.get(put-1).getItemPrice());
		System.out.println("TotalPrice: " + tempPrice);
		System.out.println("==============================================");
		System.out.println("Press Enter to Continue...");
		scan.nextLine();
	}
	
	void menu2() {
		if(transVec.isEmpty()) {
			System.out.println("There is no transaction!");
			return;
		}
		System.out.println("============================================================================================================================");
		System.out.println("| No. | Transaction ID  | Item Name            | Customer Name          | Customer Email                 | Quantity        |");
		System.out.println("============================================================================================================================");
		int idx = 0;
		for (Transaction trans : transVec) {
			System.out.printf("| %3d | %-15s | %-20s | %-22s | %-30s | %-15d |\n", ++idx, trans.getTransactionId(), trans.getItemName(), trans.getCustomerName(), trans.getCustomerEmail(), trans.getQuantity());;
		}
		System.out.println("============================================================================================================================");
	}
	
	void menu3() {
		menu2();
		if(transVec.isEmpty()) {
			System.out.println("Press Enter to Continue...");
			scan.nextLine();
			return;
		}
		int idx;
		do {
			System.out.print("Input Number to Delete[1-"+transVec.size()+"]: ");
			try {
				idx = scan.nextInt();
				scan.nextLine();
			} catch (Exception e) {
				idx = 0;
				scan.nextLine();
			}
		}while(idx < 1 || idx > transVec.size());
		
		Transaction trans = transVec.remove(idx-1);
		String sql = "DELETE FROM transaction WHERE transactionID = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		try {
			ps.setString(1, trans.getTransactionId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Transaction Successfully Deleted!");
		System.out.println("Press Enter to Continue...");
		scan.nextLine();
	}

	public Main() {
		initialize();
		int put;
		do {
			System.out.println("=========================");
			System.out.println("|  Welcome to WG Stop   |");
			System.out.println("=========================");
			System.out.println("| 1. Create Transaction |");
			System.out.println("| 2. View Transaction   |");
			System.out.println("| 3. Delete Transaction |");
			System.out.println("| 4. Exit               |");
			System.out.println("=========================");
			do {
				System.out.print(">> ");
				try {
					put = scan.nextInt();
					scan.nextLine();
				} catch (Exception e) {
					put = 0;
					scan.nextLine();
				}
			}while(put < 1 || put > 4);
			
			switch (put) {
				case 1:{
					menu1();
				}break;
				
				case 2:{
					menu2();
					System.out.println("Press Enter to Continue...");
					scan.nextLine();
				}break;
				
				case 3:{
					menu3();
				}break;
			}
		}while(put != 4);
	}

	public static void main(String[] args) {
		new Main();
	}

}
