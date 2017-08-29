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
		
		dao.RecipeAll();
		
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
			if(i ==2){
				System.out.println("�����id�� ������ �����մϴ�.");
				System.out.println("�����id�� �Է����ּ���.");
				String cooking2 = sc.nextLine();
				
				ArrayList<RecipeDTO> list2 = new ArrayList<>(); 
			
				
				list = dao.RecipeCooking(cooking2);
			
				if(list.size()==0){
					System.out.println("�Է��� �丮�� ���� ������ ��ȸ ����� �����ϴ�.");
				}
				
				if(list.size()==1){
				 System.out.println("�Է��� �丮�� ���� ������ ��ȸ ����� 1���Դϴ�.");
					view.All_View(list2);
				}
				if(list.size() >1 ){
					System.out.println("�Է��� �丮�� ���� ������ ��ȸ ����� ������ �����ϴ�.");
					view.All_View(list2);				
					System.out.println("���� �� ���ϴ� �������� ������ ��ȣ�� �Է����ּ���");
					int recipenum2 = sc.nextInt();
					sc.nextLine();
					view.RecipeView(dao.Recipeselect(cooking2, recipenum2));
					
					break;			
			}	
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
										//��ϵǾ� �ִ� �丮��� ����� ������ Ȯ���Ͽ� ������ �ѹ��� �ο��ϱ� ���� ����Ʈ
		
		cookinglist = dao.RecipeCooking(cooking);
	
		int recipenum = 1;
	
		for(int i=0; i<cookinglist.size(); i++){
			if(cookinglist.get(i).equals(cooking)){
				recipenum++;								//��ϵǾ� �ִ� �丮��� ������ Ȯ���Ͽ� �׿� ���� ����ŭ recipenum++
			}
				
		}
		
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
				
				dto = new RecipeDTO(cooking, subname,recipenum, ordernum, ordercook, userid);
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
	
			
		}
		
		case 4 :	//������ ���� 
		{
			System.out.println("��ϵ� ������ �����Դϴ�.");
			
			System.out.println("id�� �Է����ּ���.");
			String userid = sc.nextLine();	//userid db�� ���� ���� id��ȸ�ؼ� ������ ��	
			System.out.println("�н����带 �Է����ּ���.");
			String password = sc.nextLine(); //�ش� id�� �н����带 ��ȸ�ؼ� ������ �������� ������ ��
			
			
			//�̰� ������ ���븸 �����ϵ��� �ϸ� �𸦱�, �� ���� �͵� �����ϰ� ����� ���� ������ ���� �ߺ��Ǵ� ���� �ϰ����� �ؾ����ݾ�?
			
		}
					
			
		
		case 5 : //������ ����
		{
			System.out.println("��ϵ� ������ �����Դϴ�.");
			System.out.println("���� �� �����Ǹ� ��ȸ�մϴ�.");
			
		//	dao.RecipeDelete
		}
			
			
			
			
			
		}
		
		}
			
			
			
			
		}

	
	
	
	
				
				
				
				
	

}
