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
	
		// 모든 CookingIng 를 List에 담음
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
	
		// Cooking(요리명)으로 선택된 CookingIng 리턴
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
	
		// Ingredient(재료명)으로 선택된 CookingIng 리턴
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
	
		// CookingIng(재료) 추가(by DTO), 추가된 수만큼 리턴
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
	
		// CookingIng(요리재료) 삭제(by 대분류), 삭제된 수만큼 리턴
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
	
		// CookingIng(요리재료) 변경(by DTO), 변경된 수만큼 리턴
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
