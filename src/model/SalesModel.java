package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import vo.Order;
import vo.Sales;

public class SalesModel {
	Connection con;

	int TotalSales;
	int TotalCount;

	public SalesModel() throws Exception {
		con = DBCon.getConnection();
	}

	public ArrayList<Order> dayChart(String startDay, String endDay) throws Exception {
		// 3.SQL문장 만들기
		String sql = "SELECT total_price, to_char(order_time,'YYYY/MM/DD') order_time FROM table_order WHERE order_time BETWEEN '"
				+ startDay + "' AND '" + endDay + "' ORDER BY ORDER_TIME";
		// 4.SQL전송 객체 얻어오기
		PreparedStatement ps = con.prepareStatement(sql);
		// 5.SQL전송
		ResultSet rs = ps.executeQuery();
		ArrayList<Order> list = new ArrayList<Order>();
		while (rs.next()) {
			Order vo = new Order();
			vo.setTotal_Price(rs.getInt("TOTAL_PRICE"));
			vo.setOrder_Time(rs.getString("ORDER_TIME"));
			System.out.println(vo.getOrder_Time());

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

	public Sales totalSalesDay() throws Exception {
		Sales vo = new Sales();
		String sql = "SELECT sum(total_price) total_sales,count(*) total_count FROM table_order";

		PreparedStatement ps = con.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			vo.setTotalSales(rs.getInt("TOTAL_SALES"));
			vo.setTotalCount(rs.getInt("TOTAL_COUNT"));
		}

		ps.close();

		return vo;
	}
}
