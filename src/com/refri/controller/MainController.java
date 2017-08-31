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
		System.out.println("---------------냉장고 재료 리스트---------------");
		ilist = dao.selectAll();
		InventoryView.print(ilist);
		System.out.println("-----------------------------------------------------");
		System.out.println("");
		System.out.println("###############유효기간이 일주일만 남은 재료 리스트###############");
		Date curdate = new java.sql.Date(System.currentTimeMillis());
		ilist = dao.selectByDuedate(curdate);
		InventoryView.print(ilist);
		System.out.println("#####################################################");
		System.out.println("1.재료에 맞는 레시피 검색(재료명 입력)");
		System.out.println("2.냉장고에 재료정보 추가");
		System.out.println("3.냉장고에 재료정보 삭제");
		System.out.println("4.냉장고에 재료정보 조회");
		System.out.println("5.냉장고에 재료정보 수정");
		System.out.println("0.종료");

		Scanner sc = new Scanner(System.in);
		myloop: while (true) {
			System.out.print("작업선택>>");
			int select = sc.nextInt();
			switch (select) {
			case 0:
				System.out.println("종료합니다.");
				break myloop;
			case 1:
				 maindao.Search_Select();
				break;
			case 2: {
				System.out.print("재료명>>");
				String ingredient = sc.next();
				System.out.print("반입일자(그냥 엔터를 칠 경우 반입일자는 현재날짜로 저장됩니다.)>>");
				sc.nextLine();
				String import_day = sc.nextLine();
				if ("".equals(import_day) || import_day == null) {
					importdate = curdate;
				} else {
					importdate = DateUtil.stringToDate(import_day);
				}
				System.out.print("재고>>");
				int stock = sc.nextInt();
				System.out.print("단위>>");
				String unit = sc.next();
				System.out.print("유효기한(그냥 엔터를 칠 경우 반입일자 기준 일주일 후로 저장됩니다.)>>");
				sc.nextLine();
				String output_day = sc.nextLine();				
				if ("".equals(output_day) || output_day == null) {
					cal.setTime(curdate);
					cal.add(Calendar.DATE, 7);					
					outputdate = DateUtil.stringToDate(df.format(cal.getTime()));
				}else{
					outputdate = DateUtil.stringToDate(sc.next());					
				}
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
			case 3:{
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
			}
			case 4:
				ilist = dao.selectAll();
				InventoryView.print(ilist);
				break;
			case 5: {
				System.out.print("수정할 재료명>>");
				String ingredient = sc.next();
				System.out.println("original 정보입니다.");
				InventoryView.print(dao.selectByIng(ingredient));

				System.out.print("수정할 반입일자>>");
				importdate = DateUtil.stringToDate(sc.next());
				System.out.print("수정할 재고>>");
				int stock = sc.nextInt();
				System.out.print("수정할 단위>>");
				String unit = sc.next();
				System.out.print("수정할 유효기간>>");
				outputdate = DateUtil.stringToDate(sc.next());
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
			default:
				break;
			}

		}
	}

}
