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
		System.out.println("������ ��ȸ�Դϴ�.");
		
		myloop:while(true){
			
			System.out.println("���ϴ� ���񽺸� �������ּ���.");
			System.out.println("1:: ������ ��ü ��ȸ");
			System.out.println("2:: ������ ���� ��ȸ");
			System.out.println("3:: ������ ���");
			System.out.println("4:: ������ ���� ����");
			System.out.println("5:: ������ ����");
			
			
		int x = sc.nextInt();
		sc.nextLine();
		switch(x){
		
		case 1:  
		{
		System.out.println("������ ��ü ��ȸ�Դϴ�.");	
		view.All_View(dao.RecipeAll());
		
		break;
		}	
		
		case 2:
		{
			
			System.out.println("������ ���� ��ȸ�Դϴ�.");
			System.out.println("1:: �丮������ ������ ��ȸ");
			System.out.println("2:: ����� id�� ������ ��ȸ");
			int i = sc.nextInt();
			sc.nextLine();
			
			if(i == 1){
			System.out.println("���ϴ� �丮���� �Է����ּ���.");
			String cooking = sc.nextLine();
			
			ArrayList<RecipeDTO> list = new ArrayList<>(); 
		
			
			list = dao.RecipeCooking(cooking);
		
			if(list.size()==0){
				System.out.println("�Է��� �丮�� ���� ������ ��ȸ ����� �����ϴ�.");
			}
			
			if(list.size()==1){
			 System.out.println("�Է��� �丮�� ���� ������ ��ȸ ����� 1���Դϴ�.");
				view.All_View(list);
			}
			if(list.size() >1 ){
				System.out.println("�Է��� �丮�� ���� ������ ��ȸ ����� ������ �����ϴ�.");
				view.All_View(list);				
				System.out.println("���� �� ���ϴ� �������� ������ ��ȣ�� �Է����ּ���");
				int recipenum = sc.nextInt();
				sc.nextLine();
				view.RecipeView(dao.Recipeselect(cooking, recipenum));
				
				break;
			}
			
			}
			if(i ==2){
				System.out.println("�����id�� ������ �����մϴ�.");
				System.out.println("�����id�� �Է����ּ���.");
				String id = sc.nextLine();
				
				ArrayList<RecipeDTO> list2 = new ArrayList<>(); 				
				list2 = dao.RecipeCooking(id);
			
				if(list2.size()==0){
					System.out.println("������ ��ȸ ����� �����ϴ�.");
				}
				
				if(list2.size()==1){
				 System.out.println("������ ��ȸ ��� 1���Դϴ�.");
					view.All_View(list2);
				}
				if(list2.size() >1 ){
					System.out.println("����� id�� ���� ������ ��ȸ ����� ������ �����ϴ�.");
					view.All_View(list2);				
					System.out.println("���� �� ���ϴ� �丮���� �Է����ּ���.");
					String cooking = sc.nextLine();
					System.out.println("���� �� ���ϴ� �������� ������ ��ȣ�� �Է����ּ���");
					int recipenum2 = sc.nextInt();
					sc.nextLine();
					
					for(RecipeDTO rec : dao.Recipecru(cooking, recipenum2, id)){
					view.RecipeView(rec);
					}
					break;			
			}	
			
		 }	
			
		}//case 2 ��
			
			
			
		case 3:	//������ ���
		{
			
		System.out.println("������ ����Դϴ�.");	
		System.out.println("id�� �Է����ּ���.");
		String userid = sc.nextLine();	//userid db�� ���� ���� id��ȸ�ؼ� ������ ��	
		System.out.println("�н����带 �Է����ּ���.");
		String password = sc.nextLine(); //�ش� id�� �н����带 ��ȸ�ؼ� ������ �������� ������ ��
		System.out.println("����� �丮���� �Է����ּ���.");
		String cooking = sc.nextLine().trim();
	/*	if(cooking.equals(anObject).trim()){
			System.out.println("��� �� �丮���� �ƴմϴ�.");
			break;
		}		//Cooking ���̺� ��ϵ� �丮��� ������ 
		*/
		
		
		ArrayList<RecipeDTO> cookinglist = new ArrayList<RecipeDTO>(); 
										//��ϵǾ� �ִ� �丮��� ������ Ȯ���Ͽ� ������ �ѹ��� �ο��ϱ� ���� ����Ʈ
		
		
		
		int cNum = dao.getRecipeNum(cooking);
		System.out.println(cNum+"�� ������ ��ȣ�� �ο��Ǿ����ϴ�.");
		
		
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
												//��ϵǾ� �ִ� �丮��� ������ Ȯ���Ͽ� �׿� ���� ����ŭ recipenum++		
		
				//recipenum �ο� ����
		
		
		
		System.out.println("�丮�� ��Ī�� �Է����ּ���.");
		System.out.println("�丮�� ��Ī�� �ٸ� ����� ���� �丮�� ����ȭ��Ű�� ��� �丮���� �̸��Դϴ�. ex) '������' ¥���");
		String subname = sc.nextLine();
		
			ArrayList<RecipeDTO> list = new ArrayList();
			
			int ordernum=1;
		//	int i=1;
			while(true){
				System.out.println(ordernum +"��° ���� ������ ������ �Է��ϰ� ���͸� �����ּ���.");
				System.out.println("�Է��� ��ġ���� end�� �Է����ּ���.");
				String ordercook = sc.nextLine();
				if(ordercook.equalsIgnoreCase("end")){
					break;
				}
				
				dto = new RecipeDTO(cooking, subname,cNum, ordernum, ordercook, userid);
				list.add(dto);
				System.out.println(dto);
				ordernum++;
			}
			//addBatch �̿��� �ϰ� sqló�� �Լ� ����
			int[] results = dao.ManyInsert(list);
			if(results.length >= 0){
				System.out.println("�����Ǹ� ����Ͽ����ϴ�.");
				System.out.println(results);
			}
	
			break;
		}
		
		case 4 :	//������ ���� 
		{
			System.out.println("��ϵ� ������ �����Դϴ�.");
			
			System.out.println("id�� �Է����ּ���.");
			String userid = sc.nextLine();	//userid db�� ���� ���� id��ȸ�ؼ� ������ ��	
			System.out.println("�н����带 �Է����ּ���.");
			String password = sc.nextLine(); //�ش� id�� �н����带 ��ȸ�ؼ� ������ �������� ������ ��
			
//			System.out.println("1:: ���������� ������ ������ ������ ����");
	//		System.out.println("2:: �ٸ� ������ ������ �����ϰ� ������������ ����");
			
		
				
			//	System.out.println("1:: ���� ������ ������ ������ �����մϴ�.");
		//		System.out.println("�Է��� id�� ��ϵ� �����Ǵ� ������ �����ϴ�.");
				
			//	for( RecipeDTO dto : dao.RecipeUserid(userid)){
		//		System.out.println("�丮��:: "+dto.getCooking()+"������ �ѹ�:: "+ dto.getRecipenum());
		//		}
				
				System.out.println("���� �� ���� �� �丮���� �Է����ּ���.");
				String cooking = sc.nextLine();
				System.out.println("���� �� ���� �� ������ ��ȣ�� �Է����ּ���.");
				int recipenum = sc.nextInt();
				sc.nextLine();	
				
				ArrayList<RecipeDTO> list = new ArrayList<>();
								
				list = dao.Recipecru(cooking, recipenum, userid);
				
				for(RecipeDTO dto2 : list){
					System.out.println(dto2);
				}
				System.out.println("�� �����Ǹ� �����մϴ�.");
				System.out.println("�丮���� �����մϴ�. >>");
				cooking = sc.nextLine();
				System.out.println("�丮�� ��Ī�� �����մϴ�.>>");
				String subname = sc.nextLine();
				System.out.println("���� ������ �����մϴ�.>>");
				
				int ordernum = 1;
			//	sc.nextLine();
				RecipeDTO dto = new RecipeDTO();
				while(true){
						System.out.println(ordernum+"��° ���� ������ �Է����ּ���.");
						System.out.println("���� ���� �Է��� ��ġ���� end�� �Է����ּ���.");
						String ordercook = sc.nextLine();
						
					if(ordercook.equalsIgnoreCase("end")){
						break;
					}
					
					dto = new RecipeDTO(cooking, subname, recipenum, ordernum, ordercook, userid);
					
					list.add(dto);
					ordernum++;
				}
				
				int[] count = dao.RecipeUpdate(list);
				System.out.println(count+"���� �����Ǿ����ϴ�.");
				
				System.out.println("������ ���� �����Ͽ����ϴ�.");
				for( RecipeDTO dto3 : dao.Recipecru(cooking, recipenum, userid)){
					System.out.println(dto3);
				}
	
			break;
		}
					
			
		
		case 5 : //������ ����
		{
			System.out.println("��ϵ� ������ �����Դϴ�.");
			System.out.println("���� �� �����Ǹ� ��ȸ�մϴ�.");
			System.out.println("����� id�� �Է����ּ���.");

			String userid = sc.nextLine();	//userid db�� ���� ���� id��ȸ�ؼ� ������ ��	
			System.out.println("�н����带 �Է����ּ���.");
			String password = sc.nextLine(); //�ش� id�� �н����带 ��ȸ�ؼ� ������ �������� ������ ��

			System.out.println("id�� ��� �� �����Ǹ� ��ȸ�մϴ�.");
			
			for(RecipeDTO redto : dao.RecipeUserid(userid)){
				System.out.println(redto);
			}
			
			System.out.println("1:: Ư�� ������ �ϳ��� ���� ������ ����");
			System.out.println("2:: Ư�� ������ ���� ��� ����");
			
			int num = sc.nextInt();
			sc.nextLine();			
		
			if(num ==1){
				//System.out.println("���� ����");
				System.out.println("���� �� �丮���� �Է����ּ���.");
				String cooking = sc.nextLine();
				System.out.println("���� �� ������ ��ȣ�� �Է����ּ���.");
				int recipenum = sc.nextInt();
				sc.nextLine();
				System.out.println("���� �� ���� ��ȣ�� �Է����ּ���.");
				int ordernum = sc.nextInt();
				sc.nextLine();

				dao.RecipeDelete(cooking, recipenum, ordernum, userid);
				System.out.println("�����Ͽ����ϴ�.");
				if(dao.RecipeDelete(cooking, recipenum, ordernum, userid) == 1){
					System.out.println("�����Ͽ����ϴ�.");
				}
				
			}
			
			if(num ==2){
					System.out.println("���� �� �丮���� �Է����ּ���.");
					String cooking = sc.next();
					System.out.println("���� �� ������ ��ȣ�� �Է����ּ���.");
					int recipenum = sc.nextInt();
			
				
	
				
					int count = dao.RecipeDelete2(cooking, recipenum);
//					System.out.println(cooking+"������ ������ ��� �����Ͽ����ϴ�.");
					if(count >=1){
						System.out.println("�� "+count+"���� �����Ͽ����ϴ�.");
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
