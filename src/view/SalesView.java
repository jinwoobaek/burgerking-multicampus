package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import main.BurgerKing;
import model.SalesModel;

public class SalesView extends JPanel implements ActionListener {

	JButton btn_Home;
	JTextField tf_TotalSales, tf_TotalOrders;
	JRadioButton[] rb_TypeChoice;
	ButtonGroup group;
	Properties p;
	UtilDateModel startDateModel, endDateModel;
	JDatePanelImpl startDatePanel, endDatePanel;
	JDatePickerImpl startDatePicker, endDatePicker;

	SalesModel model;
	ChartView chart;

	ChartPanel cp;
	JPanel p_view = new JPanel();
	Calendar c = Calendar.getInstance();
	int year = c.get(Calendar.YEAR);
	int month = c.get(Calendar.MONTH);
	int day = c.get(Calendar.DATE);

	String startDay, endDay;
	char ymd;

	public SalesView() {
		addLayout();
		initStyle();
		connectDB();
		eventProc();
	}

	public void initStyle() {
	}

	void addLayout() {

		btn_Home = new JButton("홈으로");
		tf_TotalSales = new JTextField(15);
		tf_TotalOrders = new JTextField(15);

		// 위쪽
		JPanel p_north = new JPanel();
		p_north.setLayout(new BorderLayout());
		// 위>왼쪽
		JPanel p_north_west = new JPanel();
		p_north_west.setLayout(new GridLayout(2, 1));

		// 위>왼쪽>위 picker달력
		JPanel p_north_west_north = new JPanel();
		p_north_west_north.setLayout(new GridLayout(1, 2));
		p = new Properties();

		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");

		startDateModel = new UtilDateModel();
		endDateModel = new UtilDateModel();

		startDatePanel = new JDatePanelImpl(startDateModel, p);
		endDatePanel = new JDatePanelImpl(endDateModel, p);
		startDatePicker = new JDatePickerImpl(startDatePanel, new DateLabelFormatter());
		endDatePicker = new JDatePickerImpl(endDatePanel, new DateLabelFormatter());

		startDateModel.setDate(year, month, day);
		startDateModel.setSelected(true);
		endDateModel.setDate(year, month, day);
		endDateModel.setSelected(true);

		p_north_west_north.add(startDatePicker, BorderLayout.NORTH);
		p_north_west_north.add(endDatePicker, BorderLayout.SOUTH);

		// 위>왼쪽>아래
		JPanel p_north_west_south = new JPanel();
		p_north_west_south.setLayout(new GridLayout(1, 3));
		// 라디오 버튼 패널
		JPanel p_rbp = new JPanel();
		p_rbp.setLayout(new GridLayout(1, 3));

		String[] rbTypeChoice = { "일", "월", "년" };
		group = new ButtonGroup();
		rb_TypeChoice = new JRadioButton[3];
		for (int i = 0; i < rb_TypeChoice.length; i++) {
			rb_TypeChoice[i] = new JRadioButton(rbTypeChoice[i]);
			group.add(rb_TypeChoice[i]);
			p_rbp.add(rb_TypeChoice[i]);
		}
		JPanel p_north_west_south_null = new JPanel();
		p_north_west_south.setLayout(new FlowLayout());
		p_north_west_south_null.add(new JLabel("분기를 선택 하십시오."));

		p_north_west_south.add(p_north_west_south_null);
		p_north_west_south.add(p_rbp);
		p_north_west_south.add(btn_Home);
		// 위>왼쪽>위아래>합체
		p_north_west.add(p_north_west_north);
		p_north_west.add(p_north_west_south);
		// 위>오른쪽
		JPanel p_north_east = new JPanel();
		p_north_east.setLayout(new GridLayout(2, 2));
		p_north_east.add(new JLabel("총매출"));
		p_north_east.add(new JLabel("주문 건수"));
		p_north_east.add(tf_TotalSales);
		p_north_east.add(tf_TotalOrders);
		p_north_east.setBorder(new TitledBorder("매출 관리"));

		p_north.add(p_north_west, BorderLayout.WEST);
		p_north.add(p_north_east, BorderLayout.EAST);

		add(p_north, BorderLayout.NORTH);
	}

	void connectDB() {
		try {
			model = new SalesModel();
			System.out.println("매출관리 연결성공");
		} catch (Exception e) {
			System.out.println("매출관리 연결실패");
			e.printStackTrace();
		}
	}

	void eventProc() {

		btn_Home.addActionListener(this);
		rb_TypeChoice[0].addActionListener(this);
		rb_TypeChoice[1].addActionListener(this);
		rb_TypeChoice[2].addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();

		if (evt == btn_Home) {
			BurgerKing.card.first(BurgerKing.cardPanel);
			BurgerKing.f.setSize(1050, 700);
		} else if (evt == rb_TypeChoice[0]) {
			p_view.removeAll();
			ymd = 'D';
			chartView();
			totalSalesDay();
		} else if (evt == rb_TypeChoice[1]) {
			p_view.removeAll();
			ymd = 'M';
			chartView();
		} else if (evt == rb_TypeChoice[2]) {
			p_view.removeAll();
			ymd = 'Y';
			chartView();
		}

	}

	// 차트 붙이기
	void chartView() {
		startDay = String.valueOf(startDateModel.getYear()) + "/" + String.valueOf(startDateModel.getMonth() + 1) + "/"
				+ String.valueOf(startDateModel.getDay());

		endDay = String.valueOf(endDateModel.getYear()) + "/" + String.valueOf(endDateModel.getMonth() + 1) + "/"
				+ String.valueOf(endDateModel.getDay());

		chart = new ChartView();
		JFreeChart chart_result = chart.getChart(startDay, endDay, ymd);
		cp = new ChartPanel(chart_result);
		cp.setVisible(true);

		p_view.add(cp);
		add(p_view, BorderLayout.CENTER);
		validate();
	}

	void totalSalesDay() {
		try {
			model = new SalesModel();
			int totalmoney = model.totalSalesDay();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
