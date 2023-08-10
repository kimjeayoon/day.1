package Insa;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CRUDGUI {
   JFrame jframe = new JFrame();
   JPanel jpanel = new JPanel();
   JTextField tf1 = new JTextField();
   JTextField tf2 = new JTextField();
   JTextArea ta = new JTextArea();
   JButton btn1, btn2, btn3,btn4,btn5;
   JLabel jl1 = new JLabel("NAME : ");
   JLabel jl2 = new JLabel("AGE : ");
   
   public CRUDGUI() {
      GUI_init();
   }
   
   public void GUI_init() {
      jframe.setBounds(50,50,500,330);  // 전체 창 크기
      jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      jpanel.setLayout(null);
      jframe.add(jpanel);
      
      jframe.setVisible(true);
      
      // 입력공간
      tf1.setBounds(90,25,70,25);
      jpanel.add(tf1);  // 이름 입력 공간
      tf2.setBounds(215,25,70,25);
      jpanel.add(tf2);  // 나이 입력 공간
      
      jl1.setBounds(37,21,70,30);
      jpanel.add(jl1);  // 이름 라벨
      jl2.setBounds(170,21,70,30);
      jpanel.add(jl2);  // 나이 라벨
      
      // 입력한 글이 보이는 창
      JScrollPane jsp = new JScrollPane(ta); // 창 스크롤
      jsp.setBounds(20,70,300,200);
      jpanel.add(jsp);
            
      // 입력버튼
      jpanel.add(btn1 = new JButton("입력"));
      btn1.setBounds(350, 70, 100, 30); 
      
      // 출력버튼
      jpanel.add(btn2 = new JButton("출력"));
      btn2.setBounds(350, 110, 100, 30); 
      
      // 수정버튼
      jpanel.add(btn3 = new JButton("수정"));
      btn3.setBounds(350, 150, 100, 30);
      
      // 삭제버튼
      jpanel.add(btn4 = new JButton("삭제"));
      btn4.setBounds(350, 190, 100, 30);
      
      // 종료버튼
      jpanel.add(btn5 = new JButton("종료"));
      btn5.setBounds(350, 230, 100, 30); 
      
      InsaDAO dao = new InsaDAO();
      
      //입력 버튼 이벤트
      btn1.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ta.setText("");
			String name = tf1.getText();
			int age = Integer.parseInt(tf2.getText());
			dao.insertData(new InsaDTO(name,age));
			ta.append("입력완료 \n");
			tf1.setText("");
			tf2.setText("");
		}
      });
      
      //출력 버튼 이벤트
      btn2.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ta.setText("");
			ArrayList<InsaDTO> arr = new ArrayList<InsaDTO>();
			arr = dao.readData();
			ta.append("name" + "\t" + "age" + "\n");
			ta.append("---------------------------\n");
			
			for(int i = 0; i < arr.size(); i++) {
				ta.append(arr.get(i).getName() + "\t" + arr.get(i).getAge()+ "\n");
			}
			
		}
      });
      
      // 수정 버튼 이벤트
      btn3.addActionListener(new ActionListener() {
  		
		@Override
		public void actionPerformed(ActionEvent e) {
			ta.setText("");
			String name = tf1.getText();
			int age = Integer.parseInt(tf2.getText());
			
			dao.updateDate(new InsaDTO(name,age));
			ta.append("수정 완료 \n");
			tf1.setText("");
			tf2.setText("");
			
		}
      });
      
      // 삭제 버튼 이벤트
      btn4.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ta.setText("");
			
			String name = tf1.getText();
			dao.deleteData(name);
			ta.append("삭제 완료 \n");
			tf1.setText("");
			tf2.setText("");
			
		}
	});
   }

}


