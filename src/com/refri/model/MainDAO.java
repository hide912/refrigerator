package com.refri.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.refri.view.InventoryView;
import com.refri.view.RecipeView;

public class MainDAO {

	// 재고관리 함수. 냉장고 상태 갱신.
	// selectAll(); // 모두조회
	// selectByDuedate(Date day); //일주일 유효기간이 남은 건 조회

	// 현재 있는 재료들로 만들 수 있는 요리 레시피 제공
	// 유효기한 위험군에 지정된 재료들로 만들 수 있는 요리 레시피 제공
	// 위 경우, 사용자가 임의의 재료를 추가하여 그것도 추천 기준에 반영
	// 단순 분류별 조회.
	// 추천 요리에서 분류기능. or
	// 사용자 추천 수를 만들어 추천 수도 추천기준에 반영(별점 등) + 평론 등의 리뷰 추가

	InventoryDAO invendao = new InventoryDAO();
	InventoryView invenview = new InventoryView();
	Scanner sc = new Scanner(System.in);

	RecipeDAO recipedao = new RecipeDAO();
	RecipeView recipeview = new RecipeView();

	public static void main(String[] args) {

		MainDAO dao = new MainDAO();
		 dao.ingredientsearch();

		//dao.Search_Select();

	}

	public void ingredientsearch() {

		/*
		 * List<InventoryDTO> alllist2 = new ArrayList<InventoryDTO>(); alllist2
		 * = inven.selectAll(); List<String> alllist = new ArrayList<>(); //현재
		 * 있는 재료의 재료명 정보만을 저장한 리스트 for(int i=0; i<alllist.size(); i++){
		 * alllist.add(alllist2.get(i).getIngredient());
		 * System.out.println(alllist.get(i)); }
		 */
		// String타입의 리스트 저장 왜 안되지?

		/*
		 * Date curdate = new java.sql.Date(System.currentTimeMillis());
		 * List<InventoryDTO> duelist2 = new ArrayList<InventoryDTO>(); //현재
		 * 유효기한 1주일 이하 재료 정보 duelist2 = inven.selectByDuedate(curdate);
		 * List<String> duelist = new ArrayList(); // 현재 있는 재료 중 유효기한이 1주일 이하인
		 * 재료명만을 저장한 리스트 for(int i=0; i<duelist2.size(); i++ ){
		 * duelist.add(duelist2.get(i).getIngredient()); }
		 */

		List<InventoryDTO> alllist2 = new ArrayList<InventoryDTO>();
		alllist2 = invendao.selectAll(); // 현재 있는 재료를 저장한 리스트

		List<CookingIngDTO> cooklist2 = new ArrayList<>();
		CookingIngDAO cookinging = new CookingIngDAO();
		cooklist2 = cookinging.selectAll(); // cookinging의 요리명, 재료명, 레시피넘버 dto를
											// 저장한 리스트
		/*
		 * List<String> cooklist = new ArrayList<>();
		 * 
		 * for(int i =0; i< cooklist2.size(); i++){
		 * cooklist.add(cooklist2.get(i).getIngredient()); }
		 */

		List<CookingIngDTO> cooklist3 = new ArrayList<>();
		for (int i = 0; i < alllist2.size(); i++) {
			for (int j = 0; j < cooklist2.size(); j++) {
				if (alllist2.get(i).getIngredient().equals(cooklist2.get(j).getIngredient())) { // 냉장고에
																								// 있는
																								// 재료와
																								// cookinging에
																								// 있는
																								// 재료명이
																								// 맞으면
					cooklist3.add(cooklist2.get(j)); // 그 재료를 포함하는 요리 dto를 get해서
														// cooklist3에 저장.
				} // 요리명 재료명 레시피넘버
			}
		}

		System.out.println("현재 있는 재료로 만들 수 있는 요리를 조회합니다. >>>>>");

		List<RecipeDTO> recipelist = new ArrayList<>();
		recipelist = recipedao.RecipeAll();
		// 입력한 재료명이 들어간 요리, 재료, 레시피넘버의 dto 리스트와, recipe의 요리, 레시피 넘버를
		// 비교해서 둘 다 같으면 그 레시피 정보 출력.

		// 레시피리스트에서의 레시피넘버와 요리명과 위 cooklist3에서의 요리명과 레시피넘버가 같은지 확인하여
		// 맞다면 레시피리스트에서 레시피 get
		
		List<RecipeDTO> searchlist = new ArrayList<>();
		
		for (int i = 0; i < recipelist.size(); i++) {
			for (int j = 0; j < cooklist3.size(); j++) {
				if (recipelist.get(i).getCooking().equals(cooklist3.get(j).getCooking())
						&& (recipelist.get(i).getRecipenum() == cooklist3.get(j).getRecipenum())) {
		
						searchlist.add(recipelist.get(i));
					}
				// System.out.println(cooklist3.get(j).getIngredient());
			}
		}

	}

	

	
	
		//특정 재료명 입력으로 만들 수 있는 요리 레시피 제공
	public void Search_Select(){
		Date curdate = new java.sql.Date(System.currentTimeMillis());
		
		List<InventoryDTO> alllist2 = new ArrayList<InventoryDTO>();	
		
		System.out.println("입력한 재료로 만들 수 있는 요리의 레시피를 제공합니다.");
		System.out.println("재료를 입력해주세요.");
		
		String sx = sc.nextLine();
				
		List<CookingIngDTO> cooklist2 = new ArrayList<>();
		CookingIngDAO cookinging = new CookingIngDAO();
		cooklist2 = cookinging.selectAll();				//cookinging의 요리명, 재료명, 레시피넘버 dto를 저장한 리스트
			
		
		
		List<CookingIngDTO> cooklist3 = new ArrayList<>();
		for(int i =0; i<cooklist2.size() ; i++){
				if(sx.equals(cooklist2.get(i).getIngredient())){
					//System.out.println("ㅇㅇㅇㅇ");
					cooklist3.add(cooklist2.get(i));	//입력한 재료가 들어가는 cookinging의 요리명, 재료명, 레시피넘버 dto를 저장한 리스트
			
				}
			}

		
		List<RecipeDTO> recipelist = new ArrayList<>();
		recipelist = recipedao.RecipeAll();	
													//입력한 재료명이 들어간 요리, 재료, 레시피넘버의 dto 리스트와, recipe의 요리, 레시피 넘버를
													// 비교해서 둘 다 같으면 그 레시피 정보 출력.
		System.out.println(sx+"로 할 수 있는 요리 레시피를 제공합니다.");
		System.out.println("========>>>>>>>>");
		
		ArrayList<RecipeDTO> searchlist = new ArrayList<>();		// 최종 추천 레시피 저장할거임.
		
		for(int i=0; i< recipelist.size() ; i++){
			for(int j=0; j<cooklist3.size() ; j++){
				if(recipelist.get(i).getCooking().equals(cooklist3.get(j).getCooking())
						&& recipelist.get(i).getRecipenum() == cooklist3.get(j).getRecipenum()) {					
										searchlist.add(recipelist.get(i));
				}	
			}
		}				//searchlist 최종 추천 레시피만 들은 dto 리스트
		
		System.out.println("-----------------------------------------------------------------------------------");
		for(int i=0; i< searchlist.size(); i++){
			if(i ==0){
				System.out.println("|"+searchlist.get(i).getCooking()+" 종류의 요리(분류명) 중 (" +
						searchlist.get(i).getSubname()+")을 요리할 수 있습니다. \t\t| 레시피 넘버는 (" + searchlist.get(i).getRecipenum()+")입니다.|");
			}
			if(i !=0 && !(searchlist.get(i).getCooking().equals(searchlist.get(i-1).getCooking()))){
				System.out.println("|"+searchlist.get(i).getCooking()+" 종류의 요리(분류명) 중 (" +
						searchlist.get(i).getSubname()+")을 요리할 수 있습니다. \t\t| 레시피 넘버는 (" + searchlist.get(i).getRecipenum()+")입니다.|");
			}
		}
		System.out.println("-----------------------------------------------------------------------------------");		
	/*		
			for(int j=1; j< searchlist.size(); j++){
					if( searchlist.get(i).getCooking().equals(searchlist.get(j).getCooking())){
					break;
					}
			System.out.println(searchlist.get(i).getCooking()+" 종류의 요리(분류명) 중 (" +
					searchlist.get(i).getSubname()+")을 요리할 수 있습니다. 레시피 넘버는 (" + searchlist.get(i).getRecipenum()+")입니다.");
		}
		}*/
		System.out.println("위 요리 중 원하는 요리의 레시피를 가져옵니다. 요리 분류명과 레시피 번호를 입력해주세요.");
		
	
		myloop:while(true){
			System.out.println("요리 분류명?");
			System.out.println("마치려면 end를 입력해주세요.");
			String cookingname = sc.nextLine();
			if(cookingname.equalsIgnoreCase("end")){
				System.out.println("종료합니다.");
				break myloop;
			}
			System.out.println("레시피 넘버?");
			int recipenumber = sc.nextInt();
			sc.nextLine();
	//	Recipeselect(String cooking, int recipenum
		for(int i=0; i<searchlist.size() ; i++){
/*			if(!(searchlist.get(i).getCooking().equals(cookingname))){	//입력받은 String과 searchlist에서 꺼낸 문장은 다른 타입인가?
				System.out.println("추천 레시피에 포함 된 요리가 아닙니다. 알아봤자 재료 없어서 못만들어요. 다시 입력ㄱㄱ");
				break;
			}
			if(searchlist.get(i).getRecipenum() != recipenumber){
				System.out.println("입력한 레시피 넘버에 해당하는 요리의 레시피가 없습니다. 다시 입력 ㄱㄱ");
				break;
			}*/
			if(searchlist.get(i).getCooking().equals(cookingname.trim()) && 
					searchlist.get(i).getRecipenum() == recipenumber){
					System.out.println(searchlist.get(i).getOrdernum()+">>>");
					System.out.println(searchlist.get(i).getOrdercook());
			}
			
		}
			System.out.println("====================");
		}
	}

	 
	  
	  public void ingredient(){ 
		  
		  	//어떤 재료에 해당하면,(get(i).재료)  그 시점 그 재료의 요리명에 카운트++ (get(i).요리명과 count++
		  
	
	  }
	  
}