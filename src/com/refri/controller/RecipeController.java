package com.refri.controller;

import java.sql.SQLException;
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
		
		dao.RecipeAll();
		
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
			if(i ==2){
				System.out.println("사용자id로 레시피 선택합니다.");
				System.out.println("사용자id를 입력해주세요.");
				String cooking2 = sc.nextLine();
				
				ArrayList<RecipeDTO> list2 = new ArrayList<>(); 
			
				
				list = dao.RecipeCooking(cooking2);
			
				if(list.size()==0){
					System.out.println("입력한 요리에 대한 레시피 조회 결과는 없습니다.");
				}
				
				if(list.size()==1){
				 System.out.println("입력한 요리에 대한 레시피 조회 결과는 1건입니다.");
					view.All_View(list2);
				}
				if(list.size() >1 ){
					System.out.println("입력한 요리에 대한 레시피 조회 결과는 다음과 같습니다.");
					view.All_View(list2);				
					System.out.println("다음 중 원하는 레시피의 레시피 번호를 입력해주세요");
					int recipenum2 = sc.nextInt();
					sc.nextLine();
					view.RecipeView(dao.Recipeselect(cooking2, recipenum2));
					
					break;			
			}	
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
										//등록되어 있는 요리명과 등록할 같은지 확인하여 레시피 넘버를 부여하기 위한 리스트
		
		cookinglist = dao.RecipeCooking(cooking);
	
		int recipenum = 1;
	
		for(int i=0; i<cookinglist.size(); i++){
			if(cookinglist.get(i).equals(cooking)){
				recipenum++;								//등록되어 있는 요리명과 같은지 확인하여 그와 같은 수만큼 recipenum++
			}
				
		}
		
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
				
				dto = new RecipeDTO(cooking, subname,recipenum, ordernum, ordercook, userid);
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
	
			
		}
		
		case 4 :	//레시피 수정 
		{
			System.out.println("등록된 레시피 수정입니다.");
			
			System.out.println("id를 입력해주세요.");
			String userid = sc.nextLine();	//userid db를 만들어서 같은 id조회해서 없으면 빽	
			System.out.println("패스워드를 입력해주세요.");
			String password = sc.nextLine(); //해당 id의 패스워드를 조회해서 맞으면 다음으로 없으면 빽
			
			
			//이거 레시피 내용만 수정하도록 하면 모를까, 그 외의 것들 수정하게 만들면 조리 순서에 의해 중복되는 값들 일괄변경 해야하잖아?
			
		}
					
			
		
		case 5 : //레시피 삭제
		{
			System.out.println("등록된 레시피 삭제입니다.");
			System.out.println("삭제 할 레시피를 조회합니다.");
			
		//	dao.RecipeDelete
		}
			
			
			
			
			
		}
		
		}
			
			
			
			
		}

	
	
	
	
				
				
				
				
	

}
