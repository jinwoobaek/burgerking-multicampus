package main;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.EmployeeView;
import view.OrderView;
import view.StockView;

public class BurgerKing implements ActionListener {

	public static JFrame f;
	public static CardLayout card;
	JPanel btnPanel;
	public static JPanel cardPanel;
	JButton btn_OrderView, btn_StockView, btn_SalesView, btn_EmployeeView;
	JLabel img_Top;

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

		btnPanel.setLayout(null);
		btnPanel.add(btn_OrderView);
		btn_OrderView.setBounds(240, 250, 150, 50);
		btnPanel.add(btn_StockView);
		btn_StockView.setBounds(430, 250, 150, 50);
		btnPanel.add(btn_SalesView);
		btn_SalesView.setBounds(240, 320, 150, 50);
		btnPanel.add(btn_EmployeeView);
		btn_EmployeeView.setBounds(430, 320, 150, 50);
		
		img_Top = new JLabel();
		img_Top.setBorder(new EmptyBorder(5, 5, 5, 5));
		img_Top.setBounds(0,0, 800, 105);
		img_Top.setIcon(new ImageIcon("./src/img/maintop.PNG"));
		btnPanel.add(img_Top);

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
