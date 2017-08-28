package com.refri.controller;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.refri.model.InventoryDAO;
import com.refri.model.InventoryDTO;
import com.refri.view.InventoryView;

import util.DateUtil;

public class InventoryController {

	public static void main(String[] args) {
		System.out.println("1.�����ȸ");
		System.out.println("2.Ư�������ȸ");
		System.out.println("3.��������Է�");
		System.out.println("4.�����������");
		System.out.println("5.�����������");
		System.out.println("0.����");
		InventoryDAO dao = new InventoryDAO();
		List<InventoryDTO> ilist = null;
		InventoryDTO st = null;

		Scanner sc = new Scanner(System.in);
		myloop: while (true) {
			System.out.print("�۾�����>>");
			int select = sc.nextInt();
			switch (select) {
			case 0:
				System.out.println("����մϴ�.");
				break myloop;
			case 1:
				ilist = dao.selectAll();
				InventoryView.print(ilist);
				break;
			case 2:
				System.out.print("����>>");
				String id = sc.next();
				st = dao.selectByIng(id);
				InventoryView.print(st);
				break;
			case 3: {
				System.out.print("����>>");
				String ingredient = sc.next();
				System.out.print("��������>>");
				Date importdate = DateUtil.stringToDate(sc.next());
				System.out.print("���>>");
				int stock = sc.nextInt();
				System.out.print("����>>");
				String unit = sc.next();
				System.out.print("��ȿ����>>");
				Date outputdate = DateUtil.stringToDate(sc.next());
				System.out.print("��ġ>>");
				String location = sc.next();
				st = new InventoryDTO(ingredient, importdate, stock, unit, outputdate, location);
				int result = dao.inventoryInsert(st);
				String msg = "�Է¿� ����";
				if (result > 0)
					msg = "�Է¿� ����";
				InventoryView.print(msg);
				break;
			}
			case 4: {
				System.out.print("������ ����>>");
				String ingredient = sc.next();
				System.out.println("original �����Դϴ�.");
				InventoryView.print(dao.selectByIng(ingredient));

				System.out.print("������ ��������>>");
				Date importdate = DateUtil.stringToDate(sc.next());
				System.out.print("������ ���>>");
				int stock = sc.nextInt();
				System.out.print("������ ����>>");
				String unit = sc.next();
				System.out.print("������ ��ȿ�Ⱓ>>");
				Date outputdate = DateUtil.stringToDate(sc.next());
				System.out.print("������ ��ġ>>");
				String location = sc.next();
				
				st = new InventoryDTO(ingredient, importdate, stock, unit, outputdate, location);
				int result = dao.inventoryUpdate(st);
				String msg = "������ ����";
				if (result > 0)
					msg = "������ ����";
				InventoryView.print(msg);
				break;
			}
			case 5:
				System.out.print("������ ����>>");
				String ingredient = sc.next();
				System.out.println("original �����Դϴ�.");
				InventoryView.print(dao.selectByIng(ingredient));
				System.out.println("�����Ͻðڽ��ϱ�?");
				boolean b = sc.nextBoolean();
				if (b) {
					int result = dao.inventoryDelete(ingredient);
					String msg = "������ ����";
					if (result > 0)
						msg = "������ ����";
					InventoryView.print(msg);
				}
				break;
			default:
			}
		}

	}

}
