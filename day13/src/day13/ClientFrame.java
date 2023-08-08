package day13;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientFrame extends JFrame implements ActionListener{
   JTextArea txtA = new JTextArea();
   JTextField txtF = new JTextField(15);
   
   JButton btnTransfer = new JButton("전송");
   JButton btnExit = new JButton("닫기");
   boolean ifFirst = true;
   JPanel p1 = new JPanel();
   Socket socket;
   WriteThread wt;
   
   
   public ClientFrame(Socket socket) {
      setTitle("채팅 프로그램");
      this.socket = socket;
      wt = new WriteThread(this);
      new Id(wt, this);
      
      add("Center", txtA);
      p1.add(txtF);
      p1.add(btnTransfer);
      p1.add(btnExit);
      add("South", p1);
      
      
      // 메세지를 전송하는 클래스 생성
      btnTransfer.addActionListener(this);
      btnExit.addActionListener(this);
      
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setBounds(300,300, 350,350);
      setVisible(true);
      
   }
   
   
   
   @Override
   public void actionPerformed(ActionEvent e) {
      String id = Id.getId();
      if(e.getSource() == btnTransfer) {
         // 전송버튼을 누른경우
         if(txtF.getText().equals("")) {
            // 메세지 없이 전송버튼만 눌렀을 경우
            return;
         }
         
         txtA.append("[" + id + "]" + txtF.getText()+ "\n");
         wt.sendMsg();
         txtF.setText("");
         
      }else {
         this.dispose();
      }
      
   }
   
   

}


class Id extends JFrame implements ActionListener{
   static JTextField tf = new JTextField(8);
   JButton btn = new JButton("입력");
   
   WriteThread wt;
   ClientFrame cf;
   public Id() {
      
   }
   
   
   
   
   public Id(WriteThread wt, ClientFrame cf) {

      this.wt = wt;
      this.cf = cf;
      
      setLayout(new FlowLayout());
      
      add(new JLabel("아이디 : "));
      add(tf);
      add(btn);
      
      btn.addActionListener(this);
      
      setBounds(300,300,250,100);
      setVisible(true);
      
   }




   @Override
   public void actionPerformed(ActionEvent e) {
      wt.sendMsg();
      cf.ifFirst = false;
      cf.setVisible(true);
      this.dispose();
      
   }
   
   static public String getId() {
      return tf.getText();
   }
   
}

