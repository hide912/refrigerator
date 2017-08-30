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
			System.out.println("1. ��ü��ȸ");
			System.out.println("2. �丮�� �丮 ��ȸ");
			System.out.println("3. ��з��� �丮 ��ȸ");
			System.out.println("4. �ߺз��� �丮 ��ȸ");
			System.out.println("5. �丮 �߰� ");
			System.out.println("6. �丮 ���� ");
			System.out.println("7. �丮 ���� ");
			System.out.println("0. ����");

			System.out.print("�۾�����  >>  ");
			switch (sc.nextInt()) {
			case 0:
				System.out.println("���� .. ");
				return;
			case 1: {
				clist = cDAO.selectAll();
				CookingView.print(clist);
				break;
			}
			case 2: {
				System.out.print("�丮���� �Է��ϼ��� >> ");
				String cooking = sc.nextLine();
				clist = cDAO.selectByCooking(cooking);
				CookingView.print(clist);
				break;
			}
			case 3: {
				System.out.print("��з��� �Է��ϼ��� >> ");
				String in = sc.nextLine();
				clist = cDAO.selectByIn(in);
				CookingView.print(clist);
				break;
			}
			case 4: {
				System.out.print("�ߺз��� �Է��ϼ��� >> ");
				String subClass = sc.nextLine();
				clist = cDAO.selectBySub(subClass);
				CookingView.print(clist);
				break;
			}
			case 5: {
				System.out.println("�߰��� �丮���� �Է��ϼ��� >> ");
				String cooking = sc.nextLine();

				clist = cDAO.selectByCooking(cooking);

				if (clist.isEmpty() == true) {

					cllist = clDAO.selectAll();
					ClassView.print(cllist);
					cllist = null;
					
					System.out.print("�߰��� �丮�� ��з� >> ");
					String classin = sc.nextLine();
					
					cllist = clDAO.selectByIn(classin);
					
					if(clist.isEmpty() != true){
						
						cllist = null;
						System.out.print("�߰��� �丮�� �ߺз� >> ");
						String subClass = sc.nextLine();
						cllist = clDAO.selectByIn(subClass);
						
							if(clist.isEmpty() != true){
	
								c = new CookingDTO(cooking, classin, subClass);
								int result = cDAO.insertCooking(c);
	
								String msg = "���� ����";
								if (result > 0)
									msg = "���� ����";
								CookingView.print(msg);
								break;
							}
						}
					
				} else
					System.out.println("�̹� �����ϴ� �丮�Դϴ�.");
				break;
			}
			case 6: {
				System.out.print("������ �丮���� �Է��ϼ��� >> ");
				String cooking = sc.nextLine();

				clist = cDAO.selectByCooking(cooking);

				if (clist.isEmpty() != true) {

					CookingView.print(cDAO.selectByCooking(cooking));

					System.out.print("�����Ͻðڽ��ϱ� ? (true/false)");
					boolean b = sc.nextBoolean();

					if (b) {
						int result = cDAO.deleteCooking(cooking);
						String msg = "���� ����";
						if (result > 0) {
							CookingView.print(cDAO.selectByCooking(cooking));
							msg = "���� ����";
						}
						CookingView.print(msg);
					}
					break;
				} else
					System.out.println("�߸��� �Է��Դϴ�.");
				break;
			}
			case 7: {
				System.out.println("������ �丮���� �Է��ϼ��� >> ");
				String cooking = sc.nextLine();

				clist = cDAO.selectByCooking(cooking);

				if (clist.isEmpty() == true) {

					cllist = clDAO.selectAll();
					ClassView.print(cllist);
					cllist = null;
					
					System.out.print("������ ��з� >> ");
					String classin = sc.nextLine();
					
					cllist = clDAO.selectByIn(classin);
					
					if(clist.isEmpty() != true){
						
						cllist = null;
						System.out.print("������ �ߺз� >> ");
						String subClass = sc.nextLine();
						cllist = clDAO.selectByIn(subClass);
						
							if(clist.isEmpty() != true){
	
								c = new CookingDTO(cooking, classin, subClass);
								int result = cDAO.updateCooking(c);
	
								String msg = "���� ����";
								if (result > 0)
									msg = "���� ����";
								CookingView.print(msg);
								break;
							}
							else
								System.out.println("�߸��� �ߺз��Դϴ�.");
						}
					else
						System.out.println("�߸��� ��з��Դϴ�.");
					
				} else
					System.out.println("�̹� �����ϴ� �丮�Դϴ�.");
				break;
				}
			}
		}
	}

}
