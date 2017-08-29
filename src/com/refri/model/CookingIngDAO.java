package com.refri.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

	public class CookingIngDAO {
		Connection conn;
		PreparedStatement st;
		ResultSet rs;
		int count;
	
		private CookingIngDTO makeDTO(ResultSet rs) throws SQLException {
			String cooking = rs.getString(1);
			String ingredient = rs.getString(2);
			return new CookingIngDTO(cooking, ingredient);
		}
	
		// ��� CookingIng �� List�� ����
		public List<CookingIngDTO> selectAll() {
			List<CookingIngDTO> slist = new ArrayList<>();
			String sql = "SELECT * FROM CookingIng";
			conn = DBUtil.getConnect();
			try {
				st = conn.prepareStatement(sql);
				rs = st.executeQuery();
				while (rs.next()) {
					CookingIngDTO dto = makeDTO(rs);
					slist.add(dto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBUtil.dbClose(conn, st, rs);
			}
			return slist;
		}
	
		// Cooking(�丮��)���� ���õ� CookingIng ����
		public List<CookingIngDTO> selectByCooking(String no) {
			List<CookingIngDTO> cilist = new ArrayList<>();
			String sql = "SELECT * FROM CookingIng WHERE cooking = ?";
			conn = DBUtil.getConnect();
			try {
				st = conn.prepareStatement(sql);
				st.setString(1, no);
				rs = st.executeQuery();
				while (rs.next()) {
					CookingIngDTO dto = makeDTO(rs);
					cilist.add(dto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBUtil.dbClose(conn, st, rs);
			}
			return cilist;
		}
	
		// Ingredient(����)���� ���õ� CookingIng ����
		public List<CookingIngDTO> selectByIng(String no) {
			List<CookingIngDTO> cilist = new ArrayList<>();
			String sql = "SELECT * FROM CookingIng WHERE ingredient = ?";
			conn = DBUtil.getConnect();
			try {
				st = conn.prepareStatement(sql);
				st.setString(1, no);
				rs = st.executeQuery();
				while (rs.next()) {
					CookingIngDTO dto = makeDTO(rs);
					dto = makeDTO(rs);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBUtil.dbClose(conn, st, rs);
			}
			return cilist;
		}
	
		// CookingIng(���) �߰�(by DTO), �߰��� ����ŭ ����
		public int insertCookingING(CookingIngDTO dto) {
			String sql = "INSERT INTO CookingIng VALUES(?,?)";
			conn = DBUtil.getConnect();
			try {
				st = conn.prepareStatement(sql);
				st.setString(1, dto.getCooking());
				st.setString(2, dto.getIngredient());
				count = st.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBUtil.dbClose(conn, st, rs);
			}
			return count;
		}
		
		// �丮 �� ��� �ѹ��� ���� �� �Է�
		public int[] ManyInsert(ArrayList<CookingIngDTO> list) {
			int[] result = null;
			conn = DBUtil.getConnect();

			try {
	            		Statement st = conn.createStatement();
				for (CookingIngDTO dto : list) {
					String sql = "insert into CookingIng values " + "( '" 
							    + dto.getCooking() + "', '" + dto.getIngredient() + "')";
					st.addBatch(sql);
				}
				result = st.executeBatch();
				} catch (SQLException e) {		
				e.printStackTrace();
			}finally {
				DBUtil.dbClose(conn, st, rs);
			}
			return result;
		}
	
		// CookingIng(�丮���) ����(by ��з�), ������ ����ŭ ����
		public int deleteCookingIng(String cooking, String ing) {
			String sql = "DELETE FROM CookingIng WHERE cooking = ? and ingredient = ?" ;
			conn = DBUtil.getConnect();
			try {
				st = conn.prepareStatement(sql);
				st.setString(1, cooking);
				st.setString(2, ing);
	
				count = st.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBUtil.dbClose(conn, st, rs);
			}
			return count;
		}
	
		// CookingIng(�丮���) ����(by DTO), ����� ����ŭ ����
		public int updateCookingIng(CookingIngDTO dto) {
			String sql = "UPDATE CookingIng" + " SET" + " ingredient = ?," + " WHERE cooking = ?";
			conn = DBUtil.getConnect();
			try {
				st = conn.prepareStatement(sql);
				st.setString(1, dto.getIngredient());
				st.setString(2, dto.getCooking());
	
				count = st.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBUtil.dbClose(conn, st, rs);
			}
			return count;
		}
	}
