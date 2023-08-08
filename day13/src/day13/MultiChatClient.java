package day13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class MultiChatClient {

   public static void main(String[] args) {
      
      Socket socket = null;
      
      ClientFrame cf;
      
      try {
         socket = new Socket("localhost", 9001);
         System.out.println("접속 성공");
         cf = new ClientFrame(socket);
         new ReadThread(socket, cf).start();
      }catch(Exception e) {
         
      }
      
      

   }

}

// 서버가 보내온 문자열을 전송받는 스레드
class ReadThread extends Thread{
   Socket socket;
   ClientFrame cf;
   
   
   
   public ReadThread(Socket socket, ClientFrame cf) {
      this.socket = socket;
      this.cf = cf;
   }



   @Override
   public void run() {
      BufferedReader br = null;
      try {
         // 서버로 부터 전송된 문자열을 읽어오기 위한 스크림 객체 생성
         br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         
         while(true) {
            // 소켓으로 부터 문자열 읽어옴
            String str = br.readLine();
            if(str == null) {
               System.out.println("접속이 끊겼음");
               break;
            }
            // 전송받은 문자열 화면에 출력
            cf.txtA.append(str+"\n");
            
         }
         
      }catch(Exception e) {
         
      }finally {
         try {
            if(br != null)
               br.close();
            if(socket != null)
               socket.close();
         }catch(Exception e) {
            
         }
      }
            
   }
}


// 키보드로 전송 문자열 입력받아 서버로 전송하는 스레드

class WriteThread{
   
   Socket socket;
   ClientFrame cf;
   String str;
   String id;
   
   public WriteThread(ClientFrame cf) {
      this.cf = cf;
      this.socket = cf.socket;
   }
   
   public void sendMsg() {
      // 키보드로부터 읽어오기 위한 스트림 객체 생성
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter pw = null;
      try {
         // 서버로 문자열 전송하기 위한 스트림 객체 생성
         pw = new PrintWriter(socket.getOutputStream(), true);
         
         // 첫번째 데이터는 id이며, 상대방에게 id와 함께 내 IP를 전송함
         if(cf.ifFirst == true) {
            InetAddress iaddr = socket.getLocalAddress();
            String ip = iaddr.getHostAddress();
            getId();
            System.out.println("iP : " + ip + " ID : " + id);
            str = "["+id+"] 님 로그인(" +ip +")";
         }else {
            str = "["+id+"]" + cf.txtF.getText();
         }
         
         // 입력 받은 문자열 서버로 보냄
         pw.println(str);
         
      }catch(Exception e) {
         
      }finally {
         try {
            if(br != null)
               br.close();
            /*
            if(pw != null)
               pw.close();
            if(socket != null)
               socket.close();
               */
         }catch(Exception e) {
            
         }
      }
   }
   
   public void getId() {
      id = Id.getId();
   }
   
   
}

