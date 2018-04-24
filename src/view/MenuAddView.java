package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.MenuModel;
import vo.Menu;

public class MenuAddView extends JFrame implements ActionListener {

	JLabel img_Area;
	JButton btn_OpenImg, btn_Add, btn_Cancel;
	JTextField tf_Categori, tf_MenuName, tf_Price, tf_Scr;

	MenuModel model;

	public MenuAddView() {
		addLayout();
		initStyle();
		connectDB();
		eventProc();
	}

	void addLayout() {
		setLayout(new BorderLayout());

		img_Area = new JLabel();
		btn_OpenImg = new JButton("아이콘 등록");

		tf_Categori = new JTextField(5);
		tf_MenuName = new JTextField(5);
		tf_Price = new JTextField(5);
		tf_Scr = new JTextField(5);

		btn_Add = new JButton("등록");
		btn_Cancel = new JButton("취소");

		JPanel p_center = new JPanel();
		p_center.setLayout(new GridLayout(1, 2));

		JPanel p_center_west = new JPanel();
		p_center_west.setLayout(null);
		p_center_west.add(img_Area);
		p_center_west.add(btn_OpenImg);
		img_Area.setBounds(0, 0, 150, 150);
		btn_OpenImg.setBounds(0, 250, 150, 45);

		JPanel p_center_east = new JPanel();
		p_center_east.setLayout(new GridLayout(4, 2));
		p_center_east.add(new JLabel("분류"));
		p_center_east.add(tf_Categori);
		p_center_east.add(new JLabel("메뉴이름"));
		p_center_east.add(tf_MenuName);
		p_center_east.add(new JLabel("가격"));
		p_center_east.add(tf_Price);
		p_center_east.add(new JLabel("파일명"));
		p_center_east.add(tf_Scr);

		p_center.add(p_center_west);
		p_center.add(p_center_east);

		JPanel p_south = new JPanel();
		p_south.setLayout(new GridLayout(1,2));
		// 프레인 아래쪽
		p_south.add(btn_Add);
		//btn_Add.setBackground(bg);
		p_south.add(btn_Cancel);
		// 프레임 전체
		add(p_center, BorderLayout.CENTER);
		add(p_south, BorderLayout.SOUTH);

		setDefaultCloseOperation(this.HIDE_ON_CLOSE);
		setSize(700, 700);
		setTitle("Add Menu");
		setVisible(true);
	}

	void initStyle() {

	}

	void connectDB() {
		try {
			model = new MenuModel();
			System.out.println("메뉴 추가 연결성공");
		} catch (Exception e) {
			System.out.println("메뉴 추가 연결실패");
			e.printStackTrace();
		}
	}

	void eventProc() {
		btn_OpenImg.addActionListener(this);
		btn_Add.addActionListener(this);
		btn_Cancel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();

		if (evt == btn_OpenImg) {
			JFileChooser fc = new JFileChooser("C:\\workspace\\BurgerKing\\src\\img");
			int result = fc.showOpenDialog(null);
			String path = null;
			String fileName = null;

			if (result == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				path = file.getPath();
				fileName = file.getName();
				try {
					FileReader fr = new FileReader(file);
					int data;
					do {
						data = fr.read();
						tf_Scr.setText(fileName);
						img_Area.setIcon(new ImageIcon(path));
					} while (data != -1);

					fr.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}

			}

		} else if (evt == btn_Add) {
			insertMenu();
		} else if (evt == btn_Cancel) {
			hide();
		}

	}

	void insertMenu() {
		Menu vo = new Menu();
		vo.setMenu_Name(tf_MenuName.getText());
		vo.setPrice(Integer.parseInt(tf_Price.getText()));
		vo.setCategori(Integer.parseInt(tf_Categori.getText()));
		vo.setSrc(tf_Scr.getText());

		try {
			model.insertMenu(vo);
		} catch (Exception e) {
			System.out.println("메뉴 등록 실패 : " + e.getMessage());
		}
		JOptionPane.showMessageDialog(null, "메뉴 등록 성공");
	}

}
