package com.refri.controller;

import java.sql.Date;
import java.util.List;

import com.refri.model.InventoryDAO;
import com.refri.model.InventoryDTO;
import com.refri.view.InventoryView;

import util.DateUtil;

public class MainController {

	public static void main(String[] args) {
		InventoryDAO dao = new InventoryDAO();
		List<InventoryDTO> ilist = null;
		System.out.println("---------------����� ��� ����Ʈ---------------");
		ilist = dao.selectAll();
		InventoryView.print(ilist);
		System.out.println("-----------------------------------------------------");
		System.out.println("");
		System.out.println("###############��ȿ�Ⱓ�� �����ϸ� ���� ��� ����Ʈ###############");
		Date curdate = new java.sql.Date(System.currentTimeMillis()); 				
		ilist = dao.selectByDuedate(curdate);
		InventoryView.print(ilist);
		System.out.println("#####################################################");
		
		System.out.println("1.��ῡ �´� ������ �˻�(���� �Է�)>>");
		System.out.println("2.����� ��� �߰�>>");
		System.out.println("3.����� ��� ����>>");
		
	}

}
