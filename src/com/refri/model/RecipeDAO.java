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
				 
				dto = new RecipeDTO(cooking,  subname,  recipenum, ordernum, ordercook, userid);
				
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
	
	
	//레시피 한 건만 조회 (cooking, recipenum)
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
		public ArrayList<RecipeDTO> RecipeCookingUserid(String cooking, String userid){
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
		
		//요리명, 레시피 번호, id로 조회 RecipenumUserid
		public ArrayList<RecipeDTO> Recipecru(String cooking, int recipenum, String userid){
			conn = DBUtil.getConnect();
			ArrayList<RecipeDTO> list = new ArrayList<RecipeDTO>();
			RecipeDTO dto = new RecipeDTO();
			String sql = "select* from Recipe where cooking = ? and recipenum = ? and userid = ? "; 		//'%'||?|| '%';
		
			try {
				st = conn.prepareStatement(sql);
				st.setString(1, cooking);
				st.setInt(2, recipenum );
				st.setString(3, userid );
				
				rs = st.executeQuery();
				if(rs.next()){
							//rs.next()는 sql문이 수행된 값을 한번 찍어줘야 다음 문장들이 수행될 수 있음.
					
					 cooking = rs.getString(1);
					 String subname = rs.getString(2);
					  recipenum = rs.getInt(3);
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
		public int getRecipeNum(String cooking){
			conn = DBUtil.getConnect();
			int num=0;
			String sql ="select nvl(max(recipenum),0)+1 from recipe where cooking = ? "; 
		 
			try {
				st = conn.prepareStatement(sql);
				st.setString(1, cooking );
				
				rs = st.executeQuery();
				if (rs.next()){
				   num = rs.getInt(1);		 
				}		
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBUtil.dbClose(conn, st, rs);
			}
			return num;
		}
		
		//요리명으로 같은 요리명 모두 조회
		public ArrayList<RecipeDTO> RecipeCooking(String cooking){
			conn = DBUtil.getConnect();
			RecipeDTO dto = new RecipeDTO();
			ArrayList<RecipeDTO> list = new ArrayList<RecipeDTO>();
			String sql ="select * from recipe where cooking = ?"; 
					//"select Cooking, recipenum from Recipe group by Cooking, recipenum having Cooking = '?'"; 		//'%'||?|| '%';
			//select Cooking, recipenum from Recipe group by Cooking, recipenum having Cooking = '짜장면'

			try {
				st = conn.prepareStatement(sql);
				st.setString(1, cooking );
				
				rs = st.executeQuery();
				while (rs.next()){
							//rs.next()는 sql문이 수행된 값을 한번 찍어줘야 다음 문장들이 수행될 수 있음.
					 cooking = rs.getString(1);
					String subname = rs.getString(2);
					int recipenum = rs.getInt(3);
					int ordernum = rs.getInt(4);
					String ordercook = rs.getString(5);
					String userid = rs.getString(6);
							
					dto = new RecipeDTO(cooking,  subname,  recipenum, ordernum, ordercook, userid);
					
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
	
	
		//특정 id의 레시피 모두 조회
		public ArrayList<RecipeDTO> RecipeUserid(String userid){
			conn = DBUtil.getConnect();
			RecipeDTO dto = new RecipeDTO();
			ArrayList<RecipeDTO> list = new ArrayList();
			String sql = "select* from Recipe where Userid = ?"; 		//'%'||?|| '%';
			
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
		}finally {
			DBUtil.dbClose(conn, st, rs);
		}
		return result;
	}// insertmany	//오토커밋 false?
	

/*	
	//레시피 내용 수정 일괄처리
	public int[] RecipeUpdate(ArrayList<RecipeDTO> list){
		conn = DBUtil.getConnect();
		int[] count = null;
		try {
			
		
			String sql = "update Recipe " 
			//	+ " set cooking = ?,"
				+ " set subname = ?,"
		//		+ " recipenum = ?,"
				+ " ordernum = ?,"
				+ " ordercook = ?,"
		//		+ " userid = ?,"
				
				+ " where cooking = ? and userid = ? and recipenum = ? ";
			st = conn.prepareStatement(sql);
		//Statement st = conn.createStatement();
			for(RecipeDTO dto : list){

			st.setString(1, dto.getSubname());	
			st.setInt(2, dto.getOrdernum());
			st.setString(3, dto.getOrdercook());
		//	st.setString(4, dto.getCooking());
			st.setString(4, dto.getCooking());
			st.setString(5, dto.getUserid());
			st.setInt(6, dto.getRecipenum());

			 st.addBatch(sql);
		}
		count = st.executeBatch();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, st, rs);
		}
		return count;
	}
	*/
	
	
	//레시피 내용 수정 일괄처리
	public int[] RecipeUpdate(ArrayList<RecipeDTO> list){
		conn = DBUtil.getConnect();
		int[] count = null;
		try {
			conn.setAutoCommit(false);
			Statement st = conn.createStatement();
			
			for(RecipeDTO dto : list){
			String sql = "update Recipe " 
					+ " set subname = '"+ dto.getSubname() +  "', "
					+ "  userid = '"+ dto.getUserid() +"', " 
					+ " ordercook = '" + dto.getOrdercook() +"' "	
				+ " where cooking = '"+ dto.getCooking() +"' and ordernum = " + dto.getOrdernum() +" and recipenum = "
						+ dto.getRecipenum();
			
		/*	update Recipe  
			set subname = ' 엄마큰손    ',
			ordercook = '엄마', 	
			userid = 'Master'
			where cooking = '짜장면' and ordernum = 1 and recipenum =  3
			*/
			
			st.addBatch(sql);
			}
			count = st.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			}
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, st, rs);
		}
		return count;
	}
	
	
	
	
	//레시피에서 특정 ordernum 하나만 삭제
	public int RecipeDelete(String cooking, int recipenum, int ordernum, String userid){
		String sql = "Delete from Recipe where cooking = ? and recipenum = ? and ordernum = ? and userid = ?";
	
		conn = DBUtil.getConnect();
		
		RecipeDTO dto = new RecipeDTO();
	
		int count = 0;
		
		try{
		st = conn.prepareStatement(sql);
		
		st.setString(1, cooking);
		st.setInt(2, recipenum);
		st.setInt(3, ordernum);
		st.setString(4, userid);
		
		count = st.executeUpdate();
	
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.dbClose(conn, st, rs);
	}
		return count;
}
	
	//중복되는 레시피 번호 정보 모두 삭제
	public int RecipeDelete2(String cooking, int recipenum){
	//	String cooking, int recipenum, String userid
		String sql = "Delete from Recipe where cooking = ? and recipenum = ? ";	
		conn = DBUtil.getConnect();

//		RecipeDTO dto = new RecipeDTO();
		
		int count = 0;
		try{
		
			st = conn.prepareStatement(sql);
	//		rs = st.executeQuery();
			st.setString(1, cooking);
			st.setInt(2, recipenum);
				
		count = st.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.dbClose(conn, st, rs);
		}
		return count;
	}

	
	
	
	
}




