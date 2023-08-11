package GUI;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainPOS extends JFrame {
	public POS_pos  pos = null;
	public POS_StockMangement stockMangement = null;

	public static void main(String[] args) throws SQLException {
		MainPOS mainPOS = new MainPOS();
		mainPOS.setTitle("POS 시스템");
		
		mainPOS.pos = new POS_pos();
		mainPOS.stockMangement = new POS_StockMangement();
		
		
		JTabbedPane jtab = new JTabbedPane();
		
		jtab.add("POS", mainPOS.pos);
		jtab.add("재고관리", mainPOS.stockMangement);
		
		mainPOS.add(jtab);
		
		mainPOS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPOS.setSize(550,400);
		mainPOS.setVisible(true);
	}

}
