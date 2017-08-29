package com.refri.view;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.refri.model.InventoryDTO;

public class InventoryView {
	public static void print(List<InventoryDTO> inventorylist) {
		//System.out.println("★★★★★여러건★★★★★");
		for (InventoryDTO dept : inventorylist) {
			System.out.println(dept);
		}
	}

	public static void print(InventoryDTO inventory) {
		//System.out.println("★★★★★한건★★★★★");
		System.out.println(inventory);
	}

	public static void print(String msg) {
		System.out.println("알림:" + msg);
	}

	private InventoryDTO makeInventory(ResultSet rs) throws SQLException {
		String ingredient = rs.getString(1);
		Date importdate = rs.getDate(2);
		int stock = rs.getInt(3);
		String unit = rs.getString(4);
		Date outputdate = rs.getDate(5);
		String location = rs.getString(6);
		
		InventoryDTO inventory = new InventoryDTO(ingredient, importdate, stock, unit, outputdate, location);

		return inventory;
	}
}
