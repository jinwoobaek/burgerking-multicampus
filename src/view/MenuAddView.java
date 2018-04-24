package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuAddView extends JFrame {

	JLabel img_Area;
	JButton btn_OpenImg ,btn_Add, btn_Cancel;
	JTextField tf_Categori, tf_MenuName, tf_Price, tf_Scr;

	public MenuAddView() {
		addLayout();
		initStyle();
		connectDB();
		eventProc();
	}

	void addLayout() {
		setLayout(new BorderLayout());
		
		img_Area = new JLabel();
		btn_OpenImg = new JButton("파일 열기");
		
		tf_Categori = new JTextField(5);
		tf_MenuName = new JTextField(5);
		tf_Price = new JTextField(5);
		tf_Scr = new JTextField(5);
		
		btn_Add = new JButton("등록");
		btn_Cancel = new JButton("취소");
		
		JPanel p_south = new JPanel();
		p_south.setLayout(new FlowLayout());
		//프레인 아래쪽
		p_south.add(btn_Add);
		p_south.add(btn_Cancel);
		// 프레임 전체
		add(p_south, BorderLayout.SOUTH);
		
		setTitle("Add Menu");
		setVisible(true);
	}

	void initStyle() {

	}

	void connectDB() {

	}

	void eventProc() {

	}
}
