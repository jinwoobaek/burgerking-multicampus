package main;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import view.EmployeeView;
import view.OrderView;
import view.SalesView;
import view.StockView;

public class BurgerKing {

	public static JFrame f;
	public static CardLayout card;
	JPanel btnPanel;
	public static JPanel cardPanel;
	JLabel btn_OrderView, btn_StockView, btn_SalesView, btn_EmployeeView;
	JLabel img_Top, img_Bot;

	EmployeeView empView;
	OrderView orderView;
	StockView stockView;
	SalesView salesView;

	public BurgerKing() {
		addLayout();
		eventProc();

	}

	void eventProc() {
		btn_OrderView.addMouseListener(mlsner);
		btn_EmployeeView.addMouseListener(mlsner);
		btn_SalesView.addMouseListener(mlsner);
		btn_StockView.addMouseListener(mlsner);

	}

	void addLayout() {
		f = new JFrame(); // 메인 프레임
		f.setLocation(120, 130);
		card = new CardLayout();
		btnPanel = new JPanel(); // 버튼패널
		btn_OrderView = new JLabel();
		btn_StockView = new JLabel();
		btn_SalesView = new JLabel();
		btn_EmployeeView = new JLabel();

		btnPanel.setLayout(null);
		btnPanel.setBackground(new Color(146, 21, 15));
		btnPanel.add(btn_OrderView);
		btn_OrderView.setBounds(40, 180, 250, 250);
		btnPanel.add(btn_StockView);
		btn_StockView.setBounds(280, 180, 250, 250);
		btnPanel.add(btn_SalesView);
		btn_SalesView.setBounds(520, 180, 250, 250);
		btnPanel.add(btn_EmployeeView);
		btn_EmployeeView.setBounds(770, 180, 250, 260);

		img_Top = new JLabel();
		img_Top.setBounds(0, 0, 1050, 105);
		img_Top.setIcon(new ImageIcon("./src/img/maintop.png"));
		img_Top.setBorder(new LineBorder(new Color(204, 204, 204), 1));
		btnPanel.add(img_Top);

		img_Bot = new JLabel();
		img_Bot.setBounds(0, 695, 1050, 54);
		img_Bot.setIcon(new ImageIcon("./src/img/mainbot.PNG"));
		btnPanel.add(img_Bot);

		btn_OrderView.setIcon(new ImageIcon("./src/img/order_normal.PNG"));
		btn_StockView.setIcon(new ImageIcon("./src/img/stock_normal.PNG"));
		btn_SalesView.setIcon(new ImageIcon("./src/img/sales_normal.PNG"));
		btn_EmployeeView.setIcon(new ImageIcon("./src/img/employee_normal.PNG"));

		cardPanel = new JPanel(card);
		cardPanel.add(btnPanel, "btnPanel");

		empView = new EmployeeView();
		orderView = new OrderView();
		stockView = new StockView();
		salesView = new SalesView();

		cardPanel.add(empView, "empView");
		cardPanel.add(orderView, "orderView");
		cardPanel.add(stockView, "stockView");
		cardPanel.add(salesView, "salesView");
		f.add(cardPanel);

		f.setSize(1050, 700);
		f.setVisible(true);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	MouseListener mlsner = new MouseAdapter() {

		@Override
		public void mouseExited(MouseEvent e) {
			Object evt = e.getSource();

			if (evt == btn_OrderView) {
				btn_OrderView.setIcon(new ImageIcon("./src/img/order_normal.PNG"));
			} else if (evt == btn_StockView) {
				btn_StockView.setIcon(new ImageIcon("./src/img/stock_normal.PNG"));
			} else if (evt == btn_SalesView) {
				btn_SalesView.setIcon(new ImageIcon("./src/img/sales_normal.PNG"));
			} else if (evt == btn_EmployeeView) {
				btn_EmployeeView.setIcon(new ImageIcon("./src/img/employee_normal.PNG"));
			}

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			Object evt = e.getSource();

			if (evt == btn_OrderView) {
				btn_OrderView.setIcon(new ImageIcon("./src/img/order_pushed.PNG"));
			} else if (evt == btn_StockView) {
				btn_StockView.setIcon(new ImageIcon("./src/img/stock_pushed.PNG"));
			} else if (evt == btn_SalesView) {
				btn_SalesView.setIcon(new ImageIcon("./src/img/sales_pushed.png"));
			} else if (evt == btn_EmployeeView) {
				btn_EmployeeView.setIcon(new ImageIcon("./src/img/employee_pushed.PNG"));
			}

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			Object evt = e.getSource();

			if (evt == btn_OrderView) {
				card.show(cardPanel, "orderView");
				f.setSize(850, 800);

			} else if (evt == btn_StockView) {
				card.show(cardPanel, "stockView");

			} else if (evt == btn_SalesView) {
				card.show(cardPanel, "salesView");
			} else if (evt == btn_EmployeeView) {
				card.show(cardPanel, "empView");
			}

		}
	};

	public static void main(String[] args) {
		BurgerKing burgerking = new BurgerKing();
	}

}
