package com.refri.view;

import java.util.List;

import com.refri.model.ClassDTO;

public class ClassView {
	//������ ��ȸ
	   public static void print(List<ClassDTO> list){
	      System.out.println("==================");
	      for(ClassDTO dto : list){
	         System.out.println(dto);
	      }
	      System.out.println("==================\n");
	   }
	   //�� �� ��ȸ
	   public static void print(ClassDTO dto){
	      System.out.println("------------------");
	      System.out.println(dto);
	      System.out.println("------------------\n");
	   }
	   public static void print(String msg){
	      System.out.println("�˸� : " + msg);
	   }
}
