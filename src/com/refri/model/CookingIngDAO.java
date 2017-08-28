package com.refri.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		public CookingIngDTO selectByCooking(String no) {
			CookingIngDTO dto = null;
			String sql = "SELECT * FROM CookingIng WHERE cooking = ?";
			conn = DBUtil.getConnect();
			try {
				st = conn.prepareStatement(sql);
				st.setString(1, no);
				rs = st.executeQuery();
				while (rs.next()) {
					dto = makeDTO(rs);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBUtil.dbClose(conn, st, rs);
			}
			return dto;
		}
	
		// Ingredient(����)���� ���õ� CookingIng ����
		public CookingIngDTO selectByIng(String no) {
			CookingIngDTO dto = null;
			String sql = "SELECT * FROM CookingIng WHERE ingredient = ?";
			conn = DBUtil.getConnect();
			try {
				st = conn.prepareStatement(sql);
				st.setString(1, no);
				rs = st.executeQuery();
				while (rs.next()) {
					dto = makeDTO(rs);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBUtil.dbClose(conn, st, rs);
			}
			return dto;
		}
	
		// CookingIng(���) �߰�(by DTO), �߰��� ����ŭ ����
		public int insertClass(CookingDTO dto) {
			String sql = "INSERT INTO CookingIng VALUES(?,?,?)";
			conn = DBUtil.getConnect();
			try {
				st = conn.prepareStatement(sql);
				st.setString(1, dto.getCooking());
				st.setString(2, dto.getClassin());
				st.setString(3, dto.getSubClass());
				count = st.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBUtil.dbClose(conn, st, rs);
			}
			return count;
		}
	
		// CookingIng(�丮���) ����(by ��з�), ������ ����ŭ ����
		public int deleteClass(String no) {
			String sql = "DELETE FROM CookingIng WHERE cooking = ?";
			conn = DBUtil.getConnect();
			try {
				st = conn.prepareStatement(sql);
				st.setString(1, no);
	
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
		public int updateInstructor(CookingIngDTO dto) {
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
