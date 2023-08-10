package Insa;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JTabelEx {

	public static void main(String[] args) {
		Dimension dim = new Dimension(400,150);
		JFrame frame = new JFrame("JTable 예제");
		frame.setLocation(200,400);
		frame.setPreferredSize(dim);
		String header[] = {"학생이름", "국어","영어","수학"};
		String contents[][] = {
				{"박명수","90","80","98"},
				{"조국","10","80","98"},
				{"추미애","50","80","98"}
		};
		JTable table  = new JTable(contents, header);
		JScrollPane sp = new JScrollPane(table);
		frame.add(sp);
		
		table.setValueAt("200", 1, 1);
		System.out.println(table.getRowCount());
		System.out.println(table.getColumnCount());
		System.out.println(table.getColumnName(0));
		
		frame.pack();
		frame.setVisible(true);
	}

}
