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
			int recipenum = rs.getInt(3);
			return new CookingIngDTO(cooking, ingredient, recipenum);
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
		
		// Cooking + RecipeNum(요리명+레시피번호)으로 선택된 CookingIng 리턴
				public List<CookingIngDTO> selectByCnN(String no, int num) {
					List<CookingIngDTO> cilist = new ArrayList<>();
					String sql = "SELECT * FROM CookingIng WHERE cooking = ? and recipenum = ?";
					conn = DBUtil.getConnect();
					try {
						st = conn.prepareStatement(sql);
						st.setString(1, no);
						st.setInt(2, num);
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
				
				// Cooking + RecipeNum(요리명+레시피번호)으로 선택된 재료명 리스트 리턴
				public List<String> selectingred(String no, int num) {
					List<String> cilist = new ArrayList<>();
					String sql = "SELECT ingredient FROM CookingIng WHERE cooking = ? and recipenum = ?";
					conn = DBUtil.getConnect();
					try {
						st = conn.prepareStatement(sql);
						st.setString(1, no);
						st.setInt(2, num);
						rs = st.executeQuery();
						while (rs.next()) {
							String ingredient = rs.getString(1);
							

							cilist.add(ingredient);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						DBUtil.dbClose(conn, st, rs);
					}
					return cilist;
				}
	
		// Ingredient(재료명)으로 선택된 CookingIng 리턴
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
		
		// CookingIng(재료) 추가(by DTO), 추가된 수만큼 리턴
		public int insertCookingING(CookingIngDTO dto) {
			String sql = "INSERT INTO CookingIng VALUES(?,?,?)";
			conn = DBUtil.getConnect();
			try {
				st = conn.prepareStatement(sql);
				st.setString(1, dto.getCooking());
				st.setString(2, dto.getIngredient());
				st.setInt(3, dto.getRecipenum());
				count = st.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBUtil.dbClose(conn, st, rs);
			}
			return count;
		}
		
		// 요리 별 재료 한번에 여러 건 입력
		public int[] ManyInsert(ArrayList<CookingIngDTO> list) {
			int[] result = null;
			conn = DBUtil.getConnect();

			try {
	            		Statement st = conn.createStatement();
				for (CookingIngDTO dto : list) {
					String sql = "insert into CookingIng values " + 
				"( '" + dto.getCooking() + "', '" 
					+ dto.getIngredient() + "', "  	
					+ dto.getRecipenum()+ " )";
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
	
		// CookingIng(요리재료) 삭제(by 대분류), 삭제된 수만큼 리턴
		public int deleteCookingIng(String cooking, String ing, int num) {
			String sql = "DELETE FROM CookingIng WHERE cooking = ? and ingredient = ? and recipenum = ?" ;
			conn = DBUtil.getConnect();
			try {
				st = conn.prepareStatement(sql);
				st.setString(1, cooking);
				st.setString(2, ing);
				st.setInt(3, num);
	
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
