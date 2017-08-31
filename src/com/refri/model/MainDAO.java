package com.refri.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.refri.view.InventoryView;
import com.refri.view.RecipeView;

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
		 * = inven.selectAll(); List<String> alllist = new ArrayList<>(); //����
		 * �ִ� ����� ���� �������� ������ ����Ʈ for(int i=0; i<alllist.size(); i++){
		 * alllist.add(alllist2.get(i).getIngredient());
		 * System.out.println(alllist.get(i)); }
		 */
		// StringŸ���� ����Ʈ ���� �� �ȵ���?

		/*
		 * Date curdate = new java.sql.Date(System.currentTimeMillis());
		 * List<InventoryDTO> duelist2 = new ArrayList<InventoryDTO>(); //����
		 * ��ȿ���� 1���� ���� ��� ���� duelist2 = inven.selectByDuedate(curdate);
		 * List<String> duelist = new ArrayList(); // ���� �ִ� ��� �� ��ȿ������ 1���� ������
		 * ������ ������ ����Ʈ for(int i=0; i<duelist2.size(); i++ ){
		 * duelist.add(duelist2.get(i).getIngredient()); }
		 */

		List<InventoryDTO> alllist2 = new ArrayList<InventoryDTO>();
		alllist2 = invendao.selectAll(); // ���� �ִ� ��Ḧ ������ ����Ʈ

		List<CookingIngDTO> cooklist2 = new ArrayList<>();
		CookingIngDAO cookinging = new CookingIngDAO();
		cooklist2 = cookinging.selectAll(); // cookinging�� �丮��, ����, �����ǳѹ� dto��
											// ������ ����Ʈ
		/*
		 * List<String> cooklist = new ArrayList<>();
		 * 
		 * for(int i =0; i< cooklist2.size(); i++){
		 * cooklist.add(cooklist2.get(i).getIngredient()); }
		 */

		List<CookingIngDTO> cooklist3 = new ArrayList<>();
		for (int i = 0; i < alllist2.size(); i++) {
			for (int j = 0; j < cooklist2.size(); j++) {
				if (alllist2.get(i).getIngredient().equals(cooklist2.get(j).getIngredient())) { // �����
																								// �ִ�
																								// ����
																								// cookinging��
																								// �ִ�
																								// ������
																								// ������
					cooklist3.add(cooklist2.get(j)); // �� ��Ḧ �����ϴ� �丮 dto�� get�ؼ�
														// cooklist3�� ����.
				} // �丮�� ���� �����ǳѹ�
			}
		}

		System.out.println("���� �ִ� ���� ���� �� �ִ� �丮�� ��ȸ�մϴ�. >>>>>");

		List<RecipeDTO> recipelist = new ArrayList<>();
		recipelist = recipedao.RecipeAll();
		// �Է��� ������ �� �丮, ���, �����ǳѹ��� dto ����Ʈ��, recipe�� �丮, ������ �ѹ���
		// ���ؼ� �� �� ������ �� ������ ���� ���.

		// �����Ǹ���Ʈ������ �����ǳѹ��� �丮��� �� cooklist3������ �丮��� �����ǳѹ��� ������ Ȯ���Ͽ�
		// �´ٸ� �����Ǹ���Ʈ���� ������ get
		
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
			if(i !=0 && !(searchlist.get(i).getCooking().equals(searchlist.get(i-1).getCooking()))){
				System.out.println("|"+searchlist.get(i).getCooking()+" ������ �丮(�з���) �� (" +
						searchlist.get(i).getSubname()+")�� �丮�� �� �ֽ��ϴ�. \t\t| ������ �ѹ��� (" + searchlist.get(i).getRecipenum()+")�Դϴ�.|");
			}
		}
		System.out.println("-----------------------------------------------------------------------------------");		
	/*		
			for(int j=1; j< searchlist.size(); j++){
					if( searchlist.get(i).getCooking().equals(searchlist.get(j).getCooking())){
					break;
					}
			System.out.println(searchlist.get(i).getCooking()+" ������ �丮(�з���) �� (" +
					searchlist.get(i).getSubname()+")�� �丮�� �� �ֽ��ϴ�. ������ �ѹ��� (" + searchlist.get(i).getRecipenum()+")�Դϴ�.");
		}
		}*/
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

	 
	  
	  public void ingredient(){ 
		  
		  	//� ��ῡ �ش��ϸ�,(get(i).���)  �� ���� �� ����� �丮�� ī��Ʈ++ (get(i).�丮��� count++
		  
	
	  }
	  
}