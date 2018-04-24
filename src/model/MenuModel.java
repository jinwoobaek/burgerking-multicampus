package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Menu;

public class MenuModel {
	Connection con;
	ArrayList data;

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

	public void refreshMenu() throws Exception {
		String sql = "SELECT menu_name, src FROM table_menu ";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			//ArrayList<String> 
		}
	}
}
