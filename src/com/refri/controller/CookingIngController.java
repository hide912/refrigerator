package com.refri.controller;

import java.util.List;
import java.util.Scanner;

import com.refri.model.CookingDAO;
import com.refri.model.CookingDTO;
import com.refri.model.CookingIngDAO;
import com.refri.model.CookingIngDTO;
import com.refri.view.CookingIngView;

public class CookingIngController {
	public static void main(String[] args) {
		CookingIngDTO ci = null;
		List<CookingIngDTO> cilist = null;
		CookingIngDAO ciDAO = new CookingIngDAO();
		
		CookingDTO c = null;
		List<CookingDTO> clist = null;
		CookingDAO cDAO = new CookingDAO();
		
		
		Scanner sc = new Scanner(System.in);
		
		while(true){
			System.out.println("1. 전체조회");
			System.out.println("2. 요리별 재료 조회");
			System.out.println("3. 재료별 요리 조회");
			System.out.println("4. 재료 추가 ");
			System.out.println("5. 재료 삭제 ");
			System.out.println("6. 재료 변경 ");
			System.out.println("0. 종료");
			
			System.out.print("  작업선택  >>  ");
			switch (sc.nextInt()) {
			case 0:
				System.out.println("종료 .. ");
				return;
			case 1: {
				cilist = ciDAO.selectAll();
				CookingIngView.print(cilist);
				break;
			}
			case 2: {
				System.out.print("요리명을 입력하세요 >> ");
				String cooking = sc.nextLine();
				cilist = ciDAO.selectByCooking(cooking);
				CookingIngView.print(cilist);
				break;
			}
			case 3: {
				System.out.print("재료명을 입력하세요 >> ");
				String ing = sc.nextLine();
				cilist = ciDAO.selectByIng(ing);
				CookingIngView.print(cilist);
				break;
			}
			case 4: {
				System.out.println("요리명을 입력하세요 >> ");
				String cooking = sc.nextLine();
				
				clist = cDAO.selectByCooking(cooking);
				
				if(clist.isEmpty() != true ){
					List<CookingIngDTO> list = null;
					
					while(true){
						System.out.printf("재료를 하나씩 입력하세요 (종료 = end)>> ");
						String ing = sc.nextLine();
						if(ing.equalsIgnoreCase("end"))
							break;
						CookingIngDTO dto = new CookingIngDTO(cooking, ing);
						list.add(dto);
					}
				}
				break;
			}
			
			case 5: {
				System.out.print("삭제할 음식의 재료 (음식 선택) >> ");
				String cooking = sc.nextLine();
				System.out.print("삭제할 음식의 재료 (재료 선택) >> ");
				String ingredient = sc.nextLine();

				System.out.print("삭제하시겠습니까 ? (true/false)");
				boolean b = sc.nextBoolean();
				
				CookingIngView.print(ciDAO.selectByCooking(cooking));
			
				if (b) {
					int result = ciDAO.deleteCookingIng(cooking, ingredient);
					String msg = "삭제 실패";
					if (result > 0){
						CookingIngView.print(ciDAO.selectByCooking(cooking));
						msg = "삭제 성공";
					}
					CookingIngView.print(msg);
				}
				break;
			}
			case 6: {
				System.out.print("재료를 변경할 요리의 이름 >>");
				String cooking = sc.nextLine();
				
				System.out.print("변경할 재료의 이름 >> ");
				String ingredient = sc.nextLine();

				ci = new CookingIngDTO(cooking, ingredient);

				int result = ciDAO.updateCookingIng(ci);
				String msg = "변경 실패";
				if (result > 0)
					msg = "변경 성공";
				CookingIngView.print(msg);
				break;
				}
			
			
			}
		}
	}
}
