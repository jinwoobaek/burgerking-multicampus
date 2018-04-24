package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarPainter;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;

import model.SalesModel;
import vo.Order;

public class ChartView extends JPanel {

	SalesModel model;

	public ChartView() {
	}

	// getChart() 메서드. Chart 를 만들어서 리턴함
	public JFreeChart getChart(String startDay, String endDay, char ymd) {

		JFreeChart chart = ChartFactory.createBarChart("Sales Chart", "Date", "Sales Of Day",
				getDataSet(startDay, endDay, ymd), PlotOrientation.VERTICAL, true, true, false);
		// barChart.setBackgroundPaint(new Color(146,21,15));
		chart.setBorderVisible(true); // 차트전체의 경계선이 나타난다.
		chart.setBorderPaint(new Color(146, 21, 15)); // 차트전체의 경계선의 색을 파란색으로
														// 정한다.
		chart.setBorderStroke(new BasicStroke(5)); // 차트전체의 경계선의 두께를 정한다.
		// new Color(230,126,34)
		CategoryPlot plot = chart.getCategoryPlot(); // 챠트의 Plot 객체를 구한다.
		plot.setBackgroundPaint(new Color(146, 21, 15)); // 챠트의 Plot 배경색을

		CategoryAxis axis = plot.getDomainAxis(); // 횡축 객체 구하기
		axis.setLowerMargin(0.03); // 횡축의 가장 왼쪽과 가장 왼쪽 봉과의 여백
		axis.setUpperMargin(0.03); // 횡축의 가장 오른쪽과 가장 오른쪽 봉과의 여백
		axis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));
		BarRenderer r = (BarRenderer) chart.getCategoryPlot().getRenderer(); // BarRenderer

		r.setSeriesPaint(0, new Color(230, 126, 34));
		return chart;
	}

	// getDataSet() 메서드. dataset 을 만들어서 리턴함 - getChart() 내에서 사용
	private DefaultCategoryDataset getDataSet(String startDay, String endDay, char ymd) {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

		try {
			model = new SalesModel();
			ArrayList<Order> list = null;
			switch (ymd) {
			case 'Y':
				list = model.yearChart(startDay, endDay);
				break;
			case 'M':
				list = model.monthChart(startDay, endDay);
				break;
			case 'D':
				list = model.dayChart(startDay, endDay);
			}

			for (Order vo : list) {
				// System.out.println(vo.getTotal_Price());
				dataSet.addValue(vo.getTotal_Price(), "Sales Of Day", vo.getOrder_Time());
			}
		} catch (Exception ex) {

		}

		return dataSet;
	}

}
