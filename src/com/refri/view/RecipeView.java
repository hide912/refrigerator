package com.refri.view;

import java.util.ArrayList;

import com.refri.model.RecipeDTO;

public class RecipeView {

	
	
	//��ü ��ȸ
		public void All_View(ArrayList<RecipeDTO> list){
			
			for(RecipeDTO dto : list ){
				System.out.println(dto);
			}
			
		}
		
		//���� �� ��
		public void RecipeView(RecipeDTO dto){
			System.out.println(dto);	
				}
		
}
