package com.refri.view;

import java.util.ArrayList;

import com.refri.model.RecipeDTO;

public class RecipeView {

	
	
	//전체 조회
		public void All_View(ArrayList<RecipeDTO> list){
			
			for(RecipeDTO dto : list ){
				System.out.println(dto);
			}
			
		}
		
		//국가 한 건
		public void RecipeView(RecipeDTO dto){
			System.out.println(dto);	
				}
		
}
