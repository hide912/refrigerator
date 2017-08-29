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
	PreparedStatement st; // spl�� ������ �޴� ���
	ResultSet rs; // �� ��ο��� ���� ���� ����
	
	
	
	//������ ��ü ��ȸ
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
	
	
	//������ �� �Ǹ� ��ȸ (cooking, recipenum, ordernum)
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
					//rs.next()�� sql���� ����� ���� �ѹ� ������ ���� ������� ����� �� ����.
				
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
	
	

	//�丮��+ id�� ��ȸ ������ ��ȸ (cooking, userid)
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
							//rs.next()�� sql���� ����� ���� �ѹ� ������ ���� ������� ����� �� ����.
					
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
		
		
		
		//�丮������ ���� �丮�� ��� ��ȸ
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
					
							//rs.next()�� sql���� ����� ���� �ѹ� ������ ���� ������� ����� �� ����.
					
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
	
	
		//Ư�� id�� ������ ��� ��ȸ
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
							//rs.next()�� sql���� ����� ���� �ѹ� ������ ���� ������� ����� �� ����.
					
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
	
	
	
	
	
	
	

	// 3. ������ ���
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
			
			count = st.executeUpdate(); // Update ����� ���� ����

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, st, rs);
		}

		return count;
	}

	
	// ������ ��� �ϰ�ó��
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
	}// insertmany	//����Ŀ�� false?
	

	
	//������ ���� ���� �ϰ�ó��
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
			
			//st.setString(7, dto.getCountry());	//�̷��� �ϸ� �Ǵ��� ���߿� �ٽ� Ȯ��
			
			count = st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(conn, st, rs);
		}
	
		return count;
		
	}
	
	//������ ����
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




