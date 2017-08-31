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
	
	
	//������ �� �Ǹ� ��ȸ (cooking, recipenum)
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
		
		//�丮��, ������ ��ȣ, id�� ��ȸ RecipenumUserid
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
							//rs.next()�� sql���� ����� ���� �ѹ� ������ ���� ������� ����� �� ����.
					
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
		
		//�丮������ ���� �丮�� ��� ��ȸ
		public ArrayList<RecipeDTO> RecipeCooking(String cooking){
			conn = DBUtil.getConnect();
			RecipeDTO dto = new RecipeDTO();
			ArrayList<RecipeDTO> list = new ArrayList<RecipeDTO>();
			String sql ="select * from recipe where cooking = ?"; 
					//"select Cooking, recipenum from Recipe group by Cooking, recipenum having Cooking = '?'"; 		//'%'||?|| '%';
			//select Cooking, recipenum from Recipe group by Cooking, recipenum having Cooking = '¥���'

			try {
				st = conn.prepareStatement(sql);
				st.setString(1, cooking );
				
				rs = st.executeQuery();
				while (rs.next()){
							//rs.next()�� sql���� ����� ���� �ѹ� ������ ���� ������� ����� �� ����.
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
	
	
		//Ư�� id�� ������ ��� ��ȸ
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
		}finally {
			DBUtil.dbClose(conn, st, rs);
		}
		return result;
	}// insertmany	//����Ŀ�� false?
	

/*	
	//������ ���� ���� �ϰ�ó��
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
	
	
	//������ ���� ���� �ϰ�ó��
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
			set subname = ' ����ū��    ',
			ordercook = '����', 	
			userid = 'Master'
			where cooking = '¥���' and ordernum = 1 and recipenum =  3
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
	
	
	
	
	//�����ǿ��� Ư�� ordernum �ϳ��� ����
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
	
	//�ߺ��Ǵ� ������ ��ȣ ���� ��� ����
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




