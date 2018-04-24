package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Menu;

public class MenuModel {
	Connection con;

	public MenuModel() throws Exception {
		con = DBCon.getConnection();
	}

	public void insertMenu(Menu vo) throws Exception {
		String sql = "INSERT INTO table_menu VALUES (?, ?, ?, ?) ";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, vo.getMenu_Name());
		ps.setInt(2, vo.getPrice());
		ps.setInt(3, vo.getCategori());
		ps.setString(4, vo.getSrc());

		ps.executeUpdate();

		ps.close();
	}

	public ArrayList<ArrayList> refreshMenu() throws Exception {
		String sql = "SELECT menu_name, src, categori FROM table_menu ";

		PreparedStatement ps = con.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		ArrayList<ArrayList> data = new ArrayList<ArrayList>();
		while (rs.next()) {
			ArrayList temp = new ArrayList();

			temp.add(rs.getString("MENU_NAME"));
			temp.add(rs.getString("SRC"));
			temp.add(rs.getInt("categori"));

			data.add(temp);
		}
		rs.close();
		ps.close();

		return data;
	}
}
