package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Order;

public class SalesModel {
	Connection con;

	int resultMoney_day;

	public SalesModel() throws Exception {
		con = DBCon.getConnection();
	}

	public ArrayList<Order> dayChart(String startDay, String endDay) throws Exception {
		// 3.SQL문장 만들기
		String sql = "SELECT total_price, order_time FROM table_order WHERE order_time BETWEEN '" + startDay + "' AND '"
				+ endDay + "' ORDER BY ORDER_TIME";
		// 4.SQL전송 객체 얻어오기
		PreparedStatement ps = con.prepareStatement(sql);
		// 5.SQL전송
		ResultSet rs = ps.executeQuery();
		ArrayList<Order> list = new ArrayList<Order>();
		while (rs.next()) {
			Order vo = new Order();
			vo.setTotal_Price(rs.getInt("TOTAL_PRICE"));
			vo.setOrder_Time(rs.getString("ORDER_TIME"));

			list.add(vo);
		}
		// 6.닫기(Connection 은 제외)
		ps.close();
		return list;
	}

	public ArrayList<Order> monthChart(String startDay, String endDay) throws Exception {
		// 3.SQL문장 만들기
		String sql = "SELECT sum(total_price) total_price, to_char(order_time,'MM') order_time FROM table_order WHERE order_time BETWEEN '"
				+ startDay + "' AND '" + endDay + "' GROUP BY to_char(order_time,'MM') ORDER BY order_time ";
		// 4.SQL전송 객체 얻어오기
		PreparedStatement ps = con.prepareStatement(sql);
		// 5.SQL전송
		ResultSet rs = ps.executeQuery();
		ArrayList<Order> list = new ArrayList<Order>();
		while (rs.next()) {
			Order vo = new Order();
			vo.setTotal_Price(rs.getInt("TOTAL_PRICE"));
			vo.setOrder_Time(rs.getString("ORDER_TIME"));

			list.add(vo);
		}
		// 6.닫기(Connection 은 제외)
		ps.close();
		return list;
	}

	public ArrayList<Order> yearChart(String startDay, String endDay) throws Exception {
		// 3.SQL문장 만들기
		String sql = "SELECT sum(total_price) total_price, to_char(order_time,'YYYY') order_time FROM table_order WHERE order_time BETWEEN '"
				+ startDay + "' AND '" + endDay + "' GROUP BY to_char(order_time,'YYYY') ORDER BY order_time ";
		// 4.SQL전송 객체 얻어오기
		PreparedStatement ps = con.prepareStatement(sql);
		// 5.SQL전송
		ResultSet rs = ps.executeQuery();
		ArrayList<Order> list = new ArrayList<Order>();
		while (rs.next()) {
			Order vo = new Order();
			vo.setTotal_Price(rs.getInt("TOTAL_PRICE"));
			vo.setOrder_Time(rs.getString("ORDER_TIME"));

			list.add(vo);
		}
		// 6.닫기(Connection 은 제외)
		ps.close();
		return list;
	}

	public int totalSalesDay() throws Exception {
		String sql = "SELECT sum(total_price) total_price_day FROM table_order WHERE order_time LIKE sysdate";

		PreparedStatement ps = con.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			resultMoney_day = rs.getInt("TOTAL_PRICE_DAY");
		}

		ps.close();

		return resultMoney_day;
	}
}
