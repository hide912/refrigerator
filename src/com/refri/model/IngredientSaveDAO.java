package com.refri.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import util.DBUtil;

public class IngredientSaveDAO {

	Connection conn;
	PreparedStatement st; // spl�� ������ �޴� ���
	ResultSet rs; // �� ��ο��� ���� ���� ����
	
	
	// ���̺�� ��� �ϰ����
		public int[] Saveingred(ArrayList<IngredientSaveDTO> list) {
			int[] result = null;
			conn = DBUtil.getConnect();
			String sql = "insert into IngredientSave values ";

			try {
	            conn.setAutoCommit(false);
				Statement st = conn.createStatement();
				for (IngredientSaveDTO dto : list) {
					String sql2 = sql +  "( '" + dto.getIngredient() + "' )";
				
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

		
		//���̺� ���� ��� ����.
		public int Deleteingred(){
			String sql = "Delete from IngredientSave";	
		//	Delete from IngredientSave
			conn = DBUtil.getConnect();

//			RecipeDTO dto = new RecipeDTO();
			
			int count = 0;
			try{
				 conn.setAutoCommit(false);
				st = conn.prepareStatement(sql);
		//		rs = st.executeQuery();						
			count = st.executeUpdate();
			conn.commit();
			}catch(SQLException e){
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
				e.printStackTrace();
			}finally{
				DBUtil.dbClose(conn, st, rs);
			}
			return count;
		}

		
}
