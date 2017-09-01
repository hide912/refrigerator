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

	// ������ �Լ�. ����� ���� ����.
	// selectAll(); // �����ȸ
	// selectByDuedate(Date day); //������ ��ȿ�Ⱓ�� ���� �� ��ȸ

	// ���� �ִ� ����� ���� �� �ִ� �丮 ������ ����
	// ��ȿ���� ���豺�� ������ ����� ���� �� �ִ� �丮 ������ ����
	// �� ���, ����ڰ� ������ ��Ḧ �߰��Ͽ� �װ͵� ��õ ���ؿ� �ݿ�
	// �ܼ� �з��� ��ȸ.
	// ��õ �丮���� �з����. or
	// ����� ��õ ���� ����� ��õ ���� ��õ���ؿ� �ݿ�(���� ��) + ��� ���� ���� �߰�

	Connection conn;
	PreparedStatement st; // spl�� ������ �޴� ���
	ResultSet rs; // �� ��ο��� ���� ���� ����
	
	InventoryDAO invendao = new InventoryDAO();
	InventoryView invenview = new InventoryView();
	Scanner sc = new Scanner(System.in);

	RecipeDAO recipedao = new RecipeDAO();
	RecipeView recipeview = new RecipeView();

	public static void main(String[] args) {

		MainDAO dao = new MainDAO();
//		 dao.ingredientsearch();

//	dao.Search_Select();
	dao.�ƹ�����();
	}

	public void ingredientsearch() {

		ArrayList<RecipeDTO> list = new ArrayList<RecipeDTO>();
	
		list = renewal3();
		
		for(int i =0; i< list.size(); i++){
			System.out.println(list.get(i).getUserid() +"���� ������, " +list.get(i).getCooking()+ "�丮��<"
					 + list.get(i).getSubname()+"> �� �����Դϴ�.");
			System.out.println(list.get(i).getOrdernum()+">>>");
			System.out.println(list.get(i).getOrdercook());
		}
	}

	
	
		//Ư�� ���� �Է����� ���� �� �ִ� �丮 ������ ����
	public void Search_Select(){
		Date curdate = new java.sql.Date(System.currentTimeMillis());
		
		List<InventoryDTO> alllist2 = new ArrayList<InventoryDTO>();	
		
		System.out.println("�Է��� ���� ���� �� �ִ� �丮�� �����Ǹ� �����մϴ�.");
		System.out.println("��Ḧ �Է����ּ���.");
		
		String sx = sc.nextLine();
				
		List<CookingIngDTO> cooklist2 = new ArrayList<>();
		CookingIngDAO cookinging = new CookingIngDAO();
		cooklist2 = cookinging.selectAll();				//cookinging�� �丮��, ����, �����ǳѹ� dto�� ������ ����Ʈ
			
		
		
		List<CookingIngDTO> cooklist3 = new ArrayList<>();
		for(int i =0; i<cooklist2.size() ; i++){
				if(sx.equals(cooklist2.get(i).getIngredient())){
					//System.out.println("��������");
					cooklist3.add(cooklist2.get(i));	//�Է��� ��ᰡ ���� cookinging�� �丮��, ����, �����ǳѹ� dto�� ������ ����Ʈ
			
				}
			}
		
		List<RecipeDTO> recipelist = new ArrayList<>();
		recipelist = recipedao.RecipeAll();	
													//�Է��� ������ �� �丮, ���, �����ǳѹ��� dto ����Ʈ��, recipe�� �丮, ������ �ѹ���
													// ���ؼ� �� �� ������ �� ������ ���� ���.
		System.out.println(sx+"�� �� �� �ִ� �丮 �����Ǹ� �����մϴ�.");
		System.out.println("========>>>>>>>>");
		
		ArrayList<RecipeDTO> searchlist = new ArrayList<>();		// ���� ��õ ������ �����Ұ���.
		
		for(int i=0; i< recipelist.size() ; i++){
			for(int j=0; j<cooklist3.size() ; j++){
				if(recipelist.get(i).getCooking().equals(cooklist3.get(j).getCooking())
						&& recipelist.get(i).getRecipenum() == cooklist3.get(j).getRecipenum()) {					
										searchlist.add(recipelist.get(i));
				}	
			}
		}				//searchlist ���� ��õ �����Ǹ� ���� dto ����Ʈ
		
		System.out.println("-----------------------------------------------------------------------------------");
		for(int i=0; i< searchlist.size(); i++){
			if(i ==0){
				System.out.println("|"+searchlist.get(i).getCooking()+" ������ �丮(�з���) �� (" +
						searchlist.get(i).getSubname()+")�� �丮�� �� �ֽ��ϴ�. \t\t| ������ �ѹ��� (" + searchlist.get(i).getRecipenum()+")�Դϴ�.|");
			}
			if(	i !=0 && !(searchlist.get(i).getCooking().equals(searchlist.get(i-1).getCooking()))
					//&& !(searchlist.get(i).getRecipenum() == searchlist.get(i-1).getRecipenum())
					){
				System.out.println("|"+searchlist.get(i).getCooking()+" ������ �丮(�з���) �� (" +
						searchlist.get(i).getSubname()+")�� �丮�� �� �ֽ��ϴ�. \t\t| ������ �ѹ��� (" + searchlist.get(i).getRecipenum()+")�Դϴ�.|");
			}
		}
		System.out.println("-----------------------------------------------------------------------------------");		

		System.out.println("�� �丮 �� ���ϴ� �丮�� �����Ǹ� �����ɴϴ�. �丮 �з���� ������ ��ȣ�� �Է����ּ���.");
		
	
		myloop:while(true){
			System.out.println("�丮 �з���?");
			System.out.println("��ġ���� end�� �Է����ּ���.");
			String cookingname = sc.nextLine();
			if(cookingname.equalsIgnoreCase("end")){
				System.out.println("�����մϴ�.");
				break myloop;
			}
			System.out.println("������ �ѹ�?");
			int recipenumber = sc.nextInt();
			sc.nextLine();
	//	Recipeselect(String cooking, int recipenum
		for(int i=0; i<searchlist.size() ; i++){
/*			if(!(searchlist.get(i).getCooking().equals(cookingname))){	//�Է¹��� String�� searchlist���� ���� ������ �ٸ� Ÿ���ΰ�?
				System.out.println("��õ �����ǿ� ���� �� �丮�� �ƴմϴ�. �˾ƺ��� ��� ��� ��������. �ٽ� �Է¤���");
				break;
			}
			if(searchlist.get(i).getRecipenum() != recipenumber){
				System.out.println("�Է��� ������ �ѹ��� �ش��ϴ� �丮�� �����ǰ� �����ϴ�. �ٽ� �Է� ����");
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

	//���� ����� �ִ� ���� ���� �� �ִ� �丮�� ��ȸ.
	 
	  //������ �������� ���� �� �ִ� �丮 ��ȸ, ������ ����
	  public void �ƹ�����(){ 
		  
		 System.out.println("�Է��� ������ �������� �� �� �ִ� �丮�� �����Ǹ� �����մϴ�.");
		 
		  IngredientSaveDAO savedao = new IngredientSaveDAO();
		  ArrayList<IngredientSaveDTO> savelist = new ArrayList<IngredientSaveDTO>();
		  
		  Scanner sc = new Scanner(System.in);
		  
		  while(true){
			  System.out.println("������ �Է����ּ���.");
			  System.out.println("�Է��� ��ġ���� end�� �Է����ּ���.");
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
		  System.out.println(cooklist.get(i).getCooking()+ "�� �丮�� �� �ֽ��ϴ�. �����ǹ�ȣ�� "+cooklist.get(i).getRecipenum()+"�Դϴ�.");
		  }
		  /*
		 for( int i =0; i<cooklist.size() ; i++){
		
			 if(i ==0){
				 System.out.println(cooklist.get(i).getCooking()+ "�� �丮�� �� �ֽ��ϴ�. �����ǹ�ȣ�� "+cooklist.get(i).getRecipenum()+"�Դϴ�.");
				// System.out.println(cooklist.get(i).getIngredient());
				 }else
				if(	i !=0 && !(cooklist.get(i).getCooking().equals(cooklist.get(i).getCooking()))
						|| !(cooklist.get(i).getRecipenum() == cooklist.get(i).getRecipenum())) {
						
					 System.out.println(cooklist.get(i).getCooking()+ "�� �丮�� �� �ֽ��ϴ�. �����ǹ�ȣ�� "+cooklist.get(i).getRecipenum()+"�Դϴ�.");
				}
					 //	 System.out.println(cooklist.get(i).getIngredient());
			 
		 }
	*/
		 myloop:while(true){
			 System.out.println("�������� �Ѿ�ϴ�. >>>>>>>>>> ����");
			 sc.nextLine();
		 System.out.println("���� �� �丮�� ������ �����մϴ�.");
		 System.out.println("�丮���� ���� �� �Է����ּ���.");
		 System.out.println("�������� end�� �Է����ּ���.");
		 String cname = sc.nextLine();
		 if(cname.equalsIgnoreCase("end")){
			 System.out.println("�����մϴ�.");
			 break myloop;
		 }
		 System.out.println("������ ��ȣ�� �Է����ּ���.");
		 int recinum = sc.nextInt(); 
		 sc.nextLine();
		 
		 List<String> cookinglist = new ArrayList<>(); 
		 CookingIngDAO cookingdao = new CookingIngDAO();
		 cookinglist = cookingdao.selectingred(cname, recinum);
		 
		 RecipeDAO recipedao = new RecipeDAO();
		 ArrayList<RecipeDTO> relist = new ArrayList<RecipeDTO>();
		 relist = recipedao.Recipeselect(cname, recinum);
		 
		 System.out.println(recinum+"�� " +cname+" �� ���� ���� ������ �����ϴ�.");
		 for(int i = 0; i< cookinglist.size() ; i++){
			 System.out.print(cookinglist.get(i)+"| ");
		 }
		 System.out.println();
		 System.out.println("�� �丮�� ������ ������ ������ 1��, �ٸ� �丮�� ������ ���ϸ� 2���� �����ּ���.");
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
	  
	  
	  
	  
	  
	  
	  //���� ����� ���� � �丮�� ���� �� �ִ���, �丮��� �����ǳѹ��� dto����Ʈ�� ����. �׷� ��Ȳ ����.(��� ����ŭ �ߺ� ���)
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


//�Է��� ����� � �丮�� ���� �� �ִ���, �丮��� �����ǳѹ��� dto����Ʈ�� ����. �׷� ��Ȳ ����.
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

//���� �ִ� ���� ���� �� �ִ� ��� ������ ����
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
	



	  
		//���� ����� ���� ���� �� �ִ� �丮�� ������ ����.( oredernum�� ordercook�� ����)




