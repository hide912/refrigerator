package com.refri.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	
	
	//레시피 한 건만 조회 (cooking, recipenum, ordernum)
	public RecipeDTO Recipeselect(String cooking, int recipenum){
		conn = DBUtil.getConnect();
		RecipeDTO dto = new RecipeDTO();
		String sql = "select* from Recipe where Cooking = ? and recipenum = ? "; 		//'%'||?|| '%';
	
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, cooking );
			st.setInt(2, recipenum );
			
			
			rs = st.executeQuery();
			if (rs.next()){
					//rs.next()는 sql문이 수행된 값을 한번 찍어줘야 다음 문장들이 수행될 수 있음.
				
				  cooking = rs.getString(1);
				 String subname = rs.getString(2);
				  recipenum = rs.getInt(3);
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
	
	

	//요리명+ id로 조회 레시피 조회 (cooking, userid)
		public ArrayList<RecipeDTO> RecipeCoookingUserid(String cooking, String userid){
			conn = DBUtil.getConnect();
			RecipeDTO dto = new RecipeDTO();
			ArrayList<RecipeDTO> list = new ArrayList();
			String sql = "select* from Recipe where Cooking = ? and userid = ? "; 		//'%'||?|| '%';
		
			try {
				st = conn.prepareStatement(sql);
				st.setString(1, cooking );
				st.setString(2, userid );
				
				rs = st.executeQuery();
				while (rs.next()){
							//rs.next()는 sql문이 수행된 값을 한번 찍어줘야 다음 문장들이 수행될 수 있음.
					
					  cooking = rs.getString(1);
					 String subname = rs.getString(2);
					 int recipenum = rs.getInt(3);
					 int ordernum = rs.getInt(4);
					 String ordercook = rs.getString(5);
					  userid = rs.getString(6);
					 
					 
					dto = new RecipeDTO(cooking,  subname,  recipenum, ordernum, ordercook, 
							 userid);
					list.add(dto);
				}		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBUtil.dbClose(conn, st, rs);
			}
			return list;
			
		}
		
		
		
		//요리명으로 같은 요리명 모두 조회
		public ArrayList<RecipeDTO> RecipeCooking(String cooking){
			conn = DBUtil.getConnect();
			RecipeDTO dto = new RecipeDTO();
			ArrayList<RecipeDTO> list = new ArrayList();
			String sql = "select* from Recipe group by Cooking having Cooking = ?"; 		//'%'||?|| '%';
			
			try {
				st = conn.prepareStatement(sql);
				st.setString(1, cooking );
				
				rs = st.executeQuery();
				if(rs.next()){
				while (rs.next()){
					
							//rs.next()는 sql문이 수행된 값을 한번 찍어줘야 다음 문장들이 수행될 수 있음.
					
					 cooking = rs.getString(1);
					String subname = rs.getString(2);
					int recipenum = rs.getInt(3);
					int ordernum = rs.getInt(4);
					String ordercook = rs.getString(5);
					String userid = rs.getString(6);
					
					
					dto = new RecipeDTO(cooking,  subname,  recipenum, ordernum, ordercook, 
							userid);
					
					list.add(dto);
				}		
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			}finally{
				DBUtil.dbClose(conn, st, rs);
			}
			return list;
			
		}
	
	
		//특정 id의 레시피 모두 조회
		public ArrayList<RecipeDTO> RecipeUserid(String userid){
			conn = DBUtil.getConnect();
			RecipeDTO dto = new RecipeDTO();
			ArrayList<RecipeDTO> list = new ArrayList();
			String sql = "select* from Recipe group by userid having userid = ?"; 		//'%'||?|| '%';
			
			try {
				st = conn.prepareStatement(sql);
				st.setString(1, userid );
				
				rs = st.executeQuery();
				while (rs.next()){
							//rs.next()는 sql문이 수행된 값을 한번 찍어줘야 다음 문장들이 수행될 수 있음.
					
					String cooking = rs.getString(1);
					String subname = rs.getString(2);
					int recipenum = rs.getInt(3);
					int ordernum = rs.getInt(4);
					String ordercook = rs.getString(5);
					 userid = rs.getString(6);
					
					
					dto = new RecipeDTO(cooking,  subname,  recipenum, ordernum, ordercook, 
							userid);
					
					list.add(dto);
				}		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBUtil.dbClose(conn, st, rs);
			}
			return list;
			
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

	
	// 레시피 등록 일괄처리
	public int[] ManyInsert(ArrayList<RecipeDTO> list) {
		int[] result = null;
		conn = DBUtil.getConnect();
		String sql = "insert into Recipe values ";

		try {
            conn.setAutoCommit(false);
			Statement st = conn.createStatement();
			for (RecipeDTO dto : list) {
				String sql2 = sql +  "( '" + dto.getCooking() + "', '" + dto.getSubname() + "', "  
						+ dto.getRecipenum() + ", " + dto.getOrdernum() + ", '" + dto.getOrdercook() + "', '"
						+ dto.getUserid() + "' )";
			
				st.addBatch(sql2);
			}
			result = st.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			}
			e.printStackTrace();
		}
		return result;
	}// insertmany	//오토커밋 false?
	

	
	//레시피 내용 수정 일괄처리
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
	public int RecipeDelete(String cooking, int recipenum, String userid){
		String sql = "Delete from Recipe "
				+ " where trim(?) and trim(?) and trim(?);";
	
		conn = DBUtil.getConnect();
		
		RecipeDTO dto = new RecipeDTO();
	
		int count = 0;
		
		try{
		st = conn.prepareStatement(sql);
		st.setString(1, dto.getCooking());
		st.setInt(2, dto.getRecipenum());
		st.setString(3, userid);
		
		count = st.executeUpdate();
	
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.dbClose(conn, st, rs);
	}
		return count;
		

	
}

	

	
	
	
	
	
}




