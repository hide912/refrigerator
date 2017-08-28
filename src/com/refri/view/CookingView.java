package com.refri.view;

import java.util.List;

import com.refri.model.CookingDTO;


public class CookingView {
	//여러건 조회
	   public static void print(List<CookingDTO> list){
	      System.out.println("==================");
	      for(CookingDTO dto : list){
	         System.out.println(dto);
	      }
	      System.out.println("==================\n");
	   }
	   //한 건 조회
	   public static void print(CookingDTO dto){
	      System.out.println("------------------");
	      System.out.println(dto);
	      System.out.println("------------------\n");
	   }
	   public static void print(String msg){
	      System.out.println("알림 : " + msg);
	   }
}
