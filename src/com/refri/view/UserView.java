package com.refri.view;

import java.util.List;

import com.refri.model.UserDTO;


public class UserView {
	//여러건 조회
	   public static void print(List<UserDTO> list){
	      System.out.println("==================");
	      for(UserDTO dto : list){
	         System.out.println(dto);
	      }
	      System.out.println("==================\n");
	   }
	   //한 건 조회
	   public static void print(UserDTO dto){
	      System.out.println("------------------");
	      System.out.println(dto);
	      System.out.println("------------------\n");
	   }
	   public static void print(String msg){
	      System.out.println("알림 : " + msg);
	   }
}
