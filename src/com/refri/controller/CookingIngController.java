package com.refri.controller;

import java.util.List;
import java.util.Scanner;

import com.refri.model.CookingDAO;
import com.refri.model.CookingDTO;
import com.refri.model.CookingIngDAO;
import com.refri.model.CookingIngDTO;
import com.refri.view.CookingIngView;

public class CookingIngController {
	public static void main(String[] args) {
		CookingIngDTO ci = null;
		List<CookingIngDTO> cilist = null;
		CookingIngDAO ciDAO = new CookingIngDAO();
		
		CookingDTO c = null;
		List<CookingDTO> clist = null;
		CookingDAO cDAO = new CookingDAO();
		
		
		Scanner sc = new Scanner(System.in);
		
		while(true){
			System.out.println("1. ��ü��ȸ");
			System.out.println("2. �丮�� ��� ��ȸ");
			System.out.println("3. ��Ằ �丮 ��ȸ");
			System.out.println("4. ��� �߰� ");
			System.out.println("5. ��� ���� ");
			System.out.println("6. ��� ���� ");
			System.out.println("0. ����");
			
			System.out.print("  �۾�����  >>  ");
			switch (sc.nextInt()) {
			case 0:
				System.out.println("���� .. ");
				return;
			case 1: {
				cilist = ciDAO.selectAll();
				CookingIngView.print(cilist);
				break;
			}
			case 2: {
				System.out.print("�丮���� �Է��ϼ��� >> ");
				String cooking = sc.nextLine();
				cilist = ciDAO.selectByCooking(cooking);
				CookingIngView.print(cilist);
				break;
			}
			case 3: {
				System.out.print("������ �Է��ϼ��� >> ");
				String ing = sc.nextLine();
				cilist = ciDAO.selectByIng(ing);
				CookingIngView.print(cilist);
				break;
			}
			case 4: {
				System.out.println("�丮���� �Է��ϼ��� >> ");
				String cooking = sc.nextLine();
				
				clist = cDAO.selectByCooking(cooking);
				
				if(clist.isEmpty() != true ){
					List<CookingIngDTO> list = null;
					
					while(true){
						System.out.printf("��Ḧ �ϳ��� �Է��ϼ��� (���� = end)>> ");
						String ing = sc.nextLine();
						if(ing.equalsIgnoreCase("end"))
							break;
						CookingIngDTO dto = new CookingIngDTO(cooking, ing);
						list.add(dto);
					}
				}
				break;
			}
			
			case 5: {
				System.out.print("������ ������ ��� (���� ����) >> ");
				String cooking = sc.nextLine();
				System.out.print("������ ������ ��� (��� ����) >> ");
				String ingredient = sc.nextLine();

				System.out.print("�����Ͻðڽ��ϱ� ? (true/false)");
				boolean b = sc.nextBoolean();
				
				CookingIngView.print(ciDAO.selectByCooking(cooking));
			
				if (b) {
					int result = ciDAO.deleteCookingIng(cooking, ingredient);
					String msg = "���� ����";
					if (result > 0){
						CookingIngView.print(ciDAO.selectByCooking(cooking));
						msg = "���� ����";
					}
					CookingIngView.print(msg);
				}
				break;
			}
			case 6: {
				System.out.print("��Ḧ ������ �丮�� �̸� >>");
				String cooking = sc.nextLine();
				
				System.out.print("������ ����� �̸� >> ");
				String ingredient = sc.nextLine();

				ci = new CookingIngDTO(cooking, ingredient);

				int result = ciDAO.updateCookingIng(ci);
				String msg = "���� ����";
				if (result > 0)
					msg = "���� ����";
				CookingIngView.print(msg);
				break;
				}
			
			
			}
		}
	}
}
