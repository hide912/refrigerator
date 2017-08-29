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
		
		System.out.println("1.재료에 맞는 레시피 검색(재료명 입력)>>");
		System.out.println("2.냉장고에 재료 추가>>");
		System.out.println("3.냉장고에 재료 삭제>>");
		
	}

}
