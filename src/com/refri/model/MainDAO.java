package com.refri.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.refri.view.InventoryView;
import com.refri.view.RecipeView;

import util.DBUtil;

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

	Connection conn;
	PreparedStatement st; // spl문 보내고 받는 통로
	ResultSet rs; // 그 통로에서 받은 값을 리턴
	
	InventoryDAO invendao = new InventoryDAO();
	InventoryView invenview = new InventoryView();
	Scanner sc = new Scanner(System.in);

	RecipeDAO recipedao = new RecipeDAO();
	RecipeView recipeview = new RecipeView();

	public static void main(String[] args) {

		MainDAO dao = new MainDAO();
//		 dao.ingredientsearch();

//	dao.Search_Select();
	dao.아뭐먹지();
	}

	public void ingredientsearch() {

		ArrayList<RecipeDTO> list = new ArrayList<RecipeDTO>();
	
		list = renewal3();
		
		for(int i =0; i< list.size(); i++){
			System.out.println(list.get(i).getUserid() +"님의 레시피, " +list.get(i).getCooking()+ "요리인<"
					 + list.get(i).getSubname()+"> 의 정보입니다.");
			System.out.println(list.get(i).getOrdernum()+">>>");
			System.out.println(list.get(i).getOrdercook());
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
			if(	i !=0 && !(searchlist.get(i).getCooking().equals(searchlist.get(i-1).getCooking()))
					//&& !(searchlist.get(i).getRecipenum() == searchlist.get(i-1).getRecipenum())
					){
				System.out.println("|"+searchlist.get(i).getCooking()+" 종류의 요리(분류명) 중 (" +
						searchlist.get(i).getSubname()+")을 요리할 수 있습니다. \t\t| 레시피 넘버는 (" + searchlist.get(i).getRecipenum()+")입니다.|");
			}
		}
		System.out.println("-----------------------------------------------------------------------------------");		

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

	//현재 냉장고에 있는 재료로 만들 수 있는 요리를 조회.
	 
	  //복수의 재료명으로 만들 수 있는 요리 조회, 레시피 제공
	  public void 아뭐먹지(){ 
		  
		 System.out.println("입력한 복수의 재료명으로 할 수 있는 요리의 레시피를 제공합니다.");
		 
		  IngredientSaveDAO savedao = new IngredientSaveDAO();
		  ArrayList<IngredientSaveDTO> savelist = new ArrayList<IngredientSaveDTO>();
		  
		  Scanner sc = new Scanner(System.in);
		  
		  while(true){
			  System.out.println("재료명을 입력해주세요.");
			  System.out.println("입력을 마치려면 end를 입력해주세요.");
			  String st = sc.nextLine();
			  if(st.equalsIgnoreCase("end")){
				  break;
			  }
			  IngredientSaveDTO savedto = new IngredientSaveDTO(st);	  
			  savelist.add(savedto);
		  }
		  
		  int[] saveint = savedao.Saveingred(savelist);
		  

		  ArrayList<CookingIngDTO> cooklist = new ArrayList<>();
		  cooklist = renewal2();
		  for( int i =0; i<cooklist.size() ; i++){
		  System.out.println(cooklist.get(i).getCooking()+ "을 요리할 수 있습니다. 레시피번호는 "+cooklist.get(i).getRecipenum()+"입니다.");
		  }
		  /*
		 for( int i =0; i<cooklist.size() ; i++){
		
			 if(i ==0){
				 System.out.println(cooklist.get(i).getCooking()+ "을 요리할 수 있습니다. 레시피번호는 "+cooklist.get(i).getRecipenum()+"입니다.");
				// System.out.println(cooklist.get(i).getIngredient());
				 }else
				if(	i !=0 && !(cooklist.get(i).getCooking().equals(cooklist.get(i).getCooking()))
						|| !(cooklist.get(i).getRecipenum() == cooklist.get(i).getRecipenum())) {
						
					 System.out.println(cooklist.get(i).getCooking()+ "을 요리할 수 있습니다. 레시피번호는 "+cooklist.get(i).getRecipenum()+"입니다.");
				}
					 //	 System.out.println(cooklist.get(i).getIngredient());
			 
		 }
	*/
		 myloop:while(true){
			 System.out.println("다음으로 넘어갑니다. >>>>>>>>>> 엔터");
			 sc.nextLine();
		 System.out.println("선택 한 요리의 정보를 제공합니다.");
		 System.out.println("요리명을 선택 해 입력해주세요.");
		 System.out.println("끝내려면 end를 입력해주세요.");
		 String cname = sc.nextLine();
		 if(cname.equalsIgnoreCase("end")){
			 System.out.println("종료합니다.");
			 break myloop;
		 }
		 System.out.println("레시피 번호를 입력해주세요.");
		 int recinum = sc.nextInt(); 
		 sc.nextLine();
		 
		 List<String> cookinglist = new ArrayList<>(); 
		 CookingIngDAO cookingdao = new CookingIngDAO();
		 cookinglist = cookingdao.selectingred(cname, recinum);
		 
		 RecipeDAO recipedao = new RecipeDAO();
		 ArrayList<RecipeDTO> relist = new ArrayList<RecipeDTO>();
		 relist = recipedao.Recipeselect(cname, recinum);
		 
		 System.out.println(recinum+"번 " +cname+" 에 들어가는 재료는 다음과 같습니다.");
		 for(int i = 0; i< cookinglist.size() ; i++){
			 System.out.print(cookinglist.get(i)+"| ");
		 }
		 System.out.println();
		 System.out.println("본 요리의 레시피 정보를 보려면 1번, 다른 요리의 정보를 원하면 2번을 눌러주세요.");
		 int x = sc.nextInt();
		 sc.nextLine();
		 if(x==1){
			
			
			 for(int i=0; i<relist.size(); i++){
			 System.out.println(relist.get(i).getOrdernum()+" >>>>>");
			 System.out.println(relist.get(i).getOrdercook());
			 }
		 }
		
		 }

			int count =  savedao.Deleteingred();
			System.out.println(count);
	  }
	  
	  
	  
	  
	  
	  
	  //현재 냉장고 재료로 어떤 요리를 만들 수 있는지, 요리명과 레시피넘버를 dto리스트로 리턴. 그런 현황 갱신.(재료 수만큼 중복 허용)
public ArrayList<CookingIngDTO> renewal(){
	conn = DBUtil.getConnect();
	CookingIngDTO dto = new CookingIngDTO();
	ArrayList<CookingIngDTO> list = new ArrayList<>();

  String sql =  "select cooking, RECIPENUM, cookinging.ingredient "
		+ " from inventory,  cookinging"
		+ " where inventory.ingredient = cookinging.ingredient";

try{
st = conn.prepareStatement(sql);

rs = st.executeQuery();
while (rs.next()) {

	 String cooking = rs.getString(1);
	 String ingredient = rs.getString(3);
	 int recipenum = rs.getInt(2);
		 
	dto = new CookingIngDTO(cooking, recipenum);	
	list.add(dto);
}
}catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, st, rs);
		}
		return list;
}


//입력한 재료들로 어떤 요리를 만들 수 있는지, 요리명과 레시피넘버를 dto리스트로 리턴. 그런 현황 갱신.
public ArrayList<CookingIngDTO> renewal2(){
	conn = DBUtil.getConnect();
	CookingIngDTO dto = new CookingIngDTO();
	ArrayList<CookingIngDTO> list = new ArrayList<>();

	String sql =  "select distinct cooking, RECIPENUM "
		+ " from  ingredientsave,  cookinging"
		+ " where  ingredientsave.ingredient = cookinging.ingredient";
	 
	try{
		st = conn.prepareStatement(sql);
		
		rs = st.executeQuery();
		while (rs.next()) {
			
			String cooking = rs.getString(1);
			int recipenum = rs.getInt(2);
			dto = new CookingIngDTO(cooking, recipenum);	
			list.add(dto);
		}
	}catch(SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		DBUtil.dbClose(conn, st, rs);
	}
	return list;
}

//현재 있는 재료로 만들 수 있는 모든 레시피 정보
public ArrayList<RecipeDTO> renewal3(){
	conn = DBUtil.getConnect();
	RecipeDTO dto = new RecipeDTO();
	ArrayList<RecipeDTO> list = new ArrayList<>();
	
	String sql =  "select* "
			+ " from  recipe where (cooking, recipenum) in ( "
			+ " select cooking, RECIPENUM  from inventory,  cookinging  where inventory.ingredient = cookinging.ingredient  ) ";
			
	try{
		st = conn.prepareStatement(sql);
		
		rs = st.executeQuery();
		while (rs.next()) {
			
			String cooking = rs.getString(1);
			String subname = rs.getString(2);
			int recipenum = rs.getInt(3);
			int ordernum = rs.getInt(4);
			String ordercook = rs.getString(5);
			String userid = rs.getString(6);
			
			dto = new RecipeDTO( cooking, subname, recipenum, ordernum, ordercook, userid);	
			list.add(dto);
		}
	}catch(SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		DBUtil.dbClose(conn, st, rs);
	}
	return list;
}
	

}
	



	  
		//현재 냉장고 재료로 만들 수 있는 요리의 레시피 제공.( oredernum과 ordercook만 제공)




