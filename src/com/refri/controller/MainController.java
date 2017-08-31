package com.refri.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import com.refri.model.InventoryDAO;
import com.refri.model.InventoryDTO;
import com.refri.model.MainDAO;
import com.refri.view.InventoryView;

import util.DateUtil;

public class MainController {

	public static void main(String[] args) {
		MainDAO maindao = new MainDAO();
		InventoryDAO dao = new InventoryDAO();
		List<InventoryDTO> ilist = null;
		Date importdate = null;
		Date outputdate = null;
		InventoryDTO st = null;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
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
		System.out.println("1.��ῡ �´� ������ �˻�(���� �Է�)");
		System.out.println("2.����� ������� �߰�");
		System.out.println("3.����� ������� ����");
		System.out.println("4.����� ������� ��ȸ");
		System.out.println("5.����� ������� ����");
		System.out.println("0.����");

		Scanner sc = new Scanner(System.in);
		myloop: while (true) {
			System.out.print("�۾�����>>");
			int select = sc.nextInt();
			switch (select) {
			case 0:
				System.out.println("�����մϴ�.");
				break myloop;
			case 1:
				 maindao.Search_Select();
				break;
			case 2: {
				System.out.print("����>>");
				String ingredient = sc.next();
				System.out.print("��������(�׳� ���͸� ĥ ��� �������ڴ� ���糯¥�� ����˴ϴ�.)>>");
				sc.nextLine();
				String import_day = sc.nextLine();
				if ("".equals(import_day) || import_day == null) {
					importdate = curdate;
				} else {
					importdate = DateUtil.stringToDate(import_day);
				}
				System.out.print("���>>");
				int stock = sc.nextInt();
				System.out.print("����>>");
				String unit = sc.next();
				System.out.print("��ȿ����(�׳� ���͸� ĥ ��� �������� ���� ������ �ķ� ����˴ϴ�.)>>");
				sc.nextLine();
				String output_day = sc.nextLine();				
				if ("".equals(output_day) || output_day == null) {
					cal.setTime(curdate);
					cal.add(Calendar.DATE, 7);					
					outputdate = DateUtil.stringToDate(df.format(cal.getTime()));
				}else{
					outputdate = DateUtil.stringToDate(sc.next());					
				}
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
			case 3:{
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
			}
			case 4:
				ilist = dao.selectAll();
				InventoryView.print(ilist);
				break;
			case 5: {
				System.out.print("������ ����>>");
				String ingredient = sc.next();
				System.out.println("original �����Դϴ�.");
				InventoryView.print(dao.selectByIng(ingredient));

				System.out.print("������ ��������>>");
				importdate = DateUtil.stringToDate(sc.next());
				System.out.print("������ ���>>");
				int stock = sc.nextInt();
				System.out.print("������ ����>>");
				String unit = sc.next();
				System.out.print("������ ��ȿ�Ⱓ>>");
				outputdate = DateUtil.stringToDate(sc.next());
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
			default:
				break;
			}

		}
	}

}
