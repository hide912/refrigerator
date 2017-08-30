package com.refri.controller;

import java.util.List;
import java.util.Scanner;

import com.refri.model.ClassDAO;
import com.refri.model.ClassDTO;
import com.refri.model.CookingDAO;
import com.refri.model.CookingDTO;
import com.refri.view.ClassView;
import com.refri.view.CookingView;

public class CookingController {

	public static void main(String[] args) {
		CookingDTO c = null;
		List<CookingDTO> clist = null;
		CookingDAO cDAO = new CookingDAO();
		
		ClassDTO cl = null;
		List<ClassDTO> cllist = null;
		ClassDAO clDAO = new ClassDAO();
		

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("1. 전체조회");
			System.out.println("2. 요리별 요리 조회");
			System.out.println("3. 대분류별 요리 조회");
			System.out.println("4. 중분류별 요리 조회");
			System.out.println("5. 요리 추가 ");
			System.out.println("6. 요리 삭제 ");
			System.out.println("7. 요리 변경 ");
			System.out.println("0. 종료");

			System.out.print("작업선택  >>  ");
			switch (sc.nextInt()) {
			case 0:
				System.out.println("종료 .. ");
				return;
			case 1: {
				clist = cDAO.selectAll();
				CookingView.print(clist);
				break;
			}
			case 2: {
				System.out.print("요리명을 입력하세요 >> ");
				String cooking = sc.nextLine();
				clist = cDAO.selectByCooking(cooking);
				CookingView.print(clist);
				break;
			}
			case 3: {
				System.out.print("대분류를 입력하세요 >> ");
				String in = sc.nextLine();
				clist = cDAO.selectByIn(in);
				CookingView.print(clist);
				break;
			}
			case 4: {
				System.out.print("중분류를 입력하세요 >> ");
				String subClass = sc.nextLine();
				clist = cDAO.selectBySub(subClass);
				CookingView.print(clist);
				break;
			}
			case 5: {
				System.out.println("추가할 요리명을 입력하세요 >> ");
				String cooking = sc.nextLine();

				clist = cDAO.selectByCooking(cooking);

				if (clist.isEmpty() == true) {

					cllist = clDAO.selectAll();
					ClassView.print(cllist);
					cllist = null;
					
					System.out.print("추가할 요리의 대분류 >> ");
					String classin = sc.nextLine();
					
					cllist = clDAO.selectByIn(classin);
					
					if(clist.isEmpty() != true){
						
						cllist = null;
						System.out.print("추가할 요리의 중분류 >> ");
						String subClass = sc.nextLine();
						cllist = clDAO.selectByIn(subClass);
						
							if(clist.isEmpty() != true){
	
								c = new CookingDTO(cooking, classin, subClass);
								int result = cDAO.insertCooking(c);
	
								String msg = "삽입 실패";
								if (result > 0)
									msg = "삽입 성공";
								CookingView.print(msg);
								break;
							}
						}
					
				} else
					System.out.println("이미 존재하는 요리입니다.");
				break;
			}
			case 6: {
				System.out.print("삭제할 요리명을 입력하세요 >> ");
				String cooking = sc.nextLine();

				clist = cDAO.selectByCooking(cooking);

				if (clist.isEmpty() != true) {

					CookingView.print(cDAO.selectByCooking(cooking));

					System.out.print("삭제하시겠습니까 ? (true/false)");
					boolean b = sc.nextBoolean();

					if (b) {
						int result = cDAO.deleteCooking(cooking);
						String msg = "삭제 실패";
						if (result > 0) {
							CookingView.print(cDAO.selectByCooking(cooking));
							msg = "삭제 성공";
						}
						CookingView.print(msg);
					}
					break;
				} else
					System.out.println("잘못된 입력입니다.");
				break;
			}
			case 7: {
				System.out.println("수정할 요리명을 입력하세요 >> ");
				String cooking = sc.nextLine();

				clist = cDAO.selectByCooking(cooking);

				if (clist.isEmpty() == true) {

					cllist = clDAO.selectAll();
					ClassView.print(cllist);
					cllist = null;
					
					System.out.print("수정할 대분류 >> ");
					String classin = sc.nextLine();
					
					cllist = clDAO.selectByIn(classin);
					
					if(clist.isEmpty() != true){
						
						cllist = null;
						System.out.print("수정할 중분류 >> ");
						String subClass = sc.nextLine();
						cllist = clDAO.selectByIn(subClass);
						
							if(clist.isEmpty() != true){
	
								c = new CookingDTO(cooking, classin, subClass);
								int result = cDAO.updateCooking(c);
	
								String msg = "수정 실패";
								if (result > 0)
									msg = "수정 성공";
								CookingView.print(msg);
								break;
							}
							else
								System.out.println("잘못된 중분류입니다.");
						}
					else
						System.out.println("잘못된 대분류입니다.");
					
				} else
					System.out.println("이미 존재하는 요리입니다.");
				break;
				}
			}
		}
	}

}
