package com.refri.view;

import java.util.List;

import com.refri.model.UserDTO;


public class UserView {
	//������ ��ȸ
	   public static void print(List<UserDTO> list){
	      System.out.println("==================");
	      for(UserDTO dto : list){
	         System.out.println(dto);
	      }
	      System.out.println("==================\n");
	   }
	   //�� �� ��ȸ
	   public static void print(UserDTO dto){
	      System.out.println("------------------");
	      System.out.println(dto);
	      System.out.println("------------------\n");
	   }
	   public static void print(String msg){
	      System.out.println("�˸� : " + msg);
	   }
}
