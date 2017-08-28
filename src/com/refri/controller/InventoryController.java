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
		System.out.println("1.모두조회");
		System.out.println("2.특정재료조회");
		System.out.println("3.재료정보입력");
		System.out.println("4.재료정보수정");
		System.out.println("5.재료정보삭제");
		System.out.println("0.종료");
		InventoryDAO dao = new InventoryDAO();
		List<InventoryDTO> ilist = null;
		InventoryDTO st = null;

		Scanner sc = new Scanner(System.in);
		myloop: while (true) {
			System.out.print("작업선택>>");
			int select = sc.nextInt();
			switch (select) {
			case 0:
				System.out.println("퇴근합니다.");
				break myloop;
			case 1:
				ilist = dao.selectAll();
				InventoryView.print(ilist);
				break;
			case 2:
				System.out.print("재료명>>");
				String id = sc.next();
				st = dao.selectByIng(id);
				InventoryView.print(st);
				break;
			case 3: {
				System.out.print("재료명>>");
				String ingredient = sc.next();
				System.out.print("반입일자>>");
				Date importdate = DateUtil.stringToDate(sc.next());
				System.out.print("재고>>");
				int stock = sc.nextInt();
				System.out.print("단위>>");
				String unit = sc.next();
				System.out.print("유효기한>>");
				Date outputdate = DateUtil.stringToDate(sc.next());
				System.out.print("위치>>");
				String location = sc.next();
				st = new InventoryDTO(ingredient, importdate, stock, unit, outputdate, location);
				int result = dao.inventoryInsert(st);
				String msg = "입력에 실패";
				if (result > 0)
					msg = "입력에 성공";
				InventoryView.print(msg);
				break;
			}
			case 4: {
				System.out.print("수정할 재료명>>");
				String ingredient = sc.next();
				System.out.println("original 정보입니다.");
				InventoryView.print(dao.selectByIng(ingredient));

				System.out.print("수정할 반입일자>>");
				Date importdate = DateUtil.stringToDate(sc.next());
				System.out.print("수정할 재고>>");
				int stock = sc.nextInt();
				System.out.print("수정할 단위>>");
				String unit = sc.next();
				System.out.print("수정할 유효기간>>");
				Date outputdate = DateUtil.stringToDate(sc.next());
				System.out.print("수정할 위치>>");
				String location = sc.next();
				
				st = new InventoryDTO(ingredient, importdate, stock, unit, outputdate, location);
				int result = dao.inventoryUpdate(st);
				String msg = "수정에 실패";
				if (result > 0)
					msg = "수정에 성공";
				InventoryView.print(msg);
				break;
			}
			case 5:
				System.out.print("삭제할 재료명>>");
				String ingredient = sc.next();
				System.out.println("original 정보입니다.");
				InventoryView.print(dao.selectByIng(ingredient));
				System.out.println("삭제하시겠습니까?");
				boolean b = sc.nextBoolean();
				if (b) {
					int result = dao.inventoryDelete(ingredient);
					String msg = "삭제에 실패";
					if (result > 0)
						msg = "삭제에 성공";
					InventoryView.print(msg);
				}
				break;
			default:
			}
		}

	}

}
