package com.refri.view;

import java.util.List;

import com.refri.model.CookingDTO;


public class CookingView {
	//������ ��ȸ
	   public static void print(List<CookingDTO> list){
	      System.out.println("==================");
	      for(CookingDTO dto : list){
	         System.out.println(dto);
	      }
	      System.out.println("==================\n");
	   }
	   //�� �� ��ȸ
	   public static void print(CookingDTO dto){
	      System.out.println("------------------");
	      System.out.println(dto);
	      System.out.println("------------------\n");
	   }
	   public static void print(String msg){
	      System.out.println("�˸� : " + msg);
	   }
}
