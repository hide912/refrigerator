package com.refri.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.refri.model.RecipeDAO;
import com.refri.model.RecipeDTO;
import com.refri.view.RecipeView;

public class RecipeController {

	// static RecipeController cont = new RecipeController();

	static RecipeView view = new RecipeView();
	static RecipeDTO dto = new RecipeDTO();
	static RecipeDAO dao = new RecipeDAO();

	public static void main(String[] args) {
		
		
		ArrayList list22 = new ArrayList<>();
		list22.add("aa");
		list22.add("bb");
		int i2=5;
		while(i2< list22.size() && "aa".equals(list22.get(i2))) {
			i2++;
		}
		System.out.println("END");
		
		Scanner sc = new Scanner(System.in);
		System.out.println("레시피 조회입니다.");
		
		myloop:while(true){
			
			System.out.println("원하는 서비스를 선택해주세요.");
			System.out.println("1:: 레시피 전체 조회");
			System.out.println("2:: 레시피 선택 조회");
			System.out.println("3:: 레시피 등록");
			System.out.println("4:: 레시피 내용 수정");
			System.out.println("5:: 레시피 삭제");
			
			
		int x = sc.nextInt();
		sc.nextLine();
		switch(x){
		
		case 1:  
		{
		System.out.println("레시피 전체 조회입니다.");	
		view.All_View(dao.RecipeAll());
		
		break;
		}	
		
		case 2:
		{
			
			System.out.println("레시피 선택 조회입니다.");
			System.out.println("1:: 요리명으로 레시피 조회");
			System.out.println("2:: 사용자 id로 레시피 조회");
			int i = sc.nextInt();
			sc.nextLine();
			
			if(i == 1){
			System.out.println("원하는 요리명을 입력해주세요.");
			String cooking = sc.nextLine();
			
			ArrayList<RecipeDTO> list = new ArrayList<>(); 
		
			
			list = dao.RecipeCooking(cooking);
		
			if(list.size()==0){
				System.out.println("입력한 요리에 대한 레시피 조회 결과는 없습니다.");
			}
			
			if(list.size()==1){
			 System.out.println("입력한 요리에 대한 레시피 조회 결과는 1건입니다.");
				view.All_View(list);
			}
			if(list.size() >1 ){
				System.out.println("입력한 요리에 대한 레시피 조회 결과는 다음과 같습니다.");
				view.All_View(list);				
				System.out.println("다음 중 원하는 레시피의 레시피 번호를 입력해주세요");
				int recipenum = sc.nextInt();
				sc.nextLine();
				view.RecipeView(dao.Recipeselect(cooking, recipenum));
				
				break;
			}
			
			}
			if(i ==2){
				System.out.println("사용자id로 레시피 선택합니다.");
				System.out.println("사용자id를 입력해주세요.");
				String id = sc.nextLine();
				
				ArrayList<RecipeDTO> list2 = new ArrayList<>(); 				
				list2 = dao.RecipeCooking(id);
			
				if(list2.size()==0){
					System.out.println("레시피 조회 결과가 없습니다.");
				}
				
				if(list2.size()==1){
				 System.out.println("레시피 조회 결과 1건입니다.");
					view.All_View(list2);
				}
				if(list2.size() >1 ){
					System.out.println("사용자 id에 대한 레시피 조회 결과는 다음과 같습니다.");
					view.All_View(list2);				
					System.out.println("다음 중 원하는 요리명을 입력해주세요.");
					String cooking = sc.nextLine();
					System.out.println("다음 중 원하는 레시피의 레시피 번호를 입력해주세요");
					int recipenum2 = sc.nextInt();
					sc.nextLine();
					
					for(RecipeDTO rec : dao.Recipecru(cooking, recipenum2, id)){
					view.RecipeView(rec);
					}
					break;			
			}	
			
		 }	
			
		}//case 2 끝
			
			
			
		case 3:	//레시피 등록
		{
			
		System.out.println("레시피 등록입니다.");	
		System.out.println("id를 입력해주세요.");
		String userid = sc.nextLine();	//userid db를 만들어서 같은 id조회해서 없으면 빽	
		System.out.println("패스워드를 입력해주세요.");
		String password = sc.nextLine(); //해당 id의 패스워드를 조회해서 맞으면 다음으로 없으면 빽
		System.out.println("등록할 요리명을 입력해주세요.");
		String cooking = sc.nextLine().trim();
	/*	if(cooking.equals(anObject).trim()){
			System.out.println("등록 된 요리명이 아닙니다.");
			break;
		}		//Cooking 테이블에 등록된 요리명과 같은지 
		*/
		
		
		ArrayList<RecipeDTO> cookinglist = new ArrayList<RecipeDTO>(); 
										//등록되어 있는 요리명과 같은지 확인하여 레시피 넘버를 부여하기 위한 리스트
		
		
		
		int cNum = dao.getRecipeNum(cooking);
		System.out.println(cNum+"번 레시피 번호가 부여되었습니다.");
		
		
		//cookinglist = dao.RecipeCooking(cooking); 
	
		/*for(int i=0; i<= cookinglist.size(); i++){
			System.out.println(cookinglist.get(i).toString());
		}
		//System.out.println(cookinglist.get(0).getRecipenum());

		int recipenum = 1;
		int i=0;
		while(recipenum == cookinglist.get(i).getRecipenum()){
			i++;
			recipenum++;
		}*/
												//등록되어 있는 요리명과 같은지 확인하여 그와 같은 수만큼 recipenum++		
		
				//recipenum 부여 도움
		
		
		
		System.out.println("요리의 별칭을 입력해주세요.");
		System.out.println("요리의 별칭은 다른 사람의 같은 요리와 차별화시키는 당신 요리만의 이름입니다. ex) '엄마손' 짜장면");
		String subname = sc.nextLine();
		
			ArrayList<RecipeDTO> list = new ArrayList();
			
			int ordernum=1;
		//	int i=1;
			while(true){
				System.out.println(ordernum +"번째 조리 과정을 간략히 입력하고 엔터를 눌러주세요.");
				System.out.println("입력을 마치려면 end를 입력해주세요.");
				String ordercook = sc.nextLine();
				if(ordercook.equalsIgnoreCase("end")){
					break;
				}
				
				dto = new RecipeDTO(cooking, subname,cNum, ordernum, ordercook, userid);
				list.add(dto);
				System.out.println(dto);
				ordernum++;
			}
			//addBatch 이용한 일괄 sql처리 함수 구현
			int[] results = dao.ManyInsert(list);
			if(results.length >= 0){
				System.out.println("레시피를 등록하였습니다.");
				System.out.println(results);
			}
	
			break;
		}
		
		case 4 :	//레시피 수정 
		{
			System.out.println("등록된 레시피 수정입니다.");
			
			System.out.println("id를 입력해주세요.");
			String userid = sc.nextLine();	//userid db를 만들어서 같은 id조회해서 없으면 빽	
			System.out.println("패스워드를 입력해주세요.");
			String password = sc.nextLine(); //해당 id의 패스워드를 조회해서 맞으면 다음으로 없으면 빽
			
//			System.out.println("1:: 조리과정을 제외한 레시피 정보를 수정");
	//		System.out.println("2:: 다른 레시피 정보를 제외하고 조리과정만을 수정");
			
		
				
			//	System.out.println("1:: 조리 과정을 제외한 정보를 수정합니다.");
		//		System.out.println("입력한 id에 등록된 레시피는 다음과 같습니다.");
				
			//	for( RecipeDTO dto : dao.RecipeUserid(userid)){
		//		System.out.println("요리명:: "+dto.getCooking()+"레시피 넘버:: "+ dto.getRecipenum());
		//		}
				
				System.out.println("다음 중 수정 할 요리명을 입력해주세요.");
				String cooking = sc.nextLine();
				System.out.println("다음 중 수정 할 레시피 번호를 입력해주세요.");
				int recipenum = sc.nextInt();
				sc.nextLine();	
				
				ArrayList<RecipeDTO> list = new ArrayList<>();
								
				list = dao.Recipecru(cooking, recipenum, userid);
				
				for(RecipeDTO dto2 : list){
					System.out.println(dto2);
				}
				System.out.println("위 레시피를 수정합니다.");
				System.out.println("요리명을 수정합니다. >>");
				cooking = sc.nextLine();
				System.out.println("요리의 별칭을 수정합니다.>>");
				String subname = sc.nextLine();
				System.out.println("조리 과정을 수정합니다.>>");
				
				int ordernum = 1;
			//	sc.nextLine();
				RecipeDTO dto = new RecipeDTO();
				while(true){
						System.out.println(ordernum+"번째 조리 과정을 입력해주세요.");
						System.out.println("조리 과정 입력을 마치려면 end를 입력해주세요.");
						String ordercook = sc.nextLine();
						
					if(ordercook.equalsIgnoreCase("end")){
						break;
					}
					
					dto = new RecipeDTO(cooking, subname, recipenum, ordernum, ordercook, userid);
					
					list.add(dto);
					ordernum++;
				}
				
				int[] count = dao.RecipeUpdate(list);
				System.out.println(count+"건이 수정되었습니다.");
				
				System.out.println("다음과 같이 수정하였습니다.");
				for( RecipeDTO dto3 : dao.Recipecru(cooking, recipenum, userid)){
					System.out.println(dto3);
				}
	
			break;
		}
					
			
		
		case 5 : //레시피 삭제
		{
			System.out.println("등록된 레시피 삭제입니다.");
			System.out.println("삭제 할 레시피를 조회합니다.");
			System.out.println("사용자 id를 입력해주세요.");

			String userid = sc.nextLine();	//userid db를 만들어서 같은 id조회해서 없으면 빽	
			System.out.println("패스워드를 입력해주세요.");
			String password = sc.nextLine(); //해당 id의 패스워드를 조회해서 맞으면 다음으로 없으면 빽

			System.out.println("id에 등록 된 레시피를 조회합니다.");
			
			for(RecipeDTO redto : dao.RecipeUserid(userid)){
				System.out.println(redto);
			}
			
			System.out.println("1:: 특정 레시피 하나의 조리 과정만 삭제");
			System.out.println("2:: 특정 레시피 정보 모두 삭제");
			
			int num = sc.nextInt();
			sc.nextLine();			
		
			if(num ==1){
				//System.out.println("조리 과정");
				System.out.println("삭제 할 요리명을 입력해주세요.");
				String cooking = sc.nextLine();
				System.out.println("삭제 할 레시피 번호를 입력해주세요.");
				int recipenum = sc.nextInt();
				sc.nextLine();
				System.out.println("삭제 할 조리 번호를 입력해주세요.");
				int ordernum = sc.nextInt();
				sc.nextLine();

				dao.RecipeDelete(cooking, recipenum, ordernum, userid);
				System.out.println("삭제하였습니다.");
				if(dao.RecipeDelete(cooking, recipenum, ordernum, userid) == 1){
					System.out.println("삭제하였습니다.");
				}
				
			}
			
			if(num ==2){
					System.out.println("삭제 할 요리명을 입력해주세요.");
					String cooking = sc.next();
					System.out.println("삭제 할 레시피 번호를 입력해주세요.");
					int recipenum = sc.nextInt();
			
				
	
				
					int count = dao.RecipeDelete2(cooking, recipenum);
//					System.out.println(cooking+"레시피 정보를 모두 삭제하였습니다.");
					if(count >=1){
						System.out.println("총 "+count+"건을 삭제하였습니다.");
					}
					
			}
			
			
		//	dao.RecipeDelete
		}
			
			
		//			dto = new RecipeDTO();
		//			dto.setCooking(cooking);
		//			dto.setRecipenum(recipenum);
			
			
		}
		
		}
			
			
			
			
		}

	
	
	
	
				
				
				
				
	

}
