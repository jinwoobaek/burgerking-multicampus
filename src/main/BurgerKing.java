package main;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import view.EmployeeView;
import view.OrderView;
import view.StockView;

public class BurgerKing implements ActionListener {

	public static JFrame f;
	public static CardLayout card;
	JPanel btnPanel;
	public static JPanel cardPanel;
	JButton btn_OrderView, btn_StockView, btn_SalesView, btn_EmployeeView;

	EmployeeView empView;
	OrderView orderView;
	StockView stockView;

	public BurgerKing() {
		addLayout();
		eventProc();

	}

	void eventProc() {
		btn_OrderView.addActionListener(this);
		btn_StockView.addActionListener(this);
		btn_SalesView.addActionListener(this);
		btn_EmployeeView.addActionListener(this);
	}

	void addLayout() {
		f = new JFrame(); // 메인 프레임
		card = new CardLayout();
		btnPanel = new JPanel(); // 버튼패널
		btn_OrderView = new JButton("주문");
		btn_StockView = new JButton("재고");
		btn_SalesView = new JButton("매출");
		btn_EmployeeView = new JButton("직원");

		btnPanel.setLayout(new GridLayout(4, 1, 5, 5));
		btnPanel.add(btn_OrderView);
		btnPanel.add(btn_StockView);
		btnPanel.add(btn_SalesView);
		btnPanel.add(btn_EmployeeView);
		btnPanel.setSize(20, 15);

		cardPanel = new JPanel(card);
		cardPanel.add(btnPanel, "btnPanel");

		empView = new EmployeeView();
		orderView = new OrderView();
		stockView = new StockView();

		cardPanel.add(empView, "empView");
		cardPanel.add(orderView, "orderView");
		cardPanel.add(stockView, "stockView");
		f.add(cardPanel);

		f.setSize(800, 600);
		f.setVisible(true);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object evt = e.getSource();

		if (evt == btn_OrderView) {
			card.show(cardPanel, "orderView");
			f.setSize(850, 800);

		} else if (evt == btn_StockView) {
			card.show(cardPanel, "stockView");

		} else if (evt == btn_SalesView) {

		} else if (evt == btn_EmployeeView) {
			card.show(cardPanel, "empView");
		}

	}

	public static void main(String[] args) {
		BurgerKing burgerking = new BurgerKing();
	}

}
