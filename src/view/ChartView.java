package view;

import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import model.SalesModel;
import vo.Order;

public class ChartView extends JPanel {

	SalesModel model;

	public ChartView() {
	}

	// getChart() 메서드. Chart 를 만들어서 리턴함
	public JFreeChart getChart(String startDay, String endDay, char ymd) {

		JFreeChart chart = ChartFactory.createLineChart("매출 관리", // title			
				"Date", // categoryAxisLabel
				"Sales Of Day", // valueAxisLabel
				getDataSet(startDay, endDay, ymd), // dataset
				PlotOrientation.VERTICAL, // orientation
				true, // legend
				true, // tooltips
				false); // url

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
