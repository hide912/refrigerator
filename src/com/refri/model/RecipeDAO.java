package com.refri.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBUtil;


public class RecipeDAO {

	Connection conn;
	PreparedStatement st; // spl문 보내고 받는 통로
	ResultSet rs; // 그 통로에서 받은 값을 리턴
	
	
	
	//레시피 전체 조회
	public ArrayList<RecipeDTO> RecipeAll() {
		conn = DBUtil.getConnect();
		RecipeDTO dto = new RecipeDTO();
		ArrayList<RecipeDTO> list = new ArrayList();
		String sql = "select* " + "from Recipe";
		try {
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
			
				 String cooking = rs.getString(1);
				 String subname = rs.getString(2);
				 int recipenum = rs.getInt(3);
				 int ordernum = rs.getInt(4);
				 String ordercook = rs.getString(5);
				 String userid = rs.getString(6);
				 
				 
				dto = new RecipeDTO(cooking,  subname,  recipenum, ordernum, ordercook, 
						 userid);
				
				list.add(dto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, st, rs);
		}

		return list;
	}
	
	
	//레시피 한 건만 조회 (cook, recipenum, ordernum)
	public RecipeDTO Recipeselect(String cook, int recipenum, int ordernum){
		conn = DBUtil.getConnect();
		RecipeDTO dto = new RecipeDTO();
		String sql = "select* from Recipe where Cooking = ? and recipenum = ? and ordernum = ? "; 		//'%'||?|| '%';
	
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, cook );
			st.setInt(2, recipenum );
			st.setInt(3, ordernum );
			
			rs = st.executeQuery();
			if(rs.next()){			//rs.next()는 sql문이 수행된 값을 한번 찍어줘야 다음 문장들이 수행될 수 있음.
				
				 String cooking = rs.getString(1);
				 String subname = rs.getString(2);
				 int recipenum = rs.getInt(3);
				 int ordernum = rs.getInt(4);
				 String ordercook = rs.getString(5);
				 String userid = rs.getString(6);
				 
				 
				dto = new RecipeDTO(cooking,  subname,  recipenum, ordernum, ordercook, 
						 userid);
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.dbClose(conn, st, rs);
		}
		return dto;
		
	}
	
	
	
	
	
	
	
	
	
	
	

	// 3. 레시피 등록
	public int RecipeInsert(RecipeDTO dto) {
		int count = 0;

		conn = DBUtil.getConnect();

		String sql = "insert into Recipe(cooking, subname, recipenum, ordernum, ordercook, userid) "
				+ " values(?,?,?,?,?,?) ";
					

		try {
			st = conn.prepareStatement(sql);
			// rs = st.executeQuery();

			st.setString(1, dto.getCooking());
			st.setString(2, dto.getSubname());
			st.setInt(3, dto.getRecipenum());
			st.setInt(4, dto.getOrdernum());
			st.setString(5, dto.getOrdercook());
			st.setString(6, dto.getUserid());
			
			count = st.executeUpdate(); // Update 적용된 행의 갯수

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, st, rs);
		}

		return count;
	}


	
	//레시피 내용 수정
	public int RecipeUpdate(RecipeDTO dto){
		conn = DBUtil.getConnect();
		int count=0;
		String sql ="update Recipe"
				+ " set sumname = ?,"
		//		+ " set cooking = ?,"
		//		+ " subname = ?,"
		//		+ " recipenum = ?,"
		//		+ " ordernum = ?,"
				+ " ordercook = ?,"
				+ " userid = ?,"
				
				+ " where Cooking = ? and recipenum = ? and ordernum = ? ";
		
		try {
			st = conn.prepareStatement(sql);
			
			st.setString(1, dto.getSubname());
			st.setString(2, dto.getOrdercook());	
			st.setString(3, dto.getUserid());
			st.setString(4, dto.getCooking());
			st.setInt(5, dto.getRecipenum());
			st.setInt(6, dto.getOrdernum());
			
			//st.setString(7, dto.getCountry());	//이렇게 하면 되는지 나중에 다시 확인
			
			count = st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, st, rs);
		}
	
		return count;
		
	}
	
	//레시피 삭제
	
	public int RecipeDelete(String cooking, int recipenum){
		String sql = "Delete from Recipe "
				+ " where trim(?) and trim(?) ;";
	
		conn = DBUtil.getConnect();
		
		RecipeDTO dto = new RecipeDTO();
	
		int count = 0;
		
		try{
		st = conn.prepareStatement(sql);
		st.setString(1, dto.getCooking());
		st.setInt(2, dto.getRecipenum());
		
		count = st.executeUpdate();
	
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.dbClose(conn, st, rs);
	}
		return count;
		

	
}

	

	
	
	
	
	
}




